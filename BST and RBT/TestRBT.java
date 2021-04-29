import static org.junit.Assert.fail;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;

// TODO: Add tests to test if a Red-Black tree 
// extension of BST is correct.  Mostly check node color and position

//@SuppressWarnings("rawtypes")
public class TestRBT  {

    protected RBT<Integer,String> rbt;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
         rbt = new RBT<Integer,String>();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    /** 
     * CASE 123 Insert three values in sorted order and then check 
     * the root, left, and right keys to see if RBT rebalancing 
     * occurred.
     * 
     */
    @Test
    void testRBT_001_insert_sorted_order_simple() {
        try {
            
          rbt.insert(10, "10"); 
          Assert.assertTrue(rbt.rootIsBlack());            
                        
            rbt.insert(20, "20");            
            
            Assert.assertTrue(rbt.getKeyOfRightChildOf(10).equals(20)) ;
            Assert.assertEquals(rbt.colorOf(20),RBT.RED);
            
            rbt.insert(30, "30");  // SHOULD CAUSE REBALANCING
            Assert.assertTrue(rbt.getKeyOfRightChildOf(20).equals(30));
            
            
            rbt.print();
            
            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child
            Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
            Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
            Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

            rbt.print();
            
        } catch (Exception e) {
            //e.printStackTrace();
            fail( "Unexpected exception: "+e.getMessage() );
        }
    }

    /** 
     * CASE 321 Insert three values in reverse sorted order and then check 
     * the root, left, and right keys to see if rebalancing 
     * occurred in the other direction.
     */
    @Test
    void testRBT_002_insert_reversed_sorted_order_simple() {
      
    try {
      rbt.insert(10, "30"); 
      Assert.assertTrue(rbt.rootIsBlack());            
                    
        rbt.insert(20, "20");            
        
        Assert.assertTrue(rbt.getKeyOfRightChildOf(10).equals(20)) ;
        Assert.assertEquals(rbt.colorOf(20),RBT.RED);
        
        rbt.insert(30, "10");  // SHOULD CAUSE REBALANCING
        Assert.assertTrue(rbt.getKeyOfRightChildOf(20).equals(30));
        
        
        rbt.print();
        
        // IF rebalancing is working,
        // the tree should have 20 at the root
        // and 10 as its left child and 30 as its right child
        Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
        Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
        Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

        rbt.print();
        
    } catch (Exception e) {
        //e.printStackTrace();
        fail( "Unexpected exception: "+e.getMessage() );
    }
        
    }
//
    /** 
     * CASE 132 Insert three values so that rebalancing requires new key 
     * to be the "new" root of the rebalanced tree.
     * 
     * Then check the root, left, and right keys to see if rebalancing 
     * occurred correctly.
     */
    @Test
    void testRBT_003_insert_smallest_largest_middle_order_simple() {
      
    try {  
      rbt.insert(10, "10"); 
      Assert.assertTrue(rbt.rootIsBlack());            
                    
        rbt.insert(20, "30");            
        
        Assert.assertTrue(rbt.getKeyOfRightChildOf(10).equals(20)) ;
        Assert.assertEquals(rbt.colorOf(20),RBT.RED);
        
        rbt.insert(30, "20");  // SHOULD CAUSE REBALANCING
        Assert.assertTrue(rbt.getKeyOfRightChildOf(20).equals(30));
        
       
        rbt.print();
        
        // IF rebalancing is working,
        // the tree should have 20 at the root
        // and 10 as its left child and 30 as its right child
        Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
        Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
        Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

        rbt.print();
        
    } catch (Exception e) {
        //e.printStackTrace();
        fail( "Unexpected exception: "+e.getMessage() );
    }
        
    }

    /** 
     * CASE 312 Insert three values so that rebalancing requires new key 
     * to be the "new" root of the rebalanced tree.
     * 
     * Then check the root, left, and right keys to see if rebalancing 
     * occurred correctly.
     */
    @Test
    void testRBT_004_insert_largest_smallest_middle_order_simple() {
        
      try {  
        rbt.insert(10, "30"); 
        Assert.assertTrue(rbt.rootIsBlack());            
                      
          rbt.insert(20, "10");            
          
          Assert.assertTrue(rbt.getKeyOfRightChildOf(10).equals(20)) ;
          Assert.assertEquals(rbt.colorOf(20),RBT.RED);
          
          rbt.insert(30, "20");  // SHOULD CAUSE REBALANCING
          Assert.assertTrue(rbt.getKeyOfRightChildOf(20).equals(30));
          
         
          rbt.print();
          
          // IF rebalancing is working,
          // the tree should have 20 at the root
          // and 10 as its left child and 30 as its right child
          Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
          Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
          Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

          rbt.print();
          
      } catch (Exception e) {
          //e.printStackTrace();
          fail( "Unexpected exception: "+e.getMessage() );
      }
        
    }
    
    
    /**
     * Adding two items and removing three, checking if error is thrown.
     */
    @Test
    void testBST_005_remove_more_than_inserted() {
        try {
            rbt.insert(10, "10");
            if (!rbt.getKeyAtRoot().equals(10))
                fail("insert at root does not work");
            
            rbt.insert(20, "20");     
            
            if (rbt.getKeyOfRightChildOf(10)!=20)
            {               
              fail("insert to right child of root does not work");
            }
            rbt.remove(20);     
            
            if (!rbt.getKeyAtRoot().equals(10)) 
                fail("removing 20 changed root");            
            
             try {              
              assert(rbt.getKeyOfRightChildOf(10)!=20);
             }              
           
            catch (NullPointerException e)
            {
              
            }
                        
        
            rbt.remove(10);
            try {    
              if (!rbt.getKeyAtRoot().equals(null));
              fail("10 was not removed as null pointer wasn't thrown");
                
          }
          catch (NullPointerException e)
          {
            
          }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception: "+e.getMessage() );
        }
    }
    
    
    
    /** 
     * adds 30,10,20,40,35 in the BST
     * removes 40 and adds 34 and checks height
     * adds 32 and checks height.
     * 
     */
    @Test
    void testBST_007_test_traversals_() {
        try {
            rbt.insert(30, "30");
            if (!rbt.getKeyAtRoot().equals(30))
                fail("insert at root does not work");
            
            rbt.insert(10, "10");
            if (!rbt.getKeyOfLeftChildOf(30).equals(10)) 
                fail("insert to left child of root does not work");
            
            rbt.insert(20, "20");
            if (!rbt.getKeyAtRoot().equals(30)) 
                fail("inserting 10 changed root");

            if (!rbt.getKeyOfRightChildOf(10).equals(20)) 
                fail("inserting 20 as right child of 10");
            
            rbt.insert(40, "40");
            if (!rbt.getKeyOfRightChildOf(30).equals(40)) 
              fail("insert to right child of root does not work");
            
            rbt.insert(35, "35");
            if (!rbt.getKeyOfLeftChildOf(40).equals(35)) 
              fail("insert to Left child of root does not work");
            
            
            rbt.remove(40);
            if (!rbt.getKeyOfRightChildOf(30).equals(35)) 
              fail("Removing 40 didn't work.");
            
            
            
            rbt.insert(34, "34");
            if(!rbt.getKeyOfLeftChildOf(35).equals(34)) 
              fail("inserting 34 didn't woxxrk.");
            
           
            
            rbt.insert(32, "32");
            if(!rbt.getKeyOfLeftChildOf(34).equals(32)) 
              fail("inserting 32 didn't work.");
            
         
            rbt.print();
            
            List<Integer> arrInOrder = rbt.getInOrderTraversal();
            List<Integer> arrPreOrder = rbt.getPreOrderTraversal();
            List<Integer> arrPostOrder = rbt.getPostOrderTraversal();
            List<Integer> arrLevelOrder = rbt.getLevelOrderTraversal();
            
            String strInOrder = "";
            String strPreOrder = "";
            String strPostOrder = "";
            String strLevelOrder = "";
            
            for(int i=0; i<arrInOrder.size(); i++)
            {
              strInOrder += arrInOrder.get(i) + " ";
              strPreOrder += arrPreOrder.get(i) + " ";
              strPostOrder += arrPostOrder.get(i) + " ";
              strLevelOrder += arrLevelOrder.get(i) + " ";
            }
            
            
            if(!strInOrder.equals("10 20 30 32 34 35 "))
              fail("InOrder traversal failed");
            
            if(!strPreOrder.equals("20 10 34 30 32 35 "))
              fail("PreOrder traversal failed");
          
            if(!strPostOrder.equals("10 132 30 35 34 20 "))
              fail("PostOrder traversal failed");
            
            if(!strLevelOrder.equals("20 10 34 30 35 32 "))
              fail("LevelOrder traversal failed");

            rbt.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception: "+e.getMessage() );
        }
    }
    
    
    
    /** 
     * adds 30,10,20,40,35 in the BST
     * removes 40 and adds 50
     * 
     */
    @Test
    void testBST_006_several_insert_and_remove_() {
        try {
            rbt.insert(30, "30");
            if (!rbt.getKeyAtRoot().equals(30))
                fail("insert at root does not work");
            
            rbt.insert(10, "10");
            if (!rbt.getKeyOfLeftChildOf(30).equals(10)) 
                fail("insert to left child of root does not work");
            
            rbt.insert(20, "20");
            if (!rbt.getKeyAtRoot().equals(30)) 
                fail("inserting 10 changed root");

            if (!rbt.getKeyOfRightChildOf(10).equals(20)) 
                fail("inserting 20 as right child of 10");
            
            rbt.insert(40, "40");
            if (!rbt.getKeyOfRightChildOf(30).equals(40)) 
              fail("insert to right child of root does not work");
            
            rbt.insert(35, "35");
            if (!rbt.getKeyOfLeftChildOf(40).equals(35)) 
              fail("insert to Left child of root does not work");
            
            
            rbt.remove(40);
            if (!rbt.getKeyOfRightChildOf(30).equals(35)) 
              fail("Removing 40 didn't work.");
            
            
            
            rbt.insert(34, "34");
            if(!rbt.getKeyOfLeftChildOf(35).equals(34)) 
              fail("inserting 34 didn't work.");            
            
            
            
            if(rbt.getHeight()!=3)
              fail("Height is not 3");
            
            rbt.insert(32, "32");
            if(!rbt.getKeyOfLeftChildOf(34).equals(32)) 
              fail("inserting 32 didn't work.");
            
            if(rbt.getHeight()!=4)
              fail("Height is not 4");
            

            rbt.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception: "+e.getMessage() );
        }
    }
        
    
    
    // TODO: Add your own tests
    
    // Add tests to make sure that rebalancing occurs even if the 
    // tree is larger.   Does it maintain it's balance?
    // Does the height of the tree reflect it's actual height
    // Use the traversal orders to check.
    
    // Can you insert many and still "get" them back out?
    
    // Does delete work?  Does the tree maintain balance when a key is deleted?
    // If delete is not implemented, does calling it throw an UnsupportedOperationException

} // copyright Deb Deppeler, all rights reserved, DO NOT SHARE

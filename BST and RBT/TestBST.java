import static org.junit.Assert.fail;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//@SuppressWarnings("rawtypes")
public class TestBST {

    protected STADT<Integer,String> bst;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
         bst = new BST<Integer,String>();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    /** 
     * CASE 123 Insert three values in sorted order and then check 
     * the root, left, and right keys to see if insert worked 
     * correctly.
     */
    @Test
    void testBST_001_insert_sorted_order_simple() {
        try {
            bst.insert(10, "10");
            if (!bst.getKeyAtRoot().equals(10))
                fail("insert at root does not work");
            
            bst.insert(20, "20");
            if (!bst.getKeyOfRightChildOf(10).equals(20)) 
                fail("insert to right child of root does not work");
            
            bst.insert(30, "30");
            if (!bst.getKeyAtRoot().equals(10)) 
                fail("inserting 30 changed root");

            if (!bst.getKeyOfRightChildOf(20).equals(30)) 
                fail("inserting 30 as right child of 20");

            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(10));
            Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(20));
            Assert.assertEquals(bst.getKeyOfRightChildOf(20), Integer.valueOf(30));

            bst.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception: "+e.getMessage() );
        }
    }

    /** 
     * CASE 321 Insert three values in reverse sorted order and then check 
     * the root, left, and right keys to see if insert 
     * worked in the other direction.
     */
    @Test
    void testBST_002_insert_reversed_sorted_order_simple() {
        try {
            bst.insert(30, "30");
            if (!bst.getKeyAtRoot().equals(30))
                fail("insert at root does not work");
            
            bst.insert(20, "20");
            if (!bst.getKeyOfLeftChildOf(30).equals(20)) 
                fail("insert to left child of root does not work");
            
            bst.insert(10, "10");
            if (!bst.getKeyAtRoot().equals(30)) 
                fail("inserting 10 changed root");

            if (!bst.getKeyOfLeftChildOf(20).equals(10)) 
                fail("inserting 10 as left child of 20");

            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(30));
            Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(20));
            Assert.assertEquals(bst.getKeyOfLeftChildOf(20), Integer.valueOf(10));

            bst.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception: "+e.getMessage() );
        }
    }

    /** 
     * CASE 132 Insert three values so that rebalancing requires new key 
     * to be the "new" root of the rebalanced tree.
     * 
     * Then check the root, left, and right keys to see if insert 
     * occurred correctly.
     */
    @Test
    void testBST_003_insert_smallest_largest_middle_order_simple() {
        try {
            bst.insert(10, "10");
            if (!bst.getKeyAtRoot().equals(10))
                fail("insert at root does not work");
            
            bst.insert(30, "30");
            if (!bst.getKeyOfRightChildOf(10).equals(30)) 
                fail("insert to right child of root does not work");
            Assert.assertEquals(bst.getKeyOfRightChildOf(10),Integer.valueOf(30));
            
            bst.insert(20, "20");
            if (!bst.getKeyAtRoot().equals(10)) 
                fail("inserting 20 changed root");

            if (!bst.getKeyOfLeftChildOf(30).equals(20)) 
                fail("inserting 20 as left child of 30");

            // IF rebalancing is working,
            // the tree should have 20 at the root
            // and 10 as its left child and 30 as its right child

            Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(10));
            Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(30));
            Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(20));

            bst.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception: "+e.getMessage() );
        }
    }

    /** 
     * CASE 312 Insert three values so that rebalancing requires new key 
     * to be the "new" root of the rebalanced tree.
     * 
     * Then check the root, left, and right keys to see if insert 
     * occurred correctly.
     */
    @Test
    void testBST_004_insert_largest_smallest_middle_order_simple() {
        try {
            bst.insert(30, "30");
            if (!bst.getKeyAtRoot().equals(30))
                fail("insert at root does not work");
            
            bst.insert(10, "10");
            if (!bst.getKeyOfLeftChildOf(30).equals(10)) 
                fail("insert to left child of root does not work");
            
            bst.insert(20, "20");
            if (!bst.getKeyAtRoot().equals(30)) 
                fail("inserting 10 changed root");

            if (!bst.getKeyOfRightChildOf(10).equals(20)) 
                fail("inserting 20 as right child of 10");

            // the tree should have 30 at the root
            // and 10 as its left child and 20 as 10's right child

            Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(30));
            Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(10));
            Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(20));

            bst.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception: "+e.getMessage() );
        }
    }
    
    /**
     * Adding two items and removing three, checking if error is thrown.
     */
    @Test
    void testBST_005_remove_more_than_inserted() {
        try {
            bst.insert(10, "10");
            if (!bst.getKeyAtRoot().equals(10))
                fail("insert at root does not work");
            
            bst.insert(20, "20");     
            
            if (bst.getKeyOfRightChildOf(10)!=20)
            {               
              fail("insert to right child of root does not work");
            }
            bst.remove(20);     
            
            if (!bst.getKeyAtRoot().equals(10)) 
                fail("removing 20 changed root");            
            
             try {              
              assert(bst.getKeyOfRightChildOf(10)!=20);
             }              
           
            catch (NullPointerException e)
            {
              
            }
                        
        
            bst.remove(10);
            try {    
              if (!bst.getKeyAtRoot().equals(null));
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
     * removes 40 and adds 50
     * 
     */
    @Test
    void testBST_007_test_traversals_() {
        try {
            bst.insert(30, "30");
            if (!bst.getKeyAtRoot().equals(30))
                fail("insert at root does not work");
            
            bst.insert(10, "10");
            if (!bst.getKeyOfLeftChildOf(30).equals(10)) 
                fail("insert to left child of root does not work");
            
            bst.insert(20, "20");
            if (!bst.getKeyAtRoot().equals(30)) 
                fail("inserting 10 changed root");

            if (!bst.getKeyOfRightChildOf(10).equals(20)) 
                fail("inserting 20 as right child of 10");
            
            bst.insert(40, "40");
            if (!bst.getKeyOfRightChildOf(30).equals(40)) 
              fail("insert to right child of root does not work");
            
            bst.insert(35, "35");
            if (!bst.getKeyOfLeftChildOf(40).equals(35)) 
              fail("insert to Left child of root does not work");
            
            
            bst.remove(40);
            if (!bst.getKeyOfRightChildOf(30).equals(35)) 
              fail("Removing 40 didn't work.");
            
            
            
            bst.insert(34, "34");
            if(!bst.getKeyOfLeftChildOf(35).equals(34)) 
              fail("inserting 34 didn't woxxrk.");
            
           
            
            bst.insert(32, "32");
            if(!bst.getKeyOfLeftChildOf(34).equals(32)) 
              fail("inserting 32 didn't work.");
            
         
            bst.print();
            
            List<Integer> arrInOrder = bst.getInOrderTraversal();
            List<Integer> arrPreOrder = bst.getPreOrderTraversal();
            List<Integer> arrPostOrder = bst.getPostOrderTraversal();
            List<Integer> arrLevelOrder = bst.getLevelOrderTraversal();
            
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
            
            if(!strPreOrder.equals("30 10 20 35 34 32 "))
              fail("PreOrder traversal failed");
          
            if(!strPostOrder.equals("20 10 32 34 35 30 "))
              fail("PostOrder traversal failed");
            
            if(!strLevelOrder.equals("30 10 35 20 34 32 "))
              fail("LevelOrder traversal failed");

            bst.print();
            
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
            bst.insert(30, "30");
            if (!bst.getKeyAtRoot().equals(30))
                fail("insert at root does not work");
            
            bst.insert(10, "10");
            if (!bst.getKeyOfLeftChildOf(30).equals(10)) 
                fail("insert to left child of root does not work");
            
            bst.insert(20, "20");
            if (!bst.getKeyAtRoot().equals(30)) 
                fail("inserting 10 changed root");

            if (!bst.getKeyOfRightChildOf(10).equals(20)) 
                fail("inserting 20 as right child of 10");
            
            bst.insert(40, "40");
            if (!bst.getKeyOfRightChildOf(30).equals(40)) 
              fail("insert to right child of root does not work");
            
            bst.insert(35, "35");
            if (!bst.getKeyOfLeftChildOf(40).equals(35)) 
              fail("insert to Left child of root does not work");
            
            
            bst.remove(40);
            if (!bst.getKeyOfRightChildOf(30).equals(35)) 
              fail("Removing 40 didn't work.");
            
            
            
            bst.insert(34, "34");
            if(!bst.getKeyOfLeftChildOf(35).equals(34)) 
              fail("inserting 34 didn't work.");
            
            
            if(bst.getHeight()!=3)
              fail("Height is not 3");
            
            bst.insert(32, "32");
            if(!bst.getKeyOfLeftChildOf(34).equals(32)) 
              fail("inserting 32 didn't work.");
            
            if(bst.getHeight()!=4)
              fail("Height is not 4");
            

            bst.print();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail( "Unexpected exception: "+e.getMessage() );
        }
    }
        
    
    
   

   

}

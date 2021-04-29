import java.util.List;

public class CompareDS {

  public static void main(String[] args) throws IllegalNullKeyException, DuplicateKeyException {
    STADT<Integer,String> bst;
    bst = new BST<Integer,String>();
    STADT<Integer,String> rbt;
    rbt = new RBT<Integer,String>();
    
    //100 inputs
    System.out.println("CompareDS is comparing work time for DS_My and DS_Srivatsan");
    System.out.println("Description: Comparing insert and get times for 100 inputs");
    long startTime = System.nanoTime();
    for(int i=0;i<100;i++)
    {
      bst.insert(i+1, Integer.toString(i+1));
    }
    List<Integer> arrInOrder = bst.getInOrderTraversal();    
    
    String strInOrder = "";
    for(int i=0; i<arrInOrder.size(); i++)
    {
      strInOrder += arrInOrder.get(i) + " ";
    }
 
    long endTime = System.nanoTime();
    
    long totalTime = endTime - startTime;
    System.out.println("Total time for DS_Srivatsan= "+totalTime+" ns");    
    
    startTime = System.nanoTime();
    for(int i=0;i<100;i++)
    {
      rbt.insert(i+1, Integer.toString(i+1));
    }
    List<Integer> arrInOrder2 = rbt.getInOrderTraversal();    
    
    String strInOrder2 = "";
    for(int i=0; i<arrInOrder.size(); i++)
    {
      strInOrder2 += arrInOrder2.get(i) + " ";
    }
 
    endTime = System.nanoTime();
    
    totalTime = endTime - startTime;
    System.out.println("Total time for DS_Srivatsan= "+totalTime+" ns");
    
    
    
    //1000 inputs
    
    bst = new BST<Integer,String>();
    
    rbt = new RBT<Integer,String>();
    System.out.println("Description: Comparing insert and get times for 1000 inputs");
    startTime = System.nanoTime();
    for(int i=0;i<1000;i++)
    {
      bst.insert(i+1, Integer.toString(i+1));
    }
    arrInOrder = bst.getInOrderTraversal();    
    
    strInOrder = "";
    for(int i=0; i<arrInOrder.size(); i++)
    {
      strInOrder += arrInOrder.get(i) + " ";
    }
 
    endTime = System.nanoTime();
    
    totalTime = endTime - startTime;
    System.out.println("Total time for DS_Srivatsan= "+totalTime+" ns");    
    
    startTime = System.nanoTime();
    for(int i=0;i<1000;i++)
    {
      rbt.insert(i+1, Integer.toString(i+1));
    }
   
      arrInOrder2 = rbt.getInOrderTraversal();   
      
      strInOrder2 = "";
      for(int i=0; i<arrInOrder.size(); i++)
      {
        strInOrder += arrInOrder.get(i) + " ";
      }    
    
 
    endTime = System.nanoTime();    
    totalTime = endTime - startTime;
    System.out.println("Total time for DS_Srivatsan= "+totalTime+" ns");
    
    
    
    //10000 inputs
    
    bst = new BST<Integer,String>();
    
    rbt = new RBT<Integer,String>();
    System.out.println("Description: Comparing insert and get times for 10000 inputs");
    startTime = System.nanoTime();
    for(int i=0;i<10000;i++)
    {
      bst.insert(i+1, Integer.toString(i+1));
    }
    arrInOrder = bst.getInOrderTraversal();    
    
    strInOrder = "";
    for(int i=0; i<arrInOrder.size(); i++)
    {
      strInOrder += arrInOrder.get(i) + " ";
    }
 
    endTime = System.nanoTime();
    
    totalTime = endTime - startTime;
    System.out.println("Total time for DS_Srivatsan= "+totalTime+" ns");
    
    
    startTime = System.nanoTime();
    for(int i=0;i<10000;i++)
    {
      rbt.insert(i+1, Integer.toString(i+1));
    }
    for(int i=0;i<10000;i++)
      
    arrInOrder2 = rbt.getInOrderTraversal();    
    
    strInOrder2 = "";
    for(int i=0; i<arrInOrder.size(); i++)
    {
      strInOrder2 += arrInOrder.get(i) + " ";
    }
 
    endTime = System.nanoTime();
    
    totalTime = endTime - startTime;
    System.out.println("Total time for DS_Srivatsan= "+totalTime+" ns");
    
  
    //100000 inputs
   
    bst = new BST<Integer,String>();    
    rbt = new RBT<Integer,String>();
    System.out.println("Description: Comparing insert and get times for 100000 inputs");
    startTime = System.nanoTime();
    for(int i=0;i<100000;i++)
    {
      bst.insert(i+1, Integer.toString(i+1));
    }
    arrInOrder = bst.getInOrderTraversal();    
    
    strInOrder = "";
    for(int i=0; i<arrInOrder.size(); i++)
    {
      strInOrder += arrInOrder.get(i) + " ";
    }
 
    endTime = System.nanoTime();
    
    totalTime = endTime - startTime;
    System.out.println("Total time for DS_Srivatsan= "+totalTime+" ns");    
    
    startTime = System.nanoTime();
    for(int i=0;i<100000;i++)
    {
      rbt.insert(i+1, Integer.toString(i+1));
    }
     arrInOrder2 = rbt.getInOrderTraversal();    
    
    strInOrder2 = "";
    for(int i=0; i<arrInOrder.size(); i++)
    {
      strInOrder += arrInOrder.get(i) + " ";
    }
 
    endTime = System.nanoTime();
    
    totalTime = endTime - startTime;
    System.out.println("Total time for DS_Srivatsan= "+totalTime+" ns");
    
    
    
    //1000000000 inputs
    bst = new BST<Integer,String>();    
    rbt = new RBT<Integer,String>();
    System.out.println("Description: Comparing insert and get times for 1000000000 inputs");
    startTime = System.nanoTime();
    for(int i=0;i<1000000000;i++)
    {
      bst.insert(i+1, Integer.toString(i+1));
    }
    
    arrInOrder = bst.getInOrderTraversal();    
    
    strInOrder = "";
    for(int i=0; i<arrInOrder.size(); i++)
    {
      strInOrder += arrInOrder.get(i) + " ";
    }
 
    endTime = System.nanoTime();
    
    totalTime = endTime - startTime;
    System.out.println("Total time for DS_Srivatsan= "+totalTime+" ns");
    
    
    startTime = System.nanoTime();
    for(int i=0;i<1000000000;i++)
    {
      rbt.insert(i+1, Integer.toString(i+1));
    }
   arrInOrder2 = rbt.getInOrderTraversal();    
    
     strInOrder2 = "";
    for(int i=0; i<arrInOrder.size(); i++)
    {
      strInOrder2 += arrInOrder.get(i) + " ";
    }
 
    endTime = System.nanoTime();
    
    totalTime = endTime - startTime;
    System.out.println("Total time for DS_Srivatsan= "+totalTime+" ns");
    
    
}
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// DO IMPLEMENT A BINARY SEARCH TREE IN THIS CLASS

/**
 * Defines the operations required of student's BST class.
 *
 * NOTE: There are many methods in this interface 
 * that are required solely to support gray-box testing 
 * of the internal tree structure.  They must be implemented
 * as described to pass all grading tests.
 * 
 * 
 * @author Deb Deppeler (deppeler@cs.wisc.edu)
 * @param <K> A Comparable type to be used as a key to an associated value.  
 * @param <V> A value associated with the given key.
 */


public class BST<K extends Comparable<K>, V> implements STADT<K,V> {
    
  
  /*
   * class defined to hold nodes of binary tree.
   */
  
  class Node{      
    private K key;
    private V value; 
    Node leftChild;
    Node rightChild;
    int size;
    private Node(K key, V value, int size) {
      this.key = key;
      this.value = value;
      this.size = size;
      //height = 1;
      leftChild = null;
      rightChild = null;
    }
  }
  
  Node root;  
    
  //BST constructor
    public BST() {          
      root = null;      
    }
    
    

    /**
     * Returns the key that is in the root node of this ST.
     * If root is null, returns null.
     * @return key found at root node, or null
     */
    public K getKeyAtRoot() {
        if(root.key == null)
        {
          return null;
        }
        else return root.key;
    }
    
    /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the left child.
     * If the left child of the found node is null, returns null.
     * 
     * @param key A key to search for
     * @return The key that is in the left child of the found key
     * 
     * @throws IllegalNullKeyException if key argument is null
     * @throws KeyNotFoundException if key is not found in this BST
     */
    public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
            
      if(root == null) {
        throw new KeyNotFoundException("root is null");
      }
      if(key == null) {
          throw new IllegalNullKeyException("key is null");
      }   
      try{
        return getKeyOfLeftChildOf(root,key).leftChild.key;
      }
      catch(NullPointerException e)
      {
        System.out.println("Left child is null");
        return null;
      }
    }
    
    
    private Node getKeyOfLeftChildOf(Node root, K key) throws IllegalNullKeyException, KeyNotFoundException {
        
      try {
      if(root.key.compareTo(key)==0)
      {
        return root;        
      }
      else if(key.compareTo(root.key)<0)
      {
        return getKeyOfLeftChildOf(root.leftChild, key);
      }
      else if (key.compareTo(root.key)>0)
      {
        return getKeyOfLeftChildOf(root.rightChild, key);
      }
      else throw new KeyNotFoundException("Key not found");
     }
     catch(NullPointerException e) {
       System.out.print("Null value encountered");
       return null;
     }
    }
    /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the right child.
     * If the right child of the found node is null, returns null.
     * 
     * @param key A key to search for
     * @return The key that is in the right child of the found key
     * 
     * @throws IllegalNullKeyException if key is null
     * @throws KeyNotFoundException if key is not found in this BST
     */
    public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
           
      if(root == null) {
        throw new KeyNotFoundException("root is null");
      }
      if(key == null) {
          throw new IllegalNullKeyException("key is null");
      }   
      try {
        return getKeyOfRightChildOf(root,key).rightChild.key;
      }
      catch(NullPointerException e)
      {        
        return null;
      }
    }    
          
    
private Node getKeyOfRightChildOf(Node root, K key) throws IllegalNullKeyException, KeyNotFoundException {
    
       
    try {
      if(root.key.compareTo(key)==0)
      {
        return root;        
      }
      else if(key.compareTo(root.key)<0)
      {
        return getKeyOfRightChildOf(root.leftChild, key);
      }
      else if (key.compareTo(root.key)>0)
      {
        return getKeyOfRightChildOf(root.rightChild, key);
      }
      else throw new KeyNotFoundException("Key not found");
    }
    catch(NullPointerException e) {
      System.out.print("Null value encountered");
      return null;
    }
}  
    

    /**
     * Returns the height of this BST.
     * H is defined as the number of levels in the tree.
     * 
     * If root is null, return 0
     * If root is a leaf, return 1
     * Else return 1 + max( height(root.left), height(root.right) )
     * 
     * Examples:
     * A BST with no keys, has a height of zero (0).
     * A BST with one key, has a height of one (1).
     * A BST with two keys, has a height of two (2).
     * A BST with three keys, can be balanced with a height of two(2)
     *                        or it may be linear with a height of three (3)
     * ... and so on for tree with other heights
     * 
     * @return the number of levels that contain keys in this BINARY SEARCH TREE
     */
    public int getHeight() {
         if (root == null)
           return 0;
         else if(root.leftChild==null&&root.rightChild==null)
         {
           return 1;
         }
         else return height(root);
    }    
    
    
   private int height(Node root) {
     if(root == null)
     {
       return 0;
     }
     int leftHeight = height(root.leftChild);
     int rightHeight = height(root.rightChild);
     if(leftHeight > rightHeight)
     {
       return leftHeight+1;
     }
     else return rightHeight+1;
     
     
    }
    
   
    /**
     * Returns the keys of the data structure in sorted order.
     * In the case of binary search trees, the visit order is: L V R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in-order
     */
    public List<K> getInOrderTraversal() {
        if (root == null)
        {
          return Collections.emptyList();
        }    
        List<K> list = new ArrayList<>();
        inOrderTraversal(list, root);
        return list;
    }
    
    
    /**
     *  Helper method of inordertraversal that uses recursion.
     
     */
    private void inOrderTraversal(List<K> list, Node root){
      
      if(root == null)
        return;
      inOrderTraversal(list, root.leftChild);
      list.add(root.key);
      inOrderTraversal(list, root.rightChild);
    }
    
    /**
     * Returns the keys of the data structure in pre-order traversal order.
     * In the case of binary search trees, the order is: V L R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in pre-order
     */
    public List<K> getPreOrderTraversal() {
      if (root == null)
      {
        return Collections.emptyList();
      }    
      List<K> list = new ArrayList<>();
      preOrderTraversal(list, root);
      return list;
  }
    
    
    /**
     *  Helper method of preordertraversal that uses recursion.
     
     */
  private void preOrderTraversal(List<K> list, Node root){
    
    if(root == null)
      {
        return;
      }    
    list.add(root.key);
    preOrderTraversal(list, root.leftChild);    
    preOrderTraversal(list, root.rightChild);
  }
  

    /**
     * Returns the keys of the data structure in post-order traversal order.
     * In the case of binary search trees, the order is: L R V 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in post-order
     */
    public List<K> getPostOrderTraversal() {
      if (root == null)
      {
        return Collections.emptyList();
      }    
      List<K> list = new ArrayList<>();
      postOrderTraversal(list, root);
      return list;
  }
    
    
    /**
     *  Helper method of postordertraversal that uses recursion.
     
     */
  private void postOrderTraversal(List<K> list, Node root){
    
    if(root == null)
      return;    
    postOrderTraversal(list, root.leftChild);    
    postOrderTraversal(list, root.rightChild);
    list.add(root.key);
  }

    /**
     * Returns the keys of the data structure in level-order traversal order.
     * 
     * The root is first in the list, then the keys found in the next level down,
     * and so on. 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in level-order
     */
    public List<K> getLevelOrderTraversal() {
      if (root == null)
      {
        return Collections.emptyList();
      }    
      List<K> list = new ArrayList<>();
      int height = height(root);
      
      for(int i=1; i<=height; i++)
      {
        levelOrderTraversal(list,root,i);
      }      
      return list;
  }
    
    
    /**
     *  Helper method of levelordertraversal that uses recursion.
     
     */
  private void levelOrderTraversal(List<K> list, Node root, int level){
    
    if(root == null)
      return;
    if(level == 1) {
      list.add(root.key);
    }
    else if(level>1)
      levelOrderTraversal(list, root.leftChild,level-1);    
      levelOrderTraversal(list, root.rightChild,level-1);      
  }
    
    
    /** 
     * Add the key,value pair to the data structure and increase the number of keys.
     * If key is null, throw IllegalNullKeyException;
     * If key is already in data structure, throw DuplicateKeyException(); 
     * Do not increase the num of keys in the structure, if key,value pair is not added.
     */
    public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
      if(key == null)
      {
        throw new IllegalNullKeyException("Key is null");
      }
      if(contains(key) == true)
      {
        throw new DuplicateKeyException("Key is already in BST");
      }       
      
      try {  
            root = insertHelp(root, key, value);            
          }
      catch (Exception e) {        
      }
            
    }
    
    /**
     *  Helper method of insert that uses recursion.
     *  @return root of BST     
     */
    private Node insertHelp(Node root, K key, V value) {
      
      if(root == null)                
      {
        return new Node(key,value,1);              
      }
      if(key.compareTo(root.key)<0)
        root.leftChild = insertHelp(root.leftChild, key, value);
      else if(key.compareTo(root.key)>0)
        root.rightChild = insertHelp(root.rightChild, key, value);      
      root.size = 1 + size(root.leftChild) + size(root.rightChild);
      return root;      
      
    }   
            

    /** 
     * If key is found, remove the key,value pair from the data structure 
     * and decrease num keys, and return true.
     * If key is not found, do not decrease the number of keys in the data structure, return false.
     * If key is null, throw IllegalNullKeyException
     */
    public boolean remove(K key) throws IllegalNullKeyException {
      if(key == null)
      {
        throw new IllegalNullKeyException("Key is null");
      }             
      
        try {
            root = deleteHelp(root, key);            
            return true;
          }
      catch (Exception e) {
        System.out.println("delete; failed");
        return false;
      }
    }
    
    /**
     *  Helper method of delete that uses recursion to delete and swap places in the BST
     *  @return root of BST     
     */
    Node deleteHelp(Node root, K key) {
      if(root == null)
        return null;      
      if(key.compareTo(root.key)<0)
        root.leftChild = deleteHelp(root.leftChild,key);
      else if(key.compareTo(root.key)>0)
        {          
        root.rightChild = deleteHelp(root.rightChild,key);
          
        }
      else {
        if(root.rightChild == null)
          return root.leftChild;
        if(root.leftChild == null)
          return root.rightChild;      
             
          Node temp = lowestInOrder(root.rightChild);
          root.rightChild = deleteLow(temp.rightChild);
          root.leftChild = temp.leftChild;
        }
           
      root.size = size(root.leftChild)+size(root.rightChild) + 1;        
      return root;
        
        }
     
    /**
     * Function to find left most child.
     * @param root
     * @return Left-est most child
     */
    Node lowestInOrder(Node root) {
      if(root.leftChild == null)
        return root;
      else
        return lowestInOrder(root.leftChild);
    }
    
    /** 
     * deletes left-est most child
     * @param root
     * @return root of BST
     */
    private Node deleteLow(Node root) {
      if(root.leftChild == null)
        return root.rightChild;
      root.leftChild = deleteLow(root.leftChild);
      root.size = size(root.leftChild) +size(root.rightChild) + 1;
      return root;
    }
      
    

    /**
     * Returns the value associated with the specified key.
     *
     * Does not remove key or decrease number of keys
     * If key is null, throw IllegalNullKeyException 
     * If key is not found, throw KeyNotFoundException().
     */
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
        
        if(key == null) {
          throw new IllegalNullKeyException("Key is null");
        }
        if(!contains(key)) {
          throw new KeyNotFoundException("Key not found");
        }
        if(root == null)
        {
          throw new KeyNotFoundException("Tree is empty");
        } 
        Node temp = NodeFinder(root, key);
        return temp.value;        
        
          
      }
    
    /**
     * function to find a Node in the BST, given key and root.
     * @param root
     * @param key
     * @return root of BST
     */
    
    private Node NodeFinder(Node root, K key) {
      if(root == null)
        return null;
      if(root.key.compareTo(key)==0)
        return root;
      if(key.compareTo(root.key)<0)
        return NodeFinder(root.leftChild,key);
      else if(key.compareTo(root.key)>0)
        return NodeFinder(root.rightChild,key);      
      return null;
    }
    

    /** 
     * Returns true if the key is in the data structure
     * If key is null, throw IllegalNullKeyException 
     * Returns false if key is not null and is not present 
     */
    public boolean contains(K key) throws IllegalNullKeyException { 
        if(key == null)
          throw new IllegalNullKeyException("Key is null");
        Node temp = NodeFinder(root, key);
        try {
          if(temp.key.compareTo(key)==0)
          {
            return true;
          }
          else return false;
        }
        catch (NullPointerException e)
        {
          return false;          
        }
    }

    /**
     *  Returns the number of key,value pairs in the data structure
     */
    public int numKeys() {
        return size(root);
    }    
    
    
    /**
     * returns size of BST
     * @param root
     * @return size of BST
     */
    private int size(Node root) {
      if(root == null)
        return 0;
      else
        return root.size;
    }
    
    /**
     * checks if BST is empty
     * @return true if empty, false if not
     *
     */
    public boolean isEmpty() {
      return size(root) == 0;
  }
    
    
    /**
     * Print the tree. 
     *
     * For our testing purposes of your print method: 
     * all keys that we insert in the tree will have 
     * a string length of exactly 2 characters.
     * example: numbers 10-99, or strings aa - zz, or AA to ZZ
     *
     * This makes it easier for you to not worry about spacing issues.
     *
     * You can display a binary search in any of a variety of ways, 
     * but we must see a tree that we can identify left and right children 
     * of each node
     *
     * For example: 
     
           30
           /\
          /  \
         20  40
         /   /\
        /   /  \
       10  35  50 

       Look from bottom to top. Inorder traversal of above tree (10,20,30,35,40,50)
       
       Or, you can display a tree of this kind.

       |       |-------50
       |-------40
       |       |-------35
       30
       |-------20
       |       |-------10
       
       Or, you can come up with your own orientation pattern, like this.

       10                 
               20
                       30
       35                
               40
       50                  

       The connecting lines are not required if we can interpret your tree.
     * @throws Exception 

     */
    public void print() {
        int gap = 0; 
        System.out.println("The Tree is as Shown Below:");
        printHelp(root, gap);        
    }
    
    private void printHelp(Node root, int gap) {      
      if(root == null)
        return;
      gap+= 10;
      printHelp(root.rightChild, gap);
      System.out.println();
      for(int i=10 ; i<gap; i++)
        System.out.print(" ");
      System.out.print(root.value + "\n");
      printHelp(root.leftChild,gap);
    }
    
} // copyrighted material, students do not have permission to post on public sites




//  deppeler@cs.wisc.edu

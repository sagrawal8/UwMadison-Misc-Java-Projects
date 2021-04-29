import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Implements a generic Red-Black tree.
 *
 * DO NOT CHANGE THE METHOD SIGNATURES OF PUBLIC METHODS
 * DO NOT ADD ANY PACKAGE LEVEL OR PUBLIC ACCESS METHODS OR FIELDS.
 * 
 * You are not required to override remove.
 * If you do not override this operation,
 * you may still have the method in your type, 
 * and have the method throw new UnsupportedOperationException.
 * See https://docs.oracle.com/javase/7/docs/api/java/lang/UnsupportedOperationException.html
 * 
 * @param <K> is the generic type of key, must be a Comparable tyle
 * @param <V> is the generic type of value
 */
public class RBT<K extends Comparable<K>, V> implements STADT<K,V>{

    // USE AND DO NOT EDIT THESE CONSTANTS
    public static final int RED = 0;
    public static final int BLACK = 1;
    private Node root;
    private int size;
    
    private class Node{
      private K key;
      private V value;
      private Node leftChild, rightChild;
      private int color;
      private boolean hasColor;
      
      public Node(K key, V value, int color) {
        this.key = key;
        this.value = value;
        this.color = color;        
        if (color == RBT.RED||color == RBT.BLACK)
        {
          this.hasColor = true;
        }        
      }
    }


    // TODO: define a default no-arg constructor
    public RBT() {
      root = null;
      size = 0;
    }    

    /**
     * Returns the color of the node that contains the specified key.
     * Returns RBT.RED if the node is red, and RBT.BLACK if the node is black.
     * Returns -1 if the node is not found.
     * @param 
     * @return
     * @throws IllegalNullKeyException 
     */
    public int colorOf(K key) throws IllegalNullKeyException {
        Node root = nodeFinder(this.root, key);
        if(root == null)
          return -1;
        else return root.color;
    }

    /**
     * Returns true if root is null or the color of the root is black.
     * If root is null, returns true.
     * @return true if root is black, else returns false (for red)
     */
    public boolean rootIsBlack() {
        if(root == null)
          return true;
        else if(root.color == RBT.BLACK)
          return true;
        else return false;
    }

    /**
     * Returns true if the node that contains this key is RED.
     * If key is null, throws IllegalNullKeyException.
     * If key is not found, throws KeyNotFoundException.
     * @return true if the key is found and the node's color is RED,
     * else return false if key is found and the node's color is BLACK.
     */
    public boolean isRed(K key) 
            throws IllegalNullKeyException, KeyNotFoundException {
        if(key == null)
          throw new IllegalNullKeyException("Key is null");        
        
        if(nodeFinder(root, key) == null)
          throw new KeyNotFoundException("Key not found");
        
        Node temp = nodeFinder(root, key);
        if(temp.color == RBT.RED)
          return true;
        else return false;
    }

    /**
     * Returns true if the node that contains this key is BLACK.
     * If key is null, throws IllegalNullKeyException.
     * If key is not found, throws KeyNotFoundException.
     * @return true if the key is found and the node's color is BLACK,
     * else return false if key is found and the node's color is RED.
     */
    public boolean isBlack(K key) 
            throws IllegalNullKeyException, KeyNotFoundException {
      if(key == null)
        throw new IllegalNullKeyException("Key is null");        
      
      if(nodeFinder(root, key) == null)
        throw new KeyNotFoundException("Key not found");
      
      Node temp = nodeFinder(root, key);
      if(temp.color == RBT.BLACK)
        return true;
      else return false;
    }

    @Override
    public K getKeyAtRoot() {
      if(root.key == null)      
        return null;
      
      else return root.key;
  }

    @Override
    public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
      
      if(root == null) 
        throw new KeyNotFoundException("root is null");
      
      if(key == null) 
          throw new IllegalNullKeyException("key is null");
         
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
        return root;        
      
      else if(key.compareTo(root.key)<0)      
        return getKeyOfLeftChildOf(root.leftChild, key);
      
      else if (key.compareTo(root.key)>0)      
        return getKeyOfLeftChildOf(root.rightChild, key);
      
      else throw new KeyNotFoundException("Key not found");
     }
      
     catch(NullPointerException e) {
       System.out.print("Null value encountered");
       return null;
     }
    }

    @Override
    public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
      if(root == null) 
        throw new KeyNotFoundException("root is null");
      
      if(key == null) 
          throw new IllegalNullKeyException("key is null");
         
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
          return root;        
        
        else if(key.compareTo(root.key)<0)        
          return getKeyOfRightChildOf(root.leftChild, key);
        
        else if (key.compareTo(root.key)>0)        
          return getKeyOfRightChildOf(root.rightChild, key);
        
        else throw new KeyNotFoundException("Key not found");
      }
      catch(NullPointerException e) {
        System.out.print("Null value encountered");
        return null;
      }
    }  

    @Override
    public int getHeight() {
      if (root == null)
        return 0;
      
      else if(root.leftChild == null && root.rightChild == null)      
        return 1;
      
      else return height(root);
    }    
 
 
    private int height(Node root) {
      if(root == null)      
        return 0;
       
      int leftHeight = height(root.leftChild);
      int rightHeight = height(root.rightChild);
      
      if(leftHeight > rightHeight)      
        return leftHeight+1;
      
      else return rightHeight+1;  
  
 }

    @Override
    public List<K> getInOrderTraversal() {
      if (root == null)
        return Collections.emptyList();
          
      List<K> list = new ArrayList<>();
      inOrderTraversal(list, root);
      return list;
    }
    
    private void inOrderTraversal(List<K> list, Node root){    
      if(root == null)
        return;
      
      inOrderTraversal(list, root.leftChild);
      list.add(root.key);
      inOrderTraversal(list, root.rightChild);
    }
        
    

    @Override
    public List<K> getPreOrderTraversal() {
      if (root == null)
        return Collections.emptyList();         
      
      List<K> list = new ArrayList<>();
      preOrderTraversal(list, root);
      return list;
    }    
    
    private void preOrderTraversal(List<K> list, Node root){    
      if(root == null)
        return;
          
      list.add(root.key);
      preOrderTraversal(list, root.leftChild);    
      preOrderTraversal(list, root.rightChild);
    }
    
    
    

    @Override
    public List<K> getPostOrderTraversal() {
      if (root == null)
        return Collections.emptyList();
          
      List<K> list = new ArrayList<>();
      postOrderTraversal(list, root);
      return list;
    }
    
    private void postOrderTraversal(List<K> list, Node root){
    
      if(root == null)
        return;    
      
      postOrderTraversal(list, root.leftChild);    
      postOrderTraversal(list, root.rightChild);
      list.add(root.key);
    }    
    
    

    @Override
    public List<K> getLevelOrderTraversal() {
      if (root == null)
        return Collections.emptyList();
          
      List<K> list = new ArrayList<>();
      int height = height(root);
      
      for(int i=1; i<=height; i++)
        levelOrderTraversal(list,root,i);
            
      return list;
  }
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

    @Override
    public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
        if(key == null)
          throw new IllegalNullKeyException("Key is null.");
        if(contains(key))
          throw new DuplicateKeyException("Key exists already.");
        
        root = insertHelp(root, key, value);
        root.color = RBT.BLACK;        
    }
    
    private Node insertHelp(Node root, K key, V value) {
      if(root == null)
        return new Node(key, value, RBT.RED);
      
      if(key.compareTo(root.key)<0)
        root.leftChild = insertHelp(root.leftChild, key, value);
      else if(key.compareTo(root.key)>0)
        root.rightChild = insertHelp(root.rightChild, key, value);
      
//      boolean flagRightChild = false;
//      boolean flagLeftChild = false;
//      boolean flagLeftLeftChild = false;
//      try{
//        if(isRed(root.rightChild.key))
//          flagRightChild = true;
//      }
//      catch(NullPointerException e) {
//        flagRightChild = false;
//      } catch (IllegalNullKeyException e) {
//        flagRightChild = false;
//      } catch (KeyNotFoundException e) {
//        flagRightChild = false;
//      }
//      
//      try{
//        if(isRed(root.leftChild.key))
//          flagRightChild = true;
//      }
//      catch(NullPointerException e) {
//        flagRightChild = false;
//      } catch (IllegalNullKeyException e) {        
//        e.printStackTrace();
//      } catch (KeyNotFoundException e) {        
//        e.printStackTrace();
//      }
//      
//      try{
//        if(isRed(root.leftChild.leftChild.key))
//          flagLeftLeftChild = true;
//      }
//      catch(NullPointerException e) {
//        flagLeftLeftChild = false;
//      } catch (IllegalNullKeyException e) {        
//        flagLeftLeftChild = false;
//      } catch (KeyNotFoundException e) {        
//        flagLeftLeftChild = false;
//      }
//      
//      if(flagRightChild && !flagLeftChild)
//        root = rotateLeft(root);
//      if(flagLeftChild && flagLeftLeftChild)
//        root = rotateRight(root);
//      if(flagLeftChild && flagRightChild)
//        switchColors(root);
//      
//      size++;
      return root;                 
              
    }
    
    private Node rebalance(Node root) {
//      IllegalNullKeyException, KeyNotFoundException
      try {
        if (isRed(root.rightChild.key)) {
          root = rotateLeft(root);
        }
        
        if (isRed(root.leftChild.key) && isRed(root.leftChild.leftChild.key)) {
          root = rotateRight(root);
        }
        
        if (isRed(root.leftChild.key) && isRed(root.rightChild.key)) {
          switchColors(root);
        }
      } catch (IllegalNullKeyException e) {

      } catch (KeyNotFoundException e) {
        
      } catch (Exception e) {
        
      }
      
      return root;
    }

    @Override
    public boolean remove(K key) throws IllegalNullKeyException {
        if(key == null)
          throw new IllegalNullKeyException("key is null");
        
        boolean flagRightChild = false;
        boolean flagLeftChild = false;
        boolean flagLeftLeftChild = false;
        try{
          if(isRed(root.rightChild.key))
            flagRightChild = true;
        }
        catch(NullPointerException e) {
          flagRightChild = false;
        } catch (IllegalNullKeyException e) {
          flagRightChild = false;
          e.printStackTrace();
        } catch (KeyNotFoundException e) {
          flagRightChild = false;
          e.printStackTrace();
        }
        
        try{
          if(isRed(root.leftChild.key))
            flagLeftChild = true;
        }
        catch(NullPointerException e) {
          flagLeftChild = false;
        } catch (IllegalNullKeyException e) {        
          flagLeftChild = false;
          e.printStackTrace();
        } catch (KeyNotFoundException e) {        
          flagLeftChild = false;
          e.printStackTrace();
        }
        
        try{
          if(isRed(root.leftChild.leftChild.key))
            flagLeftLeftChild = true;
        }
        catch(NullPointerException e) {
          flagLeftLeftChild = false;
        } catch (IllegalNullKeyException e) {        
          flagLeftLeftChild = false;
          e.printStackTrace();
        } catch (KeyNotFoundException e) {        
          flagLeftLeftChild = false;
          e.printStackTrace();
        }
        
        if(flagLeftChild == false && flagRightChild == false)
          root.color = RBT.RED;
       try { 
        root = deleteHelp(root, key);
        if(root != null)
          root.color = RBT.BLACK; 
        size--;
        return true;
       }
       catch(Exception e) {
         return false;
       }
               
    }
    
    
    private Node deleteHelp(Node root, K key) {
      
      boolean flagRightChild = false;
      boolean flagLeftChild = false;
      boolean flagLeftLeftChild = false;
      boolean flagRightLeftChild = false;
      try{
        if(isRed(root.rightChild.key))
          flagRightChild = true;
      }
      catch(NullPointerException e) {
        flagRightChild = false;
      } catch (IllegalNullKeyException e) {
        flagRightChild = false;
        e.printStackTrace();
      } catch (KeyNotFoundException e) {
        flagRightChild = false;
        e.printStackTrace();
      }
      
      try{
        if(isRed(root.leftChild.key))
          flagLeftChild = true;
      }
      catch(NullPointerException e) {
        flagLeftChild = false;
      } catch (IllegalNullKeyException e) {        
        flagLeftChild = false;
        e.printStackTrace();
      } catch (KeyNotFoundException e) {        
        flagLeftChild = false;
        e.printStackTrace();
      }
      
      try{
        if(isRed(root.leftChild.leftChild.key))
          flagLeftLeftChild = true;
      }
      catch(NullPointerException e) {
        flagLeftLeftChild = false;
      } catch (IllegalNullKeyException e) {        
        flagLeftLeftChild = false;
        e.printStackTrace();
      } catch (KeyNotFoundException e) {        
        flagLeftLeftChild = false;
        e.printStackTrace();
      }
      
      try{
        if(isRed(root.rightChild.leftChild.key))
          flagRightLeftChild = true;
      }
      catch(NullPointerException e) {
        flagRightLeftChild = false;
      } catch (IllegalNullKeyException e) {
        flagRightLeftChild = false;
        e.printStackTrace();
      } catch (KeyNotFoundException e) {
        flagRightLeftChild = false;
        e.printStackTrace();
      }
      
      if(key.compareTo(root.key)<0) {
        if(flagLeftChild && flagLeftLeftChild)
          root = redLeft(root);
        root.leftChild = deleteHelp(root.leftChild, key);
      }
      
      else {
        if(flagLeftChild)
          root = rotateRight(root);
        if(key.compareTo(root.key) == 0 && flagRightChild == false)
          return null;
        if(flagRightChild && flagRightLeftChild == false)
          root = redRight(root);
        if(key.compareTo(root.key) == 0)
        {
          Node temp = lowest(root.rightChild);
          root.key = temp.key;
          root.value = temp.value;
          root.rightChild = deleteLow(root.rightChild);        
        }
        else
          root.rightChild = deleteHelp(root.rightChild, key);
      }
      return balance(root);      
    
    }              
    

    @Override
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
        
      Node temp = nodeFinder(this.root,key);
      if(temp == null)
          throw new KeyNotFoundException("Key was not found.");
      else return temp.value;
    }

    @Override
    public boolean contains(K key) throws IllegalNullKeyException {
        if(nodeFinder(this.root,key)!=null)
          return true;
        else return false;
    }

    @Override
    public int numKeys() {
        return size;
    }

    @Override
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
  



    // TODO: override the insert method so that it rebalances 
    //       according to red-black tree insert algorithm.


    
    
    
    private Node nodeFinder(Node root,K key) throws IllegalNullKeyException {
      if(key == null)
        throw new IllegalNullKeyException("Key is null");
      while(root != null) {
        if(key.compareTo(root.key)<0)
          root = root.leftChild;
        else if(key.compareTo(root.key)>0)
          root = root.rightChild;
        else if(key.compareTo(root.key)==0)
          return root;
      }
      return null;        
    }
    
    
    private Node rotateRight(Node root) {
      
      Node temp = root.leftChild;
      root.leftChild = temp.rightChild;
      temp.rightChild = root;
      temp.color = temp.rightChild.color;
      temp.rightChild.color = RBT.RED;
      return temp;    
    }
    
    private Node rotateLeft(Node root) {
      
      Node temp = root.rightChild;
      root.rightChild = temp.leftChild;
      temp.leftChild = root;
      temp.color = temp.leftChild.color;
      temp.leftChild.color = RBT.RED;
      return temp;
    }
    
    private void switchColors(Node root) {
      if(root.color == 1)
        root.color = 0;
      else root.color =1;
      
      if(root.leftChild.color == 1)
        root.leftChild.color = 0;
      else root.leftChild.color = 1;
      
      if(root.rightChild.color == 1)
        root.color = 0;
      else root.color = 1;
    }
    
    private Node redLeft(Node root) {
      switchColors(root);
      
      boolean flagRightLeftChild;
      try{
        isRed(root.rightChild.leftChild.key);
        flagRightLeftChild = true;
      }
      catch(NullPointerException e) {
        flagRightLeftChild = false;
      } catch (IllegalNullKeyException e) {        
        flagRightLeftChild = false;
        e.printStackTrace();
      } catch (KeyNotFoundException e) {        
        flagRightLeftChild = false;
        e.printStackTrace();
      }
      
      if(flagRightLeftChild) {
        root.leftChild = rotateRight(root.rightChild);
        root = rotateLeft(root);
        switchColors(root);
      }
      return root;      
      
    }
    
    
    private Node redRight(Node root) {
      switchColors(root);
      
      boolean flagLeftLeftChild;
      try{
        isRed(root.rightChild.leftChild.key);
        flagLeftLeftChild = true;
      }
      catch(NullPointerException e) {
        flagLeftLeftChild = false;
      } catch (IllegalNullKeyException e) {        
        flagLeftLeftChild = false;
        e.printStackTrace();
      } catch (KeyNotFoundException e) {        
        flagLeftLeftChild = false;
        e.printStackTrace();
      }
      
      if(flagLeftLeftChild) {        
        root = rotateLeft(root);
        switchColors(root);
      }
      return root;
    }
    
    
    private Node balance(Node root) {
      boolean flagLeftChild;
      try{
        isRed(root.rightChild.key);
        flagLeftChild = true;
      }
      catch(NullPointerException e) {
        flagLeftChild = false;
      } catch (IllegalNullKeyException e) {        
        flagLeftChild = false;
        e.printStackTrace();
      } catch (KeyNotFoundException e) {        
        flagLeftChild = false;
        e.printStackTrace();
      }
      
      boolean flagRightChild;
      try{
        isRed(root.rightChild.leftChild.key);
        flagRightChild = true;
      }
      catch(NullPointerException e) {
        flagRightChild = false;
      } catch (IllegalNullKeyException e) {        
        flagRightChild = false;
        e.printStackTrace();
      } catch (KeyNotFoundException e) {        
        flagRightChild = false;
        e.printStackTrace();
      }
      
      boolean flagLeftLeftChild;
      try{
        isRed(root.leftChild.leftChild.key);
        flagLeftLeftChild = true;
      }
      catch(NullPointerException e) {
        flagLeftLeftChild = false;
      } catch (IllegalNullKeyException e) {        
        flagLeftLeftChild = false;
        e.printStackTrace();
      } catch (KeyNotFoundException e) {        
        flagLeftLeftChild = false;
        e.printStackTrace();
      }
      
      if(flagRightChild)
        root = rotateLeft(root);
     
      if(flagLeftChild && flagLeftLeftChild)
        root = rotateRight(root);
      
      if(flagLeftChild && flagRightChild)
        switchColors(root);
      
      return root;      
    }
    
    
    private Node lowest(Node root) {
      if(root.leftChild == null)
        return root;
      else 
        return lowest(root.leftChild);
    }
    
    
    private Node deleteLow(Node root) {
      if(root.leftChild == null)
        return null;
      
      boolean flagLeftLeftChild = false;;
      try{
        if(isRed(root.leftChild.leftChild.key))
          flagLeftLeftChild = true;
      }
      catch(NullPointerException e) {
        flagLeftLeftChild = false;
      } catch (IllegalNullKeyException e) {        
        flagLeftLeftChild = false;
        e.printStackTrace();
      } catch (KeyNotFoundException e) {        
        flagLeftLeftChild = false;
        e.printStackTrace();
      }
      
      boolean flagLeftChild = false;
      try{
        if(isRed(root.leftChild.leftChild.key))
          flagLeftChild = true;
      }
      catch(NullPointerException e) {
        flagLeftChild = false;
      } catch (IllegalNullKeyException e) {        
        flagLeftChild = false;
        e.printStackTrace();
      } catch (KeyNotFoundException e) {        
        flagLeftChild = false;
        e.printStackTrace();
      }
      
      if(flagLeftChild && flagLeftLeftChild)
        root = redLeft(root);
      
      root.leftChild = deleteLow(root.leftChild);
      return balance(root);
      
    }      
      
}
    
    



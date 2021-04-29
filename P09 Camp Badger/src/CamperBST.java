import java.util.Iterator;
import java.util.LinkedList;
////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Badger Camp
//Files:           (a list of all source files used by that program)
//Course:          CS300
//
//Author:          Shashank Agrawal
//Email:           sagrawal8@wisc.edu
//Lecturer's Name: Gary Dahl
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//Partner Name:    (name of your pair programming partner)
//Partner Email:   (email address of your programming partner)
//Partner Lecturer's Name: (name of your partner's lecturer)
//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//___ Write-up states that pair programming is allowed for this assignment.
//___ We have both read and understand the course Pair Programming Policy.
//___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully 
//acknowledge and credit those sources of help here.  Instructors and TAs do 
//not need to be credited here, but tutors, friends, relatives, room mates, 
//strangers, and others do.  If you received no outside help from either type
//of source, then please explicitly indicate NONE.
//
//Persons:         (identify each person and describe their help in detail)
//Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE /////////////////////////////// 
public class CamperBST {

  public CampTreeNode root;
  private int size;
  
  public CamperBST() {
    root = null;
    size = 0;
  }
  public int size()//returns the current size of the CamperBST
  {
    return this.size;
  }
  public boolean isEmpty() //returns true if the tree is empty, false otherwise
  {
    if(size == 0)
      return true;
    else return false;
          
  }
  
//starts tree insertion by calling insertHelp() on the root and
//assigning root to be the subtree returned by that method
public void insert(Camper newCamper)
{    
  root = insertHelp(root, newCamper);
  System.out.println("Enrollment of "+ newCamper.getFirstName()+" "+newCamper.getLastName()+" Successful!");
  size++;
  }

/** Recursive helper method to insert.
* @param current, The "root" of the subtree we are inserting into,
* ie the node we are currently at.
* @param newCamper, the camper to be inserted into the tree
* @return the root of the modified subtree we inserted into
*/
private CampTreeNode insertHelp(CampTreeNode current, Camper newCamper)
{ 
  if(current==null)
  {    
    current = new CampTreeNode(); 
    current.setData(newCamper);
    return current;
  }
  else if(current.getData().compareTo(newCamper)==1)
  {
    current.setLeftNode(insertHelp(current.getLeftNode(),newCamper));
    return current;
  }
  else if(current.getData().compareTo(newCamper)==-1)
  {
    current.setRightNode(insertHelp(current.getRightNode(),newCamper));
    return current;
  }  
  return root;
    
}

/** Deletes a Camper into the binary search tree if it exists.
* @param key, the camper to be deleted from the tree
* @throws NoSuchElementException if it is thrown by deleteHelp
*/
public void delete (Camper key) throws java.util.NoSuchElementException
{
  root = deleteHelp(root, key);  
  size--;
}
/** Recursive helper method to delete.
* @param current, The "root" of the subtree we are deleting from,
* ie the node we are currently at.
* @param key, the camper to be deleted from the tree
* @return the root of the modified subtree we deleted from
* @throws NoSuchElementException if the camper is not in the tree
*/
private CampTreeNode deleteHelp(CampTreeNode current, Camper key)
{ 
      
  
  if(current==null)
  {
    throw new java.util.NoSuchElementException();    
  }  
  if(current.getData().compareTo(key)<0)
  { 
    current.setRightNode(deleteHelp(current.getRightNode(),key));    
    
  }
  else if(current.getData().compareTo(key)>0)
  {
    current.setLeftNode(deleteHelp(current.getLeftNode(),key));    
    
  }
  else if(current.getData().compareTo(key)==0)
  {
    System.out.println("Unenrollment of " + key.getFirstName()+" "+key.getLastName()+ " Successfull!");
    if(current.getLeftNode()==null&&current.getRightNode()==null)
    {
      return null;
    }
    
    else if(current.getLeftNode()==null&&current.getRightNode()!=null)
      return current.getRightNode();
    
    else if(current.getLeftNode()!=null&&current.getRightNode()==null)
      return current.getLeftNode();
    
    else {
      current.setData(helper(current.getRightNode()));
      current.setRightNode(deleteHelp(current.getRightNode(),current.getData()));      
    }
    
  }  
  
  return current;
  }

  public static Camper helper(CampTreeNode current) {
    Camper lowerVal = current.getData();
    while(current.getLeftNode()!=null)
    {
      lowerVal = current.getLeftNode().getData();
      current = current.getLeftNode();
    }
    return lowerVal;
  }
  
//LinkedList to maintain current traversal
private LinkedList<Camper> traversedLList;
//returns an iterator of camper in the correct order as designated
public Iterator<Camper> traverse(String order)
{
//first time traversing need to initialize LinkedList
if(traversedLList==null){
traversedLList = new LinkedList<Camper>();
}
else{
//clear the list to start over for a new traversal
traversedLList.clear();
}
traverseHelp(root, order);
return traversedLList.listIterator();
}
/** Recursive helper method to traverse. Will take the current CampTreeNode’s data
*and add it to traversedLList based on the given order. Then continue to recurse on
*the correct subtree.
* @param current, the root of the current subtree we are traversing
* @param order, the type of traversal to perform
*/
private void traverseHelp (CampTreeNode current, String order)
{
  if(order.equals("INORDER"))
  {
    if(current == null)
    {
      return;
    }
    System.out.println("------INORDER TRAVERSAL------");   
    printInOrder();
    System.out.println("----------");
  }
  else if(order.equals("POSTORDER"))
  {
    System.out.println("------POSTORDER TRAVERSAL------");
    printPostOrder();
    System.out.println("----------");
  }
  else {
    System.out.println("------PREORDER TRAVERSAL------");
    printPreOrder();
    System.out.println("----------");
  }
}

private void printInOrder() {
  printInOrder(root);
}

private void printPostOrder() {
  printPostOrder(root);
}

private void printPreOrder() {
  printPreOrder(root);
}
//Prints nodes InORDER
private void printInOrder(CampTreeNode current) {
  if(current==null) {return;}
  printHelp(current.getLeftNode());
  traversedLList.add(current.getData());
  printHelp(current.getRightNode());
}
//prints nodes PostOrder
private void printPostOrder(CampTreeNode current) {
  if(current == null)
  {
    return;
  }
  printPostOrder(current.getLeftNode());
  printPostOrder(current.getRightNode());
  traversedLList.add(current.getData());
}

//Prints PreOrder
private void printPreOrder(CampTreeNode current) {
  if(current == null)
  {
    return;
  }
  traversedLList.add(current.getData());
  printPreOrder(current.getLeftNode());
  printPreOrder(current.getRightNode());
  return;
}



//Prints the contents of this tree in alphabetical order
//based on the string "lastName, firstName"
public void print() { printHelp(root); }
private void printHelp(CampTreeNode current){
if(current==null) {return;}
printHelp(current.getLeftNode());
System.out.println(current.getData());
printHelp(current.getRightNode());}

}

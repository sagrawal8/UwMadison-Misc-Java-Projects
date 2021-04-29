////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Process Scheduler
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
public class WaitingProcessQueue implements WaitingQueueADT<CustomProcess> {
  
  private static final int INITIAL_CAPACITY = 20; // the initial capacity of this
  //waiting process queue
  private CustomProcess[] data; // min heap-array storing the CustomProcesses
  //inserted in this WaitingProcessQueue.
  //data is an oversize array
  private int size; // number of CustomProcesses stored in this WaitingProcessQueue
  
  /**
   * initializes a new array with capacity = INITIAL_CAPACITY
   * initializes size to 0 as its a new array
   */
  public WaitingProcessQueue() {
    data = new CustomProcess[INITIAL_CAPACITY];
    this.size = 0;
  }
  
  /**
   * percolates the value inserted into the array at data[size] by checking if
   * the value if lesser than the value at the parent node. If yes, the values
   * are swapped and the value is checked again until it's not lesser anymore.
   * @param index
   */
  private void minHeapPercolateUp(int index) {
    int parentIndex = (index-1)/2;
    int currentIndex = index;
    
    while(currentIndex > 0 && (data[currentIndex].compareTo(data[parentIndex])==-1)) {   
           
        CustomProcess tempObj = data[parentIndex];
        data[parentIndex] = data[currentIndex];
        data[currentIndex] = tempObj;
        currentIndex = parentIndex;
        parentIndex = (parentIndex-1)/2;            
    }
  }
  
  /**
   * if the array size is equal to its length then a new array with twice the 
   * length is initialized and the values are copied to the new array.
   * The new value to be inserted is inserted at the end of the array and then 
   * percolatedUp. 
   * Finally the size is incremented.
   * @param newObject
   */
  @Override
  public void insert(CustomProcess newObject) {
    if(this.size == INITIAL_CAPACITY) 
    {      
      CustomProcess[] temp = new CustomProcess[INITIAL_CAPACITY];
      for(int i=0;i<this.size;i++)
      {
        temp[i] = data[i];
      }
      data = new CustomProcess[2*data.length];
      for(int i=0;i<this.size;i++)
      {
        data[i] = temp[i];
      }
    }
    
    int index = this.size;
    data[index] = newObject;
    minHeapPercolateUp(index);  
    size=size+1;    
    
    
  }
  /**
   * percolates the value at index 0 of the array by checking if
   * the value at the parent node is greater than the left and right child values
   * If yes, the values are swapped and the value is checked again until it's 
   * not greater anymore. This is a recursive process.
   * @param index
   */
  private void minHeapPercolateDown(int index) {
    int min = index;
    int leftChild = 2*min;
    int rightChild = 2*min+1;
    if(leftChild<size && data[min].compareTo(data[leftChild])==1)
    {      
      min = leftChild;
    }
    if(rightChild<size && data[min].compareTo(data[rightChild])==1)
    {
      min = rightChild;
    }
    if(min!=index)
    {
      CustomProcess tempObj = data[min];
      data[min] = data[index];
      data[index] = tempObj;
      minHeapPercolateDown(min);
    }
  }
    
/**
 * removes the value at the parent node (highest priority) and then adds the last
 * element of the array to index 0. This value is then percolated down using the
 * percolateDown method.
 * @return minimum value or the highest priority element.
 */
  
  @Override
  public CustomProcess removeBest() {
    CustomProcess min = data[0];
    data[0] = data[size-1];
    data[size-1] = null;
    size--;
    minHeapPercolateDown(0);
    
    return min;
  }

  /**
   * @return the highest priority element but doesn't remove it.
   */
  @Override
  public CustomProcess peekBest() {
    return data[0];    
  }
  
/**
 * accessor method for size of array
 * @return size of array
 */
  @Override
  public int size() {
    return size;   
  }

  /**
   * checks if the array is empty by comparing size to 0.
   * @return true if empty, false if not.
   */
  @Override
  public boolean isEmpty() {
    return size==0;
    
  }
  
  /**
   * returns a string representation of the elements in the array if its not empty.
   * @return a string representation of the elements in the array.
   */
  @Override
  public String toString() {
   if(isEmpty())
     return (" ");
   else {
     String str="";
     for(int i=0; i<size; i++) {
       str = str + data[i].toString() + " ";
     }
     return str.trim();
   }
    }
  
}

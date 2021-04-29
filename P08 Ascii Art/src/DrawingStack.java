import java.util.Iterator;

public class DrawingStack implements StackADT<DrawingChange>, java.lang.Iterable<DrawingChange> {

  private LinkedNode<DrawingChange> top;
  private int size;
  
  public DrawingStack() {
    top = null;
    size = 0;
  }
  //returns an iterator object starting from the top of the stack.
  @Override
  public Iterator<DrawingChange> iterator() {
    
    return new DrawingStackIterator(top);
  }
  /**
   * Add an element to this stack
   * 
   * @param element an element to be added
   * @throws java.util.IllegalArgumentException with a descriptive error message if the input
   *         element is null
   */
  
  @Override
  public void push(DrawingChange element) {
    if(element == null)
      throw new IllegalArgumentException("Element is null");
    LinkedNode<DrawingChange> obj = new LinkedNode<DrawingChange>(element);    
    if(top==null)
      top = obj;
    else {
      LinkedNode<DrawingChange> currentTop = top;
      top = obj;
      top.setNext(currentTop);
    }    
    size++;    
  }
   /**
   * Remove the element on the top of this stack and return it
   * 
   * @return the element removed from the top of the stack
   * @throws java.util.EmptyStackException without error message if the stack is empty
   */
  @Override
  public DrawingChange pop() {
    if(isEmpty())
    {
      throw new java.util.NoSuchElementException("Stack is Empty");
    }
    LinkedNode<DrawingChange> obj = top;
    top = top.getNext();
    size--;
    return obj.getData();
    
  }
  /**
   * Get the element on the top of this stack
   * 
   * @return the element on the stack top
   * @throws java.util.EmptyStackException without error message if the stack is empty
   */
  @Override
  public DrawingChange peek() {
    if(top == null)
    {
      throw new java.util.EmptyStackException();
    }
    return top.getData();
  }
  /**
   * Check whether this stack is empty or not
   * 
   * @return true if this stack contains no elements, otherwise false
   */
  @Override
  public boolean isEmpty() {
    if(size == 0||top == null)
    {
      return true;
    }
    else return false;
  }
  /**
   * Get the number of elements in this stack
   * 
   * @return the size of the stack
   */
  @Override
  public int size() {
    
    return size;
  }

 

}

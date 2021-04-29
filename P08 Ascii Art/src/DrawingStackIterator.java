import java.util.Iterator;

public class DrawingStackIterator implements Iterator<DrawingChange> {

  private LinkedNode<DrawingChange> element;
  public DrawingStackIterator(LinkedNode<DrawingChange> obj)
  {
    element = obj;
  }
  //checks is current element is null and returns false is it is nd true otherwise
  @Override
  public boolean hasNext() {
    if(element == null)
    {
      return false;
    }
    else return true;
  }
//checks if current element is null and returns the data of the current element if it isnt null
  @Override
  public DrawingChange next() {
    if(hasNext())
    {      
      LinkedNode<DrawingChange> temp = element;     
      element = element.getNext();
      return temp.getData();
    }
    else throw new java.util.NoSuchElementException("Stack is exhausted"); 
        
  }

}

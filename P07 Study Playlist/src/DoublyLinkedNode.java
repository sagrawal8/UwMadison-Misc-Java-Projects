
public class DoublyLinkedNode<T> {

  private T data;
  private DoublyLinkedNode<T> previous;
  private DoublyLinkedNode<T> next;
  
  
  
  public DoublyLinkedNode(DoublyLinkedNode<T> previous,
      T data,
      DoublyLinkedNode<T> next) {
    
    if(data == null)
    {
      throw new java.lang.IllegalArgumentException("Data is null");
    }
    this.previous = previous;
    this.next = next;
    this.data = data;
  }
  
  public DoublyLinkedNode(T data) {
    
    if(data == null)
    {
      throw new java.lang.IllegalArgumentException("Data is null");
    }
    this.next = null;
    this.previous=null;
    this.data = data;
  }
  
  public T getData() {
    return data;
  }
  
  public DoublyLinkedNode<T> getNext(){
    if(next == null)
    {
      return null;
    }
    else return next;
  }
  
  public void setNext(DoublyLinkedNode<T> next)
  {
    this.next = next;
  }
  
  public DoublyLinkedNode<T> getPrevious(){
    if(previous == null) {
      return null;
    }
    else return previous;
  }
  
  public void setPrevious(DoublyLinkedNode<T> previous)
  {
    this.previous = previous;
  }
  
}

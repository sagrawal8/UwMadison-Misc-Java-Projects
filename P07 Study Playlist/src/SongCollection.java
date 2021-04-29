import java.util.Iterator;
import java.util.*;
import java.util.NoSuchElementException;

public class SongCollection implements Iterable<Song> {

  private DoublyLinkedNode<Song> head;
  private DoublyLinkedNode<Song> tail;
  private boolean playDirectionForward;
  
  public SongCollection() {
    
    head = null;
    tail = null;
    playDirectionForward = true;
  }
  
  public void add(Song song) {
    if(song == null) {
      throw new NullPointerException("song is null");
    }
    if(head == null)
    {
      DoublyLinkedNode<Song> obj = new DoublyLinkedNode<Song>(head,song,tail);
      head = obj;
      tail = obj;
    }
    else
    {
      
      DoublyLinkedNode<Song> obj = new DoublyLinkedNode<Song>(song);
      obj.setPrevious(tail);
      tail.setNext(obj);
      tail = obj;
    }        
    
  }
  
  public Song remove() // removes and returns song from the front/head of this list
  // when list is empty, throws a NoSuchElementException
  {
    if(head == null)
    {
      throw new NoSuchElementException("list is empty");
    }
    else {
      
      if(tail.equals(head))
      {
        DoublyLinkedNode<Song> obj = head;
        tail = null;
        head = null;
        return obj.getData();
      }
      DoublyLinkedNode<Song> newHead = head.getNext();
      DoublyLinkedNode<Song> oldHead = head;
      head = newHead;
      newHead.setPrevious(null);      
      return oldHead.getData();
      
    }
  }
  
  public void setPlayDirection(boolean isForward) {
    playDirectionForward = isForward;
  }

  @Override
  public Iterator<Song> iterator() {
    if(playDirectionForward==true)
    {
      return (new Playlist(head));
    }
    else
      return(new ReversePlaylist(tail));
    
  }
  
  //Other Methods
  
  public static void analysisMethodA(SongCollection songs) {
    songs.add(new Song("C is for Cookie.", "Cookie Monster"));
    songs.add(new Song("Rubber Duckie.", "Ernie"));
    songs.add(new Song("Elmo's Song.", "Elmo"));
   }
   public static void analysisMethodB(SongCollection songs) {
    SongCollection copy = new SongCollection();
    for(Song song: songs)
    {      
      copy.add(song);
    }    
        
    for(Song song: copy)
    {
      System.out.println(song);      
    }
    
   }
   public static void analysisMethodC(SongCollection songs) {
    Iterator<Song> playlist = songs.iterator();
    for(int i=0;i<1000;i++)
    if(playlist.hasNext())
    System.out.println( playlist.next() );
   }
   
   
//   public static void main(String[] args) {
//     SongCollection collec = new SongCollection();
//     analysisMethodA(collec);
//     analysisMethodB(collec);
//     analysisMethodC(collec);
//     
//   }
   
///////////////////////////////////////////////////////////////////////////////////
//For each of the following big-O time complexity analyses, consider the problem
//size to be the number of Songs that are stored within the argument songs, when
//the method is first called.
//
//For analysisMethodA, the big-O time complexity is __O(1)__________.
//
//For analysisMethodB, the big-O time complexity is ___O(N)_________.
//
//For analysisMethodC, the big-O time complexity is ___O(1000)_________.
//
///////////////////////////////////////////////////////////////////////////////////
   


 
}

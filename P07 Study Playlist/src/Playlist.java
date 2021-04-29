import java.util.Iterator;
import java.util.NoSuchElementException;

public class Playlist implements Iterator<Song> {

 private DoublyLinkedNode<Song> songNode;

public Playlist(DoublyLinkedNode<Song> songNode){
   
   this.songNode = songNode;   
 }
  
  @Override
  public boolean hasNext() {
    if(songNode==null)
      return false;
    else return true;   
  }

  @Override
  public Song next() {
       
    if(hasNext()==true)
     {
      DoublyLinkedNode<Song> currentSongNode = songNode;
      songNode = currentSongNode.getNext();    
      return currentSongNode.getData();
     }    
    else
      throw new NoSuchElementException("No songs left");
    }
   
  }



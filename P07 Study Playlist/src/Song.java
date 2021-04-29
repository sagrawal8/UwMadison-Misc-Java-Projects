
public class Song {

  private String title;
  private String artist;
  
  public Song(String title, String artist) {
    this.title = title;
    this.artist = artist; 
  }


  public String toString()
  {
    String str =title+" by "+artist;
    return str;    
  }
  
  public boolean equals(Object other) {
    
    Song obj = new Song("x","x");
    if(!other.getClass().equals(obj.getClass()))
    {
      return false;
    }
    return other.toString().equals(this.toString());
  }
  
}

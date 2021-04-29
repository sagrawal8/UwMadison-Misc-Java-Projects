import java.io.File;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

public class MatchingGame {
  
 //Congratulations message
private final static String CONGRA_MSG = "CONGRATULATIONS! YOU WON!";
//Cards not matched message
private final static String NOT_MATCHED = "CARDS NOT MATCHED. Try again!";
//Cards matched message
private final static String MATCHED = "CARDS MATCHED! Good Job!";
//2D-array which stores cards coordinates on the window display
private final static float[][] CARDS_COORDINATES =
new float[][] {{170, 170}, {324, 170}, {478, 170}, {632, 170},
{170, 324}, {324, 324}, {478, 324}, {632, 324},
{170, 478}, {324, 478}, {478, 478}, {632, 478}};
//Array that stores the card images filenames
private final static String[] CARD_IMAGES_NAMES = new String[] {"apple.png",
"ball.png", "peach.png", "redFlower.png", "shark.png", "yellowFlower.png"};

private static PApplet processing; // PApplet object that represents
//the graphic display window
private static Card[] cards; // one dimensional array of cards
private static PImage[] images; // array of images of the different cards
private static Random randGen; // generator of random numbers
private static Card selectedCard1; // First selected card
private static Card selectedCard2; // Second selected card
private static boolean winner; // boolean evaluated true if the game is won,
//and false otherwise
private static int matchedCardsCount; // number of cards matched so far
//in one session of the game
private static String message; // Displayed message to the display window
private static Card[] matchedCards;
private static int matchedCardsIndex=0; 
/**
* Defines the initial environment properties of this game as the program starts
*/
  public static void setup(PApplet processing)
  {
    MatchingGame.processing = processing;
 // Set the color used for the background of the Processing window
   // processing.background(245, 255, 250); // Mint cream color
    images = new PImage[CARD_IMAGES_NAMES.length]; 
  //load apple.png image file as PImage object and store its reference into images[0]
    images[0] = processing.loadImage("images" + File.separator + CARD_IMAGES_NAMES[0]);
    images[1] = processing.loadImage("images" + File.separator + CARD_IMAGES_NAMES[1]);
    images[2] = processing.loadImage("images" + File.separator + CARD_IMAGES_NAMES[2]);
    images[3] = processing.loadImage("images" + File.separator + CARD_IMAGES_NAMES[3]);
    images[4] = processing.loadImage("images" + File.separator + CARD_IMAGES_NAMES[4]);
    images[5] = processing.loadImage("images" + File.separator + CARD_IMAGES_NAMES[5]);
    matchedCards= new Card[CARD_IMAGES_NAMES.length*2];
    initGame();
    keyPressed();
   }
  
  /**
  * Initializes the Game
  */
  public static void initGame() {
    randGen = new Random(Utility.getSeed());
    selectedCard1 = null;
    selectedCard2 = null;
    matchedCardsCount = 0;
    winner = false;
    message = "";
    cards = new Card[CARD_IMAGES_NAMES.length*2];
    int[] position = new int[] {0,1,2,3,4,5,6,7,8,9,10,11};     //Array denoting the index of images in the images array
   
    int cardsarraycount=0;      //assigns same image to diff positions 
    
    for(int i=0;i<images.length;i++)
    {
     
      int rand_int1 = -1;         //declare random integer              
      boolean num1 = false;  //counter to check if random position is taken or not
      
      while(num1==false)
      {
        rand_int1 = randGen.nextInt(12);   //generates random integer b/w 0 and 12
        for(int j=0;j<position.length;j++)
        {
          if(position[j]==rand_int1)        //checks if random integer (Co-ordintes of Image) is already assigned to a previous image
          {
            position[j]=-1;
            num1=true;            
          }
          if(num1==true)
            break;
        }
      }
      cards[cardsarraycount]= new Card(images[i], CARDS_COORDINATES[rand_int1][0],CARDS_COORDINATES[rand_int1][1]);  //Assigns the image in the images array at index i a place on the board
    
      //assigning the matching image
      
      
      num1 = false;                         
      rand_int1= -1;
      while(num1==false)
      {
        rand_int1 = randGen.nextInt(12);   //generates random integer b/w 0 and 12
        for(int j=0;j<position.length;j++)
        {
          if(position[j]==rand_int1)            //checks if random integer (Co-ordintes of Image) is already assigned to a previous image
          {
            position[j]=-1;
            num1=true;
          }
          if(num1==true)
            break;
        }
      }
      cards[cardsarraycount+1]= new Card(images[i], CARDS_COORDINATES[rand_int1][0],CARDS_COORDINATES[rand_int1][1]);  //Assigns co-ordinate to the matching image
    
      if(cardsarraycount==10)
        break;
      if(cardsarraycount<10)
      cardsarraycount+=2;
    }
      
  }
  
  /**
  * Callback method called each time the user presses a key
  */
  public static void keyPressed()
  {
    if (processing.key=='n'||processing.key=='N')
    {
      winner=false;                         //resets the winner variable
      matchedCardsIndex=0;                  //resets matched cards index
      displayMessage("");                   //resets the congratulations message displayed at the end of previous game to an empty string
      matchedCards = new Card[CARD_IMAGES_NAMES.length*2];      //re-initializes matchedCards array
      initGame();
    }
        
  }
  
  /**
  * Callback method draws continuously this application window display
  */
  public static void draw() {
    
    processing.background(245, 255, 250); // Mint cream color
    for(int i=0;i<cards.length;i++)
    {     
      cards[i].draw();      
    }   
    
    displayMessage(message);    

  }
  
  /**
  * Displays a given message to the display window
  * @param message to be displayed to the display window
  */
  public static void displayMessage(String message) {
  processing.fill(0);
  processing.textSize(20);
  processing.text(message, processing.width / 2, 50);
  processing.textSize(12);
  }

  /**
  * Checks whether the mouse is over a given Card
  * @return true if the mouse is over the storage list, false otherwise
  */
  public static boolean isMouseOver(Card card) {
    
    float currentX = (float) processing.mouseX;     //gets X co-ordinate of current mouse poistion
    float currentY = (float) processing.mouseY;     //gets Y co-ordinate of current mouse poistion
    PImage current;
    current = card.getImage();                      //gets image of associated with card
    float cardHeight = (float) current.height;          //gets height of image card
    float cardWidth = (float) current.width;            //gets width of image card
    float cardX = card.getX();                      //gets x co-ordinte at center of card
    float cardY=card.getY();                        //gets y co-ordinte at center of card
    
    if(((currentX<=cardX+cardWidth/2) && (currentX>=cardX-cardWidth/2))&& ((currentY>=cardY-cardHeight/2)&& (currentY<=cardY+cardHeight/2)))
    {
      return true;
    }
    else return false;
  }
  
  
 
  /**
  * Callback method called each time the user presses the mouse
  */
  public static void mousePressed() {
 
   if(winner==true)
      return;
    
    for(int i=0;i<cards.length;i++)
   
        {
          
          if(isMouseOver(cards[i]))
          {
             if(selectedCard1!=null&&selectedCard2!=null) 
            {
                 selectedCard1.setVisible(false);
                 selectedCard1.deselect();
                 selectedCard2.setVisible(false);
                 selectedCard2.deselect();
                 selectedCard1=null;
                 selectedCard2=null;
            }
            
            if(matchedCards[0]!=null)
            {
              
              for(int k=0;k<matchedCardsIndex;k++)
              {
                if(matchedCards[k]==selectedCard1||matchedCards[k]==selectedCard2)
                {
                  return;
                }
              }
            }
            cards[i].setVisible(true);
            cards[i].select(); 
            if(selectedCard1==null && selectedCard2==null)
              selectedCard1=cards[i];
            else if(selectedCard1!=null&&cards[i]!=selectedCard1&&selectedCard2==null) 
              {
                selectedCard2=cards[i];
                if(matchingCards(selectedCard1,selectedCard2))
                {
                  
                  message = MATCHED;
                  matchedCardsCount++;
                  selectedCard1.deselect();
                  selectedCard2.deselect();
                  matchedCards[matchedCardsIndex]=selectedCard1;
                  matchedCards[matchedCardsIndex+1]=selectedCard2;
                  matchedCardsIndex+=2;
                  selectedCard1=null;
                  selectedCard2=null;
                }
                else 
                {
                  message=NOT_MATCHED;
                  
                }
                
              }
          }
          
        }
      
    
    
    displayMessage(message);
    
    if(matchedCardsCount==6)
    {
      winner=true;      
    }
    if (winner==true)
    {
      message = CONGRA_MSG;
      displayMessage(message);
      return;
    }
  
  }
  
  
  /**
  * Checks whether two cards match or not
  * @param card1 reference to the first card
  * @param card2 reference to the second card
  * @return true if card1 and card2 image references are the same, false otherwise
  */
  public static boolean matchingCards(Card card1, Card card2) {
    
    boolean flag = false;
    
    if (card1.getImage()==card2.getImage())
     flag=true;
    
    return flag;
    
  }
  
  
  
  public static void main(String[] args)
  {
    Utility.runApplication(); // starts the application
   
  }
  
}

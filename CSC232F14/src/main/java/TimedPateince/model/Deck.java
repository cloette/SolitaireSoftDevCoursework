package TimedPatience;

import java.util.ArrayList;
import java.util.Random;

/**
 * CSC232A - Fall 2014
 * Due date: 10/17/14 Assignment: Homework #4
 * 
 * @author Cloette Owensby, Bolun Zhang, Connie Uribe
 * 
 *         The Deck class contains operations that can be
 *         performed on the deck, including filling he deck, 
 *         shuffling the deck, clearing the deck, adding cards 
 *         to the deck, and dealing cards from the deck. This 
 *         class also checks to see if the deck is empty.
 */

public class Deck
{
   private int nextIndex;
   private ArrayList<Card> aDeck;
   int size;
   
   /** Constructs an array of 52 cards to act as the deck. */

  public Deck()
   {
      aDeck = new ArrayList<Card>();
      size = 52;
   }
  
  /** Getter method for finding a card's value (rank and suit) from its position in the deck.*/
  
  public int cardValue(int cardPosition){
    return aDeck.get(cardPosition).getRank(); 
  }
  
  /** Fills the deck with the standard library of 52 cards with 
   * 4 aces for each suit, 4 kings, 4 queens etc.*/
  
   void fill()
   {
      nextIndex = 0;
      for (int i = 0; i < 4; i++) /* for every suit */
      {
         for (int j = 0; j < 13; j++) /* for every rank */
         {
            Card c = new Card(i, j, 1); /* create a card */
            add(c); /* add that card to the aDeck */
         }
      }
   }

   /** Shuffles the cards inside the Deck. */
   
   void shuffle()
   {
	   /** If the deck is empty, throw an error!*/
	   
      if (aDeck == null)
      {
         System.out.println("empty aDeck!");
         return;
      }
      /** Creates a randomized array of the cards in Deck 
       * and swaps it with the current Deck. */
      else
      {
         for (int i = aDeck.size() - 1; i >= 0; i--)
         {

            // get random index, j, from 0 to i
            Random c = new Random();
            int number = c.nextInt(aDeck.size() - i);

            // swap aDeck[i] with aDeck[j]
            Card temp = aDeck.get(i);
            aDeck.set(i, aDeck.get(number));
            aDeck.set(number, temp);

         }

      }
   }


   /** Shuffles the cards inside the array aDeck. (Still needs revision.) */

   /*
    * void shuffle() { if (aDeck == null) { System.out.println("empty aDeck!");
    * return; } else { for(int i = 0; i < 52; i++) { int random = (int
    * )(Math.random() * 51 + 0); if (aDeck2[random] == null) { aDeck2[random] =
    * aDeck[i]; } else { i--; } aDeck = aDeck2; } }
    * 
    * }
    */

   /**
    * Prints the position of the card just removed, then removes the card.
    * "Dealing card 1", "Dealing card 2"....
    */

   Card deal()
   {
      Card c = aDeck.get(aDeck.size()-1);
      aDeck.remove(aDeck.size()-1);
      size--;
      return c;
   }

   /** Add Card c to the top of the deck. */

   void add(Card c)
   {
      aDeck.add(c);
      nextIndex++;
   }

   /** Checks to see if there is a value in the current section of the array. */

   boolean isEmpty()
   {

      if (aDeck.size() == 0)
      {
         return true;
      }
      else
      {
         return false;
      }

   }

   /** Empties the deck of all stored cards. */

   void clear()
   {
      aDeck = null;
   }
   
   /** Constructs a new deck and fills it with the standard card library.*/

  public static void main(String[] arg){
     Deck sampleDeck = new Deck();
     sampleDeck.fill();


     
  }
   
   

}

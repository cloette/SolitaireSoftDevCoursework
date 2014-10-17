package csc232;

import java.util.ArrayList;
import java.util.Random;

/**
 * Due date: 10/17/14 Assignment: Homework #4
 * 
 * @author Cloette Owensby, Bolun Zhang, Connie Uribe
 * 
 *         Creates a simple game of Solitaire, where the player must draw cards
 *         until the end of the aDeck is reached. If the player goes past the end
 *         of the aDeck, they lose the game.
 */

public class Deck
{
   private int nextIndex;
   private ArrayList<Card> aDeck;
   int size;
   /* Fills up the aDeck with 52 cards. */

  public Deck()
   {
      aDeck = new ArrayList<Card>();
      size = 5;
   }
  
  public int cardValue(int cardPosition){
    return aDeck.get(cardPosition).getRank(); 
  }
  
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

   /* Shuffles the cards inside the array aDeck. */
   void shuffle()
   {
      if (aDeck == null)
      {
         System.out.println("empty aDeck!");
         return;
      }
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


   /* Shuffles the cards inside the array aDeck. */

   /*
    * void shuffle() { if (aDeck == null) { System.out.println("empty aDeck!");
    * return; } else { for(int i = 0; i < 52; i++) { int random = (int
    * )(Math.random() * 51 + 0); if (aDeck2[random] == null) { aDeck2[random] =
    * aDeck[i]; } else { i--; } aDeck = aDeck2; } }
    * 
    * }
    */

   /*
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

   /* Add Card c to the top of the aDeck. */

   void add(Card c)
   {
      aDeck.add(c);
      nextIndex++;
   }

   /* Checks to see if there is a value in the current section of the array. */

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

   /* Empties the array (aDeck) of all stored cards. */

   void clear()
   {
      aDeck = null;
   }

  public static void main(String[] arg){
     Deck sampleDeck = new Deck();
     sampleDeck.fill();


     
  }
   
   

}

package csc232;


/**
Due date: 10/17/14
Assignment: Homework #4
@author Cloette Owensby, Bolun Zhang, Connie Uribe
*
Creates a simple game of Solitaire, where the player must draw 
cards until the end of the deck is reached. If the player goes 
past the end of the deck, they lose the game.
*/



public class Deck
{
   /* Fills up the deck with 52 cards. */
   
   Deck()
   {
       Deck = new Card[52];
   }
   
   void fill()
   {
      for (int i = 0; i< 4; i++)  /*for every suit */
      {
         for (int j=0; j<13; j++) /*for every rank*/
         {
            Card c = new Card(i , j, 1); /*create a card */
            add(c);   /*add that card to the deck */
         }
      }
   }
   
   /* Shuffles the cards inside the array Deck. */
   
  /* void shuffle()
   {
      if (Deck == null)
      {
         System.out.println("empty deck!");
         return;
      }
      else
      {
         for(int i = 0; i < 52; i++)
         {
           int random = (int )(Math.random() * 51 + 0);
           if (Deck2[random] == null)
           {
              Deck2[random] = Deck[i];
           }
           else
           {
              i--;
           }
         Deck = Deck2;
         }
      }
      
   }
   */
   
   /* Prints the position of the card just removed, then removes 
    * the card. "Dealing card 1", "Dealing card 2".... */
   
   Card deal()
   {
      return c;
   }
   
   /* Add Card c to the top of the deck. */
   
   void add(Card c)
   {
      Deck[nextIndex] = c;
      
   }
   
   /* Checks to see if there is a value in the current section of the array. */
   
   boolean isEmpty()
   {
     
      if (Deck == null)
      {
         return true;
      }
      else
      {
         return false;
      }
      
   }
   
   /* Empties the array (deck) of all stored cards. */
   
   void clear()
   {
      Deck = null;
   }
   

   private Card c; 
   private Card[] Deck; 
   private Card[] Deck2;
   int nextIndex = 0;

   
   
}

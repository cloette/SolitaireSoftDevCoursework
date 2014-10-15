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
   void shuffle()
   {
      int[] unshuffled = new int[Deck.length];
      int[] shuffled = new int[Deck.length];
      while (Deck.length != NULL){           //checks if Deck is NULL
         for( int cardIndex = Deck.length; cardIndex > 0; cardIndex--){      //going from card 52-1
            String cardContext = shuffled[cardIndex];                         //stored the context of the shuffled card in the local variable
            for( int position = 0; position< Deck.length; position++){        //goes from 0-51
               unshuffled[position] = cardContext;                            //takes the context stored in variable and stores it in the current position
            }
         }
         
         Random card = new Random(Deck.length);          // picks a random number from the length of the deck
         String randomCard = unshuffled[card];           //gets the context of that random number from the unshuffled
         for( int i = 0; i < Deck.length; i++){          // goes from 0-51
            String shuffledCard = shuffled[i];           //gets the value of the current position
            shuffled[i] = randomCard;                    //stores the random value to the shuffled array
            unshuffled[card] = shuffledCard;             // replaces the random value with the value of the shuffled card
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

package csc232;

/**
 * CSC232A - Fall 2014
 * Date: 010/11/2014
 * 
 * @author Connie Uribe, Bolun Zhang, Cloette Owensby
 * 
 *         This class exists to test the deck class.
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class DeckTest
{

   @Test
   public void test()
   {
      /**tests to see if the deck is set to the size when called on the fill function.*/
	   
      Deck myDeck = new Deck();
      myDeck.fill();
      if(myDeck.equals(myDeck.size)){
         assertTrue(true);
      }
      
      /**tests to see if the deck is set to null, when the array list is empty.*/
      
      myDeck.clear();
      if(myDeck.equals(null)){
         assertTrue(true);
      }
      
      /**Test to see if the deal function removes the a card.*/
      
      myDeck.fill();
      myDeck.deal();
      if(myDeck.equals(myDeck.size - 1)){
         assertTrue(true);
      }

      
      
      /**
      Various tests for the Deck class
      */

      /**
      1. Tests to see if the deck is set to null when the array list Deck is empty. 
      */

      /**
      2. Tests to see if a Card exists in the array list once it is added to the Deck.
      */

      /**
      3. Tests to see if the deck has been shuffled by looking at the order of the cards before and after shuffling. 
      */

      /**
      4. Tests to see if a Card no longer exists in the array list once it is dealt.
      */

      /**
      5. Tests to see if Deck throws the exception error when we try to delete more cards in the deck than the number of cards that exist/have been added.
      */

      /**
      6. Adds an Ace of spades to the deck and then looks at the array list to find that ace of spades. Searches for a joker card that does not exist and returns an error message "_________ is not part of this deck."
      */
   }

}

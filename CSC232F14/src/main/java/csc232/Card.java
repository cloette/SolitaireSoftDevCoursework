package csc232;
/**
Due date: 09/17/14
Assignment: Homework #2 
@author Connie Uribe
*
Design decision: I chose to organize my Card class by creating the constructor,
then the getters and setters methods, and lastly the fields. I chose to 
organize it this way because that is one standard way of doing it. Other tha 
*/


public class Card 
{

        /*
        *
        Construct a Card object given a suit, rank, and status
        *
        @param suit The suit of the card: out of the 4, what sign is it
        @param rank The ranking of the card: what value does the card have
        @param status The status of the card: face up or down
        *
        */
        public Card(String suit, String rank, String status)
        {
                this.suit = suit;
                this.rank = rank;
                this.status = status;
        }


        /**
        Override the default <code>toString</code> method to return the card's state formatted into 4 lines.
        */
        public String toString()
        {
                return "Card:\n " + "suit: " + suit + "\n rank: " + rank + "\n status: " + status;
        }

        /*
        A method to access the suit of the card
        */
        String getSuit()
        {
                return suit;
        }

        /*
        A method to access the rank of the card
        */ String getRank ()
        {
           return rank;
        }

         /*
         A method to access the status of the card
         */
         String getStatus ()
         {
                 return status;
         }



         /*
         A method to store/set new suit values
         */
         public void setSuit ( String newSuit)
         {
                 suit = newSuit;
         }
      
         /*
         A method to store/set new rank values
         */
         public void setRank( String newRank)
         {
                 rank = newRank;
         }
         /*
         A method to Store/set new status values
         */
         public void setStatus( String newStatus)
         {
                 status = newStatus;
         }
      
         /*
         Creating the fields for the class Card
         */
         private String suit;
         private String rank;
         private String status;
 }

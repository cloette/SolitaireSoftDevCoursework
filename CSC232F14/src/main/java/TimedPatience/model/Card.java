package TimedPatience;

/**
 * CSC232A - Fall 2014
 * Due date: 09/17/14
 * Assignment: Homework #2 
 * @author Connie Uribe(original), Bolun Zhang(revised), Cloette Owensby(comments)
 * 
 * Design decision: I chose to organize my Card class by creating the constructor,
 * then the getters and setters methods, and lastly the fields. I chose to 
 * organize it this way because that is one standard way of doing it. -Connie
 * 
 * Our Card class translates the string names of the cards ("Ace, King") into integers and 
 * translates the integers back into strings for printing. Rank cannot exceed 13, Suit cannot 
 * exceed 4, and status cannot exceed 2. - Cloette
*/

public class Card
{
            private int Rank, Suit, Status;

            private static String[] suits = { "hearts", "spades", "diamonds", "clubs" };
            private static String[] ranks  = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
            private static String[] status = {"Face up", "Face down"};
            
            /** Constructs integers to hold the value (rank, suit, status) of each card.*/

            Card(int St, int Rk, int Ss)
            {
                this.Rank=Rk;
                this.Suit=St;
                this.Status=Ss;
            }
            
          /*  public Card(int Suit, int Rank, int Status)
            {
                this.Rank=Rank;
                this.Suit=Suit;
                this.Status=Status;
            }*/
            
            /** Prints the card. Returns the card name and value as a string.*/

            public String toString()
            {
                  return "suit: "+ suits[Suit]+" rank: "+ ranks[Rank]+ " status: "+ status[Status];
                  //ranks[Rank] + " of " + suits[Suit] + ", "+ status[Status];
            }
            
            /** Returns the rank of the card.*/

            public int getRank() {
                 return Rank;
            }
            
            /** Returns the suit of the card.*/

            public int getSuit() {
                return Suit;
            }
            
            /** Returns the status of the card.*/

            public int getStatus() {
                return Status;
            }
        }






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
            private int Rank, Suit, Status;

            private static String[] suits = { "hearts", "spades", "diamonds", "clubs" };
            private static String[] ranks  = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
            private static String[] status = {"Face up", "Face down"};

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

            public String toString()
            {
                  return "suit: "+ suits[Suit]+" rank: "+ ranks[Rank]+ " status: "+ status[Status];
                  //ranks[Rank] + " of " + suits[Suit] + ", "+ status[Status];
            }

            public int getRank() {
                 return Rank;
            }

            public int getSuit() {
                return Suit;
            }

            public int getStatus() {
                return Status;
            }
        }






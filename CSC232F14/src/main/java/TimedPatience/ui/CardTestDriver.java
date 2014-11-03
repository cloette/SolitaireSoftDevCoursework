package TimedPatience;

/**
 * CSC232A - Fall 2014
 * Date: 09/17/2014
 * 
 * @author Connie Uribe, Bolun Zhang, Cloette Owensby
 * 
 *         This file is to test the Card.java file.
 */

public class CardTestDriver
{
	/** Creates two distinct cards and prints them each on a new line. */
	
       public static void main(String[] args)
       {
               Card card1 = new Card( 2,2,1);
               Card card2 = new Card( 1,1,1);

               System.out.println (card1);
               System.out.println();
               System.out.println (card2);
       }
}



package csc232;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/** This class exists to manage the user prompts and operation of the game. */

public class GameDriver {
   
   public static void main(String[] args) {
    
         /** Creates a simple user interface to test out and debug the Solitaire game. */
         
         JFrame frame = new JFrame();
         frame.setSize(400, 300);
         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         
         JOptionPane.showMessageDialog(null, "Let's play Solitaire!","Message", JOptionPane.INFORMATION_MESSAGE);
         JButton pushMe = new JButton("Deal the next card!");
         frame.add(pushMe);
         
         /** creates a deck */

         Deck myDeck = new Deck();
         myDeck.fill();
         int cardPosition = myDeck.size -1; /** (in the array, so 51-0) */
 
         /** shuffles the deck */

         /*myDeck.shuffle();*/

         /** Checks to see if there enough cards left in the deck to deal again.
         /** If not, the user has lost. The game then ends. */
         
         
        /* if ( cardPosition < cardValue )
         {
            JOptionPane.showMessageDialog(null, "You lost! Sorry. Better luck next time.");
         }*/

         /** Check to see if there are no cards left in the deck. 
	      /** If so, the user has won. The game then ends.*/

        /* if ( cardPosition == cardValue )
         {
            JOptionPane.showMessageDialog(null, "You won! Congrats! Huzzah! You did it!"); 
         }*/

         /** Deals cards until the deck is empty or runs out. */

         while ( myDeck.size != 0)
         {
            int cardValue = myDeck.cardValue(myDeck.size-1)+1;/** (rank, ace, jack, etc. 1-10) */
            final Card printCard = myDeck.deal(); /** deals the next card */
            if (cardValue <= 10)
            {
               for (int i = 0; i < cardValue; i++)
               {
                  if(myDeck.isEmpty() == true)
                  {
                     JOptionPane.showMessageDialog(null, "You lost! Sorry. Better luck next time.");
                  }
                  JOptionPane.showMessageDialog(null, myDeck.deal().toString());
               }
            }
            else
            {
               for (int i = 0; i < 10; i++)
               {
                  if(myDeck.isEmpty() == true)
                  {
                     JOptionPane.showMessageDialog(null, "You lost! Sorry. Better luck next time.");
                  }
                  JOptionPane.showMessageDialog(null, myDeck.deal().toString());
               }
               
               if ( myDeck.isEmpty() == true )
               {
                  JOptionPane.showMessageDialog(null, "You won! Congrats! Huzzah! You did it!"); 
               }
            }
            
            /**Displays the name of the card each time it is dealt. */
            pushMe.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
               {
                  JOptionPane.showMessageDialog(null, printCard.toString());
               }
            });
         }
         frame.setVisible(true);
}
}


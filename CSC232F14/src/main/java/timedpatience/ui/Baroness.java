package timedpatience.ui;

/**
 * Creates a game of Perpetual Motion. User wins when there are
 * no cards left in any of the piles.
 * 
 * @author Cloette Owensby, Connie Uribe, Bolun Zhang
 * @date 11/16/2014
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import timedpatience.model.Card;
import timedpatience.model.Deck;



public class Baroness extends JPanel 
{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   
   //private static JTextField ORE;
   static int score = 0;
   static JLabel SC = new JLabel("Score: " + score);
   
   public static void main()
   { 
     // JTextField SC = null;
      JFrame frame = new JFrame("Baroness");
      JPanel sub = new JPanel();
      JPanel subb = new JPanel();
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setSize(600, 400);
      
      frame.setLayout(new BorderLayout());
      JButton nU = new JButton("UNDO");
      JButton nR = new JButton("REDO");

      sub.add(nU);
      sub.add(nR);
      subb.add(SC);
      
     
      frame.getContentPane().add(sub, BorderLayout.SOUTH);
      frame.getContentPane().add(subb, BorderLayout.NORTH);

      
      final Deck deckA = new Deck();
      deckA.fill();
      deckA.shuffle();
      
      // Creates the other four piles
      
      final Deck deckB = new Deck();
      final Deck deckC = new Deck();
      final Deck deckD = new Deck();
      final Deck deckE = new Deck();
      
      final Deck tempDeckA = new Deck();
      final Deck tempDeckB = new Deck();
      final Deck tempDeckC = new Deck();
      final Deck tempDeckD = new Deck();
      final Deck tempDeckE = new Deck();
      
      // Loads the card images
      
      File imageDirectory = new File("src/main/Resources/Cards");
      CardImages images = new CardImages(imageDirectory);
      
      final DeckComponent dcA = new DeckComponent(deckA, images);
      final DeckComponent dcB = new DeckComponent(deckB, images, DeckComponent.FAN_VERTICAL);
      final DeckComponent dcC = new DeckComponent(deckC, images, DeckComponent.FAN_VERTICAL);
      final DeckComponent dcD = new DeckComponent(deckD, images, DeckComponent.FAN_VERTICAL);
      final DeckComponent dcE = new DeckComponent(deckE, images, DeckComponent.FAN_VERTICAL);
      
      dcA.setDeckListener(new DeckListener()
      {
         public void handleClick(DeckComponent deckComponent)
         {
            // When clicked, deal a card to deck B, C, D, and E.
            
            /*tempDeckA.clear();
            tempDeckB.clear();
            tempDeckC.clear();
            tempDeckD.clear();
            tempDeckE.clear();
            for (int i = 0; i < deckA.size(); i++)
               {
               tempDeckA.add(deckA.getNum(i));
               }
            for (int i = 0; i < deckB.size(); i++)
               {
               tempDeckB.add(deckB.getNum(i));
               }
            for (int i = 0; i < deckC.size(); i++)
               {
               tempDeckC.add(deckC.getNum(i));
               }
            for (int i = 0; i < deckD.size(); i++)
               {
               tempDeckD.add(deckD.getNum(i));
               }
            for (int i = 0; i < deckE.size(); i++)
               {
               tempDeckE.add(deckE.getNum(i));
               }*/
            
            Card newTop = deckComponent.getTopCard(); //top of the stock pile
            
            if (newTop == null){
               // If all piles are empty, the user has won. Print a winner message.
               if (deckA.isEmpty() && deckB.isEmpty() && deckC.isEmpty() && deckD.isEmpty()){
                  JOptionPane.showMessageDialog(null, "Congrats! You won. Play again?");
                  deckA.clear();
                  deckB.clear();
                  deckC.clear();
                  deckD.clear();
                  deckE.clear();
                  deckA.fill();
                  deckA.shuffle();
               }
               // all the cards go back into the stock pile
            }
               
            if (newTop != null && !newTop.isFaceUp()) {
               //deckComponent.flipTopCard();
            }
            
            Card card1 = deckComponent.removeTopCard();
            Card card2 = deckComponent.removeTopCard();
            Card card3 = deckComponent.removeTopCard();
            Card card4 = deckComponent.removeTopCard();
            
            // as long as there are enough cards left to deal, deal them upon click
            
            if (card1 != null && card2 != null && card3 != null && card4 != null)
            {
               dcB.addCard(card1);
               dcB.flipTopCard();
               dcC.addCard(card2);
               dcC.flipTopCard();
               dcD.addCard(card3);
               dcD.flipTopCard();
               dcE.addCard(card4);
               dcE.flipTopCard();
               
               // if all dealt cards have the same rank, remove those 4 from the board
                             
            }
         }
         
         // Dragging the card
         
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            // Only allow drops of the same rank as the top card
            return false;
         }
      });
      
      dcB.setDeckListener(new DeckListener()
      {
         public void handleClick(DeckComponent deckComponent)
         {
            if (deckComponent.getTopCard().getRank().getValue() + deckComponent.getPrevCard().getRank().getValue() == 13){
               deckComponent.removeTopCard();
               deckComponent.removeTopCard();
               score += 100;
               SC.setText("Score: " + score);
            }
            if (deckComponent.getTopCard().getRank().getValue() == 13){
               deckComponent.removeTopCard();
               score += 100;
               SC.setText("Score: " + score);
            }
         }
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            // Only allow drops of the same rank as the top card
            return (card.getRank().getValue() == 13- deckComponent.getTopCard().getRank().getValue());
         }
      });
      
      dcC.setDeckListener(new DeckListener()
      {
         public void handleClick(DeckComponent deckComponent)
         {
            if (deckComponent.getTopCard().getRank().getValue() + deckComponent.getPrevCard().getRank().getValue() == 13){
               deckComponent.removeTopCard();
               deckComponent.removeTopCard();
               score += 100;
               SC.setText("Score: " + score);
            }
            if (deckComponent.getTopCard().getRank().getValue() == 13){
               deckComponent.removeTopCard();
               score += 100;
               SC.setText("Score: " + score);
            }
         }
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            // Only allow drops of the same rank as the top card
            return (card.getRank().getValue() == 13- deckComponent.getTopCard().getRank().getValue());
         }
      });
      
      dcD.setDeckListener(new DeckListener()
      {
         public void handleClick(DeckComponent deckComponent)
         {
            if (deckComponent.getTopCard().getRank().getValue() + deckComponent.getPrevCard().getRank().getValue() == 13){
               deckComponent.removeTopCard();
               deckComponent.removeTopCard();
               score += 100;
               SC.setText("Score: " + score);
            }
            if (deckComponent.getTopCard().getRank().getValue() == 13){
               deckComponent.removeTopCard();
               score += 100;
               SC.setText("Score: " + score);
            }
         }
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            // Only allow drops of the same rank as the top card
            return (card.getRank().getValue() == 13- deckComponent.getTopCard().getRank().getValue());
         }
      });
      
      dcE.setDeckListener(new DeckListener()
      {
         public void handleClick(DeckComponent deckComponent)
         {
            if (deckComponent.getTopCard().getRank().getValue() + deckComponent.getPrevCard().getRank().getValue() == 13){
               deckComponent.removeTopCard();
               deckComponent.removeTopCard();
               score += 100;
               SC.setText("Score: " + score);
            }
            if (deckComponent.getTopCard().getRank().getValue() == 13){
               deckComponent.removeTopCard();
               score += 100;
               SC.setText("Score: " + score);
            }
         }
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            // Only allow drops of the same rank as the top card
            return (card.getRank().getValue() == 13- deckComponent.getTopCard().getRank().getValue());
         }
      });
      
      
      nU.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            deckA.clear();
            deckB.clear();
            deckC.clear();
            deckD.clear();
            deckE.clear();
            
            for (int i = 0; i < tempDeckA.size(); i++)
            {
               deckA.add(tempDeckA.getNum(i));
            }
            for (int i = 0; i < tempDeckB.size(); i++)
            {
               deckB.add(tempDeckB.getNum(i));
            }
            for (int i = 0; i < tempDeckC.size(); i++)
            {
               deckC.add(tempDeckC.getNum(i));
            }
            for (int i = 0; i < tempDeckD.size(); i++)
            {
               deckD.add(tempDeckD.getNum(i));
            }
            for (int i = 0; i < tempDeckE.size(); i++)
            {
               deckE.add(tempDeckE.getNum(i));
            }
            tempDeckA.clear();
            tempDeckB.clear();
            tempDeckC.clear();
            tempDeckD.clear();
            tempDeckE.clear();

         }
      });
      
      nR.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            System.out.println(2);

         }
      });
      
      // allows the cards to be dragged onto other DeckComponents
      
      dcA.setDraggable(false);
      dcB.setDraggable(true);
      dcC.setDraggable(true);
      dcD.setDraggable(true);
      dcE.setDraggable(true);
      
      // Creates the board and the 5 piles.
            
      JPanel panel = new JPanel();
      panel.add(dcA);
      panel.add(dcB);
      panel.add(dcC);
      panel.add(dcD);
      panel.add(dcE);
      
     // frame.add(SC);
      frame.add(panel, BorderLayout.CENTER);
      frame.setVisible(true);
   }
}
package timedpatience.ui;

/**
 * Creates a game of Perpetual Motion. The game consists of 4 piles and a stock pile
 * It has a score feature and a redo/undo button. User the game wins the game when there are
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



@SuppressWarnings("serial")
public class BoardDriver extends JPanel 
{
   
   //private static JTextField ORE;
   static int score = 0;
   static JLabel SC = new JLabel("Score: " + score);
   
   final static Deck deckA = new Deck();
   final static Deck deckB = new Deck();
   final static Deck deckC = new Deck();
   final static Deck deckD = new Deck();
   final static Deck deckE = new Deck();

   
   final static Deck tempDeckA = new Deck();
   final static Deck tempDeckB = new Deck();
   final static Deck tempDeckC = new Deck();
   final static Deck tempDeckD = new Deck();
   final static Deck tempDeckE = new Deck();
   final static Deck TDA = new Deck();
   final static Deck TDB = new Deck();
   final static Deck TDC = new Deck();
   final static Deck TDD = new Deck();
   final static Deck TDE = new Deck();
   
   public static void saveForU()
   {
      tempDeckA.clear();
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
         }
   }
   
   public static void main()
   { 
     // JTextField SC = null;
      JFrame frame = new JFrame("BoardDriver");
      JPanel sub = new JPanel();
      JPanel subb = new JPanel();
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setSize(600, 400);
      
      frame.setLayout(new BorderLayout());
      JButton undo = new JButton("UNDO");
      JButton redo = new JButton("REDO");

      sub.add(undo);
      sub.add(redo);
      subb.add(SC);
      
     
      frame.getContentPane().add(sub, BorderLayout.SOUTH);
      frame.getContentPane().add(subb, BorderLayout.NORTH);

      
      deckA.fill();
      deckA.shuffle();
      
      // Creates the other four piles
      
      
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
            
            saveForU();
            
            Card newTop = deckComponent.getTopCard(); //top of the stock pile
            
            if (newTop == null){
               // If all piles are empty, the user has won. Print a winner message.
               if (deckA.isEmpty() && deckB.isEmpty() && deckC.isEmpty() && deckD.isEmpty()){
                  JOptionPane.showMessageDialog(null, "Congrats! You won. Play again?");
                  deckA.fill();
                  deckA.shuffle();
               }
               // all the cards go back into the stock pile
               else{
                  // empties out the pile back into the stock
                  for (int i=deckB.size(); i>0; i--){
                     Card card1 = dcB.getTopCard();
                     card1.flip();
                     dcB.removeTopCard();
                     deckA.add(card1);
                     
                  }
                  // empties out the pile back into the stock
                  for (int i=deckC.size(); i>0; i--){
                     Card card2 = dcC.getTopCard();
                     card2.flip();
                     dcC.removeTopCard();
                     deckA.add(card2);
                  }
                  // empties out the pile back into the stock
                  for (int i=deckD.size(); i>0; i--){
                     Card card3 = dcD.getTopCard();
                     card3.flip();
                     dcD.removeTopCard();
                     deckA.add(card3);
                  }
                  // empties out the pile back into the stock
                  for (int i=deckE.size(); i>0; i--){
                     Card card4 = dcE.getTopCard();
                     card4.flip();
                     dcE.removeTopCard();
                     deckA.add(card4);
                  }
               }
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
               
               if (card1.getRank() == card2.getRank() && card2.getRank() == card3.getRank() && card3.getRank() == card4.getRank())
               {
                 dcB.removeTopCard();
                 dcC.removeTopCard();
                 dcD.removeTopCard();
                 dcE.removeTopCard();
                 score += 100;
                 SC.setText("Score: " + score);
                 JOptionPane.showMessageDialog(null, "All cards had the same rank and were automatically removed.");
               }
                             
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
            
         }
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            // Only allow drops of the same rank as the top card
            if (deckComponent.getTopCard() == null)
            {
               return false;
            }
            if (card.getRank().equals(deckComponent.getTopCard().getRank()))
            {
               saveForU();
            }
            return card.getRank().equals(deckComponent.getTopCard().getRank());
         }
      });
      
      dcC.setDeckListener(new DeckListener()
      {
         public void handleClick(DeckComponent deckComponent)
         {
            
         }
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            // Only allow drops of the same rank as the top card
            if (deckComponent.getTopCard() == null)
            {
               return false;
            }
            if (card.getRank().equals(deckComponent.getTopCard().getRank()))
            {
               saveForU();
            }
            return card.getRank().equals(deckComponent.getTopCard().getRank());
         }
      });
      
      dcD.setDeckListener(new DeckListener()
      {
         public void handleClick(DeckComponent deckComponent)
         {
            
         }
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            // Only allow drops of the same rank as the top card
            if (deckComponent.getTopCard() == null)
            {
               return false;
            }
            if (card.getRank().equals(deckComponent.getTopCard().getRank()))
            {
               saveForU();
            }
            return card.getRank().equals(deckComponent.getTopCard().getRank());
         }
      });
      
      dcE.setDeckListener(new DeckListener()
      {
         public void handleClick(DeckComponent deckComponent)
         {
            
         }
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            // Only allow drops of the same rank as the top card
            if (deckComponent.getTopCard() == null)
            {
               return false;
            }
            if (card.getRank().equals(deckComponent.getTopCard().getRank()))
            {
               saveForU();
            }
            return card.getRank().equals(deckComponent.getTopCard().getRank());
         }
      });
      
      
      undo.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (tempDeckA.size()==deckA.size() && tempDeckB.size()==deckB.size() && tempDeckC.size()==deckC.size() && tempDeckD.size()==deckD.size() && tempDeckE.size()==deckE.size())
            {
               JOptionPane.showMessageDialog(null, "Only one Undo can be made between steps!");
               return;
            }
            if(tempDeckA.isEmpty() && tempDeckB.isEmpty() && tempDeckC.isEmpty() && tempDeckD.isEmpty() && tempDeckE.isEmpty())
            {
               JOptionPane.showMessageDialog(null, "Can't Undo before any move!");
               return;
            }
            else
            {
            TDA.clear();
            TDB.clear();
            TDC.clear();
            TDD.clear();
            TDE.clear();
            
            for (int i = 0; i < deckA.size(); i++)
            {
            TDA.add(deckA.getNum(i));
            }
         for (int i = 0; i < deckB.size(); i++)
            {
            TDB.add(deckB.getNum(i));
            }
         for (int i = 0; i < deckC.size(); i++)
            {
            TDC.add(deckC.getNum(i));
            }
         for (int i = 0; i < deckD.size(); i++)
            {
            TDD.add(deckD.getNum(i));
            }
         for (int i = 0; i < deckE.size(); i++)
            {
            TDE.add(deckE.getNum(i));
            }
            
            deckA.clear();
            deckB.clear();
            deckC.clear();
            deckD.clear();
            deckE.clear();
            
            if (tempDeckA.size() == 0)
            {
               dcA.addCard(null);
               deckA.clear();
            }
            if (tempDeckB.size() == 0)
            {
               dcB.addCard(null);
               deckB.clear();
            }
            if (tempDeckC.size() == 0)
            {
               dcC.addCard(null);
               deckC.clear();
            }
            if (tempDeckD.size() == 0)
            {
               dcD.addCard(null);
               deckD.clear();
            }
            if (tempDeckE.size() == 0)
            {
               dcE.addCard(null);
               deckE.clear();
            }
            for (int i = 0; i < tempDeckA.size(); i++)
            {
               if (tempDeckA.getNum(i).isFaceUp())
               {
                  tempDeckA.getNum(i).flip();
               }
               dcA.addCard(tempDeckA.getNum(i));
            }
            for (int i = 0; i < tempDeckB.size(); i++)
            {
               if (!tempDeckB.getNum(i).isFaceUp())
               {
                  tempDeckB.getNum(i).flip();
               }
               dcB.addCard(tempDeckB.getNum(i));
            }
            for (int i = 0; i < tempDeckC.size(); i++)
            {
               if (!tempDeckC.getNum(i).isFaceUp())
               {
                  tempDeckC.getNum(i).flip();
               }
               dcC.addCard(tempDeckC.getNum(i));
            }
            for (int i = 0; i < tempDeckD.size(); i++)
            {
               if (!tempDeckD.getNum(i).isFaceUp())
               {
                  tempDeckD.getNum(i).flip();
               }
               dcD.addCard(tempDeckD.getNum(i));
            }
            for (int i = 0; i < tempDeckE.size(); i++)
            {
               if (!tempDeckE.getNum(i).isFaceUp())
               {
                  tempDeckE.getNum(i).flip();
               }
               dcE.addCard(tempDeckE.getNum(i));
            }
            return;
            }
         }
      });
      
      redo.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (TDA.size()==deckA.size() && TDB.size()==deckB.size() && TDC.size()==deckC.size() && TDD.size()==deckD.size() && TDE.size()==deckE.size())
            {
               JOptionPane.showMessageDialog(null, "Only one Redo can be made between steps!");
               return;
            }
            else
            {
               if (TDA.size() == 0 && TDB.size() == 0 && TDC.size() == 0 && TDD.size() == 0 && TDE.size() == 0)
               {
                  JOptionPane.showMessageDialog(null, "Can't Redo before Undo!");
               }
               else
               {
                  deckA.clear();
                  deckB.clear();
                  deckC.clear();
                  deckD.clear();
                  deckE.clear();
                  
                  if (TDA.size() == 0)
                  {
                     dcA.addCard(null);
                     deckA.clear();
                  }
                  if (TDB.size() == 0)
                  {
                     dcB.addCard(null);
                     deckB.clear();
                  }
                  if (TDC.size() == 0)
                  {
                     dcC.addCard(null);
                     deckC.clear();
                  }
                  if (TDD.size() == 0)
                  {
                     dcD.addCard(null);
                     deckD.clear();
                  }
                  if (TDE.size() == 0)
                  {
                     dcE.addCard(null);
                     deckE.clear();
                  }
                  for (int i = 0; i < TDA.size(); i++)
                  {
                     if (TDA.getNum(i).isFaceUp())
                     {
                        TDA.getNum(i).flip();
                     }
                     dcA.addCard(TDA.getNum(i));
                  }
                  for (int i = 0; i < TDB.size(); i++)
                  {
                     if (!TDB.getNum(i).isFaceUp())
                     {
                        TDB.getNum(i).flip();
                     }
                     dcB.addCard(TDB.getNum(i));
                  }
                  for (int i = 0; i < TDC.size(); i++)
                  {
                     if (!TDC.getNum(i).isFaceUp())
                     {
                        TDC.getNum(i).flip();
                     }
                     dcC.addCard(TDC.getNum(i));
                  }
                  for (int i = 0; i < TDD.size(); i++)
                  {
                     if (!TDD.getNum(i).isFaceUp())
                     {
                        TDD.getNum(i).flip();
                     }
                     dcD.addCard(TDD.getNum(i));
                  }
                  for (int i = 0; i < TDE.size(); i++)
                  {
                     if (!TDE.getNum(i).isFaceUp())
                     {
                        TDE.getNum(i).flip();
                     }
                     dcE.addCard(TDE.getNum(i));
                  }
                  return;
               }
            }
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

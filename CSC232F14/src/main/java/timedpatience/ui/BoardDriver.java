package timedpatience.ui;


import java.awt.BorderLayout;

import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
   int cardType;
   Timer timer;
   static JLabel SC = new JLabel("Score: " + score);
   
   final static Deck deckA = new Deck(); //create 5 decks that are going to be used to put on the 5 piles for the game
   final static Deck deckB = new Deck();
   final static Deck deckC = new Deck();
   final static Deck deckD = new Deck();
   final static Deck deckE = new Deck();

   
   final static Deck tempDeckA = new Deck(); //create 5 shadow decks to store what's in each deck before each move (provide data for undo function)
   final static Deck tempDeckB = new Deck();
   final static Deck tempDeckC = new Deck();
   final static Deck tempDeckD = new Deck();
   final static Deck tempDeckE = new Deck();
   final static Deck TDA = new Deck(); //create additional shadow decks to store the state of deck A~E before undo is called
   final static Deck TDB = new Deck();
   final static Deck TDC = new Deck();
   final static Deck TDD = new Deck();
   final static Deck TDE = new Deck();
   
   public static void saveForU(int pile) // refresh the 5 shadow decks to store what's in each deck before each move (provide data for undo function)
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
      
      if (pile == 2) // to eliminate the extra card that is been stored in certain cases
      {
         tempDeckB.deal();
         pile = 0;
      }
      if (pile == 3)
      {
         tempDeckC.deal();
         pile = 0;
      }
      if (pile == 4)
      {
         tempDeckD.deal();
         pile = 0;
      }
      if (pile == 5)
      {
         tempDeckE.deal();
         pile = 0;
      }
   }
   
   
   public static void main(int cardType)
   { 
     // JTextField SC = null;
      JFrame frame = new JFrame("Perpetual Motion");
      JPanel sub = new JPanel();
      JPanel subb = new JPanel();
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setSize(600, 400);
      
      frame.setLayout(new BorderLayout());
      JButton undo = new JButton("UNDO");
      JButton redo = new JButton("REDO");
      JLabel timerLabel = new JLabel("Timer: !!!!!!");

      sub.add(undo);
      sub.add(redo);
      subb.add(SC);
      subb.add(timerLabel);
      
     
      frame.getContentPane().add(sub, BorderLayout.SOUTH);
      frame.getContentPane().add(subb, BorderLayout.NORTH);

      
      deckA.fill();
      deckA.shuffle();
      
      // Creates the other four piles
      
      
      // Loads the card images
      
      File imageDirectory = new File("src/main/Resources/Cards");
      CardImages images = new CardImages(imageDirectory);
      
      final DeckComponent dcA = new DeckComponent(deckA, images, cardType);
      final DeckComponent dcB = new DeckComponent(deckB, images, DeckComponent.FAN_VERTICAL, cardType);
      final DeckComponent dcC = new DeckComponent(deckC, images, DeckComponent.FAN_VERTICAL, cardType);
      final DeckComponent dcD = new DeckComponent(deckD, images, DeckComponent.FAN_VERTICAL, cardType);
      final DeckComponent dcE = new DeckComponent(deckE, images, DeckComponent.FAN_VERTICAL, cardType);
      
      dcA.setDeckListener(new DeckListener() // distribute cards to pile b~e when pile a is been clicked, when pile a is empty, retrieve all cards from pile b~e. 
      {
         
         public void handleClick(DeckComponent deckComponent)
         {
            // When clicked, deal a card to deck B, C, D, and E.
            
            saveForU(1);
            
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
                     Card card1 = dcB.removeTopCard();
                     card1.flip();
                     dcA.addCard(card1);
                     
                  }
                  // empties out the pile back into the stock
                  for (int i=deckC.size(); i>0; i--){
                     Card card2 = dcC.removeTopCard();
                     card2.flip();
                     dcA.addCard(card2);
                  }
                  // empties out the pile back into the stock
                  for (int i=deckD.size(); i>0; i--){
                     Card card3 = dcD.removeTopCard();
                     card3.flip();
                     dcA.addCard(card3);
                  }
                  // empties out the pile back into the stock
                  for (int i=deckE.size(); i>0; i--){
                     Card card4 = dcE.removeTopCard();
                     card4.flip();
                     dcA.addCard(card4);
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
            
            if (card1 != null && card2 != null && card3 != null && card4 != null) // distribute as much card as possible (as long as the cards are not null.)
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
               
               if (card1.getRank() == card2.getRank() && card2.getRank() == card3.getRank() && card3.getRank() == card4.getRank()) // remove the four cards that are of the same rank
               {
                  char rk = card1.getRank().getAbbrev();
                 dcB.removeTopCard();
                 dcC.removeTopCard();
                 dcD.removeTopCard();
                 dcE.removeTopCard();
                 score += 100;
                 SC.setText("Score: " + score);
                 JOptionPane.showMessageDialog(null, "All cards had the same rank " + rk +" and were automatically removed.");
                 saveForU(0);
               }
                             
            }
         }
         
         // Dragging the card
         
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            // Only allow drops of the same rank as the top card
            return false;
         }
         
         public void completeDrop(DeckComponent deckComponent, Card card)
         {
         }
      });
      
      dcB.setDeckListener(new DeckListener() // allow drop if the dropped card has a same rank with the first card in the pile
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
            return card.getRank().equals(deckComponent.getTopCard().getRank());
         }
         public void completeDrop(DeckComponent deckComponent, Card card)
         {
            saveForU(2);
         }
      });
      
      dcC.setDeckListener(new DeckListener() // allow drop if the dropped card has a same rank with the first card in the pile
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
            return card.getRank().equals(deckComponent.getTopCard().getRank());
         }
         public void completeDrop(DeckComponent deckComponent, Card card)
         {
            saveForU(3);
         }
      });
      
      dcD.setDeckListener(new DeckListener() // allow drop if the dropped card has a same rank with the first card in the pile
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
            return card.getRank().equals(deckComponent.getTopCard().getRank());
         }
         public void completeDrop(DeckComponent deckComponent, Card card)
         {
            saveForU(4);
         }
      });
      
      dcE.setDeckListener(new DeckListener() // allow drop if the dropped card has a same rank with the first card in the pile
      {
         public void handleClick(DeckComponent deckComponent)
         {
            //saveForU();
         }
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            // Only allow drops of the same rank as the top card
            if (deckComponent.getTopCard() == null)
            {
               return false;
            }
            return card.getRank().equals(deckComponent.getTopCard().getRank());
         }
         public void completeDrop(DeckComponent deckComponent, Card card)
         {
            saveForU(5);
         }
      });
      
      DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a z");//format
      Date date = new Date();//starting time
      String formattedDateTime = dateFormat.format(date); //starting time in correct format
      timerLabel.setText("Time started: " + formattedDateTime);// display starting time
      if(score == 2800){
        Date date2 = new Date();//if win record that time
        //String formattedDateTime2 = dateFormat.format(date2);//convert to correct format
        String finished = dateFormat.format(date2);
        timerLabel.setText("Time finished: " + finished);
      }
      
      
      
      
      undo.addActionListener(new ActionListener() { // if the current state of pile A~E is different that tempDeckA~E, and not all tempDeckA~E are empty, store the current pile A~E into TDA~E(backup data for redo), empty pile A~E and replace them with tempDeckA~E.
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
      
      redo.addActionListener(new ActionListener() { // if the current state of pile A~E is different that TDA~E, and not all TDA~E are empty, empty pile A~E and replace them with TDA~E.
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

package timedpatience.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.Container;
import java.awt.Insets;
import java.awt.Dimension;

import javax.swing.JButton;

import timedpatience.ui.BoardDriver;
import timedpatience.ui.Baroness;

public class MenuDriver extends JPanel implements ActionListener
{
   /**
    * Initializes a window with 7 buttons that allows the user to select
    * between 2 game drivers, view the rules of the games
    * and choose between four different card designs.
    * 
    * @author Owensby
    * @author Bolun
    * @author Uribe
    */
   
   private static final long serialVersionUID = 1L;
   
   static String defaultString = "Use Default Card Deck";
   static String egyptianString = "Use Egyptian Card Deck";
   static String cardMageString = "Use Card Mage Deck";
   static String pySolString = "Use PySol Deck";
   
   //Stores the information about the card type the user selects
   int getType = 0;
   
   
   public MenuDriver(Container pane) {
      
      super(new BorderLayout());
      
      //Creates the push-down buttons
      JButton b1 = new JButton("Play Perpetual Motion ->");
      JButton b2 = new JButton("Play Baroness ->");
      JButton b3 = new JButton("View Rules.");
      b1.addActionListener(this);
      b2.addActionListener(this);
      b3.addActionListener(this);

      Insets insets = pane.getInsets();
      
      //Automatically resizes the window depending on the options

      Dimension size = b1.getPreferredSize();
      b1.setBounds(6 + insets.left, 70 + insets.top,
                  size.width, size.height);
      size = b2.getPreferredSize();
      b2.setBounds(6 + insets.left, 110 + insets.top,
                 size.width, size.height);
      size = b3.getPreferredSize();
      b3.setBounds(6 + insets.left, 150 + insets.top,
                   size.width, size.height);

      //Creates the radio buttons.
      JRadioButton defaultButton = new JRadioButton(defaultString);
      defaultButton.setMnemonic(KeyEvent.VK_B);
      defaultButton.setActionCommand(defaultString);
      defaultButton.setSelected(true);

      JRadioButton egyptianButton = new JRadioButton(egyptianString);
      egyptianButton.setMnemonic(KeyEvent.VK_C);
      egyptianButton.setActionCommand(egyptianString);
      
      JRadioButton cardMageButton = new JRadioButton(cardMageString);
      cardMageButton.setMnemonic(KeyEvent.VK_B);
      cardMageButton.setActionCommand(cardMageString);

      JRadioButton pySolButton = new JRadioButton(pySolString);
      pySolButton.setMnemonic(KeyEvent.VK_C);
      pySolButton.setActionCommand(pySolString);

      //Group the radio buttons.
      ButtonGroup group = new ButtonGroup();
      group.add(defaultButton);
      group.add(egyptianButton);
      group.add(cardMageButton);
      group.add(pySolButton);
      
      //Register a listener for the radio buttons.
      defaultButton.addActionListener(this);
      egyptianButton.addActionListener(this);
      cardMageButton.addActionListener(this);
      pySolButton.addActionListener(this);

      //Put the radio buttons in a column in a panel.
      JPanel radioPanel = new JPanel(new GridLayout(1, 2));
      //push-down buttons
      radioPanel.add(b1);
      radioPanel.add(b2);
      radioPanel.add(b3);
      //radio buttons
      radioPanel.add(defaultButton);
      radioPanel.add(egyptianButton);
      radioPanel.add(cardMageButton);
      radioPanel.add(pySolButton);

      // add it to the panel
      add(radioPanel, BorderLayout.LINE_START);
      setBorder(BorderFactory.createEmptyBorder(20,20,20,20));  
  }
   
   

// Actions taken upon the user's click
   public void actionPerformed(ActionEvent e) {
      
      String action = e.getActionCommand();
      
      //For the radio buttons
      if (action.equals(egyptianString)){
         System.out.println("The card type has been changed to Egyptian.");
         JOptionPane.showMessageDialog(null, "Mamluk Egyptian cards by V.H. Smith. Distributed as freeware. \n"
                  + "\n"
                  + "Diamonds  = Coins \n"
                  + "Clubs          = Polo Sticks \n"
                  + "Hearts        = Cups \n"
                  + "Spades      = Scimitar (Curved sword) \n");
         getType = 1;
      }
      
      else if (action.equals(pySolString)){
         System.out.println("The card type has been changed to PySol.");
         JOptionPane.showMessageDialog(null, "PySol cards: Original Deck © 1998 Niccolo Rigacci modified by © 1999 T. Kirk <grania@inetarena.com>. Released as free software.");
         getType = 2;
      }
      
      else if (action.equals(cardMageString)){
         System.out.println("The card type has been changed to Card Mage.");
         JOptionPane.showMessageDialog(null, "CardMage cards: Original deck, © Oxymoron modified by © Katzmiff 2002 \n"
                  + "Released under GLP license.");
         getType = 3;
      }
      
      else if (action.equals(defaultString)){
         System.out.println("The card type has been changed back to default.");
         getType = 0;
      }
      
      
      //for the push down buttons
      if (action.equals("Play Perpetual Motion ->")) {
         System.out.println("Launching Perpetual Motion...");
         BoardDriver.main(getType);
      }
      else if (action.equals("Play Baroness ->")) {
         System.out.println("Launching Baroness...");
         Baroness.main(getType);
      }
      
      //displays the rules
      else if (action.equals("View Rules.")) {
         System.out.println("Showing Rules.");
         JOptionPane.showMessageDialog(null, "Perpetual Motion \n"
                  + "\n This solitaire game consists of four piles. "
                  + "\n Four cards are dealt at a time from the stock. "
                  + "\n If three cards have the same rank, the duplicates are moved to the leftmost pile with an equal card. "
                  + "\n Only the top card of each pile is in play. "
                  + "\n If all four of the cards have the same rank, they are immediately discarded."
                  + "\n After the stock runs out, the piles are put back into the stock from rightmost pile without disturbing the order of the cards in each pile. "
                  + "\n The game is won when all the cards are discarded in fours. \n "
                  + "\n Baroness \n"
                  + "\n This solitaire game consists of five piles."
                  + "\n Four cards are dealt at a time from the stock. "
                  + "\n Only the top card of each pile is in play. "
                  + "\n The goal is to find pairs that total 13. Once the cards are paired up, they are discarded. "
                  + "\n The game is won when the entire stock is used up, and all the cards in play are used up. "
                  + "\n Ace has a value of 1, jacks have a value of 11, queens have a value of 12, and kings have a value of 13.");
      }
      
      
  }
   
   
   private static void createAndShowGUI() {
      //Create and set up the window.
      JFrame frame = new JFrame("Main Menu");
      frame.setSize(600, 800);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


      //Create and set up the content pane.
      JComponent newContentPane = new MenuDriver(frame.getContentPane());
      newContentPane.setOpaque(true); //content panes must be opaque
      frame.setContentPane(newContentPane);

      //Display the window.
      frame.pack();
      frame.setVisible(true);
      
      //Set up the content pane.
      //addComponentsToPane(frame.getContentPane());
  }

  public static void main(String[] args) {
      //Schedule a job for the event-dispatching thread:
      //creating and showing this application's GUI.
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
          public void run() {
              createAndShowGUI();
          }
      });
  }
  }
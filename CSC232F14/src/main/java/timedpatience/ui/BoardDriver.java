package timedpatience.ui;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import timedpatience.model.Card;
import timedpatience.model.Deck;

public class BoardDriver
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Perpetual Motion");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setSize(400, 300);
      
      Deck deckA = new Deck();
      deckA.fill();
      deckA.getTop().flip();
      
      Deck deckB = new Deck();
      deckB.add(deckA.deal());
      
      Deck deckC = new Deck();
      deckC.add(deckA.deal());
      
      Deck deckD = new Deck();
      deckD.add(deckA.deal());
      
      Deck deckE = new Deck();
      deckE.add(deckA.deal());
      
      deckA.getTop().flip();
      
      File imageDirectory = new File("src/main/resources/cards");
      CardImages images = new CardImages(imageDirectory);
      
      final DeckComponent dcA = new DeckComponent(deckA, images);
      final DeckComponent dcB = new DeckComponent(deckB, images, DeckComponent.FAN_VERTICAL);
      final DeckComponent dcC = new DeckComponent(deckC, images, DeckComponent.FAN_HORIZONTAL);
      final DeckComponent dcD = new DeckComponent(deckC, images, DeckComponent.FAN_HORIZONTAL);
      final DeckComponent dcE = new DeckComponent(deckC, images, DeckComponent.FAN_HORIZONTAL);
      
      dcA.setDeckListener(new DeckListener()
      {
         public void handleClick(DeckComponent deckComponent)
         {
            // When clicked, deal a card to deck B, C, D, and E.
            Card card = deckComponent.removeTopCard();
            
            Card newTop = deckComponent.getTopCard();
            if (newTop != null && !newTop.isFaceUp()) {
               deckComponent.flipTopCard();
            }
            
            if (card != null)
            {
               dcB.addCard(card);
               dcC.addCard(card);
               dcD.addCard(card);
               dcE.addCard(card);
            }
         }
         
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            // Only allow drops of the same suit as the top card
            return card.getSuit().equals(deckComponent.getTopCard().getSuit());
         }
      });
      
      dcA.setDraggable(true);
      dcB.setDraggable(true);
      dcC.setDraggable(true);
      dcD.setDraggable(true);
      dcE.setDraggable(true);
            
      JPanel panel = new JPanel();
      panel.add(dcA);
      panel.add(dcB);
      panel.add(dcC);
      panel.add(dcD);
      panel.add(dcE);
      
      frame.add(panel);
      frame.setVisible(true);
   }
}

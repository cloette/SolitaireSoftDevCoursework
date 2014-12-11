package timedpatience.ui;

import java.awt.Image;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import timedpatience.model.Card;


/**
 * Manage a set of playing card images loaded from a directory of image files.
 * 
 * @author bhoward
 */

public class CardImages
{
   /**
    * Load a set of card images from the given directory. Assumes that the
    * directory contains a series of image files, identified by having a suffix
    * such as ".png". The main part of each file name should be either a card
    * abbreviation, such as "AC", "2C", ..., or one of the special names "blank"
    * or "back".
    * 
    * @param imageDirectory
    */
   public CardImages(File imageDirectory)
   {
      try
      {
         File[] files = imageDirectory.listFiles(new FileFilter()
         {
            String[] suffixes = ImageIO.getReaderFileSuffixes();

            public boolean accept(File pathname)
            {
               for (String suffix : suffixes)
               {
                  if (pathname.getName().endsWith(suffix))
                  {
                     return true;
                  }
               }
               return false;
            }
         });

         for (File file : files)
         {
            Image image = ImageIO.read(file);
            int suffixIndex = file.getName().indexOf('.');
            String name = file.getName().substring(0, suffixIndex);
            images.put(name, image);
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
         System.exit(1); // harsh...
      }
   }

   /**
    * Retrieve the appropriate image for the given playing card. Uses the
    * {@link Card#getAbbrev()} method to select a face-up card. If the specified
    * card is null, returns a blank card image.
    * 
    * @param card
    *           the desired playing card, or null
    * @return the appropriate Image
    */
   public Image getImage(Card card, int type)
   {
      cardType = type;
      
      if (card == null)
      {
         return images.get("blank");
      }
      else if (card.isFaceUp())
      {
         if (cardType == 1){
            return images.get("e" + card.getAbbrev()).getScaledInstance( 72, 96, Image.SCALE_DEFAULT);
         }
         else if (cardType == 2){
            return images.get("p" + card.getAbbrev()).getScaledInstance( 72, 96, Image.SCALE_DEFAULT);
         } 
         else if (cardType == 3){
            return images.get("c" + card.getAbbrev()).getScaledInstance( 72, 96, Image.SCALE_DEFAULT);
         }
         else {
            return images.get(card.getAbbrev());
         }
      }
      else
      {
         if (cardType == 1){
            return (images.get("eback").getScaledInstance( 72, 96, Image.SCALE_DEFAULT));
         }
         else if (cardType == 2){
            return (images.get("pback").getScaledInstance( 72, 96, Image.SCALE_DEFAULT));
         } 
         else if (cardType == 3){
            return (images.get("cback").getScaledInstance( 72, 96, Image.SCALE_DEFAULT));
         }
         else {
            return images.get("back");
         }
         
      }
   }

   private Map<String, Image> images = new HashMap<String, Image>();
   private int cardType;
}

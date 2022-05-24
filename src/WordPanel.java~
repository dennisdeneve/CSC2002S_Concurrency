import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.Timer;
import javax.swing.SwingUtilities;

import java.util.*;
import java.lang.*;

/** Edited Program intended to set up the GUI
* @author Dennis Hammerschlag
* @version 1.1
* @since September 2021
*/

public class WordPanel extends JPanel implements Runnable {
		public static volatile boolean done;
		private WordRecord[] words;
		private int noWords;
		private int maxY;
      
      public Timer animationTimer;
		
       /** Method that draws in the words at specific coords
     * @param Graphics g
     * @return nothing
     */
     
		public void paintComponent(Graphics g) {
		    int width = getWidth();
		    int height = getHeight();
		    g.clearRect(0,0,width,height);
		    g.setColor(Color.red);
		    g.fillRect(0,WordApp.x,width,height);

		    g.setColor(Color.black);
		    g.setFont(new Font("Helvetica", Font.PLAIN, 26));

		    for (int i=0;i<noWords;i++){	    	
		    	//g.drawString(words[i].getWord(),words[i].getX(),words[i].getY());	
		    	g.drawString(words[i].getWord(),words[i].getX(),words[i].getY()+20);  //y-offset for skeleton so that you can see the words	
		    }   
		   
		  }
		
       /** Method that uses seqentuial coding to solve median filter problem
     * @param WordRecord[] words
     * @param int maxY
     * @return sets the words equatl to that in the word array
     */
     
		WordPanel(WordRecord[] words, int maxY) {
         this.words=words; //will this work?
			noWords = words.length;
			done=false;
			this.maxY=maxY;		
		}
		
       /** Method run that needs overridden, animation needs to be done here
     * @param none
     * @return nothing
     */
     
		public void run() {
         
         //timer is an instance thatll create a thread that'll run the actionlistener after every 500ms
         animationTimer = new Timer(500, new ActionListener()
            {
            public void actionPerformed(ActionEvent e)
               {
         for (int i =0; i<words.length;i++)
         
            {
            
            words[i].drop(words[i].getSpeed());
            
            //if the word has fallen off the bottom of screen
            if(words[i].dropped())
               {
               WordApp.score.missedWord();
               words[i].resetWord(); 
               }
            }
            repaint();
          }
       });
       animationTimer.start();
	}
}



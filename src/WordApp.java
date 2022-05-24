import javax.swing.*;
import javax.swing.SwingUtilities;

//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Scanner;
import java.util.concurrent.*;
//model is separate from the view.

/** Edited Program intended to contain components of the GUI and run the program
* @author Dennis Hammerschlag
* @version 1.1
* @since September 2021
*/

public abstract class WordApp extends JFrame implements ActionListener{
//shared variables
	static int noWords=4;
	static int totalWords;

   static int frameX=1000;
	static int frameY=600;
	static int yLimit=480;

	static WordDictionary dict = new WordDictionary(); //use default dictionary, to read from file eventually

	static public WordRecord[] words;
	static volatile boolean done;  //must be volatile
	static Score score = new Score();

	static WordPanel w;
	
   //starting point in window
   static int x =425;
   
   /** Method that uses seqentuial coding to solve median filter problem
     * @param int frameX
     * @param int frameY
     * @param int yLimit
     * @return nothing
     */
     
	public static void setupGUI(int frameX,int frameY,int yLimit) {
		// Frame init and dimensions
    	JFrame frame = new JFrame("WordGame"); 
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameX, frameY);
      JPanel g = new JPanel();
      g.setLayout(new BoxLayout(g, BoxLayout.PAGE_AXIS)); 
      g.setSize(frameX,frameY);
    	
		w = new WordPanel(words,yLimit);
		w.setSize(frameX,yLimit+100);
	   g.add(w); 
	    
      JPanel txt = new JPanel();
      txt.setLayout(new BoxLayout(txt, BoxLayout.LINE_AXIS)); 
      JLabel caught =new JLabel("Caught: " + score.getCaught() + "    ");
      JLabel missed =new JLabel("Missed:" + score.getMissed()+ "    ");
      JLabel scr =new JLabel("Score:" + score.getScore()+ "    ");    
      txt.add(caught);
	   txt.add(missed);
	   txt.add(scr);
  
	   final JTextField textEntry = new JTextField("",20);
	   textEntry.addActionListener(new ActionListener()
	   {
      //every instance of actionPerformed event called upon creates a new thread
      //every event handler has its own thread
	   public void actionPerformed(ActionEvent evt) {
	         String text = textEntry.getText();
             //check if word is a match (match function must be used)
             //then increment score, missed and caught as needed
	         textEntry.setText("");
	         textEntry.requestFocus();
            for (int i=0;i<words.length;i++){
            
            missed.setText("Missed:" + score.getMissed()+ "    ");
            
            //if user gets a falling word correct
            if(words[i].matchWord(text))
               {
               //to check if the game is over -> caught == total words falling
                if(score.getCaught()==totalWords-1)
                  {
                  score.caughtWord(text.length());
                  caught.setText("Caught: " + score.getCaught() + "    ");
                  scr.setText("Score:" + score.getScore()+ "    "); 
               
                  w.animationTimer.stop();
                  
                  popup.infoBox("The game is finished - Well Played :) ", "Game Over");
                  
                  score.resetScore();
                  for(int x =0; x<words.length;x++)
                     {
                     words[x].resetPos();
                     }
                  caught.setText("Caught: " + score.getCaught() + "    ");
                  missed.setText("Missed:" + score.getMissed()+ "    ");
                  scr.setText("Score:" + score.getScore()+ "    ");   
            
                  w.repaint();
                  break;
                  }
               //otherwise if the game is still going, increment relative variables
               score.caughtWord(text.length());
               caught.setText("Caught: " + score.getCaught() + "    ");
               scr.setText("Score:" + score.getScore()+ "    "); 
                    
            }
            
            //checking if the word fell off the screen
            /*
            if(words[i].dropped())
                  {
                  //score.missedWord();
                  missed.setText("Missed:" + score.getMissed()+ "    ");
                  }
                 */
           }
         }
         
	   });
      
	   txt.add(textEntry);
	   txt.setMaximumSize( txt.getPreferredSize() );
	   g.add(txt);
	    
	   JPanel b = new JPanel();
      b.setLayout(new BoxLayout(b, BoxLayout.LINE_AXIS)); 
	   JButton startB = new JButton("Start");;
		
		// add the listener to the jbutton to handle the "pressed" event
		startB.addActionListener(new ActionListener()
		{
         //what happens when start button is pressed
		   public void actionPerformed(ActionEvent e)
		   {
		      //[snip]  
            popup.infoBox("The game is about to start - Goodluck :) ", "Starting the game");
            textEntry.requestFocus();  //return focus to the text entry field
            //call run method from wordpanel class to start animation 
            SwingUtilities.invokeLater(w);
		      
   }
		});
      
      JButton pauseB = new JButton("Pause");
		
      // add the listener to the jbutton to handle the "pressed" event
		pauseB.addActionListener(new ActionListener()
		{
         //what happens when quit button is pressed
		   public void actionPerformed(ActionEvent e)
		   {
		      //[snip]
            //this just closes the game window, quitting the game
            w.animationTimer.stop();
            popup.infoBox("The game is about to be resumed when the button below is clicked :) ", "Resume the game");
            w.animationTimer.start();
		   }
		});


		JButton endB = new JButton("End");;
			   
				// add the listener to the jbutton to handle the "pressed" event
		endB.addActionListener(new ActionListener()
		{
         //what happens when end button is pressed
		   public void actionPerformed(ActionEvent e)
		   {
		      //[snip]
            //stop current game and clear screen
            
            w.animationTimer.stop();
            popup.infoBox("The game is about to restart - Well played! :) ", "Restarting the game");

            for(int i =0; i<words.length;i++)
               {
               words[i].resetPos();
               }
            score.resetScore();
            caught.setText("Caught: " + score.getCaught() + "    ");
            missed.setText("Missed:" + score.getMissed()+ "    ");
            scr.setText("Score:" + score.getScore()+ "    ");   
            
            w.repaint();
		   }
		});
      
      JButton quitB = new JButton("Quit");
		
      // add the listener to the jbutton to handle the "pressed" event
		quitB.addActionListener(new ActionListener()
		{
         //what happens when quit button is pressed
		   public void actionPerformed(ActionEvent e)
		   {
		      //[snip]
            //this just closes the game window, quitting the game
            popup.infoBox("The game is about to quit and exit - Good game! :) ", "Quitting the game");
            System.exit(0);
		   }
		});

		b.add(startB);
      b.add(pauseB);
		b.add(endB);
		b.add(quitB);
		g.add(b);
    	
      frame.setLocationRelativeTo(null);  // Center window on screen.
      frame.add(g); //add contents to window
      frame.setContentPane(g);     
       	//frame.pack();  // don't do this - packs it into small space
      frame.setVisible(true);
	}

    /** Method that gets the specified dictionary and populates a word array to be used in game
     * @param String file name
     * @return String that dictionary
     */
     
   public static String[] getDictFromFile(String filename) {
		String [] dictStr = null;
		try {
			Scanner dictReader = new Scanner(new FileInputStream(filename));
			int dictLength = dictReader.nextInt();
			//System.out.println("read '" + dictLength+"'");

			dictStr=new String[dictLength];
			for (int i=0;i<dictLength;i++) {
				dictStr[i]=new String(dictReader.next());
				//System.out.println(i+ " read '" + dictStr[i]+"'"); //for checking
			}
			dictReader.close();
		} catch (IOException e) {
	        System.err.println("Problem reading file " + filename + " default dictionary will be used");
	    }
		return dictStr;
	}
   
    /** Main Method that the game is run from
     * @param String[] args
     * @return nothing
     */
     
	public static void main(String[] args) {
    	
		//deal with command line arguments
		totalWords=Integer.parseInt(args[0]);  //total words to fall
      noWords=Integer.parseInt(args[1]); // total words falling at any point
      
		//to ensure that total words is more than the number of words on the screen
      assert(totalWords>=noWords); // this could be done more neatly
		
      String[] tmpDict=getDictFromFile(args[2]); //file of words
		if (tmpDict!=null)
			dict= new WordDictionary(tmpDict);
		
		WordRecord.dict=dict; //set the class dictionary for the words.
		
		words = new WordRecord[noWords];  //shared array of current words
		
      //make sure the game ends after args[0] number of words have fallen
		//[snip]
		
		setupGUI(frameX, frameY, yLimit);  
    	//Start WordPanel thread - for redrawing animation

		int x_inc=(int)frameX/noWords;
	  	//initialize shared array of current words

		for (int i=0;i<noWords;i++) {
			words[i]=new WordRecord(dict.getNewWord(),i*x_inc,yLimit);
		}
	}
}
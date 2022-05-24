/** Program from skeleton code for A2 intended to create wordrecord objects that have the coords of the word on screen
* @author CSC department
* @version 1.0
* @since September 2021
*/


public class WordRecord extends Thread{
	private String text;
	private  int x;
	private int y;
	private int maxY;
	public static boolean dropped;
	
	private int fallingSpeed;
	private static int maxWait=40;
	private static int minWait=20;
     
	public static WordDictionary dict;
	
	WordRecord() {
		text="";
		x=0;
		y=0;	
		maxY=300;
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
	}
	
	WordRecord(String text) {
		this();
		this.text=text;
	}
	
	WordRecord(String text,int x, int maxY) {
		this(text);
		this.x=x;
		this.maxY=maxY;
	}
	
// all getters and setters must be synchronized
	
    /** Method that sets y coord of a word
     * @param int y
     * @return nothing
     */
     
   public synchronized  void setY(int y) {
		//dropped below screen
      if (y>maxY) {
			y=maxY;
			dropped=true;
		}
		this.y=y;
	}
	
   /** Method that sets x coord of a word
     * @param int x
     * @return nothing
     */
     
	public synchronized  void setX(int x) {
		this.x=x;
	}
	
   /** Method that sets the word
     * @param String word 
     * @return nothing
     */
     
	public synchronized  void setWord(String text) {
		this.text=text;
	}
   
   /** Method that gets the word
     * @param String word 
     * @return that string
     */
     
	public synchronized  String getWord() {
		return text;
	}
   
   /** Method that gets the x coord of word
     * @param int getX  
     * @return x coord
     */
	
	public synchronized  int getX() {
		return x;
	}	
	
   /** Method that gets the y coord of word
     * @param int getY 
     * @return y coord
     */
     
	public synchronized  int getY() {
		return y;
	}
	
   /** Method that gets the speed of word
     * @param int getY 
     * @return nothing
     */
     
	public synchronized  int getSpeed() {
		return fallingSpeed;
	}

   /** Method that sets the position of the word
     * @param int x 
     * @param int y
     * @return nothing
     */
     
	public synchronized void setPos(int x, int y) {
		setY(y);
		setX(x);
	}
   
    /** Method that resets the position of the word to the top
     * @param none
     * @return nothing
     */
     
	public synchronized void resetPos() {
		setY(0);
	}
   
   /** Method that resets the position of the word 
     * @param none
     * @return nothing
     */
     
	public synchronized void resetWord() {
		resetPos();
		text=dict.getNewWord();
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
		//System.out.println(getWord() + " falling speed = " + getSpeed());

	}
	
   /** Method that checks if the word passed in as an argument is a match
     * @param none
     * @return true or false
     */
     
	public synchronized boolean matchWord(String typedText) {
		//System.out.println("Matching against: "+text);
		if (typedText.equals(this.text)) {
			resetWord();
			return true;
		}
		else
			return false;
	}
	
   /** Method that drops the word
     * @param int inc
     * @return nothing
     */
     
	public synchronized  void drop(int inc) {
		setY(y+inc);
	}
	
   /** Method that checks if word is dropped 
     * @param none
     * @return dropped boolean variable
     */
     
	public synchronized  boolean dropped() {
		return dropped;
	}

}

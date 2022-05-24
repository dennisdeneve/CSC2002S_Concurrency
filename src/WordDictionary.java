/** Program from skeleton code for A2 intended to an array and populate it with words from a specicfied dictionary
* @author CSC department
* @version 1.0
* @since September 2021
*/

public class WordDictionary {
	int size;
	static String [] theDict= {"litchi","banana","apple","mango","pear","orange","strawberry",
		"cherry","lemon","apricot","peach","guava","grape","kiwi","quince","plum","prune",
		"cranberry","blueberry","rhubarb","fruit","grapefruit","kumquat","tomato","berry",
		"boysenberry","loquat","avocado"}; //default dictionary
	
	WordDictionary(String [] tmp) {
		size = tmp.length;
      
		theDict = new String[size];
		for (int i=0;i<size;i++) {
			theDict[i] = tmp[i];
		}
		
	}
	
	WordDictionary() {
		size=theDict.length;
		
	}
	
   /** Method that gets a new word from the string array of words from specified dictionary
     * @param none
     * @return new string word
     */
     
	public synchronized String getNewWord() {
		int wdPos= (int)(Math.random() * size);
		return theDict[wdPos];
	}
	
}

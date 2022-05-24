import javax.swing.JOptionPane;

/** Program that creates popups on the game in duration of play
* @author Dennis Hammerschlag
* @version 1.0
* @since September 2021
*/

public class popup
{
    /** Method that creates a popup message
     * @param String message
     * @param String title
     * @return none
     */
     
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
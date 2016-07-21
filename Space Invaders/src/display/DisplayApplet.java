
package display;

import java.awt.BorderLayout;
import javax.swing.JApplet;

/**
 *
 * @author Java Tutorial code for inplementing Project5Driver
 */
public class DisplayApplet extends JApplet{
    private static final long serialVersionID = 1L; 
    private Project5Driver display = new Project5Driver();
    
    public void init(){
        setLayout(new BorderLayout());
        add(display);
    }
    public void start(){
        display.start();
    }
    public void stop(){
        display.stop();
    }
}

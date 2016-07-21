/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author TinieT
 */
public class Boat extends ObjectShape{
    private final int SHAPEAPOINTS = 3; 
    private int[] shapeAxPoints = new int[SHAPEAPOINTS];
    private int[] shapeAyPoints = new int[SHAPEAPOINTS];
    private Rectangle2D.Double shapeB;
    private final int SHAPECPOINTS = 3;
    private int[] shapeCxPoints = new int[SHAPECPOINTS];
    private int[] shapeCyPoints = new int[SHAPECPOINTS];
    private final int SHAPEDPOINTS = 4;
    private int[] shapeDxPoints = new int[SHAPEDPOINTS];
    private int[] shapeDyPoints = new int[SHAPEDPOINTS];
    /**
     * instantiates a object of type Boat 
     * @param x sets the x position of this Boat object
     * @param y sets the y position of this Boat object
     * @param rWidth sets the width of this Boat object
     * @param rHeight sets the height of this Boat object
     */
    public Boat(int x, int y, int rWidth, int rHeight, int direction) {
        super(x, y, rWidth, rHeight, direction);
        instantiateArrays();
        
    }
    private void instantiateArrays(){
        shapeB = new Rectangle2D.Double(x+(rWidth/10)*4,y + (rHeight/10), rWidth/10, rHeight/2);
        int temp1[] = {x+(rWidth/10*4),x+(rWidth/10*2),x+(rWidth/10*4)};
        shapeAxPoints = temp1;
        int temp2[] = {y+(rHeight/10*2),y+(rHeight/10*5),y+(rHeight/10*5)};
        shapeAyPoints = temp2;
        int temp3[] = {x+(rWidth/10*5),x+(rWidth/10*8),x+(rWidth/10*5)};
        shapeCxPoints = temp3;
        int temp4[] = {y+(rHeight/10),y+(rHeight/10*5),y+(rHeight/10*5)};
        shapeCyPoints = temp4;
        int temp5[] = {x , x+(rWidth/10*2), x+(rWidth/10*7), x+(rWidth/10*9)};
        shapeDxPoints = temp5;
        int temp6[] = {y+(rHeight/10*6), y+(rHeight/10*8),y+(rHeight/10*8), y+(rHeight/10*6)};
        shapeDyPoints = temp6;
        
        
    }
    /**
     * used to render this object to a given display
     * @param g
     */
    public void draw(Graphics2D g) {
        instantiateArrays();
        g.setColor(Color.red);
        g.fill(shapeB);
        g.drawPolygon(shapeAxPoints, shapeAyPoints, SHAPEAPOINTS);
        g.drawPolygon(shapeCxPoints, shapeCyPoints, SHAPECPOINTS);
        g.drawPolygon(shapeDxPoints, shapeDyPoints, SHAPEDPOINTS);
        g.setPaint(Color.WHITE);
        g.fillPolygon(shapeAxPoints, shapeAyPoints, SHAPEAPOINTS);
        g.fillPolygon(shapeCxPoints, shapeCyPoints, SHAPECPOINTS);
        g.setPaint(Color.CYAN);
        g.fillPolygon(shapeDxPoints, shapeDyPoints, SHAPEDPOINTS);
    }
    
}

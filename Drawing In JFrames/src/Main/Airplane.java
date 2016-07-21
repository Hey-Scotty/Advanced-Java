package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author TinieT
 */

public class Airplane extends ObjectShape{
    Rectangle2D.Double topTail;
    Rectangle2D.Double body;
    Rectangle2D.Double botTail;
    Rectangle2D.Double topWing;
    Rectangle2D.Double botWing;
    /**
     * instantiates a object of type Airplane 
     * @param x sets the x position of this Airplane object
     * @param y sets the y position of this Airplane object
     * @param rWidth sets the width of this Airplane object
     * @param rHeight sets the height of this Airplane object
     */
    public Airplane(int x, int y, int rWidth, int rHeight, int direction) {
        super(x, y , rWidth, rHeight, direction);
        this.instantiateShape();
    }
    private void instantiateShape(){
        topTail = new Rectangle2D.Double( x,(y+(rHeight)/5), rWidth/5, rHeight/5);
        body = new Rectangle2D.Double(x,y + (2*(rHeight/5)), rWidth, rHeight/5);
        botTail = new Rectangle2D.Double(x,(y+3*(rHeight)/5), rWidth/5, rHeight/5);
        topWing = new Rectangle2D.Double(x+3*(rWidth/5),y, (rWidth/5), 2 * rHeight / 5);
        botWing = new Rectangle2D.Double(x+3*(rWidth/5),y+3*(rHeight/5), rWidth/5, 2 * rHeight / 5);
    }
    /**
     * used to render this Airplane object inside a given display
     */
    public void draw(Graphics2D g) {
        instantiateShape();
        g.setPaint(Color.red);
        g.fill(body);
        g.setPaint(Color.white);
        g.fill(topTail);
        g.fill(botTail);
        g.fill(topWing);
        g.fill(botWing);
        g.draw(body);
        g.draw(topTail);
        g.draw(botTail);
        g.draw(topWing);
        g.draw(botWing);      
    }
    
}

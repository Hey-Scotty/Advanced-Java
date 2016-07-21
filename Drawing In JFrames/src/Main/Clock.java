/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author TinieT
 */
public class Clock extends ObjectShape{
    private Ellipse2D outerCircle;
    private Line2D hourHand;
    private Line2D minHand;
    /**
     * instantiates a object of type Clock 
     * @param x sets the x position of this Clock object
     * @param y sets the y position of this Clock object
     * @param rWidth sets the width of this Clock object
     * @param rHeight sets the height of this Clock object
     */
    public Clock(int x, int y, int width, int height, int direction) {
        super(x, y, width, height, direction);
        instantiateShape();
    }
    private void instantiateShape(){
        outerCircle = new Ellipse2D.Double(x, y, rWidth, rHeight);
        hourHand = new Line2D.Float(x + rWidth/2, y + rHeight/10, x+rWidth/2, y + rHeight/2);
        minHand = new Line2D.Float(x+rWidth/2, y + rHeight/2, x+rWidth/4, y+rHeight/2);
    }
    /**
     * used to render this object to a given display
     * @param g
     */
    public void draw(Graphics2D g) {
        instantiateShape();
        g.draw(outerCircle);
        g.setPaint(Color.white);
        g.fill(outerCircle);
        g.setPaint(Color.BLACK);
        g.draw(hourHand);
        g.draw(minHand);
        
    }
}

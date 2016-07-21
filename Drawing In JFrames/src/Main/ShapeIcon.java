/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author TinieT
 */
public class ShapeIcon implements Icon{
    
    private int width;
    private int height;
    private MoveableShape shape;
    /**
     * creates a ShapeIcon object and instantiates its instance variables
     * @param shape instantiates the moveableShape instance variable
     * @param width instantiates the width variable
     * @param height instantiates the height variable
     */
    public ShapeIcon(MoveableShape shape, int width, int height){
        this.shape = shape;
        this.width = width;
        this.height = height;
    }
     /**
     * @return returns this ShapeIcon's width variable 
     */
    public int getIconWidth(){
        return width;
    }
    /**
     * @return returns this ShapeIcon's height variable 
     */
    public int getIconHeight(){
        return height;
    }
    /**
     * draws this paint icon to the parent component
     * @param c inputted component
     * @param g used to render this image
     * @param x x position for which this icon will be drawn
     * @param y y position for which this con will be drawn
     */
    public void paintIcon(Component c, Graphics g, int x, int y){
        Graphics2D g2 = (Graphics2D) g;
        shape.draw(g2);
    }
 }

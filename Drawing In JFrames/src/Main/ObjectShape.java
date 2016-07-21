/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/**
A Object shape that can be moved around.
*/
public abstract class ObjectShape implements MoveableShape{
private final String NAME = "ObjectShape";
protected int x;
protected int y;
protected int rWidth;
protected int rHeight;
protected int direction; // if -2 goes left if 2 goes right
/**
* Constructs a car item.
* @param x the left of the bounding rectangle
* @param y the top of the bounding rectangle
* @param rWidth the rWidth of the bounding rectangle
*/
public ObjectShape(int x, int y, int rWidth, int rHeight,int direction){
    this.x = x;
    this.y = y;
    this.rWidth = rWidth;
    this.rHeight = rHeight;
    this.direction = direction;
}
/**
 * moves the shape's x and/or y position by the inputted amount
 * @param dx offset this x by dx
 * @param dy offset this y by dy
 */
public void translate(){
    x += direction;
}
/**
 * wraps image moving right, back to the left once it passes the right edge
 * @param edgeX
 * @param edgeY 
 */
public void wrapImagetoLeft(int edgeX){
    if(this.x > edgeX){
        this.x = 0;
    }
}
/**
 * wraps image moving left, back to the right once it passes the left edge
 * @param edgeX
 * @param edgeY 
 */
public void wrapImagetoRight(int edgeX){
    if(this.x < 0 - rWidth){
        this.x = edgeX;
    }
}
/**
 * for debugging purposes
 * @return returns this NAME variable
 */
public String toString(){
    return this.NAME;
}
/**
 * used to render this object to a given display
 * @param g2 
 */
public abstract void draw(Graphics2D g2);
}

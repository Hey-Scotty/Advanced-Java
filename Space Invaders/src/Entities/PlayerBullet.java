/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author TinieT 
 */
public class PlayerBullet {
    protected BufferedImage bullet; //image of bullet
    protected Rectangle rect; //collison
    protected double xPos, yPos;
    protected int width, height;
    public PlayerBullet(double xPos, double yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = 10;
        this.height = 10;
        this.rect = new Rectangle((int) xPos, (int) yPos, width, height);
        try{
           URL url = this.getClass().getResource("/Sprites/Player.png");
            bullet = ImageIO.read(url); 
        }catch(IOException e){};
    }
    public void draw(Graphics2D g){
        g.drawImage(bullet, (int)xPos, (int)yPos, width, height, null);
    }
    public void update(double delta){
        yPos -= 8;
        rect.y = (int) yPos;
    }
    public double getYPos(){
        return this.yPos;
    }
    public boolean isOnscreen(){
        boolean answer = true;
        if(yPos < 0)
            answer = false;
        return answer;
    }
    public boolean hasCollided(Rectangle rectInput){
        boolean answer = false;
        if(topLeftCheck(rectInput) || topRightCheck(rectInput) || botLeftCheck(rectInput) || botRightCheck(rectInput) /*|| topMidCheck(rectInput)*/)
            answer = true;
        return answer;
    }
    /**
     * help method for the collision method
     * @param rectInput
     * @return 
     */
    private boolean topLeftCheck(Rectangle rectInput){
        boolean answer = false;
        if(this.rect.getX() > rectInput.getX() && this.rect.getX() < (rectInput.getX() + rectInput.getWidth()))
            if(this.rect.getY() > rectInput.getY() && this.rect.getY() < (rectInput.getY() + rectInput.getHeight()))
                answer =true;
        return answer;
    }
    /**
     * help method for the collision method
     * @param rectInput
     * @return 
     */
    private boolean topMidCheck(Rectangle rectInput){
        boolean answer = false;
        if((this.rect.getX() + width) / 2 > rectInput.getX() && (this.rect.getX() + width) / 2 < (rectInput.getX() + rectInput.getWidth()))
            if(this.rect.getY() > rectInput.getY() && this.rect.getY() < (rectInput.getY() + rectInput.getHeight()))
                answer =true;
        return answer;
    }
     /**
     * help method for the collision method
     * @param rectInput
     * @return 
     */
    private boolean topRightCheck(Rectangle rectInput){
        boolean answer = false;
        if(this.rect.getX() + width > rectInput.getX() && this.rect.getX() + width < (rectInput.getX() + rectInput.getWidth()))
            if(this.rect.getY() > rectInput.getY() && this.rect.getY() < (rectInput.getY() + rectInput.getHeight()))
                answer =true;
        return answer;
    }
     /**
     * help method for the collision method
     * @param rectInput
     * @return 
     */
    private boolean botLeftCheck(Rectangle rectInput){
        boolean answer = false;
        if(this.rect.getX() > rectInput.getX() && this.rect.getX() < (rectInput.getX() + rectInput.getWidth()))
            if(this.rect.getY() + height > rectInput.getY() && this.rect.getY() + height < (rectInput.getY() + rectInput.getHeight()))
                answer =true;
        return answer;
    }
     /**
     * help method for the collision method
     * @param rectInput
     * @return 
     */
    private boolean botRightCheck(Rectangle rectInput){
        boolean answer = false;
        if(this.rect.getX() + width  > rectInput.getX() && this.rect.getX() + width  < (rectInput.getX() + rectInput.getWidth()))
            if(this.rect.getY() + height > rectInput.getY() && this.rect.getY() + height < (rectInput.getY() + rectInput.getHeight()))
                answer =true;
        return answer;
    }
}

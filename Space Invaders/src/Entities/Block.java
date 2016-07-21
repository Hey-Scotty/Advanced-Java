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
public class Block {
    private BufferedImage displaySprite;
    private BufferedImage[] botLeftStates;
    private BufferedImage[] topLeftStates;
    private BufferedImage[] topMidStates;
    private BufferedImage[] topRightStates;
    private BufferedImage[] botRightStates;
    private BufferedImage[] botMidStates;
    private boolean active;
    private Rectangle rect;
    private double xPos, yPos;
    private int width, height;
    private int damageCount = 0;
    private int partOfBarrier; //1=botLeft 2=topLeft 3=topMid 4=TopRight 5=botRight 6=botMid
    public Block(double xPos, double yPos, int width, int height, int partOfBarrier){
        this.active = true;
        this.rect = new Rectangle((int)xPos, (int)yPos, width, height);
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.partOfBarrier = partOfBarrier;
        intializeSpecific(partOfBarrier);
        
    }
    public void draw(Graphics2D g){
        if(isActive())
            g.drawImage(displaySprite,(int)xPos,(int)yPos, width, height, null);
    }
    public void update(double delta){
        changeState();
        
    }
    public void incrementDamageCount(){
        this.damageCount++;
        this.deactivate();
    }
    private void deactivate(){
        if(damageCount == 5){
            active = false;
            rect = new Rectangle(0,0,0,0);
        }
    }
    private void changeState(){
        switch (partOfBarrier) {
            case 1: changeBotLeft();
                break;
            case 2: changeTopLeft();
                break;
            case 3: changeTopMid();
                break;
            case 4: changeTopRight();
                break;
            case 5: changeBotRight();
                break;
            case 6: changeBotMid();
                break;    
        }
    }
    private void changeBotLeft(){
        switch (this.damageCount){
            case 1: displaySprite = botLeftStates[1];
                break;
            case 2: displaySprite = botLeftStates[2];
                break;
            case 3: displaySprite = botLeftStates[3];
                break;
            case 4: displaySprite = botLeftStates[4];
                break;
        } 
    }
    private void changeTopLeft(){
        switch (damageCount){
            case 1: displaySprite = topLeftStates[1];
                break;
            case 2: displaySprite = topLeftStates[2];
                break;
            case 3: displaySprite = topLeftStates[3];
                break;
            case 4: displaySprite = topLeftStates[4];
                break;
        } 
    }
    private void changeTopMid(){
        switch (damageCount){
            case 1: displaySprite = topMidStates[1];
                break;
            case 2: displaySprite = topMidStates[2];
                break;
            case 3: displaySprite = topMidStates[3];
                break;
            case 4: displaySprite = topMidStates[4];
                break;
        } 
    }
    private void changeTopRight(){
        switch (damageCount){
            case 1: displaySprite = topRightStates[1];
                break;
            case 2: displaySprite = topRightStates[2];
                break;
            case 3: displaySprite = topRightStates[3];
                break;
            case 4: displaySprite = topRightStates[4];
                break;
        } 
    }
    private void changeBotRight(){
        switch (damageCount){
            case 1: displaySprite = botRightStates[1];
                break;
            case 2: displaySprite = botRightStates[2];
                break;
            case 3: displaySprite = botRightStates[3];
                break;
            case 4: displaySprite = botRightStates[4];
                break;
        } 
    }
    private void changeBotMid(){
        switch (damageCount){
            case 1: displaySprite = botMidStates[1];
                break;
            case 2: displaySprite = botMidStates[2];
                break;
            case 3: displaySprite = botMidStates[3];
                break;
            case 4: displaySprite = botMidStates[4];
                break;
        } 
    }
    
    private void intializeSpecific(int partOfBarrier){
        switch (partOfBarrier) {
            case 1: { intializeBotLeft();
                displaySprite = botLeftStates[0];   
                break;
            }    
            case 2: { intializeTopLeft();
                displaySprite = topLeftStates[0];
                break;
            }    
            case 3: { intializeTopMid();
                displaySprite = topMidStates[0];
                break;
            }    
            case 4: { intializeTopRight();
                displaySprite = topRightStates[0];
                break;
            }    
            case 5: { intializeBotRight();
                displaySprite = botRightStates[0];
                break;
            }    
            case 6: { intializeBotMid();
                displaySprite = botMidStates[0];
                break;
            }    
        }
    }
    private void intializeBotLeft(){
        botLeftStates = new BufferedImage[5];
        try{
            URL url = this.getClass().getResource("/Images/barriers/BotLeft/botLeft1.png");
            botLeftStates[0] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/BotLeft/botLeft2.png");
            botLeftStates[1] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/BotLeft/botLeft3.png");
            botLeftStates[2] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/BotLeft/botLeft4.png");
            botLeftStates[3] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/BotLeft/botLeft5.png");
            botLeftStates[4] = ImageIO.read(url);
        }catch(IOException e){};
    }
    private void intializeTopLeft(){
        topLeftStates = new BufferedImage[5];
        try{
            URL url = this.getClass().getResource("/Images/barriers/TopLeft/topLeft1.png");
            topLeftStates[0] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/TopLeft/topLeft2.png");
            topLeftStates[1] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/TopLeft/topLeft3.png");
            topLeftStates[2] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/TopLeft/topLeft4.png");
            topLeftStates[3] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/TopLeft/topLeft5.png");
            topLeftStates[4] = ImageIO.read(url);
        }catch(IOException e){};
    }   
    private void intializeTopMid(){
        topMidStates = new BufferedImage[5];
        try{
            URL url = this.getClass().getResource("/Images/barriers/TopMid/topMid1.png");
            topMidStates[0] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/TopMid/topMid2.png");
            topMidStates[1] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/TopMid/topMid3.png");
            topMidStates[2] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/TopMid/topMid4.png");
            topMidStates[3] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/TopMid/topMid5.png");
            topMidStates[4] = ImageIO.read(url);
        }catch(IOException e){};
    }    
    private void intializeTopRight(){
        topRightStates = new BufferedImage[5];
        try{
            URL url = this.getClass().getResource("/Images/barriers/TopRight/topRight1.png");
            topRightStates[0] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/TopRight/topRight2.png");
            topRightStates[1] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/TopRight/topRight3.png");
            topRightStates[2] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/TopRight/topRight4.png");
            topRightStates[3] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/TopRight/topRight5.png");
            topRightStates[4] = ImageIO.read(url);
        }catch(IOException e){};
    }    
    private void intializeBotRight(){
        botRightStates = new BufferedImage[5];
        try{
            URL url = this.getClass().getResource("/Images/barriers/BotRight/botRight1.png");
            botRightStates[0] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/BotRight/botRight2.png");
            botRightStates[1] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/BotRight/botRight3.png");
            botRightStates[2] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/BotRight/botRight4.png");
            botRightStates[3] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/BotRight/botRight5.png");
            botRightStates[4] = ImageIO.read(url);
        }catch(IOException e){};
    }    
    private void intializeBotMid(){
        botMidStates = new BufferedImage[5];
        try{
            URL url = this.getClass().getResource("/Images/barriers/BotMid/botMid1.png");
            botMidStates[0] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/BotMid/botMid2.png");
            botMidStates[1] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/BotMid/botMid3.png");
            botMidStates[2] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/BotMid/botMid4.png");
            botMidStates[3] = ImageIO.read(url);
            url = this.getClass().getResource("/Images/barriers/BotMid/botMid5.png");
            botMidStates[4] = ImageIO.read(url);
        }catch(IOException e){};
    }
    public boolean hasCollided(Rectangle rectInput){
        boolean answer = false;
        if(topLeftCheck(rectInput) || topRightCheck(rectInput) || botLeftCheck(rectInput) || botRightCheck(rectInput) || topMidCheck(rectInput)                                                                                                                                                   )
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
        if(this.getRect().getX() > rectInput.getX() && this.getRect().getX() < (rectInput.getX() + rectInput.getWidth()))
            if(this.getRect().getY() > rectInput.getY() && this.getRect().getY() < (rectInput.getY() + rectInput.getHeight()))
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
        if((this.getRect().getX() + width)/2 > rectInput.getX() && (this.getRect().getX() + width)/2 < (rectInput.getX() + rectInput.getWidth()))
            if((this.getRect().getY()+ height)/2 > rectInput.getY() && (this.getRect().getY() + height)/2 < (rectInput.getY() + rectInput.getHeight()))
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
        if(this.getRect().getX() + width > rectInput.getX() && this.getRect().getX() + width < (rectInput.getX() + rectInput.getWidth()))
            if(this.getRect().getY() > rectInput.getY() && this.getRect().getY() < (rectInput.getY() + rectInput.getHeight()))
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
        if(this.getRect().getX() > rectInput.getX() && this.getRect().getX() < (rectInput.getX() + rectInput.getWidth()))
            if(this.getRect().getY() + height > rectInput.getY() && this.getRect().getY() + height < (rectInput.getY() + rectInput.getHeight()))
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
        if(this.getRect().getX() + width  > rectInput.getX() && this.getRect().getX() + width  < (rectInput.getX() + rectInput.getWidth()))
            if(this.getRect().getY() + height > rectInput.getY() && this.getRect().getY() + height < (rectInput.getY() + rectInput.getHeight()))
                answer =true;
        return answer;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the rect
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * @param rect the rect to set
     */
    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
    
}

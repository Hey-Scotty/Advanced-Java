/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Sounds.Sound;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import timer.Timer;

/**
 *
 * @author TinieT
 */
public class MotherShip {
    private BufferedImage eSprite;
    private Rectangle rect;
    private double xPos, yPos;
    private int width, height;
    private boolean isAlive;
    private boolean goingLeft;
    private boolean goingRight;
    private EnemyBullet bullet;
    private double enemySpeed;
    private boolean shoot;
    private final int SCOREVAL = 300;
    private boolean landeded = false;
    private Timer timer = new Timer();
    private Sound wepWep;
    public MotherShip(double xPos, double yPos, int width, int height){
        this.wepWep = new Sound("/Sounds/invaderkilled.wav");
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.rect = new Rectangle((int)xPos, (int)yPos, width, height);
        rect = new Rectangle((int) xPos,(int)yPos, width, height);
        this.isAlive = true;
        goingLeft = false;
        goingRight = true;
        enemySpeed = .3;
        try{
            URL url = this.getClass().getResource("/Sprites/motherShip.png");
            eSprite = ImageIO.read(url);
        }catch(IOException e){};
    }
    /**
    * draws enemy sprite to screen
    * @param g 
    */
    public void update(double delta){
        this.xPos += 1;
        this.rect.x = (int) xPos;
    }
    public boolean isOnscreen(){
        boolean answer = true;
        if(yPos < 0)
          answer = false;
        if(yPos > 760)
          answer = false;
        return answer;
    }
    public void draw(Graphics2D g){
        if(isIsAlive())
            g.drawImage(eSprite,(int)xPos,(int)yPos, width, height, null);
    }
    public boolean isIsAlive() {
        return isAlive;
    }
    /**
    * removes enemy from game
    */
    public void killEnemy(){
        wepWep.play();
        isAlive = false;
        setRect(new Rectangle(0,0,0,0));
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

    /**
     * @return the SCOREVAL
     */
    public int getSCOREVAL() {
        return SCOREVAL;
    }
}

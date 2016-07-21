/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Sounds.Sound;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author TinieT
 */
public class Player implements KeyListener{
    
    private int score;
    private BufferedImage pSprite;
    private BufferedImage holder;
    private BufferedImage exploded;
    private Rectangle rect;
    private double xPos, yPos;
    private int width, height;
    private boolean left = false, right = false;
    private boolean shoot = false;
    private boolean boom;
    private PlayerBullet bullet;
    private int lives = 3;
    private boolean pause = false;
    private Sound pewPew;
    private Sound explode;
    private int counter;
    public Player(double xPos, double yPos, int width, int height){
        counter = 0;
        boom = false;
        pewPew = new Sound("/Sounds/shoot.wav");
        explode = new Sound("/Sounds/explosion.wav");
        this.score = 0;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.rect = new Rectangle((int)xPos, (int)yPos, width, height);
        rect = new Rectangle((int) xPos,(int)yPos, width, height);
        try{
            URL url = this.getClass().getResource("/Sprites/Player.png");
            pSprite = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/PlayerExplosion.png");
            exploded = ImageIO.read(url);
            holder = pSprite;
        }catch(IOException e){};
    }
    /**
     * draws player sprite to screen
     * @param g 
     */
    public void draw(Graphics2D g){
        if(isBoom()){
            g.drawImage(exploded,(int)xPos,(int)yPos, width, height, null);
        }    
        else if(!isBoom())
            g.drawImage(pSprite,(int)xPos,(int)yPos, width, height, null);
        if(getBullet()!=null)
            getBullet().draw(g);
    }
    /**
     * updates player instance variables
     * @param delta 
     */
    public void setToNormal(){
        
        pSprite = holder;
    }
    public void Explode(){
        explode.play();
        
    }
    public void update(double delta){
        if(boom){
            counter++;
            if(counter >= 50){
                counter = 0;
                boom = false;
            }    
        }    
        if(isRight() && xPos < 770){//permits movement while right arrow key is pressed and the sprite is not touching the edge
            xPos += 3;
            rect.x = (int) xPos; //moves collision detection with the image location on screen
        }
        if(isLeft() && xPos > 0){//permits movement while left arrow key is pressed and the sprite is not touching the edge
            xPos -=3;
            rect.x = (int) xPos; //moves collision detection with the image location on screen
        }
        if(isShoot()){
            this.setBullet(new PlayerBullet((int)this.xPos + 14, (int)this.yPos));
            setShoot(false);
        }
        if(getBullet()!= null)
            getBullet().update(delta);
        if(getBullet()!=null && !bullet.isOnscreen())
            setBullet(null);
    }
    
    @Override

    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
   }

    @Override
    /**
     * checks for input
     */
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 39 && !boom)//right keycode is 39
            right = true;
        if(e.getKeyCode() == 37 && !boom)//left keycode is 37
            left = true;
        if(e.getKeyCode() == 38 && bullet == null && !boom){//up arrow
            setShoot(true);
            pewPew.play();
        }    
        if(e.getKeyCode() == 19 && !pause)
            pause = true;
        else if(e.getKeyCode() == 19 && pause)
            pause = false;
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == 39)
            right = false;
        if(e.getKeyCode() == 37)
            left = false;
            
    }
    public PlayerBullet getPlayerBullet(){
        return getBullet();
    }

    /**
     * @return the bullet
     */
    public PlayerBullet getBullet() {
        return bullet;
    }

    /**
     * @param bullet the bullet to set
     */
    public void setBullet(PlayerBullet bullet) {
        this.bullet = bullet;
    }
    public void addToScore(int input){
        this.score += input;
    }
    public int getScore(){
        return this.score;
    }
    public Rectangle getRect(){
        return this.rect;
    }
    public boolean hasCollided(Rectangle rectInput){
        boolean answer = false;
        if(topLeftCheck(rectInput) || topRightCheck(rectInput) || botLeftCheck(rectInput) || botRightCheck(rectInput) || topMidCheck(rectInput))
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
    public boolean getPause(){
        return this.pause;
    }
    public int getLives(){
        return this.lives;
    }
    public void incrementLives(){
        this.lives++;
    }
    public void decrementLives(){
        this.lives--;
    }

    /**
     * @return the left
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * @return the right
     */
    public boolean isRight() {
        return right;
    }

    /**
     * @return the shoot
     */
    public boolean isShoot() {
        return shoot;
    }

    /**
     * @param shoot the shoot to set
     */
    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }
    
    /**
     * @return the pewPew
     */
    public Sound getPewPew() {
        return pewPew;
    }

    /**
     * @param boom the boom to set
     */
    public void setBoom(boolean boom) {
        this.boom = boom;
    }

    /**
     * @return the boom
     */
    public boolean isBoom() {
        return boom;
    }
}

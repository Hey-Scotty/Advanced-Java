
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
public class Enemy {

    /**
     * @return the changeDirection
     */
    public static boolean isChangeDirection() {
        return changeDirection;
    }

    /**
     * @param aChangeDirection the changeDirection to set
     */
    public static void setChangeDirection(boolean ChangeDirection) {
        changeDirection = ChangeDirection;
    }
    private static BufferedImage eSprite[] = new BufferedImage[6];
    private BufferedImage displaySprite;
    private Rectangle rect;
    private double xPos, yPos;
    private int width, height;
    private boolean isAlive;
    private static boolean goingLeft;
    private static boolean goingRight;
    private static boolean changeDirection;
    private EnemyBullet bullet;
    private double enemySpeed;
    private boolean shoot;
    private int scoreVal;
    private boolean landeded = false;
    private Timer timer = new Timer();
    private int counter = 0;
    private Sound wepWep;
    /**
     * instantiates all uninstantiated variables
     * @param xPos x- coordinate of starting image 
     * @param yPos y- coordinate of starting image 
     * @param width width of image 
     * @param height height of image
     */
    public Enemy(double xPos, double yPos, int width, int height, int scoreVal, int startSpeed){
        this.wepWep = new Sound("/Sounds/invaderkilled.wav");
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.rect = new Rectangle((int)xPos, (int)yPos, width, height);
        rect = new Rectangle((int) xPos,(int)yPos, width, height);
        this.scoreVal = scoreVal;
        this.isAlive = true;
        goingLeft = false;
        goingRight = true;
        enemySpeed = .1 + startSpeed*.1;
        changeDirection = false;
        try{
            URL url = this.getClass().getResource("/Sprites/invader1.png");
            eSprite[0] = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/invader2.png");
            eSprite[1] = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/invader3.png");
            eSprite[2] = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/invader4.png");
            eSprite[3] = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/invader5.png");
            eSprite[4] = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/invader6.png");
            eSprite[5] = ImageIO.read(url);
        }catch(IOException e){};
        if(scoreVal == 10)
            displaySprite = eSprite[0];
        else if(scoreVal == 20)
            displaySprite = eSprite[2];
        else if(scoreVal == 40)
            displaySprite = eSprite[4];
        
    }
    private void alternate(){
        if(this.counter%48 < 24 && scoreVal == 10)
            displaySprite = eSprite[0];
        else if (this.counter%48 > 24 && scoreVal == 10)
            displaySprite = eSprite[1];
        if(this.counter%48 < 24 && scoreVal == 20)
            displaySprite = eSprite[2];
        else if (this.counter%48 > 24 && scoreVal == 20)
            displaySprite = eSprite[3];
        if(this.counter%48 < 24 && scoreVal == 40)
            displaySprite = eSprite[4];
        else if (this.counter%48 > 24 && scoreVal == 40)
            displaySprite = eSprite[5];
        
    }
    public void draw(Graphics2D g){
        if(isIsAlive())
            g.drawImage(displaySprite,(int)xPos,(int)yPos, width, height, null);
        if(isIsAlive()&& getEnemyBullet()!=null)
            getEnemyBullet().draw(g);
     }
     // needs work 
    public void copyAjacentMovement(Enemy enemy){
        this.goingLeft = enemy.isGoingLeft();
        this.goingRight = enemy.isGoingRight();
    }
    public void speedUp(){
        enemySpeed+=.2;
    }
     /**
      * removes enemy from game
      */
    public void killEnemy(){
        wepWep.play();
        isAlive = false;
        rect = new Rectangle(0,0,0,0);
    }
     /**
     * updates enemy instance variables
     * @param delta 
     */
    public void update(double delta){
        if(isIsAlive()){
            counter++;
            alternate();
            if(goingLeft){
                xPos -= enemySpeed;
                rect.x = (int) xPos;
                if(xPos < 10){
                    setChangeDirection(true);
                    goingLeft = false;
                    goingRight = true;
                }
            }  
            if(goingRight){
                xPos += enemySpeed;
                rect.x = (int) xPos;
                if(xPos > 780){
                    setChangeDirection(true);
                    goingRight = false;
                    goingLeft = true;
                }
            }
        }
        if(shoot){
            this.setEnemyBullet(new EnemyBullet((int)this.xPos + 15, (int)this.yPos + 2));
            setShoot(false);
        }
        if(getEnemyBullet()!= null)
            getEnemyBullet().update(delta);
        if(getEnemyBullet()!=null && !bullet.isOnscreen()){
            setShoot(false);
            setEnemyBullet(null); 
        }
          
    }
    public void moveDown(){
        yPos += 40;
        rect.y = (int) yPos;
    } 
    /**
     * @return the goingLeft
     */
    public boolean isGoingLeft(){
        return goingLeft;
    }

    /**
     * @return the goingRight
     */
    public boolean isGoingRight() {
        return goingRight;
    }

    /**
     * @return this rect
     */
    public Rectangle getRect() {
        return rect;
    }
    /**
     * 
     * @return this bullet 
     */
    public PlayerBullet getEnemyBullet() {
        return bullet;
    }
    public void setEnemyBullet(EnemyBullet bullet) {
        this.bullet = bullet;
    }
    public void shoot(){
        this.setShoot(true); 
    }

    /**
     * @return the SCOREVAL
     */
    public int getSCOREVAL() {
        return scoreVal;
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
    /**
     * @return the isAlive
     */
    public boolean isIsAlive() {
        return isAlive;
    }

    /**
     * @param shoot the shoot to set
     */
    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }
}

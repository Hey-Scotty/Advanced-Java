/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.awt.Rectangle;

/**
 *
 * @author TinieT
 */
public class EnemyBullet extends PlayerBullet{

    public EnemyBullet(double xPos, double yPos) {
        super(xPos, yPos);
        this.height = 10;
        this.width = 4;
    }
    public void update(double delta){
        yPos += 7;
        rect.y = (int) yPos;
    }
    public boolean isOnscreen(){
        boolean answer = true;
        if(yPos > 600)
            answer = false;
        return answer;
    }
    public boolean hasCollided(Rectangle rectInput){
        boolean answer = false;
        if(topLeftCheck(rectInput) || topRightCheck(rectInput) || botLeftCheck(rectInput) || botRightCheck(rectInput) /*||topMidCheck(rectInput)*/)
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

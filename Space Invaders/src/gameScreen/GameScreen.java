/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameScreen;

import Entities.Player;
import Entities.Block;
import Entities.Enemy;
import Entities.MotherShip;
import Sounds.Sound;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;
import state.SuperStateMachine;
/**
 *
 * @author TinieT
 */
public class GameScreen implements SuperStateMachine{
    private static Sound intro = new Sound("/Sounds/intro.wav");
    private Player player;
    private Block[] block1;
    private Block[] block2;
    private Block[] block3;
    private Block[] block4;
    private int xPos = 375;
    Random rand = new Random(); //used to control how often enemies shoot and how often the mothership 
    private int level = 1;
    private int rows = 11, cols = 5;
    private Enemy[][] enemy = new Enemy[rows][cols];
    private MotherShip ms;
    private boolean gameOver;
    private boolean menu = true;
    private BufferedImage menuImage;
    private boolean playIntro = true;
    public GameScreen(){
        try{
            URL url = this.getClass().getResource("/gameScreen/SpaceInvaders.jpg");
            menuImage = ImageIO.read(url);
        }catch(IOException e){};
        player = new Player(xPos,575,35,25);
        reset(0);
        gameOver = false;
        intializeBlock1();
        intializeBlock2();
        intializeBlock3();
        intializeBlock4();
        
    }
    private void restart(){
        reset(0);
        gameOver = false;
        intializeBlock1();
        intializeBlock2();
        intializeBlock3();
        intializeBlock4();
    }
    private void reset(int score){
        //enemy col val and sprite type
        for(int i = 0; i < rows; i++){
            for(int k = 0; k < cols; k++){
                if(k < 1)
                enemy[i][k] = new Enemy((i * 40),(80 + (k * 40)),25,25, 40, level);
                if(k < 3 && k > 0)
                enemy[i][k] = new Enemy((i * 40),(80 + (k * 40)),25,25, 20, level);
                if(k < 5 && k > 2)
                enemy[i][k] = new Enemy((i * 40),(80 + (k * 40)),25,25, 10, level);
            }
        }
        
    }
    /**
     * updates the entities displayed on screen
     * @param delta control update rate
     */
    public void update(double delta){
        if(!gameOver && !player.getPause() && !menu){
            player.update(delta);
            for(int i = 0; i < enemy.length; i++){
                for(int k = 0; k < enemy[i].length; k++){
                    if(enemy[i][k].isIsAlive())
                        enemy[i][k].update(delta);
                }
            }          
            for(int i = 0; i < enemy.length; i++){
                for(int k = 0; k < enemy[i].length; k++){
                    //**checks to see if the player's bullet has hit the enemy in the array
                    if(enemy[i][k].isIsAlive() && enemy[i][k]!=null){
                        if((player.getPlayerBullet() != null) && (player.getPlayerBullet().hasCollided(enemy[i][k].getRect()) )){
                            enemy[i][k].killEnemy();
                        
                            player.addToScore(enemy[i][k].getSCOREVAL());
                            player.setBullet(null);
                        }
                        if(enemy[i][k].getEnemyBullet() != null){
                            if (enemy[i][k].getEnemyBullet().hasCollided(player.getRect())) {
                                player.Explode();
                                player.setBoom(true);
                                player.decrementLives();   
                                enemy[i][k].setEnemyBullet(null);
                            }                                       
                        }
                        if(enemy[i][k].hasCollided(player.getRect()) || player.getLives() < 1){
                            System.out.println("bump");
                            gameOver = true;
                        }    
                        for(int j = 0; j < block1.length; j++){
                            if(enemy[i][k].getEnemyBullet() != null){
                                if(block1[j]!=null && enemy[i][k].getEnemyBullet().hasCollided(block1[j].getRect())) {
                                    block1[j].incrementDamageCount();
                                    enemy[i][k].setEnemyBullet(null);
                                }
                                else if(block2[j]!=null && enemy[i][k].getEnemyBullet().hasCollided(block2[j].getRect())) {
                                    block2[j].incrementDamageCount();
                                    enemy[i][k].setEnemyBullet(null);
                                }    
                                else if(block3[j]!=null && enemy[i][k].getEnemyBullet().hasCollided(block3[j].getRect())) {
                                    block3[j].incrementDamageCount();
                                    enemy[i][k].setEnemyBullet(null);
                                }    
                                else if(block4[j]!=null && enemy[i][k].getEnemyBullet().hasCollided(block4[j].getRect())) {
                                    block4[j].incrementDamageCount();
                                    enemy[i][k].setEnemyBullet(null);
                                }    
                            }
                        }
                    }
                }
            }
            for(int i = 0; i < block1.length; i++){
                if(block1[i].isActive() && (player.getPlayerBullet() != null) && (player.getPlayerBullet().hasCollided(block1[i].getRect()))){
                    block1[i].incrementDamageCount();
                    player.setBullet(null);
                }
                if(block2[i].isActive() && (player.getPlayerBullet() != null) && (player.getPlayerBullet().hasCollided(block2[i].getRect()))){
                    block2[i].incrementDamageCount();
                    player.setBullet(null);
                }
                if(block3[i].isActive() && (player.getPlayerBullet() != null) && (player.getPlayerBullet().hasCollided(block3[i].getRect()))){
                    block3[i].incrementDamageCount();
                    player.setBullet(null);
                }
                if(block4[i].isActive() && (player.getPlayerBullet() != null) && (player.getPlayerBullet().hasCollided(block4[i].getRect()))){
                    block4[i].incrementDamageCount();
                    player.setBullet(null);
                }
                block1[i].update(delta);
                block2[i].update(delta);
                block3[i].update(delta);
                block4[i].update(delta);
            }
            for(int i = 0; i < enemy.length; i++){
                for(int k = 0; k < enemy[i].length; k++){
                    if(enemy[i][k].isChangeDirection()){
                        enemy[i][k].speedUp();
                        enemy[i][k].moveDown();
                    }
                }
            }       
            enemy[0][0].setChangeDirection(false);
            int holder = rand.nextInt(40);
            if(holder == 1){
                boolean executed = false;
                while(!executed){
                    int row = rand.nextInt(rows);
                    int col = rand.nextInt(cols);
                    if(enemy[row][col].isIsAlive()){
                        enemy[row][col].setShoot(true);
                        executed = true;
                    }
                }
            }
            holder = rand.nextInt(300);
            if(holder == 1){
                if(ms == null)
                    ms = new MotherShip(0,40, 30, 10);
            }
            if(ms != null){
                ms.update(delta);
                if(player.getBullet() != null && player.getPlayerBullet().hasCollided(ms.getRect())){
                    player.addToScore(ms.getSCOREVAL());
                    ms = null;
                    player.setBullet(null);
                }
            }
            if(ms != null && !ms.isOnscreen())
                    ms = null;
            //game over if player.getLives == 0 or if all enemies are dead
            if(allDead()){
                level++;
                reset(player.getScore());
                player.incrementLives();
            }
        }
        else if(menu){
          
          if(player.isShoot()|| player.isLeft() || player.isRight())
              menu = false;
        }
        if(playIntro){
           intro.play();
           playIntro = false;
        }
        
    }
    
    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", 1,30));
        if(!menu && !gameOver){ 
            g.drawString(String.valueOf("Level: "+ level), 350, 30);
            g.drawString(String.valueOf(player.getScore()),20, 30);
            g.drawString(String.valueOf("Lives: " + player.getLives()),680, 30);
            player.draw(g);              
            if(ms != null)
                ms.draw(g);
            for(int i = 0; i < block1.length; i++){
                block1[i].draw(g);
                block2[i].draw(g);
                block3[i].draw(g);
                block4[i].draw(g);
            }
            for(int i = 0; i < enemy.length; i++){
                for(int k = 0; k < enemy[i].length; k++){
                    enemy[i][k].draw(g);
                }
            }
        }
        g.setFont(new Font("Arial", 1,20));
        if(menu){
            g.drawImage(menuImage,0,0, 800, 600, null);
            g.drawString(String.valueOf("Push any Button to play"), 290, 460);
            g.drawString(String.valueOf("UP to shoot, move with Left and right, Pause to Pause"), 140, 500);
            
        }
        if(gameOver && !menu){
            g.setFont(new Font("Arial", 1,40));
            g.drawString(String.valueOf("GG Scrub better luck next Time"), 70, 300);
            g.setFont(new Font("Arial", 1,30));
            g.drawString(String.valueOf("Press any Key to restart"), 70, 400);
        }
    }
    public boolean allDead(){
        boolean answer = false;
        outerloop:
        for(int i = 0; i < cols; i++){
            for(int k = 0; k < rows; k++){
                if(!enemy[k][i].isIsAlive())
                    answer = true;
                if(enemy[k][i].isIsAlive()){
                    answer = false;
                    break outerloop;
                }    
            }
        }
        return answer;
    }
    private void intializeBlock1(){
        block1 = new Block[6];
        block1[0] = new Block(80, 500, 30,30,1);
        block1[1] = new Block(80, 470, 30,30,2);
        block1[2] = new Block(110, 470, 30,30,3);
        block1[3] = new Block(140, 470, 30,30,4);
        block1[4] = new Block(140, 500, 30,30,5);
        block1[5] = new Block(110, 500, 30,30,6);
    }
    private void intializeBlock2(){
        block2 = new Block[6];
        block2[0] = new Block(250, 500, 30,30,1);
        block2[1] = new Block(250, 470, 30,30,2);
        block2[2] = new Block(280, 470, 30,30,3);
        block2[3] = new Block(310, 470, 30,30,4);
        block2[4] = new Block(310, 500, 30,30,5);
        block2[5] = new Block(280, 500, 30,30,6);
    }
    private void intializeBlock3(){
        block3 = new Block[6];
        block3[0] = new Block(420, 500, 30,30,1);
        block3[1] = new Block(420, 470, 30,30,2);
        block3[2] = new Block(450, 470, 30,30,3);
        block3[3] = new Block(480, 470, 30,30,4);
        block3[4] = new Block(480, 500, 30,30,5);
        block3[5] = new Block(450, 500, 30,30,6);
    }
    private void intializeBlock4(){
        block4 = new Block[6];
        block4[0] = new Block(590, 500, 30,30,1);
        block4[1] = new Block(590, 470, 30,30,2);
        block4[2] = new Block(620, 470, 30,30,3);
        block4[3] = new Block(650, 470, 30,30,4);
        block4[4] = new Block(650, 500, 30,30,5);
        block4[5] = new Block(620, 500, 30,30,6);
    }
    @Override
    public void init(Canvas canvas) {
        canvas.addKeyListener(player);
    }
    
}

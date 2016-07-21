/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import state.StateMachine;

/**
 *
 * @author TinieT
 */
public class Project5Driver extends Canvas implements Runnable{
    /**
     * instance variables for the display size
     */
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private Thread thread;
    private boolean running = false;
    public int FPS;
    public static StateMachine state;
    public static void main(String[] args){
        Project5Driver display = new Project5Driver();
        JFrame frame = new JFrame();
        frame.add(display);
        frame.pack();
        frame.setTitle("Space Invaders!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        display.start();
        
    }
    /**
     * creates the display
     */
    public Project5Driver(){
        this.setSize(WIDTH,HEIGHT);
        this.setFocusable(true);
        
        state = new StateMachine(this);
        state.setState((byte) 0);
    }
    @Override
    /**
     * 
     */
    public void run() {
        long timer = System.currentTimeMillis();
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        int frames = 0;
        this.createBufferStrategy(3);
        BufferStrategy bs = this.getBufferStrategy();
        while(running){
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength/((double) OPTIMAL_TIME);
            
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                FPS = frames;
            }
            draw(bs);
            update(delta);
            try{
                Thread.sleep(((lastLoopTime - System.nanoTime()) + OPTIMAL_TIME) / 1000000);
            }catch(Exception e){}
        }
    }
    /**
     * 
     * @param bs 
     */
    public void draw(BufferStrategy bs){
        do{
            do{
                Graphics2D g = (Graphics2D) bs.getDrawGraphics();
                g.setColor(Color.BLACK);
                g.fillRect(0, 0,WIDTH + 50,HEIGHT + 50);
                state.draw(g);
                g.dispose();
            }while(bs.contentsRestored());
            bs.show();
        }while(bs.contentsLost());
    }
    public void update(double delta){
        state.update(delta);
    }
    //** tutorial code for running thread
    public synchronized void start(){
        if(running)
          return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    //** tutorial code for running thread
    public synchronized void stop(){
        if(!running)
          return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

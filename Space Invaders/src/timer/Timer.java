/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timer;

/**
 *
 * @author TinieT
 */
public class Timer {
    private long prevTime;
    
    public Timer(){
        prevTime = System.currentTimeMillis();
    }
    public void setPrevTime(long currentTime){
        this.prevTime = currentTime;
    }
    public void resetTimer(){
        prevTime = System.currentTimeMillis();
    }
    public boolean timerEvent(int timer){
        boolean answer = false;
        if(System.currentTimeMillis() - getPrevTime() > timer){
            resetTimer();
            answer = true;
        }
        return answer;
        
    }
    public boolean isTimerReady(int timer){
        boolean answer = false;
        if(System.currentTimeMillis() - getPrevTime() > timer){
            answer = true;
        }
        return answer;
    }
    /**
     * @return the prevTime
     */
    public long getPrevTime() {
        return prevTime;
    }
}

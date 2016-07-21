package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author TinieT
 */
public class SubWindow extends JFrame{
    
    private JButton hide;
    private JButton exit;
    private JPanel canvas;
    private Timer timer;
    private Random rand;
    private final int DELAY = 60;
    private final int FRAMEHEIGHT = 460, FRAMEWIDTH = 420;// ;)
    private final int PANELHEIGHT = 400, PANELWIDTH = 400;
    private final int SHAPEWIDTH = 40, SHAPEHEIGHT = 40;
    private LinkedList<ObjectShape> shapeStack;
    private LinkedList<JLabel> labelStack;
    /**
     * instantiates a SubWindow JFrame and its instance variables
     */
    public SubWindow(){
        shapeStack =  new LinkedList<ObjectShape>();
        labelStack = new LinkedList<JLabel>();
        rand = new Random();
        this.setLayout(null);
        instantiateButtons();
        setButtonTexts();
        instantiatePanel();
        addActions();
        addButtons();
        organizeFrame();
        instantiateTimer();
    }
    private void instantiateTimer(){
        Timer t = new Timer(DELAY, new ActionListener(){
            public void actionPerformed(ActionEvent event){
                SubWindow.this.revalidate();
                for (int i = 0; i < shapeStack.size(); i++){
                    shapeStack.get(i).translate(); 
                    shapeStack.get(i).wrapImagetoLeft(PANELWIDTH);
                    shapeStack.get(i).wrapImagetoRight(PANELWIDTH);
                    labelStack.get(i).repaint();
                    System.out.println(i);
                }
        }});        
        t.start();    
    }

    private void createNewPlaneShape(){
        Airplane shape = new Airplane(rand.nextInt(FRAMEWIDTH  - SHAPEWIDTH) , rand.nextInt(FRAMEHEIGHT - SHAPEHEIGHT), SHAPEHEIGHT, SHAPEWIDTH, (rand.nextInt(11)-5));
        shapeStack.add(shape);
        ShapeIcon icon = new ShapeIcon(shape, SHAPEHEIGHT, SHAPEWIDTH);
        final JLabel label = new JLabel();
        
        label.setIcon(icon);
        label.setSize(PANELHEIGHT,PANELWIDTH);
        labelStack.add(label);
        canvas.add(label);
        labelStack.getLast().setLocation(0, 0);
        //learned a window must be revalidated to display the newly added label
        canvas.revalidate();
        canvas.repaint();
        System.out.println("ShapeCreated");
    }
    private void createNewBoat(){
        Boat shape = new Boat(rand.nextInt(FRAMEWIDTH  - SHAPEWIDTH) , rand.nextInt(FRAMEHEIGHT - SHAPEHEIGHT), SHAPEHEIGHT, SHAPEWIDTH, (rand.nextInt(11)-5));
        shapeStack.add(shape);
        ShapeIcon icon = new ShapeIcon(shape, SHAPEHEIGHT, SHAPEWIDTH);
        final JLabel label = new JLabel();
        label.setIcon(icon);
        label.setSize(PANELHEIGHT,PANELWIDTH);
        labelStack.add(label);
        canvas.add(label);
        labelStack.getLast().setLocation(0, 0);
        //learned a window must be revalidated to display the newly added label
        canvas.revalidate();
        canvas.repaint();
        System.out.println("ShapeCreated");
    }
    public void createNewClock(){
        Clock shape = new Clock(rand.nextInt(FRAMEWIDTH  - SHAPEWIDTH) , rand.nextInt(FRAMEHEIGHT - SHAPEHEIGHT), SHAPEHEIGHT, SHAPEWIDTH , (rand.nextInt(11)-5));
        shapeStack.add(shape);
        ShapeIcon icon = new ShapeIcon(shape, SHAPEHEIGHT, SHAPEWIDTH);
        final JLabel label = new JLabel();
        label.setIcon(icon);
        label.setSize(PANELHEIGHT,PANELWIDTH);
        labelStack.add(label);
        canvas.add(label);
        labelStack.getLast().setLocation(0, 0);
        //learned a window must be revalidated to display the newly added label
        canvas.revalidate();
        canvas.repaint();
        System.out.println("ShapeCreated");
    }
    /**
     * removes the last added shape to this window
     */ 
    public void removeLastShape(){
        canvas.remove(labelStack.getLast());
        labelStack.removeLast();
        shapeStack.removeLast();
        this.revalidate();
        this.repaint();
    }
    /**
     * selects which shape will be added to display 
     */
    public void addShape(int selectedShape){
        //if conidtionals
        if(selectedShape == 1){
            this.createNewBoat();
            System.out.println("shapeSelected");
        }
        if(selectedShape == 2){
            this.createNewPlaneShape();
            System.out.println("shapeSelected");
        }
        if(selectedShape == 3){
            this.createNewClock();
        }
    }
    private void addButtons(){
        this.add(hide);
        hide.setLocation(240, 5);
        hide.setSize(100, 20);
        this.add(exit);
        exit.setLocation(70, 5);
        exit.setSize(100, 20);
    }
    private void addToPanel(){
        
    }
    private void setButtonTexts(){
        hide.setText("hide");
        exit.setText("exit");
    }
    private void instantiateButtons(){
        hide = new JButton();
        exit = new JButton();
    }
    private void instantiatePanel(){
        canvas = new JPanel();
        canvas.setLayout(null);
        canvas.setSize(PANELHEIGHT, PANELWIDTH);
        canvas.setOpaque(true);
        canvas.setBackground(Color.BLACK);
        this.add(canvas);
        canvas.setLocation(8,30);
        
    }
    private void addActions(){
        hide.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                SubWindow.this.setState(Frame.ICONIFIED);
            }
        });
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                
                SubWindow.this.dispose();
                SubWindow.this.setVisible(false);
                
                
            }
        });
    }
    private void organizeFrame(){
        this.setVisible(true);
        this.setSize(FRAMEWIDTH, FRAMEHEIGHT);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.revalidate();
 
        
    }
    
    /*
    public void render(Graphics2D g){
        for (int i = 0; i < shapeStack.size(); i++){
            shapeStack.get(i).draw(g);
            
        }

    }
    */
}

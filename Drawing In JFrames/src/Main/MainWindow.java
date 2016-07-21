/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.control.CheckBox;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author TinieT
 */
public class MainWindow extends JFrame{
    private JButton show;
    private JButton exit;
    private JButton add;
    private JButton remove;
    private CheckboxGroup cbg;
    private Checkbox boatCheckbox, airplaneCheckbox, clockCheckbox;
    private JFrame subWindow;
    private final int HEIGHT = 120, WIDTH = 300;
    /**
     * instantiates an object of type main window, and its instance variables
     */
    public MainWindow(){
        organizeFrames();
        instantiateButtons();
        setButtonTexts();
        addActions();
        cbg = new CheckboxGroup();
        instantiateCheckboxes();
        addButtons();
        subWindow = null;

    }
    /*
    organizes this JFrame, setting the layout, size and close operation
    */
    private void organizeFrames(){
        this.setLayout(new FlowLayout());
        this.pack();
        this.setVisible(true);
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /*
    instantiates the buttons of this class
    */
    private void instantiateButtons(){
        show = new JButton();
        exit = new JButton();
        add = new JButton();
        remove = new JButton();
    }
    /*
    sets the text of the buttons for this class
    */
    private void setButtonTexts(){
        show.setText("show");
        exit.setText("exit");
        remove.setText("remove");
        add.setText("add");
    }
    /*
    adds buttons to this JFrame class
    */
    private void addButtons(){
        this.add(add);
        this.add(exit);
        this.add(show);
        this.add(remove);
        this.add(boatCheckbox);
        this.add(airplaneCheckbox);
        this.add(clockCheckbox);
        
    }
    /*
    instantiates the various checkbox options
    */
    private void instantiateCheckboxes(){
        this.boatCheckbox = new Checkbox("Boat",false,cbg);
        this.airplaneCheckbox = new Checkbox("Airplane",false,cbg);
        this.clockCheckbox = new Checkbox("Clock",false,cbg);
    }
    
    /*
    adds actionListeners to JButtons
    */
    private void addActions(){
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                showActionHelper();
                addShapeHelper();
            }
        });
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                System.exit(0);
            }
        });
        remove.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                removeShapeHelper();
            }
        });
        show.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                showActionHelper();
            }
        });
    }
    private void removeShapeHelper(){
        ((SubWindow)subWindow).removeLastShape();
    }
    private void showActionHelper(){
        if(subWindow == null){
                    subWindow = new SubWindow();
                }
                else if(!subWindow.isVisible()){
                    subWindow = new SubWindow();
                }
                else{
                    subWindow.setState(Frame.NORMAL);
                }
    }
    /*
    add shape helper method for the addButton actionListener
    */
    private void addShapeHelper(){
        if(subWindow!= null &&subWindow.isVisible()){
            if(boatCheckbox.getState()){
                // learned that i had to cast the object to access submethod
                ((SubWindow)subWindow).addShape(1);
                System.out.println(boatCheckbox.getState());
            }
            if(airplaneCheckbox.getState()){
                // learned that i had to cast the object to access submethod
                ((SubWindow)subWindow).addShape(2);
            }
            if(clockCheckbox.getState()){
                // learned that i had to cast the object to access submethod
                ((SubWindow)subWindow).addShape(3);
            }    
        }
    }
}

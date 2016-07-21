package vendingmachine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import javax.imageio.ImageIO;
/**
 * Model of a Item stored inside a vending Machine and its various attributes
 * and behaviors
 * @author Tevin Scott
 * @version 1.0
 */
public class Item {

    private String name;
    private double value;
    private final int DEFAULTSTOCK = 5;
    private int quantity;
    
    /**
     * constructs the Item Class; instantiating its instance variables
     */
    public Item() {
        this.name = "empty";
        this.value = 0.00;
        this.quantity = 0;
    }
    /**
     * constructs the Item Class; instantiating its instance variables
     * @param _name set to the name instance variable
     */
    public Item(String _name) {
        this.name = _name;
        this.value = 1.75;
        this.quantity = DEFAULTSTOCK;
    }
    /**
     * constructs the Item Class; instantiating its instance variables
     * @param _name set to the name instance variable
     * @param _value set to the value instance variable
     */
    public Item(String _name, double _value) {
        this.name = _name;
        this.value = _value;
        this.quantity = DEFAULTSTOCK;
    }
    
    /**
     * @return the name of the item 
     */
    public String getName() {
        return this.name;
    }
    public String getPricing(){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(this.value);
    }
    /**
     * @return value of the item
     */
    public double getValue() {
        return this.value;
    }
    /**
     * @param input set to name variable 
     */
    public void setName(String input) {
        this.name = input;
    }
    /**
     * @param input set to value instance variable 
     */
    public void setValue(Double input) {
        this.value = input;
    }
    /**
     * increments the item quantity instance variable by 1
     */
    public void incrementQuant() {
        this.setQuantity(this.getQuantity() + 1);
    }
    /**
     * decrements the item quantity instance variable by 1
     */
    public void decrementQuant() {
        this.setQuantity(this.getQuantity() - 1);
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

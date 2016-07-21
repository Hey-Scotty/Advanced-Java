package vendingmachine;

import java.text.DecimalFormat;
/**
 * Model of a Vending Machine and its various attributes and behaviors
 * @author Tevin Scott
 * @version 1.0
 */
public class VendingMachine {

    private final int VARIETYSIZE = 8;
    private final String DEFAULTADMINPW = "0000";
    private String storedPW,tempPW;
    private Item[] itemArray = new Item[VARIETYSIZE];
    private double bank, tempStore;
    private int selectedItem;
    private boolean isItemSelected,adminEnabled,passwordChange;
    /**
     * constructs the VendingMachine Class; instantiating its instance variables
     */
    public VendingMachine() {
        initArray();
        this.bank = 0;
        this.tempStore = 0;
        isItemSelected = false;
        adminEnabled = false;
        storedPW = DEFAULTADMINPW;
        passwordChange = false;
    }
    /**
     * instantiates the itemArray
     */
    private void initArray(){
        itemArray[0] = new Item("Coca-Cola", 1.75);
        itemArray[1] = new Item("Coke Zero", 1.50);
        itemArray[2] = new Item("Diet Coke", 1.50);
        itemArray[3] = new Item("Sprite", 1.50);
        itemArray[4] = new Item("Powerade", 1.50);
        itemArray[5] = new Item("Mountain Dew", 1.50);
        itemArray[6] = new Item("Vitamin Water", 1.75);
        itemArray[7] = new Item("Dasani Water", 1.75);
    }
    /**
     * carries out purchase by user using an inputed number for selection
     * @param itemNo selected item purchased
     */
    public void makePurchase(int itemNo) {
        if(itemArray[itemNo].getQuantity() > 0){
            if (this.tempStore >= itemArray[itemNo].getValue()) {
                setBank(getBank() + this.tempStore);
                setTempStore(tempStore - itemArray[itemNo].getValue());
                itemArray[itemNo].decrementQuant();
            }
        }
    }
    /**
     * carries out purchase by user using the selectedItem instance variable
     */
    public void makePurchase() {
        if(itemArray[selectedItem].getQuantity() > 0){
            if (this.tempStore >= itemArray[selectedItem].getValue()) {
                setBank(getBank() + this.tempStore);
                setTempStore(tempStore - itemArray[selectedItem].getValue());
                itemArray[selectedItem].decrementQuant();
            }
        }
    }
    /**
     * increments the selected Item Quantity
     */
    public void incrementItemQuantity(){
        if(this.isItemSelected)
            itemArray[selectedItem].incrementQuant();
    }
    /**
     * decrements the selected Item Quantity
     */
    public void decrementItemQuantity(){
        if(this.isItemSelected)
            itemArray[selectedItem].decrementQuant();
    }
    /**
     * gets the selected Item Quantity
     * @return output the quantity of the selected Item
     */
    public int getSelectedItemQuantity(){
        int output = 0;
        if(this.isItemSelected)
            output = itemArray[selectedItem].getQuantity();
        return output;
    }
    /**
     * returns a boolean answer to if the input is equal to the stored Password
     * @param input user password input
     * @return answer to if the input and passwords are equal
     */
    public boolean isCodeEqual(String input){
        boolean answer = false;
        if(input.equals(storedPW))
            answer = true;
        return answer;
    }
    /**
     * @param itemNo specified element location in itemArray
     * @return the selected instance variable stored in itemArray 
     */
    public Item getSelectedItemObj(int itemNo) {
        Item handle = null;
        if (itemNo >= 0 && itemNo < 8)// within the array limits
            handle = this.itemArray[itemNo];
        return handle;
    }
    /**
     * adds 1.00 to the running total balance
     */
    public void addDollar() {
        this.setTempStore(this.tempStore + 1.00);
    } 
    /**
     * adds .25 to the running total balance
     */
    public void addQuart() {
        this.setTempStore(this.tempStore + .25);
    }
    /**
     * adds .10 to the running total balance
     */
    public void addDime() {
        this.setTempStore(this.tempStore + .10);
    }
    /**
     * adds .05 to the running total balance
     */
    public void addNickel() {
        this.setTempStore(this.tempStore + .05);
    }
    /**
     * adds .01 to the running total balance
     */
    public void addPenny() {
        this.setTempStore(this.tempStore + .01);
    }
    /**
     * clears coin balance
     */
    public void coinReturn() {
        this.setTempStore(0);
    }
    
    /**
     * @return bank value
     */
    public double getBank() {
        return bank;
    }
    /**
     * @param bank value that will be set to this bank instance variable
     */
    public void setBank(double bank) {
        this.bank = bank;
    }
    /**
     * @return the tempStore value rounded to two decimal places 
     */
    public String getTempStore() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(this.tempStore);
    }
    /**
     * @param _tempStore set to tempStore instance variable
     */
    public void setTempStore(double _tempStore) {
        this.setIsItemSelected(true);
        this.tempStore = _tempStore;
    }
    /**
     * @param _selectedItem set to selectedItem instance variable 
     */
    public void setSelectedItem(int _selectedItem) {
        this.selectedItem = _selectedItem;
    }
    /**
     * @return the selectedItem
     */
    public int getSelectedItem() {
        return selectedItem;
    }
    /**
     * @return the adminEnabled
     */
    public boolean isAdminEnabled() {
        return adminEnabled;
    }
    /**
     * @param adminEnabled the adminEnabled to set
     */
    public void setAdminEnabled(boolean adminEnabled) {
        this.adminEnabled = adminEnabled;
    }
    /**
     * sets the stored password to the temporary password instance variable
     */
    public void setStoredPW() {
        if(adminEnabled)
            this.storedPW = tempPW;
    }
    /**
     * @return the tempPW
     */
    public String getTempPW() {
        return tempPW;
    }

    /**
     * @param tempPW the tempPW to set
     */
    public void setTempPW(String tempPW) {
        this.tempPW = tempPW;
    }

    /**
     * @return the passwordChange
     */
    public boolean isPasswordChange() {
        return passwordChange;
    }

    /**
     * @param _passwordChange the passwordChange to set
     */
    public void setPasswordChange(boolean _passwordChange) {
        this.passwordChange = _passwordChange;
    }

    /**
     * @return the isItemSelected
     */
    public boolean isItemSelected() {
        return isItemSelected;
    }

    /**
     * @param isItemSelected the isItemSelected to set
     */
    public void setIsItemSelected(boolean isItemSelected) {
        this.isItemSelected = isItemSelected;
    }
    
}

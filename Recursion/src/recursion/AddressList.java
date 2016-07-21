/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursion;

/**
 *
 * @author Tevin
 */
public class AddressList {

    private ListNode head;
    private ListNode currentNode;

    public AddressList() {
        this.head = null;
        this.currentNode = null;
    }

    public AddressList(ListNode newHead) {
        head = newHead;
        currentNode = newHead;
    }

    public boolean isEmpty() {
        boolean answer = false;
        if (head == null) {
            answer = true;
        }
        return answer;
    }
    /**
     * adds a node to the front of the Address List
     * @param name 
     * @param tel
     * @param email
     * @param address
     * @param dob 
     */
    public void addToFront(String name, String tel, String email, String address, String dob) {
        ListNode newHead = new ListNode(name, tel, email, address, dob);
        if (head == null) {
            this.head = newHead;
        } else if (head != null) {
            currentNode = newHead;
            currentNode.setNext(this.head);
            this.head = currentNode;
        }
        currentNode = head;
    }
    /**
     * adds a node to the end of the Address List
     * @param name 
     * @param tel
     * @param email
     * @param address
     * @param dob 
     */
    public void addToBack(String name, String tel, String email, String address, String dob) {
        ListNode end = new ListNode(name, tel, email, address, dob);
        //1st base case
        if (head == null) {
            head = end;
            currentNode = head;
        } else if (head != null) {
            //2nd base case
            if (currentNode.next == null) {
                currentNode.next = end;
                currentNode = head;
            } //recursive section
            else {
                currentNode = currentNode.next;
                this.addToBack(name, tel, email, address, dob);
            }
        }
        currentNode = head;

    }
    /**
     * 
     * @return a string value of all nodes in this Address List
     */
    public String toString() {
        String output = "";
        if (currentNode != null) {
            output += "Address: " + currentNode.getAddr() + "\nDoB: "
                    + currentNode.getDob() + "\nMail " + currentNode.getEmail() + "\nName: "
                    + currentNode.getName() + "\nTel No.: " + currentNode.getTel() + "\n\n";
            currentNode = currentNode.next;
            output += toString();
        }
        currentNode = head;
        return output;
    }
    /**
     * 
     * @return a reversed String represnetation of this AddressList
     */
    public String reverseToString(){
        String output = "";
        output = this.reverse().toString();
        return output;
    }
    /**
     * reverses this AddressList's order
     * @return 
     */
    public AddressList reverse() {
        AddressList output = new AddressList();
        if(this.head!=null){
            output.head = new ListNode(head.name, head.tel, head.email,
                    head.addr, head.dob);
        }
        if(this.head.next != null){
            head = head.next;
            output.head.next = reverse().head;
        }
        return output;
    }

    /**
     * Returns the total amount of AddressList nodes
     * @return size of this AddressList
     */
    public int sizeOf() {
        int size = 0;
        if (currentNode != null) {
            size++;
            currentNode = currentNode.next;
            size = this.sizeOf() + size;
        }
        currentNode = head;
        return size;

    }

    /**
     * Searches the Linked List data structure for a name that matches the
     * input, returns the phone number associated with that number if found
     *
     * @param name that is being searched for
     * @return the phone number associated with the found name
     */
    public String phoneNumberByName(String name) {
        String outputPhoneNumber = "not found";
        if (currentNode != null) {
            if (currentNode.name.equals(name)) {
                outputPhoneNumber = currentNode.tel;
            } else {
                currentNode = currentNode.next;
                outputPhoneNumber = phoneNumberByName(name);
            }
        }
        currentNode = head;
        return outputPhoneNumber;
    }

    /**
     * Searches the Linked List data structure for a name that matches the
     * input, returns the email associated with that number if found
     *
     * @param name that is being searched for
     * @return the email associated with the found name
     */
    public String emailByName(String name) {
        String outputEmail = "not found";
        if (currentNode != null) {
            if (currentNode.name.equals(name)) {
                outputEmail = currentNode.email;
            } else {
                currentNode = currentNode.next;
                outputEmail = emailByName(name);
            }
        }
        currentNode = head;
        return outputEmail;
    }

    /**
     * Searches the Linked List data structure for a telephone number that
     * matches the input, returns the name associated with that number if found
     *
     * @param tel that is being searched for
     * @return the name associated with the found telephone number
     */
    public String nameByPhoneNumber(String tel) {
        String outputName = " not found";
        if (currentNode != null) {
            if (currentNode.tel.equals(tel)) {
                outputName = currentNode.name;
            } else {
                currentNode = currentNode.next;
                outputName = emailByName(tel);
            }
        }
        currentNode = head;
        return outputName;
    }

    /**
     * Searches the Linked List data structure for a Name that matches the input
     * ,returns the Date of Birth associated with that name if found
     *
     * @param name that is being searched for
     * @return the date of birth associated with the found name
     */
    public String dobByName(String name) {
        String outputDoB = " not found";
        if (currentNode != null) {
            if (currentNode.name.equals(name)) {
                outputDoB = currentNode.dob;
            } else {
                currentNode = currentNode.next;
                outputDoB = emailByName(name);
            }
        }
        currentNode = head;
        return outputDoB;

    }

    /**
     * This nested, private class represents a node of a singly linked list.
     */
    private class ListNode {

        private String name;
        private String tel; // Telephone number
        private String email;
        private String addr; // Address
        private String dob; // Date of birth
        private ListNode next; // Pointer to the next node

        private ListNode(String name, String tel, String email, String addr, String dob) {
            this.name = name;
            this.tel = tel;
            this.email = email;
            this.addr = addr;
            this.dob = dob;

        } // end of the constructor

        public String getName() {
            return name;
        }

        public String getTel() {
            return tel;
        }

        public String getEmail() {
            return email;
        }

        public String getAddr() {
            return addr;
        }

        public String getDob() {
            return dob;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode link) {
            next = link;
        }
    }
}

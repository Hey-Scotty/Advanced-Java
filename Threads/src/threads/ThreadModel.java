/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tevin
 */
public class ThreadModel extends Thread {

    private long threadID;
    private String fileLocation;
    private File file;
    private Scanner scan;
    private int wordCount;
    private FileMaker fm;
    /**
     * Constructs a ThreadModel Object and instantiates its instance variables
     * @param _fileLocation specifies the location the file that will be word counted
     */
    public ThreadModel(String _fileLocation) {
        threadID = this.getId();
        this.fileLocation = _fileLocation;
        file = parseFile();
        wordCount = 0;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * @return a File based on a relative fileLocation given 
     */
    private File parseFile() {
        File output = null;
        URL url = getClass().getResource(this.getFileLocation());
        output = new File(url.getPath());

        return output;
    }
    /**
     * runs a word count on the file variable of this class
     */
    public void run() {
        scan.useDelimiter(" ");
        while (scan.hasNext()) {
            wordCount++;
            scan.next();
            System.out.println(wordCount);
        }
        if (!scan.hasNext()) {
            fm = new FileMaker(this.toString());
            synchronized (fm) {
                fm.writeToFile();
            } //hammertime
            System.out.println("word counts" + wordCount);
        }
    }
    /**
     * copies the contents of the file in this class and returns a string
     * @return the contents of the file as a String
     * @throws IOException 
     */
    public String copyFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
    /**
     * @return the wordCount variable for this object 
     */
    public int getWordcount() {
        return this.wordCount;
    }

    /**
     * @return the threadId
     */
    public long getThreadID() {
        return threadID;
    }

    /**
     * @return the fileLocation
     */
    public String getFileLocation() {
        return fileLocation;
    }
    /**
     * @return string representation of this class 
     */
    public String toString() {
        String output = "";
        output = "Thread ID: " + this.getId() + " \"" + this.fileLocation;
        output += "\" has " + this.wordCount + " words. \n";
        return output;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Tevin
 */
public class FileMaker {

    BufferedWriter writer;
    String textInput;
    /**
     * Constructs an Object of this Class and Instantiates its variables
     * @param _textInput stores this variable which will be written to a text file
     */
    public FileMaker(String _textInput){
        textInput = _textInput;
    }
    /**
     * Writes the textInput string variable to a text file known as result.txt
     */
    public void writeToFile(){
        try{
            File file = new File("C:/Users/Steven/Documents/NetBeansProjects/Threads/src/result.txt");
            System.out.println("testing to String of sync: " + textInput);
            if (!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(textInput + "\n");        // write to file
            bw.close();
            fw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

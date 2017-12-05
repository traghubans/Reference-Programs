/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.nio.file.Files.list;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Travis Raghubans
 */
class Histogram{
    
    private final HashMap<String, Integer> mHistogram;  
    
    public Histogram(){
        this.mHistogram = new HashMap<>();
    }
    
    /*
    This function counts the number of occurence of each letter group
    input: letter groups
    output: histogram
    
    example: input: ["an","an","or","or","or"]
             output: [<"an",2>, <"or",3>]
    */
    public void generateHistogram(ArrayList<String> letterGroups)
    {
    
    Set<String> diff = new HashSet<String>(letterGroups);
    for(String key : diff)
    {
       int tracker = Collections.frequency(letterGroups,key);
       printHistogram(key,tracker);
    }
    
 
    }
    
    /*
    This function prints the histogram
    input: histogram
    output: histogram printed on the display
    
    example: input: [<"an",2>, <"or",3>]
             output:
    
    an: **
    or: ***
    
    */
    public void printHistogram(String Key, int tracker)
    {
    //Declared Variables 
    char ch = '*';
    System.out.print(Key + ":");
    for(int i = 0; i < tracker; i++)
    {
        System.out.print("*");   
    }
    System.out.println();
    }
    
}





public class SentenceUtils {  
    private final ArrayList<String> mLetterGroups;
    private final int mLetterGroupLen;
    
    public SentenceUtils(int letterGroupLen){
        this.mLetterGroups = new ArrayList<>();
        this.mLetterGroupLen = letterGroupLen;
    }

    
    public void addWords(String line)
    {
        String[] tokens = this.getTokens(line);
        this.splitTokenstoLetterGroups(tokens);
    }
    public ArrayList<String> getLetterGroups(){
        return (this.mLetterGroups);
    }
      
    /*
    This function splits a string into array of strings separated by space
    character
    input: a string
    output: array of words seperated by space character
    
    example: input: "object oriented"
             output: ["object", "oriented"]
    */
    private String[] getTokens(String line)
    { 

       String arr[] = new String[line.split(" ").length];
       arr = line.split(" ");
       return arr; 
    }
    
    /*
    This function splits the tokens into letter groups according to 
    this.mLetterGroupLen
    input: array of tokens
    output: letterGroups of size this.mLetterGroupLen appended to 
    this.mLetterGroups
    
    example: input: "object" "oriented"
             output: this.mLetterGroups (of size 4) = this.mLetterGroups + 
                  ["obje","bjec", "ject","orie", "rien", "ient", "ente", "nted"]
    */
    private void splitTokenstoLetterGroups(String[] tokens)
    {
        //Declared Variables 
        int i = 0;
        int j = 0;
        ArrayList<String> tokString = new ArrayList<String>();
        Histogram h = new Histogram();
        
      
        
        while(i<tokens.length)
        {
            String happy = tokens[i].toLowerCase();
            //int diff = mLetterGroupLen - tokens[i].length();
            for(j = 0; j <= happy.length() - mLetterGroupLen; j++)
            {
                tokString.add(j,happy.substring(j, j+mLetterGroupLen));
                //System.out.println(tokString.get(j));  
            }
            i++;
            
        }
        h.generateHistogram(tokString);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       
      //Declared Variables   
       String s;
       int num = Integer.parseInt(args[1]);
       String filename = "input1.txt";
       String line;
       
      //Main Method 
      
      //Getting the number from the command line
   
      SentenceUtils x  = new SentenceUtils(num); 
      
      
      //Open File 
      try 
      {
          FileReader fileReader = new FileReader(filename);
          BufferedReader bufferedReader = new BufferedReader(fileReader);
          
          while((line = bufferedReader.readLine()) != null)
          {
             x.addWords(line);
          }
          
          //Close File 
          bufferedReader.close();
      }
      catch(FileNotFoundException ex)
      {
          System.out.println("File not found " + filename);
      }
      catch(IOException ex)
      {
          System.out.println("There was a problem reading your file" + filename);
      }
 
    }
}


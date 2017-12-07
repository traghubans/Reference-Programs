/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newassignment7;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Travis Raghubans
 */
public class Assignment7Controller implements Initializable {
    
    //FXML Variables
    @FXML
    private Label label;
    @FXML
    private TextField SumText;
    @FXML
    private Button SumButton;
    @FXML
    private TextField AddOneText;
    @FXML
    private Button Add1Button;
    @FXML
    private Button CloseButton;
    @FXML
    private Button ResetButton;
    
   //Declared Variables
    public int Num = 0;
    private Service<Void> backgroundThread;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void didClickSumButton(ActionEvent event) 
    {
        //Set Button to Disable on Click
        SumButton.setDisable(true);
        
        //Declaration of the new background thread
        backgroundThread = new Service<Void>()
        {
         //Declared Int  
         int n = 0;
         
         //What this part of the program does is calls 
         //The pre-defined functions to update the gui slowly in a background thread
         @Override
         protected Task<Void> createTask() 
         {
                return new Task<Void>() 
                {
                //Call is where the magic happens, where to define what the program should be doing    
                @Override
                    protected Void call() throws Exception 
                    {
                     String Change;
                     for(int i=1;i<51;i++)
                     {
            
                        n+=i;
                        Change = Integer.toString(n);
                        Thread.sleep(100); //Slows the text field down
                        updateMessage(Change);//Predefined function found with Task
                        
                        if(i==50)
                        SumButton.setDisable(false);
                     }
                        return null; 
                    }
               };
          }
        };
        
     //Concept of binding that allows for the message to update
     SumText.textProperty().bind(backgroundThread.messageProperty());
     backgroundThread.restart();    
    }

    @FXML
    private void didClickAddOneButton(ActionEvent event) 
    {
        String string = null;
        int i;
        if(Num < 50)
        {
        Num++;
        string = Integer.toString(Num);
        AddOneText.setText(string);
        }
        
    }

    @FXML
    private void didClickCloseButton(ActionEvent event) 
    {
        if(CloseButton.isArmed())
        {
            Platform.exit();
        }      
    }
    
}

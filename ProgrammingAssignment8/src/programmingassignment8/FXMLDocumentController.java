/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingassignment8;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


/**
 *
 * @author Travis Raghubans
 */


public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
   

    //Node Class w/ Data in Node and Next ptr
    class Node<T>
    {
        public int data; 
        public Node<T> next;
        
        //Constructor
        public Node(int data)
        {
            this.data = data;
        }
        
    }
    
    //Linked List Class
    class LinkedList<T>
    {
        public Node<T> head; //Head of the List
        
        //Constructor 
        public LinkedList()
        {
            head = null;
        }
          
        public void addNode(int data)
        {
          
          Node<T> newNode = new Node(data);
          Node current;
 
         /* Special case for head node */
         if (head == null || head.data >= newNode.data)
         {
            newNode.next = head;
            head = newNode;
         }
         else 
         {
            //Locate the node before point of insertion. 
            current = head;
 
            while (current.next != null && current.next.data < newNode.data)
            {
                  current = current.next;
            }
 
            newNode.next = current.next;
            current.next = newNode;
          
        }
        }
        
        public void delete()
        {
            if(head == null)
            {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Option");
            alert.setContentText("List is already empty");
            alert.showAndWait();
            }
            while(head != null)
            {
            Node<T> temp = head;
            head = head.next;
            temp = null;
            }
            
        }
         
        
    }
    
    

    //FXML Variables
    @FXML
    private Button Button1;
    @FXML
    private Button Button2;
    @FXML
    private Button Button3;
    @FXML
    private Button Button4;
    @FXML
    private Button Button5;
    @FXML
    private Button ResetButton;
    @FXML
    private Button CloseButton;
    @FXML
    private Label NodeLabel;
    
    
    //Declare List
    LinkedList list = new LinkedList();
    //Declared Variables
   
    
    @FXML
    private void insertItem(ActionEvent event)
    {
      //Button 1
      if(Button1.isArmed())
      {
          list.addNode(1);       
          Button1.setDisable(true);
          printList();
      }
      //Button 2
      if(Button2.isArmed())
      {
          list.addNode(2);   
          Button2.setDisable(true);
          printList();
      }
      //Button 3
      if(Button3.isArmed())
      {
          list.addNode(3);      
          Button3.setDisable(true);
          printList();
      }
      //Button 4
      if(Button4.isArmed())
      {
          list.addNode(4);
          Button4.setDisable(true);
          printList();
      }
      //Button 5
      if(Button5.isArmed())
      {
          list.addNode(5);
          Button5.setDisable(true);
          printList();
      }
     
    }
    
    @FXML
    private void clearList(ActionEvent event)
    {
        //Close the Application
        if(CloseButton.isArmed())
        {
            Platform.exit();
        }
        
        if(ResetButton.isArmed())
        {
            list.delete();
            Button1.setDisable(false);
            Button2.setDisable(false);
            Button3.setDisable(false);
            Button4.setDisable(false);
            Button5.setDisable(false);
            printList();
        }
    }
    

    
    private void printList()
    {
        String str = "mHead"; 
        Node current = list.head;
        while(current != null) 
        {
            int data = current.data;
            str = str  + "  ->  " + Integer.toString(data);
            current = current.next;
        }
        NodeLabel.setText(str);
    }
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO      
    }    
    
}



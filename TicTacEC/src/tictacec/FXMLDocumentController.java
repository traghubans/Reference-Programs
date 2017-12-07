/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictacec;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Travis Raghubans
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button panel1;
    
    @FXML
    private Button panel2;
    
    @FXML
    private Button panel3;
    
    @FXML
    private Button panel4;
    
    @FXML
    private Button panel5;
    
    @FXML
    private Button panel6;
    
    @FXML
    private Button panel7;
    
    @FXML
    private Button panel8;
    
    @FXML
    private Button panel9;
    
    @FXML
    private GridPane Layout;
    
    @FXML
    private Label P1Label;
    
    @FXML
    private Label P2Label;
    
    //Declared Variables
    private boolean FirstTurn = true;
    private String One;
    private String Two;
    

    
    @FXML
    private void UpdateClick(ActionEvent event) throws Exception
    {
       
        Button ClickAction = (Button) event.getTarget();
        String XorO = ClickAction.getText();
        
            if(FirstTurn)
            {
            P2Label.setTextFill(Color.web("#0000ff"));
            P1Label.setTextFill(Color.web("#000000"));
            }
            else
            {
            P1Label.setTextFill(Color.web("#0000ff"));
            P2Label.setTextFill(Color.web("#000000"));
            }
            
            
            if("".equals(XorO) && FirstTurn)
            {
                ClickAction.setText("X");
                FirstTurn = false;
            }
            else if("".equals(XorO) && !FirstTurn)
            {
                ClickAction.setText("O");
                FirstTurn = true;
            }
        WinnerMethod();
        
        if(WinnerMethod())
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Winner's Dialog");
            if(FirstTurn == true)
            {
            alert.setContentText(One + " Won!");
            }
            else
            {
            alert.setContentText(Two + " Won!");
            }
            alert.showAndWait();
        }
    }
    
    private boolean WinnerMethod()
    {
     
     //Row 1
        if(!"".equals(panel1.getText()) && (panel1.getText().equals(panel2.getText())) && (panel2.getText().equals(panel3.getText())))
        {
          return true;
        }
    
        if(!"".equals(panel4.getText()) && (panel4.getText().equals(panel5.getText())) && (panel5.getText().equals(panel6.getText())))
        {
          return true;
        }
        
        if(!"".equals(panel7.getText()) && (panel7.getText().equals(panel8.getText())) && (panel8.getText().equals(panel9.getText())))
        {
          return true;
        }
        
        if(!"".equals(panel1.getText()) && (panel1.getText().equals(panel4.getText())) && (panel4.getText().equals(panel7.getText())))
        {
          return true;
        }
        
        if(!"".equals(panel2.getText()) && (panel2.getText().equals(panel5.getText())) && (panel5.getText().equals(panel8.getText())))
        {
          return true;
        }
        
        if(!"".equals(panel3.getText()) && (panel3.getText().equals(panel6.getText())) && (panel6.getText().equals(panel9.getText())))
        {
          return true;
        }
        
        if(!"".equals(panel1.getText()) && (panel1.getText().equals(panel5.getText())) && (panel5.getText().equals(panel9.getText())))
        {
          return true;
        }
        
        if(!"".equals(panel3.getText()) && (panel3.getText().equals(panel5.getText())) && (panel5.getText().equals(panel7.getText())))
        {
          return true;
        }
        
        return false;
    }
    
    
    //SetName redefined function
    public void setName(String One, String Two)
    {
        this.One = One;
        this.Two = Two;
        P1Label.setText(One);
        P2Label.setText(Two);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

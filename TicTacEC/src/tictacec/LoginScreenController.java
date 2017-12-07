/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictacec;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Travis Raghubans
 */
public class LoginScreenController implements Initializable {

    @FXML
    private Button resetButton;
    @FXML
    private Button startButton;
    @FXML
    private TextField player1label;
    @FXML
    private TextField player2label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void didClickLoginButtons(ActionEvent event) throws IOException 
    {
        
        FXMLLoader fxml = new FXMLLoader();
            fxml.setLocation(getClass().getResource("FXMLDocument.fxml"));
            Parent GameScene = fxml.load();
            Scene layout = new Scene(GameScene);
            FXMLDocumentController Scene = fxml.getController();
            Scene.setName(player1label.getText(), player2label.getText());
            Stage Board = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Board.setScene(layout);
            Board.show();

    }
    
    @FXML 
    private void didClickCloseButton(ActionEvent event)
    {
        Platform.exit();
    }


}

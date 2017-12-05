/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment6;

import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

enum ChartType{barChart, pieChart};
/**
 *
 * @author neslisah
 */
public class FXMLDocumentController implements Initializable {
    
    final int TOTAL_CHARS = 26;  
    ChartType mChartType;
    int mChars[];
    
    @FXML 
    private Button mButtonClose;
    
    
    @FXML
    private PieChart mPieChart;
    
    @FXML
    private ComboBox mComboBox;
   
    @FXML
    private BarChart<?,?> mBarChart;

    
    
   
    @FXML
    private void didClickCloseButton(ActionEvent event){
        Stage stage = (Stage) mButtonClose.getScene().getWindow();
        stage.close();
    }
    
    private String showInputDialog(){
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Input your text");

        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        TextField inputText = new TextField();
        inputText.setPromptText("Input Text");
    
        gridPane.add(inputText, 0, 0);
    
        dialog.getDialogPane().setContent(gridPane);

        Platform.runLater(()->inputText.requestFocus());

        dialog.setResultConverter((ButtonType dialogButton) -> {
            if (dialogButton == okButton) {
                return (inputText.getText());
            }
            return (null);
        });
        
        Optional<String> result = dialog.showAndWait();

        return (result.get());
    }
    
    private void paint(){
        
        // Please implement this function
        // This function according to the mChartType
        // refreshes either BarChart or PieChart
        // You need to add each item and its occurance (for instance 'a', 2)
        // to the selected chart for each character in the alpabet
        
        
        if(mChartType == ChartType.barChart)
        {   
         XYChart.Series series = new XYChart.Series<>();
         mBarChart.getData().remove(0,mBarChart.getData().size());
         
            for(int i = 0; i < TOTAL_CHARS; i++)
            {
               series.getData().add(new XYChart.Data(Character.toString((char)(i+97)),mChars[i]));
            }
            mBarChart.getData().addAll(series);
        }
        else
        {
         this.mPieChart.getData().remove(0,this.mPieChart.getData().size());   
         for(int i = 0; i < TOTAL_CHARS; i++)
            {
               this.mPieChart.getData().add(new PieChart.Data(Character.toString((char)(i+97)),mChars[i]));
            }
        }
        
    }
    
    @FXML
    private void didClickInputButton(ActionEvent event) {
        
        Arrays.fill(mChars, 0);
            
        String s = showInputDialog();
        
        if ((s!= null) && (s.trim().length() > 0)){
            
            s = s.toLowerCase();
            
            for (int i=0;i<s.length();i++){
                
                Character ch = s.charAt(i);
                
                if (Character.isAlphabetic(ch)){
                    
                    int index = ch - 'a';
                    mChars[index]++;
                }
            }
            
        }
        
        paint();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Please implement this function
        // 1. initialize your mChars array
           mChars = new int[1000];
        // 2. make mPieChart hidden    
           mPieChart.setOpacity(0);
        // 3. set mChartType to barChart
           mChartType = ChartType.barChart;
        // 4. add "BarChart" and "PieChart" to the comboBox
            mComboBox.getItems().addAll("Pie Chart","Bar Chart");
        // 5. set selected item "BarChart" in the combobox
            mComboBox.setValue("Bar Chart");
        // 6. add ChangeListener to ComboBox, and implement changed event
        // 7. In the changed event update the mChartType according to newValue and
        // 8. update the visibility of the charts accordingly
            mComboBox.valueProperty().addListener(new ChangeListener<String>()
            {
               @Override
               public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
               {
                   if(newValue.equals("Pie Chart"))
                   {
                       mChartType = ChartType.pieChart;
                       mBarChart.setOpacity(0);
                       mPieChart.setOpacity(100);
                       paint();
                   }
                   else
                   {
                       mChartType = ChartType.barChart;
                       mPieChart.setOpacity(0);
                       mBarChart.setOpacity(100);
                       paint();
                   }
                //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               }
                        
            });
            
        // 9. call the paint   
        // paint();
    }  
    
}

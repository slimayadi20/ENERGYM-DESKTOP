/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nouri
 */
public class ImcController implements Initializable {

    @FXML
    private TextField heightinput;
    @FXML
    private TextField weightinput;
    @FXML
    private Label imcoutpout;
    @FXML
    private Button buttonCalculate;
    @FXML
    private Label categoutput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      private void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void calculate(ActionEvent event) {
        try {
            Double weightValue = Double.parseDouble(weightinput.getText());
            Double heightValue = Double.parseDouble(heightinput.getText());
            Double bmiValue = weightValue/((heightValue/100)*(heightValue/100));
            weightinput.clear();
            heightinput.clear();
            setResult(bmiValue);

        } catch (Exception e) {

        }
    }

    void setResult(Double bmiValue) {

        imcoutpout.setText(bmiValue.toString());
        if (bmiValue <= 18.5) {
            categoutput.setText("insuffisance pondérale");

        } else if (18.6 <= bmiValue && bmiValue <= 24.9) {
            categoutput.setText("Poids normal");

        } else if (25 <= bmiValue && bmiValue <= 29.9) {
            categoutput.setText("en surpoids");
        } else {
            categoutput.setText("obese");
        }
    }

   

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


/**
 *
 * @author Channel Ai
 */
public class Imc_omarController implements Initializable {

    @FXML
    private ImageView img_bmi;
    @FXML
    private ImageView img_blue;
    @FXML
    private ImageView img_green;
    @FXML
    private ImageView img_orange;
    @FXML
    private ImageView img_red;
    @FXML
    private TextField tx1;
    @FXML
    private TextField tx2;
    @FXML
    private Label label;
    @FXML
    private Text tx3;

  
   @FXML
   public void calculate()
   {
       double h = Double.parseDouble(tx1.getText());
       double w = Double.parseDouble(tx2.getText());
       
       double bmi = w / (h * h);
       
       String cal = String.format("%.2f",bmi);
       
       tx3.setText(cal);
       
       if(bmi <= 18.5)
       {
            label.setTextFill(Color.BLUE);
            label.setText("UnderWeight");
            img_blue.setVisible(true);
            img_green.setVisible(false);
            img_orange.setVisible(false);
            img_red.setVisible(false);
            
       }
       else if(bmi <= 24.9)
       {
           label.setTextFill(Color.LIME);
           label.setText("Normal Weight");
           img_green.setVisible(true);
          
           img_orange.setVisible(false);
           img_red.setVisible(false);
           img_blue.setVisible(false);
           
       }
        else if(bmi <= 29.5)
       {
           label.setTextFill(Color.ORANGE);
           label.setText("OverWeight");
           img_orange.setVisible(true);
           img_green.setVisible(false);
          
           img_red.setVisible(false);
           img_blue.setVisible(false);
           
       }
        else if(bmi >= 30)
       {
           label.setTextFill(Color.RED);
           label.setText("Obese");
           img_red.setVisible(true);
           img_green.setVisible(false);
           img_orange.setVisible(false);
           
           img_blue.setVisible(false);
           
       }
       
   }
   
   @FXML
   public void clear()
   {
        tx1.setText("");
        tx2.setText("");
        tx3.setText("");
        label.setText("");
        img_green.setVisible(false);
        img_orange.setVisible(false);
        img_red.setVisible(false);
        img_blue.setVisible(false);
          
   }
   
   @FXML
   public void exit()
   {
       System.exit(0);
   }
   
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

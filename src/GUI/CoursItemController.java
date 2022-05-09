/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Cours;
import Entities.Salle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nouri
 */
public class CoursItemController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private VBox vboxfx;
    @FXML
    private Label heuredfx;
    @FXML
    private Label heureffx;
    @FXML
    private Label nomcoursfrontfx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
      public void setData(Cours t){
        
       nomcoursfrontfx.setText(t.getNom());
        heuredfx.setText(t.getHeure_d());
         heureffx.setText(t.getHeure_f());
       image.setImage(new Image(t.getImage()));
  
 
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Event;
import Entities.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.xml.ws.Action;

/**
 * FXML Controller class
 *
 * @author eyaba
 */
public class ThematiqueItemController  {

    private Label lbl_thematique;
    private ImageView image_thematique;
    private Label id_thematique;
    private Parent root;
    @FXML
    private ImageView image;
    @FXML
    private Label name;
    @FXML
    private Label prename;
    @FXML
    private VBox idE;
    @FXML
    private Label idEvent;
    @FXML
    private Pane pane;
    
       
    
    public void setData(Event t){
        
        name.setText(t.getNomEvent());
        image.setImage(new Image("file:\\C:\\xampp\\htdocs\\img\\"+t.getImageFile()));
        prename.setText(String.valueOf(t.getId()));
       

                pane.setOnMouseClicked((MouseEvent event)-> {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("EventDetail.fxml"));
                    root = fxmlLoader.load();
                    System.out.println("Hello World! "+t);

                    EventDetailController controller = fxmlLoader.getController();
                    
                    controller.setEventId(t.getId());
                    controller.loadData();
                    Stage window = (Stage) idEvent.getScene().getWindow();
                    window.setScene(new Scene(root));
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(ThematiqueItemController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        }
        );
    
        
    }
    
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
import Entities.Salle;
import Entities.User;
import Services.ProduitService;
import Services.SalleService;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.xml.ws.Action;
import static energym.desktop.MainFX.UserconnectedC;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author eyaba
 */
public class ProduitItemController {
    
    @FXML
    private ImageView image;
    private Produit produit;
    @FXML
    private Rating rating;
    @FXML
    private Label nom;
    @FXML
    private Label prix;
    @FXML
    private Label qte;
    
    public void setData(Produit t) {
        produit = t;
        
        nom.setText(t.getNom());
        image.setImage(new Image("file:\\C:\\xampp\\htdocs\\img\\" + t.getImage()));
        prix.setText(String.valueOf(t.getPrix()) + "$");
        qte.setText(String.valueOf(t.getQuantité()));
        rating.setRating(t.getRating());
        rating.setOnMouseClicked((MouseEvent event) -> {
            ProduitService ps = new ProduitService();
            ps.modifierrating(t.getId(), (int) rating.getRating());
        }
        );
        
    }
}

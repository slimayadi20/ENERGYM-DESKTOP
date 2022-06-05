/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
import Entities.Salle;
import Entities.User;
import static GUI.PaiementController.showAlert;
import Services.PanierService;
import Services.ProduitService;
import Services.SalleService;
import energym.desktop.MainFX;
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
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
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
        image.setImage(new Image("file:D:\\Nouveau dossier\\SAUVGARDE\\ENERGYM\\public\\uploads\\produit\\" + t.getImage()));
        prix.setText(String.valueOf(t.getPrix()) + "$");
        qte.setText(String.valueOf(t.getQuantité()));
        rating.setRating(t.getRating());
        rating.setOnMouseClicked((MouseEvent event) -> {
            ProduitService ps = new ProduitService();
            ps.modifierrating(t.getId(), (int) rating.getRating());
        }
        );

    }

    @FXML
    private void addcarte(MouseEvent event) {
        PanierService ps = new PanierService();
        ps.ajouterPanier(produit.getId(), UserconnectedC.getId());
        showAlert(Alert.AlertType.INFORMATION, "Produit ajoutée au panier", "success", "Completez votre achat");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/Produit.fxml"));
        try {
            loader.load();
        } catch (Exception ex) {
            ex.getMessage();
        }
        ProduitController addLivraisonController = loader.getController();
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
        stage = (Stage) nom.getScene().getWindow();
        stage.close();

    }
}

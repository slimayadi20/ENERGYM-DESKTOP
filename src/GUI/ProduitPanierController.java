/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Panier;
import Entities.User;
import static GUI.PaiementController.showAlert;
import static GUI.PanierController.listTH;
import Services.PanierService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.xml.ws.Action;

/**
 * FXML Controller class
 *
 * @author karim
 */
public class ProduitPanierController {

    PanierService ps = new PanierService();
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
    private Label quantite;
    @FXML
    private Button minus;
    @FXML
    private Button plus;
    URL url;
    ResourceBundle rb;
    PanierController te = new PanierController();

    public void setData(Panier t) {

        name.setText(t.getProduit());
        image.setImage(new Image("file:\\C:\\xampp\\htdocs\\img\\" + t.getImage()));
        prename.setText(String.valueOf(t.getPrix()) + " DT");
        quantite.setText(String.valueOf(t.getQuantite()));

        plus.setOnMouseClicked((MouseEvent event) -> {

            try {
                ps.ajouterPanier(t.getIdproduit(), 37);
                int a = ps.getInstance().getQT(37, t.getIdproduit());
                if (a >= ps.getInstance().getStock(t.getIdproduit())) {
                    showAlert(Alert.AlertType.INFORMATION, "Opération Invalide", "error", "Stock insuffisant");
                } else {
                    quantite.setText(String.valueOf(a));
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/GUI/Panier.fxml"));
                    try {
                        loader.load();
                    } catch (Exception ex) {
                        ex.getMessage();
                    }

                    PanierController addLivraisonController = loader.getController();

                    Parent parent = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(parent));
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();
                    stage = (Stage) plus.getScene().getWindow();
                   stage.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        });
        minus.setOnMouseClicked((MouseEvent event) -> {

            try {
                ps.deletePanier(t.getIdproduit(), 37);

                //te.setTest(true);
                if (t.getQuantite()>1)
                {
                int a = ps.getInstance().getQT(37, t.getIdproduit());
                System.out.println(a);
                quantite.setText(String.valueOf(a));
                }
                   FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/GUI/Panier.fxml"));
                    try {
                        loader.load();
                    } catch (Exception ex) {
                        ex.getMessage();
                    }

                  PanierController addLivraisonController = loader.getController();

                    Parent parent = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(parent));
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();
                    stage = (Stage) plus.getScene().getWindow();
                   stage.close();

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        });

    }

}

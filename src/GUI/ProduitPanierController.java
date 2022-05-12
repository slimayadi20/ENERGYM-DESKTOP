/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Panier;
import Entities.User;
import static GUI.PanierController.listTH;
import Services.PanierService;
import energym.desktop.MainFX;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
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

    public static int qt = 0;

    public void setData(Panier t) {

        name.setText(t.getProduit());
        image.setImage(new Image("file:\\C:\\xampp\\htdocs\\img\\" + t.getImage()));
        prename.setText(String.valueOf(t.getPrix()) + " DT");
        quantite.setText(String.valueOf(t.getQuantite()));

        plus.setOnMouseClicked((MouseEvent event) -> {

            try {
                ps.ajouterPanier(t.getIdproduit(), MainFX.UserconnectedC.getId());
                int a = ps.getInstance().getQT(MainFX.UserconnectedC.getId(), t.getIdproduit());
                if (a >= ps.getInstance().getStock(t.getIdproduit())) {
                } else {
                    quantite.setText(String.valueOf(a));
                    qt = a;
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/GUI/Panier.fxml"));
                    try {
                        loader.load();
                    } catch (Exception ex) {
                        ex.getMessage();
                    }
                    PanierController model = new PanierController();
                    model.setTotal(new Label("hhohohohoho"));

                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        });
        minus.setOnMouseClicked((MouseEvent event) -> {

            try {
                ps.deletePanier(t.getIdproduit(), MainFX.UserconnectedC.getId());

                //te.setTest(true);
                int a = ps.getInstance().getQT(MainFX.UserconnectedC.getId(), t.getIdproduit());
                System.out.println(a);
                quantite.setText(String.valueOf(a));
                if (a == 0) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/GUI/Panier.fxml"));
                    try {
                        loader.load();
                    } catch (Exception ex) {
                        ex.getMessage();
                    }
                    PanierController te = loader.getController();
                    Platform.runLater(() -> {
                        try {
                            te.displayGrid();
                        } catch (IOException ex) {
                            Logger.getLogger(ProduitPanierController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        });

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Salle;
import Services.SalleService;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class SalleController implements Initializable {

    List<Salle> liste = new ArrayList<Salle>();
    SalleService us = new SalleService();
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane grid;
    @FXML
    private Button boutonimc;
    @FXML
    private HBox hboxnavbar;
    @FXML
    private Label sallefxid;
    @FXML
    private Label eventfxid;
    @FXML
    private Label produitfxid;
    @FXML
    private Label reclamationfxid;
    @FXML
    private JFXToggleButton btnEditMode;
    @FXML
    private AnchorPane mainpane;
    @FXML
    private Label articlefxid;
    @FXML
    private Button namefxid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SalleService ths = new SalleService();
        List<Salle> listTH = ths.afficher();

        int colonne = 0;
        int row = 1;
        try {
            for (Salle t : listTH) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("SalleItem.fxml"));

                Pane anchorPane = fxmlLoader.load();

                SalleItemController controller = fxmlLoader.getController();
                controller.setData(t);
                if (colonne == 3) {
                    colonne = 0;
                    row++;
                }
                grid.add(anchorPane, colonne++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(12));
                //break;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        mainpane.setOpacity(0);
        makeFadeInTransition();

    }
   private void makeFadeInTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(mainpane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

    }

    private void makeFadeInTransition(String a) {

        try {
            Stage stage = (Stage) mainpane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(a));/* Exception */
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void boutomimc(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/imc_omar.fxml"));
        try {
            loader.load();
        } catch (Exception ex) {
            ex.getMessage();
        }

        Imc_omarController Imc_omarController = loader.getController();

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    private void reclamation(MouseEvent event) throws IOException {
        makeFadeInTransition("ReclamationFront.fxml");

    }

    @FXML
    private void event(MouseEvent event) throws IOException {
        makeFadeInTransition("Evenement.fxml");

    }

    @FXML
    private void home(MouseEvent event) {
        makeFadeInTransition("HomeFront.fxml");

    }

    @FXML
    private void salle(MouseEvent event) {
        makeFadeInTransition("Salle.fxml");

    }

    @FXML
    private void produit(MouseEvent event) {
        makeFadeInTransition("Produit.fxml");

    }

    @FXML
    private void profile(MouseEvent event) {
        makeFadeInTransition("ProfileFront.fxml");

    }

    private void makeFadeOut(String a) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(mainpane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished((event) -> {
            try {
                Stage stage = (Stage) mainpane.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource(a));/* Exception */
                Scene scene = new Scene(root);
                stage.setScene(scene);

                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        fadeTransition.play();
    }

    @FXML
    private void btnEditModeToggle(MouseEvent event) {
    }

    @FXML
    private void article(MouseEvent event) {
                        makeFadeInTransition("Article.fxml");

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Article;
import Entities.Salle;
import Services.ArticleService;
import Services.SalleService;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ArticleController implements Initializable {

    @FXML
    private AnchorPane mainpane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane grid;
    @FXML
    private Label titlefxid;
    @FXML
    private HBox hboxsport;
    @FXML
    private HBox hboxnavbar;
    @FXML
    private Label articlefxid;
    @FXML
    private Label sallefxid;
    @FXML
    private Label eventfxid;
    @FXML
    private Label produitfxid;
    @FXML
    private Label reclamationfxid;
    @FXML
    private Button namefxid;
    @FXML
    private JFXToggleButton btnEditMode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArticleService ths = new ArticleService();
        List<Article> listTH = ths.afficher();

        int colonne = 0;
        int row = 1;
        try {
            for (Article t : listTH) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ArticleItem.fxml"));

                Pane anchorPane = fxmlLoader.load();

                ArticleItemController controller = fxmlLoader.getController();
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
    private void reclamation(MouseEvent event) throws IOException {
        makeFadeInTransition("ReclamationFront");

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

    @FXML
    private void btnEditModeToggle(MouseEvent event) {
    }

    @FXML
    private void article(MouseEvent event) {
        makeFadeInTransition("Article.fxml");

    }

}

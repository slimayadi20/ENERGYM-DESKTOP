/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Article;
import Entities.Salle;
import static GUI.HomeFrontController.status;
import GUIBACK.ProfileController;
import Services.ArticleService;
import Services.PanierService;
import Services.SalleService;
import com.jfoenix.controls.JFXToggleButton;
import energym.desktop.MainFX;
import static energym.desktop.MainFX.UserconnectedC;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
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
    @FXML
    private Circle circle;
    @FXML
    private Label badge;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         PanierService phs = new PanierService();
        badge.setText(String.valueOf(phs.getInstance().getCount(MainFX.UserconnectedC.getId())));

        namefxid.setText(UserconnectedC.getNom());
        File file = new File("D:\\Nouveau dossier\\SAUVGARDE\\ENERGYM\\public\\uploads\\user\\"  + UserconnectedC.getImageFile());

        try {
            circle.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        if (status == true) {
            btnEditMode.setText("switch to dark mode");
            mainpane.setStyle("-fx-background-color: #FFFFFF;");
            btnEditMode.setText("switch to dark mode");
            btnEditMode.setStyle("-fx-text-fill: #000000");
            hboxnavbar.setStyle("-fx-background-color: #FFFFFF;");
            hboxsport.setStyle("-fx-background-color: #000000;");
            sallefxid.setStyle("-fx-text-fill:#000000;");
            eventfxid.setStyle("-fx-text-fill:#000000;");
            produitfxid.setStyle("-fx-text-fill: #000000;");
            reclamationfxid.setStyle("-fx-text-fill: #000000;");
            titlefxid.setStyle("-fx-text-fill: #000000;");
            namefxid.setStyle("-fx-background-color: #FFFFFF;");
            grid.setStyle("-fx-background-color: #FFFFFF;");
            scrollPane.setStyle("-fx-background-color: #FFFFFF;");
            articlefxid.setStyle("-fx-text-fill: #000000;");

            //  namefxid.setStyle("-fx-text-fill: #000000;");
        }
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

    @FXML
    private void btnEditModeToggle(MouseEvent event) {
        if (btnEditMode.isSelected()) {
            status = true;
            btnEditMode.setText("switch to dark mode");
            mainpane.setStyle("-fx-background-color: #FFFFFF;");
            btnEditMode.setText("switch to dark mode");
            btnEditMode.setStyle("-fx-text-fill: #000000");
            hboxnavbar.setStyle("-fx-background-color: #FFFFFF;");
            hboxsport.setStyle("-fx-background-color: #FFFFFF;");
            sallefxid.setStyle("-fx-text-fill:#000000;");
            eventfxid.setStyle("-fx-text-fill:#000000;");
            produitfxid.setStyle("-fx-text-fill: #000000;");
            reclamationfxid.setStyle("-fx-text-fill: #000000;");
            titlefxid.setStyle("-fx-text-fill: #000000;");
            namefxid.setStyle("-fx-background-color: #FFFFFF;");
            grid.setStyle("-fx-background-color: #FFFFFF;");
            scrollPane.setStyle("-fx-background-color: #FFFFFF;");
            articlefxid.setStyle("-fx-text-fill: #000000;");

        } else {
            try {
                status = false;
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("Article.fxml"));/* Exception */
                Scene scene = new Scene(root);
                stage.setScene(scene);

                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void article(MouseEvent event) {
        makeFadeInTransition("Article.fxml");

    }

}

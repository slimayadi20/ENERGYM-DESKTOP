/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Article;
import Entities.Commentaire;
import Entities.Event;
import GUIBACK.ProfileController;
import Services.ArticleService;
import Services.CommentaireService;
import Services.EventService;
import Services.PartageFb;
import com.jfoenix.controls.JFXToggleButton;
import energym.desktop.MainFX;
import static energym.desktop.MainFX.UserconnectedC;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
public class ArticleDetailController implements Initializable {

    @FXML
    private AnchorPane mainpane;
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
    private ImageView image;
    @FXML
    private TextField txt_titre;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane grid;
    int Articleid;
    @FXML
    private Label datefxid;
    @FXML
    private Label descriptionfxid;
    Article e;
    @FXML
    private Circle circle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        namefxid.setText(UserconnectedC.getNom());
        File file = new File("D:\\Nouveau dossier\\SAUVGARDE\\ENERGYM\\public\\uploads\\user\\"  + UserconnectedC.getImageFile());

        try {
            circle.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadData();
        mainpane.setOpacity(0);
        makeFadeInTransition();
    }

    public void setArticleId(int id) {
        this.Articleid = id;
    }

    public void loadData() {
        //System.out.println("initData"+thematique_id);
        ArticleService ths = new ArticleService();
        e = ths.afficherdetail(Articleid);
        titlefxid.setText("titre : " + e.getTitre());
        descriptionfxid.setText("description : " + e.getContenu());
        image.setImage(new Image("file:D:\\Nouveau dossier\\SAUVGARDE\\ENERGYM\\public\\uploads\\posts\\" + e.getImageFile()));
        datefxid.setText("date de creation : " + String.valueOf(e.getCreated_at()));
        CommentaireService cs = new CommentaireService();
        List<Commentaire> listTH = cs.afficherCommentaireByarticle(Articleid);

        int colonne = 0;
        int row = 1;
        try {
            for (Commentaire t : listTH) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("CommentaireItem.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                CommentaireItemController controller = fxmlLoader.getController();
                controller.setData(t);

                grid.add(anchorPane, colonne, row++);
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
    }

    @FXML
    private void article(MouseEvent event) {
        makeFadeInTransition("Article.fxml");

    }

    @FXML
    private void btnAjouterAction(ActionEvent event) {

        if (txt_titre.getText() == "") {

        } else {
            if (txt_titre.getText().length() > 100) {

            } else {
                CommentaireService cs = new CommentaireService();
                java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
                Commentaire d = new Commentaire(txt_titre.getText(), date, MainFX.UserconnectedC.getId(), e.getId());
                cs.ajouter(d);
                loadData();
            }
        }
    }

    @FXML
    private void partager(MouseEvent event) {
        PartageFb fb = new PartageFb();
        try {

            fb.partager(e.getContenu(), "file:C:\\xampp\\htdocs\\img\\05f083303e10cb496827cb6628fdd3e5 (1).jpg");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArticleDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

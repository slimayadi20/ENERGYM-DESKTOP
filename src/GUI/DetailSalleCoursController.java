/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Salle;
import static GUI.PaiementController.showAlert;
import GUIBACK.ProfileController;
import GUIBACK.WebViewCaptureMap;
import Services.SalleService;
import com.jfoenix.controls.JFXToggleButton;
import static energym.desktop.MainFX.UserconnectedC;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author nouri
 */
public class DetailSalleCoursController implements Initializable {

    @FXML
    private ImageView imagefrontfx;
    @FXML
    private Label mailfrontfx;
    @FXML
    private Label descriptionfrontfx;
    @FXML
    private Label adressefrontfx;
    @FXML
    private Label telfrontfx;
    @FXML
    private Label prix1frontfx;
    @FXML
    private Label prix2frontfx;
    @FXML
    private Label prix3frontfx;
    @FXML
    private Label heureofrontfx;
    @FXML
    private Label heureffrontfx;
    @FXML
    private Button voirplanning;
    private Parent root;
    @FXML
    private Label salleidd;
    int salleid;
    @FXML
    private Label nomfrontfx;
    @FXML
    private Button x;
    @FXML
    private Button voirlocation;
    private WebView webView;
    private Pane paneMapa;
    @FXML
    private AnchorPane mainpane;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        namefxid.setText(UserconnectedC.getNom());
        File file = new File("D:\\Nouveau dossier\\SAUVGARDE\\ENERGYM\\public\\uploads\\user\\" + UserconnectedC.getImageFile());

        try {
            circle.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadData();
        mainpane.setOpacity(0);
        makeFadeInTransition();

    }

    public void setThematiqueId(int id) {
        this.salleid = id;

    }

    public void loadData() {
        //System.out.println("initData"+thematique_id);
        SalleService ths = new SalleService();
        Salle listTH = ths.findById(salleid);

        int colonne = 0;
        int row = 1;
        nomfrontfx.setText(listTH.getNom());
        prix1frontfx.setText(listTH.getPrix1());
        prix2frontfx.setText(listTH.getPrix2());
        prix3frontfx.setText(listTH.getPrix3());
        descriptionfrontfx.setText(listTH.getDescription());
        heureofrontfx.setText(listTH.getHeuref());
        heureffrontfx.setText(listTH.getHeureo());
        telfrontfx.setText(listTH.getTel());
        mailfrontfx.setText(listTH.getMail());
        adressefrontfx.setText(listTH.getAdresse());
        imagefrontfx.setImage(new Image("file:D:\\Nouveau dossier\\SAUVGARDE\\ENERGYM\\public\\uploads\\salle\\" + listTH.getImage()));
        voirplanning.setOnMouseClicked((MouseEvent event) -> {
//**************************

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("detailCours.fxml"));
                root = fxmlLoader.load();

                DetailCoursController controller = fxmlLoader.getController();

                controller.setThematiqueId(listTH.getId());
                controller.loadData();
                Stage window = (Stage) salleidd.getScene().getWindow();
                window.setScene(new Scene(root));

            } catch (IOException ex) {
                Logger.getLogger(SalleItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        x.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        try {
                            Desktop.getDesktop().browse(new URI(listTH.getUrl()));
                        } catch (URISyntaxException ex) {
                            Logger.getLogger(DetailSalleCoursController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (IOException e1) {
                        System.out.println("error");
                    }
                }
            }

        });
        voirlocation.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {

                WebViewCaptureMap w = new WebViewCaptureMap();
                Stage stage = new Stage();
                try {
                    w.start(stage);
                } catch (Exception ex) {
                    Logger.getLogger(DetailSalleCoursController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

    }

    private void makeFadeInTransition(String a) {

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

    public void map(Salle actividad) {
        paneMapa = new Pane();
        webView = new WebView();
        paneMapa.setMinSize(800, 600);

        Stage escenaMapa = new Stage();
        //    Image icono = new Image(this.getClass().getResource("/src/images/icons8_Person_32px.png").toString());
        WebEngine webEngine = webView.getEngine();
        System.out.println(actividad.getAdresse());
        webEngine.load("36.80723, 10.1779");
        //    escenaMapa.getIcons().setAll(icono);
        escenaMapa.setScene(new Scene(paneMapa));
        escenaMapa.setTitle("Ubicación " + actividad.getNom());
        paneMapa.getChildren().setAll(webView);

        escenaMapa.showAndWait();

    }

    private void makeFadeInTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(mainpane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

    }

    private void makeFadeOut(String a) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(500));
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
    private void article(MouseEvent event) {
        makeFadeInTransition("Article.fxml");

    }

    @FXML
    private void btnEditModeToggle(MouseEvent event) {
    }

    @FXML
    private void gold(MouseEvent event) {
        SalleService ss = new SalleService();

        if (ss.check(salleid, UserconnectedC.getId())) {
            showAlert(Alert.AlertType.INFORMATION, "vous etes deja inscrits", "error", "vous etes deja inscrits");

        } else {
            Salle listTH = ss.findById(salleid);
            ss.Participer(listTH, UserconnectedC);
            showAlert(Alert.AlertType.INFORMATION, "Inscription ajoutée avec succes", "success", "Bienvenue chez nous");
        }

    }

    @FXML
    private void silver(MouseEvent event) {
           SalleService ss = new SalleService();

        if (ss.check(salleid, UserconnectedC.getId())) {
            showAlert(Alert.AlertType.INFORMATION, "vous etes deja inscrits", "error", "vous etes deja inscrits");

        } else {
            Salle listTH = ss.findById(salleid);
            ss.Participer(listTH, UserconnectedC);
            showAlert(Alert.AlertType.INFORMATION, "Inscription ajoutée avec succes", "success", "Bienvenue chez nous");
        }
    }

    @FXML
    private void bronze(MouseEvent event) {
     SalleService ss = new SalleService();

        if (ss.check(salleid, UserconnectedC.getId())) {
            showAlert(Alert.AlertType.INFORMATION, "vous etes deja inscrits", "error", "vous etes deja inscrits");

        } else {
            Salle listTH = ss.findById(salleid);
            ss.Participer(listTH, UserconnectedC);
            showAlert(Alert.AlertType.INFORMATION, "Inscription ajoutée avec succes", "success", "Bienvenue chez nous");
        }
    }
}

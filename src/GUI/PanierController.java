/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Panier;
import Entities.User;
import static GUI.PaiementController.showAlert;
import GUIBACK.ProfileController;
import Quiz.Controllers.MainController;
import Services.PanierService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import energym.desktop.MainFX;
import static energym.desktop.MainFX.UserconnectedC;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class PanierController implements Initializable {

    List<User> liste = new ArrayList<User>();
    PanierService us = new PanierService();
    @FXML
    private ScrollPane scrollPane;
    @FXML
    public GridPane grid;
    @FXML
    public Label count;
    @FXML
    private Label total;
    URL url;
    ResourceBundle rb;
    public static List<Panier> listTH;
    Panier listTHH = null;
    @FXML
    private TextField codeTF;
    @FXML
    private JFXButton codeButton;
    @FXML
    private Label reduction;
    @FXML
    private Label erreur;
    @FXML
    public AnchorPane DashboardUtilis;
    public static boolean test = false;
    public int reduc = 0;
    public int c = 0;
    public int global_variable = 0;
    @FXML
    private JFXButton vider;
    @FXML
    private Label quiz;
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
    private Circle circle;
    @FXML
    private Button namefxid;
    @FXML
    private JFXToggleButton btnEditMode;
    @FXML
    private JFXButton a;
    @FXML
    private Label badge;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PanierService phs = new PanierService();
        badge.setText(String.valueOf(phs.getInstance().getCount(MainFX.UserconnectedC.getId())));

        namefxid.setText(UserconnectedC.getNom());
        File file = new File("D:\\Nouveau dossier\\SAUVGARDE\\ENERGYM\\public\\uploads\\user\\" + UserconnectedC.getImageFile());

        try {
            circle.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        PanierService ths = new PanierService();
        listTH = PanierService.getInstance().panier(MainFX.UserconnectedC.getId());
        int tt = 0;
        int colonne = 0;
        int row = 1;
        reduc = 0;
        try {
            for (Panier t : listTH) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ProduitPanier.fxml"));

                Pane anchorPane = fxmlLoader.load();

                ProduitPanierController controller = fxmlLoader.getController();
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
                tt += t.getPrix() * ths.getInstance().getQT(MainFX.UserconnectedC.getId(), t.getIdproduit());
                global_variable = tt;
                count.setText(String.valueOf(ths.getInstance().getCount(MainFX.UserconnectedC.getId())) + " Items");
                total.setText(String.valueOf(tt) + " DT");

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        DashboardUtilis.setOpacity(0);
        makeFadeInTransition();
    }

    private void makeFadeInTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(DashboardUtilis);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

    }

    private void makeFadeInTransition(String a) {

        try {
            Stage stage = (Stage) DashboardUtilis.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(a));/* Exception */
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void displayGrid() {
        AnchorPane panee;
        try {
            panee = FXMLLoader.load(getClass().getResource("Panier.fxml"));
            DashboardUtilis.getChildren().setAll(panee);
        } catch (IOException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean isTest() {
        return test;
    }

    public static void setTest(boolean test) {
        PanierController.test = test;
    }

    private void handleHome(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

    }

    private void handleProfile(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ProfileFront.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

    }

    @FXML
    private void ajouter_commande(MouseEvent event) {
        int somme = us.getCount(MainFX.UserconnectedC.getId());

        if (somme > 0) {

            AnchorPane panee;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GUI/Paiement.fxml"));
            try {
                loader.load();
            } catch (Exception ex) {
                ex.getMessage();
            }
            PaiementController addLivraisonController = loader.getController();
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage = (Stage) a.getScene().getWindow();
            stage.close();
            /*   panee = FXMLLoader.load(getClass().getResource("Paiement.fxml"));
            DashboardUtilis.getChildren().setAll(panee);*/
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Veuillez remplir votre panier", "error", "Veuillez remplir votre panier");
        }

    }

// 4242 4242 4242 4242
    @FXML
    private void ajoutcode(MouseEvent event) {
        PanierService ths = new PanierService();

        c = ths.getPromo(codeTF.getText());
        reduc = global_variable - ((global_variable * c) / 100);

        if (ths.getPromo(codeTF.getText()) == 0) {
            total.setText(String.valueOf(global_variable) + " DT");
            erreur.setText("Code invalide");
            reduction.setText("0 %");

        } else {
            reduction.setText(String.valueOf(ths.getPromo(codeTF.getText())) + " %");
            total.setText(String.valueOf(reduc) + " DT");
            System.out.println(c);
            erreur.setText("");

        }
    }

    @FXML
    private void vider_panier(MouseEvent event) {
        PanierService ths = new PanierService();
        ths.supprimerPanierbyuser(MainFX.UserconnectedC.getId());
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
        stage = (Stage) vider.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void quiz(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/QuizStyle/MainView.fxml"));
        try {
            loader.load();
        } catch (Exception ex) {
            ex.getMessage();
        }

        MainController addLivraisonController = loader.getController();

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();

    }

    @FXML
    private void home(MouseEvent event) throws IOException {
        makeFadeInTransition("HomeFront.fxml");

    }

    @FXML
    private void event(MouseEvent event) throws IOException {
        makeFadeInTransition("Evenement.fxml");
    }

    @FXML
    private void salle(MouseEvent event) {
        makeFadeInTransition("Salle.fxml");
    }

    @FXML
    private void produit(MouseEvent event) throws IOException {
        makeFadeInTransition("Produit.fxml");
    }

    @FXML
    private void reclamation(MouseEvent event) {
        makeFadeInTransition("ReclamationFront.fxml");
    }

    @FXML
    private void profile(MouseEvent event) {
    }

    @FXML
    private void article(MouseEvent event) {
        makeFadeInTransition("Article.fxml");

    }

    @FXML
    private void panier(MouseEvent event) {
        makeFadeInTransition("Panier.fxml");

    }

    @FXML
    private void btnEditModeToggle(MouseEvent event) {
    }

}

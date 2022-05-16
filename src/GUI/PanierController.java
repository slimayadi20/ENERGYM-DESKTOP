/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Panier;
import Entities.User;
import static GUI.PaiementController.showAlert;
import Services.PanierService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

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
    @FXML
    private Label reclamation;
    public int reduc = 0;
    public int c = 0;
    public int global_variable = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PanierService ths = new PanierService();
        listTH = PanierService.getInstance().panier(37);
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
                tt += t.getPrix() * ths.getInstance().getQT(37, t.getIdproduit());
                global_variable= tt ; 
                count.setText(String.valueOf(ths.getInstance().getCount(37)) + " Items");
                total.setText(String.valueOf(tt) + " DT");

               
            }
        } catch (IOException ex) {
            ex.printStackTrace();
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
        int somme = us.getCount(37);

        if (somme > 0) {

            AnchorPane panee;
            try {
                panee = FXMLLoader.load(getClass().getResource("Paiement.fxml"));
                DashboardUtilis.getChildren().setAll(panee);

            } catch (IOException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            showAlert(Alert.AlertType.INFORMATION, "Veuillez remplir votre panier", "error", "Veuillez remplir votre panier");
        }

    }

// 4242 4242 4242 4242
    @FXML
    private void reclamation(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

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
}

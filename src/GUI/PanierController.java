/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Panier;
import Entities.User;
import Services.PanierService;
import com.jfoenix.controls.JFXButton;
import energym.desktop.MainFX;
import static energym.desktop.MainFX.UserconnectedC;
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
    public Label total;
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
    public static int tt = 0;
    public static boolean test = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PanierService ths = new PanierService();

        listTH = getData();
        total.setText(String.valueOf(ProduitPanierController.qt));
        int colonne = 0;
        int row = 1;
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
                count.setText(String.valueOf(ths.getInstance().getCount(MainFX.UserconnectedC.getId())) + " Items");
                total.setText(String.valueOf(tt) + " DT");
                codeButton.setOnMouseClicked((MouseEvent event) -> {
                    int red = 0;
                    try {

                        if (ths.getPromo(codeTF.getText()) == 0) {
                            erreur.setText("Code invalide");
                            reduction.setText("0 %");

                        } else {

                            reduction.setText(String.valueOf(ths.getPromo(codeTF.getText())) + " %");
                            erreur.setText("");

                        }

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                });
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private List<Panier> getData() {
        PanierService ldao = new PanierService();
        return ldao.panier(UserconnectedC.getId());

    }

    public void setTotal(Label total) {
        this.total = total;
    }

    public Label getTotal() {
        return total;
    }

    public void displayGrid() throws IOException {

        System.out.println("okok");
        PanierService ths = new PanierService();
        total.setText(Integer.toString(12));
        System.out.println(UserconnectedC.getId());
        listTH = PanierService.getInstance().panier(MainFX.UserconnectedC.getId());

        int colonne = 0;
        int row = 1;
        try {
            for (Panier t : listTH) {
                System.out.println("total updated");
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
                count.setText(String.valueOf(ths.getInstance().getCount(MainFX.UserconnectedC.getId())) + " Items");
                total.setText(String.valueOf(tt) + " DT");
                codeButton.setOnMouseClicked((MouseEvent event) -> {
                    int red = 0;
                    try {

                        if (ths.getPromo(codeTF.getText()) == 0) {
                            erreur.setText("Code invalide");
                            reduction.setText("0 %");

                        } else {

                            reduction.setText(String.valueOf(ths.getPromo(codeTF.getText())) + " %");
                            erreur.setText("");

                        }

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                });
            }
        } catch (IOException ex) {
            ex.printStackTrace();
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
            try {
                panee = FXMLLoader.load(getClass().getResource("Paiement.fxml"));
                DashboardUtilis.getChildren().setAll(panee);

            } catch (IOException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
        }

    }

// 4242 4242 4242 4242
}

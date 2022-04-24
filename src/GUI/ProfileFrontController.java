/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ProfileFrontController implements Initializable {

    List<User> liste = new ArrayList<User>();
    UserService us = new UserService();
    @FXML
    private Label home_id;
    @FXML
    private VBox affich_produit;
    @FXML
    private HBox home;
    @FXML
    private HBox salle;
    @FXML
    private HBox evenement;
    @FXML
    private HBox produit;
    @FXML
    private HBox panier;
    @FXML
    private HBox blog;
    @FXML
    private HBox reclamation;
    @FXML
    private HBox profile;
    @FXML
    private HBox signout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        Font.loadFont(getClass().getResourceAsStream("/css/SourceSansPro-Regular.ttf"), 14);
        //setVBOXP();
    
    }

    public ImageView setImage(String nom) {
        ImageView image = new ImageView();
        File file = new File("C:\\xampp\\htdocs\\img\\" + nom);
        Image imageForFile = null;
        try {
            System.out.println(file.toURI().toURL());
            imageForFile = new Image(file.toURI().toURL().toExternalForm());
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProfileFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

        image.setImage(imageForFile);
        return image;
    }

    @FXML
    private void handlehome(MouseEvent event) throws IOException {
            Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
         String bstyle=String.format("-fx-background-radius: %s;-fx-background-color: %s;", " 500050 " ,"#F4F4FC ");
  home.setStyle(bstyle);
    }

    @FXML
    private void handleHome(MouseEvent event) throws IOException {
             Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }
    @FXML
    private void handleClicks(MouseEvent event) {
    }

    @FXML
    private void handleSalle(MouseEvent event) {
    }

    @FXML
    private void handleEvenement(MouseEvent event) {
    }

    @FXML
    private void HandleProduit(MouseEvent event) {
    }

    @FXML
    private void handlePanier(MouseEvent event) {
    }

    @FXML
    private void handleBlog(MouseEvent event) {
    }

    @FXML
    private void handleReclamation(MouseEvent event) {
    }

    @FXML
    private void handleProfile(MouseEvent event) {
    }

    @FXML
    private void handlesignout(MouseEvent event) {
    }

 
}

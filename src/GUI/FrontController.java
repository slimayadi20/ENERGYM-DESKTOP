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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class FrontController implements Initializable {

    List<User> liste = new ArrayList<User>();
    UserService us = new UserService();
    @FXML
    private Label home_id;
    @FXML
    private VBox affich_produit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Font.loadFont(getClass().getResourceAsStream("/css/SourceSansPro-Regular.ttf"), 14);
        //setVBOXP();
        HBox item = new HBox();
        affich_produit.getChildren().add(item);
        liste = us.afficher();
        int taille = liste.size();
        for (int i = 0; i < taille; i++) {

            try {
                if (i % 4 == 0) {
                    item = new HBox();
                    affich_produit.getChildren().add(item);
                }
                VBox content = new VBox();
                //    image.setImage(new Image("file:\\\\\\C:\\Users\\eyaba\\Desktop\\ImageMiddlefeast\\"+col_image.getCellData(index).toString()));

                Image image = new Image(new FileInputStream("C:\\xampp\\htdocs\\img\\" + liste.get(i).getImageFile()));
                System.out.println(liste.get(i).getImageFile());
                ImageView imageView = new ImageView(image);
                Label title = new Label();
                Label description = new Label((liste.get(i).getNom()));
                description.setStyle("-fx-strikethrough: true");
                description.getStyleClass().add("barre");
                Label prixpromo = new Label(liste.get(i).getEmail());
                prixpromo.setStyle("-fx-font-weight: bold");
                imageView.setFitHeight(150);
                imageView.setFitWidth(200);
                content.getChildren().addAll(imageView, title, prixpromo, description);
                Button btn = new Button("", content);
                //   User o1 = new User(o.getId_produit(), o.getNom_produit(),o.getDescription_produit(),o.getImage_name(),o.getQuantite_produit(),o.getPrix_produit());
                /*  btn.setOnAction(event -> {
                  try {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/DetailproduitsController.fxml"));
                  /* ImageController dp=loader.getController();
                  dp.setLb_idPatient(o.getId());
                  Scene scene = new Scene(loader.load());
                  
                  Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                  
                  stage.setScene(scene);
                  
                  stage.show();
                  DetailproduitsController controller = loader.<DetailproduitsController>getController();
                  
                  controller.initData(o1);
                  
                  
                  
                  } catch (IOException ex) {
                  Logger.getLogger(ProduitsFGUIController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  
                  });*/
                btn.setPrefWidth(200);
                item.getChildren().add(btn);
                affich_produit.setSpacing(50);
                item.setSpacing(20);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ImageView setImage(String nom) {
        ImageView image = new ImageView();
        File file = new File("C:\\xampp\\htdocs\\img\\" + nom);
        Image imageForFile = null;
        try {
            System.out.println(file.toURI().toURL());
            imageForFile = new Image(file.toURI().toURL().toExternalForm());
        } catch (MalformedURLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

        image.setImage(imageForFile);
        return image;
    }

 
}

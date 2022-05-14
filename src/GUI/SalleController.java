/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
import Entities.Salle;
import static GUI.HomeFrontController.status;
import GUIBACK.ProfileController;
import Services.SalleService;
import com.jfoenix.controls.JFXToggleButton;
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
import java.util.stream.Collectors;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
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
    @FXML
    private Label salle;
    @FXML
    private HBox hbox;
    @FXML
    private Circle circle;
    @FXML
    private TextField Searchp;
    private List<Salle> allEvenements = new ArrayList();
    @FXML
    private WebView web;
    String htLink = "http://www.google.com/search?q=";
    String adrsLink;
    WebEngine engine;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        engine = web.getEngine();
        engine.load(htLink + "www.google.com");
        namefxid.setText(UserconnectedC.getNom());
        File file = new File("D:\\Nouveau dossier\\SAUVGARDE\\ENERGYM\\public\\uploads\\user\\"  + UserconnectedC.getImageFile());
        try {
            circle.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SalleService ths = new SalleService();
        this.allEvenements = ths.afficher();
        
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
        if (status == true) {
            btnEditMode.setText("switch to dark mode");
            mainpane.setStyle("-fx-background-color: #FFFFFF;");
            btnEditMode.setText("switch to dark mode");
            btnEditMode.setStyle("-fx-text-fill: #000000");
            hboxnavbar.setStyle("-fx-background-color: #FFFFFF;");
            hbox.setStyle("-fx-background-color: #000000;");
            sallefxid.setStyle("-fx-text-fill:#000000;");
            eventfxid.setStyle("-fx-text-fill:#000000;");
            produitfxid.setStyle("-fx-text-fill: #000000;");
            reclamationfxid.setStyle("-fx-text-fill: #000000;");
            salle.setStyle("-fx-text-fill: #000000;");
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
        if (btnEditMode.isSelected()) {
            status = true;
            btnEditMode.setText("switch to dark mode");
            mainpane.setStyle("-fx-background-color: #FFFFFF;");
            btnEditMode.setText("switch to dark mode");
            btnEditMode.setStyle("-fx-text-fill: #000000");
            hboxnavbar.setStyle("-fx-background-color: #FFFFFF;");
            hbox.setStyle("-fx-background-color: #000000;");
            sallefxid.setStyle("-fx-text-fill:#000000;");
            eventfxid.setStyle("-fx-text-fill:#000000;");
            produitfxid.setStyle("-fx-text-fill: #000000;");
            reclamationfxid.setStyle("-fx-text-fill: #000000;");
            salle.setStyle("-fx-text-fill: #000000;");
            namefxid.setStyle("-fx-background-color: #FFFFFF;");
            grid.setStyle("-fx-background-color: #FFFFFF;");
            scrollPane.setStyle("-fx-background-color: #FFFFFF;");
            articlefxid.setStyle("-fx-text-fill: #000000;");
            
        } else {
            try {
                status = false;
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("Salle.fxml"));/* Exception */
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
    
    @FXML
    private void search(KeyEvent event) {
        String word = Searchp.getText();
        List<Salle> searchList = this.allEvenements.stream()
                .filter((e) -> {
                    return e.getNom().toUpperCase().startsWith(word.toUpperCase());
                })
                .collect(Collectors.toList());
        this.grid.getChildren().clear();
        this.displayGrid(searchList);
    }
    
    private void displayGrid(List<Salle> listTH) {
        int colonne = 0;
        int row = 1;
        System.out.println(listTH);
        if (listTH.isEmpty()) {
            grid.setVisible(false);
            scrollPane.setVisible(false);
            adrsLink = Searchp.getText().toString();
            engine.load(htLink + adrsLink + "&source=hp&ei=JzR9YoC1OJiJxc8Pg-GV0Ao&iflsig=AJiK0e8AAAAAYn1CNy8zP1-B_LLltpyQ2n2PNsEkWiBS&ved=0ahUKEwjA5Nior9r3AhWYRPEDHYNwBaoQ4dUDCAc&uact=5&oq=fedi&gs_lcp=Cgdnd3Mtd2l6EAMyCwgAEIAEELEDEIMBMgUILhCABDIFCAAQgAQyCwguEIAEEMcBEK8BMgUIABCABDIFCC4QgAQyBQguEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEOg4IABCPARDqAhCMAxDlAjoOCC4QjwEQ6gIQjAMQ5QI6EQguEI8BENQCEOoCEIwDEOUCOhEILhCABBCxAxCDARDHARDRAzoICAAQsQMQgwE6CAgAEIAEELEDOg4ILhCABBCxAxDHARDRAzoRCC4QgAQQsQMQgwEQxwEQowI6CAguEIAEELEDOg4IABCABBCxAxCDARDJAzoFCAAQkgM6CwguEIAEELEDEIMBUKsEWPEIYJAMaAFwAHgAgAGcAYgB2QSSAQMwLjSYAQCgAQGwAQg&sclient=gws-wiz");
        }
        try {
            for (int i = 0; i < listTH.size(); i++) {
          /*      grid.setVisible(true);
            scrollPane.setVisible(true);
            web.setVisible(false);*/
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("SalleItem.fxml"));
                
                Pane anchorPane = fxmlLoader.load();
                
                SalleItemController controller = fxmlLoader.getController();
                controller.setData(listTH.get(i));
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
        
    }
    
}

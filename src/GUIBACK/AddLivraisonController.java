/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIBACK;

import Entities.Livraison;
import Services.CommandeService;
import Services.LivraisonService;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AddLivraisonController implements Initializable {

    float reclamationid;
    private TextArea contenufid;
    int id;
    String nomUser;
    private boolean update;
    CommandeService ces = new CommandeService();
    String query = null;
    int categoriesLivraisonid;
    private Button save;

    File selectedFile;
    private String path;
    @FXML
    private TextField tfNom;
    @FXML
    private DatePicker date_Debut;
    @FXML
    private Button btn_valider;
    @FXML
    private TextField etat;
    @FXML
    private TextField categorie;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        
        // TODO
    }

    private void getQuery(String nom, String nomlivreur,  LocalDate datee, String etat) {

        if (update == false) {
            CommandeService ces = new CommandeService();
            LivraisonService es = new LivraisonService();
            int c = ces.getbyid(nom);
            java.util.Date date = java.sql.Date.valueOf(datee);

            Livraison e = new Livraison( c, nomlivreur, (java.sql.Date) date,etat);
            es.ajouterLivraison(e);
        } else {
            LivraisonService es = new LivraisonService();
            CommandeService ces = new CommandeService();
            int c = ces.getbyid(nom);
            java.util.Date date = java.sql.Date.valueOf(datee);
            Livraison e = new Livraison(categoriesLivraisonid, c, nom, (java.sql.Date) date, etat);
            es.modifier(categoriesLivraisonid, e);
        }

    }

    void setTextField(int id, int commande, String nom, Date datee, String etatt) {

        categoriesLivraisonid = id;
        tfNom.setText(nom);
        etat.setText(etatt);
        CommandeService ces = new CommandeService();
        String c = ces.getbynom(commande);
        LocalDate localDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(datee));

        categorie.setText(c);
        date_Debut.setValue(localDate);

     //   File file = new File("C:\\xampp\\htdocs\\img\\" + image);
     //   imageLivraison.setImage(new Image("C:/xampp/htdocs/img/" + image));
      //  imageLivraison.setFitHeight(150);
      //  imageLivraison.setFitWidth(250);
        //img.setText(path);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    private void Ajouter_Livraison(ActionEvent event) {
        StringBuilder errors = new StringBuilder();

        if (tfNom.getText().trim().isEmpty()) {
            errors.append("- Please enter a name\n");
        }
      
        if (etat.getText().trim().isEmpty()) {
            errors.append("- Please enter a addresse\n");
        }
        if (categorie.getText().trim().isEmpty()) {
            errors.append("- Please enter a categorie\n");
        }
      
        /*   if (imageLivraison.getImage() == null) {
            errors.append("- Please enter a imageLivraison \n");
        }*/

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            String nom = tfNom.getText();
     
            String addr = etat.getText();
            String categ = categorie.getText();
            LocalDate datee = date_Debut.getValue();

            getQuery(categ, nom, datee, addr);
            tfNom.setText("");
            etat.setText("");
            categorie.setText("");
            date_Debut.setValue(null);
            // to close the window
            Stage stage = (Stage) btn_valider.getScene().getWindow();
            stage.close();
        }
    }

   

    @FXML
    private void Categorie(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUIBACK/Commande.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
        //stage.close();  
        CommandeController ct = new CommandeController();
        //System.out.println(ct.getCat());
        categorie.setText(ct.getCat());
    }

    @FXML
    private void Ajouter_Event(ActionEvent event) {
            StringBuilder errors = new StringBuilder();

        if (tfNom.getText().trim().isEmpty()) {
            errors.append("- Please enter a name\n");
        }
      
        if (etat.getText().trim().isEmpty()) {
            errors.append("- Please enter a addresse\n");
        }
        if (categorie.getText().trim().isEmpty()) {
            errors.append("- Please enter a categorie\n");
        }
      
        /*   if (imageLivraison.getImage() == null) {
            errors.append("- Please enter a imageLivraison \n");
        }*/

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            String nom = tfNom.getText();
     
            String addr = etat.getText();
            String categ = categorie.getText();
            LocalDate datee = date_Debut.getValue();

            getQuery(categ, nom, datee, addr);
            tfNom.setText("");
            etat.setText("");
            categorie.setText("");
            date_Debut.setValue(null);
            // to close the window
            Stage stage = (Stage) btn_valider.getScene().getWindow();
            stage.close();
        }
    }


}

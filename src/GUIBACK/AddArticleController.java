/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIBACK;

import Entities.Article;
import Services.ArticleService;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
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
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AddArticleController implements Initializable {

    int id;
    private String titre;
    private String contenu;
    private String imageFile;
    private java.sql.Date created_at;
    private boolean update;
    private TextField tfNom;
    private DatePicker date_Debut;
    @FXML
    private TextArea description;
    private TextField nb_Participants;
    @FXML
    private Button btn_valider;
    private TextField addresse;
    private TextField categorie;
    private ImageView imageevent;
    File selectedFile;
    private String path;
    private ImageView nomCheckmark;
    private ImageView DescriptionCheckmark;
    private ImageView imagecheck;
    @FXML
    private Label labelnom;
    private Label labeldescription;
    @FXML
    private Button btn_annuler;
    @FXML
    private ImageView checkmark;
    @FXML
    private ImageView checkmark1;
    @FXML
    private Label labelcontenu;
    @FXML
    private TextField nomarticle;
    @FXML
    private ImageView img;
    @FXML
    private ImageView checkmark2;
    @FXML
    private Label labelimage;
    @FXML
    private Label pathh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tfNom.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "[a-zA-Z]*";

            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);

            if (controler.matches() && newValue.length() > 0) {
                checkmark.setImage(new Image("images/checkMark.png"));
                labelnom.setText("");

            } else {
                checkmark.setImage(new Image("images/erreurCheckMark.png"));
                labelnom.setText("remplir le nom qu'avec des caracteres ");

            }
        });

        description.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.length() > 0) {
                checkmark1.setImage(new Image("images/checkMark.png"));
                labelcontenu.setText("");

            } else {
                labelcontenu.setText("ce champ doit etre rempli ");
                checkmark1.setImage(new Image("images/erreurCheckMark.png"));

            }
        });

        // TODO
    }

    private void getQuery(String nom, String desc, Date date, String image) {
        System.out.println(update);
        if (update == false) {
            ArticleService es = new ArticleService();
            Article e = new Article(nom, desc, image, date);
            es.ajouter(e);
        } else {
            ArticleService es = new ArticleService();
            Article e = new Article(id, nom, desc, image);
            es.modifier(id, e);
        }

    }

    public void setNomarticle(String nomarticle) {
        this.nomarticle.setText(nomarticle);
    }

    void setTextField(int id, String titre, String contenu, String image) {

        this.id = id;
        System.out.println(id);
        System.out.println(titre);
        System.out.println(contenu);
        System.out.println(image);
        // tfNom.setText("titre");
        nomarticle.setText(titre);
        description.setText(contenu);
        // img.setString(image);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    @FXML
    private void uploadimage(MouseEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);
    if (selectedFile != null) {

            path = selectedFile.getName();
          //  path = selectedFile.toURI().toURL().toExternalForm();
            // image.setImage(new Image(selectedFile.toURI().toURL().toString()));
            // image.setFitHeight(150);
            //  image.setFitWidth(250);
            pathh.setText(path);

        }
      if (selectedFile != null) {
            try {
                File source = new File(selectedFile.toString());
                File dest = new File("C:\\xampp\\htdocs\\img");
                FileUtils.copyFileToDirectory(source, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void AjouterArticle(MouseEvent event) {

        StringBuilder errors = new StringBuilder();

        if (nomarticle.getText().trim().isEmpty()) {
            errors.append("- Please enter a name\n");
        }
        if (description.getText().trim().isEmpty()) {
            errors.append("- Please enter a description\n");
        }

        /*   if (imageevent.getImage() == null) {
            errors.append("- Please enter a imageevent \n");
        }*/
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            String nom = nomarticle.getText();
            String desc = description.getText();
            Date date = new Date(System.currentTimeMillis());
            // String image = img.getText();

            getQuery(nom, desc, date, pathh.getText());
            nomarticle.setText("");
            description.setText("");
            Stage stage = (Stage) btn_valider.getScene().getWindow();
            stage.close();
        }
    }

}

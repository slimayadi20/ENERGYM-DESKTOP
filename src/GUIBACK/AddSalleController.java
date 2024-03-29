/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIBACK;

import Entities.Salle;
import GUIBACK.MapController;
import Services.SalleService;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.Date;
import java.time.LocalDate;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author nouri
 */
public class AddSalleController implements Initializable {

    StringBuilder errors = new StringBuilder();
    @FXML
    private TextField ajoutnomfx;
    @FXML
    private TextField ajouttelfx;
    @FXML
    private TextField ajoutmailfx;
    @FXML
    private TextField ajoutdescriptionfx;
    private TextField ajoutimagefx;
    @FXML
    private TextField ajoutprix1fx;
    @FXML
    private TextField ajoutprix2fx;
    @FXML
    private TextField ajoutprix3fx;
    @FXML
    private JFXTimePicker ajoutheureofx;
    @FXML
    private JFXTimePicker ajoutheureffx;
    File selectedFile;
    private String path;
    private boolean update;
    SalleService cs = new SalleService();
    String query = null;

    int Salleid;
    @FXML
    private Button send;
    SalleService ps = new SalleService();
    @FXML
    private Button btn_localiser;
    @FXML
    private Text Location;
    @FXML
    private Button upload;
    @FXML
    private TextField ajoutfbfx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void clean() {
        ajoutnomfx.setText(null);
        Location.setText(null);
        ajouttelfx.setText(null);
        ajoutmailfx.setText(null);
        ajoutdescriptionfx.setText(null);
        //   ajoutimagefx.setText(null);
        ajoutprix1fx.setText(null);
        ajoutprix2fx.setText(null);
        ajoutprix3fx.setText(null);
        ajoutheureofx.setValue(null);
        ajoutheureffx.setValue(null);

    }

    private void getQuery(String nom, String adresse, String tel, String mail, String description, String image, String prix1, String prix2, String prix3, String heureo, String heuref, String url) {

        if (update == false) {
/////////////// nahhit id
            boolean test = cs.checksalle(mail, tel);
            if (test) {
                errors.append("-salle deja existante");

            } else {
                Salle c = new Salle(nom, adresse, tel, mail, description, image, prix1, prix2, prix3, heureo, heuref, url);
                cs.ajouter(c);
            }

        } else {
            
            Salle c = new Salle(Salleid, nom, adresse, tel, mail, description, image, prix1, prix2, prix3, heureo, heuref, url);

            cs.modifier(Salleid, c);

        }
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }

    }

    void setTextField(int id, String nom, String adresse, String tel, String mail, String description, String image, String prix1, String prix2, String prix3, String heureo, String heuref, String url) {

        Salleid = id;
        ajoutnomfx.setText(nom);
        Location.setText(adresse);
        ajouttelfx.setText(tel);
        ajoutmailfx.setText(mail);
        ajoutdescriptionfx.setText(description);
        upload.setText(image);
        ajoutprix1fx.setText(prix1);
        ajoutprix2fx.setText(prix2);
        ajoutprix3fx.setText(prix3);
        ajoutfbfx.setText(url);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime date1 = LocalTime.parse(heureo, formatter);

        LocalTime date2 = LocalTime.parse(heuref, formatter);
        ajoutheureofx.setValue(date1);
        ajoutheureffx.setValue(date2);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    public boolean isAddressValid(String address) {
        // Find the separator for the domain name
        int pos = address.indexOf('@');

        // If the address does not contain an '@', it's not valid
        if (pos == -1) {
            return false;
        }
        return true;

    }

    @FXML
    private void Localiser(ActionEvent event) throws Exception {
        //try {
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("map.fxml"));
            Parent root = loader.load();
            MapController cr = loader.getController();*/
        WebViewCaptureMap w = new WebViewCaptureMap();
        Stage stage = new Stage();
        w.start(stage);
        /*stage.setScene(new Scene(root));
            MainGUICategorie m = new MainGUICategorie();
            m.Map(stage);
            stage.show();*/

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Stage is closing");
                MapController mp = new MapController();
                Location.setText(mp.getMap_value());
                if (!"".equals(Location.getText())) {
                    btn_localiser.setDisable(true);
                    //System.out.println("done");
                }
            }
        });

        /*} catch (IOException ex) {
            Logger.getLogger(Version_3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    @FXML
    private void savetf(MouseEvent event) {

        try {
            Integer.parseInt(ajoutprix1fx.getText());
        } catch (NumberFormatException e) {
            errors.append("- Please enter a valid number\n");
        }
        try {
            Integer.parseInt(ajoutprix2fx.getText());
        } catch (NumberFormatException e) {
            errors.append("- Please enter a valid number\n");
        }
        try {
            Integer.parseInt(ajoutprix3fx.getText());
        } catch (NumberFormatException e) {
            errors.append("- Please enter a valid number\n");

        }
        try {
            Integer.parseInt(ajouttelfx.getText());
        } catch (NumberFormatException e) {
            errors.append("- Please enter a valid phone number\n");

        }
        if (ajouttelfx.getText().trim().isEmpty()) {
            errors.append("- Please enter a phone number\n");
        }
        if (!isAddressValid(ajoutmailfx.getText())) {
            errors.append("- Please enter a valid Email\n");
        }
        if (ajoutmailfx.getText().trim().isEmpty()) {
            errors.append("- Please enter an E-mail\n");
        }

        if (ajoutdescriptionfx.getText().trim().isEmpty()) {
            errors.append("- Please enter a description\n");
        }

        if (ajoutdescriptionfx.getText().trim().isEmpty()) {
            errors.append("- Please enter a description\n");
        }

        if (ajoutnomfx.getText().trim().isEmpty()) {
            errors.append("- Please enter a name \n");
        }
        if (ajoutprix1fx.getText().trim().isEmpty()) {
            errors.append("- Please enter an Gold price\n");
        }
        if (ajoutprix2fx.getText().trim().isEmpty()) {
            errors.append("- Please enter an silver price\n");
        }
        if (ajoutprix3fx.getText().trim().isEmpty()) {
            errors.append("- Please enter an Bronze price\n");
        }

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            String nom = ajoutnomfx.getText();

            String tel = ajouttelfx.getText();
            String mail = ajoutmailfx.getText();
            String description = ajoutdescriptionfx.getText();
            String image = upload.getText();
            String prix1 = ajoutprix1fx.getText();
            String prix2 = ajoutprix2fx.getText();
            String prix3 = ajoutprix3fx.getText();
            LocalTime heureo = ajoutheureofx.getValue();
            LocalTime heuref = ajoutheureffx.getValue();
            String url = ajoutfbfx.getText();
            System.out.println(url);

            getQuery(nom, Location.getText(), tel, mail, description, image, prix1, prix2, prix3, heureo.toString(), heuref.toString(), url);
            clean();
            // to close the window
            Stage stage = (Stage) send.getScene().getWindow();
            stage.close();
        }

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
//                path = selectedFile.toURI().toURL().toExternalForm();
            //  image.setImage(new Image(selectedFile.toURI().toURL().toString()));
            //  image.setFitHeight(150);
            //image.setFitWidth(250);
            upload.setText(path);

        }
        if (selectedFile != null) {
            try {
                File source = new File(selectedFile.toString());
                File dest = new File("D:\\Nouveau dossier\\SAUVGARDE\\ENERGYM\\public\\uploads\\salle");
                FileUtils.copyFileToDirectory(source, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

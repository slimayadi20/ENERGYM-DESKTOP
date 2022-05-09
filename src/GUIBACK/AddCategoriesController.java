/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIBACK;

import Entities.Categories;
import Services.CategoriesService;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author fedi
 */
public class AddCategoriesController implements Initializable {

    @FXML
    private TextField ajoutnomfx;

    final ObservableList<String> hd = FXCollections.observableArrayList();
    ObservableList<String> hf = FXCollections.observableArrayList();

    private boolean update;
    CategoriesService cs = new CategoriesService();
    String query = null;

    int Categoriesid;
    @FXML
    private Button send;
    CategoriesService ps = new CategoriesService();

    /**
     * Initializes the controller class.
     */
    private void clean() {
        ajoutnomfx.setText(null);

    }

    private void getQuery(String nom) {
        if (update == false) {
            Categories c = new Categories(nom);
            cs.ajouter(nom);
        } else {
            Categories c = new Categories(Categoriesid, nom);
            cs.modifier(Categoriesid, nom);
        }
    }

    void setTextField(int id, String nom) {

        this.Categoriesid = id;
        ajoutnomfx.setText(nom);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    @FXML
    private void savetf(MouseEvent event) {
        StringBuilder errors = new StringBuilder();

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            String nom = ajoutnomfx.getText();

            getQuery(nom);
            clean();
            // to close the window
            Stage stage = (Stage) send.getScene().getWindow();
            stage.close();

            Notifications notifications = Notifications.create();
            notifications.text("!!!ENERGYM : CATEGORIE AJOUTEE AVEC SUCCES!!!!");
            notifications.show();

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //To change body of generated methods, choose Tools | Templates.
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reclamation;
import Services.ReclamationService;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AddReclamationController implements Initializable {

    float reclamationid;
    @FXML
    private TextField titrefid;
    @FXML
    private TextArea contenufid;
    float id;
    int nomUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void save(MouseEvent event) {
        String titre = titrefid.getText();
        String contenu = contenufid.getText();
        Date date = new Date(System.currentTimeMillis());
        ReclamationService rs = new ReclamationService();
        Reclamation t = new Reclamation(titre, contenu, (int) getNomUser(), date);
        rs.ajouter(t);

    }

    void parameter(float id, int nomUser) {
        this.id = id;
        this.nomUser = nomUser;
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public int getNomUser() {
        return nomUser;
    }

    public void setNomUser(int nomUser) {
        this.nomUser = nomUser;
    }

}

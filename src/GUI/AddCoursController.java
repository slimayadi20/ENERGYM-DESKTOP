/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Cours;
import Services.CoursService;
import Services.SalleService;
import Tools.MyConnexion;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nouri
 */
public class AddCoursController implements Initializable {

    @FXML
    private TextField ajoutnomfx;
    @FXML
    private TextField ajoutcoachfx;
    @FXML
    private TextField ajoutnombrefx;
    @FXML
    private TextField ajoutdescriptionfx;
    @FXML
    private TextField ajoutimagefx;
    @FXML
    private ComboBox<String> ajoutsalleassocié;
    @FXML
    private ComboBox<String> ajoutjourfx;
    @FXML
    private JFXTimePicker ajoutheuredfx;
    @FXML
    private JFXTimePicker ajoutheureffx;
    @FXML
    private Button send;
    final 
          ObservableList<String> jour = FXCollections.observableArrayList();
          ObservableList<String> options = FXCollections.observableArrayList();
         private boolean update;
    CoursService cs = new CoursService();
    String query = null;
   
    int Coursid;
   
    SalleService ps = new SalleService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillcombobox();
         ajoutsalleassocié.setItems(options);
        // TODO  
    
        ajoutjourfx.setItems(jour);
        jour.add("Lundi");
        jour.add("Mardi");
        jour.add("Mercredi");
        jour.add("Jeudi");
        jour.add("Vendredi");
        jour.add("Samedi");
        jour.add("Dimanche");
       
        
        
       
       
        
    }  
     public void fillcombobox() {
        try {
            String requete = "SELECT * FROM salle";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                options.add(res.getString(2));
                System.out.println(res.getString(2));

            }
        } catch (SQLException ex) {
            System.out.println("aaaa");
            System.out.println(ex.getMessage());
        }
    }
    private void clean() {
        ajoutnomfx.setText(null);
        ajoutcoachfx.setText(null);
        ajoutnombrefx.setText(null);
        ajoutsalleassocié.setValue(null);
        ajoutdescriptionfx.setText(null);
        ajoutimagefx.setText(null);
        ajoutheuredfx.setValue(null);
        ajoutheureffx.setValue(null);
        ajoutjourfx.setValue(null);
      

    }
     private void getQuery(String nom, int salleassocie_id, String nom_coach, int nombre, String description, String image, String jour, String heure_d, String heure_f)  {

        if (update == false) {
/////////////// nahhit id 
            Cours c = new Cours( nom, salleassocie_id, nom_coach, nombre,description, image,  jour, heure_d, heure_f);
            cs.ajouter(c);
        } else {
            Cours c = new Cours(nom,salleassocie_id,nom_coach, nombre, description,image, jour, heure_d, heure_f) ;
            cs.modifier(Coursid,c);
             System.out.println(Coursid); 
        }

    }
     
    void setTextField(int id,String nom, int salleassocie_id, String nom_coach, int nombre, String description, String image, String jour, String heure_d, String heure_f) {
//sans salle associé
        Coursid = id;
        ajoutnomfx.setText(nom);
        ajoutcoachfx.setText(nom_coach);
        ajoutnombrefx.setText(Integer.toString(nombre));
        ajoutdescriptionfx.setText(description);
        ajoutimagefx.setText(image);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime date1 = LocalTime.parse(heure_d, formatter);
        LocalTime date2 = LocalTime.parse(heure_f, formatter);
        ajoutjourfx.setValue(jour);
        String salla = ps.getSalle(salleassocie_id);
        ajoutsalleassocié.setValue(salla);
        ajoutheuredfx.setValue(date1);
        ajoutheureffx.setValue(date2);
    }
    void setUpdate(boolean b) {
        this.update = b;

    }

    @FXML
   private void savetf(MouseEvent event) {
        StringBuilder errors = new StringBuilder();

        try {
            Integer.parseInt(ajoutnombrefx.getText());
        } catch (NumberFormatException e) {
            errors.append("- Please enter a valid number\n");
        }
        

        if (ajoutnombrefx.getText().trim().isEmpty()) {
            errors.append("- Please enter a number\n");
        }
        
            
      
         if (ajoutnomfx.getText().trim().isEmpty()) {
            errors.append("- Please enter a name\n");
        }
          if (ajoutcoachfx.getText().trim().isEmpty()) {
            errors.append("- Please enter a coach name\n");
        }
           if (ajoutsalleassocié.getValue().trim().isEmpty()) {
            errors.append("- Please select a GYM\n");
        } 
           if (ajoutjourfx.getValue().trim().isEmpty()) {
            errors.append("- Please select a day\n");
        }
         
              if (ajoutdescriptionfx.getText().trim().isEmpty()) {
            errors.append("- Please write a description\n");
        }
               if (ajoutimagefx.getText().trim().isEmpty()) {
            errors.append("- Please insert an image\n");
        }
                if (ajoutsalleassocié.getValue().trim().isEmpty()) {
            errors.append("- Please select a GYM\n");
        }
            
               
        
           
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            String nom = ajoutnomfx.getText();
            int salleassocie_id = ps.getSalleassocie_id(ajoutsalleassocié.getValue());  ////////////////
            String nom_coach = ajoutcoachfx.getText();
                int nombre = Integer.parseInt(ajoutnombrefx.getText());  
            
            String description = ajoutdescriptionfx.getText();
            String image = ajoutimagefx.getText();
          
            String jour = ajoutjourfx.getValue();
            LocalTime heure_d = ajoutheuredfx.getValue();
            LocalTime heure_f = ajoutheureffx.getValue();
           
            System.out.println(ajoutsalleassocié.getValue());
            System.out.println(ajoutsalleassocié);
           
            getQuery( nom, salleassocie_id, nom_coach, nombre,description, image,  jour, heure_d.toString(), heure_f.toString());
            clean();
            // to close the window
            Stage stage = (Stage) send.getScene().getWindow();
            stage.close();
        }

    }
    
}

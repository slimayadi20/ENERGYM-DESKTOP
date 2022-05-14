/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIBACK;

import Entities.Produit;
import Services.CategoriesService;
import Services.ProduitService;
import Tools.MyConnexion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author fedi
 */
public class AddProduitController implements Initializable {

    @FXML
    private TextField ajoutnomfx;
    @FXML
    private TextField ajoutdescriptionfx;
    @FXML
    private TextField ajoutimagefx;
    @FXML
    private TextField ajoutprixfx;
    @FXML
    private TextField ajoutquantitéfx;
    @FXML
    private ComboBox<String> ajoutNomCategoriesfx;
    File selectedFile;
    private String path;
    StringBuilder errors = new StringBuilder();

    final ObservableList<String> options = FXCollections.observableArrayList();

    private boolean update;
    ProduitService cs = new ProduitService();
    String query = null;

    int Produitid;
    @FXML
    private Button send;
    ProduitService ps = new ProduitService();
    CategoriesService os = new CategoriesService();
    @FXML
    private Button upload;

    /**
     * Initializes the controller class.
     */
    public void fillcombobox() {
        try {
            String requete = "SELECT * FROM categories";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                options.add(res.getString(3));

            }
        } catch (SQLException ex) {
            System.out.println("aaaa");
            System.out.println(ex.getMessage());
        }
    }

    private void clean() {
        ajoutnomfx.setText(null);
        ajoutdescriptionfx.setText(null);
        ajoutimagefx.setText(null);
        ajoutprixfx.setText(null);
        ajoutquantitéfx.setText(null);
        ajoutNomCategoriesfx.setValue(null);

    }

    private void getQuery(String nom, String description, String image, int prix, int quantité, int NomCategories) {

        if (update == false) {
            boolean test = cs.checkProduit(nom, description);
            if (test) {
                errors.append("-produit deja existante");
            } /////////////// nahhit id 
            else {
                System.out.println("prodcut ud" + Produitid);
                Produit c = new Produit( nom, description, image, prix, quantité, NomCategories);
                cs.ajouter(c);
            }
        } else {
            Produit c = new Produit(Produitid, nom, description, image, prix, quantité, NomCategories);
            cs.modifier(Produitid, c);
        }
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
    }

    void setTextField(int id, String nom, String description, String image, int prix, int quantité, int NomCategories) {

        Produitid = id;
        ajoutnomfx.setText(nom);
        ajoutdescriptionfx.setText(description);
        ajoutimagefx.setText(image);
        ajoutprixfx.setText(String.valueOf((prix)));
        ajoutquantitéfx.setText(String.valueOf((quantité)));
        String catego = os.getCateg(NomCategories);
        ajoutNomCategoriesfx.setValue(catego);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    @FXML
    private void savetf(MouseEvent event) {
        StringBuilder errors = new StringBuilder();

        try {
            Integer.parseInt(ajoutprixfx.getText());
        } catch (NumberFormatException e) {
            errors.append("- Please enter a valid number\n");
        }
        try {
            Integer.parseInt(ajoutquantitéfx.getText());
        } catch (NumberFormatException e) {
            errors.append("- Please enter a valid number\n");
        }

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            String nom = ajoutnomfx.getText();
            String description = ajoutdescriptionfx.getText();
            String image = ajoutimagefx.getText();

            int prix = Integer.parseInt(ajoutprixfx.getText());
            int quantité = Integer.parseInt(ajoutquantitéfx.getText());

            int NomCategories = os.getSalleassocie_id(ajoutNomCategoriesfx.getValue());  ////////////////

            getQuery(nom, description, image, prix, quantité, NomCategories);

            clean();
            // to close the window
            Stage stage = (Stage) send.getScene().getWindow();
            stage.close();

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillcombobox();
        ajoutNomCategoriesfx.setItems(options);
        //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void uploadimage(MouseEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", ".jpg", ".png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = selectedFile.getName();
//                path = selectedFile.toURI().toURL().toExternalForm();

            ajoutimagefx.setText(path);

        }
        if (selectedFile != null) {
            File source = new File(selectedFile.toString());
            File dest = new File("D:\\Nouveau dossier\\SAUVGARDE\\ENERGYM\\public\\uploads\\produit");
            FileUtils.copyFileToDirectory(source, dest);
        }
    }

}

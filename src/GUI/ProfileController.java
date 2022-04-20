package GUI;

import Entities.User;
import Services.UserService;
import energym.desktop.MainClass;
import static energym.desktop.MainClass.UserconnectedC;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;

public class ProfileController implements Initializable {

    private VBox pnItems = null;
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnCustomers;

    private Button btnMenus;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    ObservableList<String> ss = FXCollections.observableArrayList();
    ObservableList<User> data = FXCollections.observableArrayList();
    @FXML
    private Button btnReclamation;
    private TextField recherchetf;
    private TableColumn<User, Integer> status_col;
    private TableColumn<User, String> created_at_col;
    UserService us = new UserService();
    @FXML
    private AnchorPane DashboardUtilis;
    private Label currentTimeTF;
    @FXML
    private Label emailtf;
    private Label gerantfid;
    private Label membrefid;
    @FXML
    private Button btnProfile;
    @FXML
    private Circle circleu;
    @FXML
    private Button modifier;
    @FXML
    private Label e_nom;
    @FXML
    private TextField nom_i;
    @FXML
    private Label e_mail;
    @FXML
    private TextField email_i;
    @FXML
    private Label e_telephone;
    @FXML
    private TextField telephone_i;
    @FXML
    private Label e_prenom;
    @FXML
    private TextField prenom_i;
    @FXML
    private TextField tf_image;
    @FXML
    private Button importb;
    @FXML
    private Label Nomc;
    private ImageView image;
    @FXML
    private Circle circleu1;
    @FXML
    private DatePicker birthday;
    @FXML
    private Label e_prenom1;
    @FXML
    private Label e_prenom11;
    StringBuilder errors = new StringBuilder();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("C:\\xampp\\htdocs\\img\\" + UserconnectedC.getImageFile());
        circleu.setRadius(55);

        try {
            circleu.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
            circleu1.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setUser();
        emailtf.setText(UserconnectedC.getNom() + " " + UserconnectedC.getPrenom());

    }

    @FXML
    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnCustomers) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("User.fxml"));
            DashboardUtilis.getChildren().setAll(panee);
            //   btnCustomers.setStyle("-fx-background-color : #02030A");
        }
        if (actionEvent.getSource() == btnReclamation) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
            DashboardUtilis.getChildren().setAll(panee);
            //    btnReclamation.setStyle("-fx-background-color :#1620A1");
        }
        if (actionEvent.getSource() == btnProfile) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            DashboardUtilis.getChildren().setAll(panee);
        }
    }

    @FXML
    private void logout(MouseEvent event) {
        try {
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML.fxml"));
            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setTitle("reset password");
            stage.setScene(scene);
            stage.show();
            UserconnectedC = null;
        } catch (IOException ex) {
            System.err.println("Erreur");
        }
    }

    public void setImage(String name) {
        File file = new File("C:\\xampp\\htdocs\\img\\" + UserconnectedC.getImageFile());
        circleu.setRadius(55);
        try {
            System.out.println(new Image(file.toURI().toURL().toExternalForm()));
            circleu.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUser() {
        nom_i.setText(UserconnectedC.getNom());
        prenom_i.setText(UserconnectedC.getPrenom());
        telephone_i.setText(String.valueOf(UserconnectedC.getPhone()));
        email_i.setText(UserconnectedC.getEmail());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = UserconnectedC.getBirthday();
        LocalDate localDate = LocalDate.parse(date, formatter);
        birthday.setValue(localDate);
        tf_image.setText(UserconnectedC.getImageFile());
        if (UserconnectedC.getImageFile() != null) {
            setImage(UserconnectedC.getImageFile());
        }
        Nomc.setText(prenom_i.getText() + " " + nom_i.getText());
        email_i.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                    + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                e_mail.setText("");

            } else {
                e_mail.setText("mail invalide ");
                errors.append("- votre email est invalid\n");//string s --- s+="erreur"

            }
        });
        tf_image.textProperty().addListener((observable, oldValue, newValue) -> {

            if (tf_image.getText().trim().isEmpty()) {
                e_prenom11.setText("image invalide ");
                errors.append("- votre image est invalid\n");//string s --- s+="erreur"

            }
        });

        telephone_i.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "[0-9]*";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                if (newValue.length() == 8) {
                    e_telephone.setText("");
                }

            } else {
                e_telephone.setText("le numero du telephone est invalid");
                errors.append("- votre telephone est invalid\n");//string s --- s+="erreur"

            }
        });
        nom_i.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "[a-zA-Z]*";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                e_nom.setText("");
            } else {
                e_nom.setText("votre nom est invalid");
                errors.append("- votre nom est invalid\n");//string s --- s+="erreur"

            }
        });
        prenom_i.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "[a-zA-Z]*";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                e_prenom.setText("");

            } else {
                e_prenom.setText("votre prenom est invalid");
                errors.append("- votre prenom est invalid\n");//string s --- s+="erreur"

            }
        });
        birthday.valueProperty().addListener((observable, oldValue, newValue) -> {

            LocalDate dateBefore18years = LocalDate.now(ZoneId.of("Europe/Paris")).minusDays(6570);

            boolean cond = (newValue.isBefore(dateBefore18years));
            System.out.println(cond);
            if (cond) {
                e_prenom1.setText("");

            } else {
                e_prenom1.setText("*vous devez avoir au moins 18 ans");
                errors.append("- Vous devez avoir au moins 18 ans \n");//string s --- s+="erreur"
            }
        });
    }

    @FXML
    private void modifierUtilisateur(ActionEvent event) {

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            UserService us = new UserService();
            User u = new User();
            u.setNom(nom_i.getText());
            u.setPrenom(prenom_i.getText());
            u.setEmail(email_i.getText());
            u.setPhone(telephone_i.getText());
            u.setBirthday(Date.valueOf(birthday.getValue()).toString());
            u.setImageFile(tf_image.getText());
            u.setId(UserconnectedC.getId());
            us.modifier(UserconnectedC.getId(), u);
            System.out.println(UserconnectedC.getId());
            System.out.println("updated ! ");
        }
    }

    @FXML
    private void importer(ActionEvent event) {
        String fileName = null;
        FileChooser fc = new FileChooser();
        File SelectedFile = fc.showOpenDialog(null);
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", ".jpg", ".png"));
        //  e.setImage_event(SelectedFile.getAbsolutePath());
        if (SelectedFile != null) {

            String fcs = SelectedFile.toString();
            int index = fcs.lastIndexOf('\\');
            if (index > 0) {
                fileName = fcs.substring(index + 1);
            }

        }
        String key = UUID.randomUUID().toString();
        String fcs = SelectedFile.getAbsolutePath();
        File source = new File(fcs);
        File destination = new File("C:\\xampp\\htdocs\\img\\" + key + fileName);
        String url = destination.getAbsolutePath();
        System.out.println(url);

        if (!destination.exists()) {
            try {
                FileUtils.copyFile(source, destination);
            } catch (Exception E) {

            }
            tf_image.setText(key + fileName);
        }
    }

}

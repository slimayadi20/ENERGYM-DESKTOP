/** *****************************************************************************
 * Controller class and logic implementation for movies.fxml
 ***************************************************************************** */
package GUI;

import Entities.Reclamation;
import GUI.*;
import Entities.User;
import Services.ReclamationService;
import Services.UserService;
import Services.WebCamService;
import Services.WebCamView;
import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.lowagie.text.DocumentException;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import static energym.desktop.MainFX.UserconnectedC;
import java.io.File;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;

public class ProfileController implements Initializable {

    @FXML
    private JFXButton closebtn;
    @FXML
    private JFXButton minimisebtn;
    @FXML
    private JFXButton logoutbtn;
    @FXML
    private JFXButton homebtn;
    private JFXButton addbtn;

    private JFXTextField searchfield;
    @FXML
    private AnchorPane mainmoviespane;
    @FXML
    private AnchorPane leftpane;
    @FXML
    private AnchorPane toppane;
    private AnchorPane tablepane;
    private ImageView movieimage;
    private Label movietitle;
    private Label movierating;
    private Label movieduration;
    @FXML
    private Label userlabel;

    private String[] movienames;
    private boolean faded = false, showingall = false;
    public static String currentmovie = "";
    private int currentslots = 0;
    public static int slotseatNo = -1;

    private ImageView userimage;
    private JFXTextField recherchetf;
    private Label emaillabel;
    private Label dn;
    private Label role;
    private Label createdat;
    ObservableList<String> ss = FXCollections.observableArrayList();
    ObservableList<Reclamation> data = FXCollections.observableArrayList();
    UserService us = new UserService();
    private TableColumn<?, ?> createdat_col;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnUsers;
    @FXML
    private JFXButton btnReclamation;
    @FXML
    private JFXButton btnReply;
    ReclamationService rs = new ReclamationService();
    Reclamation reclamation = null;
    ObservableList<Reclamation> ReclamationList = FXCollections.observableArrayList();
    private TableColumn<Reclamation, String> produit_col1;
    private Label senderlabel;
    private Label titrelabel;
    private Label contenulabel;
    private Label produitlabel;
    private TableColumn<User, String> sender_col;
    private TableColumn<?, ?> titre_col;
    private TableColumn<?, ?> created_at_col_rec;
    private TableColumn<?, ?> contenu_col;
    private TableColumn<?, ?> statut_col;
    private TableView<Reclamation> tableviewreclamation;
    private Label repondufid;
    private Label encoursfid;
    @FXML
    private Circle circleu;
    @FXML
    private TextField nom_i;
    @FXML
    private Label e_nom;
    @FXML
    private TextField email_i;
    @FXML
    private Label e_mail;
    @FXML
    private TextField telephone_i;
    @FXML
    private Label e_telephone;
    @FXML
    private TextField prenom_i;
    @FXML
    private Label e_prenom;
    @FXML
    private TextField tf_image;
    @FXML
    private Button importb;
    @FXML
    private Label e_prenom11;
    @FXML
    private DatePicker birthday;
    @FXML
    private Label e_prenom1;
    @FXML
    private JFXButton modifier;
    @FXML
    private Label Nomc;
    StringBuilder errors = new StringBuilder();
    @FXML
    private Pane webcamPane;
    @FXML
    private ImageView imgPrevWeb;
    @FXML
    private Pane paneNoir;
    @FXML
    private Button importb1;
    private WebCamService service;

    /**
     * Initialise method required for implementing initializable and, sets up
     * and applies all effects and animations to nodes in logout.fxml
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("C:\\xampp\\htdocs\\img\\" + UserconnectedC.getImageFile());
        circleu.setRadius(55);

        try {
            circleu.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setUser();

    }

    @FXML
    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnUsers) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Users.fxml"));
            mainmoviespane.getChildren().setAll(panee);
            //  pnlCustomer.setStyle("-fx-background-color : #1620A1");
            //  pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnReclamation) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
            mainmoviespane.getChildren().setAll(panee);
            //    btnReclamation.setStyle("-fx-background-color :#02030A");
            //    pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnProfile) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (actionEvent.getSource() == btnReply) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Reply.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (actionEvent.getSource() == homebtn) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("home.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }

    }

    @FXML
    private void logOut(ActionEvent event) {
        try {
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML.fxml"));
            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setTitle("Login");
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
            u.setBirthday(java.sql.Date.valueOf(birthday.getValue()).toString());
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

    @FXML
    private void btnExit(MouseEvent event) {
    }

    @FXML
    private void btnHover(MouseEvent event) {
    }

    @FXML
    private void minimiseWindow(ActionEvent event) {
    }

    @FXML
    private void handleClose(ActionEvent event) {
    }

    @FXML
    private void send(MouseEvent event) {
    }

    @FXML
    private void openWebcam(ActionEvent event)  {
        /*    Webcam cam = Webcam.getWebcams().get(0);
        service = new WebCamService(cam);
        WebCamView view = new WebCamView(service);
        webcamPane.setVisible(true);
        webcamPane.getChildren().add(view.getView());*/
        Webcam wb = Webcam.getWebcams().get(0);

        paneNoir.setVisible(true);
        webcamPane.setVisible(true);
        wb.open();
        String name = UUID.randomUUID().toString().substring(1, 8) + ".jpg";
        File f = new File("src/images/" + name);

        try {
            ImageIO.write(wb.getImage(), "JPG", f);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Ok");

        Image i = new Image(f.toURI().toString());

        imgPrevWeb.setImage(i);

        String chemin = f.getAbsolutePath();
        System.out.println(chemin);

        circleu.setFill(new ImagePattern(i));
         paneNoir.setVisible(false);
        webcamPane.setVisible(false);
        tf_image.setText(f.toURI().toString());

    }

    @FXML
    private void historique(MouseEvent event) {
        UserService u = new UserService();
        try {
            us.RapportHCbyUser(UserconnectedC);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

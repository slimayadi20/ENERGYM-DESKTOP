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
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ComboBox;
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
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserController implements Initializable {

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

    @FXML
    private ComboBox<String> combotri;
    @FXML
    private TableView<User> tableviewuser;
    @FXML
    private TableColumn<User, String> nom_col;
    @FXML
    private TableColumn<User, String> prenom_col;
    @FXML
    private TableColumn<User, String> email_col;
    @FXML
    private TableColumn<User, String> date_col;
    @FXML
    private TableColumn<User, String> role_col;
    ObservableList<String> ss = FXCollections.observableArrayList();
    ObservableList<User> data = FXCollections.observableArrayList();
    @FXML
    private Button btnReclamation;
    @FXML
    private TextField recherchetf;
    @FXML
    private TableColumn<User, Integer> status_col;
    @FXML
    private TableColumn<User, String> created_at_col;
    UserService us = new UserService();
    @FXML
    private AnchorPane DashboardUtilis;
    @FXML
    private Label currentTimeTF;
    @FXML
    private Label emailtf;
    @FXML
    private Label gerantfid;
    @FXML
    private Label membrefid;
    @FXML
    private Button btnProfile;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button btntri;
    @FXML
    private TableColumn<?, ?> salle_col;
    private ImageView image;
    @FXML
    private Circle circleu1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ss.add("Par role");
        ss.add("Par date");
        ss.add("Par status");
          File file = new File("C:\\xampp\\htdocs\\img\\" + UserconnectedC.getImageFile());


        try {
            circleu1.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
        emailtf.setText(UserconnectedC.getNom() + " " + UserconnectedC.getPrenom());
        gerantfid.setText((Integer.toString(us.affichernumber("ROLE_GERANT"))));
        membrefid.setText((Integer.toString(us.affichernumber("ROLE_CLIENT"))));
        // TODO
        combotri.setItems(ss);
        refreshlist();
        recherche_avance();
        initClock();

    }

    public static String getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return "years " + diffInMillies;
    }

    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(us.afficher());
        nom_col.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prenom_col.setCellValueFactory(new PropertyValueFactory<>("Prenom"));

        email_col.setCellValueFactory(new PropertyValueFactory<>("Email"));
        status_col.setCellValueFactory(new PropertyValueFactory<>("status"));
//String age =calculateAge(new PropertyValueFactory<>("birthday")) ;
//SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
//Date date = new Date(System.currentTimeMillis());
        //    date_col.setCellValueFactory(cellData -> new SimpleStringProperty(getDateDiff(cellData.getValue().getBirthday(),date,TimeUnit.MINUTES)));
        //   System.out.println(date);
        date_col.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        created_at_col.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        role_col.setCellValueFactory(new PropertyValueFactory<>("Roles"));
        tableviewuser.setItems(data);
        gerantfid.setText((Integer.toString(us.affichernumber("ROLE_GERANT"))));
        membrefid.setText((Integer.toString(us.affichernumber("ROLE_CLIENT"))));
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
    private void trilist(ActionEvent event) {
        if (combotri.getValue().equals("Par role")) {
            ObservableList<User> tri1 = FXCollections.observableArrayList();
            tri1 = FXCollections.observableArrayList(us.sortByRoles());
            tableviewuser.setItems(tri1);

        } else if (combotri.getValue().equals("Par date")) {
            ObservableList<User> tri2 = FXCollections.observableArrayList();
            tri2 = FXCollections.observableArrayList(us.sortByDate());
            tableviewuser.setItems(tri2);
        } else if (combotri.getValue().equals("Par status")) {
            ObservableList<User> tri3 = FXCollections.observableArrayList();
            tri3 = FXCollections.observableArrayList(us.sortByStatus());
            tableviewuser.setItems(tri3);
        }
    }

    @FXML
    public void recherche_avance() {
        System.out.println("*******************");

        FilteredList<User> filtereddata = new FilteredList<>(data, b -> true);
        System.out.println(recherchetf.getText());
        recherchetf.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate((User user) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (user.getNom().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getPrenom().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getNom().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getEmail().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (String.valueOf(user.getPhone()).indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getRoles().toString().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        //System.out.println(filtereddata);
        SortedList<User> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(tableviewuser.comparatorProperty());
        tableviewuser.setItems(filtereddata);
    }

    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            currentTimeTF.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
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

    @FXML
    private void fillforum(MouseEvent event) {
    }


}

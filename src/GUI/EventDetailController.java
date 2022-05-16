/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Event;
import Entities.User;
import GUIBACK.ProfileController;
import Services.EventService;
import Tools.JavaMailEvent;
import Tools.JavaMailUtilUser;
import com.google.zxing.qrcode.QRCodeWriter;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import energym.desktop.MainFX;
import static energym.desktop.MainFX.UserconnectedC;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import javafx.event.ActionEvent;
import javax.swing.JFileChooser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javassist.NotFoundException;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author user
 */
public class EventDetailController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public int Eventid;
    @FXML
    private Label namefxid;
    @FXML
    private ImageView imagefxid;
    @FXML
    private Label nbrfxid;
    @FXML
    private Label lieufxid;
    @FXML
    private Label descriptionfxid;
    @FXML
    private Label datefxid;
    @FXML
    private JFXButton participerfxid;
    @FXML
    private Label errorfxid;

    Random rand = new Random();
    int randomcode = rand.nextInt(9999);
    String code = String.valueOf(randomcode);
    private Parent root;
    @FXML
    private HBox hboxnavbar;
    @FXML
    private Label sallefxid;
    @FXML
    private Label eventfxid;
    @FXML
    private Label produitfxid;
    @FXML
    private Label reclamationfxid;
    @FXML
    private Button namefxid1;
    @FXML
    private JFXToggleButton btnEditMode;
    @FXML
    private AnchorPane mainpane;
    @FXML
    private Label articlefxid;
    @FXML
    private Circle circle;
    @FXML
    private GridPane grid;
    @FXML
    private Label namefxid2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        namefxid.setText(UserconnectedC.getNom());
        File file = new File("D:\\Nouveau dossier\\SAUVGARDE\\ENERGYM\\public\\uploads\\user\\" + UserconnectedC.getImageFile());

        try {
            circle.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadData();
        mainpane.setOpacity(0);
        makeFadeInTransition();
        System.out.println("eventid" + Eventid);

        // TODO
    }

    private void makeFadeInTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(mainpane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

    }

    public void setEventId(int id) {
        this.Eventid = id;
    }

    public void loadData() {
        //System.out.println("initData"+thematique_id);
        EventService ths = new EventService();
        System.out.println("loaddata event id " + Eventid);
        Event e = ths.EventDetailFront(Eventid);
        namefxid.setText(e.getNomEvent());
        nbrfxid.setText(e.getNbrPlacesEvent());
        lieufxid.setText(e.getLieuEvent());
        descriptionfxid.setText(e.getDescriptionEvent());
        /*   String pattern = "yyyy-mm-dd";
        DateFormat df = new SimpleDateFormat(pattern);
        String todayAsString = df.format(e.getDateEvent());
        datefxid.setText(todayAsString);
        System.out.println(e.getDateEvent());*/
        imagefxid.setImage(new Image("file:D:\\Nouveau dossier\\SAUVGARDE\\ENERGYM\\public\\uploads\\Event_image\\" + e.getImageFile()));
        imagefxid.setFitHeight(190);
        imagefxid.setFitWidth(1700);
        EventService thss = new EventService();
        Event ee = thss.EventDetailFront(Eventid);
        System.out.println("id categ" + ee.getCategories());
        List<Event> listTHH = thss.afficherbycategorie(ee.getCategories());

        int colonne = 0;
        int row = 1;
        try {
            for (Event t : listTHH) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("EventItem.fxml"));

                Pane anchorPane = fxmlLoader.load();

                EventItemController controller = fxmlLoader.getController();
                controller.setData(t);
                if (colonne == 6) {
                    colonne = 0;
                    row++;
                }
                grid.add(anchorPane, colonne++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(12));
                //break;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void participer(MouseEvent event) throws Exception {
        EventService ths = new EventService();
        User u = new User(37, "slim", "ayadi", "azertyuiop", "95590010", "slim.ayadi@esprit.tn", "admiinnslm1");
        Event e = ths.EventDetailFront(Eventid);
        JavaMailEvent mail = new JavaMailEvent();
        System.out.println(ths.check(Eventid, u.getId()));
        System.out.println("event" + Eventid);
        System.out.println("user" + u.getId());
        if (ths.check(Eventid, MainFX.UserconnectedC.getId()) == true) {
            errorfxid.setText("vous avez deja participé");
        } else {

            ths.Participer(e, MainFX.UserconnectedC, code);
            String content = "Bonjour mr/mme " + u.getNom() + "\n"
                    + "Merci pour votre participation et voici votre pass pour l'evenement\n"
                    + "on vous attend chaleurheusement ! ";
            String url = qr(e.getNomEvent(), u.getNom());
            //  String url = "";
            mail.sendMail("Confirmation de participation!", content, u.getEmail(), url);
            int nbr = Integer.parseInt(e.getNbrPlacesEvent());
            TrayNotification tray = new TrayNotification();

            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Participation");
            tray.setMessage("You successufuly participated in our Event");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
            if (nbr == 1) {
                ths.modifierEvent(Eventid, String.valueOf(nbr - 1), "complet");

            } else {

                ths.modifierEvent(Eventid, String.valueOf(nbr - 1), "incomplet");
            }

        }

    }

    public String qr(String nom, String nomu) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String Id_stat = nom + nomu;

        String LoginCode = "event :" + nom + "user : " + nomu + "code de verification:" + code;
        ByteArrayOutputStream out = QRCode.from(LoginCode)
                .to(ImageType.PNG).stream();
        String f_name = Id_stat;
        String Path_name = new File("src/css/").getAbsolutePath();
        try {
            FileOutputStream fout = new FileOutputStream(new File(Path_name + "/" + (f_name + ".PNG")));
            fout.write(out.toByteArray());
            fout.flush();
            System.out.println(Path_name);

        } catch (Exception e) {
            System.out.println(e);

        }
        //qrView.setImage(SwingFXUtils.toFXImage(out, null));

        return Id_stat;

    }

    private void makeFadeInTransition(String a) {

        try {
            Stage stage = (Stage) mainpane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(a));/* Exception */
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void reclamation(MouseEvent event) throws IOException {
        makeFadeInTransition("ReclamationFront.fxml");

    }

    @FXML
    private void event(MouseEvent event) throws IOException {
        makeFadeInTransition("Evenement.fxml");

    }

    @FXML
    private void home(MouseEvent event) {
        makeFadeInTransition("HomeFront.fxml");

    }

    @FXML
    private void salle(MouseEvent event) {
        makeFadeInTransition("Salle.fxml");

    }

    @FXML
    private void produit(MouseEvent event) {
        makeFadeInTransition("Produit.fxml");

    }

    @FXML
    private void profile(MouseEvent event) {
        makeFadeInTransition("ProfileFront.fxml");

    }

    @FXML
    private void btnEditModeToggle(MouseEvent event) {
    }

    @FXML
    private void article(MouseEvent event) {
        makeFadeInTransition("Article.fxml");

    }
}

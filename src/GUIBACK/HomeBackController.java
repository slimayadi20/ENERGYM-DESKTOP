/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIBACK;

import Entities.Event;
import Entities.Produit;
import Entities.Salle;
import GUI.EventItemController;
import Services.EventService;
import Services.ProduitService;
import Services.SalleService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import static energym.desktop.MainFX.UserconnectedC;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class HomeBackController implements Initializable {

    @FXML
    private JFXButton homebtn;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnUsers;
    @FXML
    private JFXButton btnReclamation;
    @FXML
    private JFXButton btnReply;
    @FXML
    private AnchorPane toppane;
    @FXML
    private JFXButton minimisebtn;
    @FXML
    private JFXButton closebtn;
    @FXML
    private JFXTabPane tabpane;
    private JFXButton s1issuebtn;
    @FXML
    private AnchorPane s1tile3;
    @FXML
    private AnchorPane s1tile2;
    @FXML
    private AnchorPane s1tile1;
    @FXML
    private AnchorPane s1infopane;
    @FXML
    private AnchorPane s2infopane;
    @FXML
    private AnchorPane s2tile1;
    @FXML
    private AnchorPane s2tile2;
    @FXML
    private AnchorPane s2tile3;
    @FXML
    private AnchorPane p3;
    @FXML
    private AnchorPane p3shadow;
    @FXML
    private Label datelabel;
    @FXML
    private Label timelabel;
    @FXML
    private AnchorPane mainmoviespane;
    @FXML
    private AnchorPane leftpane1;
    private JFXButton logoutbtn1;
    @FXML
    private JFXButton btnsalle;
    @FXML
    private JFXButton btncours;
    @FXML
    private JFXButton btnproduit;
    @FXML
    private JFXButton btncategories;
    @FXML
    private JFXButton btnevenement;
    @FXML
    private JFXButton btncategoriesevent;
    @FXML
    private JFXButton btnparticipation;
    @FXML
    private ImageView Fr;
    @FXML
    private ImageView Ar;
    public static String Langue = "Fr";
    @FXML
    private Label home_label;
    @FXML
    private Tab Best_Seller;
    @FXML
    private Tab Best_Event;
    @FXML
    private Tab Best_Salle;
    private Label Products_Sold_Today;
    private Label Total_Sale_Today;
    @FXML
    private PieChart stat;
    @FXML
    private LineChart<String, Double> statcourbe;
    ObservableList<PieChart.Data> piechartdata;
    XYChart.Series<String, Double> linechartdata = new XYChart.Series();

    Connection cnx;
    ResultSet rs;
    @FXML
    private JFXButton logoutbtn;
    @FXML
    private JFXButton btnarticle;
    @FXML
    private JFXButton btncommentaire;
    @FXML
    private JFXButton btncommande;
    @FXML
    private JFXButton btnlivraison;
    @FXML
    private Label name;
    @FXML
    private Label rating;
    @FXML
    private Label qte;
    @FXML
    private Label prix;
    @FXML
    private Label description;
    @FXML
    private ImageView image;
    @FXML
    private Label namesalle;
    @FXML
    private Label prix3;
    @FXML
    private Label prix2;
    @FXML
    private Label prix1;
    @FXML
    private ImageView imagesalle;
    @FXML
    private Label addresssalle;
    @FXML
    private Label descriptionsalle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String str = formatter.format(date);
        datelabel.setText(str);
        if (UserconnectedC.getRoles().equals("ROLE_GERANT")) {
            btnevenement.setVisible(false);
            btnproduit.setVisible(false);
            btncategories.setVisible(false);
            btncategoriesevent.setVisible(false);
            btnparticipation.setVisible(false);
            btncommande.setVisible(false);
            btnlivraison.setVisible(false);
            btncommentaire.setVisible(false);
            btnarticle.setVisible(false);

        }
        Traduction();
        try {
            // TODO
            loadDataPie();
        } catch (SQLException ex) {
            //Logger.getLogger(StatRatingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stat.setData(piechartdata);
        try {
            loadDataLine();
        } catch (SQLException ex) {
            //  Logger.getLogger(StatRatingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        statcourbe.getData().add(linechartdata);
        initClock();
        loadData();
        // TODO
    }

    public void loadData() {
        //System.out.println("initData"+thematique_id);
        ProduitService ths = new ProduitService();
        Produit e = ths.getProduithome();
        name.setText(e.getNom());
        description.setText(e.getDescription());
        prix.setText(String.valueOf(e.getPrix()));
        qte.setText(String.valueOf(e.getQuantité()));
        rating.setText(String.valueOf(e.getRating()));

        image.setImage(new Image("file:D:\\Nouveau dossier\\SAUVGARDE\\ENERGYM\\public\\uploads\\produit\\" + e.getImage()));
        image.setFitHeight(190);
        image.setFitWidth(1700);
        SalleService ss = new SalleService();
        Salle s = ss.getsallehome();
        namesalle.setText(s.getNom());
        descriptionsalle.setText(s.getDescription());
        addresssalle.setText(s.getAdresse());
        prix3.setText(s.getPrix3());
        prix2.setText(s.getPrix2());
        prix1.setText(s.getPrix1());
        imagesalle.setImage(new Image("file:D:\\Nouveau dossier\\SAUVGARDE\\ENERGYM\\public\\uploads\\salle\\" + s.getImage()));
        imagesalle.setFitHeight(190);
        imagesalle.setFitWidth(600);

    }

    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            timelabel.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void loadDataPie() throws SQLException {
        int i = 0;
        int j = 0;
        int k = 0;
        piechartdata = FXCollections.observableArrayList();
        String dburl = "jdbc:mysql://localhost:3306/energym3";
        String dblogin = "root";
        String dbpwd = "";

        cnx = DriverManager.getConnection(dburl, dblogin, dbpwd);
        PreparedStatement pst = cnx.prepareStatement("SELECT * from Produit");
        rs = pst.executeQuery();

        while (rs.next()) {
            piechartdata.add(new PieChart.Data(rs.getString("nom"), rs.getInt("quantite")));

        }

    }

    public void loadDataLine() throws SQLException {

        String dburl = "jdbc:mysql://localhost:3306/energym3";
        String dblogin = "root";
        String dbpwd = "";

        cnx = DriverManager.getConnection(dburl, dblogin, dbpwd);
        PreparedStatement pst = cnx.prepareStatement("SELECT * from Produit");
        rs = pst.executeQuery();

        while (rs.next()) {
            String s;
            s = String.valueOf(rs.getInt("id"));
            linechartdata.getData().add(new XYChart.Data<String, Double>(s, rs.getDouble("id")));
//            name.add(rs.getString("nom_local"));
//            cap.add(rs.getInt("capacite"));             
        }
//        linechart.getData().add(linechartdata);
    }

    @FXML
    private void btnExit(MouseEvent event) {
    }

    @FXML
    private void btnHover(MouseEvent event) {
    }

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btnUsers) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Users.fxml"));
            mainmoviespane.getChildren().setAll(panee);

        }
        if (event.getSource() == btnReclamation) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btnProfile) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btnReply) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Reply.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == homebtn) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("HomeBack.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == homebtn) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("HomeBack.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btnsalle) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Salle.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btncours) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Cours.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btncategories) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Categories.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btnproduit) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Produit.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btnevenement) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("EvenementBack.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btncategoriesevent) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("CategoriesEventBack.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btnparticipation) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Participation.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btnarticle) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Article.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btncommentaire) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Commentaire.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btncommande) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Commande.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btnlivraison) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Livraison.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
          if (event.getSource() == logoutbtn) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            mainmoviespane.getChildren().setAll(panee);
            UserconnectedC=null ; 
        }
    }

    @FXML
    private void logOut(ActionEvent event) {
    }

    @FXML
    private void minimiseWindow(ActionEvent event) {
    }

    @FXML
    private void handleClose(ActionEvent event) {
    }

    @FXML
    private void tileExit(MouseEvent event) {
    }

    @FXML
    private void tileHover(MouseEvent event) {
    }

    @FXML
    private void rotatePane(MouseEvent event) {
    }

    @FXML
    private void LangueAr(MouseEvent event) {

        this.Langue = "Ar";
        Traduction();

    }

    @FXML
    private void LangueFr(MouseEvent event) {

        this.Langue = "Fr";
        Traduction();

    }

    public void Traduction() {
        if ("Fr".equals(this.Langue)) {

            Best_Seller.setText("meilleure vente");
            Best_Event.setText("meilleur Salle");

            Best_Salle.setText("Statistique");

            home_label.setText("Acceuil");
            // s1issuebtn.setText("émettre un billet");
            homebtn.setText("Acceuil");
            btnProfile.setText("Profile");
            btnUsers.setText("Utilisateurs");
            btnReclamation.setText("Réclamation");
            btnReply.setText("Réponse");
            logoutbtn.setText("Quitter");

            btnsalle.setText("Salle");
            btncours.setText("Cours");
            btncategories.setText("Categories des produits");
            btnproduit.setText("Produits");
            btnevenement.setText("Evenement");
            btnparticipation.setText("Participation");
            btnarticle.setText("Article");
            btncommentaire.setText("Commentaire");
            btncommande.setText("Commande");
            btnlivraison.setText("Livraison");

        } else {
            Best_Seller.setText("الأكثر مبيعًا");
            Best_Event.setText("أفضل صالة");

            Best_Salle.setText("إحصاء");

            home_label.setText("ترحيب");
            //   s1issuebtn.setText("اصدار التذكرة ");
            homebtn.setText("ترحيب");
            btnProfile.setText("حساب تعريفي");
            btnUsers.setText("المستخدمون");
            btnReclamation.setText("استصلاح");
            btnReply.setText("إجابه");
            logoutbtn.setText("خروج");

            btnsalle.setText("قاعة الرياضة");
            btncours.setText("درس");
            btncategories.setText("فئات المنتجات");
            btnproduit.setText("منتجات");
            btnevenement.setText("الأحداث");
            btnparticipation.setText("مشاركة");
            btncategoriesevent.setText("فئة الأحداث");
            btnarticle.setText("مقالة ");
            btncommentaire.setText("ملاحظة ");
            btncommande.setText("طلب تجاري");
            btnlivraison.setText("توصيل");
        }
    }

}

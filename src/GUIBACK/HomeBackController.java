/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIBACK;

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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    @FXML
    private JFXButton s1issuebtn;
    @FXML
    private AnchorPane s1tile6;
    @FXML
    private AnchorPane s1tile5;
    @FXML
    private Label s1timeslot;
    @FXML
    private AnchorPane s1tile4;
    @FXML
    private Label s1timeremaining;
    @FXML
    private AnchorPane s1tile3;
    @FXML
    private Label s1rating;
    @FXML
    private AnchorPane s1tile2;
    @FXML
    private Label s1repeatsleft;
    @FXML
    private AnchorPane s1tile1;
    @FXML
    private Label s1availableseats;
    @FXML
    private AnchorPane s1infopane;
    @FXML
    private Label s1moviename;
    @FXML
    private Label s1movieduration;
    @FXML
    private Label s1nextmovie;
    @FXML
    private Label s1status;
    @FXML
    private ImageView s1movieimage;
    @FXML
    private JFXButton s2issuebtn;
    @FXML
    private AnchorPane s2infopane;
    @FXML
    private Label s2moviename;
    @FXML
    private Label s2movieduration;
    @FXML
    private Label s2nextmovie;
    @FXML
    private Label s2status;
    @FXML
    private AnchorPane s2tile1;
    @FXML
    private Label s2availableseats;
    @FXML
    private AnchorPane s2tile4;
    @FXML
    private Label s2timeremaining;
    @FXML
    private AnchorPane s2tile2;
    @FXML
    private Label s2repeatsleft;
    @FXML
    private AnchorPane s2tile5;
    @FXML
    private Label s2timeslot;
    @FXML
    private AnchorPane s2tile3;
    @FXML
    private Label s2rating;
    @FXML
    private AnchorPane s2tile6;
    @FXML
    private ImageView s2movieimage;
    @FXML
    private AnchorPane p1;
    @FXML
    private AnchorPane p1shadow;
    @FXML
    private AnchorPane p2;
    @FXML
    private AnchorPane p2shadow;
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
    @FXML
    private Label Products_Sold_Today;
    @FXML
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                if (UserconnectedC.getRoles().equals("ROLE_GERANT"))
{
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
        
        // TODO
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
            piechartdata.add(new PieChart.Data( rs.getString("nom"), rs.getInt("quantite")));
           

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
    private void viewMovieScreens(MouseEvent event) {
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
            Best_Event.setText("meilleur événement");
            Products_Sold_Today.setText("Produits vendus aujourd'hui");
            Total_Sale_Today.setText("Vente totale aujourd'hui");
            Best_Salle.setText("Meilleure salle");

            home_label.setText("Acceuil");
            s1issuebtn.setText("émettre un billet");
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

        } else {
             Best_Seller.setText("الأكثر مبيعًا");
            Best_Event.setText("أفضل حدث");
            Products_Sold_Today.setText("المنتجات المباعة اليوم");
            Total_Sale_Today.setText("إجمالي البيع اليوم");
            Best_Salle.setText("أفضل صالة");

            home_label.setText("ترحيب");
            s1issuebtn.setText("اصدار التذكرة ");
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

        }
    }

 
    
}

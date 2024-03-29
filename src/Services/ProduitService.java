/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categories;
import Entities.Produit;
import Entities.Reply;
import Entities.Salle;
import Tools.MyConnexion;
import energym.desktop.MainFX;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MSI
 */
public class ProduitService {

    Connection cnx;

    public ProduitService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

    public Produit afficherbyid(int id) {
        Produit u = new Produit();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from produit where id='" + id + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                //  System.out.println(rs);
                u.setId(rs.getInt("id"));
                u.setImage(rs.getString("image"));
                u.setNom(rs.getString("nom"));
            }
        } catch (SQLException ex) {
        }
        return u;
    }

    public int findid(String id) {
        Produit u = new Produit();
        int idd = 0;
        try {
            Statement st = cnx.createStatement();
            String query = "select * from produit where nom='" + id + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                //  System.out.println(rs);
                u.setId(rs.getInt("id"));
                u.setImage(rs.getString("image"));
                idd = rs.getInt("id");
            }
        } catch (SQLException ex) {
        }
        return idd;
    }

    public int afficherSLIM() {
        Produit u = new Produit();
        int image=0;
        try {
            Statement st = cnx.createStatement();
            String query = "select * from produit_user where user_id= "+MainFX.UserconnectedC.getId();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                //  System.out.println(rs);
                image= (rs.getInt("produit_id"));

            }
        } catch (SQLException ex) {
        }
        return image;
    }

    public void ajouter(Produit t) {
        try {
            Statement st;
            Statement pt;
            st = cnx.createStatement();
            pt = cnx.createStatement();
            String query = "INSERT INTO `Produit`( `nom`, `description`,`image`, `prix`, `quantite`) "
                    + "VALUES ('" + t.getNom() + "','" + t.getDescription() + "','" + t.getImage() + "','" + t.getPrix() + "','" + t.getQuantité() + "')";
            st.executeUpdate(query);

            String query2 = "INSERT INTO `produit_categories`( `produit_id`, `categories_id`) "
                    + "VALUES ('" + getproduitassocie_id((t.getNom())) + "','" + t.getNomCategories() + "')";
            System.out.println(query2);
            System.out.println("nom" + t.getNom());
            System.out.println("id= " + getproduitassocie_id(t.getNom()));
            System.out.println("categ= " + t.getNomCategories());
            pt.executeUpdate(query2);
            System.out.println("Produit ajouté avec success");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifier(int id_amodifier, Produit t) {
        try {
            System.out.println("1");

            PreparedStatement st;
            PreparedStatement pt;

            st = cnx.prepareStatement("UPDATE `Produit` SET `nom`=?,`description`=?, `image`=?,`prix`=?,`quantite`=?"
                    + " WHERE id=?");
            System.out.println("2");

            st.setString(1, t.getNom());
            st.setString(2, t.getDescription());
            st.setString(3, t.getImage());
            st.setInt(4, t.getPrix());
            st.setInt(5, t.getQuantité());
            // st.setString(5, t.getImageFile());
            //   st.setDate(2, new java.sql.Date(t.getDate_naissance().getTime()));
            st.setInt(6, id_amodifier);

            pt = cnx.prepareStatement("UPDATE `produit_categories` SET `produit_id`=?,`categories_id`=?"
                    + " WHERE produit_id=?");
            pt.setInt(1, t.getId());
            pt.setInt(2, t.getNomCategories());
            pt.setInt(3, t.getId());
            if (st.executeUpdate() == 1) {
                System.out.println("Produit modifié avec success");
            } else {
                System.out.println("Produit n'existe pas");
            }
            if (pt.executeUpdate() == 1) {
                System.out.println("Produit modifié avec success");
            } else {
                System.out.println("Produit n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifierrating(long id_amodifier, int c) {
        int s = getRating((int) id_amodifier);
        int rating = (s + c) / 2;
        System.out.println(s);
        System.out.println("rating" + rating);
        try {
            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE produit SET `rating`=? WHERE id=?");
            st.setInt(1, rating);
            st.setLong(2, id_amodifier);
            if (st.executeUpdate() == 1) {
                System.out.println("produit modifié avec success");
            } else {
                System.out.println("produit n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriesService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimer(long id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from Produit where id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("Produit n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Produit> afficher() {
        List<Produit> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from Produit";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Produit u = new Produit();
                u.setNom(rs.getString("nom"));
                u.setDescription(rs.getString("description"));
                u.setImage(rs.getString("image"));
                u.setPrix(rs.getInt("prix"));
                u.setQuantité(rs.getInt("quantite"));
                u.setId(rs.getInt("id"));
                u.setRating(rs.getInt("rating"));
                Statement pt = cnx.createStatement();
                String query1 = "select * from produit_categories where produit_id=" + rs.getInt("id");
                ResultSet rss = pt.executeQuery(query1);
                while (rss.next()) {
                    u.setNomCategories(rss.getInt("categories_id"));
                }
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }

    public String getNom() {
        ObservableList<Produit> myList = FXCollections.observableArrayList();
        String titre = "";
        try {
            String requete = "SELECT nom FROM produit";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Produit p = new Produit();
                p.setId(res.getInt(1));
                titre = res.getString(2);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return titre;
    }

    public int getRating(int id) {
        int rate = 0;
        try {
            String requete = "SELECT rating FROM produit where id=" + id;
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Produit p = new Produit();
                rate = res.getInt(1);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rate;
    }

    public String getProduit(float prod) {
        ObservableList<Produit> myList = FXCollections.observableArrayList();
        String nom = "";
        try {
            String requete = "SELECT * FROM produit where id=" + prod;
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Produit p = new Produit();
                p.setId(res.getInt(1));
                nom = res.getString(2);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nom;
    }

    public Produit getProduithome() {
        ObservableList<Produit> myList = FXCollections.observableArrayList();
        String nom = "";
        Produit p = new Produit();

        try {
            String requete = "SELECT * FROM produit where rating =  (SELECT max(rating) FROM produit)";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                p.setId(res.getInt(1));
                p.setNom(res.getString(2));
                p.setDescription(res.getString(3));
                p.setPrix(res.getInt(4));
                p.setQuantité(res.getInt(5));
                p.setImage(res.getString(6));
                p.setRating(res.getInt(8));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }

    public int getProduitqte(float prod) {
        ObservableList<Produit> myList = FXCollections.observableArrayList();
        int qte = 0;
        try {
            String requete = "SELECT * FROM produit where id=" + prod;
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                qte = res.getInt(5);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return qte;
    }

    public int getProduitPrix(int prod) {
        ObservableList<Produit> myList = FXCollections.observableArrayList();
        int prix = 0;
        try {
            String requete = "SELECT * FROM produit where id=" + prod;
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Produit p = new Produit();
                p.setId(res.getInt(1));
                prix = res.getInt(4);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return prix;
    }

    public int getproduitassocie_id(String nom) {
        ObservableList<Produit> myList = FXCollections.observableArrayList();
        int id = 0;
        try {
            String query = "SELECT * FROM `produit` WHERE `nom`='" + nom + "'";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                id = res.getInt(1);
                //   nom=res.getString(2);
                System.out.println("id" + id);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public int getProduitid(String nom) {
        ObservableList<Produit> myList = FXCollections.observableArrayList();
        int id = 0;
        try {
            String query = "SELECT * FROM `Produit` WHERE `nom`='" + nom + "'";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                //  Produit p = new Produit();
                //    p.setId(res.getInt(1));
                id = res.getInt(1);
                System.out.println("id dans getproduitid" + id);
                //   nom=res.getString(2);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public boolean checkProduit(String nom, String description) {
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM Produit WHERE nom='" + nom + "' AND description='" + description + "'";
            ResultSet rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String findByIdimage(int produit) {
        Produit u = new Produit();
        String image = " ";
        try {
            Statement st = cnx.createStatement();
            String query = "select * from Produit where id='" + produit + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {

                image = rs.getString("image");

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }

    public String getCategories(int prod) {
        ObservableList<Categories> myList = FXCollections.observableArrayList();
        String nom = "";
        try {
            String requete = "SELECT * FROM categories where id=" + prod;
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Categories p = new Categories();
                p.setId(res.getInt(1));
                nom = res.getString(3);
                System.out.println("nom" + nom);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nom;
    }
}

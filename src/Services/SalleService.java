/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Event;
import Entities.Salle;
import Entities.User;
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
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SalleService {

    Connection cnx;

    public SalleService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

    public void ajouter(Salle t) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `Salle`( `nom`, `adresse`, `tel`, `mail`, `description`, `image`, `prix1`, `prix2`, `prix3`, `heureo`, `heuref`, `url`) "
                    + "VALUES ('" + t.getNom() + "','" + t.getAdresse() + "','" + t.getTel() + "','" + t.getMail() + "','" + t.getDescription() + "','" + t.getImage() + "','" + t.getPrix1() + "','" + t.getPrix2() + "','" + t.getPrix3() + "','" + t.getHeureo() + "','" + t.getHeuref() + "','" + t.getUrl() + "')";
            st.executeUpdate(query);
            String query1 = "INSERT INTO `user_salle`( `user_id`, `salle_id`) "
                    + "VALUES ('" + MainFX.UserconnectedC.getId() + "','" + getSalleassocie_id(t.getNom()) + "')";
            st.executeUpdate(query1);
            System.out.println("Salle ajoutée avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checksalle(String mail, String tel) {
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `salle` WHERE `mail`='" + mail + "' AND `tel`='" + tel + "'";
            ResultSet rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkLike(int u, int salle) {
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `salle_like` WHERE `salle_id`='" + salle + "' AND `user_id`='" + u + "'";
            ResultSet rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void ajouterLike(int u, int s) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `salle_like`( `salle_id`, `user_id`) "
                    + "VALUES ('" + s + "','" + u + "')";

            st.executeUpdate(query);
            System.out.println("like ajoutée avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerLike(int u, int s) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "delete from salle_like WHERE `salle_id`='" + s + "' AND `user_id`='" + u + "'";
            System.out.println(query);
            st.executeUpdate(query);
            System.out.println("like supprimé avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifier(long id_amodifier, Salle t) {
        try {
            System.out.println("1");
            System.out.println("prix2 service" + t.getPrix2());
            System.out.println("prix3 service" + t.getPrix3());
            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `Salle` SET `nom`=?,`adresse`=?, `tel`=?,`mail`=?,`description`=?,`image`=?,`prix1`=?,`prix2`=?,`prix3`=?,`heureo`=?,`heuref`=?,`url`=? WHERE id=?");
            System.out.println("2");

            st.setString(1, t.getNom());
            st.setString(2, t.getAdresse());
            st.setString(3, t.getTel());
            st.setString(4, t.getMail());
            st.setString(5, t.getDescription());
            st.setString(6, t.getImage());
            st.setString(7, t.getPrix1());
            st.setString(8, t.getPrix2());
            st.setString(9, t.getPrix3());
            st.setString(10, t.getHeureo());
            st.setString(11, t.getHeuref());
            st.setString(12, t.getUrl());
            st.setLong(13, id_amodifier);
            System.out.println(t.getPrix3());
            System.out.println(t.getPrix2());
            if (st.executeUpdate() == 1) {
                System.out.println("Salle modifié avec success");
            } else {
                System.out.println("Salle n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int modifiernblike(long id_amodifier) {
        Salle s = findById((int) id_amodifier);
        int nblike = s.getNblike() + 1;
        try {

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `Salle`SET like_count= '" + nblike + "' WHERE id='" + id_amodifier + "'");
            System.out.println("2");

            if (st.executeUpdate() == 1) {
                System.out.println("Salle modifié avec success");
            } else {
                System.out.println("Salle n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nblike;

    }

    public int modifiernblikeminus(long id_amodifier) {
        Salle s = findById((int) id_amodifier);
        int nblike = s.getNblike() - 1;
        try {
            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `Salle`SET like_count= '" + nblike + "' WHERE id='" + id_amodifier + "'");
            System.out.println("2");

            if (st.executeUpdate() == 1) {
                System.out.println("Salle modifié avec success");
            } else {
                System.out.println("Salle n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nblike;
    }

    public void supprimer(long id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from Salle where id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("Salle n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Salle> afficher() {
        List<Salle> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from Salle";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Salle u = new Salle();
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setAdresse(rs.getString("adresse"));
                u.setTel(rs.getString("tel"));
                u.setMail(rs.getString("mail"));
                u.setDescription(rs.getString("description"));
                u.setImage(rs.getString("image"));
                u.setPrix1(rs.getString("prix1"));
                u.setPrix2(rs.getString("prix2"));
                u.setPrix3(rs.getString("prix3"));
                u.setHeureo(rs.getString("heureo"));
                u.setHeuref(rs.getString("heuref"));
                u.setNblike(rs.getInt("like_count"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }

    public List<Salle> affichergerant() {
        List<Salle> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            Statement st1 = cnx.createStatement();
            String query = "select * from user_salle where user_id=" + MainFX.UserconnectedC.getId() ;
            ResultSet rs = st.executeQuery(query);
            System.out.println(rs);
            while (rs.next()) {
                System.out.println(rs.getInt("salle_id"));
                String query1 = "select * from salle where id=" + rs.getInt("salle_id");
                ResultSet rss = st1.executeQuery(query1);
                  while (rss.next()) {
                Salle u = new Salle();
                u.setId(rss.getInt("id"));
                u.setNom(rss.getString("nom"));
                u.setAdresse(rss.getString("adresse"));
                u.setTel(rss.getString("tel"));
                u.setMail(rss.getString("mail"));
                u.setDescription(rss.getString("description"));
                u.setImage(rss.getString("image"));
                u.setPrix1(rss.getString("prix1"));
                u.setPrix2(rss.getString("prix2"));
                u.setPrix3(rss.getString("prix3"));
                u.setHeureo(rss.getString("heureo"));
                u.setHeuref(rss.getString("heuref"));
                u.setNblike(rss.getInt("like_count"));
                lu.add(u);
                  }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }

    public List<Salle> findByNom(String nom) {
        List<Salle> Salles = afficher();
        List<Salle> resultat = Salles.stream().filter(Salle -> nom.equals(Salle.getNom())).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("la salle n existe pas");
        } else {
            System.out.println("la salle existe");
        }
        return resultat;
    }

    public int getSalleassocie_id(String nom) {
        ObservableList<Salle> myList = FXCollections.observableArrayList();
        int id = 0;
        try {
            String query = "SELECT * FROM `salle` WHERE `nom`='" + nom + "'";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                Salle p = new Salle();
                p.setId(res.getInt(1));
                id = res.getInt(1);
                //   nom=res.getString(2);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public String getSalle(int prod) {
        ObservableList<Salle> myList = FXCollections.observableArrayList();
        String nom = "";
        try {
            String requete = "SELECT * FROM salle where id=" + prod;
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Salle p = new Salle();
                p.setId(res.getInt(1));
                nom = res.getString(2);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nom;
    }

    public Salle findById(int username) {
        Salle u = new Salle();
        String email = " ";
        try {
            Statement st = cnx.createStatement();
            String query = "select * from salle where id='" + username + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {

                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setAdresse(rs.getString("adresse"));
                u.setTel(rs.getString("tel"));
                u.setMail(rs.getString("mail"));
                u.setDescription(rs.getString("description"));
                u.setImage(rs.getString("image"));
                u.setPrix1(rs.getString("prix1"));
                u.setPrix2(rs.getString("prix2"));
                u.setPrix3(rs.getString("prix3"));
                u.setHeureo(rs.getString("heureo"));
                u.setHeuref(rs.getString("heuref"));
                u.setUrl(rs.getString("url"));
                u.setNblike(rs.getInt("like_count"));
                System.out.println(rs.getString("url"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    public void Participer(Salle e, User u) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `user_salle`(`user_id`,`salle_id`)"
                    + "VALUES ('" + u.getId() + "','" + e.getId() + "')";
            st.executeUpdate(query);
            System.out.println("inscription ajouté avec success");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public Boolean check(int idevent , int iduser){
          try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `user_salle` WHERE `user_id`='" + iduser + "'";
            ResultSet rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

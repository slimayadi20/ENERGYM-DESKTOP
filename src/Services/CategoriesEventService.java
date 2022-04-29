/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CategoriesEvent;
import Entities.Event;
import static Services.CryptWithMD5.cryptWithMD5;
import Tools.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriesEventService {

    Connection cnx;

    public CategoriesEventService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

    public void ajouterCategoriesEvent(String t) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `categories_event`( `nom_categorie`) "
                    + "VALUES ('" + t+ "')";
            st.executeUpdate(query);
            System.out.println("CategEvent ajouté avec success");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(long id_amodifier, String t) {
        try {

            PreparedStatement st;
                  st = cnx.prepareStatement("UPDATE `categories_event` SET `nom_categorie`=? WHERE id=?");

            st.setString(1, t);

            st.setLong(2, id_amodifier);
            if (st.executeUpdate() == 1) {
                System.out.println("event modifier avec success");
            } else {
                System.out.println("event n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimer(long id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from categories_event where id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("evenement n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<CategoriesEvent> afficher() {
        List<CategoriesEvent> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from categories_event";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CategoriesEvent u = new CategoriesEvent();

                u.setNomCategories(rs.getString("nom_categorie"));
               u.setId(rs.getInt("id"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }
    public int getbyid(String nom) {
int id = 0  ; 
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `categories_event` WHERE `nom_categorie`='" + nom + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CategoriesEvent u = new CategoriesEvent();

                u.setNomCategories(rs.getString("nom_categorie"));
               u.setId(rs.getInt("id"));
               id=rs.getInt("id") ; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    public String getbynom(int id) {
String nom =""  ; 
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `categories_event` WHERE `id`='" + id + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                CategoriesEvent u = new CategoriesEvent();

                u.setNomCategories(rs.getString("nom_categorie"));
               u.setId(rs.getInt("id"));
               nom=rs.getString("nom_categorie") ; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nom;
    }

}

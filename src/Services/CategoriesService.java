/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categories;
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
 * @author Legion
 */
public class CategoriesService {
    Connection cnx;

    public CategoriesService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

    public void ajouter(String nom) {
          try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `categories`( `nom`,`user_id`) "
                    + "VALUES ('" + nom+ "','" + MainFX.UserconnectedC.getId()+"')";     
            st.executeUpdate(query);
            System.out.println("categories ajouté avec success");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifier(long id_amodifier, String c) {
        try {
            System.out.println("1");

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE categories SET `nom`=? WHERE id=?");
            System.out.println("2");

            st.setString(1, c);
       
            
            st.setLong(2, id_amodifier);
            if (st.executeUpdate() == 1) {
                System.out.println("Categories modifié avec success");
            } else {
                System.out.println("Categories n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriesService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimer(long id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from Categories where id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("Categories n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriesService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    

 
   public List<Categories> afficher() {
        List<Categories> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from Categories";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
               Categories u = new Categories();
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }
   public String getCateg(int prod) {
        ObservableList<Categories> myList = FXCollections.observableArrayList();
        String nom="" ; 
        try {
            String requete = "SELECT * FROM categories where id="+prod;
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                Categories p = new Categories();
            p.setId(res.getInt(1));
              nom=res.getString(3);
              
                 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nom;
 }
 
public int getSalleassocie_id(String nom) {
        ObservableList<Categories> myList = FXCollections.observableArrayList();
int id=0; 
try {
            String query = "SELECT * FROM categories WHERE `nom`='" + nom + "'";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                Categories p = new Categories();
            p.setId(res.getInt(1));
            id= res.getInt(1) ;
           //   nom=res.getString(2);
              
                 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
 }
    
}

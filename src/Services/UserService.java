/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
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


public class UserService  {

    Connection cnx;

    public UserService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

    public void ajouter(User t) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `user`( `nom`, `prenom`, `email`, `roles`, `phone`, `password`, `created_at`, `status`, `image_file`) "
                    + "VALUES ('" + t.getNom()+ "','" + t.getPrenom()+ "','" + t.getEmail() + "','" + t.getRoles()+ "','" + t.getPhone()+ "','" + cryptWithMD5(t.getPassword()) + "','" + t.getCreated_at()+ "','" + t.getStatus()+ "','" + t.getImageFile()+ "')";
            st.executeUpdate(query);
            System.out.println("user ajouter avec success");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifier(long id_amodifier, User t) {
        try {
            System.out.println("1");

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `user` SET `nom`=?,`prenom`=?, `email`=?,`phone`=?,`password`=?,"
                    + "`image_file`=? WHERE id=?");
            System.out.println("2");

            st.setString(1, t.getNom());
            st.setString(2, t.getPrenom());
            st.setString(3, t.getEmail());
       //   st.setString(4, t.getRoles());
            st.setString(4, t.getPhone());
            st.setString(5, cryptWithMD5(t.getPassword()));
            st.setString(6, t.getImageFile());
           // st.setString(7, t.getPassword());
         //   st.setDate(2, new java.sql.Date(t.getDate_naissance().getTime()));
         //   st.setString(7, t.getRole().toString());
           // st.setString(8, t.getUsername());
            st.setLong(7, id_amodifier);
            if (st.executeUpdate() == 1) {
                System.out.println("user modifier avec success");
            } else {
                System.out.println("user n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimer(long id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from user where id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("user n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    

    public boolean checklogin(String email, String password) {
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE `email`='" + email + "' AND `password`='" + password + "'";
            ResultSet rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
   public List<User> afficher() {
        List<User> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from user";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                User u = new User();
                u.setPhone(rs.getString("phone"));
                u.setCreated_at(rs.getDate("created_at"));
                u.setEmail(rs.getString("email"));
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setPassword(cryptWithMD5(rs.getString("password")));
                u.setPrenom(rs.getString("prenom"));
                u.setRoles(rs.getString("roles"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }

}

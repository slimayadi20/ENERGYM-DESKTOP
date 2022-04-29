/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Produit;
import Entities.Reply;
import Tools.MyConnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public String afficher() {
        Produit u = new Produit();
        String image = "";
        try {
            Statement st = cnx.createStatement();
            String query = "select * from produit ";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                //  System.out.println(rs);
                u.setId(rs.getInt("id"));
                u.setImage(rs.getString("image"));
                image = rs.getString("nom");

            }
        } catch (SQLException ex) {
        }
        return image;
    }

}

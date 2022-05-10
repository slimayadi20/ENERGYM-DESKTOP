/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Article;
import Entities.Commentaire;
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


public class ArticleService {

    Connection cnx;

    public ArticleService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

    public void ajouter(Article t) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `article`( `id`, `titre`, `description`, `image`, `date_creation`) "
                    + "VALUES ('" + t.getId()+ "','" + t.getTitre()+ "','" + t.getContenu() + "','" + t.getImageFile()+ "','" + t.getCreated_at() + "')";
            st.executeUpdate(query);
            System.out.println("article ajouté avec success");
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modifier(int id_amodifier, Article t) {
        try {
            System.out.println("1");

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `article` SET `titre`=?,`description`=?,"
                    + "`image`=? WHERE id=?");
            System.out.println("2");

            st.setString(1, t.getTitre());
            st.setString(2, t.getContenu());
            st.setString(3, t.getImageFile());
            
            st.setInt(4, id_amodifier);
            if (st.executeUpdate() == 1) {
                System.out.println("article modifié avec success");
            } else {
                System.out.println("article n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimer(long id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from article where id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("article n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   public List<Article> afficher() {
        List<Article> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from article";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Article u = new Article();
                u.setCreated_at(rs.getDate("date_creation"));
                u.setContenu(rs.getString("description"));
                u.setImageFile(rs.getString("image"));
                            
                u.setId(rs.getInt("id"));
                u.setTitre(rs.getString("titre"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }
   public Article afficherdetail(int id) {
                Article u = new Article();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from article where id="+id;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                u.setCreated_at(rs.getDate("date_creation"));
                u.setContenu(rs.getString("description"));
                u.setImageFile(rs.getString("image"));
                            
                u.setId(rs.getInt("id"));
                u.setTitre(rs.getString("titre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

  
}
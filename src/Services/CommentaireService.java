/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Article;
import Entities.Commentaire;
import Tools.MyConnexion;
import static com.sun.javafx.util.Utils.contains;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CommentaireService {
    Connection cnx;

    public CommentaireService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

    public void ajouter(Commentaire t) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `commentaire`( `id`,`article_id`,`user_id`, `contenu`, `date_creation`) "
                    + "VALUES ('" + t.getId()+"','"+t.getArticle_id()+"','"+t.getUser_id()+"','" + badwords(t.getContenu()) + "','" + t.getCreated_at() + "')";
            st.executeUpdate(query);
            System.out.println("commentaire ajouté avec success");
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modifier(long id_amodifier, Commentaire t) {
        try {
            System.out.println("1");

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `commentaire` SET `titre`=?,`contenu`=?,"
                    + "`image_file`=? WHERE id=?");
            System.out.println("2");

            st.setString(2, t.getContenu());
                        
            st.setLong(7, id_amodifier);
            if (st.executeUpdate() == 1) {
                System.out.println("Commentaire modifié avec success");
            } else {
                System.out.println("Commentaire n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimer(long id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from Commentaire where id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("Commentaire n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   public List<Commentaire> afficher() {
        List<Commentaire> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from commentaire";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Commentaire u = new Commentaire();
                u.setCreated_at(rs.getDate("date_creation"));
                u.setContenu(rs.getString("contenu"));
                u.setId(rs.getInt("id"));
                u.setUser_id(rs.getInt("user_id"));
                u.setArticle_id(rs.getInt("article_id"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }
   public List<Commentaire> afficherCommentaireByarticle(int id) {
        List<Commentaire> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from commentaire where article_id="+id;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Commentaire u = new Commentaire();
                u.setCreated_at(rs.getDate("date_creation"));
                u.setContenu(rs.getString("contenu"));
                u.setId(rs.getInt("id"));
                u.setUser_id(rs.getInt("user_id"));
                u.setArticle_id(rs.getInt("article_id"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }
    //Métier BadWords
    public String badwords(String comment){
        
        String result = comment;
        String[] badwords = {"tuer","sang","con","merde","vomir","conne","putain"};
        
        for(String word : badwords){
            //System.out.println(result);
            if(contains(result,word)){
                result=result.replace(word,"****");
                //break;
            }
            //System.out.println(result);
        }
        return result;
    }
}
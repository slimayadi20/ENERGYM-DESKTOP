/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Event;
import Entities.Participation;
import Entities.User;
import Tools.MyConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventService {

    Connection cnx;

    public EventService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

    public void ajouterEvent(Event t) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `evenement`(`nom_categorie_id`,`nom_event`, `date_event`, `description_event`, `lieu_event`, `nbr_participants_event`, `image`, `etat`) "
                    + "VALUES ('" + t.getCategories() + "','" + t.getnomEvent() + "','" + t.getDateEvent() + "','" + t.getDescriptionEvent() + "','" + t.getLieuEvent() + "','" + t.getNbrPlacesEvent() + "','" + (t.getImageFile()) + "','" + "incomplet" + "')";
            st.executeUpdate(query);
            System.out.println("Event ajouté avec success");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(long id_amodifier, Event t) {
        try {
            System.out.println("1");

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `evenement` SET `nom_categorie_id`=?,`nom_event`=?, `date_event`=?,`description_event`=?,`lieu_event`=?,`nbr_participants_event`=?,`image`=? WHERE id=?");
            System.out.println("2");

            st.setInt(1, t.getCategories());
            st.setString(2, t.getnomEvent());
            st.setDate(3, (Date) t.getDateEvent());
            st.setString(4, t.getDescriptionEvent());
            st.setString(5, t.getLieuEvent());
            st.setString(6, t.getNbrPlacesEvent());
            st.setString(7, t.getImageFile());
            st.setLong(8, id_amodifier);
            if (st.executeUpdate() == 1) {
                System.out.println("event modifier avec success");
            } else {
                System.out.println("event n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   public void modifierEvent(long id_amodifier, String t,String etat) {
        try {
            System.out.println("1");

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `evenement` SET `nbr_participants_event`=?,`etat`=? WHERE id=?");
            System.out.println("2");

           
            st.setString(1, t);
            st.setString(2, etat);
            st.setLong(3, id_amodifier);
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
            String query = "delete from evenement where id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("evenement n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void supprimerparticipation(long id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from participation where id_event_id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("evenement n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Event> afficher() {
        List<Event> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from evenement";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Event u = new Event();

                u.setDateEvent(rs.getDate("date_event"));
                u.setCategories(rs.getInt("nom_categorie_id"));
                u.setId(rs.getInt("id"));
                u.setnomEvent(rs.getString("nom_event"));
                u.setDescriptionEvent(rs.getString("description_event"));
                u.setLieuEvent(rs.getString("lieu_event"));
                u.setImageFile(rs.getString("image"));
                u.setEtatEvent(rs.getString("etat"));
                u.setNbrPlacesEvent(rs.getString("nbr_participants_event"));
                u.setTime(rs.getTimestamp("time"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }
    public List<Event> afficher2() {
        List<Event> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select  * from evenement LIMIT 3";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Event u = new Event();

                u.setDateEvent(rs.getDate("date_event"));
                u.setCategories(rs.getInt("nom_categorie_id"));
                u.setId(rs.getInt("id"));
                u.setnomEvent(rs.getString("nom_event"));
                u.setDescriptionEvent(rs.getString("description_event"));
                u.setLieuEvent(rs.getString("lieu_event"));
                u.setImageFile(rs.getString("image"));
                u.setEtatEvent(rs.getString("etat"));
                u.setNbrPlacesEvent(rs.getString("nbr_participants_event"));
                u.setTime(rs.getTimestamp("time"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }
    public List<Participation> afficherParticipants() {
        List<Participation> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from participation";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Participation u = new Participation();

                u.setIdevent(rs.getInt("id_event_id"));
                u.setIduser(rs.getInt("id_user_id"));
                u.setId(rs.getInt("id"));
             
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }
    public List<Event> fetchParticipationByUser(int id ) {
        List<Event> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from participation where id_user_id="+id;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Event u = new Event();
u=EventDetailFront(rs.getInt("id_event_id")) ;
             
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }
    public int affichernbParticipants() {
int nbr =0 ; 
try {
            Statement st = cnx.createStatement();
            String query = "select count(*) from participation";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Participation u = new Participation();

            nbr = rs.getInt(1) ; 
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nbr;
    }

    public String findByIdimage(int username) {
        Event u = new Event();
        String email = " ";
        try {
            Statement st = cnx.createStatement();
            String query = "select * from evenement where id='" + username + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {

                email = rs.getString("image");

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return email;
    }
    public String findByIdnom(int username) {
        Event u = new Event();
        String email = " ";
        try {
            Statement st = cnx.createStatement();
            String query = "select * from evenement where id='" + username + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {

                email = rs.getString("nom_event");

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return email;
    }

    public Event EventDetailFront(int username) {
        Event u = new Event();

        try {
            Statement st = cnx.createStatement();
            String query = "select * from evenement where id='" + username + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {

                u.setDateEvent(rs.getDate("date_event"));
                u.setCategories(rs.getInt("nom_categorie_id"));
                u.setId(rs.getInt("id"));
                u.setnomEvent(rs.getString("nom_event"));
                u.setDescriptionEvent(rs.getString("description_event"));
                u.setLieuEvent(rs.getString("lieu_event"));
                u.setImageFile(rs.getString("image"));
                u.setEtatEvent(rs.getString("etat"));
                u.setNbrPlacesEvent(rs.getString("nbr_participants_event"));
                u.setTime(rs.getTimestamp("time"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public void Participer(Event e, User u, String verification) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `participation`(`id_user_id`,`id_event_id`, `verification_code`) "
                    + "VALUES ('" + u.getId() + "','" + e.getId() + "','" + verification + "')";
            st.executeUpdate(query);
            System.out.println("participation ajouté avec success");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public Boolean check(int idevent , int iduser){
          try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `participation` WHERE `id_user_id`='" + iduser + "' AND `id_event_id`='" + idevent + "'";
            ResultSet rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public User checkemail(int idevent ){
              User u = new User();
          try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `participation` WHERE`id_event_id`='" + idevent + "'";
            ResultSet rs = st.executeQuery(query);
 if (rs.next()) {

                u.setId(rs.getInt("id_user_id"));

            }    
          } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

}

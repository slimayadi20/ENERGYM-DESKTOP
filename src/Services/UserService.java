/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.LoginAttempt;
import Entities.User;
import static Services.CryptWithMD5.cryptWithMD5;
import Tools.MyConnexion;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class UserService {

    Connection cnx;

    public UserService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

    public void ajouter(User t) {
        try {
            Statement st;
            st = cnx.createStatement();
            Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32, 64, 1, 15 * 1024, 2);
            String hash = encoder.encode(t.getPassword());
            String query = "INSERT INTO `user`( `nom`, `prenom`, `email`, `roles`, `phone`, `password`, `created_at`, `status`, `image_file` ,`activation_token`,`birthday`) "
                    + "VALUES ('" + t.getNom() + "','" + t.getPrenom() + "','" + t.getEmail() + "','" + t.getRoles() + "','" + t.getPhone() + "','" + hash + "','" + t.getCreated_at() + "','" + t.getStatus() + "','" + t.getImageFile() + "','" + t.getActivation_token() + "','" + t.getBirthday() + "')";
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
            st = cnx.prepareStatement("UPDATE `user` SET `nom`=?,`prenom`=?, `email`=?,`phone`=?,"
                    + "`image_file`=? ,`birthday`=? WHERE id=?");
            System.out.println("2");

            st.setString(1, t.getNom());
            st.setString(2, t.getPrenom());
            st.setString(3, t.getEmail());
            st.setString(4, t.getPhone());
            st.setString(5, t.getImageFile());
            st.setString(6, t.getBirthday());
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

    public void ajouterLoginAttempt(LoginAttempt t) throws ParseException {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `login_attempt`( `ip_address`, `date`, `username`,`fail`,`image`) "
                    + "VALUES ('" + t.getIpaddress() + "','" + t.getDate() + "','" + t.getEmail() + "','" + 1 + "','" + t.getImage() + "')";
            st.executeUpdate(query);
            if (countRecentLoginAttempts(t.getEmail()) > 4) {
                Statement stm = cnx.createStatement();
                String queryy = "UPDATE login_attempt SET fail= '" + 0 + "' WHERE username='" + t.getEmail() + "'";
                stm.executeUpdate(queryy);
            }
            System.out.println("login attempt ajouter avec success");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checklogin(String username, String password) {
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE `email`='" + username + "' AND `password`='" + password + "'";

            ResultSet rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean checkloginargon(String username, String password) throws SQLException {
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE `email`='" + username + "'";

            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                String Mypasssword = password;
                Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32, 64, 1, 15 * 1024, 2);
                String hash = encoder.encode(password);
                boolean validPassword = encoder.matches(Mypasssword, rs.getString(4));
                if (validPassword) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public int countRecentLoginAttempts(String email) throws ParseException {
        /*  Date date = new Date(System.currentTimeMillis());
        final String current = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = df.parse(current);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, 3);
        String after = df.format(cal.getTime());
        //    System.out.println("Current time now : " + current);
        //  System.out.println("After adding 3 mins : " + after);*/

        try {
            Statement st = cnx.createStatement();
            String query = "SELECT count(*) FROM `login_attempt` WHERE `username`='" + email + "' AND `fail`='" + 1 + "'";
            //  System.out.println(query);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                System.out.println("number of attempts : ");
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
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
                u.setStatus(rs.getInt("status"));
                u.setNom(rs.getString("nom"));
                u.setImageFile(rs.getString("image_file"));
                System.out.println(rs.getString("image_file"));
                u.setPassword(cryptWithMD5(rs.getString("password")));
                u.setPrenom(rs.getString("prenom"));
                u.setRoles(rs.getString("roles"));
                u.setBirthday(rs.getString("birthday"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }

    public List<User> affichergerant(int id) {
        List<User> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            Statement pt = cnx.createStatement();
            Statement qt = cnx.createStatement();

            String query = "select * from user_salle where user_id=" + id;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String query2 = "select * from user_salle where salle_id=" + rs.getInt("salle_id");
                ResultSet rss = pt.executeQuery(query2);
                while (rss.next()) {
                    String query3 = "select * from user where id=" + rss.getInt("user_id");
                    ResultSet rsss = qt.executeQuery(query3);
                    while (rsss.next()) {
                        User u = new User();
                        u.setPhone(rsss.getString("phone"));
                        u.setCreated_at(rsss.getDate("created_at"));
                        u.setEmail(rsss.getString("email"));
                        u.setId(rsss.getInt("id"));
                        u.setStatus(rsss.getInt("status"));
                        u.setNom(rsss.getString("nom"));
                        u.setImageFile(rsss.getString("image_file"));
                        System.out.println(rsss.getString("image_file"));
                        u.setPassword(cryptWithMD5(rsss.getString("password")));
                        u.setPrenom(rsss.getString("prenom"));
                        u.setRoles(rsss.getString("roles"));
                        u.setBirthday(rsss.getString("birthday"));
                        lu.add(u);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }

    public List<LoginAttempt> afficherloginattempt(String email) {
        List<LoginAttempt> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from login_attempt WHERE `username`='" + email + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                LoginAttempt u = new LoginAttempt();
                u.setIpaddress(rs.getString("ip_address"));
                u.setDate(rs.getDate("date"));
                u.setEmail(rs.getString("username"));
                u.setImage(rs.getString("image"));

                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }

    public List<User> sortByDate() {
        List<User> users = afficher();
        List<User> resultat = users.stream().sorted(Comparator.comparing(User::getCreated_at)).collect(Collectors.toList());
        return resultat;

    }

    public List<User> sortByStatus() {
        List<User> users = afficher();
        List<User> resultat = users.stream().sorted(Comparator.comparing(User::getStatus)).collect(Collectors.toList());
        return resultat;
    }

    public List<User> sortByRoles() {
        List<User> users = afficher();
        List<User> resultat = users.stream().sorted(Comparator.comparing(User::getRoles)).collect(Collectors.toList());
        return resultat;
    }

    public User findByUsername(String username) {
        User u = new User();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from user where email='" + username + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                u.setPhone(rs.getString("phone"));
                u.setCreated_at(rs.getDate("created_at"));
                u.setEmail(rs.getString("email"));
                u.setId(rs.getInt("id"));
                u.setStatus(rs.getInt("status"));
                u.setNom(rs.getString("nom"));
                u.setImageFile(rs.getString("image_file"));
                u.setPassword(cryptWithMD5(rs.getString("password")));
                u.setPrenom(rs.getString("prenom"));
                u.setRoles(rs.getString("roles"));
                u.setBirthday(rs.getString("birthday"));
                u.setActivation_token(rs.getString("activation_token"));
                System.out.println("found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public String findById(int username) {
        User u = new User();
        String email = " ";
        try {
            Statement st = cnx.createStatement();
            String query = "select * from user where id='" + username + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {

                email = rs.getString("email");

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return email;
    }

    public String findByIdimage(int username) {
        User u = new User();
        String email = " ";
        try {
            Statement st = cnx.createStatement();
            String query = "select * from user where id='" + username + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {

                email = rs.getString("image_file");

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return email;
    }

    public boolean emailExist(String email) {

        try {
            Statement st = cnx.createStatement();
            String query = "select * from user where email='" + email + "'";
            ResultSet rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public void updatemdp(String email, String mdp) throws SQLException {
        Statement stm = cnx.createStatement();
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32, 64, 1, 15 * 1024, 2);
        String hash = encoder.encode(mdp);
        String query = "UPDATE user SET password= '" + hash + "' WHERE email='" + email + "'";
        stm.executeUpdate(query);

    }

    public int affichernumber(String role) {
        if (role.equals("ROLE_GERANT")) {
            try {
                Statement st = cnx.createStatement();
                String query = "select count(*) from user where roles='ROLE_GERANT'";
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    int theCount = rs.getInt(1);
                    System.out.println(theCount);
                    return theCount;

                }
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (role.equals("ROLE_CLIENT")) {
            try {
                Statement st = cnx.createStatement();
                String query = "select count(*) from user where roles='ROLE_CLIENT'";
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    int theCount = rs.getInt(1);
                    System.out.println(theCount);
                    return theCount;

                }
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return 0;
    }
    public void modifierLoginAttempt() {
        try {
            System.out.println("1");

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `login_attempt` SET `image`=?  WHERE image is NULL");
            System.out.println("2");

            st.setString(1, "");
            
            if (st.executeUpdate() == 1) {
                System.out.println("user modifier avec success");
            } else {
                System.out.println("user n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void modifierLoginAttemptfail() {
        try {
            System.out.println("1");

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `login_attempt` SET `fail`=?  WHERE fail is NULL");
            System.out.println("2");

            st.setInt(1, 0);
            
            if (st.executeUpdate() == 1) {
                System.out.println("user modifier avec success");
            } else {
                System.out.println("user n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void RapportHCbyUser(User us) throws IOException, DocumentException, IOException {
        List<LoginAttempt> lHc = afficherloginattempt(us.getEmail());
        String filename = "";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("src/css/test1.pdf"));
        document.open();

        Font f24 = FontFactory.getFont(FontFactory.HELVETICA, 24f);
        Font f16 = FontFactory.getFont(FontFactory.HELVETICA, 14f);
        Font f12 = FontFactory.getFont(FontFactory.HELVETICA, 10f);

        try {
            document.add(new Paragraph("Votre Historique de Connexion \n\n", f24));
            document.add(new Paragraph("Monsieur/Madame " + us.getNom() + " identifant " + us.getId() + " suite a votre demande voici votre historique de connexion :  \n ", f16));
            document.add(new Paragraph("Nombre Total de connexion : " + lHc.size() + "\n ", f16));

        } catch (DocumentException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        PdfPTable table = new PdfPTable(4);
        try {
            table.setWidths(new float[]{8, 35, 35, 35});
        } catch (DocumentException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.addCell("");
        table.addCell("Ip Adresse");
        table.addCell("Image");
        table.addCell("Date");

        for (int i = 0; i < lHc.size(); i++) {
            table.addCell("" + (i + 1));
            table.addCell(lHc.get(i).getIpaddress());
            // System.out.println(lHc.get(i).getImage());
            // Image img = Image.getInstance("src\\images\\" + lHc.get(i).getImage());
            if (lHc.get(i).getImage().isEmpty()) {
                filename = "src\\images\\images.jpg";
                System.out.println(filename);
                Image img = Image.getInstance(filename);
                img.scalePercent(1);// Creating an Image object 
                PdfPCell cell = new PdfPCell();
                cell.addElement(new Chunk(img, 5, -5));

                table.addCell(cell);
            } else {
                filename = "src\\images\\" + lHc.get(i).getImage();
                System.out.println(filename);
                Image img = Image.getInstance(filename);
                img.scalePercent(28);// Creating an Image object 
                PdfPCell cell = new PdfPCell();
                cell.addElement(new Chunk(img, 5, -5));

                cell.setFixedHeight(80);
                cell.setPaddingTop(50);
                table.addCell(cell);
            }

            table.addCell(lHc.get(i).getDate().toString());
        }

        try {

            document.add(table);
        } catch (DocumentException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            document.add(new Paragraph("\nPour assurer une meilleur transparence entre nous ENERGYM vous donne accés a tous vos données , et ceci on conformité avec la loi de vie privéé ", f12));

        } catch (DocumentException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        document.close();
        try {
            Desktop.getDesktop().open(new File("src/css/test1.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

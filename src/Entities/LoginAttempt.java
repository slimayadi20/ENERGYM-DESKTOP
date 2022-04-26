/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author MSI
 */
public class LoginAttempt {
    private int id ; 
private String ipaddress ; 
private Date date ; 
private String email ; 
String image ; 

    public LoginAttempt(int id, String ipaddress, Date date, String email) {
        this.id = id;
        this.ipaddress = ipaddress;
        this.date = date;
        this.email = email;
    }

    public LoginAttempt(String ipaddress, Date date, String email, String image) {
        this.ipaddress = ipaddress;
        this.date = date;
        this.email = email;
        this.image= image ; 
    }

    public LoginAttempt() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "LoginAttempt{" + "id=" + id + ", ipaddress=" + ipaddress + ", date=" + date + ", email=" + email + ", image=" + image + '}';
    }
    
 

}

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

    public LoginAttempt(int id, String ipaddress, Date date, String email) {
        this.id = id;
        this.ipaddress = ipaddress;
        this.date = date;
        this.email = email;
    }

    public LoginAttempt(String ipaddress, Date date, String email) {
        this.ipaddress = ipaddress;
        this.date = date;
        this.email = email;
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
   @Override
    public String toString() {
        return "login_attempt{" + "id=" + id + ", ip_adress=" + ipaddress + ", date=" + date + ", email=" + email + ", email=" + email  + '}'+"\n";
    }

}

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
public class User {

    private String nom;
    int id;
    private String prenom;
    private String password;
    private String phone;
    private String email;
    private String imageFile;
    private String roles;
    private int status;
    private Date created_at;

    public User() {
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public User(int id, String nom, String prenom, String password, String phone, String email, String imageFile) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.imageFile = imageFile;
    }

    public User(String nom, String prenom, String password, String phone, String email, String imageFile, String roles, int status, Date created_at) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.imageFile = imageFile;
        this.roles = roles;
        this.status = status;
        this.created_at = created_at;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

}

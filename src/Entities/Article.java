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
public class Article {

    int id;
    private String titre;
    private String contenu;
    private String imageFile;
    private Date created_at;

    public Article() {
    }
    
    public Article(String titre, String contenu, String imageFile, Date created_at) {
        this.titre = titre;
        this.contenu = contenu;
        this.imageFile = imageFile;
        this.created_at = created_at;
    }

    public Article(int id, String titre, String contenu, String imageFile) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.imageFile = imageFile;
        this.created_at = created_at;
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", titre=" + titre + ", contenu=" + contenu + ", imageFile=" + imageFile + ", created_at=" + created_at + '}';
    }
   
}

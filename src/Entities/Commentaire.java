/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.util.Date;

/**
 *
 * @author MSI
 */
public class Commentaire {
    
    int id;
    private String contenu;
    private Date created_at;
    private int user_id ; 
    private int article_id ; 
    
    public Commentaire() {
    }

    public Commentaire(String contenu, Date created_at) {
        this.contenu = contenu;
        this.created_at = created_at;
    }

    public Commentaire(int id, String contenu, Date created_at, int user_id, int article_id) {
        this.id = id;
        this.contenu = contenu;
        this.created_at = created_at;
        this.user_id = user_id;
        this.article_id = article_id;
    }

    public Commentaire(String contenu, Date created_at, int user_id, int article_id) {
        this.contenu = contenu;
        this.created_at = created_at;
        this.user_id = user_id;
        this.article_id = article_id;
    }
    

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    
        
}

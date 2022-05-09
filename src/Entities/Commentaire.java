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
public class Commentaire {
    
    int id;
    private String contenu;
    private Date created_at;
    
    public Commentaire() {
    }

    public Commentaire(String contenu, Date created_at) {
        this.contenu = contenu;
        this.created_at = created_at;
    }

    public Commentaire(int id, String contenu, Date created_at) {
        this.id = id;
        this.contenu = contenu;
        this.created_at = created_at;
    }

    public Commentaire(int id, String contenu) {
        this.id = id;
        this.contenu = contenu;
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

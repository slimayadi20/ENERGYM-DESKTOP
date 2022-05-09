/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Legion
 */
public class Categories {
   int id;
    String nom;

    public Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Categories(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Categories(String nom) {
        this.nom = nom;
    }

    public Categories() {
    }

    @Override
    public String toString() {
        return "Categories{" + "id=" + id + ", nom=" + nom + '}';
    }
    
    
}

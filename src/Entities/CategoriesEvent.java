/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author user
 */
public class CategoriesEvent {
    int id  ; 
    String nomCategories ; 

    public CategoriesEvent(int id, String nomCategories) {
        this.id = id;
        this.nomCategories = nomCategories;
    }

    public CategoriesEvent(String nomCategories) {
        this.nomCategories = nomCategories;
    }

    public CategoriesEvent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCategories() {
        return nomCategories;
    }

    public void setNomCategories(String nomCategories) {
        this.nomCategories = nomCategories;
    }

    @Override
    public String toString() {
        return "CategoriesEvent{" + "id=" + id + ", nomCategories=" + nomCategories + '}';
    }
    
}

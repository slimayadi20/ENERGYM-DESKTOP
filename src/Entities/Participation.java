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
public class Participation {
    int id ; 
    int iduser ; 
    int idevent ; 

    public Participation(int id, int iduser, int idevent) {
        this.id = id;
        this.iduser = iduser;
        this.idevent = idevent;
    }

    public Participation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    @Override
    public String toString() {
        return "Participation{" + "id=" + id + ", iduser=" + iduser + ", idevent=" + idevent + '}';
    }
    
}

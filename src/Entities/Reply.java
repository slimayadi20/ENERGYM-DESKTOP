/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author MSI
 */
public class Reply {
    int id ; 
    String contenu ; 
    String email_receiver ; 
    String email_sender ; 
    int reclamation ; 

    public Reply(String contenu, String email_receiver, String email_sender, int reclamation) {
        this.contenu = contenu;
        this.email_receiver = email_receiver;
        this.email_sender = email_sender;
        this.reclamation = reclamation;
    }

    public Reply(int id, String contenu, String email_receiver, String email_sender, int reclamation) {
        this.id = id;
        this.contenu = contenu;
        this.email_receiver = email_receiver;
        this.email_sender = email_sender;
        this.reclamation = reclamation;
    }

    public Reply() {
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

    public String getEmail_receiver() {
        return email_receiver;
    }

    public void setEmail_receiver(String email_receiver) {
        this.email_receiver = email_receiver;
    }

    public String getEmail_sender() {
        return email_sender;
    }

    public void setEmail_sender(String email_sender) {
        this.email_sender = email_sender;
    }

    public int getReclamation() {
        return reclamation;
    }

    public void setReclamation(int reclamation) {
        this.reclamation = reclamation;
    }

    @Override
    public String toString() {
        return "Reply{" + "id=" + id + ", contenu=" + contenu + ", email_receiver=" + email_receiver + ", email_sender=" + email_sender + ", reclamation=" + reclamation + '}';
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;
import java.util.Date;

/**
 *
 * @author gebsi
 */
public class Reclamation {
   private int id;
    private String nom;
    private String prenom;
    private String email;
    private int tel;
    private String etat;
    private String description ;
   private Date date_reclamation;

    public Reclamation() {
    }

    public Reclamation(int id, String nom, String prenom, String email, int tel, String etat, String description, Date date_reclamation) {
        this.id = id;

        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.etat = etat;
        this.description = description;
        this.date_reclamation = date_reclamation;
    }

    public Reclamation(String nom, String prenom, String email, int tel, String etat, String description, Date date_reclamation) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.etat = etat;
        this.description = description;
        this.date_reclamation = date_reclamation;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_reclamation() {
        return date_reclamation;
    }

    public void setDate_reclamation(Date date_reclamation) {
        this.date_reclamation = date_reclamation;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", type_id=" + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", tel=" + tel + ", etat=" + etat + ", description=" + description + ", date_reclamation=" + date_reclamation + '}';
    }


}
 


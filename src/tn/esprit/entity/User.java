/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.entity;

import java.sql.Date;

/**
 *
 * @author gabsi
 */
public class User {
    private int id ;
    public String nom ;
    private String prenom ;
    private String email ;
    private String pwd ;
    private int numTel  ;
    private Date dateNaissc  ;   
    private String adresse ;
    private String sexe ;
    private Role role ;                    

    
    public User() {
    }

    public User(String nom, String prenom, String email, String pwd, int numTel, Date dateNaissc, String adresse, String sexe, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pwd = pwd;
        this.numTel = numTel;
        this.dateNaissc = dateNaissc;
        this.adresse = adresse;
        this.sexe = sexe;
        this.role = role;
    }

    

    public User(int id, String nom, String prenom, String email, String pwd, int numTel, Date dateNaissc, String adresse, String sexe, Role role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pwd = pwd;
        this.numTel = numTel;
        this.dateNaissc = dateNaissc;
        this.adresse = adresse;
        this.sexe = sexe;
        this.role = role;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public Date getDateNaissc() {
        return dateNaissc;
    }

    public void setDateNaissc(Date dateNaissc) {
        this.dateNaissc = dateNaissc;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", pwd=" + pwd + ", numTel=" + numTel + ", dateNaissc=" + dateNaissc + ", adresse=" + adresse + ", sexe=" + sexe + ", role=" + role + '}';
    }

    }



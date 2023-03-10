/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.services;

import java.io.IOException;
import tn.esprit.entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.scene.control.Alert;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import tn.esprit.entity.Role;
import tn.esprit.tools.MaConnection;
import tn.esprit.tools.sendmail;

/**
 *
 * @author gabsi
 */
    public class UserService implements InterfaceService<User> {
    public static UserSession userSession;
    Connection cnx2;
    public static int code;
    Connection cnx;

    public UserService() {
         cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(User u) {
    
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO user( nom,prenom,email,pwd,numtel,datenaissc,adresse,sexe,role) "
                    + "VALUES ('" + u.getNom()+ "','" + u.getPrenom()+ "','" + u.getEmail() + "','" + u.getPwd()+ "','" + u.getNumTel() + "','" + u.getDateNaissc() + "','" + u.getAdresse() + "','" + u.getSexe() + "','" + u.getRole() + "')";
            st.executeUpdate(query);
            System.out.println("user ajouter avec success");
   
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void modifier(int id, User p) throws SQLException {
        try {
          

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `user` SET `nom`=?,`prenom`=?, `email`=?,`pwd`=?,`numtel`=?,`dateNaissc`=?,"
                    + "`adresse`=?,`sexe`=?,`role`=? WHERE id=?") ;
            st.setString(1, p.getNom());
            st.setString(2, p.getPrenom());
            st.setString(3, p.getEmail());
            st.setString(4, p.getPwd());
            st.setInt(5, p.getNumTel());
            st.setDate(6,new java.sql.Date(p.getDateNaissc().getTime()));   
            st.setString(7,p.getAdresse());
            st.setString(8,p.getSexe()); 
            st.setString(9, p.getRole().toString());
            st.setInt(10,id);
            
            if (st.executeUpdate() == 1) {
                System.out.println("user modifier avec success");
            } else {
                System.out.println("user n'existe pas");
            }
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }

    }
 
    @Override
    public List<User> getAll() throws SQLException{
      List<User> user = new ArrayList<>();
    String requete = "select * from user";
    try {
        PreparedStatement pst = cnx.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setEmail(rs.getString("email"));
            u.setPwd(rs.getString("pwd"));
            u.setNumTel(rs.getInt("numtel"));
            u.setDateNaissc(rs.getDate("datenaissc"));
            u.setAdresse(rs.getString("adresse"));
            u.setSexe(rs.getString("sexe"));
            u.setRole(Role.valueOf(rs.getString("role")));
            user.add(u);
            
        }
        System.out.println(user);
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
       
    }
    
    return user;
}
   

    @Override
  
    public List<User> findById(int id) {
       List<User> user = new ArrayList<>();
    String requete = "select * from user where id = ?";
    try {
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setEmail(rs.getString("email"));
            u.setPwd(rs.getString("pwd"));
            u.setNumTel(rs.getInt("numtel"));
            u.setDateNaissc(rs.getDate("datenaissc"));
            u.setAdresse(rs.getString("adresse"));
            u.setSexe(rs.getString("sexe"));
            u.setRole(Role.valueOf(rs.getString("role")));
            user.add(u);
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return user;
    }
  public void supprimer(int id) {
 try {
            String sql = "DELETE FROM user WHERE id="+id+"";
            PreparedStatement ste  = cnx.prepareStatement(sql);
           
            ste.executeUpdate();
            System.out.println("user Supprimée ");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    
    
    }
  public User findByEmail(String email) {
    User u = null;
    try {
        Statement st = cnx.createStatement();
        String query = "SELECT * FROM user WHERE email='" + email + "'";
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            u = new User();
            u.setId(rs.getInt("id"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setEmail(rs.getString("email"));
            u.setPwd(rs.getString("pwd"));
            u.setNumTel(rs.getInt("numTel"));
            u.setAdresse(rs.getString("adresse"));
            u.setDateNaissc(rs.getDate("dateNaissc"));
            u.setSexe(rs.getString("sexe"));  
            u.setRole(Role.valueOf(rs.getString("role")));
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return u;
}

  public boolean checklogin(String email, String password) {
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE `email`='" + email + "' AND `pwd`='" + password + "'";
            ResultSet rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
  
  public List<User> findByEmail1(String email) throws SQLException {
        List<User> users = getAll();
        List<User> resultat = users.stream().filter(user -> email.equals(user.getEmail())).collect(Collectors.toList());
        if (resultat.isEmpty()) {
            System.out.println("l utilisateur n existe pas");
        } else {
            System.out.println("l utilisateur existe");
        }
        return resultat;
    }

  public User GetUserByMailSession(String mail) {
        User user = null;


        String pass = "";
        try {
            String requete = "Select id, nom, prenom, email, pwd, numTel, dateNaissc, adresse, sexe, role from user where email = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, mail);
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next()) {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        mail,
                        pass,
                        rs.getInt("numTel"),
                        rs.getDate("dateNaissc"),
                        rs.getString("adresse"),
                        rs.getString("adresse"),
                        Role.valueOf(rs.getString("role"))
                );

                System.out.println(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (StringIndexOutOfBoundsException ex) {
            /*Login_pageController lc = new Login_pageController();
            new animatefx.animation.Shake(lc.getPasswordtxt()).play();
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            lc.getPasswordtxt().setEffect(in);
             */
        }
        return user;
    }
    }
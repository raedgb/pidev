/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.services;

import tn.esprit.entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entity.Role;
import tn.esprit.tools.MaConnection;
/**
 *
 * @author gabsi
 */
    public class UserService implements InterfaceService<User> {

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
        List<User> users = new ArrayList<>();
        try {
            String sql = "select* from user";
            Statement st = cnx.createStatement();
            ResultSet s = st.executeQuery(sql);
            System.out.println("connextion etablie");
            while (s.next()) {
                
         
         
        User u = new User(s.getInt(1),s.getString("nom") ,s.getString("prenom"), s.getString("email"), s.getString("pwd"), s.getInt("numtel"),s.getDate("datenaissc"),s.getString("adresse"),s.getString("sexe") ,Role.valueOf(s.getString("role"))  );
          
                users.add(u);
                System.out.print(users);

            }
        } catch (SQLException ex) {
            System.out.println("arze "+ex.getMessage());
        }
        return users;
    }
   

    @Override
  
    public List<User> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

  


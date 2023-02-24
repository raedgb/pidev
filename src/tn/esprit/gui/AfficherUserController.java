/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tn.esprit.entity.Role;
import tn.esprit.entity.User;
import tn.esprit.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author gabsi
 */
public class AfficherUserController implements Initializable {
Connection cnx;
    @FXML
    private TableColumn<?, ?> nomcol;
    @FXML
    private TableColumn<?, ?> prenomcol;
    @FXML
    private TableColumn<?, ?> pwdcol;
    @FXML
    private TableColumn<?, ?> numcol;
    @FXML
    private TableColumn<?, ?> datecol;
    @FXML
    private TableColumn<?, ?> adressecol;
    @FXML
    private TableColumn<?, ?> sexecol;
    @FXML
    private TableView<User> usertable;
    @FXML
    private TableColumn<?,?> emailcol;
    @FXML
    private TableColumn<?,?> rolecol;

    ObservableList<User> userList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            /*   try {
            UserService us = new UserService();
            List<User> user = us.getAll();
            ObservableList<User> list = FXCollections.observableArrayList(user);
            nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenomcol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
            pwdcol.setCellValueFactory(new PropertyValueFactory<>("pwd"));
            numcol.setCellValueFactory(new PropertyValueFactory<>("numTel"));
            datecol.setCellValueFactory(new PropertyValueFactory<>("dateNaissc"));
            adressecol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            sexecol.setCellValueFactory(new PropertyValueFactory<>("sexe"));
            rolecol.setCellValueFactory(new PropertyValueFactory<>("role"));
            usertable.setItems(list);
          

        }
        catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }

}*/

      try {
            Connection con = MaConnection.getInstance().getCnx();
            ResultSet rs = con.createStatement().executeQuery("select * from user");

            while (rs.next()) {
                userList.add(new User(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("pwd"),
                        rs.getInt("numtel"),
                        rs.getDate("dateNaissc"),
                        rs.getString("adresse"),
                        rs.getString("sexe"),
                        Role.valueOf(rs.getString("role"))
                ));
            }}
            
             catch (SQLException ex) {
             System.out.println(ex.getMessage());
             }
             usertable.setItems(userList);}}

             /*
   @FXML
    private void exel(MouseEvent event) {

        WritableWorkbook wworkbook;
        try {
            wworkbook = Workbook.createWorkbook(new File("Bureau\\users.xls"));

            String query = "select nom,prenom,email,pwd,numtel,datenaissc,adresse,sexe,role";
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs = ste.executeQuery();
            WritableSheet wsheet = wworkbook.createSheet("First Sheet", 0);
            Label label = new Label(0, 2, "A label record");
            wsheet.addCell(label);
            int i = 0;

            int j = 1;
            while (rs.next()) {

                i = 0;

                label = new Label(i++, j, j + "");
                wsheet.addCell(label);
                label = new Label(i++, j, rs.getString("nom"));
                wsheet.addCell(label);
                label = new Label(i++, j, rs.getString("prenom"));
                wsheet.addCell(label);
                label = new Label(i++, j, rs.getString("email"));
                wsheet.addCell(label);
                label = new Label(i++, j, rs.getString("pwd"));
                wsheet.addCell(label);
                label = new Label(i++, j, rs.getString("numtel"));
                wsheet.addCell(label);
                label = new Label(i++, j, rs.getString("datenaissc"));
                wsheet.addCell(label);
                label = new Label(i++, j, rs.getString("adresse"));
                wsheet.addCell(label);
                label = new Label(i++, j, rs.getString("sexe"));
                wsheet.addCell(label);
                label = new Label(i++, j, rs.getString("role"));
                wsheet.addCell(label);
                j++;
            }

            wworkbook.write();
            wworkbook.close();
            System.out.println("fineshed");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}    }*/
    


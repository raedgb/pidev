/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.gui;

import java.sql.Date;
import java.util.List;
import javafx.scene.Node;
import tn.esprit.entity.Role;
import tn.esprit.entity.User;
import tn.esprit.services.UserService;
import tn.esprit.entity.Reclamation;
import tn.esprit.tools.MaConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author gabsi
 */
public class AfficherUserController implements Initializable {
 Notifications no ;
    Connection cnx;
    UserService us = new UserService();
    @FXML
    private TableColumn<User, String> nomcol;
    @FXML
    private TableColumn<User, String> prenomcol;
    @FXML
    private TableColumn<User, String> pwdcol;
    @FXML
    private TableColumn<User, String> numcol;
    @FXML
    private TableColumn<User, Date> datecol;
    @FXML
    private TableColumn<User, String> adressecol;
    @FXML
    private TableColumn<User, String> sexecol;
    @FXML
    private TableView<User> usertable;
    @FXML
    private TableColumn<User, String> emailcol;
    @FXML
    private TableColumn<User, Role> rolecol;
    ObservableList<User> data = FXCollections.observableArrayList();
    ObservableList<User> userList = FXCollections.observableArrayList();
    User user = null;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnrefresh;
    @FXML
    private Button btnlogout;

    @FXML
    private TextField recherche;
    @FXML
    private AnchorPane recherchet;
    @FXML
    private Button btnmodifier;
    int id_d;
    @FXML
    private ComboBox<String> cbx_search;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<String> searchOptions = Arrays.asList("Role", "Nom/Prenom");
        cbx_search.setItems(FXCollections.observableArrayList(searchOptions));
        try {
            UserService us = new UserService();
            List<User> user = us.getAll();
            ObservableList<User> list = FXCollections.observableArrayList(user);

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
                        Role.valueOf(rs.getString("role"))));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        loadDate();
        usertable.setItems(userList);
    }

    private void loadDate() {
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        pwdcol.setCellValueFactory(new PropertyValueFactory<>("pwd"));
        numcol.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("dateNaissc"));
        adressecol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        sexecol.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        rolecol.setCellValueFactory(new PropertyValueFactory<>("role"));

    }

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterUser.fxml"));
            btnajouter.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        user = usertable.getSelectionModel().getSelectedItem();
        UserService ds = new UserService();
        int input = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer !?",
                "Choisir une option...",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == 0) {
            us.supprimer(user.getId());

        } else if (input == 1) {

        }
    }

    @FXML
    private void refreshlist(ActionEvent event) throws SQLException {
        data.clear();
        data = FXCollections.observableArrayList(us.getAll());
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        pwdcol.setCellValueFactory(new PropertyValueFactory<>("pwd"));
        numcol.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("dateNaissc"));
        adressecol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        sexecol.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        rolecol.setCellValueFactory(new PropertyValueFactory<>("role"));

        usertable.setItems(data);
    }

    @FXML
    private void rechercheavance(KeyEvent event) {
      
        if(cbx_search.getValue()=="Nom/Prenom"){
        
        FilteredList<User> filtereddata = new FilteredList<>(data, b -> true);
        System.out.println(recherche.getText());
        recherche.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (user.getNom().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getPrenom().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getEmail().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getPwd().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (String.valueOf(user.getNumTel()).toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getSexe().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;

                } else if (String.valueOf(user.getDateNaissc()).toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        System.out.println(filtereddata);
        SortedList<User> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(usertable.comparatorProperty());
        usertable.setItems(filtereddata);}
        else{
            //System.out.println(id.departement);
        FilteredList<User> filtereddata = new FilteredList<>(data, b -> true);
        System.out.println(recherche.getText());
        recherche.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                 if (String.valueOf(user.getRole()).toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        System.out.println(filtereddata);
        SortedList<User> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(usertable.comparatorProperty());
        usertable.setItems(filtereddata);
        }
    }

    @FXML
    private void Logout(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void recherche_avance(KeyEvent event) {
    }

    @FXML
    private void update(ActionEvent event) {
        // Get the selected user
        User selectedUser = usertable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                 
            
                // Pass the selected user's ID to the ModifierUserController
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierUser.fxml"));
                Parent root = loader.load();
                ModifierUserController controller = loader.getController();
                controller.initData(selectedUser.getEmail());
                System.out.println("email dans update est:" + selectedUser.getEmail());

                // Navigate to the ModifierUserController
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}

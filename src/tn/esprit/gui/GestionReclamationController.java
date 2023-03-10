/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import tn.esprit.entity.Reclamation;
import tn.esprit.tools.MaConnection;
import tn.esprit.services.ReclamationService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
//import jxl.*;
//import jxl.Workbook;
//import jxl.write.Label;
//import jxl.write.WritableSheet;
//import jxl.write.WritableWorkbook;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GestionReclamationController implements Initializable {

    @FXML
    private TableColumn<?, ?> tblNom;
    @FXML
    private TableColumn<?, ?> tblPrenom;
    @FXML
    private TableColumn<?, ?> tblEmail;
    @FXML
    private TableColumn<?, ?> tblTel;
    @FXML
    private TableColumn<?, ?> tblEtat;
    @FXML
    private TableColumn<?, ?> tblDescription;
    @FXML
    private TableColumn<?, ?> tblDateReclamation;
    @FXML
    private TableView<Reclamation> TableReclamation;
    @FXML
    private TextField recherchet;
    ObservableList<Reclamation> reclamationList = FXCollections.observableArrayList();
    ObservableList<Reclamation> data = FXCollections.observableArrayList();

    ReclamationService ds = new ReclamationService();
    Reclamation reclamation = null;
    Connection cnx;
    @FXML
    private Button btnmodfier;
    @FXML
    private Button btnrefresh;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnajouter;

    public GestionReclamationController() {

        cnx = MaConnection.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection cnx = MaConnection.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("select * from reclamation");

            while (rs.next()) {
                reclamationList.add(new Reclamation(
                           rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getInt("tel"),
                        rs.getString("etat"),
                        rs.getString("description"),
                        rs.getDate("date_reclamation")
                ));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        loadDate();
        TableReclamation.setItems(reclamationList);

    }

    private void loadDate() {
        tblNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tblPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tblEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        tblEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tblDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblDateReclamation.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));

    }

    @FXML
    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(ds.afficherReclamation());
        tblNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tblPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tblEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        tblEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tblDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblDateReclamation.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));

        TableReclamation.setItems(data);
    }

///////////////////////////////////////////////////////////////////////////
    @FXML
    private void recherche_avance(KeyEvent event) {
         System.out.println("*******************");

        //System.out.println(id.departement);
         FilteredList<Reclamation> filtereddata = new FilteredList<>(data, b -> true);
        System.out.println(recherchet.getText());
        recherchet.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate(reclamation -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (reclamation.getNom().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (reclamation.getPrenom().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (reclamation.getEmail().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (String.valueOf(reclamation.getTel()).toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (reclamation.getEtat().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (reclamation.getDescription().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (String.valueOf(reclamation.getDate_reclamation()).toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        System.out.println(filtereddata);
        SortedList<Reclamation> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(TableReclamation.comparatorProperty());
        TableReclamation.setItems(filtereddata);
    }
/////////////////////////////////////////////////////
 
ReclamationService rs = new ReclamationService();

    @FXML
    private void generatePDF(ActionEvent event) throws IOException {
         FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
      File file = fileChooser.showSaveDialog(uggo.primaryStage);
        rs.generatePDF(file);
    }  



    @FXML
    private void deleteReclamation(MouseEvent event) {
                reclamation = TableReclamation.getSelectionModel().getSelectedItem();
        ReclamationService ds = new ReclamationService();
        int input = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer !?",
                "Choisir une option...",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == 0) {
            ds.supprimerReclamation(reclamation.getId());

        } else if (input == 1) {

        }
    }

    @FXML
    private void UpdateReclamation(MouseEvent event) {
    }

    @FXML
    private void ajouterReclamtion(ActionEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(GestionReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshlist();
    }

    @FXML
    private void UpdateReclamation(ActionEvent event) {
    }

    @FXML
    private void deleteReclamation(ActionEvent event) {
                        reclamation = TableReclamation.getSelectionModel().getSelectedItem();
        ReclamationService ds = new ReclamationService();
        int input = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer !?",
                "Choisir une option...",
                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
        if (input == 0) {
            ds.supprimerReclamation(reclamation.getId());

        } else if (input == 1) {

        }
    }
        
    
}
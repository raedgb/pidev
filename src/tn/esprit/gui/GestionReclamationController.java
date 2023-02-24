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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
        reclamation = (Reclamation) TableReclamation.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../GUI/AjouterReclamation.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(GestionReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AjouterReclamationController AjouterreclamationController = loader.getController();

        AjouterreclamationController.setTextField(
                reclamation.getId(),
                reclamation.getNom(),
                reclamation.getPrenom(),
                reclamation.getEmail(),
                String.valueOf(reclamation.getTel()),
                reclamation.getEtat(),
                reclamation.getDescription(),
                reclamation.getDate_reclamation());
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

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

   /* @FXML
    private void exel(MouseEvent event) {
        WritableWorkbook wworkbook;
        try {
            wworkbook = Workbook.createWorkbook(new File("C:\\Users\\LENOVO\\Desktop\\ReclamationExcel.xls"));

            String query = "select nom,prenom,email,tel,etat,description,date_reclamation from reclamation";
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
                label = new Label(i++, j, rs.getString("tel"));
                wsheet.addCell(label);
                label = new Label(i++, j, rs.getString("etat"));
                wsheet.addCell(label);
                label = new Label(i++, j, rs.getString("description"));
                wsheet.addCell(label);
                label = new Label(i++, j, rs.getString("date_reclamation"));
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
*/
}

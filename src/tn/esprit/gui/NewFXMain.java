package tn.esprit.gui ;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws SQLException, IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/GestionReclamation.fxml"));
        Scene scene = new Scene(root); 
        primaryStage.setTitle("Gérer Reclamation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }    
}
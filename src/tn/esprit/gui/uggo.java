package tn.esprit.gui ;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage; 
import tn.esprit.tools.MaConnection;

public class uggo extends Application {

    
      public static Stage primaryStage;
   //  public static Stage stage = null;
   

    @Override
    public void start(Stage primaryStage) throws Exception {
    
        

        uggo.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        launch(args);
        // TODO code application logic here
        MaConnection mc = MaConnection.getInstance();
    }
}
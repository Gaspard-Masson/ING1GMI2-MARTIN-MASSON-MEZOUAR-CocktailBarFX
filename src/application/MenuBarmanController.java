package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
public class MenuBarmanController {
	
    @FXML
    private AnchorPane rootpane;
    @FXML
    private Button Commande;


    @FXML
    private Button Boisson;

    @FXML
    void Goboisson(ActionEvent event) throws IOException {
    	AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("stockManage.fxml"));
		rootpane.getChildren().setAll(pane);
    }

    @FXML
    void Gocommande(ActionEvent event) throws IOException {
    	AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("commandeManage.fxml"));
		rootpane.getChildren().setAll(pane);
    }

   @FXML
    void deconnect(ActionEvent event) throws IOException {
    	AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("Accueil.fxml"));
		rootpane.getChildren().setAll(pane);
    }


	}


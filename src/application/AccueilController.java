package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class AccueilController {
	
		@FXML
		private AnchorPane rootpane;
	    @FXML
	    private Button boutonclient;

	    @FXML
	    private Button boutonbarman;

	    @FXML
	    void Barman(ActionEvent event) throws IOException {
	    	AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
    		rootpane.getChildren().setAll(pane);
	    }

	    @FXML
	    void Client(ActionEvent event) throws IOException {
	    	AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("interfaceclient.fxml"));
    		rootpane.getChildren().setAll(pane);
	    }

	}



package application;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class SampleController {
	

	  @FXML
	    private AnchorPane rootpane;

	    @FXML
	    private TextField tfusername;

	    @FXML
	    private TextField tfpwd;

	    @FXML
	    private Button boutonquitter;

	    @FXML
	    private Button boutonconnecter;

		@FXML
		private Button buttonRetour;

	    @FXML
	    private Label tferror;

	    @FXML
	    void btOK(ActionEvent event) throws IOException {
	    	Map<String,String> usermap = new HashMap<String,String>();
	    	usermap.put("barman1", "aa");
	    	usermap.put("barman2", "bb");
	    	ObservableMap<String,String>observablemap = FXCollections.observableMap(usermap);
	    	if(observablemap.containsKey(tfusername.getText()) && observablemap.get(tfusername.getText()).contentEquals(tfpwd.getText())){
	    		AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("Menubarman.fxml"));
	    		rootpane.getChildren().setAll(pane);
	    	}
	    	else{
	    		tferror.setText("Please check your username and your password");
	    		tfusername.setText("");
	    		tfpwd.setText("");
	    	}
	    }

	    @FXML
	    void btquitter(ActionEvent event) {
	    	Stage stage = (Stage)boutonquitter.getScene().getWindow();
	    	stage.close();
	    }

		@FXML
		void retour(ActionEvent event) throws IOException {
			AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("Accueil.fxml"));
			rootpane.getChildren().setAll(pane);
		}

	}



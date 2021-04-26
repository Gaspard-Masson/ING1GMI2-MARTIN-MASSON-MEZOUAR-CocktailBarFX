package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class commandeManageController {

    @FXML
    private Button buttonAfficherCommandes;

    @FXML
    private TextArea textareaCommandes;

    @FXML
    private AnchorPane root;

    @FXML
    void retour(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("MenuBarman.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    void Afficher(ActionEvent event) {
        File file = null;
        Scanner input = null;

        try {
            file = new File("src\\com\\cytech\\collections\\Commandes.txt");
            input = new Scanner(file);
            Double CA = 0.0;

            while(input.hasNextLine()){
                textareaCommandes.appendText(input.nextLine() +" \n" );
            }

            textareaCommandes.appendText("Pour un chiffre d'affaires de : " + CA + "€ !");
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null){
                input.close();
            }
        }

    }

}

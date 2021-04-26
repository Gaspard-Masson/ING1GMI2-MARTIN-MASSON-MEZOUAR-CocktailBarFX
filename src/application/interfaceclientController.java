package application;
import com.cytech.gestionFichiers.*;
import com.cytech.ingredients.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class interfaceclientController implements Initializable {
    @FXML
    private Button retourmenu;
    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Boisson> tableListeBoissons;

    @FXML
    private TableColumn<Boisson, String> colNom;

    @FXML
    private TableColumn<Boisson, Float> colContenance;

    @FXML
    private TableColumn<Boisson, Double> colPrix;

    @FXML
    private TableColumn<Boisson, Integer> colStock;

    @FXML
    private TableColumn<Boisson, Float> colDegre;

    @FXML
    private TableColumn<Boisson, String> colType;


    @FXML
    private Label boissonselected;

    @FXML
    private Button boutonQuitter;


    @FXML
    private Button boutonAjouter;

    @FXML
    private ChoiceBox<String> choiceboxCocktails;

    @FXML
    private Button buttonCreerCocktail;

    Barman Bman = new Barman();
    ArrayList<BoissonMere> C = new ArrayList<BoissonMere>();

    @FXML
    void gotoCocktail(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("composerCocktailClient.fxml"));
        root.getChildren().setAll(pane);
    }
    public static ArrayList<Cocktail> LBC = new ArrayList<Cocktail>();
    @FXML
    void Ajouter(ActionEvent event) throws Exception {

        Boisson b = tableListeBoissons.getSelectionModel().getSelectedItem();
        String cocktail = choiceboxCocktails.getSelectionModel().getSelectedItem();

        if(cocktail != null) {

            try {
                init.chargerListeCocktail(LBC);
            } catch (Exception e) {
                e.printStackTrace();
            }

            int index = 0;
            for(Cocktail c : LBC) {
                if(cocktail.equals(c.getNom())){
                    break;
                }
                index = index +1;
            }

            Cocktail c = LBC.get(index);


            boissonselected.setText(boissonselected.getText()+" "+c.getNom());
            C.add(c);
            choiceboxCocktails.setValue(null);
        }

        if(b != null){
            if(b.getQuantite()>0) {
                boissonselected.setText(boissonselected.getText()+" "+b.getNom());
                C.add(b);
                tableListeBoissons.getSelectionModel().getSelectedItem().setQuantite(tableListeBoissons.getSelectionModel().getSelectedItem().getQuantite()-1);
                tableListeBoissons.refresh();
                ArrayList<Boisson> lb = new ArrayList<Boisson>();
                lb.add(b);
                String [] s = {""};
                init.updateListeBoissons(lb,s);
            }
        }
    }

    @FXML
    void Delete(ActionEvent event) {
        ObservableList<Boisson> allBoisson;
        allBoisson=tableListeBoissons.getItems();

        if(tableListeBoissons.getSelectionModel().getSelectedItem()==null)
        {
            Alert a= new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please select a Product");
            a.setHeaderText(null);
            a.showAndWait();
        }
        else
        {
            Boisson singleProduct=tableListeBoissons.getSelectionModel().getSelectedItem();
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure to delete? \n"+singleProduct.getNom());
            alert.setHeaderText(null);
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get()==ButtonType.OK) {
                tableListeBoissons.getItems().remove(singleProduct);
            }

        }
    }

    @FXML
    void Passercommande(ActionEvent event) throws Exception {
        Bman.ajouterCommande(C);
    }
    @FXML
    void Quitter(ActionEvent event) {
        Stage stage = (Stage)boutonQuitter.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colContenance.setCellValueFactory(new PropertyValueFactory<>("contenance"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        colDegre.setCellValueFactory(new PropertyValueFactory<>("degre"));
        ArrayList<Boisson> LB = new ArrayList<Boisson>();
        try {
            init.chargerListeBoisson(LB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObservableList<Boisson> list= FXCollections.observableArrayList(LB);
        tableListeBoissons.setItems(list);

        ArrayList<Cocktail> LBC = new ArrayList<Cocktail>();
        try {
            init.chargerListeCocktail(LBC);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> listNomC = new ArrayList<String>();
        for(int i =0;i<LBC.size();i++){
            listNomC.add(LBC.get(i).getNom());
        }

        ObservableList<String> listC = FXCollections.observableArrayList(listNomC);
        choiceboxCocktails.setItems(listC);
    }

    @FXML
    void retourmenu(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("Accueil.fxml"));
        root.getChildren().setAll(pane);

    }
}
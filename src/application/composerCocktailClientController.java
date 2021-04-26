package application;

import com.cytech.gestionFichiers.init;
import com.cytech.ingredients.Boisson;
import com.cytech.ingredients.Cocktail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class composerCocktailClientController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private Button buttonRetour;

    @FXML
    private TableView<Boisson> tableviewStockManage;


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
    private Label boissonselected;

    @FXML
    private Button boutonAjouter;

    @FXML
    private Button buttonComposer;

    @FXML
    private Label lbErrorNomCocktail;

    @FXML
    private TextField textfieldNameCocktail;

    ArrayList<Boisson> LB = new ArrayList<Boisson>();

    @FXML
    void Ajouter(ActionEvent event) {


        Boisson b = tableviewStockManage.getSelectionModel().getSelectedItem();

        if(b != null){
            if(b.getQuantite()>0) {
                boissonselected.setText(boissonselected.getText()+" "+b.getNom());
                LB.add(b);
                tableviewStockManage.getSelectionModel().getSelectedItem().setQuantite(tableviewStockManage.getSelectionModel().getSelectedItem().getQuantite()-1);
                tableviewStockManage.refresh();
                ArrayList<Boisson> lb = new ArrayList<Boisson>();
                lb.add(b);
                String [] s = {"false","1"};
                init.updateListeBoissons(lb,s);

            }
        }
    }

    @FXML
    void Retour(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("interfaceClient.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    void composer(ActionEvent event) {
        if(textfieldNameCocktail.getText() != null){
            Cocktail c = new Cocktail(LB,textfieldNameCocktail.getText(),(float)1.0);
            interfaceclientController.LBC.add(c);

        } else {
            lbErrorNomCocktail.setText("Erreur, veuillez remplir le nom du cocktail");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        tableviewStockManage.setItems(list);
    }
}

package application;

import com.cytech.ingredients.Boisson;
import com.cytech.ingredients.BoissonAlcoolisee;
import com.cytech.ingredients.BoissonNonAlcoolisee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class stockManageController implements Initializable {

    @FXML
    private TableView<Boisson> tableviewStockManage;

    @FXML
    private TableColumn<Boisson, String> colNom;

    @FXML
    private TableColumn<Boisson, Float> colContenance;

    @FXML
    private TableColumn<Boisson, Double> colPrix;

    @FXML
    private TableColumn<Boisson, Float> colDegre;

    @FXML
    private TableColumn<Boisson, Integer> colStock;

    @FXML
    private TextField textfieldContenance;

    @FXML
    private TextField textfieldPrix;

    @FXML
    private TextField textfieldDegre;

    @FXML
    private TextField textfieldStock;

    @FXML
    private TextField textfieldNom;

    @FXML
    private Label labelErrorNom;

    @FXML
    private Label labelErrorContenance;

    @FXML
    private Label labelErrorPrix;

    @FXML
    private Label labelErrorDegre;

    @FXML
    private Label labelErrorStock;

    @FXML
    private Button buttonAddBoisson;

    @FXML
    private ChoiceBox<String> choiceboxType;

    @FXML
    private TextField textfieldStock1;

    @FXML
    private TextField textfieldNom1;

    @FXML
    private Button buttonAddBoisson1;

    @FXML
    private Label labelErrorstock1;

    @FXML
    private Label labelErrorNom1;

    @FXML
    private Label labelErrorBoissonInexistante;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonretour;

    @FXML
    private AnchorPane rootpane;

    @FXML
    void retour(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("MenuBarman.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    void Delete(ActionEvent event) {
        ObservableList<Boisson> allBoisson;
        allBoisson=tableviewStockManage.getItems();

        if(tableviewStockManage.getSelectionModel().getSelectedItem()==null)
        {
            Alert a= new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please select a Product");
            a.setHeaderText(null);
            a.showAndWait();
        }
        else
        {
            Boisson singleProduct=tableviewStockManage.getSelectionModel().getSelectedItem();
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure to delete? \n"+singleProduct.getNom());
            alert.setHeaderText(null);
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get()==ButtonType.OK) {
                tableviewStockManage.getItems().remove(singleProduct);
            }

        }
    }

    @FXML
    void addBoissonStock(ActionEvent event) {
        Boolean b1 = emptyTextField(textfieldNom1,labelErrorNom1,"attention champs vide");
        Boolean b2 = emptyTextField(textfieldStock1,labelErrorstock1,"attention champs vide");
        Boolean b3 = notNumberTextField(textfieldStock1,labelErrorstock1,"champs non numerique");

        if(!b1 && !b2 && !b3) {
            ArrayList<Boisson> LB = new ArrayList<Boisson>();
            fillArrayList(LB);

            String target = textfieldNom1.getText();
            int index = 0;
            for(Boisson b : LB) {
                if(target.equals(b.getNom())){
                    break;
                }
                index = index +1;
            }

            if(index == LB.size()){
                labelErrorBoissonInexistante.setText("La boisson n'est pas deja dans le stock !");

                textfieldNom1.setText("");
                textfieldStock1.setText("");
                labelErrorNom1.setText("");
                labelErrorstock1.setText("");
            }else {
                int newStock = LB.get(index).getQuantite()+Integer.parseInt(textfieldStock1.getText());
                LB.get(index).setQuantite(newStock);

                ObservableList<Boisson> list= FXCollections.observableArrayList(LB);
                tableviewStockManage.setItems(list);

                textfieldNom1.setText("");
                textfieldStock1.setText("");
                labelErrorNom1.setText("");
                labelErrorstock1.setText("");
            }

        }

    }

    @FXML
    void addBoisson(ActionEvent event) {

        // On réalise des tests sur les données rentrées :
        Boolean b1 = emptyTextField(textfieldNom,labelErrorNom,"attention champs vide");
        Boolean b2 = emptyTextField(textfieldContenance,labelErrorContenance,"attention champs vide");
        Boolean b3 = emptyTextField(textfieldDegre,labelErrorDegre,"attention champs vide");
        Boolean b4 = emptyTextField(textfieldPrix,labelErrorPrix,"attention champs vide");
        Boolean b5 = emptyTextField(textfieldStock,labelErrorStock,"attention champs vide");
        Boolean b6 = notNumberTextField(textfieldContenance,labelErrorContenance,"champs non numerique");
        Boolean b7 = notNumberTextField(textfieldDegre,labelErrorDegre,"champs non numerique");
        Boolean b8 = notNumberTextField(textfieldPrix,labelErrorPrix,"champs non numerique");
        Boolean b9 = notNumberTextField(textfieldStock,labelErrorStock,"champs non numerique");

        if(!b1 && !b2 && !b3 && !b4 && !b5 && !b6 && !b7 && !b8 && !b9) {

            if(choiceboxType.getValue() == "BoissonAlcoolisee"){
                BoissonAlcoolisee b = new BoissonAlcoolisee(textfieldNom.getText(),Float.parseFloat(textfieldContenance.getText()),Double.parseDouble(textfieldPrix.getText()),Integer.parseInt(textfieldStock.getText()),Float.parseFloat(textfieldDegre.getText()));
                writeRecord(b,"BoissonAlcoolisee");
            }else {
                BoissonNonAlcoolisee b = new BoissonNonAlcoolisee(textfieldNom.getText(),Float.parseFloat(textfieldContenance.getText()),Double.parseDouble(textfieldPrix.getText()),Integer.parseInt(textfieldStock.getText()),Float.parseFloat(textfieldDegre.getText()));
                writeRecord(b,"BoissonNonAlcoolisee");
            }

            //On update la table des boissons :
            ArrayList<Boisson> LB = new ArrayList<Boisson>();
            fillArrayList(LB);

            ObservableList<Boisson> list= FXCollections.observableArrayList(LB);
            tableviewStockManage.setItems(list);

            //On vide tous les champs et labels d'erreur:
            textfieldNom.setText("");
            textfieldContenance.setText("");
            textfieldPrix.setText("");
            textfieldStock.setText("");
            textfieldDegre.setText("");

            labelErrorNom.setText("");
            labelErrorContenance.setText("");
            labelErrorDegre.setText("");
            labelErrorPrix.setText("");
            labelErrorStock.setText("");
        }

    }

    //Initialisation de la table avec la base de données et intitialisation du choiceBox.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colContenance.setCellValueFactory(new PropertyValueFactory<>("contenance"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        colDegre.setCellValueFactory(new PropertyValueFactory<>("degre"));

        // Création de la liste des boissons puis transformation en Observable liste et affichage dans la tableview.
        ArrayList<Boisson> LB = new ArrayList<Boisson>();
        fillArrayList(LB);

        ObservableList<Boisson> list= FXCollections.observableArrayList(LB);
        tableviewStockManage.setItems(list);

        // Initialisation du choiceBox
        ObservableList<String> listchoix= FXCollections.observableArrayList("BoissonAlcoolisee","BoissonNonAlcoolisee");
        choiceboxType.setValue("BoissonNonAlcoolisee");
        choiceboxType.setItems(listchoix);
    }

    //Fonction annexe qui sert à remplir une liste de Boissons avec celles présentes dans la base de données.
    public void fillArrayList(ArrayList<Boisson> LB){
        File file = null;
        Scanner input = null;

        try {
            file = new File("src\\com\\cytech\\collections\\Boissons.txt");
            input = new Scanner(file);

            while(input.hasNext()){
                String type = input.next();
                String name = input.next();
                float contenance = input.nextFloat();
                double price = input.nextDouble();
                int quantity = input.nextInt();
                float degre = input.nextFloat();

                if (type == "BoissonAlcoolisee"){
                    BoissonAlcoolisee b = new BoissonAlcoolisee(name,contenance,price,quantity,degre);
                    LB.add(b);

                } else {
                    BoissonNonAlcoolisee b = new BoissonNonAlcoolisee(name,contenance,price,quantity,degre);
                    LB.add(b);
                }

            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (input != null){
                input.close();
            }
        }
    }

    // Fonction annexe permettant d'écrire dans le fichier texte :

    public void writeRecord(Boisson b,String type){
        FileWriter file = null;
        PrintWriter output = null;

        try{
            file = new FileWriter("src\\com\\cytech\\collections\\Boissons.txt",true);
            output = new PrintWriter(file);

            output.println(
                  type + " " + b.getNom() + " " + b.getContenance() + " " + b.getPrix() + " " + b.getQuantite() + " " + b.getDegre()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (output != null) { // in case if it is not closed properly
                output.close();
            }
        }
    }


    //Fonctions annexes de vérification des champs.

    public boolean emptyTextField(TextField tf)
    {
        if (tf.getText().isEmpty()) {
            return true;
        }else {
            return false;
        }
    }

    public boolean emptyTextField(TextField tf, Label lberrordes2, String msgerror)
    {
        if(!emptyTextField(tf))
        {
            return false;
        }else
        {
            lberrordes2.setText(msgerror);
            return true;
        }
    }

    public boolean notNumberTextField(TextField tf)
    {
        if (tf.getText().matches("-?\\d*")||tf.getText().matches("-?\\d+\\.\\d+"))
        {
            return false;
        }
        else
            return true;

    }

    public boolean notNumberTextField (TextField tf, Label lb, String msgerror)
    {
        if (notNumberTextField (tf))
        {
            lb.setText(msgerror);
            return true;
        }
        else
            return false;

    }
}

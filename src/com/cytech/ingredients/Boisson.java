package com.cytech.ingredients;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
public class Boisson extends BoissonMere {
   SimpleStringProperty nom;
   SimpleFloatProperty contenance;
   SimpleDoubleProperty prix;
   SimpleIntegerProperty quantite;
   public float getDegre() {
       return 0;
   }
   
   public Boisson(String nom, Float contenance, Double prixB, Integer quantite) {

       if (contenance<0) {
           System.out.print("ERROR la contenance renseignee est incorrecte");
           System.exit(1);        
       }
       if(prixB<0) {
           System.out.print("ERROR le prix de la boisson renseigne est incorrect");
           System.exit(1);
       }
       if(quantite<0) {
           System.out.print("ERROR la quantite renseignee est incorrecte");
           System.exit(1);
       }
       else {    
          this.nom = new SimpleStringProperty(nom);
          this.contenance = new SimpleFloatProperty(contenance);
          this.prix = new SimpleDoubleProperty(prixB);
          this.quantite = new SimpleIntegerProperty(quantite);
       }
   }

   public String getNom() {
      return nom.get();
   }

   public float getContenance() {
      return contenance.get();
   }

   public double getPrix() {
      return prix.get();
   }

   public int getQuantite() {
      return quantite.get();
   }

public void setQuantite(int i) {
	quantite = new SimpleIntegerProperty(i);
	
}

}
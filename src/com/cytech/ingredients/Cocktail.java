package com.cytech.ingredients;

import java.util.*;

public class Cocktail extends BoissonMere{
	
	protected ArrayList<Boisson> listeBoissonsC;
	protected String nomC;
	protected float contenance;
	protected double prixC;
	
	
	public float getDegreAlcoolC(){
		
		float degre = 0;
		int cpt = 0;
		
		for(int i = 0;i<listeBoissonsC.size();i++) {
			if(listeBoissonsC.get(i) instanceof BoissonAlcoolisee) {
				degre = degre + listeBoissonsC.get(i).getDegre();
				cpt+=1;
			}
		}
		
		if(cpt==0) {
			return 0;
		}else {
			return degre/cpt;
		}
	}
	
	public float getDegreSucreC(){
		
		float degre = 0;
		int cpt = 0;
		
		for(int i = 0;i<listeBoissonsC.size();i++) {
			if(listeBoissonsC.get(i) instanceof BoissonNonAlcoolisee) {
				degre = degre + listeBoissonsC.get(i).getDegre();
				cpt = cpt + 1;
			} 
			
		}
		if(cpt==0) {
			return 0;
		}else {
			return degre/cpt;
		}
		
	}

	public String getNom() {
		return nomC;
	}
	public float getContenance() {
		return contenance;
	}
	public double getPrix() {
        double prixC=0;
        for(int i = 0;i<listeBoissonsC.size();i++) {
            prixC = prixC + listeBoissonsC.get(i).getPrix();
    }
        return prixC + prixC*(0.1);
    }
	public ArrayList<Boisson> getListeBoissonsC() {
		return listeBoissonsC;
	}
	
	public Cocktail(ArrayList<Boisson> listeBoissonsC, String nomC, float contenance) {
		super();
		if(listeBoissonsC.size()<2) {
			System.out.print("ERROR nombre de boissons insuffisant pour le cocktail");
			System.exit(1);	
		}else if (contenance<0) {
			System.out.print("ERROR la contenance renseignee est incorrecte");
			System.exit(1);		
		}else {
			this.listeBoissonsC = listeBoissonsC;
			this.nomC = nomC;
			this.contenance = contenance;
		}
		
	}
	
	
}

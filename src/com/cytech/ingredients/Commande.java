package com.cytech.ingredients;

import java.util.ArrayList;

public class Commande {
	
	protected int numeroCommande;
	protected ArrayList<BoissonMere> listeBoissonsMeresCommande;
	protected double prixCommande;

	public int getNumeroCommande() {
		return numeroCommande;
	}
	
	public double getPrixCommande(){
		double prix = 0;
		for(int i = 0;i<listeBoissonsMeresCommande.size();i++) {
			prix = prix + listeBoissonsMeresCommande.get(i).getPrix();
		}
		return Math.round(prix*100.00)/100.00;
	}
	
	public Commande(int numeroCommande, ArrayList<BoissonMere> listeCommande) {
		super();
		this.numeroCommande = numeroCommande;
		this.listeBoissonsMeresCommande = listeCommande;
	}

	public String afficherlisteBoissonMere() {
		String S ="";
		for(int i=0;i<listeBoissonsMeresCommande.size();i++) {
			S = S + listeBoissonsMeresCommande.get(i).getNom()+", ";
		}
		return S;
	}
	
}

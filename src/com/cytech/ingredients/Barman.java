package com.cytech.ingredients;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.cytech.gestionFichiers.EcrireFichier;
import com.cytech.gestionFichiers.LireFichier;
import com.cytech.gestionFichiers.init;

public class Barman implements gererCommande {

	Map <Integer,Commande>lesCommandes = new HashMap<Integer,Commande>();
	int compteur = 1;
	
	public void facturerCocktail(Cocktail c) {
		System.out.println("Vous nous devez : " + c.getPrix() +"€ pour le cocktail " + c.getNom() + " !");
		
	}
	
	public Cocktail composerCocktail(ArrayList<Boisson> listeBoissons, String nomC, float contenance) {
		
		for(int i = 0; i<listeBoissons.size();i++) {
			if(gererStock(listeBoissons.get(i))) {
				listeBoissons.get(i).setQuantite(listeBoissons.get(i).getQuantite()-1);
			} else {
				System.out.println("Désolé, il n'y a plus assez de " + listeBoissons.get(i).getNom() +" pour réaliser ce cocktail");
				System.exit(1);
			}
		}
		
		Cocktail C = new Cocktail(listeBoissons, nomC, contenance);
		
		return C;
	}
	
	public boolean gererStock(Boisson b) {
		if(b.getQuantite()>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void ajouterCommande(ArrayList<BoissonMere> listeBoissonMereCommande) throws Exception {
		Commande commande = new Commande(compteur,listeBoissonMereCommande);	
		
		lesCommandes.put(commande.getNumeroCommande(), commande);
		String s = "Commande n°" +compteur+ " : " + commande.afficherlisteBoissonMere() + "pour un total de : " + commande.getPrixCommande() + "€";
		EcrireFichier.enregistrerCommande(s);
		compteur ++;
	}

	@Override
	public void supprimerCommande(int noCommande) {
		if(lesCommandes.containsKey(noCommande)) {
			lesCommandes.remove(noCommande);
		}else {
			System.out.println("Erreur : La commande n'existe pas !");
			System.exit(1);
		}
		
	}

	public void afficherCommande(int noCommande) throws Exception {
		if(lesCommandes.containsKey(noCommande)) {
			LireFichier.lireCommande(noCommande);
		}else {
			System.out.println("Erreur : La commande n'existe pas !");
			System.exit(1);
		}
	}
	
	public void afficherHistorique() throws Exception {
		for(int i =1;i<=lesCommandes.size();i++) {
		afficherCommande(lesCommandes.get(i).getNumeroCommande());
		}
	}
	
	public String renvoyerCommande(int noCommande) {
		return "Commande n°" + noCommande + " : " + lesCommandes.get(noCommande).afficherlisteBoissonMere() +"pour un total de : " + lesCommandes.get(noCommande).getPrixCommande() +"€";
		}
	
	public void ajouterStock(Boisson b,int qte) {
		try
        {
			ArrayList<Boisson> L = new ArrayList<Boisson>();
			L.add(b);
			
			String [] s = {String.valueOf(true),String.valueOf(qte)};
			init.updateListeBoissons(L, s);
	    }
        catch (Exception e)
        {
            System.out.println("Erreur" + e);
        }
	}
	
	public String afficherChiffreDaffaire() {
		double resultat = 0;
		for (int i =1; i<lesCommandes.size()+1;i++) {
			resultat += lesCommandes.get(i).getPrixCommande();
		}
		return "Le chiffre d'affaire est de " + String.valueOf(resultat) + "€";
	}
	
	
}

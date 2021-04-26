package com.cytech.ingredients;
import com.cytech.gestionFichiers.*;
import java.util.ArrayList;

public class Client {

	
	public Cocktail composerCocktail(ArrayList<Boisson> listeBoissons, String nomC, float contenance) throws Exception {
		
		for(int i = 0; i<listeBoissons.size();i++) {
			if(listeBoissons.get(i).getQuantite()>0) {
				listeBoissons.get(i).setQuantite(listeBoissons.get(i).getQuantite()-1);
			} else {
				System.out.println("Désolé, il n'y a plus assez de " + listeBoissons.get(i).getNom() +" pour réaliser ce cocktail");
				System.exit(1);
			}
		}
		String [] s = {};
		init.updateListeBoissons(listeBoissons,s);
		
		Cocktail C = new Cocktail(listeBoissons, nomC, contenance);
		
		return C;
	}

}

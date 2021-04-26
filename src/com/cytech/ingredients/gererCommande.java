package com.cytech.ingredients;

import java.util.ArrayList;

public interface gererCommande {

	public void ajouterCommande(ArrayList<BoissonMere> listeBoissonMereCommande) throws Exception;
	public void supprimerCommande(int noCommande);
}

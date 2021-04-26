package com.cytech.gestionFichiers;

import java.io.*;

public class EcrireFichier {
	 
	public static void enregistrerCommande(String commande) throws Exception {
		try {
			FileWriter fw=new FileWriter(".\\src\\com\\cytech\\collections\\Commandes.txt",true);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(commande+"\r\n");
			bw.close();
		} catch (Exception e) {
			System.out.println("Erreur "+e);
		}
	}
}

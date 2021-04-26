package com.cytech.gestionFichiers;

import java.io.*;

public class LireFichier {

	
	public static void lireCommande(int noCommande) throws Exception {
		try {
			FileReader fr = new FileReader(".\\src\\com\\cytech\\collections\\Commandes.txt");
			BufferedReader br=new BufferedReader(fr);
			while(br.ready()) {
				String s = br.readLine();
				String[] temp = s.split(" ");
				String[] tp = temp[1].split("°");
				if (Integer.parseInt(tp[1]) == noCommande) {
					System.out.println(s);
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println("Erreur "+e);
		}
		}
}


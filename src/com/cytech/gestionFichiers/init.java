package com.cytech.gestionFichiers;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;

import com.cytech.ingredients.Boisson;
import com.cytech.ingredients.BoissonAlcoolisee;
import com.cytech.ingredients.BoissonNonAlcoolisee;
import com.cytech.ingredients.Cocktail;


public class init {

	public static void chargerListeBoisson(ArrayList<Boisson> L) throws Exception {

		try {

			FileReader fr = new FileReader(".\\src\\com\\cytech\\collections\\Boissons.txt");
			BufferedReader br = new BufferedReader(fr);

			while(br.ready()) {

				String line = br.readLine();
				String[] T = line.split(" ");

				switch(T[0]){
					case "BoissonAlcoolisee":

						BoissonAlcoolisee bA = new BoissonAlcoolisee(T[1],Float.parseFloat(T[2]),Double.parseDouble(T[3]),Integer.parseInt(T[4]),Float.parseFloat(T[5]));
						L.add(bA);
						break;

					case "BoissonNonAlcoolisee":

						BoissonNonAlcoolisee bNA = new BoissonNonAlcoolisee(T[1],Float.parseFloat(T[2]),Double.parseDouble(T[3]),Integer.parseInt(T[4]),Float.parseFloat(T[5]));
						L.add(bNA);
						break;

				}


			}
			br.close();

		} catch (Exception e) {
			System.out.println("Erreur ddd"+e);
			System.exit(1);
		}
	}

	public static void chargerListeCocktail(ArrayList<Cocktail> LC) throws Exception {

		try {

			FileReader fr = new FileReader(".\\src\\com\\cytech\\collections\\Cocktails.txt");
			BufferedReader br = new BufferedReader(fr);

			ArrayList<Boisson> LB = new ArrayList<Boisson>();
			chargerListeBoisson(LB);

			ArrayList<Boisson> LBC = new ArrayList<Boisson>();

			while(br.ready()) {

				String line = br.readLine();
				String[] T = line.split(" ");

				for(int i=1;i<T.length-2;i++) {
					Boisson b = retournerBoisson(T[i],LB);
					LBC.add(b);
				}

				Cocktail c = new Cocktail(LBC,T[T.length-2],Float.parseFloat(T[T.length-1]));
				LC.add(c);
			}

			br.close();

		}catch(Exception e) {
			System.out.println("Erreur " + e);
			System.exit(1);
		}

	}

	public static Boisson retournerBoisson(String s,ArrayList<Boisson> LB) {
		for(int i = 0;i<LB.size();i++) {
			if(s.compareTo(LB.get(i).getNom()) == 0){
				return LB.get(i);
			}
		}
		System.out.println("Boisson non repertoriee");
		return null;
	}

	public static void updateListeBoissons(ArrayList<Boisson> LBM,String[] args) {
		try {
			Boolean B = false;
			int qte = 0;
			if(args.length>1) {
				if(Boolean.parseBoolean(args[0])) {
					B = Boolean.parseBoolean(args[0]);
				}
				if(Integer.parseInt(args[1])>0) {
					qte = Integer.parseInt(args[1]);
				}

			}


			FileReader fr = new FileReader(".\\src\\com\\cytech\\collections\\Boissons.txt");
			BufferedReader br = new BufferedReader(fr);
			StringBuffer inputBuffer = new StringBuffer();

			ArrayList<String> L = new ArrayList<String>();

			for (int k=0; k<LBM.size(); k++)
			{
				L.add(LBM.get(k).getNom());
			}
			String strCurrentLine;

			while((strCurrentLine = br.readLine()) != null)
			{
				String[] T = strCurrentLine.split(" ");
				if(L.contains(T[1]) && !B) {
					inputBuffer.append(T[0] +" "+ T[1] +" "+ T[2] +" "+ T[3] +" "+ String.valueOf(Integer.parseInt(T[4])-1) +" "+ T[5]+"\n");
				} else if(L.contains(T[1])) {
					inputBuffer.append(T[0] +" "+ T[1] +" "+ T[2] +" "+ T[3] +" "+ String.valueOf(Integer.parseInt(T[4])+qte) +" "+ T[5]+"\n");
				} else {
					inputBuffer.append(strCurrentLine+"\n");
				}
			}

			br.close();

			String inputStr = inputBuffer.toString();
			System.out.println (inputStr);
			FileOutputStream fileOut = new FileOutputStream(".\\src\\com\\cytech\\collections\\Boissons.txt");
			fileOut.write(inputStr.getBytes());
			fileOut.close();



		}catch(Exception e) {
			System.out.println("Erreur " +e);
			System.exit(13);
		}
	}
}
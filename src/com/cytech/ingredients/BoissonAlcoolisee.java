package com.cytech.ingredients;

public class BoissonAlcoolisee extends Boisson {

	private float degreAlcool;

	public BoissonAlcoolisee(String nomB, float contenance, double prixB, int quantite, float degreAlcool) {
		super(nomB, contenance, prixB, quantite);
		if(degreAlcool <0) {
			System.out.print("ERROR le degre d'alcool est incorrect");
			System.exit(1);
		}else {
			this.degreAlcool = degreAlcool;
		}
	}
	
	public float getDegre() {
		return degreAlcool;
	}

	public void setDegreAlcool(float degreAlcool) {
		this.degreAlcool = degreAlcool;
	}
	
}

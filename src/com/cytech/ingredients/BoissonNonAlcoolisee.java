package com.cytech.ingredients;

public class BoissonNonAlcoolisee extends Boisson {
	
	private float degreSucre;
	
	public BoissonNonAlcoolisee(String nomB, float contenance, double prixB, int quantite, float degreSucre) {
		super(nomB, contenance, prixB, quantite);
		if(degreSucre <0) {
			System.out.print("ERROR le degre de sucre est incorrect");
			System.exit(1);
		}else {
			this.degreSucre = degreSucre;
		}
		
	}

	public float getDegre() {
		return degreSucre;
	}

	public void setDegreSucre(float degreSucre) {
		this.degreSucre = degreSucre;
	}
	
}

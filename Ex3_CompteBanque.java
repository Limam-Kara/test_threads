package exrcices;


public class Ex3_CompteBanque {
	private int solde = 1600; //initialisation du solde pour tester le fonctionnement
	
	public int getSolde()
	{
		return solde ;
	}
	
	public void retirer(int montant)
	{
		this.solde -= montant ;
		System.out.println("----------- solde = " + this.solde);
	}
}

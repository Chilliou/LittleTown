public class Ouvrier
{
	private int    iPosLigne;
	private int    iPosColonne;
	private Joueur jProprietaire;

	public Ouvrier ( int iPosLigne, int iPosColonne, Joueur jProprietaire )
	{
		this.iPosLigne     = iPosLigne;
		this.iPosColonne   = iPosColonne;
		this.jProprietaire = jProprietaire;
	}

	public Joueur getProprietaire()
	{
		return this.jProprietaire;
	}

	public void resetOuvrier() // Pour replacer ouvrier a la fin de chaque manche
	{
		this.iPosLigne   = -1; 
		this.iPosColonne = -1;
	}
}

public class Ouvrier
{
	private int    posLigne;
	private int    posColonne;
	private Joueur proprietaire;

	public Ouvrier ( int posLigne, int posColonne, Joueur proprietaire )
	{
		this.posLigne     = posLigne;
        this.posColonne   = posColonne;
		this.proprietaire = proprietaire;
	}

	public Joueur getProprietaire()
	{
		return this.proprietaire;
	}

	public void resetOuvrier() // Pour replacer ouvrier a la fin du tour
	{
		this.posLigne   = -1; 
		this.posColonne = -1;
	}
}
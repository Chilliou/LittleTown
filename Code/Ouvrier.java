public class Ouvrier
{
	private int posLigne;
	private int posColonne;

	public Ouvrier ( int posLigne, int posColonne )
	{
		this.posLigne   = posLigne;
    this.posColonne = posColonne;
	}

	public void resetOuvrier() // Pour replacer ouvrier a la fin du tour
	{
		this.posLigne = -1; 
		this.posColonne = -1;
	}
}
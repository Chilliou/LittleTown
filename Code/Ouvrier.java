public class Ouvrier
{
	private int iPosLigne;
	private int iPosColonne;

	public Ouvrier ( int iPosLigne, int iPosColonne )
	{
		this.iPosLigne   = iPosLigne;
    	this.iPosColonne = iPosColonne;
	}

	public void resetOuvrier() // Pour replacer ouvrier a la fin du tour
	{
		this.iPosLigne   = -1; 
		this.iPosColonne = -1;
	}
}
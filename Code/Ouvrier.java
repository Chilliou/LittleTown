public class Ouvrier
{
	private int posLigne;
	private int posColonne;

	public Ouvrier()
	{
		this.posLigne = -1; // -1 Signifie qu'il n'est plas placer puis 0 aurait équivalut a la case 0;
		this.posColonne = -1;
	}

	public boolean placerOuvrier(int posLigne,int posColonne) // Dans le futur faire les test pour voir que l'on essaye pas de le sortir
	{
		if(this.posLigne < 0 || this.posLigne > 6)  return false;// 6 équivaut a la derniere ligne du plateau
		if(this.posColonne < 0 || this.posLigne > 9)  return false;

		this.posLigne = posLigne;
		this.posColonne = posColonne;

		return true;
	}

	public void resetOuvrier() // Pour replacer ouvrier a la fin du tour
	{
		this.posLigne = -1; 
		this.posColonne = -1;
	}
}
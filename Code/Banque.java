public class Banque
{
	private int nbPierre;
	private int nbBois;
	private int nbEau;
	private int nbBle;

	private int nbPiece;

	public Banque()
	{
		this.nbPierre = 15;
		this.nbBois   = 15;
		this.nbEau    = 15;
		this.nbBle    = 15;

		this.nbPiece = 40;
	}

	public Banque(int nbPiece)
	{
		this.nbPierre = 0;
		this.nbBois   = 0;
		this.nbEau    = 0;
		this.nbBle    = 0;

		this.nbPiece = nbPiece;
	}

	public int getRsc(char rsc)
	{
		int nombre=0;

		return nombre = switch(rsc)
						{
							case 'P' -> this.nbPierre;
							case 'B' -> this.nbBois;
							case 'E' -> this.nbEau;
							case 'C' -> this.nbBle;
							case 'A' -> this.nbPiece;
							default -> 99;
						};
	}

	public void ajouterEnlever(char rsc,int nb)
	{
		switch(rsc)
		{
			case 'P' -> this.nbPierre += nb;
			case 'B' -> this.nbBois   += nb;
			case 'E' -> this.nbEau    += nb;
			case 'C' -> this.nbBle    += nb;
			case 'A' -> this.nbPiece  += nb;
		}
	}

	
	public boolean echangerRscBanqueVJoueur(Joueur j,char rsc,int nb)
	{
		
		if(this.getRsc(rsc) < j.getRsc(rsc))
			return false;
		else
		{
			j.ajouterEnlever(rsc, nb);
			this.ajouterEnlever(rsc,-nb);
			return true;
		}

	}
	

	public String toString ()
{
    return "Nombre de bois   : " + this.nbBois   + "\n" +
           "Nombre de Ble    : " + this.nbBle    + "\n" +
           "Nombre d'eau     : " + this.nbEau    + "\n" +
           "Nombre de Pierre : " + this.nbPierre + "\n" +
           "Nombre de Piece  : " + this.nbPiece  + "\n" ;
}

	public static void main(String[] args)
	{
		Banque b = new Banque();
		System.out.println(b);

		b.ajouterEnlever('B',-4);
		System.out.println(b);
	}
}
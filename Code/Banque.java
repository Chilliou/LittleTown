public class Banque
{
	private int iNbPierre;
	private int iNbBois;
	private int iNbEau;
	private int iNbBle;

	private int iNbPiece;

	private int iScore;

	public Banque()
	{
		this.iNbPierre = 15;
		this.iNbBois   = 15;
		this.iNbEau    = 15;
		this.iNbBle    = 15;
		this.iNbPiece = 40;

		this.iScore   = 0;

		
	}

	public Banque(int iNbPiece)
	{
		this.iNbPierre = 10;
		this.iNbBois   = 10;
		this.iNbEau    = 10;
		this.iNbBle    = 10;
		this.iNbPiece = iNbPiece;

		this.iScore   = 0;

		
	}

	public int getRsc(char rsc)
	{
		int nombre=0;

		return nombre = switch(rsc)
						{
							case 'P' -> this.iNbPierre;
							case 'A' -> this.iNbBois;
							case 'E' -> this.iNbEau;
							case 'C' -> this.iNbBle;
							case 'M' -> this.iNbPiece;
							default -> 99;
						};
	}

	public void ajouterEnlever(char rsc,int nb)
	{
		switch(rsc)
		{
			case 'P' -> this.iNbPierre += nb;
			case 'A' -> this.iNbBois   += nb;
			case 'E' -> this.iNbEau    += nb;
			case 'C' -> this.iNbBle    += nb;
			case 'M' -> this.iNbPiece  += nb;
		}	
	}

	// Cette méthode peut retirer ou ajouter du score
	public void changeScore(int iScore)
	{
		this.iScore += iScore;
	}

	public int  getScore()
	{
		return this.iScore;
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
    return "Nombre de Pierre : " + this.iNbPierre + "\n" +
           "Nombre de bois   : " + this.iNbBois   + "\n" +
           "Nombre d'eau     : " + this.iNbEau    + "\n" +
           "Nombre de Ble    : " + this.iNbBle    + "\n" +
           "Nombre de Piece  : " + this.iNbPiece  + "\n" +
           "Nombre de Point  : " + this.iScore    ;
}

	public static void main(String[] args)
	{
		Banque b = new Banque();
		System.out.println(b);

		b.ajouterEnlever('B',-4);
		System.out.println(b);
	}
}
package littletown;


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
		
		this.iScore   = 9999;
		
	}

	// Constructeur de la banque du joueur
	public Banque(int iNbPiece)
	{
		this.iNbPierre = 0;
		this.iNbBois   = 0;
		this.iNbEau    = 0;
		this.iNbBle    = 0;

		this.iNbPiece = iNbPiece;
		
		this.iScore   = 0;

		
	}
	
	public int getRsc( char rsc )
	{
		int iNombre = 0;

		return iNombre = switch(rsc)
						{
							case 'P' -> this.iNbPierre;
							case 'A' -> this.iNbBois;
							case 'E' -> this.iNbEau;
							case 'C' -> this.iNbBle;
							case 'M' -> this.iNbPiece;
							case 'S' -> this.iScore;
							default -> 99;
						};
	}

	//Cette méthode sert à enlever et ajouter une ressources donner
	public void ajouterEnlever( char rsc, int iNb )
	{
		switch(rsc)
		{
			case 'P' -> this.iNbPierre += iNb;
			case 'A' -> this.iNbBois   += iNb;
			case 'E' -> this.iNbEau    += iNb;
			case 'C' -> this.iNbBle    += iNb;
			case 'M' -> this.iNbPiece  += iNb;
			case 'S' -> this.iScore    += iNb;
		}
	}

	// Cette méthode peut retirer ou ajouter du score
	public void changeScore( int iScore )
	{
		this.iScore += iScore;
	}

	public int  getScore()
	{
		return this.iScore;
	}

	//Cette méthode sert à echangé une ressource entre la banque et le joueur
	public boolean echangerRscBanqueVJoueur( Joueur j,char rsc,int iNb )
	{
		
		if( this.getRsc( rsc ) < j.getRsc( rsc ) )
		{
			return false;
		}
		else
		{
			j.ajouterEnlever(rsc, iNb);
			this.ajouterEnlever(rsc,-iNb);
			return true;
		}

	}


	public String toString ()
	{
		return  "Nombre de Pierre : " + this.iNbPierre + "\n" +
		        "Nombre de bois   : " + this.iNbBois   + "\n" +
		        "Nombre d'eau     : " + this.iNbEau    + "\n" +
		        "Nombre de Ble    : " + this.iNbBle    + "\n" +
		        "Nombre de Piece  : " + this.iNbPiece   ;
	}

}

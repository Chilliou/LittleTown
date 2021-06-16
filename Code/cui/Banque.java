package littletown.cui;

/**
* Gère les ressources de la banque et échanges entre joueurs.
*/
public class Banque
{
	private int iNbPierre;
	private int iNbBois;
	private int iNbEau;
	private int iNbBle;

	private int iNbPiece;

	private int iScore;

	/**
	* Constructeur de la classe Banque
	*/
	public Banque()
	{
		this.iNbPierre = 15;
		this.iNbBois   = 15;
		this.iNbEau    = 15;
		this.iNbBle    = 15;
		
		this.iNbPiece = 40;
		
		this.iScore   = 9999;
		
	}

	/**
	* Constructeur de la banque du joueur
	* @param iNbPiece
    *            Nombre de pieces du joueur
	*/
	public Banque(int iNbPiece)
	{
		this.iNbPierre = 0;
		this.iNbBois   = 0;
		this.iNbEau    = 0;
		this.iNbBle    = 0;

		this.iNbPiece = iNbPiece;
		
		this.iScore   = 0;

		
	}


	/**
	* Constructeur de la banque du joueur pour le scénario
	* @param mode
    *            Nombre de pieces du joueur
	*/
	public Banque(String mode)
	{
		this.iNbPierre = 0;
		this.iNbBois   = 0;
		this.iNbEau    = 0;
		this.iNbBle    = 0;

		this.iNbPiece  = 20;
		
		this.iScore    = 0;

		
	}
	
	/**
	* Affiche les informations d'une ressource spécifié en paramètre de la méthode.
	*
	* @param rsc
	*			Type de ressource
	*
	* @return Ressource(s).
	*/
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
							default  -> 99;
						};
	}

	/**
	* Permets d'enlever et ajouter une ressource donnée
	*
	* @param rsc
	*		Type de ressource.
	* @param iNb
	*		Nombre de ressource(s).
	*/	
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

	/**
	* Permets de retirer ou ajouter du score
	*
	* @param iScore
	*			Score à ajouter/retirer.
	*/
	public void changeScore( int iScore )
	{
		this.iScore += iScore;
	}

	/**
	* Permets d'obtenir le score.
	*
	* @return Score.
	*/
	public int  getScore()
	{
		return this.iScore;
	}

	/**
	* Permets d'échanger une ressource entre la banque et le joueur
	*
	* @param j
	*		Nom du joueur
	* @param rsc
	*		Type de ressource
	* @param iNb
	*		Nombre de ressources
	*
	* @return Retourne un boolean pour déterminer si la ressource peut être échangée
	*/
	public boolean echangerRscBanqueVJoueur( Joueur j, char rsc, int iNb )
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

	/**
	* Affiche les ressources de la banque.
	*
	* @return Retourne une chaine de caractères (String) de l'ensemble des ressources disponibles de la banque.
	*/
	public String toString ()
	{
		return  "Nombre de Pierre : " + this.iNbPierre + "\n" +
		        "Nombre de Arbre  : " + this.iNbBois   + "\n" +
		        "Nombre d'eau     : " + this.iNbEau    + "\n" +
		        "Nombre de Ble    : " + this.iNbBle    + "\n" +
		        "Nombre de Piece  : " + this.iNbPiece  ;
	}

}

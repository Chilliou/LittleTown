package littletown.ihm;


public class Metier 
{
    private int nbJoueur;

	public Metier()
	{
		this.nbJoueur = 2;
	}

	public void augmenterNbJoueur()
	{
		this.nbJoueur++;
		if ( this.nbJoueur > 4 ) this.nbJoueur = 2;
	}

	public void diminuerNbJoueur()
	{
		this.nbJoueur--;
		if ( this.nbJoueur < 2 ) this.nbJoueur = 4;
	}

	public int getNbJoueur(){ return this.nbJoueur; }    
}



public class Controleur
{
	private Metier metier;

	private IhmCUI ihm;

	public Controleur()
	{
		this.metier = new Metier(this);

		this.ihm    =  new IhmCUI   ( );

		this.lancerJeu();
	}

	private void lancerJeu()
	{
		int action;

		while ( this.metier.getTour() <= 4 )
		{

			for(int iTour = 0; iTour < this.metier.getNbJoueur(); iTour++)
			{
				boolean bTour = false;


				do
				{
					this.ihm.afficher();
				
					System.out.println(this.metier.getJoueurActif().toString());
					
					action = this.ihm.getChoix();	
					
					switch ( action )
					{
						case 1 -> this.metier.placerOuvrier     (  );
						case 2 -> this.metier.construireBatiment     (  );
						case 3 -> this.metier.activerTuile     (  );
						case 4 -> this.metier.getInfoBatiment     ( );
						case 5 -> this.metier.echangerPiece     (  );
						case 6 ->
						{
							if (this.metier.getJoueurActif().aJouer()) bTour = true;
							else System.out.println("Merci d'effectuer une action !");
	
							this.metier.finTour     (  );
						}
						
	
					}
	
				}
				while (! (this.metier.getJoueurActif().aJouer()) || bTour == false);
	
	
				this.metier.getJoueurActif().setAction(false);
				this.metier.changementJoueur();
	
			}

			// NOURIR OUVRIER
			this.metier.nourrirOuvrier();
			
		}
	}

  

	public IhmCUI getIhm ()
	{
		return this.ihm;
	}

	public static void main(String[] a)
	{
		new Controleur();
	}
}

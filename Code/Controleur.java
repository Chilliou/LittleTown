public class Controleur
{
	private Metier metier;

	private IhmCUI ihm;

	public Controleur()
	{
		this.metier = Metier();

		this.ihm    =  new IhmCui   ( this );

		this.lancerJeu();
	}

	private void lancerJeu()
	{
		char action;

		this.ihm.afficher();

		while ( this.metier.getTour() <= 4 )
		{
			action = this.ihm.getChoix();

			switch ( action )
			{
				case '1' : this.metier.placerOuvrier     ( );
				case '2' : this.metier.constuireBtm     (  );
				case '3' : this.metier.activerTuile     (  );
				case '4' : this.metier.infoBtm     ( );
				case '5' : this.metier.echangerRrc     (  );
				case '6' : this.metier.finDeTour     (  );

			}

			this.metier.changerJoueurActif();

			this.ihm.afficher();
		}
	}

	public static void main(String[] a)
	{
		new Controleur();
	}
}

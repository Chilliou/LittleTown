public class Controleur
{
	private Metier metier;

	private Plateau    PlateauPrincpal;
	private PlateauBas PlateauSecondaire;

	public Controleur()
	{
		this.metier = Metier();

		this.Plateau    =  new PlateauPrincpal   (  );
		this.PlateauBas = new PlateauSecondaire  (  );

		this.lancerJeu();
	}

	private void lancerJeu()
	{
		char action;

		this.PlateauPrincpal.afficher();
		this.PlateauPrincpal.afficher();

		while ( true )
		{
			action = this.ihm.getChoix();

			switch ( action )
			{
				case 'i' -> this.metier.poser     ( );
				case 'j' -> this.metier.constuire     (  );
				case 'k' -> this.metier.info     (  );
				

			}

			this.metier.changeJoueurActif();

			this.PlateauPrincpal.afficher();
			this.PlateauPrincpal.afficher();
		}
	}

	public static void main(String[] a)
	{
		new Controleur();
	}
}
import java.util.Scanner;

public class IhmCUI
{

    private Plateau    plateau;
    private PlateauBas plateauBas;

    public IhmCUI()
    {
        this.plateau    = new Plateau(2,1);
        this.plateauBas = new PlateauBas();
    }

    public void afficher()
    {
        System.out.println("Plateau actuel : ");
        System.out.println( this.plateau);
        System.out.println( this.plateauBas);

    }


    public Tuile getTuile(int x, int y)
    {
        return this.plateau.getTuile(x,y);
    }

    public TuileVide getTuileVide ( int x, int y )
    {
      return this.plateau.getTuileVide(x,y);
    }

    public void setTuile(int x, int y, Tuile tuile)
    {
      this.plateau.setTuile(x,y,tuile);
    }

    public void setTuileVide (int x, int y, TuileVide tuile)
    {
      this.plateau.setTuile(x,y,tuile);
    }

    public int getChoix()
    {
        String sChoix;
        int iChoix;

        sChoix  = "+-----------------------------------------------+\n";
        sChoix += "| > Action possibles:                           |\n";
        sChoix += "| 1 - Placer Ouvrier                            |\n";
        sChoix += "| 2 - Construire batiment                       |\n";
        sChoix += "| 3 - Activer tuile                             |\n";
        sChoix += "| 4 - Info Batiment                             |\n";
        sChoix += "| 5 - Echanger pièce -> ressources              |\n";
        sChoix += "| 6 - Fin de tour                               |\n";
        sChoix += "+-----------------------------------------------+\n";

		System.out.println( sChoix);
		 
        Scanner sc = new Scanner( System.in );
        
        System.out.print( "Faites votre choix : " );
        iChoix = sc.nextInt();
        

        return iChoix;
    }

    public String getSaisiePos()
    {
      Scanner sc = new Scanner( System.in );
      System.out.print( "Entrez la position : " );

      String saisie = sc.nextLine();
      return saisie;
        
    }
}

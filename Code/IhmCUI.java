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
        this.plateau.toString();
        this.plateauBas.toString();

    }

    public int getChoix()
    {
        String sChoix;
        int iChoix;

        sChoix  = "+-----------------------------------------------+\n";
        sChoix += "| > Action possibles:                            \n";
        sChoix += "| 1 - Placer Ouvrier                             \n";
        sChoix += "| 2 - Construire batiment                        \n";
        sChoix += "| 3 - Activer tuile                              \n";
        sChoix += "| 4 - Info Batiment                              \n";
        sChoix += "| 5 - Echanger pièce -> ressources               \n";
        sChoix += "| 6 - Fin de tour                                \n";
        sChoix += "+-----------------------------------------------+\n";

        try ( Scanner scanner = new Scanner( System.in ) )
        {
            
            System.out.print( "Faites votre choix :" );
            iChoix = scanner.nextInt();
        }


        return iChoix;
    }
}

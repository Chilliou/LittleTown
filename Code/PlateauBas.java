
public class PlateauBas
{
	private Tuile[] plateauBas;
    private int     iNbBle;
    private int     iNbConstru1;
    private int     iNbConstru2;

    public PlateauBas()
    {
        this.plateauBas   = new Tuile[6];
        this.iNbBle       = 5;
        this.iNbConstru1  = 0;
        this.iNbConstru2  = 0;
        this.initPlateauBas();
    }


    private void initPlateauBas()
    {
        this.plateauBas[0] = new Tuile("T");
        this.plateauBas[1] = new Tuile("T");
        this.plateauBas[2] = new Tuile("T");

        this.plateauBas[3] = new Tuile("T");
        this.plateauBas[4] = new Tuile("T");
        this.plateauBas[5] = new Tuile("T");
    }


    public String toString()
    {
		String sRet="";

        for (int cpt = 0; (cpt < this.plateauBas.length -2); cpt++)
        {
            if (cpt < 2)sRet += "+---";
            else sRet += "+----";
        }
		sRet+="+----+\n";

        sRet+="| " + this.iNbBle + " | B ";

        for (int cpt=0; cpt < 3; cpt++) sRet+= "| " + this.plateauBas[cpt].toString() + cpt+ " ";

        sRet+="|\n";


        for (int cpt = 0; (cpt < this.plateauBas.length -2); cpt++)
        {
            if (cpt < 2)sRet += "+---";
            else sRet += "+----";
        }
		sRet+="+----+\n";


        sRet+="| " + this.iNbConstru1 + " | " +  this.iNbConstru2 + " ";

        for (int cpt=3; cpt < 6; cpt++) sRet+= "| " + this.plateauBas[cpt].toString()+cpt + " ";
        sRet+="|\n";

        for (int cpt = 0; (cpt < this.plateauBas.length -2); cpt++)
        {
            if (cpt < 2)sRet += "+---";
            else sRet += "+----";
        }
		sRet+="+----+\n";

		return sRet;
    }

    public static void main(String[] args)
    {
		PlateauBas p  = new PlateauBas();
		System.out.println(p);
    }
}

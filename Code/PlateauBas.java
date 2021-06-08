
public class PlateauBas
{
	private Tuile[][] plateauBas;
    private int     iNbBle;
    private int     iNbConstru1;
    private int     iNbConstru2;

    public PlateauBas()
    {
        this.plateauBas   = new Tuile[2][3];
        this.iNbBle       = 5;
        this.iNbConstru1  = 0;
        this.iNbConstru2  = 0;
        this.initPlateauBas();
    }


    private void initPlateauBas()
    {
        this.plateauBas[0][0] = new Batiment("Bar", "P2C2", "", "S3", 7);
        this.plateauBas[0][1] = new Batiment("Librairie", "P4", "","M3", 8);
        this.plateauBas[0][2] = new Batiment("Mine", "A1P1", "", "M2", 4);

        this.plateauBas[1][0] = new Batiment("Puits", "A1P1", "", "S2", 4);
        this.plateauBas[1][1] = new Batiment("Zonton", "A3", "", "E2", 5);
        this.plateauBas[1][2] = new Batiment("Satue", "P4", "", "", 10);
    }

    public Batiment getBatiment(int x, int y)
    {
        return (Batiment) this.plateauBas[x-6][y-2];
    }

    public void setTuile(int x, int y, Tuile tuile)
    {
      this.plateauBas[x-6][y-2] = tuile;
    }


    public String toString()
    {
		String sRet="";

        for(int i = 0; i < this.plateauBas.length; i++)
        {
            for(int y = 0; y < this.plateauBas[0].length; y++)
            {
                sRet += this.plateauBas[i][y].toString() + " ";
            }
            sRet+= "\n";
        }

		return sRet;
    }

}

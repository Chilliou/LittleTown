import java.util.ArrayList;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Collections;

public class PlateauBas
{
	private Tuile[][] plateauBas;
	private int       iNbBle;
	private int       iNbConstru1;
	private int       iNbConstru2;

	private ArrayList<Batiment> ensBatiment;

	public PlateauBas()
	{
		this.plateauBas   = new Tuile[2][6];
		this.iNbBle       = 5;
		this.iNbConstru1  = 0;
		this.iNbConstru2  = 0;
		this.initTuile();
		this.initPlateauBas();
		
	}

	public boolean enleverBle ()
	{
		if ( this.iNbBle < 1 )
		    return false;

		this.iNbBle--;
		return true;
	}

	private void initPlateauBas()
	{

		int iNbTuile = 0;
		for ( int i = 0; i < this.plateauBas.length; i++ )
		{
			for ( int y = 0; y < this.plateauBas[0].length; y++ )
			{
				this.plateauBas[i][y] = this.ensBatiment.get( iNbTuile );
				iNbTuile++;
			}
		}

	}

	private void initTuile()
	{
		this.ensBatiment = new ArrayList<Batiment>();

		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( "tuile.data" ) );

			while ( sc.hasNextLine() )
			{
				String[] parts = sc.nextLine().split("/");
				ensBatiment.add( new Batiment(parts[0], parts[1], parts[2], parts[3], Integer.parseInt( parts[4] ) ) );
			}

			sc.close();
		}
		catch (Exception e) { e.printStackTrace(); }

		// On mélange l'ArrayList
		Collections.shuffle(this.ensBatiment);
		
	}


	public Batiment getBatiment( int x, int y )
	{
		return (Batiment) this.plateauBas[x-6][y-2];
	}

	public void setTuile(int x, int y, Tuile tuile)
	{
		this.plateauBas[x-6][y-2] = tuile;
	}

	public void addOuvrier( int iNumJoueur)
	{
		if ( iNumJoueur+1 == 1 )
			this.iNbConstru1++;

		if ( iNumJoueur+1 == 2 )
			this.iNbConstru2++;

	}

	public void clearOuvrier()
	{
		this.iNbConstru1 = 0;
		this.iNbConstru2 = 0;
	}


	public String toString()
	{
		String sRet="";
		int iNumLigne = 7;

		for ( int i = 0; i < this.plateauBas[0].length + 1; i++ )
			sRet+= "+----";

		sRet += "+----+\n";

		for( int i = 0; i < this.plateauBas.length; i++ )
		{
			for( int y = 0; y < this.plateauBas[0].length; y++ )
			{
				if (i == 0 && y == 0)
					sRet += "| " + this.iNbBle + "  | B  ";

				if (i == 1 && y == 0)
					sRet += "| " + this.iNbConstru1 + "  | " + this.iNbConstru2 + "  ";

				sRet += "| " + this.plateauBas[i][y].toString() + "  ";

			}
			
			sRet+= "|      " + iNumLigne + "\n";
			iNumLigne++;
			
			for (int cpt = 0; cpt < this.plateauBas[0].length + 1; cpt++)
				sRet+= "+----";

			sRet += "+----+\n";
		}

		return sRet;
	}

}

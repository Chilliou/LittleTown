public class Metier 
{
    private int val;

	public Metier()
	{
		this.val = 2;
	}

	public void augmenter()
	{
		this.val++;

		if ( this.val > 4 ) this.val = 2;
	}

	public void diminuer()
	{
		this.val--;

		if ( this.val < 2 ) this.val = 4;
	}

	public int getVal(){ return this.val; }    
}

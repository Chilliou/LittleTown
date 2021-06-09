public class TuileVide extends Tuile
{
  private Ouvrier ouvrier;

  public TuileVide ()
  {

    super( "Vide" );
    this.ouvrier = null;
  }

  public void setOuvrier ( Ouvrier ovr )
  {
    this.ouvrier = ovr;
  }

  public Ouvrier getOuvrier ()
  {
    return this.ouvrier;
  }

  public Joueur getProprietaire()
  { 
    if ( this.ouvrier == null ) return null;

    return this.ouvrier.getProprietaire();
  }

  public String toString ()
  {
    if ( this.ouvrier != null )
      return "O"+this.getProprietaire().getNumJoueur();
    else
      return " ";
  }

}
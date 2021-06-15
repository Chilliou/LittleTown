package littletown.cui;


public class Objectif
{
    private String sCondition;
    private int    iScore;


    public Objectif(String sCondition, int iScore)
    {
        this.sCondition = sCondition;
        this.iScore     = iScore;
    }

    public int getScore()
    {
        return this.iScore;
    }

    public String getCondition()
    {
        return this.sCondition;
    }
}
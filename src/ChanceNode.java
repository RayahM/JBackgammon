/*
 * Every instance of this class is a state of the player CHANCE.
 */
public class ChanceNode 
{

	
	//the result of the first dice.
    private int dice1;

    //the result of the second dice.
    private int dice2;

    //the probability of having the combined result of the two dices...
    private double probability;
    
    
    
    
    //default constructor
    public ChanceNode()
    {
        dice1 = 0;
        dice2 = 0;
        probability = -1;
    }
    
    
    
    //constructor
    public ChanceNode(int d1, int d2, double p)
    {
        dice1 = d1;
        dice2 = d2;
        probability = p;
    }
    
    
    
    
    public int getDice1()
    {
        return dice1;
    }
    
    
    
    public int getDice2()
    {
        return dice2;
    }
    
    
    
    public double getProbability()
    {
        return probability;
    }
	
}
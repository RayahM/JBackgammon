/*
 * Every instance of this class represents a node of the board.
 */
public class Node
{
	
	//black or white.
    private String colour;

    //the number of tower , where this node lies, or -1 if the node went out of the game.
    private int position;

    //A round button which represents the current piece.
    private RoundButton Rbutton;

    //This indicates the number of the piece , i.e. 1-15
    private int NumberOfNode;
    
    
    
    //constructor
    public Node(String str, int pos,RoundButton rb , int number)
    {
        colour = str;
        position = pos;
        Rbutton = rb;
        NumberOfNode = number;
    }
    
    
    public void setPosition(int position)
    {
    	this.position = position;
    }
    
    
    public int getPosition()
    {
    	return this.position;
    }
    
    
    //Returns the colour of the current Node.
    public String getColour()
    {
        return colour;
    }
    
    
    //Returns the button which represents the current Node.
    public RoundButton getButton()
    {
        return Rbutton;
    }
    
    
    //Returns the Number-ID of the current Node.
    public int getNumberOfNode()
    {
        return NumberOfNode;
    }
    
    
    @Override
    public int hashCode()
    {
        return colour.hashCode() + position + NumberOfNode;
    }

}
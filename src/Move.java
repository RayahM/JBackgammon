import java.util.ArrayList;

/*
 * Every instance of this class consists of a number of 0-4 moves according to the result of the dices.
 */
public class Move
{
	
	//The value of the current move.
    double value;


    //in this list we are going to keep couples of integers.
    //The first one is the position were we want a piece to go and second one is were the piece was.
    private ArrayList<Integer> moves;


    //constructor 1
    public Move()
    {
        moves = new ArrayList<Integer>();
        value = 0.0;
    }
    
    
    //constructor 2
    public Move(double v)
    {
        moves = new ArrayList<Integer>();
        value = v;
    }
    
    
    //constructor 3
    public Move(Move mv)
    {
        value = mv.value;
        moves = new ArrayList<Integer>();

        for (int i = 0; i < mv.moves.size(); i++)
        {
            moves.add(mv.moves.get(i));
        }
    }
    
    
    //Constructor 4
    public Move(int current_position, int next_position, double v)
    {
        moves = new ArrayList<Integer>();
        moves.add(next_position);
        moves.add(current_position);
        value = v;
    }
    
    
    
    
    public void setValue(double value)
    {
    	this.value = value;
    }
    
    
    public double getValue()
    {
    	return this.value;
    }
    
    
    //returns the moves
    public ArrayList<Integer> getMoves()
    {
        return moves;
    }
    
    
    //sets the moves
    public void setMoves(ArrayList<Integer> list)
    {
        //we clear all the previous moves.
        moves.clear();

        for (int i = 0; i < list.size(); i++)
        {
            moves.add(list.get(i));
        }
    }
    
    
    //Ads a pair of integers to the List
    public void AddMove(int current_position, int next_position)
    {
        moves.add(next_position);
        moves.add(current_position);
    }
    
    
    public void AddMove(Move mv)
    {
        for (int i = 0; i < mv.moves.size(); i++)
        {
            moves.add(mv.moves.get(i));
        }
    }
    
    
    @Override
    public boolean equals(Object obj)
    {
        if (! (obj instanceof Move) ) return false;

        Move m = (Move)obj;

        if (moves.size() != m.moves.size()) return false;

        for (int i = 0; i < moves.size(); i += 2)
        {
            //a move is equal to another if the final position is the same.
            if ((moves.get(i+1) != m.moves.get(i+1)) && (moves.get(i) != m.moves.get(i))) return false;
        }

        return true;
    }
    
    
    @Override
    public int hashCode()
    {
        int sum = (int)this.value;

        for (int i = 0; i < moves.size(); i++)
        {
            sum += moves.get(i);
        }

        return sum;
    }
	
}
import java.util.ArrayList;
import java.util.Random;

/*
 * This class contains the implementation of the minimax algorithm , with and without pruning , 
 * that the computer will use to decide it's moves.
 */
public class MiniMax 
{

	 //Variable that holds the maximum depth the MiniMax algorithm will reach for this player.
    private int maxDepth;

    //constructor
    public MiniMax(int maxDepth)
    {
	    this.maxDepth = maxDepth;
    }
    
    
    
    
    
    
    //---------------------------------------ExpectiMiniMax algorith without pruning---------------------------------------//

    //Initiates the ExpectiMiniMax algorithm.
    public Move ExpectiMiniMax(Board board , int dice1 , int dice2)
    {
        //The computer is always the Max player.
        return Max(new Board(board) , 0 , dice1 , dice2);
    }
    
    
    
    
    //The max and min functions are called interchangingly, one after another until a max depth is reached.
    private Move Max(Board board , int depth , int dice1 , int dice2)
    {
        Random r = new Random();

        /* If MAX is called on a state that is terminal or after a maximum depth is reached,
         * then a heuristic is calculated on the state and the move returned.
         */
        if ((board.IsTerminal()) || (depth == maxDepth))
        {
            Move lastMove = new Move(board.getLastMove());
            return lastMove;
        }

        //The children-moves of the state are calculated
        ArrayList<Board> children = new ArrayList<Board>(board.GetChildren(dice1,dice2));
    
        Move maxMove = new Move(Integer.MIN_VALUE);

        for(Board child : children)
        {
           //And for each child chance is called, on a lower depth
           Move move = Chance(child, depth+1);
           
            if (move.getValue() >= maxMove.getValue())
            {
                //If the heuristic has the save value then we randomly choose one of the two moves
                if (move.getValue() == maxMove.getValue())
                {
                    if (r.nextInt(2) == 1)
                    {
                        maxMove.setValue(move.getValue());
                        maxMove.setMoves(child.getLastMove().getMoves());
                    }
                }

                else
                {
                	maxMove.setValue(move.getValue());
                    maxMove.setMoves(child.getLastMove().getMoves());
                }
            }
        }

        return maxMove;
    }
    
    
    
    
    //Min works similarly to max.
    private Move Min(Board board , int depth , int dice1 , int dice2)
    {
        Random r = new Random();

        if ((board.IsTerminal()) || (depth == maxDepth))
        {
            Move lastMove = new Move(board.getLastMove());
            return lastMove;
        }

        //The children-moves of the state are calculated
        ArrayList<Board> children = new ArrayList<Board>(board.GetChildren(dice1,dice2));
        Move minMove = new Move(Integer.MIN_VALUE);

        for(Board child : children)
        {
            //And for each child chance is called, on a lower depth
            Move move = Chance(child , depth+1);
           
            if (move.getValue() <= minMove.getValue())
            {
                //If the heuristic has the save value then we randomly choose one of the two moves
                if (move.getValue() == minMove.getValue())
                {
                    if (r.nextInt(2) == 0)
                    {
                    	minMove.setValue(move.getValue());
                        minMove.setMoves(child.getLastMove().getMoves());
                    }
                }

                else
                {
                	minMove.setValue(move.getValue());
                    minMove.setMoves(child.getLastMove().getMoves());
                }
            }
        }

        return minMove;
    }
    
    
    
    
    //Chance is the "third" player of this game.
    private Move Chance(Board board , int depth)
    {

        if ((board.IsTerminal()) || (depth == maxDepth))
        {
            Move lastMove = new Move(board.getLastMove());
            return lastMove;
        }

        ArrayList<ChanceNode> children = new ArrayList<ChanceNode>(board.GetChanceChildren());
        double TotalValue = 0;

        //if last player was Max,apply the Chance on Max's children.
        if (board.getPlayer())
        {
            board.setPlayer(false);
            Move minMove = new Move(board.getLastMove());

            for (ChanceNode child : children)
            {
                Move move = Min(board, depth + 1, child.getDice1(), child.getDice2());
                TotalValue += (child.getProbability() * move.getValue());
            }

            minMove.setValue(TotalValue);
            return minMove;
        }

        //else if last player was Min,apply the Chance on Min's children.
        else
        {
        	 board.setPlayer(true);
            Move maxMove = new Move(board.getLastMove());

            for (ChanceNode child : children)
            {

                Move move = Max(board, depth + 1, child.getDice1(), child.getDice2());
                TotalValue += (child.getProbability() * move.getValue());
            }

            maxMove.setValue(TotalValue);
            return maxMove;
        }
        
    }
    
    
    
    
    
    
    //---------------------------------------ExpectiMiniMax algorith with alpha-beta pruning---------------------------------------//

    //Initiates the ExpectiMiniMax algorithm.
    public Move ExpectiMiniMaxAlphaBeta(Board board, int dice1, int dice2)
    {
        //The computer is always the Max player.
        return MaxAlphaBeta(new Board(board), 0, dice1, dice2, Double.MIN_VALUE , Double.MAX_VALUE);
    }
    
    
    
    
    //The max algorithm with alpha-beta pruning.
    private Move MaxAlphaBeta(Board board, int depth, int dice1, int dice2, double a, double b)
    {
        Random r = new Random();
       
        /* If MAX is called on a state that is terminal or after a maximum depth is reached,
         * then a heuristic is calculated on the state and the move returned.
         */
        if ((board.IsTerminal()) || (depth == maxDepth))
        {
            Move lastMove = new Move(board.getLastMove());
            return lastMove;
        }
      
        //The children-moves of the state are calculated
        ArrayList<Board> children = new ArrayList<Board>(board.GetChildren(dice1, dice2));
        
        Move maxMove = new Move(Integer.MIN_VALUE);
       
        for (Board child : children)
        {
            //And for each child chance is called, on a lower depth
            Move move = ChanceAlphaBeta(new Board(child), depth + 1, a, b);
            
            if (move.getValue() >= maxMove.getValue())
            {
                //If the heuristic has the save value then we randomly choose one of the two moves
                if (move.getValue() == maxMove.getValue())
                {
                    if (r.nextInt(2) == 1)
                    {
                        maxMove.setValue(move.getValue());
                        maxMove.setMoves(child.getLastMove().getMoves());
                    }
                }

                else
                {
                	maxMove.setValue(move.getValue());
                    maxMove.setMoves(child.getLastMove().getMoves());
                }
            }

            //Beta pruning.
            if (maxMove.getValue() >= b)  return maxMove; 

            //Update the a of the current max node.
            a = (a > maxMove.getValue()) ? a : maxMove.getValue();
        }

        return maxMove;
    }
    
    
    
    
    //The min algorithm with alpha-beta pruning.
    private Move MinAlphaBeta(Board board, int depth, int dice1, int dice2, double a, double b)
    {
        Random r = new Random();
        
        if ((board.IsTerminal()) || (depth == maxDepth))
        {
            Move lastMove = new Move(board.getLastMove());
            return lastMove;
        }
        
        //The children-moves of the state are calculated
        ArrayList<Board> children = new ArrayList<Board>(board.GetChildren(dice1, dice2));
        Move minMove = new Move(Integer.MAX_VALUE);

        for (Board child : children)
        {
            //And for each child chance is called, on a lower depth
            Move move = ChanceAlphaBeta(new Board(child), depth+1,a,b);

            if (move.getValue() <= minMove.getValue())
            {
                //If the heuristic has the save value then we randomly choose one of the two moves
                if (move.getValue() == minMove.getValue())
                {
                    if (r.nextInt(2) == 0)
                    {
                    	minMove.setValue(move.getValue());
                        minMove.setMoves(child.getLastMove().getMoves());
                    }
                }

                else
                {
                	minMove.setValue(move.getValue());
                    minMove.setMoves(child.getLastMove().getMoves());
                }
            }

            //Alpha pruning.
            if (minMove.getValue() <= a) return minMove;

            //Update the b of the current min node.
            b = (b < minMove.getValue()) ? b : minMove.getValue();
        }

        return minMove;
    }
    
    
    
    
    //The chance "player" with alpha-beta pruning.
    private Move ChanceAlphaBeta(Board board, int depth , double a , double b)
    {
       
    	 if ((board.IsTerminal()) || (depth == maxDepth))
         {
             Move lastMove = new Move(board.getLastMove());
             return lastMove;
         }

         ArrayList<ChanceNode> children = new ArrayList<ChanceNode>(board.GetChanceChildren());
         double TotalValue = 0;

         //if last player was Max,apply the Chance on Max's children.
         if (board.getPlayer())
         {
             board.setPlayer(false);
             Move minMove = new Move(board.getLastMove());

             for (ChanceNode child : children)
             {
                 Move move = MinAlphaBeta(new Board(board), depth + 1, child.getDice1(), child.getDice2() , a , b);
                 TotalValue += (child.getProbability() * move.getValue());
             }

             minMove.setValue(TotalValue);
             return minMove;
         }

         //else if last player was Min,apply the Chance on Min's children.
         else
         {
         	 board.setPlayer(true);
             Move maxMove = new Move(board.getLastMove());

             for (ChanceNode child : children)
             {

                 Move move = MaxAlphaBeta(new Board(board), depth + 1, child.getDice1(), child.getDice2() , a , b);
                 TotalValue += (child.getProbability() * move.getValue());
             }

             maxMove.setValue(TotalValue);
             return maxMove;
         }

    }
	

}
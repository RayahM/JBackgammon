import java.util.ArrayList;


public class Board implements Cloneable
{

	//represents the current  board.
    private Tower[] gameBoard;

    //Immediate moves that lead to this board.
    private Move LastMove;

    //true=computer , false=player.
    private boolean player;
    
    
    
    
    
    
    //constructor 1
    public Board(Board board)
    {
        gameBoard = new Tower[board.gameBoard.length];

        LastMove = new Move(board.LastMove);
        player = board.player;

        for (int i = 0; i < gameBoard.length; i++)
        {
        	gameBoard[i] = new Tower(i , board.gameBoard[i].get_X() , board.gameBoard[i].get_Y() , board.gameBoard[i].getTextField());
        	gameBoard[i].setColourOfNodes(board.gameBoard[i].getColourOfNodes());
        	gameBoard[i].setNumberOfNodes(board.gameBoard[i].getNumberOfNodes());   
        	gameBoard[i].setNodes(board.gameBoard[i].getNodes());
        }
    }
    
    
    
    
    //constructor 2
    public Board(Tower[] towers)
    {
        gameBoard = new Tower[towers.length];
        LastMove = new Move();
        player = true;

        for (int i = 0; i < towers.length; i++)
        {
        	gameBoard[i] = new Tower(i , towers[i].get_X() , towers[i].get_Y() , towers[i].getTextField());
        	gameBoard[i].setColourOfNodes(towers[i].getColourOfNodes());
        	gameBoard[i].setNumberOfNodes(towers[i].getNumberOfNodes());   
        	gameBoard[i].setNodes(towers[i].getNodes());
        }
    }
    
    
    
    
    //Checks if the state is terminal.
    public boolean IsTerminal()
    {
        //the state is terminal if computer or player has gathered both of his/its pieces.
        return (gameBoard[24].getNumberOfNodes() == 15 || gameBoard[25].getNumberOfNodes() == 15);
    }
    
    
    
    
    //sets the game board.
    public void setBoard(Tower[] towers)
    {
        for (int i = 0; i < 28; i++)
        {
        	gameBoard[i] = new Tower(i , towers[i].get_X() , towers[i].get_Y() , towers[i].getTextField());
        	gameBoard[i].setColourOfNodes(towers[i].getColourOfNodes());
        	gameBoard[i].setNumberOfNodes(towers[i].getNumberOfNodes());   
        	gameBoard[i].setNodes(towers[i].getNodes());
        }
    }
    
    
    
    
    //overriding equals method.
    @Override
	public boolean equals(Object obj)
    { 
        if (obj == null || !(obj instanceof Board)) return false;

        Board b = (Board)obj;
      
        for (int i = 0; i < gameBoard.length; i++)
        {
            //if the number of nodes or the colour of the nodes at the same position on the board are not the same , then the boards are different.
            if (gameBoard[i].getNumberOfNodes() != b.gameBoard[i].getNumberOfNodes() || !(gameBoard[i].getColourOfNodes().equals(b.gameBoard[i].getColourOfNodes())))
            {
                return false;
            }
        }
        
        return true;
    }
    
    
    
    
    //overriding GetHashCode method.
    @Override
	public int hashCode()
    {
        int count = 0;

        for (Tower tower : gameBoard)
        {
            count += tower.hashCode();
        }
        
        return count;
    }
    
    
    
    
    //overrides clone method.
    @Override
	public Object clone()
    {
    	try
    	{
    		Board b = (Board)super.clone();
    		b.gameBoard = gameBoard.clone();
    		b.LastMove = LastMove;
    		b.player = player;		
    		return b;
    	}
    	
    	catch(Exception e)
    	{
    		System.err.println("ERROR : "+e.getMessage());
    		return null;
    	}
    }
    
    
    
    
    //overrides toString method.
    @Override
	public String toString()
    {
    	String result = "";
    	
    	for(int i=0; i<this.gameBoard.length; i++)
    	{
    		result += i+" "+this.gameBoard[i].getColourOfNodes()+ " " + this.gameBoard[i].getNumberOfNodes()+"\n";
    	}
    	
    	return result;
    }
    
    
    
    
    public boolean getPlayer()
    {
    	return this.player;
    }
    
    
    
    
    public void setPlayer(boolean player)
    {
    	this.player=player;
    }
    
    
    
    
    
    //--------------------------------------------MOVE METHODS AND HELPERS--------------------------------------------//
    
    //Makes the specified move.
    public void MakeMove(Move move)
    {
        ArrayList<Integer> tempList = move.getMoves();

        for (int i = 0; i < tempList.size(); i += 2)
        {
            gameBoard[tempList.get(i)].addNodeWithMove(gameBoard[tempList.get(i+1)].removeNodeWithMove());
        }

        LastMove = new Move(move);
        LastMove.setValue(Evaluate());
    }
    
    
    
    
    //Makes a local move.
    private void MakeLocalMove(Move mv)
    {
        ArrayList<Integer> tempList = mv.getMoves();

        for (int i = 0; i < tempList.size(); i += 2)
        {
        	 gameBoard[tempList.get(i)].addNodeWithoutMove(gameBoard[tempList.get(i+1)].removeNodeWithoutMove());
        }

        LastMove.AddMove(mv);
        LastMove.setValue(Evaluate());
    } 
    
    
    
    
    //Undo all the moves that occured...
    private void UndoMove(Move move)
    {
        ArrayList<Integer> tempList = move.getMoves();

        for (int i = tempList.size() - 1; i >= 0; i -= 2)
        {
            gameBoard[tempList.get(i)].addNodeWithoutMove(gameBoard[tempList.get(i-1)].removeNodeWithoutMove());
        }
    }
    
    
    
    
    //returns the last moves.
    public Move getLastMove()
    {
        return LastMove;
    }
    
    
    
    
    
    
    //-----------------------------------------Evaluate METHOD AND HEURISTICS-----------------------------------------//

    //evaluates the current board according to the heuristics.
    public double Evaluate()
    {
        double blackPoints = (BlackDoors()) + (2 * BlackDoorsInRow()) - (2 * ExposedBlackPieces()) + (10 * BlackPiecesOut()) + (4 * CapturedWhitePieces()) ;
        double whitePoints = (WhiteDoors()) + (2 * WhiteDoorsInRow()) - (2 * ExposedWhitePieces()) + (10 * WhitePiecesOut()) + (4 * CapturedBlackPieces()) ;
       
        return (blackPoints - whitePoints);
    } 




    //heuristic 1 : counts how many "doors" the computer has...
    private int BlackDoors()
    {
        int total = 0;

        for (int i = 0; i < 24; i++)
        {
            if(gameBoard[i].getNumberOfNodes()>=2 && gameBoard[i].getColourOfNodes().equals("black")) total++;
        }

        return total;
    }

    //The same as above but now for the player...
    private int WhiteDoors()
    {
        int total = 0;

        for (int i = 0; i < 24; i++)
        {
            if (gameBoard[i].getNumberOfNodes() >= 2 && gameBoard[i].getColourOfNodes().equals("white")) total++;
        }

        return total;
    }



    //heuristic 2 : counts how many "doors" in a row the computer has...
    private int BlackDoorsInRow()
    {
        int total = 0;

        for (int i = 0; i < 24; i++)
        {
            //if there is a door...
            if (gameBoard[i].getNumberOfNodes() >= 2 && gameBoard[i].getColourOfNodes().equals("black"))
            {
                //check if there are any doors after it...
                for (int j = i+1; j < 24; j++)
                {
                    if (gameBoard[j].getNumberOfNodes() >= 2 && gameBoard[j].getColourOfNodes().equals("black")) total++;
                    else break;
                }
            } 
        }

        return (total == 0) ? total : (total + 1);
    }

    //The same as above but now for the player...
    private int WhiteDoorsInRow()
    {
        int total = 0;

        for (int i = 0; i < 24; i++)
        {
            //if there is a door...
            if (gameBoard[i].getNumberOfNodes() >= 2 && gameBoard[i].getColourOfNodes().equals("white"))
            {
                //check if there are any doors after it...
                for (int j = i+1; j < 24; j++)
                {
                    if (gameBoard[j].getNumberOfNodes() >= 2 && gameBoard[j].getColourOfNodes().equals("white")) total++;
                    else break;
                }
            }
        }

        return (total == 0) ? total : (total + 1);
    }



    //heuristic 3 : counts how many exposed pieces the computer has. i.e how many pieces are "alone" on the board.
    private int ExposedBlackPieces()
    {
        int total = 0;

        for (int i = 0; i < 24; i++)
        {
            if (gameBoard[i].getNumberOfNodes() == 1 && gameBoard[i].getColourOfNodes().equals("black"))
            {
                if (IsBlackPieceInDanger(i)) total++;
            }
        }
        
        return total;
    }

    //checks if the specified black piece that is "alone" on the board , is under danger.
    private boolean IsBlackPieceInDanger(int pos)
    {
        for (int i = pos+1; i < 28; i++)
        {
            if (i == 27 || i == 24) continue;

            if (gameBoard[i].getNumberOfNodes()>=1 && gameBoard[i].getColourOfNodes().equals("white"))
            {
                return true;
            }
        }

        return false;
    }


    //The same as above but now for the player...
    private int ExposedWhitePieces()
    {
        int total = 0;

        for (int i = 23; i >=0 ; i--)
        {
            if (gameBoard[i].getNumberOfNodes() == 1 && gameBoard[i].getColourOfNodes().equals("white"))
            {
                if (IsWhitePieceInDanger(i)) total++;
            }
        }

        return total;
    }

    //checks if the specified black piece that is "alone" on the board , is under danger.
    private boolean IsWhitePieceInDanger(int pos)
    {
        for (int i = pos - 1; i >= 0; i--)
        {
            if (i == 26 || i == 25) continue;

            if (gameBoard[i].getNumberOfNodes()>=1 && gameBoard[i].getColourOfNodes().equals("black"))
            {
                return true;
            }
        }

        return false;
    }
   


    //heuristic 4 : counts how many pieces the computer has moved out of the game.
    private int BlackPiecesOut()
    {
        return gameBoard[26].getNumberOfNodes();
    }

    //The same as above but now for the player...
    private int WhitePiecesOut()
    {
        return gameBoard[27].getNumberOfNodes();
    }



    //heuristic 5 : counts how many pieces the computer has captured from the player
    private int CapturedWhitePieces()
    {
        return gameBoard[25].getNumberOfNodes();
    }

    //The same as above but now for the player...
    private int CapturedBlackPieces()
    {
        return gameBoard[24].getNumberOfNodes();
    }
    
    
    
    
    
     
    //-----------------------------------------IsValidMove METHOD AND HELPERS-----------------------------------------//

    //Checks if a move from computer is valid.
    private boolean IsValidBlackMove(int current_position, int next_position)
    {
        //-10 is a help value to recognize a piece that can not move out of the game.
        if (next_position == -10) return false;
      
        //if we try to take a piece from the captured stack but there is no piece there , return false.
        if (current_position == -1 && !BlackCaptured()) return false;

        //if there are captured pieces, we must move only them!
        if (BlackCaptured() && current_position != -1) return false;

        //if there is a "white door" in next_position , return false. 
        if (gameBoard[next_position].getNumberOfNodes() >= 2 && gameBoard[next_position].getColourOfNodes().equals("white")) return false;

        //if black pieces are not in computer's area and computer tries to put a piece out of the game , return false.
        if (!BlacksInPlace() && next_position == 27) return false;

        //a black piece must always move to biggest positions.
        if (next_position <= current_position) return false;

        //restore the current_position if it was -1.
        current_position = (current_position == -1) ? 24 : current_position;

        //if there is no piece in current position , return false.
        if (gameBoard[current_position].getNumberOfNodes() == 0) return false;

        //if there is a white piece in current position, return false.
        if (gameBoard[current_position].getColourOfNodes().equals("white")) return false;


        //in any other case return true.
        return true;
    }

    //Checks if all black pieces are on the computer's area. i.e Towers 19-24(18-23).
    private boolean BlacksInPlace()
    {
        int inPlace = 0;

        for (int i = 18; i < 24; i++)
        {
            if (gameBoard[i].getColourOfNodes().equals("black")) inPlace += gameBoard[i].getNumberOfNodes();
        }

        return ((inPlace + gameBoard[27].getNumberOfNodes()) == 15);
    }

    //Checks if there are any black pieces captured.
    private boolean BlackCaptured()
    {
        return gameBoard[24].getNumberOfNodes() > 0;
    }

    //checks if a black piece can go out of the game.
    private boolean CanTakeBlackPiece(int pos)
    {
        if (BlacksInPlace())
        {
            for (int i = pos - 1; i >= 18; i--)
            {
                if (gameBoard[i].getNumberOfNodes() >= 1 && gameBoard[i].getColourOfNodes().equals("black")) return false;
            }
        }

        return true;
    }
    
    
    
    
    //checks if a move from player is valid.
    private boolean IsValidWhiteMove(int current_position, int next_position)
    {
        //-10 is a help value to recognize a piece that can not move out of the game.
        if (next_position == -10) return false;

        //if we try to take a piece from the captured stack but there is no piece there , return false.
        if (current_position == 24 && !WhiteCaptured()) return false;

        //if there are captured pieces, we must move only them!
        if (WhiteCaptured() && current_position != 24) return false;

        //if there is a "black door" in next_position , return false. 
        if (gameBoard[next_position].getNumberOfNodes() >= 2 && gameBoard[next_position].getColourOfNodes().equals("black")) return false;

        //if white pieces are not in computer's area and player tries to put a piece out of the game , return false.
        if (!WhitesInPlace() && next_position <=-1) return false;

        //a white piece must always move to smaller positions.
        if (next_position >= current_position) return false;


        //restore the current_position if it was 24.
        current_position = (current_position == 24) ? 25 : current_position;

        //if there is no piece in current position , return false.
        if (gameBoard[current_position].getNumberOfNodes() == 0) return false;

        //if there is a black piece in current position, return false.
        if (gameBoard[current_position].getColourOfNodes().equals("black")) return false;


        //in any other case return true.
        return true;
    }

    //Checks if there are any white pieces captured.
    private boolean WhiteCaptured()
    {
        return gameBoard[25].getNumberOfNodes() > 0;
    }

    //Checks if all white pieces are on the player's area. i.e Towers 1-6.
    private boolean WhitesInPlace()
    {
        int inPlace = 0;

        for (int i = 0; i < 6; i++)
        {
           if (gameBoard[i].getColourOfNodes().equals("white")) inPlace += gameBoard[i].getNumberOfNodes();
        }

        return ((inPlace + gameBoard[26].getNumberOfNodes()) == 15);
    }

    //checks if a black piece can go out of the game.
    private boolean CanTakeWhitePiece(int pos)
    {
        if (WhitesInPlace())
        {
            for (int i = pos + 1; i <= 5; i++)
            {
                if (gameBoard[i].getNumberOfNodes() >= 1 && gameBoard[i].getColourOfNodes().equals("white")) return false;
            }
        }

        return true;
    }
    
    
    
    
    //Checks if there is one piece of the opposite colour on the specified tower , so that it can be captured.
    private boolean OnePieceAlone(int tower)
    {
        //if it is computer's turn...
        if (player)
        {
            if (gameBoard[tower].getNumberOfNodes() == 1 && gameBoard[tower].getColourOfNodes().equals("white")) return true;
        }

        //else if it is player's turn...
        else
        {
            if (gameBoard[tower].getNumberOfNodes() == 1 && gameBoard[tower].getColourOfNodes().equals("black")) return true;
        }
        
        return false;
    }
    
    
    
    
    
    
    //-----------------------------------------GetChildren METHODS AND HELPERS-----------------------------------------//
    
    //returns all the suitable children of this state, according two the dices.
    public ArrayList<Board> GetChildren(int dice1, int dice2)
    {
        if (player) return GetBlackChildren(dice1, dice2);
        else return GetWhiteChildren(dice1, dice2);
    }
    
    
    
    
    //return all the suitable moves the computer can do , if it is to play at this turn...
    private ArrayList<Board> GetBlackChildren(int dice1, int dice2)
    {
        ArrayList<Board> myList = new ArrayList<Board>();           

        if (dice1 != dice2)
        {

            //this list will contain the cases that only one of the two dices can be played.
            ArrayList<Board> OneMoveList = new ArrayList<Board>();

            //for1 :there we play the dice1 first and foreach available move we play the dice2
            for (int i = 0; i < 28; i++)
            {
                if (i == 25 || i == 26) continue;

                int position1 = i;

                //24=black captured pieces stack. -1=the position there the piece should start from.
                if (position1 == 24) position1 = -1;

                int next_position1 = position1 + dice1;

                if (next_position1 >= 24)
                {
                    if (next_position1 == 24) next_position1 = 27;

                    else
                    {
                        if (CanTakeBlackPiece(position1)) next_position1 = 27;
                        else next_position1 = -10;
                    }
                }

                if (IsValidBlackMove(position1, next_position1))
                {
                    //restore the current position if it was -1.
                    position1 = (position1 == -1) ? 24 : position1;

                    Move mv1 = new Move();

                    if (OnePieceAlone(next_position1) && next_position1 != 27)
                    {
                        mv1.AddMove(next_position1, 25);
                    }

                    mv1.AddMove(position1, next_position1);
                    
                    LastMove = new Move();

                    //make the move.
                    MakeLocalMove(mv1);

                    //we avoid duplicates.
                    if (!(OneMoveList.contains(this)))
                    {
                        //add the child to the list.
                        OneMoveList.add(new Board(this));
                    }

                    //take back the moves, so that the result won't be permanent.
                    UndoMove(LastMove);
                    
                    
                    for (int j = 0; j < 28; j++)
                    {

                        LastMove = new Move();

                        //make the move.
                        MakeLocalMove(mv1);

                        if (j == 25 || j == 26)
                        {
                            UndoMove(mv1);
                            continue;
                        }

                        int position2 = j;

                        //24=black captured pieces stack. -1=the position there the piece should start from.
                        if (position2 == 24) position2 = -1;

                        int next_position2 = position2 + dice2;

                        if (next_position2 >= 24)
                        {
                            if (next_position2 == 24) next_position2 = 27;

                            else
                            {
                                if (CanTakeBlackPiece(position2)) next_position2 = 27;
                                else next_position2 = -10;
                            }
                        }

                        if (IsValidBlackMove(position2, next_position2))
                        {
                            //restore the current position if it was -1.
                            position2 = (position2 == -1) ? 24 : position2;

                            Move mv2 = new Move();

                            if (OnePieceAlone(next_position2) && next_position2 != 27)
                            {
                                mv2.AddMove(next_position2, 25);
                            }

                            mv2.AddMove(position2, next_position2);

                            //make the move.
                            MakeLocalMove(mv2);
                            
                            //we avoid duplicates.
                            if (!(myList.contains(this)))
                            {
                                //add the child to the list.
                                myList.add(new Board(this));
                            }
                            
                            //take back the moves, so that the result won't be permanent.
                            UndoMove(LastMove);

                        }

                        else
                        {
                            UndoMove(mv1);
                        }

                    }

                }
                
            }//for1 end

         	//for2 : there we play the dice2 first and foreach available move we play the dice1
            for (int i = 0; i < 28; i++)
            {

                if (i == 25 || i == 26) continue;

                int position1 = i;

                //24=black captured pieces stack. -1=the position there the piece should start from.
                if (position1 == 24) position1 = -1;

                int next_position1 = position1 + dice2;

                if (next_position1 >= 24)
                {
                    if (next_position1 == 24) next_position1 = 27;

                    else
                    {
                        if (CanTakeBlackPiece(position1)) next_position1 = 27;
                        else next_position1 = -10;
                    }
                }

                if (IsValidBlackMove(position1, next_position1))
                {
                    //restore the current position if it was -1.
                    position1 = (position1 == -1) ? 24 : position1;

                    Move mv1 = new Move();

                    if (OnePieceAlone(next_position1) && next_position1 != 27)
                    {
                        mv1.AddMove(next_position1, 25);
                    }

                    mv1.AddMove(position1, next_position1);

                    LastMove = new Move();

                    //make the move.
                    MakeLocalMove(mv1);
                    
                    
                    //we avoid duplicates.
                    if (!OneMoveList.contains(this))
                    {
                        //add the child to the list.
                        OneMoveList.add(new Board(this));
                    }

                    
                    //take back the moves, so that the result won't be permanent.
                    UndoMove(LastMove);
                    

                    for (int j = 0; j < 28; j++)
                    {

                        LastMove = new Move();

                        //make the move.
                        MakeLocalMove(mv1);

                        if (j == 25 || j == 26)
                        {
                            UndoMove(mv1);
                            continue;
                        }

                        int position2 = j;

                        //24=black captured pieces stack. -1=the position there the piece should start from.
                        if (position2 == 24) position2 = -1;

                        int next_position2 = position2 + dice1;

                        if (next_position2 >= 24)
                        {
                            if (next_position2 == 24) next_position2 = 27;

                            else
                            {
                                if (CanTakeBlackPiece(position2)) next_position2 = 27;
                                else next_position2 = -10;
                            }
                        }

                        if (IsValidBlackMove(position2, next_position2))
                        {
                            //restore the current position if it was -1.
                            position2 = (position2 == -1) ? 24 : position2;

                            Move mv2 = new Move();

                            if (OnePieceAlone(next_position2) && next_position2 != 27)
                            {
                                mv2.AddMove(next_position2, 25);
                            }

                            mv2.AddMove(position2, next_position2);

                            //make the move.
                            MakeLocalMove(mv2);

                            //we avoid duplicates.
                            if (!myList.contains(this))
                            {
                                //add the child to the list.
                                myList.add(new Board(this));
                            }
                            
                            //take back the moves, so that the result won't be permanent.
                            UndoMove(LastMove);

                        }

                        else
                        {
                            UndoMove(mv1);
                        }

                    }

                }

            }//for2 end

            

            if (myList.size() == 0) return OneMoveList;
            else return myList;

        }//if(dice1!=dice2)


        //if(dice1==dice2) : doubles!
        else
        {
            //this list will contain the cases that only one of the two dices can be played.
            ArrayList<Board> OneMoveList = new ArrayList<Board>();

            //this list will contain the cases that only one of the two dices can be played.
            ArrayList<Board> TwoMovesList = new ArrayList<Board>();

            //this list will contain the cases that only one of the two dices can be played.
            ArrayList<Board> ThreeMovesList = new ArrayList<Board>();

            for (int i = 0; i < 28; i++)
            {
                if (i == 25 || i == 26) continue;

                int position1 = i;

                //24=black captured pieces stack. -1=the position there the piece should start from.
                if (position1 == 24) position1 = -1;

                int next_position1 = position1 + dice1;

                if (next_position1 >= 24)
                {
                    if (next_position1 == 24) next_position1 = 27;

                    else
                    {
                        if (CanTakeBlackPiece(position1)) next_position1 = 27;
                        else next_position1 = -10;
                    }
                }

                if (IsValidBlackMove(position1, next_position1))
                {
                    //restore the current position if it was -1.
                    position1 = (position1 == -1) ? 24 : position1;

                    Move mv1 = new Move();

                    if (OnePieceAlone(next_position1) && next_position1 != 27)
                    {
                        mv1.AddMove(next_position1, 25);
                    }

                    mv1.AddMove(position1, next_position1);

                    LastMove = new Move();

                    //make the move.
                    MakeLocalMove(mv1);

                    
                    //we avoid duplicates.
                    if (!OneMoveList.contains(this))
                    {
                        //add the child to the list.
                        OneMoveList.add(new Board(this));
                    }
                    
                    //take back the moves, so that the result won't be permanent.
                    UndoMove(LastMove);


                    for (int j = 0; j < 28; j++)
                    {
                        LastMove = new Move();

                        //make the move.
                        MakeLocalMove(mv1);

                        if (j == 25 || j == 26)
                        {
                            UndoMove(mv1);
                            continue;
                        }

                        int position2 = j;

                        //24=black captured pieces stack. -1=the position there the piece should start from.
                        if (position2 == 24) position2 = -1;

                        int next_position2 = position2 + dice1;

                        if (next_position2 >= 24)
                        {
                            if (next_position2 == 24) next_position2 = 27;

                            else
                            {
                                if (CanTakeBlackPiece(position2)) next_position2 = 27;
                                else next_position2 = -10;
                            }
                        }

                        if (IsValidBlackMove(position2, next_position2))
                        {
                            //restore the current position if it was -1.
                            position2 = (position2 == -1) ? 24 : position2;

                            Move mv2 = new Move();

                            if (OnePieceAlone(next_position2) && next_position2 != 27)
                            {
                                mv2.AddMove(next_position2, 25);
                            }

                            mv2.AddMove(position2, next_position2);

                            //make the move.
                            MakeLocalMove(mv2);


                            //we avoid duplicates.
                            if (!TwoMovesList.contains(this))
                            {
                                //add the child to the list.
                                TwoMovesList.add(new Board(this));
                            }
                            
                            //take back the moves, so that the result won't be permanent.
                            UndoMove(LastMove);


                            for (int x = 0; x < 28; x++)
                            {
                                LastMove = new Move();
                                MakeLocalMove(mv1);
                                MakeLocalMove(mv2);

                                if (x == 25 || x == 26)
                                {
                                    UndoMove(mv2);
                                    UndoMove(mv1);
                                    continue;
                                }

                                int position3 = x;

                                //24=black captured pieces stack. -1=the position there the piece should start from.
                                if (position3 == 24) position3 = -1;

                                int next_position3 = position3 + dice1;

                                if (next_position3 >= 24)
                                {
                                    if (next_position3 == 24) next_position3 = 27;

                                    else
                                    {
                                        if (CanTakeBlackPiece(position3)) next_position3 = 27;
                                        else next_position3 = -10;
                                    }
                                }

                                if(IsValidBlackMove(position3,next_position3))
                                {
                                    //restore the current position if it was -1.
                                    position3 = (position3 == -1) ? 24 : position3;

                                    Move mv3 = new Move();

                                    if (OnePieceAlone(next_position3) && next_position3 != 27)
                                    {
                                        mv3.AddMove(next_position3, 25);
                                    }

                                    mv3.AddMove(position3, next_position3);

                                    //make the move.
                                    MakeLocalMove(mv3);

                                  
                                    //we avoid duplicates.
                                    if (!ThreeMovesList.contains(this))
                                    {
                                        //add the child to the list.
                                        ThreeMovesList.add(new Board(this));
                                    }
                                    
                                    //take back the moves, so that the result won't be permanent.
                                    UndoMove(LastMove);


                                    for (int y = 0; y < 28; y++)
                                    {
                                        LastMove = new Move();
                                        MakeLocalMove(mv1);
                                        MakeLocalMove(mv2);
                                        MakeLocalMove(mv3);

                                        if (y == 25 || y == 26)
                                        {
                                            UndoMove(mv3);
                                            UndoMove(mv2);
                                            UndoMove(mv1);
                                            continue;
                                        }

                                        int position4 = y;

                                        //24=black captured pieces stack. -1=the position there the piece should start from.
                                        if (position4 == 24) position4 = -1;

                                        int next_position4 = position4 + dice1;

                                        if (next_position4 >= 24)
                                        {
                                            if (next_position4 == 24) next_position4 = 27;

                                            else
                                            {
                                                if (CanTakeBlackPiece(position4)) next_position4 = 27;
                                                else next_position4 = -10;
                                            }
                                        }

                                        if(IsValidBlackMove(position4,next_position4))
                                        {
                                            //restore the current position if it was -1.
                                            position4 = (position4 == -1) ? 24 : position4;

                                            Move mv4 = new Move();

                                            if (OnePieceAlone(next_position4) && next_position4 != 27)
                                            {
                                                mv4.AddMove(next_position4, 25);
                                            }

                                            mv4.AddMove(position4, next_position4);

                                            //make the move.
                                            MakeLocalMove(mv4);

                                            
                                            //we avoid duplicates.
                                            if (!myList.contains(this))
                                            {
                                                //add the child to the list.
                                                myList.add(new Board(this));
                                            }
                                            
                                            //take back the moves, so that the result won't be permanent.
                                            UndoMove(LastMove);

                                        }

                                        else
                                        {
                                            UndoMove(mv3);
                                            UndoMove(mv2);
                                            UndoMove(mv1);
                                        }
                                    }
                                }

                                else
                                {
                                    UndoMove(mv2);
                                    UndoMove(mv1);
                                }
                            }
                           
                        }

                        else
                        {
                            UndoMove(mv1);
                        }

                    }

                }

            }


            if (myList.size() == 0)
            {
                if (ThreeMovesList.size() == 0)
                {
                    if (TwoMovesList.size() == 0)
                    {
                        return OneMoveList;
                    }

                    else
                    {
                        return TwoMovesList;
                    }
                }

                else
                {
                    return ThreeMovesList;
                }
            }

            else
            {
                return myList;
            }
        }// else if(dice1==dice2)
       
    }//GetBlackChildren end. 
    
    
    
    
    //return all the suitable moves the player can do , if he/she is to play at this turn...
    private ArrayList<Board> GetWhiteChildren(int dice1, int dice2)
    {
       ArrayList<Board> myList = new ArrayList<Board>();


       if (dice1 != dice2)
       {
           //this list will contain the cases that only one of the two dices can be played.
    	   ArrayList<Board> OneMoveList = new ArrayList<Board>();

           //for1 :there we play the dice1 first and foreach available move we play the dice2
           for (int i = 27; i >= 0; i--)
           {
               if (i == 24 || i == 27) continue;

               int position1 = i;

               //25=white captured pieces stack. 24=the position there the piece should start from.
               if (position1 == 25) position1 = 24;

               int next_position1 = position1 - dice1;

               if (next_position1 < 0)
               {
                   if (next_position1 == -1) next_position1 = 26;

                   else
                   {
                       if (CanTakeWhitePiece(position1)) next_position1 = 26;
                       else next_position1 = -10;
                   }
               }

               if (IsValidWhiteMove(position1, next_position1))
               {
                   //restore the current position if it was -1.
                   position1 = (position1 == 24) ? 25 : position1;

                   Move mv1 = new Move();

                   if (OnePieceAlone(next_position1) && next_position1 != 26)
                   {
                       mv1.AddMove(next_position1, 24);
                   }

                   mv1.AddMove(position1, next_position1);

                   LastMove = new Move();

                   //make the move.
                   MakeLocalMove(mv1);

                  
                   //we avoid duplicates.
                   if (!OneMoveList.contains(this))
                   {
                       //add the child to the list.
                       OneMoveList.add(new Board(this));
                   }
                   
                   //take back the moves, so that the result won't be permanent.
                   UndoMove(LastMove);


                   for (int j = 27; j >= 0; j--)
                   {

                       LastMove = new Move();

                       //make the move.
                       MakeLocalMove(mv1);

                       if (j == 24 || j == 27)
                       {
                           UndoMove(mv1);
                           continue;
                       }

                       int position2 = j;

                       //24=black captured pieces stack. -1=the position there the piece should start from.
                       if (position2 == 25) position2 = 24;

                       int next_position2 = position2 - dice2;

                       if (next_position2 < 0)
                       {
                           if (next_position2 == -1) next_position2 = 26;

                           else
                           {
                               if (CanTakeWhitePiece(position2)) next_position2 = 26;
                               else next_position2 = -10;
                           }
                       }

                       if (IsValidWhiteMove(position2, next_position2))
                       {
                           //restore the current position if it was -1.
                           position2 = (position2 == 24) ? 25 : position2;

                           Move mv2 = new Move();

                           if (OnePieceAlone(next_position2) && next_position2 != 27)
                           {
                               mv2.AddMove(next_position2, 24);
                           }

                           mv2.AddMove(position2, next_position2);

                           //make the move.
                           MakeLocalMove(mv2);

                           if(!(myList.contains(this)))
                           {
                        	   //add the child to the list.
                        	   myList.add(new Board(this));
                           }
                           
                           //take back the moves, so that the result won't be permanent.
                           UndoMove(LastMove);

                       }

                       else
                       {
                           UndoMove(mv1);
                       }

                   }

               }
           }//for1 end

         //for2 :there we play the dice2 first and foreach available move we play the dice1
           for (int i = 27; i <= 0; i--)
           {
               if (i == 24 || i == 27) continue;

               int position1 = i;

               //25=white captured pieces stack. 24=the position there the piece should start from.
               if (position1 == 25) position1 = 24;

               int next_position1 = position1 - dice2;

               if (next_position1 < 0)
               {
                   if (next_position1 == -1) next_position1 = 26;

                   else
                   {
                       if (CanTakeWhitePiece(position1)) next_position1 = 26;
                       else next_position1 = -10;
                   }
               }

               if (IsValidWhiteMove(position1, next_position1))
               {
                   //restore the current position if it was -1.
                   position1 = (position1 == 24) ? 25 : position1;

                   Move mv1 = new Move();

                   if (OnePieceAlone(next_position1) && next_position1 != 26)
                   {
                       mv1.AddMove(next_position1, 24);
                   }

                   mv1.AddMove(position1, next_position1);

                   LastMove = new Move();

                   //make the move.
                   MakeLocalMove(mv1);

                  
                   //we avoid duplicates.
                   if (!OneMoveList.contains(this))
                   {
                       //add the child to the list.
                       OneMoveList.add(new Board(this));
                   }
                   
                   //take back the moves, so that the result won't be permanent.
                   UndoMove(LastMove);


                   for (int j = 27; j <= 0; j--)
                   {

                       LastMove = new Move();

                       //make the move.
                       MakeLocalMove(mv1);

                       if (j == 24 || j == 27)
                       {
                           UndoMove(mv1);
                           continue;
                       }

                       int position2 = j;

                       //24=black captured pieces stack. -1=the position there the piece should start from.
                       if (position2 == 25) position2 = 24;

                       int next_position2 = position2 - dice1;

                       if (next_position2 < 0)
                       {
                           if (next_position2 == -1) next_position2 = 26;

                           else
                           {
                               if (CanTakeWhitePiece(position2)) next_position2 = 26;
                               else next_position2 = -10;
                           }
                       }

                       if (IsValidWhiteMove(position2, next_position2))
                       {
                           //restore the current position if it was -1.
                           position2 = (position2 == 24) ? 25 : position2;

                           Move mv2 = new Move();

                           if (OnePieceAlone(next_position2) && next_position2 != 27)
                           {
                               mv2.AddMove(next_position2, 24);
                           }

                           mv2.AddMove(position2, next_position2);

                           //make the move.
                           MakeLocalMove(mv2);

                         
                           if(!(myList.contains(this)))
                           {
                        	   //add the child to the list.
                        	   myList.add(new Board(this));
                           }
                           
                           //take back the moves, so that the result won't be permanent.
                           UndoMove(LastMove);

                       }

                       else
                       {
                           UndoMove(mv1);
                       }

                   }

               }
               
           }//for2 end

           

           if (myList.size() == 0) return OneMoveList;
           else return myList;
           
       }//if(dice1!=dice2)


       //if(dice1==dice2) : doubles!
       else
       {
           //this list will contain the cases that only one of the two dices can be played.
           ArrayList<Board> OneMoveList = new ArrayList<Board>();

           //this list will contain the cases that only one of the two dices can be played.
           ArrayList<Board> TwoMovesList = new ArrayList<Board>();

           //this list will contain the cases that only one of the two dices can be played.
           ArrayList<Board> ThreeMovesList = new ArrayList<Board>();


           for (int i = 27; i >= 0; i--)
           {
               if (i == 24 || i == 27) continue;

               int position1 = i;

               //25=white captured pieces stack. 24=the position there the piece should start from.
               if (position1 == 25) position1 = 24;

               int next_position1 = position1 - dice1;

               if (next_position1 < 0)
               {
                   if (next_position1 == -1) next_position1 = 26;

                   else
                   {
                       if (CanTakeWhitePiece(position1)) next_position1 = 26;
                       else next_position1 = -10;
                   }
               }

               if (IsValidWhiteMove(position1, next_position1))
               {
                   //restore the current position if it was -1.
                   position1 = (position1 == 24) ? 25 : position1;

                   Move mv1 = new Move();

                   if (OnePieceAlone(next_position1) && next_position1 != 26)
                   {
                       mv1.AddMove(next_position1, 24);
                   }

                   mv1.AddMove(position1, next_position1);

                   LastMove = new Move();

                   //make the move.
                   MakeLocalMove(mv1);

                  
                   //we avoid duplicates.
                   if (!OneMoveList.contains(this))
                   {
                       //add the child to the list.
                       OneMoveList.add(new Board(this));
                   }
                   
                   //take back the moves, so that the result won't be permanent.
                   UndoMove(LastMove);


                   for (int j = 27; j >= 0; j--)
                   {

                       LastMove = new Move();

                       //make the move.
                       MakeLocalMove(mv1);

                       if (j == 24 || j == 27)
                       {
                           UndoMove(mv1);
                           continue;
                       }

                       int position2 = j;

                       //24=black captured pieces stack. -1=the position there the piece should start from.
                       if (position2 == 25) position2 = 24;

                       int next_position2 = position2 - dice2;

                       if (next_position2 < 0)
                       {
                           if (next_position2 == -1) next_position2 = 26;

                           else
                           {
                               if (CanTakeWhitePiece(position2)) next_position2 = 26;
                               else next_position2 = -10;
                           }
                       }

                       if (IsValidWhiteMove(position2, next_position2))
                       {
                           //restore the current position if it was -1.
                           position2 = (position2 == 24) ? 25 : position2;

                           Move mv2 = new Move();

                           if (OnePieceAlone(next_position2) && next_position2 != 27)
                           {
                               mv2.AddMove(next_position2, 24);
                           }

                           mv2.AddMove(position2, next_position2);

                           //make the move.
                           MakeLocalMove(mv2);

                          
                           //we avoid duplicates.
                           if (!TwoMovesList.contains(this))
                           {
                               //add the child to the list.
                               TwoMovesList.add(new Board(this));
                           }
                           
                           //take back the moves, so that the result won't be permanent.
                           UndoMove(LastMove);


                           for (int x = 0; x < 28; x++)
                           {
                               LastMove = new Move();
                               MakeLocalMove(mv1);
                               MakeLocalMove(mv2);


                               if (x == 24 || x == 27)
                               {
                                   UndoMove(mv2);
                                   UndoMove(mv1);
                                   continue;
                               }

                               int position3 = x;

                               //24=black captured pieces stack. -1=the position there the piece should start from.
                               if (position3 == 25) position3 = 24;

                               int next_position3 = position3 - dice1;

                               if (next_position3 < 0)
                               {
                                   if (next_position3 == -1) next_position3 = 26;

                                   else
                                   {
                                       if (CanTakeWhitePiece(position3)) next_position3 = 26;
                                       else next_position3 = -10;
                                   }
                               }

                               if (IsValidWhiteMove(position3, next_position3))
                               {
                                   //restore the current position if it was -1.
                                   position3 = (position3 == 24) ? 25 : position3;

                                   Move mv3 = new Move();

                                   if (OnePieceAlone(next_position3) && next_position3 != 27)
                                   {
                                       mv3.AddMove(next_position3, 24);
                                   }

                                   mv3.AddMove(position3, next_position3);

                                   //make the move.
                                   MakeLocalMove(mv3);

                                   
                                   //we avoid duplicates.
                                   if (!ThreeMovesList.contains(this))
                                   {
                                       //add the child to the list.
                                       ThreeMovesList.add(new Board(this));
                                   }
                                   
                                   //take back the moves, so that the result won't be permanent.
                                   UndoMove(LastMove);


                                   for (int y = 0; y < 28; y++)
                                   {
                                       LastMove = new Move();
                                       MakeLocalMove(mv1);
                                       MakeLocalMove(mv2);
                                       MakeLocalMove(mv3);

                                       if (y == 24 || y == 27)
                                       {
                                           UndoMove(mv3);
                                           UndoMove(mv2);
                                           UndoMove(mv1);
                                           continue;
                                       }

                                       int position4 = x;

                                       //24=black captured pieces stack. -1=the position there the piece should start from.
                                       if (position4 == 25) position4 = 24;

                                       int next_position4 = position4 - dice1;

                                       if (next_position4 < 0)
                                       {
                                           if (next_position4 == -1) next_position4 = 26;

                                           else
                                           {
                                               if (CanTakeWhitePiece(position4)) next_position4 = 26;
                                               else next_position4 = -10;
                                           }
                                       }

                                       if (IsValidWhiteMove(position4, next_position4))
                                       {
                                           //restore the current position if it was -1.
                                           position4 = (position4 == 24) ? 25 : position4;

                                           Move mv4 = new Move();

                                           if (OnePieceAlone(next_position4) && next_position4 != 27)
                                           {
                                               mv4.AddMove(next_position4, 24);
                                           }

                                           mv4.AddMove(position4, next_position4);

                                           //make the move.
                                           MakeLocalMove(mv4);

                                         
                                           //we avoid duplicates.
                                           if (!myList.contains(this))
                                           {
                                               //add the child to the list.
                                               myList.add(new Board(this));
                                           }
                                           
                                           //take back the moves, so that the result won't be permanent.
                                           UndoMove(LastMove);

                                       }

                                       else
                                       {
                                           UndoMove(mv3);
                                           UndoMove(mv2);
                                           UndoMove(mv1);
                                       }
                                   }

                               }

                               else
                               {
                                   UndoMove(mv2);
                                   UndoMove(mv1);
                               }

                           }
                       }

                       else
                       {
                           UndoMove(mv1);
                       }

                   }

               }
           }



           if (myList.size() == 0)
           {
               if (ThreeMovesList.size() == 0)
               {
                   if (TwoMovesList.size() == 0)
                   {
                       return OneMoveList;
                   }

                   else
                   {
                       return TwoMovesList;
                   }
               }

               else
               {
                   return ThreeMovesList;
               }
           }

           else
           {
               return myList;
           }

       }// else if(dice1==dice2)
      

   }//GetWhiteChildren end
   
   
  
     
    //returns all of the children if "Chance" is to play at this turn.
    public ArrayList<ChanceNode> GetChanceChildren()
    {
    	ArrayList<ChanceNode>  list = new ArrayList<ChanceNode>(); 
        
    	list.add(new ChanceNode(1, 1, (double)1 / (double)36));
        list.add(new ChanceNode(2, 2, (double)1 / (double)36));
        list.add(new ChanceNode(3, 3, (double)1 / (double)36));
        list.add(new ChanceNode(4, 4, (double)1 / (double)36));
        list.add(new ChanceNode(5, 5, (double)1 / (double)36));
        list.add(new ChanceNode(6, 6, (double)1 / (double)36));
        list.add(new ChanceNode(1, 2, (double)1 / (double)18));
        list.add(new ChanceNode(1, 3, (double)1 / (double)18));
        list.add(new ChanceNode(1, 4, (double)1 / (double)18));
        list.add(new ChanceNode(1, 5, (double)1 / (double)18));
        list.add(new ChanceNode(1, 6, (double)1 / (double)18));
        list.add(new ChanceNode(2, 3, (double)1 / (double)18));
        list.add(new ChanceNode(2, 4, (double)1 / (double)18));
        list.add(new ChanceNode(2, 5, (double)1 / (double)18));
        list.add(new ChanceNode(2, 6, (double)1 / (double)18));
        list.add(new ChanceNode(3, 4, (double)1 / (double)18));
        list.add(new ChanceNode(3, 5, (double)1 / (double)18));
        list.add(new ChanceNode(3, 6, (double)1 / (double)18));
        list.add(new ChanceNode(4, 5, (double)1 / (double)18));
        list.add(new ChanceNode(4, 6, (double)1 / (double)18));
        list.add(new ChanceNode(5, 6, (double)1 / (double)18));
        
        return list;
    }

 
    
}
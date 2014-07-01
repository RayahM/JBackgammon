import java.util.Stack;
import javax.swing.JTextField;

/*
 * Every instance of this class represents a tower of the board. There are 28 towers totally.
 */
public class Tower
{

	//i.e. 0-27.
    private int NumberOfTower;

    //the number of nodes on this tower.
    private int NumberOfNodes;

    //The colour of piece(s) on this tower.
    private String ColourOfNodes;

    //The x position of the tower.
    private int Alignment_x;

    //The y position of the base of the tower.
    private int Alignment_y;

    //This will be used if the number of pieces in the current tower are more than 5.
    //If they do then exactly 5 will be displayed and the text box will declare how many more pieces are on the tower.
    private JTextField textBox;

    //A stack that will store the Nodes-pieces than are on the tower.
    private Stack<Node> nodes;
    
    
    
    
    
    
    //constructor
    public Tower(int number , int x , int y , JTextField tb)
    {
        NumberOfNodes = 0;
        NumberOfTower = number;
        ColourOfNodes = "";
        Alignment_x = x;
        Alignment_y = y;
        nodes = new Stack<Node>();
        textBox = tb;
    }
    
    
    
    
    //Returns the NumberOfNodes on the current Tower
    public int getNumberOfNodes()
    {
        return NumberOfNodes;
    }
    
    
    
    
    public void setNumberOfNodes(int num)
    {
    	this.NumberOfNodes = num;
    }
    
    
    
    
    //Returns The Number-ID of the current Tower
    public int getNumberOfTower()
    {
        return NumberOfTower;
    }
    
    
    
    
    public void setNumberOfTower(int num)
    {
    	this.NumberOfTower = num;
    }
    
    
    
    
    //returns the colour of Node(s) on the current tower.
    public String getColourOfNodes()
    {
        return ColourOfNodes;
    }
    
    
    
    
    public void setColourOfNodes(String colour)
    {
    	this.ColourOfNodes = colour;
    }
    
    
    
    
    //Returns X-position of the current Tower
    public int get_X()
    {
        return Alignment_x;
    }
    
    
    
    
    public void set_X(int x)
    {
    	this.Alignment_x = x;
    }
    
    
    
    
    //Returns Y-position of the current Tower
    public int get_Y()
    {
        return Alignment_y;
    }
    
    
    
    
    public void set_Y(int y)
    {
    	this.Alignment_y = y;
    }
    
    
    
    
    public JTextField getTextField()
    {
    	return this.textBox;
    }
    
    
    
    
    public void setTextField(JTextField field)
    {
    	this.textBox = field;
    }
    
    
    
    
    public Stack<Node> getNodes()
    {
    	return this.nodes;
    }
    
    
    
    
    public void setNodes(Stack<Node> nds)
    {
    	for(int i = 0 ; i<nds.size(); i++)
    	{
    		nodes.add(nds.get(i));
    	}
    }
    
    
    
    
    //adds a node to the current tower and displays the result to the frame.
    public void addNodeWithMove(Node obj)
    {
        //if there are no pieces in the current tower...
        if (NumberOfNodes == 0)
        {
            obj.getButton().setEnabled(false);
            obj.getButton().setVisible(true);
            obj.setPosition(NumberOfTower);

            ColourOfNodes = obj.getColour();

            //this if-else sets the position of the piece in the board.
            if ((NumberOfTower >= 1) && (NumberOfTower <= 12))
            {
                obj.getButton().setBounds(Alignment_x, (Alignment_y - (NumberOfNodes * 32)), 35, 32);
            }

            else if ((NumberOfTower >= 13) && (NumberOfTower <= 24))
            {
                obj.getButton().setBounds(Alignment_x, (Alignment_y + (NumberOfNodes * 35)), 35, 32);
            }

            else if(NumberOfTower==25 || NumberOfTower==26)
            {
                obj.getButton().setBounds(Alignment_x, Alignment_y, 35, 32);
                obj.getButton().setEnabled(false);
                textBox.setVisible(true);
                textBox.setText(String.valueOf(NumberOfNodes+1));
            }

            else if (NumberOfTower == 27 || NumberOfTower == 28)
            {
                obj.getButton().setBounds(Alignment_x, Alignment_y, 35, 32);
                obj.getButton().setEnabled(false);
                obj.getButton().setVisible(false);
            }
            
            nodes.add(obj);
            NumberOfNodes++;
        }

        //if there 1 to 4 pieces int current tower...
        else if ((NumberOfNodes > 0) && (NumberOfNodes < 5))
        {
            nodes.get(NumberOfNodes - 1).getButton().setEnabled(false);
            obj.getButton().setEnabled(false);
            obj.getButton().setVisible(true);
            obj.setPosition(NumberOfTower);

            //this if-else sets the position of the piece in the board.
            if ((NumberOfTower >= 1) && (NumberOfTower <= 12))
            {
                obj.getButton().setBounds(Alignment_x, (Alignment_y - (NumberOfNodes * 35)), 35, 32);
            }

            else if ((NumberOfTower >= 13) && (NumberOfTower <= 24))
            {
                obj.getButton().setBounds(Alignment_x, (Alignment_y + (NumberOfNodes * 35)), 35, 32);
            }

            else if (NumberOfTower == 25 || NumberOfTower == 26)
            {
                obj.getButton().setBounds(Alignment_x, Alignment_y, 35, 32);
                obj.getButton().setEnabled(false);
                textBox.setVisible(true);
                textBox.setText(String.valueOf((NumberOfNodes+1)));
            }

            else if (NumberOfTower == 27 || NumberOfTower == 28)
            {
                obj.getButton().setBounds(Alignment_x, Alignment_y, 35, 32);
                obj.getButton().setEnabled(false);
                obj.getButton().setVisible(false);
            }

            nodes.add(obj);
            NumberOfNodes++;
        }

        //if there are 5 or more pieces in the tower...
        else
        {
            nodes.get(NumberOfNodes - 1).getButton().setVisible(false);
            obj.getButton().setEnabled(false);
            obj.getButton().setVisible(true);
            obj.setPosition(NumberOfTower);

            //this if-else sets the position of the piece in the board.
            if ((NumberOfTower >= 1) && (NumberOfTower <= 12))
            {
                obj.getButton().setBounds(Alignment_x, (Alignment_y - (4 * 35)), 35, 32);
                textBox.setVisible(true);
                textBox.setText(String.valueOf((NumberOfNodes+1)));
            }

            else if ((NumberOfTower >= 13) && (NumberOfTower <= 24))
            {
                obj.getButton().setBounds(Alignment_x, (Alignment_y + (4 * 35)), 35, 32);
                textBox.setVisible(true);
                textBox.setText(String.valueOf((NumberOfNodes+1)));
            }

            else if (NumberOfTower == 25 || NumberOfTower == 26)
            {
                obj.getButton().setBounds(Alignment_x, Alignment_y, 35, 32);
                obj.getButton().setEnabled(false);
                textBox.setVisible(true);
                textBox.setText(String.valueOf((NumberOfNodes+1)));
            }

            else if (NumberOfTower == 27 || NumberOfTower == 28)
            {
                obj.getButton().setBounds(Alignment_x, Alignment_y, 35, 32);
                obj.getButton().setEnabled(false);
                obj.getButton().setVisible(false);
                textBox.setVisible(true);
                textBox.setText(String.valueOf((NumberOfNodes+1)));
            }

            nodes.add(obj);
            NumberOfNodes++;
        }

    }//addNode method end.
    
    
    
    
    //adds a node to the current tower but without displaying the results to the frame.
    public void addNodeWithoutMove(Node obj)
    {
            obj.getButton().setEnabled(false);
            obj.getButton().setVisible(true);
            obj.setPosition(NumberOfTower);
            ColourOfNodes = obj.getColour();    
            nodes.add(obj);
            NumberOfNodes++;
    }
    
    
    
    
    //removes a Node object from the current tower and displays the results to the frame.
    public Node removeNodeWithMove()
    {
        //If there are no Nodes on this Tower return null.
        if (NumberOfNodes == 0) return null;

        else
        {
            NumberOfNodes--;
            Node obj = nodes.get(NumberOfNodes);
            obj.getButton().setVisible(false);
            obj.setPosition(-1);
            nodes.remove(NumberOfNodes);

            
            if(NumberOfNodes == 0 ) this.ColourOfNodes = "";
            
            //If we remove a piece from the captured stack...
            if (NumberOfTower == 25 || NumberOfTower == 26)
            {
                if (NumberOfNodes == 0)
                {
                    textBox.setText("");
                    textBox.setVisible(false);
                }

                else
                {
                	nodes.get(NumberOfNodes - 1).getButton().setVisible(true);
                    nodes.get(NumberOfNodes - 1).getButton().setEnabled(true);
                    textBox.setText(String.valueOf((NumberOfNodes)));
                }
            }

            else if (NumberOfTower == 27 || NumberOfTower == 28)
            {
                //do nothing.
            }

            //else if we remove a piece from any other tower...
            else
            {

                if ((NumberOfNodes >= 1) && (NumberOfNodes <= 5))
                {
                    if (NumberOfNodes > 0)
                    {
                    	nodes.get(NumberOfNodes - 1).getButton().setVisible(true);
                        nodes.get(NumberOfNodes - 1).getButton().setEnabled(false);
                    }

                    textBox.setText("");
                    textBox.setVisible(false);
                }

                else if (NumberOfNodes >= 6)
                {
                	nodes.get(NumberOfNodes - 1).getButton().setVisible(true);
                    nodes.get(NumberOfNodes - 1).getButton().setEnabled(false);
                    textBox.setText(String.valueOf((NumberOfNodes)));
                    textBox.setVisible(true);
                }
            }

            return obj;
        }

    }//removeNode method end.
    
    
    
    
    //removes a Node object from the current tower but does not displays the results to the frame.
    public Node removeNodeWithoutMove()
    {
    	//If there are no Nodes on this Tower return null.
        if (NumberOfNodes == 0) return null;

        else
        {
            NumberOfNodes--;
            if(NumberOfNodes == 0 ) this.ColourOfNodes = "";
            Node obj = nodes.get(NumberOfNodes);
            obj.setPosition(-1);
            nodes.remove(NumberOfNodes);
            return obj;
        }
    }
    
    
    
    
    //Checks if the specified button-piece is in the list.
    public boolean ContainsButton(RoundButton rb)
    {
        for (int i = 0; i < NumberOfNodes; i++)
        {
            if (nodes.get(i).getButton() == rb) return true;
        }
    
        return false;
    
    }
    
    
    
    
    //Disables the top white piece of the current Tower if exists.
    public void DisableFirstWhiteNodes()
    {
    	 if ((NumberOfNodes > 0) && (ColourOfNodes.equals("white")))
    	 {
    		 nodes.peek().getButton().setEnabled(false);
    	 }
    }
    
    
    
    
    //Enables the top white piece of the current Tower if exists.
    public void EnableFirstWhiteNodes()
    {
    	 if ((NumberOfNodes > 0) && (ColourOfNodes.equals("white")))
    	 {
    		 nodes.peek().getButton().setEnabled(true);
    	 }    	 
    }
    
    
    
    
    @Override
    public int hashCode()
    {
        int count = 0;

        count = count + NumberOfTower + NumberOfNodes + ColourOfNodes.hashCode() + nodes.hashCode();

        return count;
    }
	
}
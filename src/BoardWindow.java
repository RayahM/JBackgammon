import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.StringTokenizer;



public class BoardWindow extends JFrame
{
	
	//----------------------------------------WINDOW WIDGETS----------------------------------------//
	
	//Menu bars and items.
	private MenuBar menuBar;
	private Menu menu1;
	private MenuItem item11;
	private MenuItem item12;
	
	//an image that will display the board.
	private Icon boardimg;
	private JLabel boardLabel;
	
	//Pieces of the player.
	private RoundButton player_button_1;
	private RoundButton player_button_2;
	private RoundButton player_button_3;
	private RoundButton player_button_4;
	private RoundButton player_button_5;
	private RoundButton player_button_6;
	private RoundButton player_button_7;
	private RoundButton player_button_8;
	private RoundButton player_button_9;
	private RoundButton player_button_10;
	private RoundButton player_button_11;
	private RoundButton player_button_12;
	private RoundButton player_button_13;
	private RoundButton player_button_14;
	private RoundButton player_button_15;
	
	//Pieces of the computer.
	private RoundButton computer_button_1;
	private RoundButton computer_button_2;
	private RoundButton computer_button_3;
	private RoundButton computer_button_4;
	private RoundButton computer_button_5;
	private RoundButton computer_button_6;
	private RoundButton computer_button_7;
	private RoundButton computer_button_8;
	private RoundButton computer_button_9;
	private RoundButton computer_button_10;
	private RoundButton computer_button_11;
	private RoundButton computer_button_12;
	private RoundButton computer_button_13;
	private RoundButton computer_button_14;
	private RoundButton computer_button_15;
	
	//Text fields that will display the numbers of nodes on a tower if it is >=6 nodes.
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private JTextField text4;
	private JTextField text5;
	private JTextField text6;
	private JTextField text7;
	private JTextField text8;
	private JTextField text9;
	private JTextField text10;
	private JTextField text11;
	private JTextField text12;
	private JTextField text13;
	private JTextField text14;
	private JTextField text15;
	private JTextField text16;
	private JTextField text17;
	private JTextField text18;
	private JTextField text19;
	private JTextField text20;
	private JTextField text21;
	private JTextField text22;
	private JTextField text23;
	private JTextField text24;
	private JTextField text25;
	private JTextField text26;
	private JTextField text27;
	private JTextField text28;
	
	//Dices.
	private JLabel label_dice_1;
	private ImageIcon icon_dice_1;
	private JLabel label_dice_2;
	private ImageIcon icon_dice_2;
	private JLabel initial_dice_player_label_1;
	private ImageIcon initial_dice_player_icon_1;
	private JLabel initial_dice_player_label_2;
	private ImageIcon initial_dice_player_icon_2;
	
	//Labels that will be used as the towers of the board for the "click".
	private JLabel label_tower_1;
	private JLabel label_tower_2;
	private JLabel label_tower_3;
	private JLabel label_tower_4;
	private JLabel label_tower_5;
	private JLabel label_tower_6;
	private JLabel label_tower_7;
	private JLabel label_tower_8;
	private JLabel label_tower_9;
	private JLabel label_tower_10;
	private JLabel label_tower_11;
	private JLabel label_tower_12;
	private JLabel label_tower_13;
	private JLabel label_tower_14;
	private JLabel label_tower_15;
	private JLabel label_tower_16;
	private JLabel label_tower_17;
	private JLabel label_tower_18;
	private JLabel label_tower_19;
	private JLabel label_tower_20;
	private JLabel label_tower_21;
	private JLabel label_tower_22;
	private JLabel label_tower_23;
	private JLabel label_tower_24;
	private JLabel label_tower_25;
	
	//continue , undo and roll dices buttons.
	private JButton cont;
	private JButton undo;
	private JButton roll;
	private JButton initial_roll_white;
	private JButton initial_roll_black;
	
	//A text area that will display the dialogs of the game.
	private JTextArea Dialogs;
	
	
	
	
	
	
	//------------------------------------------ VARIABLES -----------------------------------------//
	
	//a variable that shows the version of the window.
	private static final long serialVersionUID = 1L;

	//the colour of player's pieces.
	private Color player_colour;
	
	//the colour of computer's pieces.
	private Color computer_colour;
	
	//the depth-search of the minimax algorithm.
	private int minimax_depth;
	
	//This array keeps objects of class Tower , which will represent the states of the game.
    private Tower[] towers;

    //The board of the game.
    private Board board;

    //The Computer-player of this game.
    private MiniMax minimax;

    //All the Node objects that are "white" will be stored there.
    private Node[] whiteNodes;

    //All the Node objects that are "black" will be stored there.
    private Node[] blackNodes;

    //this stack will be used for the UNDO option. We will use it to keep the previous state.
    private Stack<Integer> myStack;
    
    //An object of class Random that we will use for the results of the dices.
    private Random r;

    //This variable is used to help us use the function WhoPlaysFirst() correctly;
    private int helper;

    //The result of the Dice1 and Dice2.
    private int dice1, dice2;

    //These two variables are used to help us know which of the two dices the player uses any time.
    private boolean Dice1_played, Dice2_played;

    //This variable is used when the result of a throw is doubles!
    private int NumberOfTimesPlayed;

    //Represents the number of the tower where the "clicked" piece lies...
    private int selected_piece;

    //The number of black pieces that come out of the game. If there are 15 black pieces out , then computer wins! 
    private int black_nodes_out;

    //The number of white pieces that come out of the game. If there are 15 white pieces out , then player wins!
    private int white_nodes_out;

    //Determines who plays in this turn. true=player , false=computer.
    private boolean player;
	
    //An objects that will handle the events.
    private EventHandler handler = new EventHandler();
	
	
	
	
    
    
	//----------------------------------------FUNCTIONS----------------------------------------//
	
    
	//constructor.
	public BoardWindow()
	{
		super("BACKGAMMON");
		
		this.setWindowPreferences();
		this.AddBoard();
		this.AddMenus();
		this.InitializeWhiteButtons();
		this.PlaceWhiteButtons();
		this.InitializeBlackButtons();
		this.PlaceBlackButtons();
		this.AddTextFields();
		this.AddDices();
		this.AddTowerLabels();
		this.AddButtons();
		this.AddDialog();
				
		//this.InitializeComponent();
        r = new Random();
        myStack = new Stack<Integer>();
	}
	
	
	
	
	//Loads the user's preferences from the preferences.txt file.
	private void LoadPreferences()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(new File("preferences.txt")));
			String s1 = br.readLine();
			String s2 = br.readLine();
			String s3 = br.readLine();
			
			StringTokenizer tok = new StringTokenizer(s1,":");
			s1 = tok.nextToken();
			s1 = tok.nextToken().trim();
			
			tok = new StringTokenizer(s2,":");
			s2 = tok.nextToken();
			s2 = tok.nextToken().trim();
			
			tok = new StringTokenizer(s3,":");
			s3 = tok.nextToken();
			s3 = tok.nextToken().trim();
			
			this.minimax_depth = Integer.parseInt(s3);
			this.setColours(s1, s2);
			
			br.close();
		}
		
		catch(Exception e)
		{
			System.err.println("ERROR : "+e.getMessage());
		}
		
	}
	
	
	
	
	//sets the prefered colour of pieces.
	private void setColours(String s1 , String s2)
	{
		if(s1.equals("WHITE")) 		    player_colour = Color.WHITE;
		else if(s1.equals("BLACK"))     player_colour = Color.BLACK;
		else if(s1.equals("RED"))       player_colour = Color.RED;
		else if(s1.equals("GREEN"))     player_colour = Color.GREEN;
		else if(s1.equals("BLUE"))      player_colour = Color.BLUE;
		else if(s1.equals("CYAN"))      player_colour = Color.CYAN;
		else if(s1.equals("DARK GRAY")) player_colour = Color.DARK_GRAY;
		else if(s1.equals("ORANGE"))    player_colour = Color.ORANGE;
		else if(s1.equals("PINK"))      player_colour = Color.PINK;
		else if(s1.equals("MAGENTA"))   player_colour = Color.MAGENTA;
		
		if(s2.equals("WHITE")) 		    computer_colour = Color.WHITE;
		else if(s2.equals("BLACK"))     computer_colour = Color.BLACK;
		else if(s2.equals("RED"))       computer_colour = Color.RED;
		else if(s2.equals("GREEN"))     computer_colour = Color.GREEN;
		else if(s2.equals("BLUE"))      computer_colour = Color.BLUE;
		else if(s2.equals("CYAN"))      computer_colour = Color.CYAN;
		else if(s2.equals("DARK GRAY")) computer_colour = Color.DARK_GRAY;
		else if(s2.equals("ORANGE"))    computer_colour = Color.ORANGE;
		else if(s2.equals("PINK"))      computer_colour = Color.PINK;
		else if(s2.equals("MAGENTA"))   computer_colour = Color.MAGENTA;
	}
	
	
	
	
	//Sets the window preferences.
	private void setWindowPreferences()
	{
		//configure window preferences.		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(727,750);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	
	
	
	//Adds the board to the window.
	private void AddBoard()
	{
		//adding the board image to the window.
		boardimg = new ImageIcon("Images/board.png");
		boardLabel = new JLabel(boardimg);
		boardLabel.setBounds(0,0,720,641);
		boardLabel.setVisible(true);
		add(boardLabel);
	}
	
	
	
	
	//Adds the menu bars and items to the window
	private void AddMenus()
	{
		//adding the menu bar.
		menuBar = new MenuBar();
		menu1 = new Menu("File");
		item11 = new MenuItem("New Game");
		item12 = new MenuItem("Preferences");
		menu1.add(item11);
		menu1.add(item12);
		item11.addActionListener(handler);
		item12.addActionListener(handler);
		menuBar.add(menu1);
		this.setMenuBar(menuBar);
	}
	
	
	
	
	//initializes the player's pieces.
	private void InitializeWhiteButtons()
	{
		player_button_1 = new RoundButton("");
		player_button_1.setVisible(false);
		player_button_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		player_button_1.setBackground(Color.WHITE);
		player_button_1.addActionListener(handler);
		boardLabel.add(player_button_1);
		
		player_button_2 = new RoundButton("");
		player_button_2.setVisible(false);
		player_button_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		player_button_2.setBackground(Color.WHITE);
		player_button_2.addActionListener(handler);
		boardLabel.add(player_button_2);
		
		player_button_3 = new RoundButton("");
		player_button_3.setVisible(false);
		player_button_3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		player_button_3.setBackground(Color.WHITE);
		player_button_3.addActionListener(handler);
		boardLabel.add(player_button_3);
		
		player_button_4 = new RoundButton("");
		player_button_4.setVisible(false);
		player_button_4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		player_button_4.setBackground(Color.WHITE);
		player_button_4.addActionListener(handler);
		boardLabel.add(player_button_4);
		
		player_button_5 = new RoundButton("");
		player_button_5.setVisible(false);
		player_button_5.setCursor(new Cursor(Cursor.HAND_CURSOR));
		player_button_5.setBackground(Color.WHITE);
		player_button_5.addActionListener(handler);
		boardLabel.add(player_button_5);
		
		player_button_6 = new RoundButton("");
		player_button_6.setVisible(false);
		player_button_6.setCursor(new Cursor(Cursor.HAND_CURSOR));
		player_button_6.setBackground(Color.WHITE);
		player_button_6.addActionListener(handler);
		boardLabel.add(player_button_6);
		
		player_button_7 = new RoundButton("");
		player_button_7.setVisible(false);
		player_button_7.setCursor(new Cursor(Cursor.HAND_CURSOR));
		player_button_7.setBackground(Color.WHITE);
		player_button_7.addActionListener(handler);
		boardLabel.add(player_button_7);
		
		player_button_8 = new RoundButton("");
		player_button_8.setVisible(false);
		player_button_8.setCursor(new Cursor(Cursor.HAND_CURSOR));
		player_button_8.setBackground(Color.WHITE);
		player_button_8.addActionListener(handler);
		boardLabel.add(player_button_8);
		
		player_button_9 = new RoundButton("");
		player_button_9.setVisible(false);
		player_button_9.setCursor(new Cursor(Cursor.HAND_CURSOR));
		player_button_9.setBackground(Color.WHITE);
		player_button_9.addActionListener(handler);
		boardLabel.add(player_button_9);
		
		player_button_10 = new RoundButton("");
		player_button_10.setVisible(false);
		player_button_10.setCursor(new Cursor(Cursor.HAND_CURSOR));
		player_button_10.setBackground(Color.WHITE);
		player_button_10.addActionListener(handler);
		boardLabel.add(player_button_10);
		
		player_button_11 = new RoundButton("");
		player_button_11.setVisible(false);
		player_button_11.setCursor(new Cursor(Cursor.HAND_CURSOR));
		player_button_11.setBackground(Color.WHITE);
		player_button_11.addActionListener(handler);
		boardLabel.add(player_button_11);
		
		player_button_12 = new RoundButton("");
		player_button_12.setVisible(false);
		player_button_12.setCursor(new Cursor(Cursor.HAND_CURSOR));
		player_button_12.setBackground(Color.WHITE);
		player_button_12.addActionListener(handler);
		boardLabel.add(player_button_12);
		
		player_button_13 = new RoundButton("");
		player_button_13.setVisible(false);
		player_button_13.setCursor(new Cursor(Cursor.HAND_CURSOR));
		player_button_13.setBackground(Color.WHITE);
		player_button_13.addActionListener(handler);
		boardLabel.add(player_button_13);
		
		player_button_14 = new RoundButton("");
		player_button_14.setVisible(false);
		player_button_14.setCursor(new Cursor(Cursor.HAND_CURSOR));
		player_button_14.setBackground(Color.WHITE);
		player_button_14.addActionListener(handler);
		boardLabel.add(player_button_14);
		
		player_button_15 = new RoundButton("");
		player_button_15.setVisible(false);
		player_button_15.setCursor(new Cursor(Cursor.HAND_CURSOR));
		player_button_15.setBackground(Color.WHITE);
		player_button_15.addActionListener(handler);
		boardLabel.add(player_button_15);
	}
	
	
	
	
	//places the player's buttons on the specified locations.
	private void PlaceWhiteButtons()
	{
		player_button_1.setBounds(40,37,35,32);
		player_button_1.setEnabled(false);
		player_button_1.setBackground(player_colour);
		
		player_button_2.setBounds(40,72,35,32);
		player_button_2.setEnabled(false);
		player_button_2.setBackground(player_colour);
		
		player_button_3.setBounds(645,37,35,32);
		player_button_3.setEnabled(false);
		player_button_3.setBackground(player_colour);
		
		player_button_4.setBounds(645,72,35,32);
		player_button_4.setEnabled(false);
		player_button_4.setBackground(player_colour);
		
		player_button_5.setBounds(645,107,35,32);
		player_button_5.setEnabled(false);
		player_button_5.setBackground(player_colour);
		
		player_button_6.setBounds(645,142,35,32);
		player_button_6.setEnabled(false);
		player_button_6.setBackground(player_colour);
		
		player_button_7.setBounds(645,177,35,32);
		player_button_7.setEnabled(false);
		player_button_7.setBackground(player_colour);
		
		player_button_8.setBounds(447,575,35,32);
		player_button_8.setEnabled(false);
		player_button_8.setBackground(player_colour);
		
		player_button_9.setBounds(447,540,35,32);
		player_button_9.setEnabled(false);
		player_button_9.setBackground(player_colour);
		
		player_button_10.setBounds(447,505,35,32);
		player_button_10.setEnabled(false);
		player_button_10.setBackground(player_colour);
		
		player_button_11.setBounds(287,575,35,32);
		player_button_11.setEnabled(false);
		player_button_11.setBackground(player_colour);
		
		player_button_12.setBounds(287,540,35,32);
		player_button_12.setEnabled(false);
		player_button_12.setBackground(player_colour);
		
		player_button_13.setBounds(287,505,35,32);
		player_button_13.setEnabled(false);
		player_button_13.setBackground(player_colour);
		
		player_button_14.setBounds(287,470,35,32);
		player_button_14.setEnabled(false);
		player_button_14.setBackground(player_colour);
		
		player_button_15.setBounds(287,435,35,32);
		player_button_15.setEnabled(false);
		player_button_15.setBackground(player_colour);
	}
	
	
	
	
	//Shows the white buttons.
	private void ShowWhiteButtons()
	{
		this.player_button_1.setVisible(true);
		this.player_button_2.setVisible(true);
		this.player_button_3.setVisible(true);
		this.player_button_4.setVisible(true);
		this.player_button_5.setVisible(true);
		this.player_button_6.setVisible(true);
		this.player_button_7.setVisible(true);
		this.player_button_8.setVisible(true);
		this.player_button_9.setVisible(true);
		this.player_button_10.setVisible(true);
		this.player_button_11.setVisible(true);
		this.player_button_12.setVisible(true);
		this.player_button_13.setVisible(true);
		this.player_button_14.setVisible(true);
		this.player_button_15.setVisible(true);
	}
	
	
	
	
	//Changes the color of all player's buttons to their initial colour.
	private void PlayerButtonsToWhite()
	{
		this.player_button_1.setBackground(this.player_colour);
		this.player_button_2.setBackground(this.player_colour);
		this.player_button_3.setBackground(this.player_colour);
		this.player_button_4.setBackground(this.player_colour);
		this.player_button_5.setBackground(this.player_colour);
		this.player_button_6.setBackground(this.player_colour);
		this.player_button_7.setBackground(this.player_colour);
		this.player_button_8.setBackground(this.player_colour);
		this.player_button_9.setBackground(this.player_colour);
		this.player_button_10.setBackground(this.player_colour);
		this.player_button_11.setBackground(this.player_colour);
		this.player_button_12.setBackground(this.player_colour);
		this.player_button_13.setBackground(this.player_colour);
		this.player_button_14.setBackground(this.player_colour);
		this.player_button_15.setBackground(this.player_colour);
	}
	
	
	
	
	//initializes the computer's.
	private void InitializeBlackButtons()
	{
		computer_button_1 = new RoundButton("");
		computer_button_1.setVisible(false);
		computer_button_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		computer_button_1.setBackground(Color.BLACK);
		boardLabel.add(computer_button_1);
			
		computer_button_2 = new RoundButton("");
		computer_button_2.setVisible(false);
		computer_button_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		computer_button_2.setBackground(Color.BLACK);
		boardLabel.add(computer_button_2);
			
		computer_button_3 = new RoundButton("");
		computer_button_3.setVisible(false);
		computer_button_3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		computer_button_3.setBackground(Color.BLACK);
		boardLabel.add(computer_button_3);
		
		computer_button_4 = new RoundButton("");
		computer_button_4.setVisible(false);
		computer_button_4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		computer_button_4.setBackground(Color.BLACK);
		boardLabel.add(computer_button_4);
		
		computer_button_5 = new RoundButton("");
		computer_button_5.setVisible(false);
		computer_button_5.setCursor(new Cursor(Cursor.HAND_CURSOR));
		computer_button_5.setBackground(Color.BLACK);
		boardLabel.add(computer_button_5);
		
		computer_button_6 = new RoundButton("");
		computer_button_6.setVisible(false);
		computer_button_6.setCursor(new Cursor(Cursor.HAND_CURSOR));
		computer_button_6.setBackground(Color.BLACK);
		boardLabel.add(computer_button_6);
		
		computer_button_7 = new RoundButton("");
		computer_button_7.setVisible(false);
		computer_button_7.setCursor(new Cursor(Cursor.HAND_CURSOR));
		computer_button_7.setBackground(Color.BLACK);
		boardLabel.add(computer_button_7);
		
		computer_button_8 = new RoundButton("");
		computer_button_8.setVisible(false);
		computer_button_8.setCursor(new Cursor(Cursor.HAND_CURSOR));
		computer_button_8.setBackground(Color.BLACK);
		boardLabel.add(computer_button_8);
		
		computer_button_9 = new RoundButton("");
		computer_button_9.setVisible(false);
		computer_button_9.setCursor(new Cursor(Cursor.HAND_CURSOR));
		computer_button_9.setBackground(Color.BLACK);
		boardLabel.add(computer_button_9);
		
		computer_button_10 = new RoundButton("");
		computer_button_10.setVisible(false);
		computer_button_10.setCursor(new Cursor(Cursor.HAND_CURSOR));
		computer_button_10.setBackground(Color.BLACK);
		boardLabel.add(computer_button_10);
		
		computer_button_11 = new RoundButton("");
		computer_button_11.setVisible(false);
		computer_button_11.setCursor(new Cursor(Cursor.HAND_CURSOR));
		computer_button_11.setBackground(Color.BLACK);
		boardLabel.add(computer_button_11);
		
		computer_button_12 = new RoundButton("");
		computer_button_12.setVisible(false);
		computer_button_12.setCursor(new Cursor(Cursor.HAND_CURSOR));
		computer_button_12.setBackground(Color.BLACK);
		boardLabel.add(computer_button_12);
		
		computer_button_13 = new RoundButton("");
		computer_button_13.setVisible(false);
		computer_button_13.setCursor(new Cursor(Cursor.HAND_CURSOR));
		computer_button_13.setBackground(Color.BLACK);
		boardLabel.add(computer_button_13);
		
		computer_button_14 = new RoundButton("");
		computer_button_14.setVisible(false);
		computer_button_14.setCursor(new Cursor(Cursor.HAND_CURSOR));
		computer_button_14.setBackground(Color.BLACK);
		boardLabel.add(computer_button_14);
		
		computer_button_15 = new RoundButton("");
		computer_button_15.setVisible(false);
		computer_button_15.setCursor(new Cursor(Cursor.HAND_CURSOR));
		computer_button_15.setBackground(Color.BLACK);
		boardLabel.add(computer_button_15);
	}
	
	
	
	
	//places the computer's buttons on the specified locations.
	private void PlaceBlackButtons()
	{
		computer_button_1.setBounds(40,575,35,32);
		computer_button_1.setEnabled(false);
		computer_button_1.setBackground(computer_colour);
		
		computer_button_2.setBounds(40,540,35,32);
		computer_button_2.setEnabled(false);
		computer_button_2.setBackground(computer_colour);
		
		computer_button_3.setBounds(645,575,35,32);
		computer_button_3.setEnabled(false);
		computer_button_3.setBackground(computer_colour);
		
		computer_button_4.setBounds(645,540,35,32);
		computer_button_4.setEnabled(false);
		computer_button_4.setBackground(computer_colour);
		
		computer_button_5.setBounds(645,505,35,32);
		computer_button_5.setEnabled(false);
		computer_button_5.setBackground(computer_colour);
		
		computer_button_6.setBounds(645,470,35,32);
		computer_button_6.setEnabled(false);
		computer_button_6.setBackground(computer_colour);
		
		computer_button_7.setBounds(645,435,35,32);
		computer_button_7.setEnabled(false);
		computer_button_7.setBackground(computer_colour);
		
		computer_button_8.setBounds(447,37,35,32);
		computer_button_8.setEnabled(false);
		computer_button_8.setBackground(computer_colour);
		
		computer_button_9.setBounds(447,72,35,32);
		computer_button_9.setEnabled(false);
		computer_button_9.setBackground(computer_colour);
		
		computer_button_10.setBounds(447,107,35,32);
		computer_button_10.setEnabled(false);
		computer_button_10.setBackground(computer_colour);
		
		computer_button_11.setBounds(287,37,35,32);
		computer_button_11.setEnabled(false);
		computer_button_11.setBackground(computer_colour);
		
		computer_button_12.setBounds(287,72,35,32);
		computer_button_12.setEnabled(false);
		computer_button_12.setBackground(computer_colour);
		
		computer_button_13.setBounds(287,107,35,32);
		computer_button_13.setEnabled(false);
		computer_button_13.setBackground(computer_colour);
		
		computer_button_14.setBounds(287,142,35,32);
		computer_button_14.setEnabled(false);
		computer_button_14.setBackground(computer_colour);
		
		computer_button_15.setBounds(287,177,35,32);
		computer_button_15.setEnabled(false);
		computer_button_15.setBackground(computer_colour);
	}
	
	
	
	
	//Shows the black buttons.
	private void ShowBlackButtons()
	{
		this.computer_button_1.setVisible(true);
		this.computer_button_2.setVisible(true);
		this.computer_button_3.setVisible(true);
		this.computer_button_4.setVisible(true);
		this.computer_button_5.setVisible(true);
		this.computer_button_6.setVisible(true);
		this.computer_button_7.setVisible(true);
		this.computer_button_8.setVisible(true);
		this.computer_button_9.setVisible(true);
		this.computer_button_10.setVisible(true);
		this.computer_button_11.setVisible(true);
		this.computer_button_12.setVisible(true);
		this.computer_button_13.setVisible(true);
		this.computer_button_14.setVisible(true);
		this.computer_button_15.setVisible(true);
	}
	
	
	
	
	//places the text fields on the board.
	private void AddTextFields()
	{
		text1 = new JTextField();
		text1.setColumns(2);
		text1.setVisible(false);
		text1.setEditable(false);
		text1.setBounds(37,610,40,20);
		boardLabel.add(text1);
		
		text2 = new JTextField();
		text2.setColumns(2);
		text2.setVisible(false);
		text2.setEditable(false);
		text2.setBounds(87,610,40,20);
		boardLabel.add(text2);
		
		text3 = new JTextField();
		text3.setColumns(2);
		text3.setVisible(false);
		text3.setEditable(false);
		text3.setBounds(137,610,40,20);
		boardLabel.add(text3);
		
		text4 = new JTextField();
		text4.setColumns(2);
		text4.setVisible(false);
		text4.setEditable(false);
		text4.setBounds(185,610,40,20);
		boardLabel.add(text4);
		
		text5 = new JTextField();
		text5.setColumns(2);
		text5.setVisible(false);
		text5.setEditable(false);
		text5.setBounds(235,610,40,20);
		boardLabel.add(text5);
		
		text6 = new JTextField();
		text6.setColumns(2);
		text6.setVisible(false);
		text6.setEditable(false);
		text6.setBounds(285,610,40,20);
		boardLabel.add(text6);
		
		text7 = new JTextField();
		text7.setColumns(2);
		text7.setVisible(false);
		text7.setEditable(false);
		text7.setBounds(395,610,40,20);
		boardLabel.add(text7);
		
		text8 = new JTextField();
		text8.setColumns(2);
		text8.setVisible(false);
		text8.setEditable(false);
		text8.setBounds(445,610,40,20);
		boardLabel.add(text8);
		
		text9 = new JTextField();
		text9.setColumns(2);
		text9.setVisible(false);
		text9.setEditable(false);
		text9.setBounds(495,610,40,20);
		boardLabel.add(text9);
		
		text10 = new JTextField();
		text10.setColumns(2);
		text10.setVisible(false);
		text10.setEditable(false);
		text10.setBounds(543,610,40,20);
		boardLabel.add(text10);
		
		text11 = new JTextField();
		text11.setColumns(2);
		text11.setVisible(false);
		text11.setEditable(false);
		text11.setBounds(593,610,40,20);
		boardLabel.add(text11);
		
		text12 = new JTextField();
		text12.setColumns(2);
		text12.setVisible(false);
		text12.setEditable(false);
		text12.setBounds(643,610,40,20);
		boardLabel.add(text12);
		
		text13 = new JTextField();
		text13.setColumns(2);
		text13.setVisible(false);
		text13.setEditable(false);
		text13.setBounds(643,10,40,20);
		boardLabel.add(text13);
		
		text14 = new JTextField();
		text14.setColumns(2);
		text14.setVisible(false);
		text14.setEditable(false);
		text14.setBounds(593,10,40,20);
		boardLabel.add(text14);
		
		text15 = new JTextField();
		text15.setColumns(2);
		text15.setVisible(false);
		text15.setEditable(false);
		text15.setBounds(543,10,40,20);
		boardLabel.add(text15);
		
		text16 = new JTextField();
		text16.setColumns(2);
		text16.setVisible(false);
		text16.setEditable(false);
		text16.setBounds(493,10,40,20);
		boardLabel.add(text16);
		
		text17 = new JTextField();
		text17.setColumns(2);
		text17.setVisible(false);
		text17.setEditable(false);
		text17.setBounds(445,10,40,20);
		boardLabel.add(text17);
		
		text18 = new JTextField();
		text18.setColumns(2);
		text18.setVisible(false);
		text18.setEditable(false);
		text18.setBounds(395,10,40,20);
		boardLabel.add(text18);
		
		text19 = new JTextField();
		text19.setColumns(2);
		text19.setVisible(false);
		text19.setEditable(false);
		text19.setBounds(285,10,40,20);
		boardLabel.add(text19);
		
		text20 = new JTextField();
		text20.setColumns(2);
		text20.setVisible(false);
		text20.setEditable(false);
		text20.setBounds(235,10,40,20);
		boardLabel.add(text20);
		
		text21 = new JTextField();
		text21.setColumns(2);
		text21.setVisible(false);
		text21.setEditable(false);
		text21.setBounds(185,10,40,20);
		boardLabel.add(text21);
		
		text22 = new JTextField();
		text22.setColumns(2);
		text22.setVisible(false);
		text22.setEditable(false);
		text22.setBounds(137,10,40,20);
		boardLabel.add(text22);
		
		text23 = new JTextField();
		text23.setColumns(2);
		text23.setVisible(false);
		text23.setEditable(false);
		text23.setBounds(87,10,40,20);
		boardLabel.add(text23);
		
		text24 = new JTextField();
		text24.setColumns(2);
		text24.setVisible(false);
		text24.setEditable(false);
		text24.setBounds(37,10,40,20);
		boardLabel.add(text24);
		
		text25 = new JTextField();
		text25.setColumns(2);
		text25.setVisible(false);
		text25.setEditable(false);
		text25.setBounds(340,550,40,20);
		boardLabel.add(text25);
		
		text26 = new JTextField();
		text26.setColumns(2);
		text26.setVisible(false);
		text26.setEditable(false);
		text26.setBounds(340,70,40,20);
		boardLabel.add(text26);
		
		text27 = new JTextField();
		text27.setColumns(2);
		text27.setVisible(false);
		text27.setEditable(false);
		text27.setBounds(0,370,35,20);
		boardLabel.add(text27);
		
		text28 = new JTextField();
		text28.setColumns(2);
		text28.setVisible(false);
		text28.setEditable(false);
		text28.setBounds(0,250,35,20);
		boardLabel.add(text28);
	}
	
	
	
	
	//places the dice images on the window.
	private void AddDices()
	{
		icon_dice_1 = new ImageIcon("Images/1.png");
		label_dice_1 = new JLabel(icon_dice_1);
		label_dice_1.setBounds(490,300,31,32);
		label_dice_1.setVisible(false);
		boardLabel.add(label_dice_1);
		
		icon_dice_2 = new ImageIcon("Images/1.png");
		label_dice_2 = new JLabel(icon_dice_2);
		label_dice_2.setBounds(550,300,31,32);
		label_dice_2.setVisible(false);
		boardLabel.add(label_dice_2);
		
		initial_dice_player_icon_1 = new ImageIcon("Images/1.png");
		initial_dice_player_label_1 = new JLabel(initial_dice_player_icon_1);
		initial_dice_player_label_1.setBounds(163,250,32,32);
		initial_dice_player_label_1.setVisible(false);
		boardLabel.add(initial_dice_player_label_1);
		
		initial_dice_player_icon_2 = new ImageIcon("Images/1.png");
		initial_dice_player_label_2 = new JLabel(initial_dice_player_icon_2);
		initial_dice_player_label_2.setBounds(163,360,32,32);
		initial_dice_player_label_2.setVisible(false);
		boardLabel.add(initial_dice_player_label_2);
	}
	
	
	
	
	//Adds the labels on the towers.
	private void AddTowerLabels()
	{
		
		label_tower_1 = new JLabel();
		label_tower_1.setBounds(40,395,35,210);
		label_tower_1.setVisible(false);
		label_tower_1.setEnabled(true);
		label_tower_1.addMouseListener(handler);
		label_tower_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_1);
		
		label_tower_2 = new JLabel();
		label_tower_2.setBounds(90,395,35,210);
		label_tower_2.setVisible(false);
		label_tower_2.setEnabled(true);
		label_tower_2.addMouseListener(handler);
		label_tower_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_2);
		
		label_tower_3 = new JLabel();
		label_tower_3.setBounds(140,395,35,210);
		label_tower_3.setVisible(false);
		label_tower_3.setEnabled(true);
		label_tower_3.addMouseListener(handler);
		label_tower_3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_3);
		
		label_tower_4 = new JLabel();
		label_tower_4.setBounds(190,395,35,210);
		label_tower_4.setVisible(false);
		label_tower_4.setEnabled(true);
		label_tower_4.addMouseListener(handler);
		label_tower_4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_4);
		
		label_tower_5 = new JLabel();
		label_tower_5.setBounds(240,395,35,210);
		label_tower_5.setVisible(false);
		label_tower_5.setEnabled(true);
		label_tower_5.addMouseListener(handler);
		label_tower_5.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_5);
		
		label_tower_6 = new JLabel();
		label_tower_6.setBounds(290,395,35,210);
		label_tower_6.setVisible(false);
		label_tower_6.setEnabled(true);
		label_tower_6.addMouseListener(handler);
		label_tower_6.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_6);
		
		label_tower_7 = new JLabel();
		label_tower_7.setBounds(398,395,35,210);
		label_tower_7.setVisible(false);
		label_tower_7.setEnabled(true);
		label_tower_7.addMouseListener(handler);
		label_tower_7.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_7);
		
		label_tower_8 = new JLabel();
		label_tower_8.setBounds(448,395,35,210);
		label_tower_8.setVisible(false);
		label_tower_8.setEnabled(true);
		label_tower_8.addMouseListener(handler);
		label_tower_8.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_8);
		
		label_tower_9 = new JLabel();
		label_tower_9.setBounds(498,395,35,210);
		label_tower_9.setVisible(false);
		label_tower_9.setEnabled(true);
		label_tower_9.addMouseListener(handler);
		label_tower_9.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_9);
		
		label_tower_10 = new JLabel();
		label_tower_10.setBounds(548,395,35,210);
		label_tower_10.setVisible(false);
		label_tower_10.setEnabled(true);
		label_tower_10.addMouseListener(handler);
		label_tower_10.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_10);
		
		label_tower_11 = new JLabel();
		label_tower_11.setBounds(598,395,35,210);
		label_tower_11.setVisible(false);
		label_tower_11.setEnabled(true);
		label_tower_11.addMouseListener(handler);
		label_tower_11.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_11);
		
		label_tower_12 = new JLabel();
		label_tower_12.setBounds(648,395,35,210);
		label_tower_12.setVisible(false);
		label_tower_12.setEnabled(true);
		label_tower_12.addMouseListener(handler);
		label_tower_12.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_12);
		
		label_tower_13 = new JLabel();
		label_tower_13.setBounds(648,35,35,210);
		label_tower_13.setVisible(false);
		label_tower_13.setEnabled(true);
		label_tower_13.addMouseListener(handler);
		label_tower_13.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_13);
		
		label_tower_14 = new JLabel();
		label_tower_14.setBounds(598,35,35,210);
		label_tower_14.setVisible(false);
		label_tower_14.setEnabled(true);
		label_tower_14.addMouseListener(handler);
		label_tower_14.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_14);
		
		label_tower_15 = new JLabel();
		label_tower_15.setBounds(548,35,35,210);
		label_tower_15.setVisible(false);
		label_tower_15.setEnabled(true);
		label_tower_15.addMouseListener(handler);
		label_tower_15.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_15);
		
		label_tower_16 = new JLabel();
		label_tower_16.setBounds(498,35,35,210);
		label_tower_16.setVisible(false);
		label_tower_16.setEnabled(true);
		label_tower_16.addMouseListener(handler);
		label_tower_16.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_16);
		
		label_tower_17 = new JLabel();
		label_tower_17.setBounds(448,35,35,210);
		label_tower_17.setVisible(false);
		label_tower_17.setEnabled(true);
		label_tower_17.addMouseListener(handler);
		label_tower_17.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_17);
		
		label_tower_18 = new JLabel();
		label_tower_18.setBounds(398,35,35,210);
		label_tower_18.setVisible(false);
		label_tower_18.setEnabled(true);
		label_tower_18.addMouseListener(handler);
		label_tower_18.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_18);
		
		label_tower_19 = new JLabel();
		label_tower_19.setBounds(287,35,35,210);
		label_tower_19.setVisible(false);
		label_tower_19.setEnabled(true);
		label_tower_19.addMouseListener(handler);
		label_tower_19.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_19);
		
		label_tower_20 = new JLabel();
		label_tower_20.setBounds(237,35,35,210);
		label_tower_20.setVisible(false);
		label_tower_20.setEnabled(true);
		label_tower_20.addMouseListener(handler);
		label_tower_20.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_20);
		
		label_tower_21 = new JLabel();
		label_tower_21.setBounds(187,35,35,210);
		label_tower_21.setVisible(false);
		label_tower_21.setEnabled(true);
		label_tower_21.addMouseListener(handler);
		label_tower_21.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_21);
		
		label_tower_22 = new JLabel();
		label_tower_22.setBounds(140,35,35,210);
		label_tower_22.setVisible(false);
		label_tower_22.setEnabled(true);
		label_tower_22.addMouseListener(handler);
		label_tower_22.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_22);
		
		label_tower_23 = new JLabel();
		label_tower_23.setBounds(90,35,35,210);
		label_tower_23.setVisible(false);
		label_tower_23.setEnabled(true);
		label_tower_23.addMouseListener(handler);
		label_tower_23.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_23);
		
		label_tower_24 = new JLabel();
		label_tower_24.setBounds(40,35,35,210);
		label_tower_24.setVisible(false);
		label_tower_24.setEnabled(true);
		label_tower_24.addMouseListener(handler);
		label_tower_24.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_24);
		
		label_tower_25 = new JLabel();
		label_tower_25.setBounds(0,450,33,155);
		label_tower_25.setVisible(true);
		label_tower_25.setEnabled(false);
		label_tower_25.addMouseListener(handler);
		label_tower_25.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boardLabel.add(label_tower_25);
	}
	
	
	
	
	//Adds the buttons on the window.
	private void AddButtons()
	{
		cont = new JButton("CONTINUE");
		cont.setBackground(Color.GREEN);
		cont.setFont(new Font("SERIF" , Font.PLAIN , 10));
		cont.setBounds(310,240,100,30);
		cont.setEnabled(true);
		cont.setVisible(false);
		cont.addActionListener(handler);
		boardLabel.add(cont);
		
		undo = new JButton("UNDO");
		undo.setBackground(Color.RED);
		undo.setFont(new Font("SERIF" , Font.PLAIN , 10));
		undo.setBounds(310,365,100,30);
		undo.setEnabled(true);
		undo.setVisible(false);
		undo.addActionListener(handler);
		boardLabel.add(undo);
		
		roll = new JButton("ROLL DICES");
		roll.setBackground(Color.CYAN);
		roll.setFont(new Font("SERIF" , Font.PLAIN , 10));
		roll.setBounds(310,300,100,30);
		roll.setEnabled(true);
		roll.setVisible(false);
		roll.addActionListener(handler);
		boardLabel.add(roll);
		
		initial_roll_white = new JButton("ROLL DICE");
		initial_roll_white.setBackground(Color.CYAN);
		initial_roll_white.setFont(new Font("SERIF" , Font.PLAIN , 10));
		initial_roll_white.setBounds(130,330,100,30);
		initial_roll_white.setEnabled(true);
		initial_roll_white.setVisible(false);
		initial_roll_white.addActionListener(handler);
		boardLabel.add(initial_roll_white);
		
		initial_roll_black = new JButton("ROLL DICE");
		initial_roll_black.setBackground(Color.CYAN);
		initial_roll_black.setFont(new Font("SERIF" , Font.PLAIN , 10));
		initial_roll_black.setBounds(130,280,100,30);
		initial_roll_black.setEnabled(true);
		initial_roll_black.setVisible(false);
		initial_roll_black.addActionListener(handler);
		boardLabel.add(initial_roll_black);
	}
	
	
	
	
	//Adds the Dialogs text area to the window.
	private void AddDialog()
	{
		Dialogs = new JTextArea();
		Dialogs.setRows(1);
		Dialogs.setVisible(true);
		Dialogs.setBounds(0,641,727,50);
		Dialogs.setFont(new Font("Dialog" , Font.BOLD|Font.ITALIC , 20));
		Dialogs.setForeground(Color.BLACK);
		Dialogs.setEnabled(true);
		Dialogs.setEditable(false);		
		add(Dialogs);		
	}
	
	
	
	
	/*
	 * This method is called when a new game begins...
     * It's purpose is to "clean" the board from the previous game and start from the begin.
     */
    private void InitializeGame()
    {
        this.InitializeUtilities();
        this.Clean();
       
        black_nodes_out = 0;
        white_nodes_out = 0;
        helper = 0;
        myStack.clear();

        this.DisableWhitePieces();
       
        this.WhoPlaysFirst();
    }
	
    
    
    
    //This method initializes objects of Classes Node and Tower that represent the flow of the game.
    private void InitializeUtilities()
    {

        //Firstly, we initialize the "towers"
        Tower obj1 = new Tower(1, 40, 575, text1);
        Tower obj2 = new Tower(2, 90, 575, text2);
        Tower obj3 = new Tower(3 , 140, 575, text3);
        Tower obj4 = new Tower(4, 188, 575, text4);
        Tower obj5 = new Tower(5, 238, 575, text5);
        Tower obj6 = new Tower(6, 288, 575, text6);
        
        Tower obj7 = new Tower(7, 398, 575, text7);
        Tower obj8 = new Tower(8, 448, 575, text8);
        Tower obj9 = new Tower(9, 498, 575, text9);
        Tower obj10 = new Tower(10, 546, 575, text10);
        Tower obj11 = new Tower(11, 596, 575, text11);
        Tower obj12 = new Tower(12, 645, 575, text12);
        
        Tower obj13 = new Tower(13, 646, 37, text13);
        Tower obj14 = new Tower(14, 595, 37, text14);
        Tower obj15 = new Tower(15, 547, 37, text15);
        Tower obj16 = new Tower(16, 498, 37, text16);
        Tower obj17 = new Tower(17, 448, 37, text17);
        Tower obj18 = new Tower(18, 398, 37, text18);
        
        Tower obj19 = new Tower(19, 288, 37, text19);
        Tower obj20 = new Tower(20, 238, 37, text20);
        Tower obj21 = new Tower(21, 190, 37, text21);
        Tower obj22 = new Tower(22, 140,37, text22);
        Tower obj23 = new Tower(23, 90,37, text23);
        Tower obj24 = new Tower(24, 40,37, text24);
        
        //This tower will be used for captured black nodes.
        Tower obj25 = new Tower(25,342,510,text25);
        
        //This tower will be used for captured white nodes.
        Tower obj26 = new Tower(26,342,120,text26);
        
        //This tower will be used for white pieces that went out of the game.
        Tower obj27 = new Tower(27, 335, 0, text27);
        
        //This tower will be used for black pieces that went out of the game.
        Tower obj28 = new Tower(28, 272, 0, text28);

        towers = new Tower[]{ obj1, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19, obj20, obj21, obj22, obj23, obj24, obj25, obj26, obj27, obj28 };

        //Now the nodes...
        Node wn1 = new Node ("white", 24, player_button_1,  1);
        Node wn2 = new Node ("white", 24, player_button_2,  2);
        Node wn3 = new Node ("white", 13, player_button_3,  3);
        Node wn4 = new Node ("white", 13, player_button_4,  4);
        Node wn5 = new Node ("white", 13, player_button_5,  5);
        Node wn6 = new Node ("white", 13, player_button_6,  6);
        Node wn7 = new Node ("white", 13, player_button_7,  7);
        Node wn8 = new Node ("white", 8,  player_button_8,  8);
        Node wn9 = new Node ("white", 8,  player_button_9,  9);
        Node wn10 = new Node("white", 8,  player_button_10, 10);
        Node wn11 = new Node("white", 6,  player_button_11, 11);
        Node wn12 = new Node("white", 6,  player_button_12, 12);
        Node wn13 = new Node("white", 6,  player_button_13, 13);
        Node wn14 = new Node("white", 6,  player_button_14, 14);
        Node wn15 = new Node("white", 6,  player_button_15, 15);

        whiteNodes = new Node[] { wn1, wn2, wn3, wn4, wn5, wn6, wn7, wn8, wn9, wn10, wn11, wn12, wn13, wn14, wn15 };

        Node bn1 = new Node ("black", 1,  computer_button_1, 1);
        Node bn2 = new Node ("black", 1,  computer_button_2, 2);
        Node bn3 = new Node ("black", 12, computer_button_3, 3);
        Node bn4 = new Node ("black", 12, computer_button_4, 4);
        Node bn5 = new Node ("black", 12, computer_button_5, 5);
        Node bn6 = new Node ("black", 12, computer_button_6, 6);
        Node bn7 = new Node ("black", 12, computer_button_7, 7);
        Node bn8 = new Node ("black", 17, computer_button_8, 8);
        Node bn9 = new Node ("black", 17, computer_button_9, 9);
        Node bn10 = new Node("black", 17, computer_button_10, 10);
        Node bn11 = new Node("black", 19, computer_button_11, 11);
        Node bn12 = new Node("black", 19, computer_button_12, 12);
        Node bn13 = new Node("black", 19, computer_button_13, 13);
        Node bn14 = new Node("black", 19, computer_button_14, 14);
        Node bn15 = new Node("black", 19, computer_button_15, 15);

        blackNodes = new Node[] { bn1, bn2, bn3, bn4, bn5, bn6, bn7, bn8, bn9, bn10, bn11, bn12, bn13, bn14, bn15 };

        //now we add the nodes.
        obj1.addNodeWithMove(bn1);
        obj1.addNodeWithMove(bn2);
       
        obj24.addNodeWithMove(wn1);
        obj24.addNodeWithMove(wn2);
        
        obj12.addNodeWithMove(bn3);
        obj12.addNodeWithMove(bn4);
        obj12.addNodeWithMove(bn5);
        obj12.addNodeWithMove(bn6);
        obj12.addNodeWithMove(bn7);

        obj13.addNodeWithMove(wn3);
        obj13.addNodeWithMove(wn4);
        obj13.addNodeWithMove(wn5);
        obj13.addNodeWithMove(wn6);
        obj13.addNodeWithMove(wn7);

        obj17.addNodeWithMove(bn8);
        obj17.addNodeWithMove(bn9);
        obj17.addNodeWithMove(bn10);

        obj8.addNodeWithMove(wn8);
        obj8.addNodeWithMove(wn9);
        obj8.addNodeWithMove(wn10);

        obj19.addNodeWithMove(bn11);
        obj19.addNodeWithMove(bn12);
        obj19.addNodeWithMove(bn13);
        obj19.addNodeWithMove(bn14);
        obj19.addNodeWithMove(bn15);

        obj6.addNodeWithMove(wn11);
        obj6.addNodeWithMove(wn12);
        obj6.addNodeWithMove(wn13);
        obj6.addNodeWithMove(wn14);
        obj6.addNodeWithMove(wn15);

        board = new Board(towers);
    }
    
    
    
    
    //Checks if there is one piece of the opposite colour on the specified tower , so that it can be captured.
    private boolean OnePieceAlone(int tower)
    {
        //if it is player's turn...
        if (player)
        {
            if( towers[tower-1].getNumberOfNodes()==1 && towers[tower-1].getColourOfNodes().equals("black")) return true;
        }

        //else if it is computer's turn...
        else
        {
             if( towers[tower-1].getNumberOfNodes()==1 && towers[tower-1].getColourOfNodes().equals("white")) return true;
        }

        return false;
    }
    
    
    
    
    //Checks if there are any white pieces captured.
    private boolean WhiteCaptured()
    {
        return towers[25].getNumberOfNodes() > 0;
    }
    
    
     
    
    //This method checks if there are pieces that went out of the game this turn , so we restore the number of pieces out if Undo was pressed.
    private void RestorePiecesOut()
    {
        int pieces_out = 0;

        //if it is player's turn...
        if (player)
        {
            for (int i = 0; i < myStack.size(); i++)
            {
                if (myStack.get(i) == 26) pieces_out++;
            }

            //restore the number of white pieces out...
            white_nodes_out -= pieces_out;
        }

        //else if it is computer's turn...
        else
        {
            for (int i = 0; i < myStack.size(); i++)
            {
                if (myStack.get(i) == 27) pieces_out++;
            }

            //restore the number of black pieces out...
            black_nodes_out -= pieces_out;
        }
    }
	
	
	
	
    //Checks if all white pieces are on the player's area. i.e Towers 1-6.
    private boolean WhitesInPlace()
    {
        int inPlace = 0;

        for (int i = 0; i < whiteNodes.length; i++)
        {
            int pos = whiteNodes[i].getPosition();

            if ((pos >= 1) && (pos <= 6)) inPlace++;
        }

        return ((inPlace + white_nodes_out) == 15);
    }
    
    
    
    
    //Checks if all black pieces are on the computer's area. i.e Towers 19-24.
    private boolean BlacksInPlace()
    {
        int inPlace = 0;

        for (int i = 0; i < blackNodes.length; i++)
        {
            int pos = blackNodes[i].getPosition();

            if ((pos >= 19) && (pos <= 24)) inPlace++;
        }

        return ((inPlace + black_nodes_out) == 15);
    }
	
    
    
    
    //Enables all the top white pieces that are not captured.
    private void EnableWhitePieces_1()
    {    	
        for (int i = 0; i < 24; i++)
        {
        	towers[i].EnableFirstWhiteNodes();
        }
    }
    
    
    
    
    //Enables the white pieces that are suitable according to the roll result,if player has all of his pieces into his area.
    private void EnableWhitePieces_2()
    {
        //if the dice1 was not played...
        if(!Dice1_played)
        {
            //if there are pieces on tower number 6, enable them,regardless the result of the dices.
            if (towers[5].getNumberOfNodes() > 0)
            {
                towers[5].EnableFirstWhiteNodes();
            }

            //if there are pieces on tower number 5.
            if (towers[4].getNumberOfNodes() > 0)
            {
                //if there are no pieces on tower number 6, enable pieces on tower number 5 ,regardless the result of the dices.
                if (towers[5].getNumberOfNodes() == 0) towers[4].EnableFirstWhiteNodes();

                //else if there are pieces on tower 6, enable the pieces on tower 5 iff the result of the dice is <=5.
                else
                {
                    if ((dice1 + 1) <= 5) towers[4].EnableFirstWhiteNodes();
                }
            }
            
            //if there are pieces on tower number 4.
            if (towers[3].getNumberOfNodes() > 0)
            {
                //if there are no pieces on tower number 6 and tower number 5, enable pieces on tower number 4,regardless the result of the dices.
                if (towers[5].getNumberOfNodes() == 0 && towers[4].getNumberOfNodes()==0 ) towers[3].EnableFirstWhiteNodes();

                //else if there are pieces on tower 6 and/or tower 5, enable the pieces on tower 4 iff the result of the dice is <=4.
                else
                {
                    if ((dice1 + 1) <= 4) towers[3].EnableFirstWhiteNodes();
                }
            }

            //if there are pieces on tower number 3.
            if (towers[2].getNumberOfNodes() > 0)
            {
                //if there are no pieces on tower number 6 and tower number 5 and tower number 4, enable pieces on tower number 3,regardless the result of the dices.
                if (towers[5].getNumberOfNodes() == 0 && towers[4].getNumberOfNodes() == 0 && towers[3].getNumberOfNodes() == 0) towers[2].EnableFirstWhiteNodes();

                //else if there are pieces on tower 6 and/or tower 5 and/or tower 4, enable the pieces on tower 3 iff the result of the dice is <=3.
                else
                {
                    if ((dice1 + 1) <= 3) towers[2].EnableFirstWhiteNodes();
                }
            }

            //if there are pieces on tower number 2.
            if (towers[1].getNumberOfNodes() > 0)
            {
                //if there are no pieces on tower number 6 and tower number 5 and tower number 4 and tower number 3, enable pieces on tower number 2,regardless the result of the dices.
                if (towers[5].getNumberOfNodes() == 0 && towers[4].getNumberOfNodes() == 0 && towers[3].getNumberOfNodes() == 0 && towers[2].getNumberOfNodes() == 0) towers[1].EnableFirstWhiteNodes();

                //else if there are pieces on tower 6 and/or tower 5 and/or tower 4 and/or tower 3, enable the pieces on tower 2 iff the result of the dice is <=2.
                else
                {
                    if ((dice1 + 1) <= 2) towers[1].EnableFirstWhiteNodes();
                }
            }

            //if there are pieces on tower number 1.
            if (towers[0].getNumberOfNodes() > 0)
            {
                //if there are no pieces on tower number 6 and tower number 5 and tower number 4 and tower number 3 and tower number 2, enable pieces on tower number 1,regardless the result of the dices.
                if (towers[5].getNumberOfNodes() == 0 && towers[4].getNumberOfNodes() == 0 && towers[3].getNumberOfNodes() == 0 && towers[2].getNumberOfNodes() == 0 && towers[1].getNumberOfNodes() == 0) towers[0].EnableFirstWhiteNodes();

                //else if there are pieces on tower 6 and/or tower 5 and/or tower 4 and/or tower 3 and/or tower 2, enable the pieces on tower 1 iff the result of the dice is =1.
                else
                {
                    if ((dice1 + 1) == 1) towers[0].EnableFirstWhiteNodes();
                }
            }

        }//if(!Dice1_played)

        if (!Dice2_played)
        {
            //if there are pieces on tower number 6, enable them,regardless the result of the dices.
            if (towers[5].getNumberOfNodes() > 0)
            {
                towers[5].EnableFirstWhiteNodes();
            }

            //if there are pieces on tower number 5.
            if (towers[4].getNumberOfNodes() > 0)
            {
                //if there are no pieces on tower number 6, enable pieces on tower number 5 ,regardless the result of the dices.
                if (towers[5].getNumberOfNodes() == 0) towers[4].EnableFirstWhiteNodes();

                //else if there are pieces on tower 6, enable the pieces on tower 5 iff the result of the dice is <=5.
                else
                {
                    if ((dice2 + 1) <= 5) towers[4].EnableFirstWhiteNodes();
                }
            }

            //if there are pieces on tower number 4.
            if (towers[3].getNumberOfNodes() > 0)
            {
                //if there are no pieces on tower number 6 and tower number 5, enable pieces on tower number 4,regardless the result of the dices.
                if (towers[5].getNumberOfNodes() == 0 && towers[4].getNumberOfNodes() == 0) towers[3].EnableFirstWhiteNodes();

                //else if there are pieces on tower 6 and/or tower 5, enable the pieces on tower 4 iff the result of the dice is <=4.
                else
                {
                    if ((dice2 + 1) <= 4) towers[3].EnableFirstWhiteNodes();
                }
            }

            //if there are pieces on tower number 3.
            if (towers[2].getNumberOfNodes() > 0)
            {
                //if there are no pieces on tower number 6 and tower number 5 and tower number 4, enable pieces on tower number 3,regardless the result of the dices.
                if (towers[5].getNumberOfNodes() == 0 && towers[4].getNumberOfNodes() == 0 && towers[3].getNumberOfNodes() == 0) towers[2].EnableFirstWhiteNodes();

                //else if there are pieces on tower 6 and/or tower 5 and/or tower 4, enable the pieces on tower 3 iff the result of the dice is <=3.
                else
                {
                    if ((dice2 + 1) <= 3) towers[2].EnableFirstWhiteNodes();
                }
            }

            //if there are pieces on tower number 2.
            if (towers[1].getNumberOfNodes() > 0)
            {
                //if there are no pieces on tower number 6 and tower number 5 and tower number 4 and tower number 3, enable pieces on tower number 2,regardless the result of the dices.
                if (towers[5].getNumberOfNodes() == 0 && towers[4].getNumberOfNodes() == 0 && towers[3].getNumberOfNodes() == 0 && towers[2].getNumberOfNodes() == 0) towers[1].EnableFirstWhiteNodes();

                //else if there are pieces on tower 6 and/or tower 5 and/or tower 4 and/or tower 3, enable the pieces on tower 2 iff the result of the dice is <=2.
                else
                {
                    if ((dice2 + 1) <= 2) towers[1].EnableFirstWhiteNodes();
                }
            }

            //if there are pieces on tower number 1.
            if (towers[0].getNumberOfNodes() > 0)
            {
                //if there are no pieces on tower number 6 and tower number 5 and tower number 4 and tower number 3 and tower number 2, enable pieces on tower number 1,regardless the result of the dices.
                if (towers[5].getNumberOfNodes() == 0 && towers[4].getNumberOfNodes() == 0 && towers[3].getNumberOfNodes() == 0 && towers[2].getNumberOfNodes() == 0 && towers[1].getNumberOfNodes() == 0) towers[0].EnableFirstWhiteNodes();

                //else if there are pieces on tower 6 and/or tower 5 and/or tower 4 and/or tower 3 and/or tower 2, enable the pieces on tower 1 iff the result of the dice is =1.
                else
                {
                    if ((dice2 + 1) == 1) towers[0].EnableFirstWhiteNodes();
                }
            }

        }// if (!Dice2_played)
    }
    
    
    
    
    //Enables white pieces that are captured.
    private void EnableWhiteCapturedPieces()
    {
        towers[25].EnableFirstWhiteNodes();
    }
    
    
    
    
    //Disables all top white pieces.
    private void DisableWhitePieces()
    {
        for (int i = 0; i < towers.length; i++)
        {
            towers[i].DisableFirstWhiteNodes();
        }
    }
    
    
    
    
    //Prepares the board for the next player to roll te dices.
    private void PrepareToRollAgain()
    {
        roll.setVisible(true);
        label_dice_1.setVisible(false);
        label_dice_2.setVisible(false);
    }
    
    
    
    
    //Returns true if the result of dices are doubles. i.e. 1-1 , 2-2 etc...
    private boolean Doubles()
    {
        return (dice1 == dice2);
    }
    
    
    
    
    //Checks which dice was played, according to the selected_piece variable and the number of tower. 
    private void CheckWhichDiceWasPlayed(int n)
    {
        int result = Math.abs(n - (selected_piece + 1));

        //if the dices were "doubles"...
        if (Doubles())
        {
            //If there are more moves for the player...
            if (NumberOfTimesPlayed >= 0 && NumberOfTimesPlayed < 3)
            {
                    NumberOfTimesPlayed++;
                    if (NumberOfTimesPlayed == 2)
                    {
                    	label_dice_1.setVisible(false);
                        Dice1_played = true;
                    }
            }

            //if the player completed both of his 4 moves...
            else if (NumberOfTimesPlayed == 3)
            {
                Dice1_played = true;
                Dice2_played = true;
                label_dice_2.setVisible(false);
            }
        }

        //else if the result was not doubles...
        else
        {
            //if move matches the result of the first dice. 
            if ((result == dice1 + 1) && (!Dice1_played))
            {
                Dice1_played = true;
                label_dice_1.setVisible(false);
            }

            //if move matches the result of the second dice. 
            else if ((result == dice2 + 1) && (!Dice2_played))
            {
                Dice2_played = true;
                label_dice_2.setVisible(false);
            }

            //if the move does not matches exactly any dice , this means that a piece was taken out of the game.
            else
            {
                if (!Dice1_played && !Dice2_played)
                {
                    if (dice1 > dice2)
                    {
                        Dice1_played = true;
                        label_dice_1.setVisible(false);
                    }

                    else
                    {
                        Dice2_played = true;
                        label_dice_2.setVisible(false);
                    }
                }

                else if (!Dice1_played && Dice2_played)
                {
                    Dice1_played = true;
                    label_dice_1.setVisible(false);
                }

                else if (Dice1_played && !Dice2_played)
                {
                    Dice2_played = true;
                    label_dice_2.setVisible(false);
                }
            }
        }
    }
    
    
    
    
    //Checks if the current player has finished his moves in order for the next player to play.
    private void NextPlayer()
    {
        //if both of the dices have been played...
        if (Dice1_played && Dice2_played)
        {
            //if it was the player's turn...
            if (player)
            {
                DisableWhitePieces();
                cont.setVisible(true);
            }
        }
    }
    
    
    
    
    //Rolls the dices and enables the current player's pieces.
    private void RollDices()
    {
    	initial_dice_player_label_1.setVisible(false);
    	initial_dice_player_label_2.setVisible(false);
    	
        //always give an initial value 0 to variable NumberOfTimesPlayed.
        NumberOfTimesPlayed = 0;

        roll.setVisible(false);
        label_dice_1.setVisible(true);
        label_dice_2.setVisible(true);
        dice1 = r.nextInt(6);
        ToDice(dice1,(byte)0);
        dice2 = r.nextInt(6);
        ToDice(dice2,(byte)1);
        Dice1_played = false;
        Dice2_played = false;
    }
    
    
    
    
    //sets the specified image of the dice.
    private void ToDice(int i, byte flag)
    {	
    	if(flag == 0)
    	{
    		if      (i == 0) label_dice_1.setIcon(new ImageIcon("Images/1.png"));
            else if (i == 1) label_dice_1.setIcon(new ImageIcon("Images/2.png"));
            else if (i == 2) label_dice_1.setIcon(new ImageIcon("Images/3.png"));
            else if (i == 3) label_dice_1.setIcon(new ImageIcon("Images/4.png"));
            else if (i == 4) label_dice_1.setIcon(new ImageIcon("Images/5.png"));
            else if (i == 5) label_dice_1.setIcon(new ImageIcon("Images/6.png"));
    	}
    	
    	else if (flag == 1)
    	{
    		if      (i == 0) label_dice_2.setIcon(new ImageIcon("Images/1.png"));
            else if (i == 1) label_dice_2.setIcon(new ImageIcon("Images/2.png"));
            else if (i == 2) label_dice_2.setIcon(new ImageIcon("Images/3.png"));
            else if (i == 3) label_dice_2.setIcon(new ImageIcon("Images/4.png"));
            else if (i == 4) label_dice_2.setIcon(new ImageIcon("Images/5.png"));
            else if (i == 5) label_dice_2.setIcon(new ImageIcon("Images/6.png"));
    	}
    	
    	else if (flag == 2)
    	{
    		if      (i == 0) initial_dice_player_label_2.setIcon(new ImageIcon("Images/1.png"));
            else if (i == 1) initial_dice_player_label_2.setIcon(new ImageIcon("Images/2.png"));
            else if (i == 2) initial_dice_player_label_2.setIcon(new ImageIcon("Images/3.png"));
            else if (i == 3) initial_dice_player_label_2.setIcon(new ImageIcon("Images/4.png"));
            else if (i == 4) initial_dice_player_label_2.setIcon(new ImageIcon("Images/5.png"));
            else if (i == 5) initial_dice_player_label_2.setIcon(new ImageIcon("Images/6.png"));
    	}
    	
    	else
    	{
    		if      (i == 0) initial_dice_player_label_1.setIcon(new ImageIcon("Images/1.png"));
            else if (i == 1) initial_dice_player_label_1.setIcon(new ImageIcon("Images/2.png"));
            else if (i == 2) initial_dice_player_label_1.setIcon(new ImageIcon("Images/3.png"));
            else if (i == 3) initial_dice_player_label_1.setIcon(new ImageIcon("Images/4.png"));
            else if (i == 4) initial_dice_player_label_1.setIcon(new ImageIcon("Images/5.png"));
            else if (i == 5) initial_dice_player_label_1.setIcon(new ImageIcon("Images/6.png"));
    	}
    }
    
    
    
    
    //Displays the winner of the game.
    private void DisplayWinner()
    {
        //If computer was the first that removed all of it's pieces , then it is the winner.
        if (towers[27].getNumberOfNodes() == 15)
        {
            Dialogs.setText("COMPUTER WINS!!!");
            DisableWhitePieces();
            label_dice_1.setVisible(false);
            label_dice_2.setVisible(false);
            cont.setVisible(false);
            undo.setVisible(false);
        }
        
        //else player is the winner.
        else if (white_nodes_out == 15)
        {
            DisableWhitePieces();
            Dialogs.setText("COMPUTER WINS!!!");
            DisableWhitePieces();
            label_dice_1.setVisible(false);
            label_dice_2.setVisible(false);
            cont.setVisible(false);
            undo.setVisible(false);
        }
    }
    
    
    
    
    //This method "cleans" the board.
    private void Clean()
    {
        //Firtsly , we "clean" the textBoxes. 
        text1.setText("");
        text1.setVisible(false);

        text2.setText("");
        text2.setVisible(false);
        
        text3.setText("");
        text3.setVisible(false);
        
        text4.setText("");
        text4.setVisible(false);
        
        text5.setText("");
        text5.setVisible(false);
        
        text6.setText("");
        text6.setVisible(false);
        
        text7.setText("");
        text7.setVisible(false);
        
        text8.setText("");
        text8.setVisible(false);
        
        text9.setText("");
        text9.setVisible(false);
        
        text10.setText("");
        text10.setVisible(false);
        
        text11.setText("");
        text11.setVisible(false);
        
        text12.setText("");
        text12.setVisible(false);
        
        text13.setText("");
        text13.setVisible(false);
        
        text14.setText("");
        text14.setVisible(false);
        
        text15.setText("");
        text15.setVisible(false);
        
        text16.setText("");
        text16.setVisible(false);
        
        text17.setText("");
        text17.setVisible(false);
        
        text18.setText("");
        text18.setVisible(false);
        
        text19.setText("");
        text19.setVisible(false);
        
        text20.setText("");
        text20.setVisible(false);
        
        text21.setText("");
        text21.setVisible(false);
        
        text22.setText("");
        text22.setVisible(false);
        
        text23.setText("");
        text23.setVisible(false);
        
        text24.setText("");
        text24.setVisible(false);
        
        text25.setText("");
        text25.setVisible(false);
        
        text26.setText("");
        text26.setVisible(false);
        
        text27.setText("");
        text27.setVisible(false);
        
        text28.setText("");
        text28.setVisible(false);
        

        //Then the dices and the dices' buttons.
        initial_dice_player_label_1.setVisible(false);
        initial_dice_player_label_2.setVisible(false);
        
        initial_roll_white.setVisible(false);
        initial_roll_black.setVisible(false);
        
        roll.setVisible(false);
        cont.setVisible(false);
        undo.setVisible(false);
        label_dice_1.setVisible(false);
        label_dice_2.setVisible(false);

        //And now we "clean" the tower buttons.
        DisableTowers();
    }

    
    
    
    
    //Disables all the Tower buttons.
    private void DisableTowers()
    {
        label_tower_1.setVisible(false);
        label_tower_2.setVisible(false);
        label_tower_3.setVisible(false);
        label_tower_4.setVisible(false);
        label_tower_5.setVisible(false);
        label_tower_6.setVisible(false);
        label_tower_7.setVisible(false);
        label_tower_8.setVisible(false);
        label_tower_9.setVisible(false);
        label_tower_10.setVisible(false);
        label_tower_11.setVisible(false);
        label_tower_12.setVisible(false);
        label_tower_13.setVisible(false);
        label_tower_14.setVisible(false);
        label_tower_15.setVisible(false);
        label_tower_16.setVisible(false);
        label_tower_17.setVisible(false);
        label_tower_18.setVisible(false);
        label_tower_19.setVisible(false);
        label_tower_20.setVisible(false);
        label_tower_21.setVisible(false);
        label_tower_22.setVisible(false);
        label_tower_23.setVisible(false);
        label_tower_24.setVisible(false);
        label_tower_25.setVisible(false);
    }
    
    
    
    
    //Enables the suitable towers.
    private void EnableTower(int i)
    {
        switch (i)
        {
        	case 0 :  label_tower_1.setVisible(true);  break;
        	case 1 :  label_tower_2.setVisible(true);  break;
        	case 2 :  label_tower_3.setVisible(true);  break;
        	case 3 :  label_tower_4.setVisible(true);  break;
        	case 4 :  label_tower_5.setVisible(true);  break;
        	case 5 :  label_tower_6.setVisible(true);  break;
        	case 6 :  label_tower_7.setVisible(true);  break;
        	case 7 :  label_tower_8.setVisible(true);  break;
        	case 8 :  label_tower_9.setVisible(true);  break;
        	case 9 :  label_tower_10.setVisible(true); break;
        	case 10 : label_tower_11.setVisible(true); break;
        	case 11 : label_tower_12.setVisible(true); break;
        	case 12 : label_tower_13.setVisible(true); break;
        	case 13 : label_tower_14.setVisible(true); break;
        	case 14 : label_tower_15.setVisible(true); break;
        	case 15 : label_tower_16.setVisible(true); break;
        	case 16 : label_tower_17.setVisible(true); break;
        	case 17 : label_tower_18.setVisible(true); break;
        	case 18 : label_tower_19.setVisible(true); break;
        	case 19 : label_tower_20.setVisible(true); break;
        	case 20 : label_tower_21.setVisible(true); break;
        	case 21 : label_tower_22.setVisible(true); break;
        	case 22 : label_tower_23.setVisible(true); break;
        	case 23 : label_tower_24.setVisible(true); break;
        	case 24 : label_tower_25.setVisible(true); break;
        }
    } 
    
    
    
    
    //Enables the suitable towers according to the current player and the result of the dices.
    private void EnableSuitableTowers()
    {
        //If it is the player's turn...
        if (player)
        {
            if (!Dice1_played)
            {
                //convert range 0-5 to range 1-6.
                dice1++;

                //The number of tower where the specified piece can move , according to the result of the first dice.
                int availablePosition = selected_piece - dice1;

                //if the piece wont go out of bounds...
                if (availablePosition >= 0)
                {
                    //Enable the suitable tower.
                    //A tower is suitable if it is empty, if there are pieces of the same colour on it, or if there is only one piece of the opposite colour.
                    if ((towers[availablePosition].getNumberOfNodes() == 0) || (towers[availablePosition].getNumberOfNodes() != 0 && towers[availablePosition].getColourOfNodes().equals("white")) || (towers[availablePosition].getNumberOfNodes() == 1 && towers[availablePosition].getColourOfNodes().equals("black")))
                    {
                        EnableTower(availablePosition);
                    }
                }

                else
                {
                    if (WhitesInPlace()) text27.setVisible(true);
                }

                dice1--;
            }

            if (!Dice2_played)
            {
                //convert range 0-5 to range 1-6.
                dice2++;

                //The number of tower where the specified piece can move , according to the result of the first dice.
                int availablePosition = selected_piece - dice2;

                if (availablePosition >= 0)
                {
                    //Enable the suitable tower.
                    //A tower is suitable if it is empty, if there are pieces of the same colour on it, or if there is only one piece of the opposite colour.
                    if ((towers[availablePosition].getNumberOfNodes() == 0) || (towers[availablePosition].getNumberOfNodes() != 0 && towers[availablePosition].getColourOfNodes().equals("white")) || (towers[availablePosition].getNumberOfNodes() == 1 && towers[availablePosition].getColourOfNodes().equals("black")))
                    {
                        EnableTower(availablePosition);
                    }
                }

                else
                {
                    if (WhitesInPlace()) text27.setVisible(true);
                }

                dice2--;
            }
        }

        //else if it is computer's turn...
        else
        {
            if (!Dice1_played)
            {
                //convert range 0-5 to range 1-6.
                dice1++;

                //The number of tower where the specified piece can move , according to the result of the first dice.
                int availablePosition = selected_piece + dice1;

                if (availablePosition <= 23)
                {
                    //Enable the suitable tower.
                    //A tower is suitable if it is empty, if there are pieces of the same colour on it, or if there is only one piece of the opposite colour.
                    if ((towers[availablePosition].getNumberOfNodes() == 0) || (towers[availablePosition].getNumberOfNodes() != 0 && towers[availablePosition].getColourOfNodes().equals("black")) || (towers[availablePosition].getNumberOfNodes() == 1 && towers[availablePosition].getColourOfNodes().equals("white")))
                    {
                        EnableTower(availablePosition);
                    }
                }

                else
                {
                    if (BlacksInPlace()) text28.setVisible(true);
                }

                dice1--;
            }

            if (!Dice2_played)
            {
                //convert range 0-5 to range 1-6.
                dice2++;

                //The number of tower where the specified piece can move , according to the result of the first dice.
                int availablePosition = selected_piece + dice2;

                if (availablePosition <= 23)
                {
                    //Enable the suitable tower.
                    //A tower is suitable if it is empty, if there are pieces of the same colour on it, or if there is only one piece of the opposite colour.
                    if ((towers[availablePosition].getNumberOfNodes() == 0) || (towers[availablePosition].getNumberOfNodes() != 0 && towers[availablePosition].getColourOfNodes().equals("black")) || (towers[availablePosition].getNumberOfNodes() == 1 && towers[availablePosition].getColourOfNodes().equals("white")))
                    {
                        EnableTower(availablePosition);
                    }
                }

                else
                {
                    if (BlacksInPlace()) text28.setVisible(true);
                }

                dice2--;
            }
        }
    }
    
    
    
    
    //This method checks if the current player can move his/it's pieces according to the dices result.
    private boolean IsAnyMoveAvailable()
    {
        //if it is player's turn.
        if (player)
        {
            //if there are captured white pieces.
            if (WhiteCaptured())
            {
                //The number of total available moves according to dice1.
                int NumberOfAvailableMoves1 = 0;

                //The number of total available moves according to dice2.
                int NumberOfAvailableMoves2 = 0;

                //The number of tower where the specified piece can move , according to the result of the first dice.
                int availablePosition1 = (25 - (dice1 + 1)) - 1;

                //The number of tower where the specified piece can move , according to the result of the second dice.
                int availablePosition2 = (25 - (dice2 + 1)) - 1;

                //A tower is suitable if it is empty, if there are pieces of the same colour on it, or if there is only one piece of the opposite colour.
                if ((towers[availablePosition1].getNumberOfNodes() == 0) || (towers[availablePosition1].getNumberOfNodes() != 0 && towers[availablePosition1].getColourOfNodes().equals("white")) || (towers[availablePosition1].getNumberOfNodes() == 1 && towers[availablePosition1].getColourOfNodes().equals("black")))
                {
                    NumberOfAvailableMoves1++;
                }

                //A tower is suitable if it is empty, if there are pieces of the same colour on it, or if there is only one piece of the opposite colour.
                if ((towers[availablePosition2].getNumberOfNodes() == 0) || (towers[availablePosition2].getNumberOfNodes() != 0 && towers[availablePosition2].getColourOfNodes().equals("white")) || (towers[availablePosition2].getNumberOfNodes() == 1 && towers[availablePosition2].getColourOfNodes().equals("black")))
                {
                    NumberOfAvailableMoves2++;
                }

                //if none of the dices has been played...
                if ((!Dice1_played) && (!Dice2_played))
                {
                    //if there is no move available for any piece for both of the two dices,return false.
                    if (NumberOfAvailableMoves1 == 0 && NumberOfAvailableMoves2 == 0) return false;

                    //in any other case there is an available move,so return true.
                    else return true;
                }

                //if the dice1 has been played but the dice2 not...
                else if ((Dice1_played) && (!Dice2_played))
                {
                    if (NumberOfAvailableMoves2 == 0) return false;
                    else if (NumberOfAvailableMoves2 > 0) return true;
                }

                //if the dice2 has been played but the dice1 not...
                else if ((!Dice1_played) && (Dice2_played))
                {
                    if (NumberOfAvailableMoves1 == 0) return false;
                    else if (NumberOfAvailableMoves1 > 0) return true;
                }
            }

            //else if there no captured white pieces...
            else
            {
                //The number of total available moves according to dice1.
                int NumberOfAvailableMoves1 = 0;

                //The number of total available moves according to dice2.
                int NumberOfAvailableMoves2 = 0;

                for (int i = 0; i < whiteNodes.length; i++)
                {
                    //if this Node did not go out of the game.
                    if ((whiteNodes[i].getPosition() > 0) && (whiteNodes[i].getPosition()!=27))
                    {
                        //The number of tower where the specified piece can move , according to the result of the first dice.
                        int availablePosition1 = (whiteNodes[i].getPosition() - (dice1 + 1)) - 1;

                        //The number of tower where the specified piece can move , according to the result of the second dice.
                        int availablePosition2 = (whiteNodes[i].getPosition() - (dice2 + 1)) - 1;

                        //if the piece wont go out of bounds...
                        if (availablePosition1 >= 0)
                        {   
                            //A tower is suitable if it is empty, if there are pieces of the same colour on it, or if there is only one piece of the opposite colour.
                            if ((towers[availablePosition1].getNumberOfNodes() == 0) || (towers[availablePosition1].getNumberOfNodes() != 0 && towers[availablePosition1].getColourOfNodes().equals("white")) || (towers[availablePosition1].getNumberOfNodes() == 1 && towers[availablePosition1].getColourOfNodes().equals("black")))
                            {
                                NumberOfAvailableMoves1++;
                            }
                        }

                        //else if the move is out of bounds...
                        else
                        {
                            if (WhitesInPlace()) NumberOfAvailableMoves1++;
                        }

                        //if the piece wont go out of bounds...
                        if (availablePosition2 >= 0)
                        {
                            //A tower is suitable if it is empty, if there are pieces of the same colour on it, or if there is only one piece of the opposite colour.
                            if ((towers[availablePosition2].getNumberOfNodes() == 0) || (towers[availablePosition2].getNumberOfNodes() != 0 && towers[availablePosition2].getColourOfNodes().equals("white")) || (towers[availablePosition2].getNumberOfNodes() == 1 && towers[availablePosition2].getColourOfNodes().equals("black")))
                            {
                                NumberOfAvailableMoves2++;
                            }
                        }

                        //else if the move is out of bounds...
                        else
                        {
                            if (WhitesInPlace()) NumberOfAvailableMoves2++;
                        }


                    }//if (whiteNodes[i].POSITION > 0)

                }//for

                //if none of the dices has been played...
                if ((!Dice1_played) && (!Dice2_played))
                {
                    //if there is no move available for any piece for both of the two dices,return false.
                    if (NumberOfAvailableMoves1 == 0 && NumberOfAvailableMoves2 == 0) return false;

                    //in any other case there is an available move,so return true.
                    else return true;
                }

                //if the dice1 has been played but the dice2 not...
                else if ((Dice1_played) && (!Dice2_played))
                {
                    if (NumberOfAvailableMoves2 == 0) return false;
                    else if (NumberOfAvailableMoves2 > 0) return true;
                }

                //if the dice2 has been played but the dice1 not...
                else if ((!Dice1_played) && (Dice2_played))
                {
                    if (NumberOfAvailableMoves1 == 0) return false;
                    else if (NumberOfAvailableMoves1 > 0) return true;
                }

            }//else(!WhiteCaptured())

        }//if(player)

        return true;
    }
    
    
    
    
    //Enables current player's pieces that can move.
    private void EnableSuitablePieces()
    {
        //if there is not an available move.
        if (!IsAnyMoveAvailable())
        {
            Dialogs.setText("THERE IS NO MOVE AVAILABLE!");
            cont.setVisible(true);
        }

        //else if there is a move available.
        else
        {
            //if it is the player's turn...
            if (player)
            {
                //disable the pieces from the previous move and enable them again.
                DisableWhitePieces();

                //if the player has some pieces captured, enable only them.
                if (WhiteCaptured())
                {
                    EnableWhiteCapturedPieces();
                }

                //else enable the other pieces.
                else
                {
                    if(WhitesInPlace()) EnableWhitePieces_2();

                    else EnableWhitePieces_1();
                }
            }
        }
    }
    
    
    
    
    //This method lets the computer and the player throw a dice and determines who plays first, according to the biggest result.
    private void WhoPlaysFirst()
    {
        Dialogs.setText("LET'S SEE WHO WILL PLAY FIRST..."); 
        
        if (helper == 0)
        {
            handler.actionPerformed(new ActionEvent(initial_roll_black,0,""));
        }

        else if (helper == 1)
        {  
        	initial_roll_white.setVisible(true);
        }
        
        else
        {


            if (dice1 > dice2)
            {
                player = true; 
                Play_Backgammon();
            }

            else if (dice2 > dice1)
            {
                player = false;
                Play_Backgammon();
            }

            //if dice results are the same, call this method again.
            else
            {
                helper = 0;
                initial_dice_player_label_1.setVisible(false);
                initial_dice_player_label_2.setVisible(false);
                WhoPlaysFirst();
            }

        }
        
    }
    
    
    
    
    //The main method of this program. This is where the game goes on...
    private void Play_Backgammon()
    {

        PrepareToRollAgain();
            
         //If it is player's turn...
         if (player)
         {
               Dialogs.setText("PLAYER PLAYS");
         }       

         //else if it is computer's turn...
         else
         {
             roll.setVisible(false);

             //if computer has a move available...
             if (IsAnyMoveAvailable())
             {
                 handler.actionPerformed(new ActionEvent(roll , 0 , "pressed"));
                 
                 board.setBoard(towers);
                 Dialogs.setText("COMPUTER PLAYS : PLEASE WAIT, I'M THINKING...");

                 
                 //PrintBoard();
                 //Run the expectiminimax with pruning.
                 Move mv = minimax.ExpectiMiniMaxAlphaBeta(new Board(board) , dice1 + 1 , dice2 + 1);
                 ArrayList<Integer> moves = mv.getMoves();
                 
                 for (int i = 0; i < moves.size(); i += 2)
                 {
                     towers[moves.get(i)].addNodeWithMove(towers[moves.get(i+1)].removeNodeWithMove());
                 }
                 
                 
                 if(towers[27].getNumberOfNodes() == 15) DisplayWinner();
                 
                 else Dialogs.setText("I HAVE FINISHED MY MOVES. YOUR TURN NOW...");
             }

             //else if computer has no move available...
             else
             {
            	 Dialogs.setText("I HAVE NOT ANY AVAILABLE MOVES.");
             }

             cont.setVisible(true);
         }

    }//Play_Backgammon end.
    
	
    
    
    
    
    //----------------------------------------INNER CLASS FOR HANDLING EVENTS----------------------------------------//
    
	private class EventHandler implements ActionListener , MouseListener
	{
		
		@Override
		public void actionPerformed(ActionEvent ev)
		{
			if(ev.getSource() == item11)
			{
				int choice = JOptionPane.showConfirmDialog(null, "Do you really want to start a new game?" ,"NEW GAME", JOptionPane.YES_NO_OPTION);
				
				if(choice == JOptionPane.YES_OPTION)
				{
					LoadPreferences();
					minimax = new MiniMax(minimax_depth);
					InitializeGame();
					PlaceWhiteButtons();
					ShowWhiteButtons();
					PlaceBlackButtons();
					ShowBlackButtons();
				}
				
				else return;
			}
			
			else if(ev.getSource() == item12)
			{
				PreferencesWindow pref = new PreferencesWindow();
				pref.setVisible(true);
			}
			
			else if(ev.getSource() == cont)
			{
				if (player) player = false;
	            else player = true;
	            cont.setVisible(false);
	            undo.setVisible(false);
	            myStack.clear();
	            Play_Backgammon();
			}
			
			else if(ev.getSource() == undo)
			{
				RestorePiecesOut();

	            int x = myStack.size();

	            for (int i = 0; i < x; i+=2)
	            {
	                towers[myStack.pop()].addNodeWithMove(towers[myStack.pop()].removeNodeWithMove());
	            }

	            Dice1_played = false;
	            Dice2_played = false;
	            label_dice_1.setVisible(true);
	            label_dice_2.setVisible(true);
	            undo.setVisible(false);
	            cont.setVisible(false);
	            NumberOfTimesPlayed = 0;
	            DisableTowers();
	            myStack.clear();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
			}
			
			else if(ev.getSource() == roll)
			{
				RollDices();
				
				if(player)
				{
					EnableSuitablePieces();
				}
			}
			
			else if(ev.getSource() == initial_roll_white)
			{
				dice1 = r.nextInt(6);
				initial_roll_white.setVisible(false);
	            initial_dice_player_label_2.setVisible(true);
	            ToDice(dice1, (byte)2);
	            helper++; 
	            WhoPlaysFirst(); 
			}
			
			else if(ev.getSource() == initial_roll_black)
			{
				dice2 = r.nextInt(6);
				initial_roll_black.setVisible(false);
	            initial_dice_player_label_1.setVisible(true);
	            ToDice(dice2, (byte)3);
	            helper++; 
	            WhoPlaysFirst(); 
			}
			
			else if(ev.getSource() == player_button_1)
			{
				 DisableTowers();
				 PlayerButtonsToWhite();
				 player_button_1.setBackground(Color.YELLOW);

		         for (int i = 0; i < towers.length; i++)
		         {
		             if (towers[i].ContainsButton(player_button_1)) selected_piece = i;
		         }

		         //if the piece was on the captured stack, then display its position as 24.
		         if (selected_piece == 25) selected_piece = 24;

		         EnableSuitableTowers();
			}
			
			else if(ev.getSource() == player_button_2)
			{
				 DisableTowers();
				 PlayerButtonsToWhite();
				 player_button_2.setBackground(Color.YELLOW);

		         for (int i = 0; i < towers.length; i++)
		         {
		             if (towers[i].ContainsButton(player_button_2)) selected_piece = i;
		         }

		         //if the piece was on the captured stack, then display its position as 24.
		         if (selected_piece == 25) selected_piece = 24;

		         EnableSuitableTowers();
			}
			
			else if(ev.getSource() == player_button_3)
			{
				 DisableTowers();
				 PlayerButtonsToWhite();
				 player_button_3.setBackground(Color.YELLOW);

		         for (int i = 0; i < towers.length; i++)
		         {
		             if (towers[i].ContainsButton(player_button_3)) selected_piece = i;
		         }

		         //if the piece was on the captured stack, then display its position as 24.
		         if (selected_piece == 25) selected_piece = 24;

		         EnableSuitableTowers();
			}
			
			else if(ev.getSource() == player_button_4)
			{
				 DisableTowers();
				 PlayerButtonsToWhite();
				 player_button_4.setBackground(Color.YELLOW);

		         for (int i = 0; i < towers.length; i++)
		         {
		             if (towers[i].ContainsButton(player_button_4)) selected_piece = i;
		         }

		         //if the piece was on the captured stack, then display its position as 24.
		         if (selected_piece == 25) selected_piece = 24;

		         EnableSuitableTowers();
			}
			
			else if(ev.getSource() == player_button_5)
			{
				 DisableTowers();
				 PlayerButtonsToWhite();
				 player_button_5.setBackground(Color.YELLOW);

		         for (int i = 0; i < towers.length; i++)
		         {
		             if (towers[i].ContainsButton(player_button_5)) selected_piece = i;
		         }

		         //if the piece was on the captured stack, then display its position as 24.
		         if (selected_piece == 25) selected_piece = 24;

		         EnableSuitableTowers();
			}
			
			else if(ev.getSource() == player_button_6)
			{
				 DisableTowers();
				 PlayerButtonsToWhite();
				 player_button_6.setBackground(Color.YELLOW);

		         for (int i = 0; i < towers.length; i++)
		         {
		             if (towers[i].ContainsButton(player_button_6)) selected_piece = i;
		         }

		         //if the piece was on the captured stack, then display its position as 24.
		         if (selected_piece == 25) selected_piece = 24;

		         EnableSuitableTowers();
			}
			
			else if(ev.getSource() == player_button_7)
			{
				 DisableTowers();
				 PlayerButtonsToWhite();
				 player_button_7.setBackground(Color.YELLOW);

		         for (int i = 0; i < towers.length; i++)
		         {
		             if (towers[i].ContainsButton(player_button_7)) selected_piece = i;
		         }

		         //if the piece was on the captured stack, then display its position as 24.
		         if (selected_piece == 25) selected_piece = 24;

		         EnableSuitableTowers();
			}
			
			else if(ev.getSource() == player_button_8)
			{
				 DisableTowers();
				 PlayerButtonsToWhite();
				 player_button_8.setBackground(Color.YELLOW);

		         for (int i = 0; i < towers.length; i++)
		         {
		             if (towers[i].ContainsButton(player_button_8)) selected_piece = i;
		         }

		         //if the piece was on the captured stack, then display its position as 24.
		         if (selected_piece == 25) selected_piece = 24;

		         EnableSuitableTowers();
			}
			
			else if(ev.getSource() == player_button_9)
			{
				 DisableTowers();
				 PlayerButtonsToWhite();
				 player_button_9.setBackground(Color.YELLOW);

		         for (int i = 0; i < towers.length; i++)
		         {
		             if (towers[i].ContainsButton(player_button_9)) selected_piece = i;
		         }

		         //if the piece was on the captured stack, then display its position as 24.
		         if (selected_piece == 25) selected_piece = 24;

		         EnableSuitableTowers();
			}
			
			else if(ev.getSource() == player_button_10)
			{
				 DisableTowers();
				 PlayerButtonsToWhite();
				 player_button_10.setBackground(Color.YELLOW);

		         for (int i = 0; i < towers.length; i++)
		         {
		             if (towers[i].ContainsButton(player_button_10)) selected_piece = i;
		         }

		         //if the piece was on the captured stack, then display its position as 24.
		         if (selected_piece == 25) selected_piece = 24;

		         EnableSuitableTowers();
			}
			
			else if(ev.getSource() == player_button_11)
			{
				 DisableTowers();
				 PlayerButtonsToWhite();
				 player_button_11.setBackground(Color.YELLOW);

		         for (int i = 0; i < towers.length; i++)
		         {
		             if (towers[i].ContainsButton(player_button_11)) selected_piece = i;
		         }

		         //if the piece was on the captured stack, then display its position as 24.
		         if (selected_piece == 25) selected_piece = 24;

		         EnableSuitableTowers();
			}
			
			else if(ev.getSource() == player_button_12)
			{
				 DisableTowers();
				 PlayerButtonsToWhite();
				 player_button_12.setBackground(Color.YELLOW);

		         for (int i = 0; i < towers.length; i++)
		         {
		             if (towers[i].ContainsButton(player_button_12)) selected_piece = i;
		         }

		         //if the piece was on the captured stack, then display its position as 24.
		         if (selected_piece == 25) selected_piece = 24;

		         EnableSuitableTowers();
			}
			
			else if(ev.getSource() == player_button_13)
			{
				 DisableTowers();
				 PlayerButtonsToWhite();
				 player_button_13.setBackground(Color.YELLOW);

		         for (int i = 0; i < towers.length; i++)
		         {
		             if (towers[i].ContainsButton(player_button_13)) selected_piece = i;
		         }

		         //if the piece was on the captured stack, then display its position as 24.
		         if (selected_piece == 25) selected_piece = 24;

		         EnableSuitableTowers();
			}
			
			else if(ev.getSource() == player_button_14)
			{
				 DisableTowers();
				 PlayerButtonsToWhite();
				 player_button_14.setBackground(Color.YELLOW);

		         for (int i = 0; i < towers.length; i++)
		         {
		             if (towers[i].ContainsButton(player_button_14)) selected_piece = i;
		         }

		         //if the piece was on the captured stack, then display its position as 24.
		         if (selected_piece == 25) selected_piece = 24;

		         EnableSuitableTowers();
			}
			
			else if(ev.getSource() == player_button_15)
			{
				 DisableTowers();
				 PlayerButtonsToWhite();
				 player_button_15.setBackground(Color.YELLOW);

		         for (int i = 0; i < towers.length; i++)
		         {
		             if (towers[i].ContainsButton(player_button_15)) selected_piece = i;
		         }

		         //if the piece was on the captured stack, then display its position as 24.
		         if (selected_piece == 25) selected_piece = 24;

		         EnableSuitableTowers();
			}
		}
		
		
		
		
		@Override
		public void mouseClicked(MouseEvent ev)
		{
			if(ev.getSource() == label_tower_1)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(1))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[0].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(0);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[0].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(0);
	                }
	            }

	            CheckWhichDiceWasPlayed(1);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(0);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[0].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_2)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(2))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[1].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(1);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[1].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(1);
	                }
	            }

	            CheckWhichDiceWasPlayed(2);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(1);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[1].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_3)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(3))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[2].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(2);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[2].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(2);
	                }
	            }

	            CheckWhichDiceWasPlayed(3);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(2);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[2].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_4)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(4))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[3].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(3);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[3].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(3);
	                }
	            }

	            CheckWhichDiceWasPlayed(4);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(3);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[3].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_5)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(5))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[4].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(4);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[4].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(4);
	                }
	            }

	            CheckWhichDiceWasPlayed(5);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(4);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[4].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_6)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(6))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[5].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(5);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[5].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(5);
	                }
	            }

	            CheckWhichDiceWasPlayed(6);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(5);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[5].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_7)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(7))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[6].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(6);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[6].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(6);
	                }
	            }

	            CheckWhichDiceWasPlayed(7);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(6);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[6].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_8)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(8))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[7].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(7);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[7].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(7);
	                }
	            }

	            CheckWhichDiceWasPlayed(8);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(7);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[7].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_9)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(9))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[8].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(8);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[8].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(8);
	                }
	            }

	            CheckWhichDiceWasPlayed(9);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(8);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[8].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_10)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(10))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[9].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(9);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[9].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(9);
	                }
	            }

	            CheckWhichDiceWasPlayed(10);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(9);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[9].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_11)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(11))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[10].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(10);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[10].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(10);
	                }
	            }

	            CheckWhichDiceWasPlayed(11);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(10);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[10].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_12)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(12))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[11].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(11);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[11].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(11);
	                }
	            }

	            CheckWhichDiceWasPlayed(12);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(11);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[11].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_13)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(13))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[12].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(12);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[12].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(12);
	                }
	            }

	            CheckWhichDiceWasPlayed(13);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(12);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[12].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_14)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(14))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[13].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(13);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[13].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(13);
	                }
	            }

	            CheckWhichDiceWasPlayed(14);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(13);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[13].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_15)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(15))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[14].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(14);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[14].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(14);
	                }
	            }

	            CheckWhichDiceWasPlayed(15);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(14);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[14].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_16)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(16))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[15].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(15);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[15].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(15);
	                }
	            }

	            CheckWhichDiceWasPlayed(16);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(15);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[15].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_17)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(17))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[16].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(16);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[16].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(16);
	                }
	            }

	            CheckWhichDiceWasPlayed(17);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(16);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[16].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_18)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(18))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[17].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(17);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[17].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(17);
	                }
	            }

	            CheckWhichDiceWasPlayed(18);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(17);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[17].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_19)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(19))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[18].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(18);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[18].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(18);
	                }
	            }

	            CheckWhichDiceWasPlayed(19);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(18);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[18].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_20)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(20))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[19].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(19);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[19].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(19);
	                }
	            }

	            CheckWhichDiceWasPlayed(20);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(19);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[19].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_21)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(21))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[20].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(20);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[20].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(20);
	                }
	            }

	            CheckWhichDiceWasPlayed(21);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(20);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[20].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_22)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(22))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[21].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(21);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[21].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(21);
	                }
	            }

	            CheckWhichDiceWasPlayed(22);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(21);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[21].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_23)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(23))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[22].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(22);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[22].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(22);
	                }
	            }

	            CheckWhichDiceWasPlayed(23);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(22);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[22].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_24)
			{
				//if there was only one piece of the opponent player here, now it is captured...
	            if (OnePieceAlone(24))
	            {
	                if (player)
	                {
	                    towers[24].addNodeWithMove(towers[23].removeNodeWithMove());
	                    myStack.push(24);
	                    myStack.push(23);
	                }

	                else
	                {
	                    towers[25].addNodeWithMove(towers[23].removeNodeWithMove());
	                    myStack.push(25);
	                    myStack.push(23);
	                }
	            }

	            CheckWhichDiceWasPlayed(24);

	            //restore the position of the "clicked" piece.
	            if (selected_piece == -1) selected_piece = 24;
	            else if (selected_piece == 24) selected_piece = 25;

	            //add the current position of the node to the stack.
	            myStack.push(23);
	           
	            //add the position of the current tower to the stack.
	            myStack.push(selected_piece);
	            
	            //set the UNDO button as visible.
	            undo.setVisible(true);

	            towers[23].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
	            DisableTowers();
	            EnableSuitablePieces();
	            PlayerButtonsToWhite();
	            NextPlayer();
			}
			
			else if(ev.getSource() == label_tower_25)
			{
				 white_nodes_out++;
		         text27.setText(String.valueOf(white_nodes_out));

		         CheckWhichDiceWasPlayed(0);

		         //add the current position of the node to the stack.
		         myStack.push(26);

		         //add the position of the current tower to the stack.
		         myStack.push(selected_piece);

		         //set the UNDO button as visible.
		         undo.setVisible(true);

		         towers[26].addNodeWithMove(towers[selected_piece].removeNodeWithMove());
		         DisableTowers();
		         EnableSuitablePieces();
		         PlayerButtonsToWhite();
		         NextPlayer();
		         DisplayWinner();
			}
		}
		
		
		
		
		@Override
		public void mouseEntered(MouseEvent ev)
		{
			//Empty.
		}
		
		
		
		
		@Override
		public void mouseExited(MouseEvent ev)
		{
			//Empty.
		}
		
		
		
		
		@Override
		public void mouseReleased(MouseEvent ev)
		{
			//Empty.
		}
		
		
		
		
		@Override
		public void mousePressed(MouseEvent ev)
		{
			//Empty.
		}
	}
		  
}//class end.
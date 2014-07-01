import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class PreferencesWindow extends JFrame
{
	
	
	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> drop_down_1;
	private JComboBox<String> drop_down_2;
	private JComboBox<Integer> drop_down_3;
	
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	
	private JButton apply;
	private JButton cancel;
	
	private EventHandler handler;
	
	
	
	
	public PreferencesWindow()
	{
		super("PREFERENCES");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setSize(400,300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		handler = new EventHandler();
		
		label1 = new JLabel("Player's pieces colour : ");
		label2 = new JLabel("Computer's pieces colour : ");
		label3 = new JLabel("Minimax depth search : ");
		add(label1);
		add(label2);
		add(label3);
		label1.setBounds(10 , 50 , 200 , 20);
		label2.setBounds(10 , 100 , 200 , 20);
		label3.setBounds(10 , 150 , 200 , 20);
		
		drop_down_1 = new JComboBox<String>();
		drop_down_1.addItem("WHITE");
		drop_down_1.addItem("BLACK");
		drop_down_1.addItem("RED");
		drop_down_1.addItem("GREEN");
		drop_down_1.addItem("BLUE");
		drop_down_1.addItem("CYAN");
		drop_down_1.addItem("DARK GRAY");
		drop_down_1.addItem("ORANGE");
		drop_down_1.addItem("PINK");
		drop_down_1.addItem("MAGENTA");
		drop_down_1.setSelectedIndex(0);
		
		drop_down_2 = new JComboBox<String>();
		drop_down_2.addItem("WHITE");
		drop_down_2.addItem("BLACK");
		drop_down_2.addItem("RED");
		drop_down_2.addItem("GREEN");
		drop_down_2.addItem("BLUE");
		drop_down_2.addItem("CYAN");
		drop_down_2.addItem("DARK GRAY");
		drop_down_2.addItem("ORANGE");
		drop_down_2.addItem("PINK");
		drop_down_2.addItem("MAGENTA");
		drop_down_2.setSelectedIndex(1);
		
		drop_down_3 = new JComboBox<Integer>();
		drop_down_3.addItem(1);
		drop_down_3.addItem(3);
		drop_down_3.addItem(5);
		drop_down_3.addItem(7);
		drop_down_3.setSelectedIndex(0);
		
		add(drop_down_1);
		add(drop_down_2);
		add(drop_down_3);
		drop_down_1.setBounds(230,50,150,20);
		drop_down_2.setBounds(230,100,150,20);
		drop_down_3.setBounds(230,150,150,20);
		
		apply = new JButton("Apply Changes");
		cancel = new JButton("Cancel");
		add(apply);
		add(cancel);
		apply.setBounds(50 , 220 , 150 , 30);
		apply.addActionListener(handler);
		cancel.setBounds(230 , 220 , 100 , 30);
		cancel.addActionListener(handler);
	}
	
	
	
	
	
	
	public class EventHandler implements ActionListener
	{
		
		
		@Override
		public void actionPerformed(ActionEvent ev)
		{
			
			if(ev.getSource() == cancel)
			{
				dispose();
			}
			
			else if(ev.getSource() == apply)
			{
				try
				{
					File pref = new File("resources/preferences.txt");
					
					if(! (pref.exists()) ) 
					{
						pref.createNewFile();
					}
					
					pref.setWritable(true);
					
					String player_color     = (String)drop_down_1.getSelectedItem();
					String computer_color   = (String)drop_down_2.getSelectedItem();
					String depth            = String.valueOf(drop_down_3.getSelectedItem());
					
					if(player_color == computer_color)
					{
						JOptionPane.showMessageDialog(null , "Player and computer can not have the same colour for their pieces!!!" , "ERROR" , JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					BufferedWriter bw = new BufferedWriter(new FileWriter(pref));
					bw.write("player : "   +player_color + "\n");
					bw.write("computer : " +computer_color + "\n");
					bw.write("depth : "    +depth + "\n");
					bw.close();
					
					JOptionPane.showMessageDialog(null , "Game settings have been changed.\nThe changes will be applied in the next game." , "" , JOptionPane.INFORMATION_MESSAGE);
					pref.setWritable(false);
					dispose();
				}
				
				catch(Exception e)
				{
					System.err.println("ERROR : "+e.getMessage());
				}
				
			}//else if.
			
		}//action performed.
		
	}//inner class.
	
}//class end.
package GuiDrafts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;


public class ExitWindow extends JPanel implements ActionListener{

	private JLabel thanks;
	private JButton beginAgain, exit;
	
	public ExitWindow(){
		setLayout(new MigLayout("fill"));
		thanks = new JLabel("Thanks for completing the Discounting and Student Behavior Assessment.");
		add(thanks, "center, wrap 15px");
		beginAgain = new JButton("Begin Again");
		add(beginAgain, "center, split 2, gapright 50");
		exit = new JButton("Return to Main Menu");
		add(exit, "gapleft 50, center");
		exit.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == exit){
			((JFrame)this.getTopLevelAncestor()).dispose();
		}
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.add(new ExitWindow());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
package GuiDrafts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;


public class MultipleChoice extends JPanel{
	private JLabel question;
	private JToggleButton early, late;
	private JButton submit;
	private ButtonGroup bg;
	
	public MultipleChoice(){
		this.setLayout(new MigLayout("fill"));
		JLabel comment = new JLabel("**This item represents Multiple Choice**");
		add(comment, "center, wrap 15px");
		question = new JLabel("Would you rather");
		add(question, "center,wrap 15px");
		early = new JToggleButton("Receive x points now");
		bg = new ButtonGroup();
		bg.add(early);
		add(early, "center, wrap 15px, w 350px");
		late = new JToggleButton("Receive x+y points later");
		bg.add(late);
		add(late, "center, wrap 15px, w 350px");
		submit = new JButton("Confirm Choice");
		add(submit, "center, wrap 15px");
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.add(new MultipleChoice());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
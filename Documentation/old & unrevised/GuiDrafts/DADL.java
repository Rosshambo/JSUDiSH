package GuiDrafts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;


public class DADL extends JPanel{
	private JLabel question;
	private JToggleButton early, late;
	private JButton submit;
	private ButtonGroup bg;
	
	public DADL(){
		this.setLayout(new MigLayout("fill"));
		JLabel comment = new JLabel("**This item represents Decreasing Adjustment and Double Limit**");
		add(comment, "center, wrap 15px");
		question = new JLabel("Would you rather");
		add(question, "center,wrap 15px");
		early = new JToggleButton("Receive x points now");
		bg = new ButtonGroup();
		bg.add(early);
		add(early, "center, split 2, gapright 20px");
		late = new JToggleButton("Receive x+y points later");
		bg.add(late);
		add(late, "gapleft 20px, center, wrap 15px");
		submit = new JButton("Confirm Choice");
		add(submit, "center, wrap 15px");
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.add(new DADL());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
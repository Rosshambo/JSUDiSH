package GuiDrafts;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;

public class DLInstruct extends JPanel{
	
	private JTextArea area;
	private JButton button;
	private JLabel label;
	
	public DLInstruct(){
		setLayout(new MigLayout("fill"));
		label = new JLabel("These instructions will be shown the first time each method is introduced during the experiment");
		add(label, "center, wrap");
		area = new JTextArea("The next screen will present you with a question and two scenarios to choose from. Once you have read all of the scenarios select your answer. When you are sure of your answer press the confirm choice button at the bottom of the screen. You will repeat this process until the assessment is complete.", 5, 50);
		add(area, "center, wrap");
		area.setEditable(false);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setBackground(this.getBackground());
		button = new JButton("Next");
		add(button, "center");
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.add(new DLInstruct());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
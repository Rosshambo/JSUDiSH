package GuiDrafts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.text.*;

public class RealTimeInstruct extends JPanel{
	private JTextArea area;
	private JButton button;
	
	public RealTimeInstruct(){
		setLayout(new MigLayout("fill"));
		area = new JTextArea("You will be presented with some text to begin reading aloud, the longer you read the more points you will earn. When you feel that you have reached your limit select the quit session button at the bottom of the screen. ", 3, 50);
		add(area, "span 5, center, wrap");
		area.setEditable(false);
		area.setLineWrap(true);
		area.setBackground(this.getBackground());
		button = new JButton("Next");
		add(button, "center");
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.add(new RealTimeInstruct());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
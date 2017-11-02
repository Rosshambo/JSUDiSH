package GuiDrafts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.text.*;

public class RealTime extends JPanel{
	private JSlider slider;
	private JTextArea textPane;
	private JButton button;
	private JButton pause;
	private JLabel timeLabel, pointsEarned;
	private JLabel exactPoints;
	private JPanel slidePane;
	
	public RealTime(){
		setLayout(new MigLayout());
		slidePane = new JPanel(new MigLayout("fill"));
		JSlider slider = new JSlider(JSlider.VERTICAL, 0, 10, 0);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		pointsEarned = new JLabel("Points Earned: ##");
		textPane = new JTextArea("readable content");
		textPane.setEditable(false);
		JScrollPane areaScrollPane = new JScrollPane(textPane);
		areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(500, 250));
		add(areaScrollPane, "center, span 3, wrap");
		timeLabel = new JLabel("Time elapsed: 00:00:00");
		add(timeLabel, "center, gaptop 15px, gapbottom 15px, split 2, gapright 15px");
		add(pointsEarned, "center, wrap");
		slidePane.add(slider, "center, gapright 25px, gapleft 25px, wrap");
		exactPoints = new JLabel("Maximum Points Possible: ##");
		slidePane.add(exactPoints, "center, wrap");
		add(slidePane, "west");
		pause = new JButton("Pause Reading");
		button = new JButton("Stop reading and take points earned.");
		add(pause, "center, wrap, gapbottom 15px, w 200px");
		add(button, "center, wrap, gapbottom 15px, w 200px");
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.add(new RealTime());
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
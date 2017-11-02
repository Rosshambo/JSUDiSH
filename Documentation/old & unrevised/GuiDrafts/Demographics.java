package GuiDrafts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.miginfocom.swing.MigLayout;



public class Demographics extends JPanel{

	private JLabel nameLabel, studentNumLabel, genderLabel, raceLabel, example;
	private JButton submit;
	private JComboBox ethnicityBox, agesBox;
	private JRadioButton maleButton, femaleButton;
	private JTextField firstName, lastName, studentNumField;
	private String[] ages;
	
	public Demographics(){
		createAges();
		createGUI();
		this.setLayout(new MigLayout("fill"));
		add(nameLabel, "gap para");
		add(firstName, "gap para");
		add(lastName, "gap para, wrap");
		add(studentNumLabel, "gap para");
		add(studentNumField, "gap para");
		add(example, "gap para, wrap");
		add(genderLabel, "span 1 1, gap para");
		ButtonGroup group = new ButtonGroup();
		group.add(maleButton);
		group.add(femaleButton);
		add(maleButton, "gap para, split 2");
		add(femaleButton, "gap para, wrap");
		add(raceLabel, "gap para");
		add(ethnicityBox, "gap para, wrap");
		add(new JLabel("Age:"), "gap para");
		add(agesBox, "gap para, wrap");
		add(submit, "cell 3 7");
	}
	
	public void createAges(){
		ages = new String[81];
		for(int i = 0; i < 81; i++){
			ages[i] = Integer.toString(i+10);
		}
	}

	public void createGUI(){
		nameLabel = new JLabel("Name:");
		studentNumLabel = new JLabel("Student Number:");
		genderLabel = new JLabel("Gender:");
		raceLabel = new JLabel("Race:");
		submit = new JButton("Submit");
		String[] ethnics = { "Black or African American", "Hispanic or Latino", "Asian or Pacific Islander", "American Indian", "Alaskan Native", "Other", "White" };
		ethnicityBox = new JComboBox(ethnics);
		maleButton = new JRadioButton("Male");
		femaleButton = new JRadioButton("Female");
		firstName = new JTextField("First Name", 15);
		lastName = new JTextField("Last name", 15);
		studentNumField = new JTextField(9);
		example = new JLabel("ex: 000-00-0000");
		agesBox = new JComboBox(ages);
		agesBox.setSelectedIndex(9);
	}	
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.add(new Demographics());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
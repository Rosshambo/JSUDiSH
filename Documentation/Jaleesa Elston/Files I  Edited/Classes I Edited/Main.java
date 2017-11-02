package edu.jsu.dish;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import java.lang.NullPointerException;

//import org.kohsuke.args4j.CmdLineParser;
//import org.kohsuke.args4j.CmdLineException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import org.xml.sax.XMLReader;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

import net.miginfocom.swing.MigLayout;

public class Main extends JFrame implements ActionListener, KeyListener, FocusListener{
	private JMenuBar menuBar;
	private JMenu file, admin, help, logOutMenu;
	private JMenuItem load, exit, userAdmin, userManual, logOut;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton submitButton;
	private JPanel loadPanel, scrollPanePanel;
	private JScrollPane scrollPane;
	private Experiment currentExperiment;
	private ExperimentUI experimentUI;
	public static JPanel mainContentPanel;  
	private int verify;
	private DBUI db;
	private boolean isMainContentPanelEmpty;
	private JPanel panel;
	private boolean isLogOutMenuSet;
	private database Database;
	private ExperimentLoader experimentLoader;
	
	public Main() {
		Database = new database();
		this.setLayout(new MigLayout("fill"));
		createMenu();
		setTitle("Discounting in Study Habits Experiment System");
		loadPanel = new JPanel();
		loadPanel.setLayout(new MigLayout("fill"));
		JLabel hello = new JLabel("Welcome to the Discounting in Study Habits Experiment System.");
		loadPanel.add(hello, "wrap, gapbottom para");
		loadPanel.add(new JLabel("Please log in."), "gapbottom para, center, wrap");
		loadPanel.add(new JLabel("Username: "), "split 2, center");
		usernameField = new JTextField(8);
		usernameField.addFocusListener(this);
		usernameField.addActionListener(this);
		loadPanel.add(usernameField, "wrap, gapbottom para");
		loadPanel.add(new JLabel("Password: "), "split 2, center");
		passwordField = new JPasswordField(8);
		passwordField.addActionListener(this);
		passwordField.addFocusListener(this);
		loadPanel.add(passwordField, "wrap, gapbottom para");
		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);
		submitButton.addKeyListener(this);
		usernameField.addKeyListener(this);
		passwordField.addKeyListener(this);
		loadPanel.add(submitButton, "center");
		scrollPanePanel = new JPanel(new MigLayout("fill"));
		scrollPanePanel.add(loadPanel);
		scrollPane = new JScrollPane(scrollPanePanel);
		scrollPane.setBorder(null);
		add(scrollPane, "center, wrap");
		isMainContentPanelEmpty = true;
		mainContentPanel = new JPanel(new MigLayout("fill"));
		loadPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED), BorderFactory.createLineBorder(Color.BLACK)));
		panel = new JPanel();
		ImageIcon icon = new ImageIcon("./JacksonvilleState.png");
		JLabel label = new JLabel();
		label.setIcon(icon);
		panel.add(label);
		this.add(panel, "center, south");
		scrollPanePanel.setBackground(Color.BLACK);
		mainContentPanel.setBackground(Color.DARK_GRAY);
		isLogOutMenuSet = false;
	}
	public void createMenu() {
		menuBar = new JMenuBar();
		userAdmin = new JMenuItem("User Administration");
		admin = new JMenu("Administration");
		admin.add(userAdmin);
		userAdmin.addActionListener(this);
		file = new JMenu("File");
		load = new JMenuItem("Load");
		load.addActionListener(this);
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		menuBar.add(file);
		help = new JMenu("Help");
		userManual = new JMenuItem("User's Manual");
		help.add(userManual);
		menuBar.add(help);
		userManual.addActionListener(this);
		logOut = new JMenuItem("Return to Log In");
		logOut.addActionListener(this);
		logOutMenu = new JMenu("Log Out");
		logOutMenu.add(logOut);
	}
	
	public void focusGained(FocusEvent e){
		if(e.getSource() == usernameField){
			usernameField.selectAll();
		}
		else if(e.getSource() == passwordField){
			passwordField.selectAll();
		}
	}
	
	public void focusLost(FocusEvent e) { }
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			verify = -1;
			String passTemp = new String(passwordField.getPassword());
			if (!usernameField.getText().equals("") && !passTemp.equals("")) {
				verify = Database.verifyLogin(usernameField.getText(), new String(passwordField.getPassword()));
				//Experimenter verified
				if (verify == 1) {		
					this.setJMenuBar(menuBar);
					if (isLogOutMenuSet == true) {
						menuBar.remove(logOutMenu);
						isLogOutMenuSet = false;
					}
					scrollPanePanel.remove(loadPanel);
					scrollPane.remove(scrollPanePanel);
					this.getContentPane().remove(scrollPane);
					this.validate();
				}
				//Admin verified
				else if (verify == 2) {
					menuBar.remove(help);
					if (isLogOutMenuSet == true) {
						menuBar.remove(logOutMenu);
						isLogOutMenuSet = false;
					}
					menuBar.add(admin);
					menuBar.add(help);
					file.add(load);
					file.addSeparator();
					file.add(exit);
					this.setJMenuBar(menuBar);
					scrollPanePanel.remove(loadPanel);
					scrollPane.remove(scrollPanePanel);
					this.getContentPane().remove(scrollPane);
					this.validate();
				}
				//Not verified
				else { JOptionPane.showMessageDialog(this, "The password is invalid.", "Invalid Password!", JOptionPane.ERROR_MESSAGE); }
			}
			else if (usernameField.getText().equals("") && passTemp.equals("")) { JOptionPane.showMessageDialog(this, "The username and password are invalid.", "Invalid Username and Password", JOptionPane.ERROR_MESSAGE); }
			else if (usernameField.getText().equals("")) { JOptionPane.showMessageDialog(this, "The username is invalid.", "Invalid Username", JOptionPane.ERROR_MESSAGE); }
			else if (passTemp.equals("")) { JOptionPane.showMessageDialog(this, "The password is invalid.", "Invalid Password", JOptionPane.ERROR_MESSAGE); }
			usernameField.setText("");
			passwordField.setText("");
			verify = -1;
		}
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitButton) {
			verify = -1;
			String passTemp = new String(passwordField.getPassword());
			if (!usernameField.getText().equals("") && !passTemp.equals("")) {
				verify = Database.verifyLogin(usernameField.getText(), new String(passwordField.getPassword()));
				//Experimenter verified
				if (verify == 1) {	
					if (isLogOutMenuSet == true) {
						menuBar.remove(logOutMenu);
						isLogOutMenuSet = false;
					}
					this.setJMenuBar(menuBar);
					scrollPanePanel.remove(loadPanel);
					scrollPane.remove(scrollPanePanel);
					this.getContentPane().remove(scrollPane);
					this.validate();
				}
				//Admin verified
				else if (verify == 2) {
					if (isLogOutMenuSet == true) {
						menuBar.remove(logOutMenu);
						isLogOutMenuSet = false;
					}
					menuBar.add(admin);
					this.setJMenuBar(menuBar);
					scrollPanePanel.remove(loadPanel);
					remove(scrollPane);
					validate();
				}
				//Not verified
				else { JOptionPane.showMessageDialog(this, "The password is invalid.", "Invalid Password!", JOptionPane.ERROR_MESSAGE); }
			}
			else if (usernameField.getText().equals("") && passTemp.equals("")) { JOptionPane.showMessageDialog(this, "The username and password are invalid.", "Invalid Username and Password", JOptionPane.ERROR_MESSAGE); }
			else if (usernameField.getText().equals("")) { JOptionPane.showMessageDialog(this, "The username is invalid.", "Invalid Username", JOptionPane.ERROR_MESSAGE); }
			else if (passTemp.equals("")) { JOptionPane.showMessageDialog(this, "The password is invalid.", "Invalid Password", JOptionPane.ERROR_MESSAGE); }
			usernameField.setText("");
			passwordField.setText("");
			verify = -1;
		}
		else if(e.getSource() == userManual){ JOptionPane.showMessageDialog(this, "Coming soon!", "Coming soon!", JOptionPane.ERROR_MESSAGE); }
		else if (e.getSource() == load) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter( "XML Files", "xml");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				String fileName = String.valueOf(chooser.getSelectedFile());
				//System.out.println(fileName);
				experimentLoader = new ExperimentLoader (fileName);
				currentExperiment = experimentLoader.getExperiment();
				//System.out.println("currentExperiment is:" + currentExperiment);
				experimentUI = new ExperimentUI(currentExperiment);
				try {
					if (isMainContentPanelEmpty == false) {
						mainContentPanel.removeAll();
						mainContentPanel.add(experimentUI, "center");
						mainContentPanel.validate();
					}
					else {
						isMainContentPanelEmpty = false;
						mainContentPanel.add(experimentUI, "center");
					}
					
					this.validate();
				}
				catch(NullPointerException ex){System.out.println("The experiment was not passed to me correctly,I can not add null to the mainContentPanel"); }
				this.add(mainContentPanel, "center");
				menuBar.remove(admin);
				file.remove(load);
				if (isLogOutMenuSet == true) {
					menuBar.remove(logOutMenu);
					isLogOutMenuSet = false;
				}
				mainContentPanel.validate();
				this.validate();
			}
		}
		else if (e.getSource() == exit) { System.exit(0); }
		else if (e.getSource() == userAdmin) {
			db = new DBUI();
			this.getContentPane().removeAll();
			menuBar.remove(admin);
			menuBar.remove(help);
			menuBar.add(logOutMenu);
			menuBar.add(help);
			isLogOutMenuSet = true;
			getContentPane().add(db, "center, wrap, gap para");
			this.validate();
		}
		else if (e.getSource() == logOut) {
			reset();
		}
	}
	
	public void reset() {
		if (isLogOutMenuSet == true) {
			menuBar.remove(logOutMenu);
			isLogOutMenuSet = false;
		}
		this.getContentPane().removeAll();
		this.setJMenuBar(null);
		scrollPanePanel.add(loadPanel);
		getContentPane().add(scrollPane, "center");
		this.add(panel, "center, south");
		this.validate();
	}
		
	public static void main(String[] args) {
		Main win = new Main();
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.pack();
		//win.setLocation(0,0); 
		win.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		win.setVisible(true);
	}
}
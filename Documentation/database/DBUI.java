package edu.jsu.dish;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;

public class DBUI extends JPanel implements ActionListener, ListSelectionListener {
	public database Database;
  
	//DECLARE CONTROLS & LABELS
	public static JLabel usernameT, passwordT, confirmpasswordT, firstnameT, lastnameT, departmentT, privilegeT, hide, show, queryname, querynamepanelL;
	public static JTextField username, password, confirmpassword, firstname, lastname, department, querynameT, querynamepanelT, valueField;
	public static int petName, i, sindex;
	public static String[] result;	
	public static JList userlist, tl, querylist, optionsbox1, optionsbox2;
	private JButton newuser, removeuser, addtolib, copycode, runquery, addtolibrary, left, right, addmorefilters, adduser, hidecode;
	private JRadioButton all, some;
	private static ListSelectionModel tlmodel, op1model, op2model, qmodel, lsm, usermodel;
	private static DefaultListModel model, querymodel, option1, option2;
	public static JComboBox priv;
	public static JTextArea textarea, cvbox;
	public static String multiple = "";
	public static JPanel cvpane = new JPanel(new MigLayout("fill"));  
	public static JPanel resultsfilter = new JPanel(new MigLayout("fill"));  
	public static JScrollPane filterscrollpane;
	 
	public static filtersome filster;
		
	public DBUI() {
		Database = new database();
		Database.connect();
		
		JTabbedPane tab = new JTabbedPane();
		this.setLayout(new MigLayout("fill, center"));
		this.add(tab, "center");
		tab.setBorder(null);
		
		//**************************************************************************
		//TAB 1 ********************************************************************
		//**************************************************************************
		JPanel tab1 = new JPanel(new MigLayout("fill"));
		JPanel userdetails = new JPanel(new MigLayout("fill"));
		
		//USER DETAILS PANEL CONTROL
		usernameT = new JLabel("Username: ");
		username = new JTextField();
		
		passwordT = new JLabel("Password: ");
		password = new JTextField();
		
		confirmpasswordT = new JLabel("Confirm Password: ");
		confirmpassword = new JTextField();
		
		firstnameT = new JLabel("First Name: ");
		firstname = new JTextField();
		
		lastnameT = new JLabel("Last Name: ");
		lastname = new JTextField();
		
		departmentT = new JLabel("Department: ");
		department = new JTextField();
		
		privilegeT = new JLabel("Privilege: ");
		String[] privilege = { "Experimenter", "Administrator" };

		priv = new JComboBox(privilege);
		priv.setSelectedIndex(0);
		priv.addActionListener(this);
		
		adduser = new JButton("Add User");
		adduser.addActionListener(this);
		
		//ADD USER DETAILS PANEL CONTROLS
		userdetails.add(usernameT, "right");
		userdetails.add(username, "wrap, w 120!");
		userdetails.add(passwordT, "right");
		userdetails.add(password, "wrap, w 120!");
		userdetails.add(confirmpasswordT, "right");
		userdetails.add(confirmpassword, "wrap, w 120!");
		userdetails.add(firstnameT, "right");
		userdetails.add(firstname, "wrap, w 120!");
		userdetails.add(lastnameT, "right");
		userdetails.add(lastname, "wrap, w 120!");
		userdetails.add(departmentT, "right");
		userdetails.add(department, "wrap, w 120!");
		userdetails.add(privilegeT, "right");
		userdetails.add(priv, "wrap, w 120!");
		userdetails.add(adduser, "center, span 2, w 150!");
		
		//VIEW USERS PANEL
		JPanel viewusers = new JPanel(new GridLayout(3,1));
		
		//USER LIST-------------------------------------------------------------------
		result = Database.getUsers().split(",");
		model = new DefaultListModel();
		for (int i = 0; i < result.length; i++) { model.addElement(result[i]); }
		userlist = new JList(model);
		userlist.setVisibleRowCount(4);
		Font displayFont = new Font("Serif", Font.BOLD, 18);
		userlist.setFont(displayFont);
		JScrollPane listPane = new JScrollPane(userlist);
		ListSelectionModel model;
		usermodel = userlist.getSelectionModel();
		usermodel.addListSelectionListener(this);	
		//END USER LIST ---------------------------------------------------------------
		
		//NEW USER BUTTON ACTION---------------------------------------------
		newuser = new JButton("New User");
		newuser.addActionListener(this);
		//END NEW USER BUTTON ACTION-----------------------------------------
			
		//REMOVE USER BUTTON ACTION-------------------------------------------
		removeuser = new JButton("Remove User");
		removeuser.addActionListener(this);
		//END OF REMOVE USER BUTTON ACTION------------------------------------
		

		//ADD USER DETAILS PANEL ------------------------------------------------------------------
		viewusers.add(listPane);
		JPanel userbuttons = new JPanel(new MigLayout("fill"));
		userbuttons.add(newuser, "w 90!");
		userbuttons.add(newuser, "w 90!");
		userbuttons.add(removeuser, "w 110!");
		viewusers.add(userbuttons, "w 50!");
		//--------------------------------------------------------------------------------------------------
		
		//ADD USER DETAILS PANEL TO TAB1 PANEL
		tab1.add(viewusers, "w 300!, split 2");
		tab1.add(userdetails, "top, left, w 300!");
		tab.add("User Admin", tab1);
		//==========================================================================
		
		
		//**************************************************************************
		//TAB 2 ********************************************************************
		//**************************************************************************
		JPanel tab2 = new JPanel(new MigLayout("fill"));
		
		//DEFINE PANELS
		JPanel optionsboxpanel1 = new JPanel(new MigLayout("fill"));
		JPanel optionsboxpanel2 = new JPanel(new MigLayout("fill"));    
		JPanel leftright = new JPanel(new MigLayout("fill"));
		
		//ADD LEFT LISTBOX==========================================
		option1 = new DefaultListModel();
		//ADD JLIST ITEMS------------------------
		  option1.addElement("First Name");
		  option1.addElement("Last Name");
		  option1.addElement("Age");
		  option1.addElement("Gender");
		  option1.addElement("Education Level");
		  option1.addElement("Race");
		//---------------------------------------
		optionsbox1 = new JList(option1);
		optionsbox1.setVisibleRowCount(4);
		optionsbox1.setFont(displayFont);
		ListSelectionModel op1model;
		op1model = optionsbox1.getSelectionModel();
		op1model.addListSelectionListener(this);
		JScrollPane optionsbox1scrollpane = new JScrollPane(optionsbox1);
		hide = new JLabel("Hide");
		optionsboxpanel1.add(hide, "wrap");
		optionsboxpanel1.add(optionsbox1scrollpane, "w 200!, h 400!, split 2, top");
		//END OF LEFT LISTBOX=======================================
		
		//ADD RIGHT LISTBOX==========================================
		option2 = new DefaultListModel();
		optionsbox2 = new JList(option2);
		optionsbox2.setVisibleRowCount(4);
		optionsbox2.setFont(displayFont);
		JScrollPane optionsbox2scrollpane = new JScrollPane(optionsbox2);
		op2model = optionsbox2.getSelectionModel();
		op2model.addListSelectionListener(this);
		show = new JLabel("Show");
		optionsboxpanel2.add(show, "wrap");
		optionsboxpanel2.add(optionsbox2scrollpane, "w 200!, h 400!, split 2, top");
		//END OF RIGHT LISTBOX=======================================
		 
		//RESULTS FILTER PANE------------------------------------------------
		JPanel filterallsome = new JPanel(new MigLayout("fill"));
		ButtonGroup group2 = new ButtonGroup();
		all = new JRadioButton("Retrieve All The Results", true);
		group2.add(all);
		filterallsome.add(all);
		some = new JRadioButton("Filter The Results", false);
		group2.add(some);
		filterallsome.add(some);
		//-------------------------------------------------------------------------------

		//===========================================================================================
		//DISABLE OR ENABLE FILTERS
		all.addActionListener(this);
		some.addActionListener(this);
		//END OF ENABLED/DISABLE FILTERS
		//=========================================================================================
	   
		addmorefilters = new JButton("Add More Filters");
		addmorefilters.addActionListener(this);
		
		//ADD PANES TO RESULTS FILTER
		tab2.add(filterallsome, "wrap");

		runquery = new JButton("Run Query");
		
		addtolibrary = new JButton("Add Query To Library");
		addtolibrary.addActionListener(this);
			
		queryname = new JLabel("Query Name: ");
		querynameT = new JTextField();
		
		//MOVE ITEMS LEFT-----------------------------------------
		left = new JButton("<<");
		left.addActionListener(this);
		//END OF MOVE ITEMS LEFT----------------------------------
		
		leftright.add(left,"wrap, left");
		
		//MOVE ITEMS RIGHT-----------------------------------------
		right = new JButton(">>");
		right.addActionListener(this);
		//END OF MOVE ITEMS RIGHT----------------------------------
		
		leftright.add(right, "left");  
		//END OF LEFT RIGHT PANE
		
		filterscrollpane = new JScrollPane(resultsfilter);
		
		//ADD TAB 2 TO THE TABBED PANE
		tab.add("Query Builder", tab2);
		
		//ADD PANELS TO TAB 2
		tab2.add(addmorefilters, "wrap");
		tab2.add(filterscrollpane, "w 350!, h 300!, top");
		addmorefilters.setVisible(false);
		filterscrollpane.setVisible(false);
		tab2.add(optionsboxpanel1, "w 200!, h 400!, split 2, top");
		tab2.add(leftright, "h 100!, top");
		tab2.add(optionsboxpanel2, "w 200!, h 400!, split 2, top, wrap");
		tab2.add(runquery, "split 2");
		tab2.add(addtolibrary, "left");
		tab2.add(queryname, "left, split 2");
		tab2.add(querynameT, "left, w 200!");
		//==========================================================================
		
		
		
		//**************************************************************************
		//TAB 3 ********************************************************************
		//**************************************************************************
		JPanel tab3 = new JPanel(new MigLayout("fill"));
		JPanel querynamepanel = new JPanel(new MigLayout("fill"));  
		JPanel querybuttons = new JPanel(new MigLayout("fill"));      
		
		//ADD TAB 3 TO THE TABBED PANE
		tab.add("Advanced Query Builder", tab3);
		
		
		//TEXTAREA
		textarea = new JTextArea();
		//================================
		
		//QUERYNAME
		querynamepanelL = new JLabel("Query Name: ");
		querynamepanelT = new JTextField();
		querynamepanel.add(querynamepanelL, "w 100!");
		querynamepanel.add(querynamepanelT, "w 150!");
		//===================================
		
		//QUERY BUTTONS
		JButton runqueryb;
		runqueryb = new JButton("Run Query");
		
		//START OF ADD QUERY TO LIBRARY--------------------------------
		addtolib = new JButton("Add Query To Library");
		addtolib.addActionListener(this);
		//END OF ADD TO LIBRARY
		
		querybuttons.add(runqueryb, "split 2");
		querybuttons.add(addtolib);
		//===============================================
		
		//ADD PANELS TO TAB 3
		tab3.add(textarea, "w 600!, h 150!, top, left, wrap");
		tab3.add(querynamepanel, "wrap");
		tab3.add(querybuttons);
		//===============================================    
		
		
		//**************************************************************************
		//TAB 4 ********************************************************************
		//**************************************************************************
		JPanel tab4 = new JPanel(new MigLayout("fill"));
		JPanel qlpane = new JPanel(new MigLayout("fill"));   
		JPanel cbpane = new JPanel(new MigLayout("fill"));   
		JPanel lbpane = new JPanel(new MigLayout("fill"));   
		
		//ADD TAB 4 TO THE TABBED PANE
		tab.add("Query Library", tab4);
		//QUERYLISTBOX PANE====================================
		

	   //ADD QUERYLIST==========================================
		querymodel = new DefaultListModel();
		
		result = Database.getQueries().split(",");
		//ADD QUERY ITEMS------------------------
		for (i = 0; i < result.length; i++){
		  querymodel.addElement(result[i]);
		}
		//---------------------------------------

		querylist = new JList(querymodel);
		querylist.setVisibleRowCount(4);
		querylist.setFont(displayFont);
		
		//ListSelectionModel qmodel;
		qmodel = querylist.getSelectionModel();
		qmodel.addListSelectionListener(this);
		JScrollPane qscrollpane = new JScrollPane(querylist);
		qlpane.add(qscrollpane, "w 600!, h 150!, split 2, top");
		//END OF QUERYLIST=======================================

		//==================================================
		//CODE BUTTONS PANE
		hidecode = new JButton("Show Code");
		hidecode.addActionListener(this);
		copycode = new JButton("Copy Code");
		copycode.addActionListener(this);
		cbpane.add(hidecode, "center, w 100!");
		cbpane.add(copycode, "center, w 100!, wrap");
		//==================================================
		
		//CODE VIEWER PANE
		cvbox = new JTextArea();
		//==================================================
		
		//LIBRARY BUTTONS PANE
		JButton runquery3;
		runquery3 = new JButton("Run Query");
		JButton deletequery;
		deletequery = new JButton("Delete Query From Library");
		lbpane.add(runquery3, "split 2");
		lbpane.add(deletequery);
		//==================================================
		
		tab4.add(qlpane, "wrap");
		tab4.add(cbpane, "wrap");
		tab4.add(cvpane, "wrap");
		tab4.add(lbpane, "wrap");
		//==========================================================================
		
		
		//**************************************************************************
		//TAB 5 ********************************************************************
		//**************************************************************************
		JPanel tab5 = new JPanel(new MigLayout("fill")); 

		//ADD TAB 5 TO THE TABBED PANE
		tab.add("Results", tab5); 
  }

  
	//ACTION LISTENERS==================================================================================================================================
	public void actionPerformed (ActionEvent event) {
		if (event.getSource() == priv) {
			JComboBox cb = (JComboBox)event.getSource();
			petName = cb.getSelectedIndex();
			if(petName==0){ petName=1; }
			else if(petName==1){ petName=2; }
		}
		else if (event.getSource() == adduser) {
			if(adduser.getText().equals("Add User")){
				//CONFIRM THE PASSWORD
				if(confirmpassword.getText().equals(password.getText()) == true){
					Database.insert("INSERT INTO investigator VALUES('" + Integer.toString(Database.getLastId("investigator")+1) + "','" + username.getText() + "','" + password.getText() + "','" + firstname.getText() + "','" + lastname.getText() + "','" + department.getText() + "','" + petName + "')");
					username.setText("");
					password.setText("");
					confirmpassword.setText("");
					firstname.setText("");
					lastname.setText("");
					department.setText("");
				} 
				else {
					JOptionPane.showMessageDialog(null, "Password Does Not Match!", "Password Does Not Match!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if (adduser.getText()=="Update User"){
				if (confirmpassword.getText().equals(password.getText()) == true){
					result = Database.getSelectedUser(model.getElementAt(sindex).toString()).split(",");
					petName = priv.getSelectedIndex();
					if (petName==0){ petName=1; }
					else if (petName==1){ petName=2; }
					Database.insert("UPDATE investigator SET username = '" + username.getText() + "', password = '" + password.getText() + "', first_name = '" + firstname.getText() + "', last_name = '" + lastname.getText() + "', department = '" + department.getText() + "', privilege = '" + petName + "' WHERE id = '" + result[0] + "'");
					username.setText("");
					password.setText("");
					confirmpassword.setText("");
					firstname.setText("");
					lastname.setText("");
					department.setText("");
				} 
				else{
					JOptionPane.showMessageDialog(null, "Password Does Not Match!", "Password Does Not Match!", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			//REMOVE JLIST ITEMS------------------------
			int size = model.getSize();
			for (i = 0; i < size; i++){
				model.removeElementAt(0);
			}
			//---------------------------------------
			result = Database.getUsers().split(",");
			
			//ADD JLIST ITEMS------------------------
			for (i = 0; i < result.length; i++){
			  model.addElement(result[i]);
			}
			//---------------------------------------
		}
		else if (event.getSource() == newuser) {
			username.setText("");
			password.setText("");
			firstname.setText("");
			lastname.setText("");
			department.setText("");
			priv.setSelectedIndex(1);
			adduser.setText("Add User");
		}
		else if (event.getSource() == removeuser) {
			result = Database.getSelectedUser(model.getElementAt(sindex).toString()).split(",");
			model.removeElementAt(sindex);
			Database.insert("DELETE FROM investigator WHERE username = '" + result[1] + "'");
			
			//CLEAR THE FIELDS
			username.setText("");
			password.setText("");
			firstname.setText("");
			lastname.setText("");
			department.setText("");
			priv.setSelectedIndex(1);
			adduser.setText("Add User");
			
			//REMOVE JLIST ITEMS------------------------
			int size = model.getSize();
			for (i = 0; i < size; i++){
				model.removeElementAt(0);
			}
			//---------------------------------------
			result = Database.getUsers().split(",");
			
			//ADD JLIST ITEMS------------------------
			for (i = 0; i < result.length; i++){
			  model.addElement(result[i]);
			}
			//---------------------------------------
		}
		else if (event.getSource() == all) {
			addmorefilters.setVisible(false);
			filterscrollpane.setVisible(false);
		}
		else if (event.getSource() == hidecode) {
			if(hidecode.getText() == "Show Code"){
			  hidecode.setText("Hide Code"); 
			  cvpane.add(cvbox, "w 600!, h 150!");
			}
			else if(hidecode.getText() == "Hide Code"){
				hidecode.setText("Show Code");
				cvpane.remove(cvbox);
			}
		}
		else if (event.getSource() == addtolib) {
			Database.insert("INSERT INTO querylibrary VALUES('" + Integer.toString(Database.getLastId("querylibrary")+1) + "','" + querynamepanelT.getText() + "','" + textarea.getText() + "')");
		}
		else if (event.getSource() == copycode) {
			cvbox.selectAll();
			cvbox.copy();
		}
		else if (event.getSource() == some) {
			addmorefilters.setVisible(true);
			filterscrollpane.setVisible(true);
		}
		else if (event.getSource() == addmorefilters) {
			//maybe make this an array- java.util.ArrayList maybe
			filster = new filtersome();
			resultsfilter.add(filtersome.filtersome, "wrap, top");
			resultsfilter.updateUI();
		}
		else if (event.getSource() == addtolibrary) {
			String querycode;
			querycode = "SELECT ";
			int size = option2.getSize();
			
			//CONCATENATE SELECTED CRITERIA------------------------
			for (int i = 0; i < size; i++) {
			  querycode = querycode + option2.getElementAt(i).toString() + ",";
				querycode=querycode+"FROM participant WHERE " + filtersome.optionsl.getSelectedItem().toString() + " " + filtersome.ops.getSelectedItem().toString() + " @" + filtersome.cvalue.getSelectedItem().toString() + "@"; 
				//---------------------------------------
				Database.insert("INSERT INTO querylibrary VALUES('" + "" + Database.getLastId("querylibrary")+1 + /*Integer.toString(database.getLastId("querylibrary")+1) */ "', '" + querynameT.getText() + "', '" + querycode + "')");
			}
		}
		else if (event.getSource() == left) {
			if(option2.getSize() > 0){
				option1.addElement(option2.getElementAt(sindex).toString());
				option2.removeElement(option2.getElementAt(sindex).toString());
			}
		}
		else if (event.getSource() == right) {
			if(option1.getSize() > 0){
				option2.addElement(option1.getElementAt(sindex).toString());
				option1.removeElement(option1.getElementAt(sindex).toString());
			}
		}
	}
	public void valueChanged(ListSelectionEvent event2) {
		if (event2.getSource() == qmodel) {
			lsm = (ListSelectionModel)event2.getSource();

			int firstIndex = event2.getFirstIndex();
			int lastIndex = event2.getLastIndex();
			boolean isAdjusting = event2.getValueIsAdjusting();

			if (lsm.isSelectionEmpty()) {
				System.out.println(" <none>");
			} 
			else {
				// Find out which indexes are selected.
				int minIndex = lsm.getMinSelectionIndex();
				int maxIndex = lsm.getMaxSelectionIndex();
				for (int i = minIndex; i <= maxIndex; i++) {
					if (lsm.isSelectedIndex(i)) {
						//System.out.println(" " + i);
						sindex = i;
						
						//SHOW THE SELECTED QUERY CODE
						cvbox.setText(Database.getSelectedQuery("" + querymodel.getElementAt(sindex)).toString());
					}
				}
			}
		}
		else if (event2.getSource() == op1model || event2.getSource() == op2model) {
			lsm = (ListSelectionModel)event2.getSource();

			int firstIndex = event2.getFirstIndex();
			int lastIndex = event2.getLastIndex();
			boolean isAdjusting = event2.getValueIsAdjusting();

			if (lsm.isSelectionEmpty()) {
				System.out.println(" <none>");
			} 
			else {
				// Find out which indexes are selected.
				int minIndex = lsm.getMinSelectionIndex();
				int maxIndex = lsm.getMaxSelectionIndex();
				for (int i = minIndex; i <= maxIndex; i++) {
					if (lsm.isSelectedIndex(i)) {
						//System.out.println(" " + i);
						sindex = i;
					}
				}
			}
		}
		else if (event2.getSource() == usermodel) {
			lsm = (ListSelectionModel)event2.getSource();

			int firstIndex = event2.getFirstIndex();
			int lastIndex = event2.getLastIndex();
			boolean isAdjusting = event2.getValueIsAdjusting();

			if (lsm.isSelectionEmpty()) {
				System.out.println(" <none>");
			} 
			else {
				// Find out which indexes are selected.
				int minIndex = lsm.getMinSelectionIndex();
				int maxIndex = lsm.getMaxSelectionIndex();
				for (int i = minIndex; i <= maxIndex; i++) {
					if (lsm.isSelectedIndex(i)) {
						//System.out.println(" " + i);
						sindex = i;
						
						//POPULATE THE USER FIELDS
						result = Database.getSelectedUser(model.getElementAt(sindex).toString()).split(",");
						username.setText(result[1]);
						password.setText(result[2]);
						firstname.setText(result[3]);
						lastname.setText(result[4]);
						department.setText(result[5]);
						if(Integer.parseInt(result[6])==1){ priv.setSelectedIndex(0); }
						if(Integer.parseInt(result[6])==2){ priv.setSelectedIndex(1); }
						adduser.setText("Update User");
					}
				}
			}
		}
	}
	//END OF ACTION LISTENERS----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
}

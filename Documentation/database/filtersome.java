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

public class filtersome extends JPanel
{

  public static JTextField criteria;
  public static JComboBox ops;
  public static JComboBox cvalue;
  public static JLabel where;
  public static JComboBox optionsl;
 
  public static String[] operators;  
  
  public static JPanel filtersome;
  
	public filtersome()
	{
	
	 filtersome = new JPanel(new MigLayout("fill")); 
	
	//WHERE JLABEL
    where = new JLabel("Where: ");
    filtersome.add(where);
	
	

	
	
	//OPTIONS
    String[] options = { "Age", "Race", "Gender", "First Name", "Last Name" };
    optionsl = new JComboBox(options);
    optionsl.setSelectedIndex(4);
    
    //CRITERIA VALUES.. SUCH AS CAUCASIAN, HISPANIC, MALE, FEMALE ETC...
    String[] cvalues = { "Age", "Race", "Gender", "First Name", "Last Name" };
    cvalue = new JComboBox(cvalues);
    cvalue.setSelectedIndex(0);
    
    optionsl.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if(optionsl.getSelectedItem().toString() == "Gender"){
          ops.removeAllItems();
          ops.addItem("=");
          ops.addItem("!=");
          filtersome.remove(criteria);
          filtersome.add(cvalue);
          cvalue.removeAllItems();
          cvalue.addItem("Male");
          cvalue.addItem("Femaile");
        } else if(optionsl.getSelectedItem().toString() == "Age"){
          ops.removeAllItems();
          ops.addItem("=");
          ops.addItem("!=");
          ops.addItem(">");
          ops.addItem("<");
          filtersome.remove(cvalue);
          filtersome.add(criteria, "w 100!");
        } else if(optionsl.getSelectedItem().toString() == "Race"){
          ops.removeAllItems();
          ops.addItem("=");
          ops.addItem("!=");
          filtersome.remove(criteria);
          filtersome.add(cvalue);
          cvalue.removeAllItems();
          cvalue.addItem("Black or African American");
          cvalue.addItem("Hispanic or Latino");
          cvalue.addItem("Asian or Pacific Islander");
          cvalue.addItem("American Indian or Alaskan Native");
          cvalue.addItem("Other");
          cvalue.addItem("White");
        } else if(optionsl.getSelectedItem().toString() == "First Name"){
          ops.removeAllItems();
          ops.addItem("=");
          ops.addItem("!=");
          filtersome.remove(cvalue);
          filtersome.add(criteria, "w 100!");
        } else if(optionsl.getSelectedItem().toString() == "Last Name"){
          ops.removeAllItems();
          ops.addItem("=");
          ops.addItem("!=");
          filtersome.remove(cvalue);
          filtersome.add(criteria, "w 100!");
        }
      }
    });
    
    
    filtersome.add(optionsl, "w 100!");
    
    //OPERATORS
    String[] operators = { "<", ">", "=", "!="};
    ops = new JComboBox(operators);
    ops.setSelectedIndex(3);
    filtersome.add(ops, "w 40!");
    
    //FILTER TEXT CRITERIA
    criteria = new JTextField();
    filtersome.add(criteria, "w 100!");
    
    //END OF FILTER SOME PANEL
	
	}

}
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

import net.miginfocom.swing.MigLayout;

public class DBUI extends JPanel
{
  
  //DECLARE CONTROLS & LABELS
  public static JLabel usernameT;
  public static JTextField username;
  public static JLabel passwordT;
  public static JTextField password;
  public static JLabel confirmpasswordT;
  public static JTextField confirmpassword;
  public static JLabel firstnameT;
  public static JTextField firstname;
  public static JLabel lastnameT;
  public static JTextField lastname;
  public static JLabel departmentT;
  public static JTextField department;
  public static JLabel privilegeT;
  public static int petName;
  public static String[] result;
  public static DefaultListModel model;
  public static DefaultListModel option1;
  public static DefaultListModel option2;
  public static DefaultListModel querymodel;
  public static int i;
  public static JList sampleJList;
  private static ListSelectionModel tlmodel;
  private static JList tl;
  private static JList optionsbox1;
  private static JList optionsbox2;
  private static JList querylist;
  public static int sindex;
  public static JComboBox priv;
  public static JComboBox optionsl;
  public static JComboBox ops;
  public static JComboBox cvalue;
  public static JButton adduser;
  public static JTextField criteria;
  public static String[] operators;
  public static ListSelectionModel op2model;
  public static ListSelectionModel qmodel;
  public static JTextField querynameT;
  public static JTextField querynamepanelT;
  public static JTextArea textarea;
  public static JButton hidecode;
  public static JTextArea cvbox;
  
  public static JPanel filtersome = new JPanel(new MigLayout("fill"));  
  public static JPanel cvpane = new JPanel(new MigLayout("fill"));  
      
 
  public static void main(String[] args)
  {
    database.connect();
  }
  
  public DBUI()
  {
    
    JTabbedPane tab = new JTabbedPane();
    this.add(tab);
    
    this.setLayout(new MigLayout("fill"));
    
    
    
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
    String[] privilege = { "Administrator", "Experimenter" };

    priv = new JComboBox(privilege);
    priv.setSelectedIndex(0);
    priv.addActionListener(new ActionListener()
                             {
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        petName = cb.getSelectedIndex();
		if(petName==0){petName=1;}
		else if(petName==1){petName=2;}
        }
    });

    
    adduser = new JButton("Add User");
    adduser.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if(adduser.getText()=="Add User"){
        database.insert("INSERT INTO investigator VALUES('" + Integer.toString(database.getLastId("investigator")+1) + "','" + username.getText() + "','" + password.getText() + "','" + firstname.getText() + "','" + lastname.getText() + "','" + department.getText() + "','" + petName + "')");
        }
        else if(adduser.getText()=="Update User"){
        result = database.getSelectedUser(model.getElementAt(sindex).toString()).split(",");
        database.insert("UPDATE investigator SET username = '" + username.getText() + "', password = '" + password.getText() + "', first_name = '" + firstname.getText() + "', last_name = '" + lastname.getText() + "', department = '" + department.getText() + "', privilege = '" + priv.getSelectedIndex() + "' WHERE id = '" + result[0] + "'");
        }
        username.setText("");
        password.setText("");
        confirmpassword.setText("");
        firstname.setText("");
        lastname.setText("");
        department.setText("");
    
    //REMOVE JLIST ITEMS------------------------
       int size = model.getSize();
       for (i = 0; i < size; i++){
      model.removeElementAt(0);
       }
    //---------------------------------------
    result = database.getUsers().split(",");
    //ADD JLIST ITEMS------------------------
        for (i = 0; i < result.length; i++){
          model.addElement(result[i]);
        }
   //---------------------------------------
      } 
      
    });
    

    
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
    
    //VIEW USERS PANEL CONTROLS
    JTextField valueField;
    
    result = database.getUsers().split(",");

    model = new DefaultListModel();
    
    //ADD JLIST ITEMS------------------------
    for (int i = 0; i < result.length; i++)
      model.addElement(result[i]);
    //---------------------------------------

    
    String[] entries = {};
    sampleJList = new JList(model);
    sampleJList.setVisibleRowCount(4);
    Font displayFont = new Font("Serif", Font.BOLD, 18);
    sampleJList.setFont(displayFont);
    
    JScrollPane listPane = new JScrollPane(sampleJList);
    
    //NEW USER BUTTON ACTION---------------------------------------------
    JButton newuser = new JButton("New User");
        newuser.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        username.setText("");
        password.setText("");
        firstname.setText("");
        lastname.setText("");
        department.setText("");
        priv.setSelectedIndex(1);
        adduser.setText("Add User");
      }
     });
     //END NEW USER BUTTON ACTION-----------------------------------------
        
    //REMOVE USER BUTTON ACTION-------------------------------------------
    JButton removeuser = new JButton("Remove User");
    removeuser.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        result = database.getSelectedUser(model.getElementAt(sindex).toString()).split(",");
        database.insert("DELETE FROM investigator WHERE username = '" + result[1] + "'");
        
     //REMOVE JLIST ITEMS------------------------
       int size = model.getSize();
       for (i = 0; i < size; i++){
      model.removeElementAt(0);
       }
    //---------------------------------------
    result = database.getUsers().split(",");
    //ADD JLIST ITEMS------------------------
        for (i = 0; i < result.length; i++){
          model.addElement(result[i]);
        }
   //---------------------------------------
      }
    });
    //END OF REMOVE USER BUTTON ACTION------------------------------------
    
    //--------------------------------------------------------------------
    //EDIT USER BUTTON
    JButton edituser = new JButton("Edit User");
    
    edituser.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        result = database.getSelectedUser(model.getElementAt(sindex).toString()).split(",");
        username.setText(result[1]);
        password.setText(result[2]);
        firstname.setText(result[3]);
        lastname.setText(result[4]);
        department.setText(result[5]);
        priv.setSelectedIndex(Integer.parseInt(result[6]));
        adduser.setText("Update User");
      }
    });
    //END OF EDIT USER BUTTON ACTION--------------------------------------
    
    ListSelectionModel model;
    model = sampleJList.getSelectionModel();
    model.addListSelectionListener(new SharedListSelectionHandler());

    
    
    //ADD VIEW USERS PANEL CONTROLS
    viewusers.add(listPane);
    JPanel userbuttons = new JPanel(new MigLayout("fill"));
    userbuttons.add(newuser, "w 90!");
    userbuttons.add(edituser, "w 85!");
    userbuttons.add(removeuser, "w 110!");
    viewusers.add(userbuttons, "w 50!");
    
    
    
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
    JPanel resultsfilter = new JPanel(new MigLayout("fill"));    
    
    
    //ADD OPTIONS BOX1==========================================
   
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
    op1model.addListSelectionListener(new SharedListSelectionHandler());
    
    JScrollPane optionsbox1scrollpane = new JScrollPane(optionsbox1);
    optionsboxpanel1.add(optionsbox1scrollpane, "w 200!, h 300!, split 2, top");
    
    //END OF OPTIONS BOX1=======================================
    
    
    //ADD OPTIONS BOX2==========================================
    option2 = new DefaultListModel();
    
    //ADD JLIST ITEMS------------------------
      
    //---------------------------------------

    optionsbox2 = new JList(option2);
    optionsbox2.setVisibleRowCount(4);
    optionsbox2.setFont(displayFont);
    
    JScrollPane optionsbox2scrollpane = new JScrollPane(optionsbox2);
    

    op2model = optionsbox2.getSelectionModel();
    op2model.addListSelectionListener(new SharedListSelectionHandler());
    
    optionsboxpanel2.add(optionsbox2scrollpane, "w 200!, h 300!, split 2, top");
    //END OF OPTIONS BOX2=======================================
    
    
    //RESULTS FILTER PANE
    JPanel filterallsome = new JPanel(new MigLayout("fill"));
    ButtonGroup group2 = new ButtonGroup();
    JRadioButton all = new JRadioButton("Retrieve All The Results", true);
    group2.add(all);
    filterallsome.add(all);
    JRadioButton some = new JRadioButton("Filter The Results", false);
    group2.add(some);
    filterallsome.add(some, "wrap");
    
    
    //DISABLE OR ENABLE FILTERS
    all.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        optionsl.setEnabled(false);
        ops.setEnabled(false);
        criteria.setEnabled(false);
      }
    });
    
    some.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        optionsl.setEnabled(true);
        ops.setEnabled(true);
        criteria.setEnabled(true);
      }
    });
    
    //END OF ENABLED/DISABLE FILTERS
    

        
    //FILTER SOME PANEL
    //WHERE JLABEL
    JLabel where;
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
    filtersome.add(cvalue, "left");
    //END OF FILTER SOME PANEL
    
    
    //SET FILTER INITIAL STATE
    optionsl.setEnabled(false);
    ops.setEnabled(false);
    criteria.setEnabled(false);
    //---------------------------
    
    
    //ADD PANES TO RESULTS FILTER
    resultsfilter.add(filterallsome, "wrap");
    resultsfilter.add(filtersome, "wrap");
    
   
    JButton runquery;
    runquery = new JButton("Run Query");
    
    JButton addtolibrary;
    addtolibrary = new JButton("Add Query To Library");
    addtolibrary.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        String querycode;
       querycode = "SELECT ";
       
       int size = option2.getSize();
    //CONCATENATE SELECTED CRITERIA------------------------
    for (int i = 0; i < size; i++)
      querycode = querycode + option2.getElementAt(i).toString() + ",";
    
    querycode=querycode+"FROM participant WHERE " + optionsl.getSelectedItem().toString() + " " + ops.getSelectedItem().toString() + " @" + cvalue.getSelectedItem().toString() + "@"; 
    //---------------------------------------
        database.insert("INSERT INTO querylibrary VALUES('" + Integer.toString(database.getLastId("querylibrary")+1) + "', '" + querynameT.getText() + "', '" + querycode + "')");
      }
    });
    
    JLabel queryname;
    queryname = new JLabel("Query Name: ");
    

    querynameT = new JTextField();
    
    
    
    //LEFT RIGHT PANE
    
    //MOVE ITEMS LEFT-----------------------------------------
    JButton left = new JButton("<<");
    left.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if(option2.getSize() > 0){
        option1.addElement(option2.getElementAt(sindex).toString());
        option2.removeElement(option2.getElementAt(sindex).toString());
        }
      }
    });
    //END OF MOVE ITEMS LEFT----------------------------------
    
    leftright.add(left,"wrap, left");
    
    //MOVE ITEMS RIGHT-----------------------------------------
    JButton right = new JButton(">>");
    right.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if(option1.getSize() > 0){
        option2.addElement(option1.getElementAt(sindex).toString());
        option1.removeElement(option1.getElementAt(sindex).toString());
        }
      }
    });
    //END OF MOVE ITEMS RIGHT----------------------------------
    
    
    leftright.add(right, "left");  
    //END OF LEFT RIGHT PANE
    
    //ADD TAB 2 TO THE TABBED PANE
    tab.add("Query Builder", tab2);
    
    //ADD PANELS TO TAB 2
    tab2.add(optionsboxpanel1, "w 200!, h 300!, split 2, top");
    tab2.add(leftright, "h 100!, top");
    tab2.add(optionsboxpanel2, "w 200!, h 200!, split 2, top");
    tab2.add(resultsfilter, "top, wrap");
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
    JLabel querynamepanelL;
    querynamepanelL = new JLabel("Query Name: ");
    

    querynamepanelT = new JTextField();
    
    querynamepanel.add(querynamepanelL, "w 100!");
    querynamepanel.add(querynamepanelT, "w 150!");
    //===================================
    
    //QUERY BUTTONS
    JButton runqueryb;
    runqueryb = new JButton("Run Query");
    
    //START OF ADD QUERY TO LIBRARY--------------------------------
    JButton addtolib;
    addtolib = new JButton("Add Query To Library");
    addtolib.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        database.insert("INSERT INTO querylibrary VALUES('" + Integer.toString(database.getLastId("querylibrary")+1) + "','" + querynamepanelT.getText() + "','" + textarea.getText() + "')");
      }
        });
        //END OF ADD TO LIBRARY
    
    querybuttons.add(runqueryb, "split 2");
    querybuttons.add(addtolib);
    //===============================================
    
    //ADD PANELS TO TAB 3
    tab3.add(textarea, "w 600!, h 150!, top, left, wrap");
    tab3.add(querynamepanel, "wrap");
    tab3.add(querybuttons);
    //===============================================
    
    
    
    
    //==========================================================================
    
    
    
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
    
    result = database.getQueries().split(",");
    //ADD QUERY ITEMS------------------------
        for (i = 0; i < result.length; i++){
          querymodel.addElement(result[i]);
        }
    //---------------------------------------

    querylist = new JList(querymodel);
    querylist.setVisibleRowCount(4);
    querylist.setFont(displayFont);
    
  
    ListSelectionModel qmodel;
    qmodel = querylist.getSelectionModel();
    qmodel.addListSelectionListener(new SharedListSelectionHandler());
    
    JScrollPane qscrollpane = new JScrollPane(querylist);
    qlpane.add(qscrollpane, "w 600!, h 150!, split 2, top");
    
    //END OF QUERYLIST=======================================
    
    
    //==================================================
    
    //CODE BUTTONS PANE

    hidecode = new JButton("Show Code");
    hidecode.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {

        if(hidecode.getText() == "Show Code"){
          hidecode.setText("Hide Code"); 
          cvpane.add(cvbox, "w 600!, h 150!");
          cvbox.setText(database.getSelectedQuery(querymodel.getElementAt(sindex).toString()));
        } else if(hidecode.getText() == "Hide Code"){
        hidecode.setText("Show Code");
        cvpane.remove(cvbox);
        }
        
       }
    });
    
    JButton copycode;
    copycode = new JButton("Copy Code");
    
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
        
    
    //==========================================================================
    
    
  }
  
   public static class SharedListSelectionHandler implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();

        int firstIndex = e.getFirstIndex();
        int lastIndex = e.getLastIndex();
        boolean isAdjusting = e.getValueIsAdjusting();

        if (lsm.isSelectionEmpty()) {
            System.out.println(" <none>");
        } else {
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

}
  
}


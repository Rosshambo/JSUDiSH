import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;

import net.miginfocom.swing.MigLayout;

public class DBUI
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
  public static int i;
  public static JList sampleJList;
  private static ListSelectionModel tlmodel;
  private static JList tl;
  public static int sindex;
  public static JComboBox priv;
  public static JButton adduser;
      
 
  public static void main(String[] args)
  {
    database.connect();
  }
  
  public static void create()
  {
    JFrame frame = new JFrame("DISH ADMIN PANEL");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JTabbedPane tab = new JTabbedPane();
    frame.add(tab);
    
    frame.setLayout(new MigLayout("fill"));
    
    
    
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
    JPanel optionsbox = new JPanel(new MigLayout("fill"));
    JPanel optionsbox2 = new JPanel(new MigLayout("fill"));    
    JPanel leftright = new JPanel(new MigLayout("fill"));
    JPanel resultsfilter = new JPanel(new MigLayout("fill"));    
    JPanel filtersome = new JPanel(new MigLayout("fill"));  
    
    //ADD OPTIONS BOX
    JList optionsbox1;
    JTextField optionsbox1valuefield;
    String[] optionbox1entries = { "Logan Moore", "Lori Liles", "Tony Teem",
                         "Jaleesa Elston", "Tonya Burton", "Andrew Peil", 
                         "Dr. Thornton", "Dr. Garrett", "Michael Bolton", 
                         "Michael Jackson", "George Michael", "Prince" };
    sampleJList = new JList(entries);
    sampleJList.setVisibleRowCount(4);
    Font optionsbox1displayFont = new Font("Serif", Font.BOLD, 18);
    sampleJList.setFont(displayFont);
    //sampleJList.addListSelectionListener(new ValueReporter());
    JScrollPane optionsboxz1 = new JScrollPane(sampleJList);
    optionsbox.add(optionsboxz1, "w 300!, h 200!, top");
    //END OF OPTIONS BOX
    
    
    //ADD OPTIONSBOX2
    JList optionsboxa;
    JTextField optionsbox2valuefield;
    String[] optionbox2entries = { "" };
    sampleJList = new JList(optionbox2entries);
    sampleJList.setVisibleRowCount(4);
    Font optionsbox2displayFont = new Font("Serif", Font.BOLD, 18);
    sampleJList.setFont(displayFont);
    //sampleJList.addListSelectionListener(new ValueReporter());
    JScrollPane optionsboxz2 = new JScrollPane(sampleJList);
    //END OF OPTIONS BOX 2
    
    
    //RESULTS FILTER PANE
    JPanel filterallsome = new JPanel(new MigLayout("fill"));
    ButtonGroup filterallsome2 = new ButtonGroup();
    JRadioButton all = new JRadioButton("Retrieve All The Results", true);
    //group.add(all);
    filterallsome.add(all);
    JRadioButton some = new JRadioButton("Filter The Results", false);
    //group.add(some);
    filterallsome.add(some, "wrap");
    

        
    //FILTER SOME PANEL
    //WHERE JLABEL
    JLabel where;
    where = new JLabel("Where: ");
    filtersome.add(where);
    
    
    //OPTIONS
    String[] options = { "Age", "Race", "Gender", "First Name", "Last Name" };
    JComboBox optionsl = new JComboBox(options);
    optionsl.setSelectedIndex(4);
    //options.addActionListener(this);
    filtersome.add(optionsl, "w 100!");
    
    //OPERATORS
    String[] operators = { "<", ">", "=", "!="};
    JComboBox ops = new JComboBox(operators);
    ops.setSelectedIndex(3);
    //options.addActionListener(this);
    filtersome.add(ops, "w 40!");
    
    //FILTER TEXT CRITERIA
    JTextField criteria;
    criteria = new JTextField();
    filtersome.add(criteria, "w 100!");
    //END OF FILTER SOME PANEL
    
    
    //ADD PANES TO RESULTS FILTER
    resultsfilter.add(filterallsome, "wrap");
    resultsfilter.add(filtersome, "wrap");
    
   
    JButton runquery;
    runquery = new JButton("Run Query");
    
    JButton addtolibrary;
    addtolibrary = new JButton("Add Query To Library");
    
    JLabel queryname;
    queryname = new JLabel("Query Name: ");
    
    JTextField querynameT;
    querynameT = new JTextField();
    
    
    
    //LEFT RIGHT PANE
    JButton left = new JButton("<<");
    leftright.add(left,"wrap, left");
    
    //MOVE ITEMS RIGHT-----------------------------------------
    JButton right = new JButton(">>");
    right.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        
      }
    });
    //END OF MOVE ITEMS RIGHT----------------------------------
    
    
    leftright.add(right, "left");  
    //END OF LEFT RIGHT PANE
    
    //ADD TAB 2 TO THE TABBED PANE
    tab.add("Query Builder", tab2);
    
    //ADD PANELS TO TAB 2
    tab2.add(optionsbox, "w 200!, h 300!, split 2, top");
    tab2.add(leftright, "h 100!, top");
    tab2.add(optionsboxz2, "w 200!, h 200!, split 2, top");
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
    JTextArea textarea;
    textarea = new JTextArea();
    //================================
    
    //QUERYNAME
    JLabel querynamepanelL;
    querynamepanelL = new JLabel("Query Name: ");
    
    JTextField querynamepanelT;
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
        edituser.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        
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
    JPanel cvpane = new JPanel(new MigLayout("fill"));   
    JPanel lbpane = new JPanel(new MigLayout("fill"));   
    
    //ADD TAB 4 TO THE TABBED PANE
    tab.add("Query Library", tab4);
    
    //QUERYLISTBOX PANE====================================
    
    //ADD QUERIES LIST----------------------------------
    JList qlbox;
    JTextField qlboxfield;
    String[] qlboxentries = { "Red Haired Ladies", "Caucasian Males", "Hispanic Women Under 40", " ", " ", " ", " ", " ", " ", " ", " ",
                         };
    sampleJList = new JList(qlboxentries);
    sampleJList.setVisibleRowCount(4);
    Font qldisplayFont = new Font("Serif", Font.BOLD, 18);
    sampleJList.setFont(displayFont);
    //sampleJList.addListSelectionListener(new ValueReporter());
    JScrollPane qlboxscrollpane = new JScrollPane(sampleJList);
    qlpane.add(qlboxscrollpane, "w 600!, wrap");
    //END OF QUERIES LIST------------------------------
    
    
    //==================================================
    
    //CODE BUTTONS PANE
    JButton hidecode;
    hidecode = new JButton("Hide Code");
    
    JButton copycode;
    copycode = new JButton("Copy Code");
    
    cbpane.add(hidecode, "center, w 100!");
    cbpane.add(copycode, "center, w 100!, wrap");
    
      
    //==================================================
    
    //CODE VIEWER PANE
    
    JTextArea cvbox;
    cvbox = new JTextArea();
    
    cvpane.add(cvbox, "w 600!, h 150!");
    
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
    
    frame.setSize(850,600);
    frame.setVisible(true);
    
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


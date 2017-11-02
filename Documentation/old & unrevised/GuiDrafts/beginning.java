package GuiDrafts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import net.miginfocom.swing.MigLayout;

public class beginning extends JFrame implements ActionListener{

	private JMenuBar menuBar;
	private JMenu file, admin;
	private JMenuItem load, exit, userAdmin, dataAdmin;
	private JPanel panel;
	private Demographics demos;
	private MultipleChoice multi;
	private ExitWindow exitWindow;
	private DLInstruct DLI;
	private RealTime rt;
	private RealTimeInstruct rti;
	private JButton next;
	private CardLayout cl;
	private DADL dl;
	
	public beginning(){	
		this.setLayout(new MigLayout());
		createMenu();
		createDemos();
		cl = new CardLayout();
		panel = new JPanel(cl);
		next = new JButton("Next");
		panel.add(demos, "Demos");
		panel.add(DLI, "DLI");
		panel.add(multi, "multi");
		panel.add(dl, "dl");
		panel.add(rti, "rti");
		panel.add(rt, "rt");
		panel.add(exitWindow, "exit");
		this.add(panel, BorderLayout.CENTER);
		this.add(next, BorderLayout.SOUTH);
		cl.first(panel);
		next.addActionListener(this);
	}
	
	public void createMenu() {
		menuBar = new JMenuBar();
		file = new JMenu("File");
		menuBar.add(file);
		admin = new JMenu("Administration");
		menuBar.add(admin);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(665,475));
		add(menuBar, BorderLayout.NORTH);	
		load = new JMenuItem("Load");
		file.add(load);
		load.addActionListener(this);
		exit = new JMenuItem("Exit");
		file.add(exit);
		exit.addActionListener(this);
		userAdmin = new JMenuItem("User Administration");
		admin.add(userAdmin);
		userAdmin.addActionListener(this);
		dataAdmin = new JMenuItem("Data Administration");
		admin.add(dataAdmin);
		dataAdmin.addActionListener(this);
	}
	
	public void createDemos(){
		demos = new Demographics();
		multi = new MultipleChoice();
		exitWindow = new ExitWindow();
		DLI = new DLInstruct();
		rt = new RealTime();
		rti = new RealTimeInstruct();
		dl = new DADL();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == load) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter( "XML Files", "xml");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				JOptionPane.showMessageDialog(null, "The XML Validator is missing currently!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (e.getSource() == exit) { System.exit (0); }
		else if (e.getSource() == userAdmin) {
			
		}
		else if (e.getSource() == dataAdmin) {
			
		}
		else if(e.getSource() == next){
			cl.next(panel);
		}
	}
	
	public static void main(String[] args) {
		beginning win = new beginning();
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.pack();
		win.setVisible(true);
	}
}
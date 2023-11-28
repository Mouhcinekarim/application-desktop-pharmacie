import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.border.CompoundBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.Color;
public class Select extends JFrame {

	private JPanel forme;
	private JTextField din;
	private JTextField classe;
	private JTable table;
	DefaultTableModel modele;
	Database data;
	Home home ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Select frame = new Select();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public Select() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				home =new Home();
            	home.setVisible(true);
            	dispose();
			}
		});
//		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		addWindowListener(new WindowAdapter() {
//            public void windowClose(WindowEvent e) {
//            	home =new Home();
//            	home.setVisible(true);
//            	hide();
//                   
//            }});
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 50, 675, 590);
		forme = new JPanel();
		forme.setBackground(new Color(245, 255, 250));
		forme.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(forme);
		forme.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(328, 0, 16, 259);
		forme.add(separator);
		
		JLabel ldin = new JLabel("chercher par le numero de medicamnent");
		ldin.setFont(new Font("SansSerif", Font.BOLD, 16));
		ldin.setBounds(10, 11, 308, 52);
		forme.add(ldin);
		
		din= new JTextField();
		din.setColumns(10);
		din.setBounds(82, 74, 162, 32);
		forme.add(din);
		
		JButton chercher_din = new JButton("chercher");
		chercher_din.setBackground(new Color(224, 255, 255));
		chercher_din.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chercher_din.setBounds(103, 156, 127, 32);
		forme.add(chercher_din);
		
		JLabel ldin_1 = new JLabel("chercher par le classe de medicamnent");
		ldin_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		ldin_1.setBounds(343, 11, 308, 52);
		forme.add(ldin_1);
		
		classe = new JTextField();
		classe.setColumns(10);
		classe.setBounds(406, 74, 162, 32);
		forme.add(classe);
		
		JButton chercher_class = new JButton("chercher");
		chercher_class.setBackground(new Color(224, 255, 255));
		
		chercher_class.setFont(new Font("SansSerif", Font.PLAIN, 16));
		chercher_class.setBounds(433, 156, 105, 32);
		forme.add(chercher_class);
		
		table = new JTable();
		table.setBackground(new Color(245, 245, 220));
		String[] titre={"id", "nom", "classe", "date","quantite", "prix"};
		
		
		
		table.setBounds(82, 256, 486, 259);
		table.getTableHeader().setFont(
                new Font(table.getFont().getFamily(),
                        Font.BOLD, 15));
		variable_valide valide=new variable_valide();
		JScrollPane scroll= new JScrollPane(table);		
		scroll.setSize(651, 203);
		scroll.setLocation(10, 298);forme.add(scroll);
//		table.getTableHeader().setReorderingAllowed(false);
		 data = new Database();
		chercher_din.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(valide.valide_int(din.getText())) {
					
				modele= new DefaultTableModel(data.cherche_by_elem(din.getText(),"din"),titre);
				table.setModel(modele);
				
				}
				
			}
		});
		chercher_class.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!classe.getText().equals("")) {
				
				modele= new DefaultTableModel(data.cherche_by_elem(classe.getText(),"classe"),titre);
				table.setModel(modele);}
				}
			
		});
		
	}
	


}

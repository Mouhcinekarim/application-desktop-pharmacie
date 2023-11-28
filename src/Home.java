import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class Home extends JFrame {

	private JPanel forme;
	private JTable table;
	DefaultTableModel modele ;
	Database data;
	Select select= new Select();
	Ajouter ajouter =new Ajouter();
	Update update = new Update();
	Delete delete = new Delete();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setBackground(Color.WHITE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				data.close_con();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 963, 474);
	    forme = new JPanel();
	    forme.setBackground(new Color(245, 255, 250));
		forme.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(forme);
		forme.setLayout(null);
		
		JLabel ltitre = new JLabel("les medicament disponible");
		ltitre.setForeground(new Color(0, 0, 0));
		ltitre.setBackground(new Color(106, 90, 205));
		ltitre.setFont(new Font("Castellar", Font.BOLD, 21));
		ltitre.setBounds(294, 11, 391, 42);
		forme.add(ltitre);
		
		JButton btnajouter = new JButton("Ajouter");
		btnajouter.setForeground(new Color(0, 0, 0));
		btnajouter.setBackground(new Color(224, 255, 255));
		btnajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouter.setVisible(true);
				hide();
				
			}
		});
		btnajouter.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnajouter.setBounds(56, 345, 143, 42);
		forme.add(btnajouter);
		
		JButton btnmodifier = new JButton("Modifier");
		btnmodifier.setBackground(new Color(224, 255, 255));
		btnmodifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update.setVisible(true);
				hide();
			}
		});
		btnmodifier.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnmodifier.setBounds(272, 345, 143, 42);
	    forme.add(btnmodifier);
		JButton btnsupprime = new JButton("Supprimer");
		btnsupprime.setBackground(new Color(224, 255, 255));
		btnsupprime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete.setVisible(true);
				hide();
			}
		});
		btnsupprime.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnsupprime.setBounds(516, 345, 143, 42);
		forme.add(btnsupprime);
		
		JButton btnchercher = new JButton("Chercher");
		btnchercher.setBackground(new Color(224, 255, 255));
		btnchercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select.setVisible(true);
				hide();
			}
		});
		btnchercher.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnchercher.setBounds(731, 345, 143, 42);
		forme.add(btnchercher);
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 17));
		table.setBackground(new Color(0, 255, 255));
		String[] titre={"Din", "Nom", "Classe",  "Date d'expiration","Quantite","Prix"};
		data= new Database();
		modele = new DefaultTableModel(data.cherche_by_elem("tout","tout"),titre);
	    table.setModel(modele);
	    table.getColumnModel().getColumn(1).setPreferredWidth(270);
	    table.getColumnModel().getColumn(2).setPreferredWidth(100);
	    table.getColumnModel().getColumn(3).setPreferredWidth(130);
	    table.getColumnModel().getColumn(4).setPreferredWidth(78);
	    table.getColumnModel().getColumn(5).setPreferredWidth(79);
		table.setBounds(82, 256, 486, 259);
		table.getTableHeader().setFont(
                new Font(table.getFont().getFamily(),
                        Font.BOLD, 17));
		
		JScrollPane scroll= new JScrollPane(table);		
		scroll.setSize(847, 270);
		scroll.setLocation(46, 64);
		forme.add(scroll);
	}

}

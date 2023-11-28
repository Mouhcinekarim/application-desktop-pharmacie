import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import java.awt.Color;

public class Delete extends JFrame {
	private JPanel forme;
	private JTextField din;
	Home home ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delete frame = new Delete();
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
	public Delete() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				home =new Home();
            	home.setVisible(true);
            	
            	hide();
			}
		});
		setBounds(300, 50, 605, 378);
		forme = new JPanel();
		forme.setBackground(new Color(245, 255, 250));
		forme.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(forme);
		forme.setLayout(null);
		
		JLabel Jdin = new JLabel("Entrer le Num\u00E9ro de m\u00E9dicament");
		Jdin.setFont(new Font("SansSerif", Font.BOLD, 16));
		Jdin.setBounds(148, 11, 255, 50);
		forme.add(Jdin);
		
		din = new JTextField();
		din.setFont(new Font("SansSerif", Font.PLAIN, 15));
		din.setBounds(178, 82, 193, 39);
		forme.add(din);
		din.setColumns(10);
		Database base = new Database();
		JButton delete = new JButton("supprime");
		delete.setBackground(new Color(224, 255, 255));
		 variable_valide valide = new variable_valide(); 
		 
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(valide.valide_int(din.getText()))
				{
					base.Delete(Integer.parseInt(din.getText()));
					if(base.message!=null) JOptionPane.showMessageDialog(null,base.message);
				}
				else {
						if(!valide.valide_int(din.getText())) JOptionPane.showMessageDialog(null,"din invalide");
				}
			}
		});
		delete.setBounds(228, 145, 98, 39);
		forme.add(delete);
		
		JButton delete_1 = new JButton("supprime tout");
		delete_1.setBackground(new Color(224, 255, 255));
		delete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				base.Delete(-1);
			}
		});
		delete_1.setBounds(216, 209, 127, 39);
		forme.add(delete_1);		
	}
}


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.components.JSpinField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class Update extends JFrame {
	private JPanel forme;
	private JTextField din;
	private JTextField prix;
	Home home ;
    /*
	* Launch the application.
	*/ 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				 	Update frame = new Update();
				 	frame.setVisible(true);
				} catch (Exception e){
				    e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Update() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				home =new Home();
            	home.setVisible(true);
            
            	hide();
			}
		});
		setBounds(200, 50, 867, 441);
		forme = new JPanel();
		forme.setBackground(new Color(245, 255, 250));
		forme.setForeground(new Color(102, 0, 204));
		forme.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(forme);
		forme.setLayout(null);
		
		JLabel ldin = new JLabel("Entrer le DIN de m\u00E9dicament");
		ldin.setFont(new Font("SansSerif", Font.BOLD, 16));
		ldin.setBounds(317, 11, 254, 52);
		forme.add(ldin);
		
		din = new JTextField();
		din.setBounds(348, 74, 162, 32);
		forme.add(din);
		din.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(428, 117, 26, 287);
		forme.add(separator);
		
		JLabel ltitre = new JLabel("mise \u00E0 jour la quantite");
		ltitre.setFont(new Font("SansSerif", Font.BOLD, 16));
		ltitre.setBounds(89, 129, 203, 32);
		forme.add(ltitre);
		
		JSpinField quantite = new JSpinField();
		quantite.getSpinner().setForeground(new Color(224, 255, 255));
		quantite.setBounds(132, 182, 108, 32);
		forme.add(quantite);
		
		JLabel lquantite = new JLabel("quantite  :");
		lquantite.setFont(new Font("SansSerif", Font.BOLD, 16));
		lquantite.setBounds(47, 182, 77, 32);
		forme.add(lquantite);
		variable_valide valide = new variable_valide();
		Database base = new Database();
		
		JButton vendre = new JButton("vendre");
		vendre.setBackground(new Color(224, 255, 255));
		vendre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(valide.valide_int(""+quantite.getValue())&&valide.valide_int(din.getText())) {
					base.updeate_quantite(quantite.getValue(),Integer.parseInt(din.getText()),"diminuer");
					JOptionPane.showMessageDialog(null,base.message);
				}
			}
		});
		vendre.setFont(new Font("SansSerif", Font.PLAIN, 16));
		vendre.setBounds(211, 262, 89, 32);
		forme.add(vendre);
		
		
		
		JButton acheter = new JButton("acheter");
		acheter.setBackground(new Color(224, 255, 255));
		acheter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(valide.valide_int(""+quantite.getValue())&&valide.valide_int(din.getText())) {
					base.updeate_quantite(quantite.getValue(),Integer.parseInt(din.getText()),"ajouter");
					JOptionPane.showMessageDialog(null,base.message);
				}
				
			}
		});
		acheter.setFont(new Font("SansSerif", Font.PLAIN, 16));
		acheter.setBounds(89, 262, 89, 32);
		forme.add(acheter);
		
		JLabel titre_prix = new JLabel("mise \u00E0 jour le prix");
		titre_prix.setFont(new Font("SansSerif", Font.BOLD, 16));
		titre_prix.setBounds(532, 129, 203, 32);
		forme.add(titre_prix);
		
		JLabel lprix = new JLabel("le prix  :");
		lprix.setFont(new Font("SansSerif", Font.BOLD, 16));
		lprix.setBounds(481, 182, 77, 32);
		forme.add(lprix);
		
		JButton ajouter_prix = new JButton("nouvelle prix");
		ajouter_prix.setBackground(new Color(224, 255, 255));
		ajouter_prix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(valide.valide_float(prix.getText())&&valide.valide_int(din.getText())) {
					base.updeate_prix(Float.parseFloat(prix.getText()),Integer.parseInt(din.getText()));
					JOptionPane.showMessageDialog(null,base.message);
					
				}
				else {
					
					JOptionPane.showMessageDialog(null,"le prix ou din sont invalide");
				}
			}
		});
		ajouter_prix.setFont(new Font("SansSerif", Font.PLAIN, 16));
		ajouter_prix.setBounds(559, 262, 132, 32);
		forme.add(ajouter_prix);
		
		prix = new JTextField();
		prix.setBounds(559, 182, 110, 29);
		forme.add(prix);
		prix.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel.setBounds(145, 335, 147, 32);
		forme.add(lblNewLabel);
		
	}
}

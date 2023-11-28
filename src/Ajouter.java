
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import java.awt.Color;

public class Ajouter extends JFrame {
//    Home home =new Home();
	private JPanel forme;
	private JTextField nom;
	private JTextField din;
	private JTextField quantite;
	private JTextField prix;
	private JTextField date;
	Home home ;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ajouter frame = new Ajouter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public Ajouter()  {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				home =new Home();
            	home.setVisible(true);
               
			}
		});
		setBounds(300, 50, 604, 619);
		forme = new JPanel();
		forme.setBackground(new Color(240, 248, 255));
		forme.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(forme);
		forme.setLayout(null);
		
		JLabel lDIN = new JLabel("Identification num\u00E9rique de drogue(DIN) :");
		lDIN.setBounds(33, 37, 317, 36);
		lDIN.setFont(new Font("SansSerif", Font.BOLD, 16));
		forme.add(lDIN);
		
		JLabel lnom = new JLabel("Nom de m\u00E9dicament :");
		lnom.setBounds(33, 93, 176, 36);
		lnom.setFont(new Font("SansSerif", Font.BOLD, 16));
		forme.add(lnom);
		
		JLabel lclasse = new JLabel("Classe :");
		lclasse.setBounds(33, 166, 158, 20);
		lclasse.setFont(new Font("SansSerif", Font.BOLD, 16));
		forme.add(lclasse);
		
		JLabel lquantite = new JLabel("Quantit\u00E9 :");
		lquantite.setBounds(33, 209, 228, 36);
		lquantite.setFont(new Font("SansSerif", Font.BOLD, 16));
		forme.add(lquantite);
		
		JLabel ldate_ex = new JLabel("Date d'expiration :");
		ldate_ex.setBounds(33, 256, 158, 36);
		ldate_ex.setFont(new Font("SansSerif", Font.BOLD, 16));
		forme.add(ldate_ex);
		
		nom = new JTextField();
		nom.setBounds(367, 99, 196, 31);
		forme.add(nom);
		nom.setColumns(10);
		
		din = new JTextField();
		din.setBounds(367, 43, 196, 31);
		forme.add(din);
		din.setColumns(10);
		
		JComboBox classe = new JComboBox();
		classe.setBackground(new Color(248, 248, 255));
		classe.setBounds(367, 162, 196, 31);
		classe.setFont(new Font("Arial", Font.PLAIN, 16));
		classe.setModel(new DefaultComboBoxModel(new String[] {"antibiotique", "antifongique", "antiviraux","Antalgiques"}));
		forme.add(classe);
		
		quantite = new JTextField();
		quantite.setBounds(367, 215, 196, 31);
		quantite.setColumns(10);
		forme.add(quantite);
		
		prix = new JTextField();
		prix.setBounds(367, 309, 196, 31);
		prix.setColumns(10);
		forme.add(prix);
		
		JLabel lprix = new JLabel("Prix en DH:");
		lprix.setBounds(33, 303, 116, 36);
		lprix.setFont(new Font("SansSerif", Font.BOLD, 16));
		forme.add(lprix);
	
		
		date = new JTextField();
		date.setText("YYYY-MM-DD");
		date.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		date.setBounds(367, 259, 196, 31);
		forme.add(date);
		
		  
		
		 variable_valide valide = new variable_valide(); 
		Database base= new Database();
		JButton Ajouter = new JButton("Ajouter");
		Ajouter.setBackground(new Color(245, 255, 250));
		Ajouter.setBounds(219, 410, 116, 44);
		Ajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(valide.valide_int(din.getText())&&!nom.getText().equals("")&&valide.valide_int(quantite.getText())&&!date.equals("")&&valide.valide_float(prix.getText())&&validateJavaDate(date.getText())) {
					base.Ajouter(Integer.parseInt(din.getText()),nom.getText(),classe.getSelectedItem().toString(),Integer.parseInt(quantite.getText()),date.getText(),Float.parseFloat(prix.getText()));
				    
				    if(base.message!=null){JOptionPane.showMessageDialog(null,base.message);
				        base.message=null;
				        din.setText("");nom.setText("");quantite.setText("");date.setText("YYYY-MM-DD");prix.setText("");
				    }
				    else {
				    	 
				    	JOptionPane.showMessageDialog(null,"le medicament bien ajouter");
				   
				    }
//				    home.setVisible(true);
				}
				else {
					if(!valide.valide_int(din.getText())) {
						JOptionPane.showMessageDialog(null,"           DIN invalide");
						}
					else if(!valide.valide_int(quantite.getText())) {JOptionPane.showMessageDialog(null,"quantite invalide");}
					else if(!valide.valide_float(prix.getText())) {JOptionPane.showMessageDialog(null,"le prix invalide");}
					else if(!validateJavaDate(date.getText())) {
						JOptionPane.showMessageDialog(null,"svp entrer la date sous form YYYY-MM-DD");}
					else if(nom.getText().equals("")) {JOptionPane.showMessageDialog(null,"le nom de medicament est vide");}
					
					
				  
				}
				 
				
			}
		});
		Ajouter.setFont(new Font("SansSerif", Font.BOLD, 16));
		forme.add(Ajouter);
	}
	 public static boolean validateJavaDate(String strDate)
	   {
		/* Check if date is 'null' */
		 boolean valide=true;
		if (strDate.trim().equals(""))
		{
		    valide= true;
		}
		/* Date is not 'null' */
		else
		{
		    /*
		     * Set preferred date format,
		     * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
		    SimpleDateFormat sdfrmt = new SimpleDateFormat("YYYY-mm-DD");
		    sdfrmt.setLenient(true);
		    /* Create Date object
		     * parse the string into date 
	             */
		    
		    try
		    {
		        java.util.Date javaDate = sdfrmt.parse(strDate); 
		       
		    }
		    /* Date format is invalid */
		    catch (ParseException e)
		    {
		    	 
		    	valide= false;
		    }
		    /* Return true if date format is valid */
		    if(valide) {
		    	if(!intervale_date(strDate)) {
		    		valide=false;
		    	}
		    }
		}
		return valide;
	   }
	   static boolean intervale_date(String date){
		  String[] strar= date.split("-",3);
		 
	      int anne=Integer.parseInt(strar[0]),moins=Integer.parseInt(strar[1]),jour=Integer.parseInt(strar[2]);
	      if(anne-2023<0||moins-12>0||jour-31>0) {
	    	  return false;
	      }
	      return true;
	      
	  }

}

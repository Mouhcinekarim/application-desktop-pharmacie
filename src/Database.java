import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;



public class Database {

	static String message=null;
	 String url="jdbc:mysql://localhost:3306/pharmacie";
	 String login="root";
	 String pwd="";
	 Connection con=null;
     Statement st;
     String requette=null;
     ResultSet re=null;
     int res=0;
     String table="medicament";
     ///les collums de la base de donner;
     // din nom 
     
	Database(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			 con=DriverManager.getConnection(url,login,pwd);
			
			 st=con.createStatement();
    	
    	 
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	//constraicteur pour supristion
	
	
    void Ajouter(int din,String nom,String classe,int quantite,String date_exp,Float prix) {
    	if(!din_if_exist(din)) {
	     try {
	    	 requette="insert into "+table+" values('"+din+"','"+nom+"','"+classe+"','"+prix+"','"+date_exp+"','"+quantite+"')";
	    	 
	    	 res=st.executeUpdate(requette);
	    	 if(res==1) message="le medicamnet N_"+din+" bien ajouter";
//	    	 con.close();
	    	 
	    	
	     }
	     catch(Exception e) {
	    	 System.out.println(e.getMessage());
	     }
    	}
    	else {
    		message="cette Identifiant deja exist";
    	}
    }
    void Delete(int din) {
    	requette=null;
    	if(din<0) 	requette="delete from "+table;
    	else requette="delete from "+table+" where din='"+din+"'";
    	message="cette Identifiant n'exist pas";
    	if(din_if_exist(din) || din<0) {
    		
    	try {
			res=st.executeUpdate(requette);
			if(res==1) message="le "+table+" N_"+din+" est supprimé";
			else  message="tout les medicamnet sont supprimé";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    
		}
    	}
    	 
    	
    }
    void updeate_prix(float prix,int din){
    	if(din_if_exist(din)) {
    	requette="update "+table+" set prix='"+prix+"' where din='"+din+"'";
    	try {
			res=st.executeUpdate(requette);
			message="le prix mise à jour "+prix;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
    }
    void updeate_quantite(int quantite,int din,String operation) {
    	 requette="select quantite from "+table+" where din='"+din+"'";
    	 int q=0;
    	 if(din_if_exist(din)) {
    	 try {
    		 
			re=st.executeQuery(requette);
			if(re.next())  {q=re.getInt("quantite");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(operation.equals("ajouter")) {
    		requette="update "+table+" set quantite='"+(q+quantite)+"' where din='"+din+"'";
    		try {
				res=st.executeUpdate(requette);
				message="la quantite mise à jour de "+q+" à "+(q+quantite);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	if(operation.equals("diminuer")) {
    		if(q-quantite>=0) {
    		requette="update "+table+" set quantite='"+(q-quantite)+"' where din='"+din+"'";
    		message="la quantite mise à jour de "+q+" à "+(q-quantite);
    		try {
				res=st.executeUpdate(requette);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
    		else {
    		message="la quantite disponible insuffisance";
    		}
    	}
    	}
    }
    boolean din_if_exist(int din) {
    	String requette1;
    	requette1="select *from "+table+" where din='"+din+"'";
    	
    	try {
			re=st.executeQuery(requette1);
			if(re.next()) return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		
			 return false;
		}
    	message="ce din n'exist pas";
    	return false;
    }
    Object[][]  cherche_by_elem(String elem,String nomelem){
    	ArrayList<ArrayList<String>> table1 = new ArrayList<ArrayList<String> >();
    	
    	int din1;
    	String nom=null;
    	String classe=null;
    	Float prix=null;
    	Date date=null;
    	int quantite;
    	if(nomelem.equals("tout")) requette= "select * from "+table;
    	if(nomelem.equals("din")) {if(din_if_exist(Integer.parseInt(elem)))requette="select *from "+table+" where din='"+elem+"'";}
    	if(nomelem.equals("classe")) requette="select *from "+table+" where classe='"+elem+"'";
    	
    	if(requette!=null){
    		
    		try {
				re=st.executeQuery(requette);
				while(re.next()) {
					
					din1=re.getInt("din");
					nom=re.getString("nom");
					classe=re.getString("classe");
					prix=re.getFloat("prix");
					date=re.getDate("date_exp");
					quantite=re.getInt("quantite");
					
					table1.add(new ArrayList<String>(Arrays.asList(""+din1,""+nom,""+classe,""+date,""+quantite,""+prix)));
						
							
					
				}
				return arrayList_to_Object(table1);	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	return null;
    } 
    Object[][] arrayList_to_Object(ArrayList<ArrayList<String>> array ){
		Object[][] Objet=null;
		if(array!=null) {
		Objet =new Object[array.size()][6];
		for(int i=0;i<array.size();i++) {
		Objet[i]= array.get(i).toArray();
		}
		return Objet;}
		else return null;
	}
    
	  void close_con() {
			try {
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


public class variable_valide {
	//valide le string si est int ou no
	boolean valide_int(String numbre) {
		if(numbre.equals("")) return false;
		if(numbre==null) {
			return false;
		}
		int n;
		try {
			 n=Integer.parseInt(numbre);
			
		}
		catch(Exception e) {
			return false;
		}
		if(n<=0) return false;
		return true;
	}
	
	
	
	boolean valide_float(String numbre) {
		if(numbre.equals("")) return false;
		if(numbre==null) {
			return false;
		}
		float n;
		try {
			 n=Float.parseFloat(numbre);
			
		}
		catch(Exception e) {
			return false;
		}
		if(n<=0) return false;
		return true;
	}
}

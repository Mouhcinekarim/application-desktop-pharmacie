package basic.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class RespenseProf {
	private String nom;
    private String prenom;
    private String email;
    private String password;
    private String nomDepartement;
	public RespenseProf(String nom, String prenom, String email, String password, String nomDepartement) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.nomDepartement = nomDepartement;
	}
    
}

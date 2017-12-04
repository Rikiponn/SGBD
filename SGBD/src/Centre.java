
public class Centre {
	int uidCentre;
	Adresse adr;
	String nom;
	
	public Centre(int uid, Adresse a, String n){
		uidCentre = uid;
		adr = a;
		nom = n;
	}
	
	public int getUid(){
		return uidCentre;
	}
	
	public Adresse getAdresse(){
		return adr;
	}
	
	public String getNom(){
		return nom;
	}
	
	public String toString(){
		return nom + "(" + uidCentre + ")";
	}
}

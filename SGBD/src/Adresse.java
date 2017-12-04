
public class Adresse {
	int uidAdresse;
	int num;
	String rue;
	String codePostal;
	String ville;
	
	public Adresse(int n, String r, String cp, String v){
		num = n;
		rue = r;
		codePostal = cp;
		ville = v;
	}
	
	public Adresse( String r, String cp, String v){
		num = 0;
		rue = r;
		codePostal = cp;
		ville = v;
	}

	public int getNum() {
		return num;
	}

	public String getRue() {
		return rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public String getVille() {
		return ville;
	}
	
	public String toString(){
		return num + " " + rue + " " + codePostal + " " + ville;
	}
	
	public void setUid(int uid){
		uidAdresse = uid;
	}
	public int getUid() {
		return uidAdresse;
	}
	
}

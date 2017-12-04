
public class Personne {
	String nom;
	String prenom;
	String mail;
	String tel;
	Date date;
	Adresse adresse;
	
	public Personne(String n, String p, String m, String t, Date d, Adresse adr){
		nom=n;
		prenom=p;
		mail=m;
		tel=t;
		date=d;
		adresse = adr;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getMail() {
		return mail;
	}

	public String getTel() {
		return tel;
	}

	public Date getDate() {
		return date;
	}
	
	public Adresse getAdresse() {
		return adresse;
	}
}

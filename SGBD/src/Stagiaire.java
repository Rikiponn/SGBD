import java.util.HashSet;

public class Stagiaire extends Personne{

	private HashSet<Integer> activiteList = new HashSet<Integer>();
	private HashSet<Integer> niveauList = new HashSet<Integer>();
	Date debut;
	Date fin;
	
	public Stagiaire(String n, String p, String m, String t, Date d, Adresse adr, HashSet<Integer> activites, HashSet<Integer> niveaux) {
		super(n, p, m, t, d, adr);
		activiteList = activites;
		niveauList = niveaux;
	}
	
	public Stagiaire(String n, String p, String m, String t, Date d, Adresse adr) {
		super(n, p, m, t, d, adr);
	}
	
	public HashSet<Integer> getActivites(){
		return activiteList;
	}
	
	public HashSet<Integer> getNiveaux(){
		return niveauList;
	}
	
	public String toString(){
		return nom + " " + prenom + ", mail : " + mail + ", tel : " + tel + ", né le " + date.toString() + ", adresse : " + adresse.toString();
	}
	
	public void setDebut(Date d){
		debut = d;
	}
	public void setFin(Date d){
		fin = d;
	}
	public Date getDebut(){
		return debut;
	}
	public Date getFin(){
		return fin;
	}

	
	
}

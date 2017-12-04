import java.util.HashSet;

public class Seance {
	int uidSeance=0;
	int uidGroupe;
	int uidActivite;
	int duree;
	Date date;
	HashSet<Integer> materiel;
	HashSet<String> moniteurs = new HashSet<String>();
	
	public Seance(int g, int a, int d, Date da, HashSet<Integer> m){
		uidGroupe = g;
		uidActivite = a;
		duree = d;
		date = da;
		materiel = m;
	}
	public Seance(int g, int a, int d, Date da){
		uidGroupe = g;
		uidActivite = a;
		duree = d;
		date = da;
		materiel = new HashSet<Integer>();
	}
	public Seance(int g, int a, int d, Date da, int num){
		uidGroupe = g;
		uidActivite = a;
		duree = d;
		date = da;
		uidSeance = num;
		materiel = new HashSet<Integer>();
	}
	
	public void setUidSeance(int num){
		uidSeance = num;
	}
	
	public void setUidGroupe(int num){
		uidGroupe = num;
	}
	
	public void setUidActivite(int num){
		uidActivite = num;
	}
	
	public int getGroupe(){
		return uidGroupe;
	}
	public int getActivite(){
		return uidActivite;
	}
	public int getDuree(){
		return duree;
	}
	public Date getDate(){
		return date;
	}
	public HashSet<Integer> getMateriel(){
		return materiel;
	}
	public HashSet<String> getMoniteurs(){
		return moniteurs;
	}
	public void addMoniteur(String mail){
		moniteurs.add(mail);
	}
	public int getNumSeance(){
		return uidSeance;
	}
	
	public String toString(){
		return "Seance du groupe " + uidGroupe + " le " + date.toString() + " d'une durée de " + duree + " pour l'activité " + Menu.getActiviteNameByNumber(uidActivite);
	}
	public static void main(String[] args) {
		
		System.out.println("\n[OK] Activité ajoutée\n");
	}
}

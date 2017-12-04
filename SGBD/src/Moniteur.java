import java.util.HashSet;

public class Moniteur extends Personne{

	Badge badge;
	HashSet<Integer> habiliteEncadrer = new HashSet<Integer>();
	
	public Moniteur(String n, String p, String m, String t, Date d, Adresse adr) {
		super(n, p, m, t, d, adr);
	}
	
	public void setBadge(Badge b){
		badge = b;
	}
	
	public Badge getBadge(){
		return badge;
	}
	
	public void addActivite(int num){
		habiliteEncadrer.add(num);
	}
	
	public HashSet<Integer> getHabilite(){
		return habiliteEncadrer;
	}
	
	

}

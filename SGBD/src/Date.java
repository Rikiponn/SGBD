
public class Date {
	int jour;
	int mois;
	int annee;
	int heure;
	int min;
	String dateString;
	
	public Date(int j, int m, int a, int h, int min){
		jour = j;
		mois = m;
		annee = a;
		heure = h;
		this.min = min;
		dateString = jour + "/" + mois + "/" + annee + "/" + heure + "/" + min;
	}
	
	public Date(int j, int m, int a){
		jour = j;
		mois = m;
		annee = a;
		dateString = jour + "/" + mois + "/" + annee;
	}
	
	public Date(String st){
		dateString = st;
		String[] s = st.split("/");
		jour = Integer.parseInt(s[0]);
		mois = Integer.parseInt(s[1]);
		annee = Integer.parseInt(s[2]);
		if(s.length>3) heure = Integer.parseInt(s[3]);
		if(s.length>4) min = Integer.parseInt(s[4]);
	}
	
	public int getJour(){
		return jour;
	}
	public int getMois(){
		return mois;
	}
	public int getAnnee(){
		return annee;
	}
	public int getHeure(){
		return heure;
	}
	public int getMin(){
		return min;
	}
	
	public String toString(){
		return dateString;
	}
}

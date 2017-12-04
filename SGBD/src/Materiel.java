
public class Materiel {
	int uidMateriel;
	String type;
	String marque;
	String modele;
	Niveau niveau;
	int stock;
	
	public Materiel(String t, String m, String mo, int n, int s){
		type = t;
		marque = m;
		modele = mo;
		if(n==1) niveau = Niveau.Debutant;
		if(n==2) niveau = Niveau.Confirme;
		if(n==3) niveau = Niveau.Expert;
		stock = s;
	}
	
	public void setUid(int uid){
		uidMateriel = uid;
	}
	
	public int getUid(){
		return uidMateriel;
	}
	
	public String getType(){
		return type;
	}
	public String getMarque(){
		return marque;
	}
	public String getModele(){
		return modele;
	}
	public String getNiveau(){
		if(niveau==Niveau.Debutant) return "Debutant";
		if(niveau==Niveau.Confirme) return "Confirme";
		if(niveau==Niveau.Expert) return "Expert";
		return "";
	}
	public int getStock(){
		return stock;
	}
	
	public String toString(){
		return "Materiel type " + type + ", marque " + marque + ", modèle " + modele + ", niveau" + niveau.toString()+ ", " + stock + " disponibles";
	}
}

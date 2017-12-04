
public class Activite {
	int uidActivite;
	int uidCentre;
	String nom;
	Categorie categorie;
	String description;
	int nbMin;
	int nbMax;
	
	public Activite(String nom, int categorie, String description, int nbMin, int nbMax){
		this.nom = nom;
		if(categorie==1) this.categorie = Categorie.Montagne;
		if(categorie==2) this.categorie = Categorie.Nautique;
		if(categorie==3) this.categorie = Categorie.Air;
		this.description = description;
		this.nbMin = nbMin;
		this.nbMax = nbMax;
		
	}
	
	public void setCentre(int c){
		uidCentre = c;
	}
	public int getCentre(){
		return uidCentre;
	}
	
	public void setUid(int uid){
		uidActivite = uid;
	}
	
	public int getUid(){
		return uidActivite;
	}
	
	public String getNom(){
		return nom;
	}
	public String getCategorie(){
		if(categorie==Categorie.Nautique) return "Nautique";
		if(categorie==Categorie.Montagne) return "Montagne";
		if(categorie==Categorie.Air) return "Air";
		return "";
		
	}
	public String getDescription(){
		return description;
	}

	public int getNbMin(){
		return nbMin;
	}
	public int getNbMax(){
		return nbMax;
	}
	
	public String toString(){
		return "Nom : " + nom + " , catégorie : " + categorie;
	}
	
	
	
	
}

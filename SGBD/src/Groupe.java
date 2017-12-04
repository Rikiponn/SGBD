
public class Groupe {
	int uidGroupe;
	Niveau niveau;
	
	public Groupe(int uid, String niv){
		uidGroupe=uid;
		if(niv.equals("Debutant")) niveau = Niveau.Debutant;
		if(niv.equals("Confirme")) niveau = Niveau.Confirme;
		if(niv.equals("Expert")) niveau = Niveau.Expert;
	}
	
	public int getUid(){
		return uidGroupe;
	}
	
	public String getNiveau(){
		if(niveau==Niveau.Debutant) return "Debutant";
		if(niveau==Niveau.Confirme) return "Confirme";
		if(niveau==Niveau.Expert) return "Expert";
		return "";
	}
	
	public String toString(){
		return "Groupe " + uidGroupe + ", niveau " + niveau.toString();
	}
}

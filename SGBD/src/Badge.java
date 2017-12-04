
public class Badge {
	int id;
	Date validite;
	
	public Badge(int i, Date v){
		id = i;
		validite = v;
	}
	
	public int getUid(){
		return id;
	}
	public Date getValidite(){
		return validite;
	}
}

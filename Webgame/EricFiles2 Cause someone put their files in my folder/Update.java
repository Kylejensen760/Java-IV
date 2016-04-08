import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Update implements Serializable{
	private List<Player> pList;
	
	public Update(List<Player> p){
		pList = p;
	}
	
	public List<Player> getPList() {
		return pList;
	}
}

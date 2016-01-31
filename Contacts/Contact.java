import java.io.Serializable;

@SuppressWarnings("serial")
public class Contact implements Comparable<Contact>, Serializable {

	private String [] data;
	
	public Contact (String f, String l, String a, String c, String s, String z,
										String e, String hp, String cp){
		data = new String[] {f, l, a, c, s, z, e, hp, cp};
	}
	
	public String getData(Integer index) {
		return data[index];
	}
	
	public void setData(Integer index, String newData) {
		data[index] = newData;
	}

	@Override
	public int compareTo(Contact arg0) {
		
		return 0;
	}
}

import java.io.Serializable;

public class Contact implements Comparable<Contact>, Serializable {

	private String [] data;
	
	public Contact (String f, String l, String e, String a, String c, String s,
										String z, String hp, String cp){
		data = new String[] {f, l, e, a, c, s, z, hp, cp};
	}
	
	public String getData(Integer index) {
		return data[index];
	}
	
	public void setData(Integer index, String newData) {
		data[index] = newData;
	}
	
/*	String first = "";
	String last = "";
	String email = "";
	String add = "";
	String city = "";
	String state = "";
	int zip;
	int home;
	int cell;
	
	public Contact(){
		
	}
	
	public Contact(String f, String l, String e, String a, String c, 
												String s, int z, int hp, int cp) {
		first = f;
		last = l;
		email = e;
		add = a;
		city = c;
		state = s;
		zip = z;
		home = hp;
		cell = cp;
	}
	
	public String getFirst() {
		return first;	
	}
	
	public void setFirst(String f) {
		first = f;
	}
	
	public String getLast() {
		return last;	
	}
	
	public void setLast(String l) {
		last = l;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String e) {
		email = e;
	}
	
	public String getAdd() {
		return add;
	}
	
	public void setAdd(String a) {
		add = a;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String c) {
		city = c;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String s) {
		state = s;
	}
	
	public int getZip() {
		return zip;
	}
	
	public void setZip(int z) {
		zip = z;
	}
	
	public int getHome() {
		return home;
	}
	
	public void setHome(int h) {
		home = h;
	}
	
	public int getCell() {
		return cell;
	}
	
	public void setCell(int c) {
		cell = c;
	}*/

	@Override
	public int compareTo(Contact arg0) {
		
		return 0;
	}
}

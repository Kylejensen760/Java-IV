import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class CustomTableModel extends AbstractTableModel {
	
	private ArrayList<Contact> data = new ArrayList<Contact>();
	private String [] columnNames = {"First Name", "Last Name"};
	
	public CustomTableModel() {

	}
	
	@Override
	public int getColumnCount() {
		return 2;
	}
	
	public String getColumnName(int i) {
		return columnNames[i];
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int i, int j) {
		return data.get(i).getData(j);
	}
	
	public void addContact(String f, String l, String a, String c, String s, 
											String z, String e, String hp, String cp) {
		Contact newContact = new Contact(f, l, a, c, s, z, e, hp, cp);
		data.add(newContact);
	    int row = data.indexOf(newContact);
	    fireTableRowsInserted(row, row);
	}
	
	public void editContact(int r, Contact con, String f, String l, String a, String c, String s, 
			String z, String e, String hp, String cp ) {
		
		con.setData(0, f);
		con.setData(1, l);
		con.setData(2, a);
		con.setData(3, c);
		con.setData(4, s);
		con.setData(5, z);
		con.setData(6, e);
		con.setData(7, hp);
		con.setData(8, cp);
		fireTableRowsUpdated(r, r);		
	}
	
	public void removeData(int rowIndex) {
		data.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	public Contact getContact(int index) {
		return data.get(index);
	}
}

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel{
	
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
	
	public void addContact(String f, String l, String e, String a, String c, 
											String s, String z, String hp, String cp) {
		Contact newContact = new Contact(f, l, e, a, c, s, z, hp, cp);
		data.add(newContact);
	    int row = data.indexOf(newContact);
	    fireTableRowsInserted(row, row);
	}
	
	public void removeData(int rowIndex) {
		data.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	public Contact getContact(int index) {
		return data.get(index);
	}
}

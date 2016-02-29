import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class CustomTableModel extends AbstractTableModel {
	
	private ArrayList<String> users = new ArrayList<String>();
	private String [] columnNames = {"Select", "UserID"};
	
	public CustomTableModel(ArrayList<String> loadData) {
		users = loadData;
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
		return users.size();
	}

	@Override
	public String getValueAt(int row, int column) {
		String user = users.get(row);

		switch(column) {
			case 1:
				return user;
			default:
				return "";
		}
	}
	
	public void updateTable(ArrayList<String> update) {
		users = update;
		fireTableDataChanged();
	}
}

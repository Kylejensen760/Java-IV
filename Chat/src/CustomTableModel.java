import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel{
	ArrayList<User> list = new ArrayList<User>();
	private String [] columnNames = {"Select", "User Name"};
	
	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}
	
	public String getColumnName(int i) {
		return columnNames[i];
	}

	@Override
	public Object getValueAt(int row, int column) {
		User object = list.get(row);

		switch(column) {
			case 0:
				return object.select;
			case 1: 
				return object.getUserName();
			default:
				return "unknown";
		}
	}
	
	 @Override
     public Class getColumnClass(int column) {
         switch (column) {
             case 0:
                 return Boolean.class;
             default:
                 return String.class;
         }
     }
	
	public void addUser(String userName) {
		User newUser = new User(userName);
		list.add(newUser);
		int row = list.indexOf(newUser);
		fireTableRowsInserted(row, row);
	}
	
	public void removeUser(int rowIndex) {
		list.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
}

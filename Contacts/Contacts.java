import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;

public class Contacts implements ActionListener
{
	Color background = new Color(190, 210, 230);
	Color darkerBackground = new Color(160, 180, 200);
	Color lighterBackground = new Color (215, 235, 255);
	Font baseFont = new Font("Sans Serif", Font.PLAIN, 20);
	Font searchFont = new Font("Sans Serif", Font.PLAIN, 28);
	private JTextField searchBar;
	private CustomTableModel table;
	private JTable displayArea;
	private TableRowSorter<CustomTableModel> sorter;
	private JLabel welcomeText;
	private JLabel titleL;
	private JLabel firstNameL;
	private JLabel lastNameL;
	private JLabel addL;
	private JLabel cityL;
	private JLabel stateL;
	private JLabel zipL;
	private JLabel emailL;
	private JLabel homePhoneL;
	private JLabel cellPhoneL;
	public JTextField firstNameTF;
	private JTextField lastNameTF;
	private JTextField addTF;
	private JTextField cityTF;
	private JTextField stateTF;
	private JTextField zipTF;
	private JTextField emailTF;
	private JTextField homePhoneTF;
	private JTextField cellPhoneTF;
	private BorderLayout layout;
	private JButton addButton;
	private JButton editButton;
	private JButton delButton;
	private JButton submitButton;
	private JButton cancelButton;
	private JPanel welcomePanel;
	private JPanel inputPanel;
	private JPanel mainPanel;
	private JPanel sidePanel;
	private JPanel viewPanel;
	private int buttonSelect = -1;

	public static void main(String[] args) throws Exception
	{
		new Contacts();
	}

	public Contacts() throws Exception
	{
		//Look and feel, mainPanel set up, and frame set up
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		mainPanel = new JPanel();
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Contacts");
		frame.add(mainPanel);
		frame.setSize(500, 800);

		mainPanel.setLayout(layout = new BorderLayout());
		mainPanel.setBackground(Color.black);

		//welcomePanel ---> initial display on startup
		welcomePanel = new JPanel();
		welcomePanel.setPreferredSize(new Dimension(775, 800));
		welcomePanel.setBackground(lighterBackground);
		welcomeText = new JLabel("Welcome to Contacts!");
		welcomeText.setFont(searchFont);
		welcomePanel.add(welcomeText);
		mainPanel.add(welcomePanel, BorderLayout.EAST);

		//sidePanel set up --> holds search bar, buttons, and contact list(displayArea)
		sidePanel = new JPanel();
		sidePanel.setPreferredSize(new Dimension(300, 800));
		sidePanel.setBackground(background);
		mainPanel.add(sidePanel, BorderLayout.WEST);

		//Initialization of searchBar textField
		searchBar = new JTextField("Search");
		searchBar.setPreferredSize(new Dimension(290, 50));
		searchBar.setLocation(13, 13);
		searchBar.setEditable(true);
		searchBar.addActionListener(this);
		searchBar.setFont(searchFont);
		sidePanel.add(searchBar);
		//Search bar implementation
		searchBar.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = searchBar.getText();

                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (searchBar.getText().trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchBar.getText()));
                }
            }

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				 throw new UnsupportedOperationException("Not supported yet."); 
			}
		});

		//Makes search bar text disappear after gaining focus
		searchBar.addFocusListener(new FocusListener() {
		    public void focusGained(FocusEvent e) {
		        searchBar.setText("");
		    }

		    public void focusLost(FocusEvent e) {
		      //  if(searchBar.getText().equals("")) {
		        	searchBar.setText("Search");
		        	sorter.setRowFilter(null);
		     //   }
		    }
		});
		
		//Placeholder for contact list (displayArea table)
		table = new CustomTableModel();
		displayArea = new JTable(table);
		displayArea.getSelectionModel().addListSelectionListener(new RowListener());
		JScrollPane pane = new JScrollPane(displayArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Font tableFont = new Font("", Font.PLAIN, 18);
		displayArea.setFont(tableFont);
		displayArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		displayArea.setRowSelectionAllowed(true);
		displayArea.setRowHeight(35);
		pane.setPreferredSize(new Dimension(290, 624));
		sidePanel.add(pane);
		
		//Auto sorts table in ascending order by first name
		displayArea.setRowSorter(sorter = new TableRowSorter<CustomTableModel>(table));
		ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
		int columnIndexToSort = 1;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		sorter.sort();

		//Side Panel buttons
		addButton = new JButton("Add");
		addButton.setPreferredSize(new Dimension(93, 50));
		addButton.addActionListener(this);
		sidePanel.add(addButton);
		editButton = new JButton("Edit");
		editButton.setPreferredSize(new Dimension(93, 50));
		editButton.addActionListener(this);
		sidePanel.add(editButton);
		delButton = new JButton("Delete");
		delButton.setPreferredSize(new Dimension(93, 50));
		delButton.addActionListener(this);
		sidePanel.add(delButton);

		//Input Panel set up --> handles add/edit fields
		inputPanel = new JPanel(null);
		inputPanel.setPreferredSize(new Dimension(775, 800));
		inputPanel.setBackground(lighterBackground);

		//First name label/text field set up
		titleL = new JLabel();
		titleL.setFont(new Font("", Font.BOLD, 35));
		titleL.setSize(new Dimension(215, 30));
		titleL.setLocation(280, 20);
		inputPanel.add(titleL);

		firstNameL = new JLabel("First Name");
		firstNameL.setFont(baseFont);
		firstNameL.setSize(new Dimension(100, 50));
		firstNameL.setLocation(75, 90);
		firstNameTF = new JTextField();
		firstNameTF.setSize(200, 25);
		firstNameTF.setLocation(74,128);
		inputPanel.add(firstNameL);
		inputPanel.add(firstNameTF);

		lastNameL = new JLabel("Last Name");
		lastNameL.setFont(baseFont);
		lastNameL.setSize(new Dimension(100, 50));
		lastNameL.setLocation(300, 90);
		lastNameTF = new JTextField();
		lastNameTF.setSize(200, 25);
		lastNameTF.setLocation(299, 128);
		inputPanel.add(lastNameL);
		inputPanel.add(lastNameTF);

		addL = new JLabel("Street Address");
		addL.setFont(baseFont);
		addL.setSize(new Dimension(150, 50));
		addL.setLocation(75, 175);
		addTF = new JTextField();
		addTF.setSize(315, 25);
		addTF.setLocation(74, 213);
		inputPanel.add(addL);
		inputPanel.add(addTF);

		cityL = new JLabel("City");
		cityL.setFont(baseFont);
		cityL.setSize(new Dimension(100, 50));
		cityL.setLocation(75, 250);
		cityTF = new JTextField();
		cityTF.setSize(225, 25);
		cityTF.setLocation(74, 288);
		inputPanel.add(cityL);
		inputPanel.add(cityTF);

		stateL = new JLabel("State");
		stateL.setFont(baseFont);
		stateL.setSize(new Dimension(100, 50));
		stateL.setLocation(340, 250);
		stateTF = new JTextField();
		stateTF.setSize(50, 25);
		stateTF.setLocation(339, 288);
		inputPanel.add(stateL);
		inputPanel.add(stateTF);

		zipL = new JLabel("Zip Code");
		zipL.setFont(baseFont);
		zipL.setSize(new Dimension(100, 50));
		zipL.setLocation(420, 250);
		zipTF = new JTextField();
		zipTF.setSize(80, 25);
		zipTF.setLocation(419, 288);
		inputPanel.add(zipL);
		inputPanel.add(zipTF);

		emailL = new JLabel("Email Address");
		emailL.setFont(baseFont);
		emailL.setSize(new Dimension(150, 50));
		emailL.setLocation(75, 325);
		emailTF = new JTextField();
		emailTF.setSize(200, 25);
		emailTF.setLocation(74, 363);
		inputPanel.add(emailL);
		inputPanel.add(emailTF);

		homePhoneL = new JLabel("Home Phone #");
		homePhoneL.setFont(baseFont);
		homePhoneL.setSize(new Dimension(150, 50));
		homePhoneL.setLocation(75, 400);
		homePhoneTF = new JTextField();
		homePhoneTF.setSize(200, 25);
		homePhoneTF.setLocation(74, 438);
		inputPanel.add(homePhoneL);
		inputPanel.add(homePhoneTF);

		cellPhoneL = new JLabel("Cell Phone #");
		cellPhoneL.setFont(baseFont);
		cellPhoneL.setSize(new Dimension(150, 50));
		cellPhoneL.setLocation(75, 475);
		cellPhoneTF = new JTextField();
		cellPhoneTF.setSize(200, 25);
		cellPhoneTF.setLocation(74, 513);
		inputPanel.add(cellPhoneL);
		inputPanel.add(cellPhoneTF);

		submitButton = new JButton("Submit");
		submitButton.setSize(new Dimension(100, 25));
		submitButton.setLocation(280, 550);
		submitButton.addActionListener(this);
		inputPanel.add(submitButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setSize(new Dimension(100, 25));
		cancelButton.setLocation(399, 550);
		cancelButton.addActionListener(this);
		inputPanel.add(cancelButton);

		//Test data
		table.addContact("Eric", "Rogers", "74 Chestnut St", "North Adams", "MA", "01247",
				"eric.j.rogers18@gmail.com", "4138845031", "4138845031");
		table.addContact("Chris", "Malloy", "74 Chestnut St", "North Adams", "MA", "01247",
				"eric.j.rogers18@gmail.com", "4138845031", "4138845031");
		table.addContact("John", "Doe", "24 Henderson Rd", "Clarksburg", "MA", "01247",
				"john.doe24@hotmail.com", "4138841933", "4136635532");
		table.addContact("Marie", "Willis", "80 Shaft St.", "Danville", "NH", "03819",
				"marie.Willis55@yahoo.com", "5532217463", "5533264812");

		frame.pack();
		mainPanel.requestFocusInWindow();
		frame.setVisible(true);
	}

	//Display selected contact from table
	public void displayContact(int r) {
		
		int index = displayArea.getRowSorter().convertRowIndexToModel(r);
		Contact displayContact = table.getContact(index);
		String [] displayData = new String[9];
		for(int i = 0; i < 9; i++) {
			displayData[i] = displayContact.getData(i);
		}

		viewPanel = new JPanel(new GridBagLayout());
		viewPanel.setPreferredSize(new Dimension(775, 800));
		viewPanel.setBackground(lighterBackground);

		Font viewFont = new Font("", Font.BOLD, 35);
		JLabel details = new JLabel("<html>" + "Name: " + displayData[0] + " " + displayData[1] + "<br>" +
				"Address Line: " + displayData[2] + "<br>" + displayData[3] + " " + displayData[4] + " " +
				displayData[5] + "<br>" + "Email: " + displayData[6] + "<br>" + "Home: " + displayData[7] +
				"<br>" + "Cell: " + displayData[8] + "</html>");
		details.setFont(viewFont);
		details.setSize(250, 250);
		viewPanel.add(details);

		mainPanel.remove(layout.getLayoutComponent(BorderLayout.EAST));
		mainPanel.add(viewPanel, BorderLayout.EAST);
		mainPanel.revalidate();
		mainPanel.repaint();
	}
	
	private void clearTF() {
		firstNameTF.setText("");
		lastNameTF.setText("");
		addTF.setText("");
		cityTF.setText("");
		stateTF.setText("");
		zipTF.setText("");
		emailTF.setText("");
		homePhoneTF.setText("");
		cellPhoneTF.setText("");
	}

	//Allows table to react and display contact when row is selected
    private class RowListener implements ListSelectionListener {
        @Override
    	public void valueChanged(ListSelectionEvent event) {            
            if(displayArea.getRowCount() > 0 && displayArea.getSelectedRow() > -1) { 
            	displayContact(displayArea.getSelectedRow()); 
            }
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Add Button
		if(e.getSource() == addButton) {
			if(displayArea.getRowCount() > 0) {
				displayArea.clearSelection();
			}
			buttonSelect = 0;
			titleL.setText("New Contact");
			mainPanel.remove(layout.getLayoutComponent(BorderLayout.EAST));
			mainPanel.add(inputPanel, BorderLayout.EAST);
			mainPanel.revalidate();
			mainPanel.repaint();
		}

		//Edit Button
		if(e.getSource() == editButton && displayArea.getSelectedRow() > -1) {
			buttonSelect = 1;
			titleL.setText("Edit Contact");
			mainPanel.remove(layout.getLayoutComponent(BorderLayout.EAST));
			int index = displayArea.getRowSorter().convertRowIndexToModel(displayArea.getSelectedRow());
			Contact editContact = table.getContact(index);
			firstNameTF.setText(editContact.getData(0));
			lastNameTF.setText(editContact.getData(1));
			addTF.setText(editContact.getData(2));
			cityTF.setText(editContact.getData(3));
			stateTF.setText(editContact.getData(4));
			zipTF.setText(editContact.getData(5));
			emailTF.setText(editContact.getData(6));
			homePhoneTF.setText(editContact.getData(7));
			cellPhoneTF.setText(editContact.getData(8));
			
			mainPanel.add(inputPanel, BorderLayout.EAST);
			mainPanel.revalidate();
			mainPanel.repaint();
		}

		//Delete Button
		if(e.getSource() == delButton) {
			if(displayArea.getSelectedRow() > -1) {
				if(displayArea.getRowCount() > 0) {
					displayContact(0); 
				}
				else{
					mainPanel.remove(layout.getLayoutComponent(BorderLayout.EAST));
					mainPanel.add(welcomePanel);
				}
				
				int removeIndex = displayArea.getRowSorter().convertRowIndexToModel(displayArea.getSelectedRow());
				displayArea.clearSelection();
				table.removeData(removeIndex) ;
			}
			
			mainPanel.revalidate();
			mainPanel.repaint();
		}

		//Submit button for both add/edit panels
		if(e.getSource() == submitButton) {
			//Add panel submit button
			if(buttonSelect == 0) {
			table.addContact(firstNameTF.getText(), lastNameTF.getText(), addTF.getText(),
					cityTF.getText(), stateTF.getText(), zipTF.getText(), emailTF.getText(),
					homePhoneTF.getText(), cellPhoneTF.getText());
			buttonSelect = -1;
			clearTF();
			displayContact(displayArea.getRowCount() - 1);
			}
			
			//Edit panel submit button
			if(buttonSelect == 1) {
				int rowSelected = displayArea.getRowSorter().convertRowIndexToModel
						(displayArea.getSelectedRow());
				table.editContact(rowSelected, table.getContact(rowSelected), 
					firstNameTF.getText(), lastNameTF.getText(), addTF.getText(),
					cityTF.getText(), stateTF.getText(), zipTF.getText(), emailTF.getText(),
					homePhoneTF.getText(), cellPhoneTF.getText());
				buttonSelect = -1;
				
				clearTF();
				displayContact(displayArea.getRowSorter().convertRowIndexToModel(rowSelected));
			}
		}

		//Cancel button for both add/edit panels
		if(e.getSource() == cancelButton && table.getRowCount() > 0) {
			clearTF();
			displayContact(0);
		}
		else if(e.getSource() == cancelButton && table.getRowCount() < 1){
			clearTF();
			mainPanel.remove(layout.getLayoutComponent(BorderLayout.EAST));
			mainPanel.add(welcomePanel);
			mainPanel.requestFocusInWindow();
			mainPanel.revalidate();
			mainPanel.repaint();
		}
	}
}

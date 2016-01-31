import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.File;

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
	private JLabel welcomeText;
	private JLabel titleAdd;
	private JLabel titleEdit;
	private JLabel titleDelete;
	private JLabel firstNameL;
	private JLabel lastNameL;
	private JLabel addL;
	private JLabel cityL;
	private JLabel stateL;
	private JLabel zipL;
	private JLabel emailL;
	private JLabel homePhoneL;
	private JLabel cellPhoneL;
	private JTextField firstNameTF;
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
	private JPanel inputPanel;
	private JPanel mainPanel;
	private JPanel sidePanel;
	private JPanel viewPanel;
	private JPanel deletePanel;
	private JPanel editPanel;

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
		frame.setTitle("Contacts");
		frame.add(mainPanel);
		frame.setSize(1100, 800);
		mainPanel.setLayout(layout = new BorderLayout());
		mainPanel.setBackground(Color.black);

		//welcomePanel ---> initial display on startup
		JPanel welcomePanel = new JPanel();
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
		searchBar = new JTextField();
		searchBar.setPreferredSize(new Dimension(290, 50));
		searchBar.setLocation(13, 13);
		searchBar.setEditable(true);
		searchBar.setFont(searchFont);
		searchBar.setText("Search...");
		sidePanel.add(searchBar);

		//Placeholder for contact list (displayArea table)
		table = new CustomTableModel();
		displayArea = new JTable(table);
		displayArea.getSelectionModel().addListSelectionListener(new RowListener());
		JScrollPane pane = new JScrollPane(displayArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Font tableFont = new Font("", Font.PLAIN, 18);
		displayArea.setFont(tableFont);
		displayArea.setAutoCreateRowSorter(true);
		displayArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		displayArea.setRowSelectionAllowed(true);
		displayArea.setRowHeight(35);
		pane.setPreferredSize(new Dimension(290, 624));
		sidePanel.add(pane);

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
		titleAdd = new JLabel("New Contact");
		titleAdd.setFont(new Font("", Font.BOLD, 35));
		titleAdd.setSize(new Dimension(215, 30));
		titleAdd.setLocation(280, 20);
		inputPanel.add(titleAdd);

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

		//Panel called when Edit button is pressed
		editPanel = new JPanel(null);
		editPanel.setPreferredSize(new Dimension(775, 800));
		editPanel.setBackground(lighterBackground);

		titleEdit = new JLabel("Edit Contact");
		titleEdit.setFont(new Font("", Font.BOLD, 35));
		titleEdit.setSize(new Dimension(215, 30));
		titleEdit.setLocation(280, 20);
		editPanel.add(titleEdit);

		//Panel called when Delete button is pressed
		deletePanel = new JPanel(null);
		deletePanel.setPreferredSize(new Dimension(775, 800));
		deletePanel.setBackground(lighterBackground);

		titleDelete = new JLabel("Delete Contact");
		titleDelete.setFont(new Font("", Font.BOLD, 35));
		titleDelete.setSize(new Dimension(275, 30));
		titleDelete.setLocation(280, 20);
		deletePanel.add(titleDelete);

		//Test data
		table.addContact("Eric", "Rogers", "74 Chestnut St", "North Adams", "MA", "01247",
				"eric.j.rogers18@gmail.com", "4138845031", "4138845031");
		table.addContact("Chris", "Malloy", "74 Chestnut St", "North Adams", "MA", "01247",
				"eric.j.rogers18@gmail.com", "4138845031", "4138845031");
		table.addContact("John", "Doe", "24 Henderson Rd", "Clarksburg", "MA", "01247",
				"john.doe24@hotmail.com", "4138841933", "4136635532");
		table.addContact("Marie", "Willis", "80 Shaft St.", "Danville", "NH", "03819",
				"marie.Willis55@yahoo.com", "5532217463", "5533264812");

		frame.setVisible(true);
	}

	public void displayContact(int r) {

		Contact displayContact = table.getContact(r);
		String [] displayData = new String[9];
		for(int i = 0; i < 9; i++) {
			displayData[i] = displayContact.getData(i);
		}

		viewPanel = new JPanel(new GridBagLayout());
		viewPanel.setPreferredSize(new Dimension(775, 800));
		viewPanel.setBackground(lighterBackground);

		Font viewFont = new Font("", Font.BOLD, 40);
		JLabel details = new JLabel("<html>" + displayData[0] + " " + displayData[1] + "<br>" +
				displayData[2] + "<br>" + displayData[3] + ", " + displayData[4] + " " +
				displayData[5] + "<br>" + displayData[6] + "<br>" + "Home: " + displayData[7] +
				"<br>" + "Cell: " + displayData[8] + "</html>");
		details.setFont(viewFont);
		details.setSize(250, 250);
		details.setLocation(250, 100);
		viewPanel.add(details);

		layout.removeLayoutComponent(layout.getLayoutComponent(BorderLayout.EAST));
		mainPanel.add(viewPanel, BorderLayout.EAST);
		mainPanel.revalidate();
	}

    private class RowListener implements ListSelectionListener {
        @Override
    	public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
            displayContact(displayArea.getSelectedRow());
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addButton) {
			layout.removeLayoutComponent(layout.getLayoutComponent(BorderLayout.EAST));
			mainPanel.add(inputPanel, BorderLayout.EAST);
			mainPanel.revalidate();


			System.out.println("Add Button Pushed");
		}

		if(e.getSource() == editButton) {
			layout.removeLayoutComponent(layout.getLayoutComponent(BorderLayout.EAST));
			mainPanel.add(editPanel, BorderLayout.EAST);
			mainPanel.revalidate();

			System.out.println("Edit Button Pushed");
		}

		if(e.getSource() == delButton) {
			layout.removeLayoutComponent(layout.getLayoutComponent(BorderLayout.EAST));
			mainPanel.add(deletePanel, BorderLayout.EAST);
			mainPanel.revalidate();

			System.out.println("Delete Button Pushed");
		}

		if(e.getSource() == submitButton) {
			System.out.println("Submit Button Pushed");
		}

		if(e.getSource() == cancelButton) {
			System.out.println("Cancel Button Pushed");
		}
	}
}

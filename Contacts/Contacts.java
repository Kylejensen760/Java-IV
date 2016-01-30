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
	//Variables for welcomePanel
	private JLabel welcomeText;
	//Variables for addPanel
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel streetLabel;
	private JLabel cityLabel;
	private JLabel stateLabel;
	private JLabel zipLabel;
	private JLabel emailLabel;
	private JLabel homePhoneLabel;
	private JLabel cellPhoneLabel;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField street;
	private JTextField city;
	private JTextField state;
	private JTextField zip;
	private JTextField email;
	private JTextField homePhone;
	private JTextField cellPhone;
	private BorderLayout layout;
	private JButton addButton;
	private JButton editButton;
	private JButton delButton;
	private JPanel addPanel;
	private JPanel mainPanel;
	private JPanel viewPanel;

	public static void main(String[] args) throws Exception
	{
		new Contacts();
	}

	public Contacts() throws Exception
	{
		//Taken from Calculator program
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		mainPanel = new JPanel();
		JFrame frame = new JFrame();
		frame.setTitle("Contacts");
		frame.add(mainPanel);
		frame.setSize(1100, 800);
		mainPanel.setLayout(layout = new BorderLayout());
		mainPanel.setBackground(Color.black);

		JPanel sidePanel = new JPanel();
		sidePanel.setPreferredSize(new Dimension(300, 800));
		sidePanel.setBackground(background);
		mainPanel.add(sidePanel, layout.LINE_START);

		JPanel welcomePanel = new JPanel();
		welcomePanel.setPreferredSize(new Dimension(775, 800));
		welcomePanel.setBackground(lighterBackground);
		mainPanel.add(welcomePanel, layout.LINE_END);
		welcomeText = new JLabel("Welcome to Contacts!");
		welcomeText.setFont(searchFont);
		welcomePanel.add(welcomeText);

		layout.removeLayoutComponent(welcomePanel);

	/*	JPanel viewPanel = new JPanel();
		viewPanel.setPreferredSize(new Dimension(608, 856));
		viewPanel.setBackground(Color.black);
		mainPanel.add(viewPanel, BorderLayout.LINE_END);*/

		addPanel = new JPanel(null);
		addPanel.setPreferredSize(new Dimension(775, 800));
		addPanel.setBackground(lighterBackground);
		
		firstNameLabel = new JLabel("First Name");
		firstNameLabel.setFont(baseFont);
		firstNameLabel.setSize(new Dimension(100, 50));
		firstNameLabel.setLocation(350, 300);
		addPanel.add(firstNameLabel);
		firstName = new JTextField();
		firstName.setSize(100, 40);
		firstName.setLocation(350, 50);
		addPanel.add(firstName);
		mainPanel.add(addPanel, layout.LINE_END);

	/*	JPanel deletePanel = new JPanel();
		deletePanel.setPreferredSize(new Dimension(608, 856));
		deletePanel.setBackground(lighterBackground);
		mainPanel.add(deletePanel, BorderLayout.LINE_END);

		JPanel editPanel = new JPanel();
		editPanel.setPreferredSize(new Dimension(608, 856));
		editPanel.setBackground(lighterBackground);
		mainPanel.add(editPanel, BorderLayout.LINE_END);*/


		//Initialization of textbox, textfield, and JButton locations

		searchBar = new JTextField();
		searchBar.setPreferredSize(new Dimension(290, 50));
		searchBar.setLocation(13, 13);
		searchBar.setEditable(true);
		searchBar.setFont(searchFont);
		searchBar.setText("Search...");
		sidePanel.add(searchBar);

		//Placeholder for contact list
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
		
		
		
		
		table.addContact("Eric", "Rogers", "74 Chestnut St", "North Adams", "MA", "01247", 
				"eric.j.rogers18@gmail.com", "4138845031", "4138845031");
		table.addContact("Chris", "Malloy", "74 Chestnut St", "North Adams", "MA", "01247", 
				"eric.j.rogers18@gmail.com", "4138845031", "4138845031");

		frame.setVisible(true);
	}

	public void displayContact(int r) {
		int row = r;
		
		Contact displayContact = table.getContact(row);
		String [] displayData = new String[9];
		for(int i = 0; i < 9; i++) {
			displayData[i] = displayContact.getData(i);
		}
		
		viewPanel = new JPanel(null);
		viewPanel.setPreferredSize(new Dimension(775, 800));
		viewPanel.setBackground(lighterBackground);
		
		Font viewFont = new Font("", Font.BOLD, 25);
		JLabel details = new JLabel("<html>" + displayData[0] + " " + displayData[1] + "<br>" +
				displayData[2] + "<br>" + displayData[3] + ", " + displayData[4] + " " +
				displayData[5] + "<br>" + displayData[6] + "<br>" + "Home: " + displayData[7] + 
				"<br>" + "Cell: " + displayData[8] + "</html>");
		details.setFont(viewFont);
		details.setSize(600, 600);
		details.setLocation(250, 100);
		viewPanel.add(details);
		
		layout.removeLayoutComponent(addPanel);
		mainPanel.add(viewPanel, layout.LINE_END);
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

	}


}

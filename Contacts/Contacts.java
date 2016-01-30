import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;

public class Contacts
{
	Color background = new Color(190, 210, 230);
	Color darkerBackground = new Color(160, 180, 200);
	Color lighterBackground = new Color (215, 235, 255);
	Font baseFont = new Font("Sans Serif", Font.PLAIN, 20);
	Font searchFont = new Font("Sans Serif", Font.PLAIN, 28);
	private JTextArea contactArea;
	private JTextField searchBar;
	private JScrollPane contactAreaPane;
	private JButton add;
	private JButton edit;
	private JButton delete;
	private CustomTableModel table;
	private JTable displayArea;
	//Variables for welcomePanel
	private JLabel welcomeText;
	//Variables for addPanel
	private JLabel addLabel;
	private JLabel firstNameLabel;
	private JLabel middleInitialLabel;
	private JLabel lastNameLabel;
	private JLabel streetLabel;
	private JLabel cityLabel;
	private JLabel stateLabel;
	private JLabel zipLabel;
	private JLabel emailLabel;
	private JLabel homePhoneNumberLabel;
	private JLabel cellPhoneNumberLabel;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField street;
	private JTextField city;
	private JTextField state;
	private JTextField zip;
	private JTextField email;
	private JTextField homePhoneNumber;
	private JTextField cellPhoneNumber;
	private JButton addPerson;
	private JButton cancelAdd;

	public static void main(String[] args) throws Exception
	{
		Contacts contacts = new Contacts();
	}

	public Contacts() throws Exception
	{
		//Taken from Calculator program
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		JPanel mainPanel = new JPanel();
		JFrame frame = new JFrame();
		frame.setTitle("Contacts");
		frame.add(mainPanel);
		frame.setSize(836, 856);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(darkerBackground);

		JPanel sidePanel = new JPanel();
		sidePanel.setPreferredSize(new Dimension(208, 856));
		sidePanel.setBackground(background);
		mainPanel.add(sidePanel, BorderLayout.LINE_START);

		JPanel welcomePanel = new JPanel();
		welcomePanel.setPreferredSize(new Dimension(608, 856));
		welcomePanel.setBackground(lighterBackground);
		mainPanel.add(welcomePanel, BorderLayout.LINE_END);
		welcomeText = new JLabel("Welcome to Contacts!");
		welcomeText.setFont(searchFont);
		welcomePanel.add(welcomeText);

		welcomePanel.removeAll();
		mainPanel.remove(welcomePanel);

		JPanel viewPanel = new JPanel();
		viewPanel.setPreferredSize(new Dimension(608, 856));
		viewPanel.setBackground(Color.black);
		mainPanel.add(viewPanel, BorderLayout.LINE_END);

		/*variables to assign in the addPanel panel.
			private JLabel firstNameLabel;
			private JLabel middleInitialLabel;
			private JLabel lastNameLabel;
			private JLabel streetLabel;
			private JLabel cityLabel;
			private JLabel stateLabel;
			private JLabel zipLabel;
			private JLabel emailLabel;
			private JLabel homePhoneNumberLabel;
			private JLabel cellPhoneNumberLabel;
			private JTextField firstName;
			private JTextField lastName;
			private JTextField street;
			private JTextField city;
			private JTextField state;
			private JTextField zip;
			private JTextField email;
			private JTextField homePhoneNumber;
			private JTextField cellPhoneNumber;
			private JButton addPerson;
			private JButton cancelAdd;*/


		JPanel addPanel = new JPanel();
		addPanel.setPreferredSize(new Dimension(608, 856));
		addPanel.setBackground(lighterBackground);
		mainPanel.add(addPanel, BorderLayout.LINE_END);
		addLabel = new JLabel("Add Contact");
		addLabel.setFont(searchFont);
		addPanel.add(addLabel);
		firstNameLabel = new JLabel("First Name");
		firstNameLabel.setFont(baseFont);
		firstNameLabel.setLocation(200, 450);
		addPanel.add(firstNameLabel);
		firstName = new JTextField();
		firstName.setSize(240, 40);
		addPanel.add(firstName);

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
		searchBar.setSize(240, 60);
		searchBar.setLocation(13, 13);
		searchBar.setEditable(true);
		searchBar.setFont(searchFont);
		searchBar.setText("Search...");
		sidePanel.add(searchBar);

		//Placeholder for contact list
		table = new CustomTableModel();
		displayArea = new JTable(table);
		JScrollPane pane = new JScrollPane(displayArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Font displayFont = new Font("", Font.PLAIN, 18);
		displayArea.setFont(displayFont);
		displayArea.setAutoCreateRowSorter(true);
		displayArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		displayArea.setRowSelectionAllowed(true);
		displayArea.setRowHeight(35);
		pane.setSize(200, 520);
		pane.setLocation(13, 86);
		sidePanel.add(pane);



		frame.setVisible(true);

		table.addContact("Eric", "Rogers", "eric.j.rogers18@gmail.com", "74 Chestnut St", "North Adams",
								"MA", "01247", "4138845031", "4138845031");
		table.addContact("Chris", "Malloy", "eric.j.rogers18@gmail.com", "74 Chestnut St", "North Adams",
				"MA", "01247", "4138845031", "4138845031");

	}

}

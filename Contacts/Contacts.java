import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
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

		JPanel addPanel = new JPanel(null);
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
		JScrollPane pane = new JScrollPane(displayArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Font displayFont = new Font("", Font.PLAIN, 18);
		displayArea.setFont(displayFont);
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


		frame.setVisible(true);

		table.addContact("Eric", "Rogers", "eric.j.rogers18@gmail.com", "74 Chestnut St", "North Adams",
								"MA", "01247", "4138845031", "4138845031");
		table.addContact("Chris", "Malloy", "eric.j.rogers18@gmail.com", "74 Chestnut St", "North Adams",
				"MA", "01247", "4138845031", "4138845031");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	

}

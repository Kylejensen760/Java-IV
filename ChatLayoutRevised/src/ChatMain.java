import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import java.util.*;
import java.net.*;
import java.io.*;

public class ChatMain implements ActionListener, MessageListener {
	private static ChatMain m_instance = null;
	private JTabbedPane tabPane;
	private JTable userTable;
	private JButton startGroup;

	private ChatMain() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try {	//Sets up connection stuff
			Socket socket = new Socket("localhost", 4324);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			MessageReceiver mr = new MessageReceiver(in, this);
			Thread t = new Thread(mr);
			t.start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		JFrame mainFrame = new JFrame("Chat");
		mainFrame.setSize(new Dimension(1000, 600));
		mainFrame.setResizable(false);

		JPanel container = new JPanel(new BorderLayout());
		container.setSize(new Dimension(1000, 600));
		container.setBackground(Color.BLACK);

		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(275, 545));
		leftPanel.setBackground(new Color(40, 40, 40));
		leftPanel.setBorder(new MatteBorder(5, 5, 5, 0, Color.BLACK));
		container.add(leftPanel, BorderLayout.WEST);

		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Select");
		columnNames.add("UserName");
		Vector<String> data = new Vector<String>();
		userTable = new JTable(data, columnNames);
		Font tableFont = new Font("", Font.BOLD, 15);
		userTable.setFont(tableFont);
		userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userTable.setRowSelectionAllowed(true);
		userTable.setRowHeight(35);
		JScrollPane tablePane = new JScrollPane(userTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tablePane.setPreferredSize(new Dimension(265, 485));
		userTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		userTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		leftPanel.add(tablePane);

		startGroup = new JButton("Start Group");
		startGroup.setPreferredSize(new Dimension(265, 40));
		startGroup.addActionListener(this);
		leftPanel.add(startGroup);

		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(700, 547));
		rightPanel.setBackground(new Color(0, 0, 0));
		container.add(rightPanel, BorderLayout.EAST);

		tabPane = new JTabbedPane();
		tabPane.setPreferredSize(new Dimension(694, 539));
		tabPane.setBackground(new Color(40, 40, 40));
		rightPanel.add(tabPane);
		tabPane.addTab("All Clients", new ChatTab());

		mainFrame.add(container);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	public void deliverMessage(String s) {
		messageArea.append(s); //Needs to see 'messageArea' JTextArea in ChatTab.
	}

	public void removeMe() {
		System.exit(0);
	}

	private void createTab(String title) {
		tabPane.addTab(title, new ChatTab());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startGroup) {
			createTab("Group Chat");
		}
	}

	public static ChatMain startChat() {
		if(m_instance == null) {
			m_instance = new ChatMain();
		}
		return m_instance;
	}
}
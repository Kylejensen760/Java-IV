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
	private final String userID;
	private JTabbedPane tabPane;
	private ArrayList<ChatTab> tabList = new ArrayList<ChatTab>();
	private JTable userTable;
	private JButton startGroup;
	private ObjectOutputStream out;	

	public ChatMain(String inputID) {
		
		userID = inputID;
		//Gui Layout
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
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
		tabList.add(new ChatTab(this, "All Users"));
		tabPane.addTab("All Users", tabList.get(0));
		
		mainFrame.add(container);
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		run();
	}
	
	private void run() {
		try {
			Socket socket = new Socket("localhost", 4324);
			out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			MessageReceiver mr = new MessageReceiver(in, this);
			Thread t = new Thread(mr);
			t.start();
			
			//out.writeObject("@" + userID);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void createTab(String title) {
		ChatTab newTab = new ChatTab(this, title);
		tabList.add(newTab);
		tabPane.addTab(title, newTab);
	}	
	
	public void sendMessageRequest(ChatTab requestingTab) {
		String receiver = requestingTab.getTabTitle();
		String message = requestingTab.getMessageSent();
		
		Message newMessage = new Message("@"+userID, "#"+receiver, "$"+message, new ArrayList<>());
		try {
			out.writeObject(newMessage);
			System.out.println("Client sent message");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deliverMessage(Message m) {
		tabList.get(0).updateConvo(m.getSender() + " " + m.getMessage());
	}

	@Override
	public void removeMe() {
		System.exit(0);
	}
	
	public String getUserID() {
		return userID;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startGroup) {
			int index = userTable.getSelectedRow();
			String receiver = (String) userTable.getValueAt(index, index);
			createTab(receiver);
		}
	}
}
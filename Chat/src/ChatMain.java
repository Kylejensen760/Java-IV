import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private CustomTableModel tableModel;
	private ArrayList<String> clients;
	private JTable userTable;
	private JButton startGroup;
	private ObjectOutputStream out;	
	private Socket socket;

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

		mainFrame.addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent e) {
				removeMe();
		}});
		
		JPanel container = new JPanel(new BorderLayout());
		container.setSize(new Dimension(1000, 600));
		container.setBackground(Color.BLACK);

		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(275, 545));
		leftPanel.setBackground(new Color(40, 40, 40));
		leftPanel.setBorder(new MatteBorder(5, 5, 5, 0, Color.BLACK));
		container.add(leftPanel, BorderLayout.WEST);

		clients = new ArrayList<String>();
		tableModel = new CustomTableModel(clients);
		userTable = new JTable(tableModel);
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

		startGroup = new JButton("Start Chat");
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
		
		mainFrame.add(container);
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		go();
	}
	
	private void go() {
		try {
			socket = new Socket("localhost", 4324);
			out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			MessageReceiver mr = new MessageReceiver(in, this);
			Thread t = new Thread(mr);
			t.start();
			
			out.writeObject(new Message(userID, "", "", new ArrayList<>()));
			createTab("All Users");

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void createTab(String title) {
		ChatTab newTab = new ChatTab(this, title);
		JButton closeButton = newTab.getButton();
		
		if(!title.equals("All Users")) {
			JPanel tabComponent = new JPanel(new GridBagLayout());
			tabComponent.setOpaque(true);
			JLabel tabTitle = new JLabel(title);
			tabTitle.setPreferredSize(new Dimension(75, 25));

			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weightx = 5;
			
			tabComponent.add(tabTitle, gbc);

			gbc.gridx++;
			gbc.weightx = 0;
			tabComponent.add(closeButton, gbc);
			
			tabPane.addTab(title, newTab);
			tabPane.setTabComponentAt(tabPane.indexOfTab(title), tabComponent);
		}
		
		else {
			tabPane.addTab(title, newTab);
		}
	}	
	
	public void sendMessageRequest(ChatTab requestingTab) {
		String receiver = requestingTab.getTabTitle();
		String message = requestingTab.getMessageSent();
		
		Message newMessage = new Message(userID, receiver, message, new ArrayList<>());
		try {
			out.writeObject(newMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deliverMessage(Message m) {
		boolean tabExists = false;
		
		for(int i = 0; i < tabPane.getTabCount(); i++) {
			ChatTab temp = (ChatTab) tabPane.getComponentAt(i);
			
			if(m.getSender().equals(temp.getTabTitle())) {
				temp.updateConvo(m.getSender() + ": " + m.getMessage());
				tabExists = true;
			}
			else if(m.getSender().equals(userID) && m.getReceiver().equals(temp.getTabTitle())) {
				temp.updateConvo(m.getSender() + ": " + m.getMessage());
				tabExists = true;
			}
			else if(m.getReceiver().equals("All Users")) {
				temp.updateConvo(m.getSender() + ": " + m.getMessage());
				tabExists = true;
			}	
		}

		if(!tabExists && !(m.getSender().equals(""))) {
			createTab(m.getSender());
			int index = tabPane.indexOfTab(m.getSender());
			ChatTab temp = (ChatTab) tabPane.getComponentAt(index);
			temp.updateConvo(m.getSender() + ": " + m.getMessage());
		}
		
		tableModel.updateTable(m.getClients());
	}

	@Override
	public void removeMe() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public String getUserID() {
		return userID;
	}
	
	public void removeTab(String title) {
		
		for(int i = 0; i < tabPane.getTabCount(); i++) {
			ChatTab temp = (ChatTab) tabPane.getComponentAt(i);
			String s = temp.getTabTitle();
			if(s.equals(title)) {
				tabPane.remove(tabPane.indexOfTab(title));
				break;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startGroup) {
			int index = userTable.getSelectedRow();
			boolean exists = false;
			if(index > -1) {
				String receiver = (String) userTable.getValueAt(index, 1);
				
				for(int i = 0; i < tabPane.getTabCount(); i++) {
					ChatTab temp = (ChatTab) tabPane.getComponentAt(i);
					
					if(temp.getTabTitle().equals(receiver))
						exists = true;
				}
				if(!exists) 
					createTab(receiver);
			
				else 
					createTab("All Users");
			}
		}
	}
}
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
import javax.swing.table.TableColumn;

public class ChatMain implements ActionListener {
	private static ChatMain m_instance = null;
	private JTabbedPane tabPane;
	private JTable userTable;
	private JButton startGroup;
	
	private ChatMain() {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
		
		JFrame mainFrame = new JFrame("Chat");
		mainFrame.setSize(new Dimension(1000, 600));
		mainFrame.setResizable(false);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(1000, 600));
		mainPanel.setBackground(new Color(40, 40, 40));
		mainFrame.add(mainPanel);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(250, 600));
		leftPanel.setBackground(new Color(40, 40, 40));
		leftPanel.setBorder(new MatteBorder(5, 5, 5, 0, Color.BLACK));
		mainPanel.add(leftPanel, BorderLayout.WEST);
		
		//CustomTableModel model = new CustomTableModel();
		Vector<String> names = new Vector<String>();
		names.add("Select");
		names.add("UserName");
		Vector<String> data = new Vector<String>();
		userTable = new JTable(data, names);
		Font tableFont = new Font("", Font.BOLD, 15);
		userTable.setFont(tableFont);
		userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userTable.setRowSelectionAllowed(true);
		userTable.setRowHeight(35);
		JScrollPane tablePane = new JScrollPane(userTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tablePane.setPreferredSize(new Dimension(228, 480));
		userTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		userTable.getColumnModel().getColumn(1).setPreferredWidth(120);
		leftPanel.add(tablePane);
		
		startGroup = new JButton("Start Group");
		startGroup.setPreferredSize(new Dimension(200, 50));
		startGroup.addActionListener(this);
		leftPanel.add(startGroup);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(750, 600));
		rightPanel.setBackground(new Color(0, 0, 0));
		mainPanel.add(rightPanel);
		
		tabPane = new JTabbedPane();
		tabPane.setPreferredSize(new Dimension(745, 592));
		tabPane.setBackground(new Color(40, 40, 40));
		rightPanel.add(tabPane, BorderLayout.EAST);
		tabPane.addTab("All Clients", new ChatTab());
		
		mainFrame.setVisible(true);
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

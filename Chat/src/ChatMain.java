import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

public class ChatMain {
	private static ChatMain m_instance = null;
	
//	public static void main(String args[]) {
//		new ChatMain();
//	}
	
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
		
		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(750, 600));
		rightPanel.setBackground(new Color(0, 0, 0));
		mainPanel.add(rightPanel);
		
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.setPreferredSize(new Dimension(745, 592));
		tabPane.setBackground(new Color(40, 40, 40));
		rightPanel.add(tabPane, BorderLayout.EAST);
		tabPane.addTab("All Clients", new ChatTab());


		

		

		
		
		mainFrame.setVisible(true);
	}
	
	private void createTab(String title, JTabbedPane tabPane) {
		tabPane.addTab(title, new ChatTab());
	}
	
	public static ChatMain startChat() {
		if(m_instance == null) {
			m_instance = new ChatMain();
		}
		return m_instance;
	}
}

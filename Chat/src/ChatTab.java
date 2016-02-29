import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class ChatTab extends JPanel implements ActionListener {

	private ChatMain parent;
	private JButton sendButton;
	private JButton closeButton = new JButton("x");
	private JTextArea convoArea;
	private JTextArea messageArea;
	public String tabTitle;

	public ChatTab(ChatMain main, String title) {

		parent = main;
		tabTitle = title;
		
		Font textFont = new Font("", Font.PLAIN, 25);
		this.setPreferredSize(new Dimension(700, 550));
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(40, 40, 40));

		JPanel northPanel = new JPanel(new GridBagLayout());
		northPanel.setPreferredSize(new Dimension(700, 300));
		this.add(northPanel, BorderLayout.NORTH);

		convoArea = new JTextArea();
		JScrollPane convoPane = new JScrollPane(convoArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		convoPane.setPreferredSize(new Dimension(650, 250));
		convoArea.setBorder(BorderFactory.createLoweredBevelBorder());
		convoArea.setFont(textFont);
		convoArea.setLineWrap(true);
		convoArea.setEditable(false);
		convoArea.setWrapStyleWord(true);
		northPanel.add(convoPane);

		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setPreferredSize(new Dimension(700, 100));
		this.add(centerPanel, BorderLayout.CENTER);

		messageArea = new JTextArea();
		JScrollPane messagePane = new JScrollPane(messageArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		messagePane.setPreferredSize(new Dimension(650, 100));
		messageArea.setBorder(BorderFactory.createLoweredBevelBorder());
		messageArea.setFont(textFont);
		messageArea.setLineWrap(true);
		messageArea.setWrapStyleWord(true);
		centerPanel.add(messagePane);

		JPanel southPanel = new JPanel(null);
		southPanel.setPreferredSize(new Dimension(700, 75));
		this.add(southPanel, BorderLayout.SOUTH);

		sendButton = new JButton("Send");
		sendButton.setSize(new Dimension(120, 35));
		sendButton.setLocation(532, 20);
		sendButton.addActionListener(this);
		southPanel.add(sendButton);
		
		closeButton = new JButton(" x ");
		closeButton.addActionListener(this);
		closeButton.setBorder(null);
	}
	
	public String getTabTitle() {
		return tabTitle;
	}
	
	public String getMessageSent() {
		return messageArea.getText();
	}
	
	public void updateConvo(String m) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		convoArea.append("[" + dateFormat.format(date) + "] " + m + "\n");
	}
	
	public JButton getButton() {
		return closeButton;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sendButton) {
			parent.sendMessageRequest(this);
			messageArea.setText("");
		}
		
		if(e.getSource() == closeButton) {
			parent.removeTab(tabTitle);
		}
	}
}
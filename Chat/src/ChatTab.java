import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

public class ChatTab extends JPanel implements ActionListener {
	
	JButton sendButton;
	
	public ChatTab() {

		Font textFont = new Font("", Font.PLAIN, 25);
		this.setPreferredSize(new Dimension(750, 600));
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(40, 40, 40));
		
		JPanel northPanel = new JPanel(new GridBagLayout());
		northPanel.setPreferredSize(new Dimension(700, 350));
		this.add(northPanel, BorderLayout.NORTH);
		
		JTextArea convoArea = new JTextArea();
		JScrollPane convoScroll = new JScrollPane(convoArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		convoScroll.setPreferredSize(new Dimension(680, 300));
		convoArea.setBorder(BorderFactory.createLoweredBevelBorder());
		convoArea.setFont(textFont);
		convoArea.setLineWrap(true);
		convoArea.setWrapStyleWord(true);
		northPanel.add(convoScroll);
		
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setPreferredSize(new Dimension(700, 200));
		this.add(centerPanel, BorderLayout.CENTER);
		
		JTextArea messageArea = new JTextArea();
		JScrollPane messageScroll = new JScrollPane(messageArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		messageScroll.setPreferredSize(new Dimension(680, 100));
		messageArea.setBorder(BorderFactory.createLoweredBevelBorder());
		messageArea.setFont(textFont);
		messageArea.setLineWrap(true);
		messageArea.setWrapStyleWord(true);
		centerPanel.add(messageScroll);
		
		JPanel southPanel = new JPanel(null);
		southPanel.setPreferredSize(new Dimension(700, 100));
		this.add(southPanel, BorderLayout.SOUTH);		
		
		sendButton = new JButton("Send");
		sendButton.setSize(new Dimension(120, 50));
		sendButton.setLocation(573, 25);
		sendButton.addActionListener(this);
		southPanel.add(sendButton);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sendButton) {
			
		}
	}
}
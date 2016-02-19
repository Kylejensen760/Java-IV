import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ChatTab extends JPanel implements ActionListener {
	
	JButton sendButton;
	
	public ChatTab() {

		Font textFont = new Font("", Font.PLAIN, 25);
		this.setSize(new Dimension(700, 550));
		this.setLayout(null);
		this.setBackground(new Color(40, 40, 40));
		
		JTextArea convoArea = new JTextArea();
		JScrollPane convoPane = new JScrollPane(convoArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		convoPane.setSize(new Dimension(672, 273));
		convoPane.setLocation(10, 10);
		convoArea.setBorder(BorderFactory.createLoweredBevelBorder());
		convoArea.setFont(textFont);
		convoArea.setLineWrap(true);
		convoArea.setWrapStyleWord(true);
		
		JTextArea messageArea = new JTextArea();
		JScrollPane messagePane = new JScrollPane(messageArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		messagePane.setSize(new Dimension(672, 150));
		messagePane.setLocation(10, 304);
		messageArea.setBorder(BorderFactory.createLoweredBevelBorder());
		messageArea.setFont(textFont);
		messageArea.setLineWrap(true);
		messageArea.setWrapStyleWord(true);	
		
		sendButton = new JButton("Send");
		sendButton.setSize(new Dimension(120, 35));
		sendButton.setLocation(545, 462);
		sendButton.addActionListener(this);
		
		this.add(convoPane);
		this.add(messagePane);
		this.add(sendButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sendButton) {
			
		}
	}
}
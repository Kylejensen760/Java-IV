import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Login implements ActionListener {
	
	JFrame loginFrame;
	JTextField nameField;
	JButton submit;
	JButton cancel;
	
	public static void main (String[] args) {
		new Login();
	}
	
	public Login() {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		
		catch(Exception e) {
			e.printStackTrace(); 
		}
		
		Color background = new Color(40, 40, 40);
		Font labelFont = new Font("", Font.BOLD, 20);
		Font fieldFont = new Font("", Font.BOLD, 20);
		
		loginFrame = new JFrame("Login");
		loginFrame.setSize(new Dimension(350, 155));
		loginFrame.setResizable(false);
		
		JPanel loginPanel = new JPanel(null);
		loginPanel.setSize(new Dimension(350, 155));
		loginPanel.setBackground(background);
		
		
		JLabel nameLabel = new JLabel("UserName: ");
		nameLabel.setForeground(Color.white);
		nameLabel.setFont(labelFont);
		nameLabel.setSize(new Dimension(300, 25));
		nameLabel.setLocation(25, 25);
		loginPanel.add(nameLabel);
		
		nameField = new JTextField();
		nameField.setFont(fieldFont);
		nameField.setSize(new Dimension(300, 40));
		nameField.setLocation(25, 50);
		loginPanel.add(nameField);
		
		submit = new JButton("Submit");
		submit.setSize(new Dimension(100, 30));
		submit.setLocation(50, 100);
		loginPanel.add(submit);
		
		cancel = new JButton("Cancel");
		cancel.setSize(new Dimension(100, 30));
		cancel.setLocation(200, 100);
		loginPanel.add(cancel);
		
		loginFrame.add(loginPanel);
		loginFrame.setVisible(true);
	}

	public boolean checkUserName() {
		return false;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancel)
			System.exit(0);
		
		if(e.getSource() == submit) {
			if(checkUserName()) {
				loginFrame.dispose();
			//	chatScreen.getScreen();
				
			}
			else {
				JOptionPane.showMessageDialog(loginFrame, "Username not available.", 
						"Error Message", JOptionPane.ERROR_MESSAGE);
		    }
		}
	}
}

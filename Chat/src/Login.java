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
	JButton submitButton;
	JButton cancelButton;
	
	public static void main (String args[]) {
		new Login();
	}
	
	public Login() {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		
		catch(Exception e) {
			e.printStackTrace(); 
		}

		Font labelFont = new Font("", Font.BOLD, 20);
		Font fieldFont = new Font("", Font.BOLD, 20);
		
		loginFrame = new JFrame("Login");
		loginFrame.setSize(new Dimension(350, 155));
		loginFrame.setResizable(false);
		
		JPanel loginPanel = new JPanel(null);
		loginPanel.setSize(new Dimension(350, 155));
		loginPanel.setBackground(new Color(40, 40, 40));
		
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
		
		submitButton = new JButton("Submit");
		submitButton.setSize(new Dimension(100, 30));
		submitButton.setLocation(50, 108);
		submitButton.addActionListener(this);
		loginPanel.add(submitButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setSize(new Dimension(100, 30));
		cancelButton.setLocation(200, 108);
		cancelButton.addActionListener(this);
		loginPanel.add(cancelButton);
		
		loginFrame.add(loginPanel);
		loginFrame.setVisible(true);
	}

	public boolean checkUserName() {
		return true;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancelButton) {
			System.exit(0);
			System.out.println("Cancel");
		}
			
		if(e.getSource() == submitButton) {
			if(checkUserName()) {
				System.out.println("Submit");
				loginFrame.dispose();
				ChatMain.startChat();
			}
			else {
				JOptionPane.showMessageDialog(loginFrame, "Username not available.", 
						"Error Message", JOptionPane.ERROR_MESSAGE);
		    }
		}
	}
}

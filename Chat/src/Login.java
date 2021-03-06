import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		
		loginFrame.addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent e) {
				System.exit(0);
		}});
		
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
		submitButton.setLocation(50, 100);
		submitButton.addActionListener(this);
		loginPanel.add(submitButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setSize(new Dimension(100, 30));
		cancelButton.setLocation(200, 100);
		cancelButton.addActionListener(this);
		loginPanel.add(cancelButton);
		
		loginFrame.add(loginPanel);
		loginFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancelButton) {
			System.exit(0);
		}
			
		if(e.getSource() == submitButton) {
			loginFrame.dispose();
			new ChatMain(nameField.getText());
		}
	}
}

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
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class Login implements ActionListener {

	private JFrame loginFrame;
	private JTextField nameField;
	private JButton submitButton;
	private JButton cancelButton;
	private JRadioButton player1;
	private JRadioButton player2;
	private JRadioButton player3;
	private JRadioButton player4;
	private JRadioButton invisibleMan;
	private JRadioButton goku;
	private JRadioButton greenArrow;
	private JRadioButton ironMan;
	private JRadioButton enemy;
	private ButtonGroup group;

	private String character;

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
		loginFrame.setSize(new Dimension(350, 250));
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

		player1 = new JRadioButton("Player 1");
		player1.setForeground(Color.white);
		player1.setSize(100, 20);
		player1.setLocation(50, 130);
		player1.addActionListener(this);
		loginPanel.add(player1);

		player2 = new JRadioButton("Player 2");
		player2.setForeground(Color.white);
		player2.setSize(100, 20);
		player2.setLocation(50, 150);
		player2.addActionListener(this);
		loginPanel.add(player2);

		player3 = new JRadioButton("Player 3");
		player3.setForeground(Color.white);
		player3.setSize(100, 20);
		player3.setLocation(50, 170);
		player3.addActionListener(this);
		loginPanel.add(player3);

		player4 = new JRadioButton("Player 4");
		player4.setForeground(Color.white);
		player4.setSize(100, 20);
		player4.setLocation(50, 190);
		player4.addActionListener(this);
		loginPanel.add(player4);

		invisibleMan = new JRadioButton("Invisible Man");
		invisibleMan.setForeground(Color.white);
		invisibleMan.setSize(120, 20);
		invisibleMan.setLocation(200, 130);
		invisibleMan.addActionListener(this);
		loginPanel.add(invisibleMan);

		goku = new JRadioButton("Goku");
		goku.setForeground(Color.white);
		goku.setSize(100, 20);
		goku.setLocation(200, 150);
		goku.addActionListener(this);
		loginPanel.add(goku);

		greenArrow = new JRadioButton("Green Arrow");
		greenArrow.setForeground(Color.white);
		greenArrow.setSize(120, 20);
		greenArrow.setLocation(200, 170);
		greenArrow.addActionListener(this);
		loginPanel.add(greenArrow);

		ironMan = new JRadioButton("Iron Man");
		ironMan.setForeground(Color.white);
		ironMan.setSize(100, 20);
		ironMan.setLocation(200, 190);
		ironMan.addActionListener(this);
		loginPanel.add(ironMan);

		submitButton = new JButton("Submit");
		submitButton.setSize(new Dimension(100, 30));
		submitButton.setLocation(50, 100);
		submitButton.addActionListener(this);
		loginPanel.add(submitButton);

		group = new ButtonGroup();
		group.add(player1);
		group.add(player2);
		group.add(player3);
		group.add(player4);
		group.add(invisibleMan);
		group.add(goku);
		group.add(greenArrow);
		group.add(ironMan);

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

		if(e.getSource() == submitButton)
		{
			loginFrame.dispose();

			/*if(player1.isSelected())
			{character = "Player1.png";}
			if(player2.isSelected())
			{character = "Player1.png";}
			if(player3.isSelected())
			{character = "Player1.png";}
			if(player4.isSelected())
			{character = "Player1.png";}
			if(invisibleMan.isSelected())
			{character = "Player1.png";}
			if(player1.isSelected())
			{character = "Player1.png";}
			if(player1.isSelected())
			{character = "Player1.png";}
			if(player1.isSelected())
			{character = "Player1.png";}*/

			if(player1.isSelected())
			{character = "Player1.png";}
			else if(player2.isSelected())
			{character = "Player2.png";}
			else if(player3.isSelected())
			{character = "Player3.png";}
			else if(player4.isSelected())
			{character = "Player4.png";}
			else if(invisibleMan.isSelected())
			{character = "InvisibleMan.png";}
			else if(goku.isSelected())
			{character = "Goku.png";}
			else if(greenArrow.isSelected())
			{character = "GreenArrow.png";}
			else if(ironMan.isSelected())
			{character = "IronMan.png";}

			group.getSelection();

			new PlayerClient(nameField.getText(), character);
		}
	}
}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/* Tileset
	0 ----- Ground ------------------(traversable)
	1 ----- Player ------------------(traversable)
	2 ----- Gold   ------------------(traversable/pickupable)
	(etc)
	*/

@SuppressWarnings("serial")
public class Map extends JPanel implements MouseListener, ActionListener
{
	private List<int[]> map;
	public List<Player> players = new ArrayList<Player>();
	private PlayerClient m_parent;
	
	public Map(PlayerClient parent, String mapFile, List<Player> pList) { 
		m_parent = parent;
		players = pList;
		populateMap(mapFile); 
		JFrame gameFrame = new JFrame("Web Game");
		gameFrame.setSize(new Dimension(500, 500));
		gameFrame.setResizable(false);
		
		this.setSize(new Dimension(500, 500));
		this.addMouseListener(this);
		this.addMouseListener(this);
		
		gameFrame.addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent e) {
				m_parent.removeMe();
		}});
		Timer timer = new Timer(5, this);
		timer.start();
		gameFrame.add(this);
		gameFrame.setVisible(true);
	}
	
	private void populateMap(String mapFile) {
		map = new ArrayList<int[]>();
		try {
			File inLevel = new File(mapFile);
			Scanner readLevel = new Scanner(inLevel);
			
			do {
				String line = readLevel.nextLine();
				String tokens[] = line.split(",");
				int nums[] = new int[tokens.length];
				for(int i = 0; i < tokens.length; i++) {
					nums[i] = Integer.parseInt(tokens[i]);
				}
				map.add(nums);
				
			} while(readLevel.hasNext());
			readLevel.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		for(int i = 0; i < map.size(); i++) {
			for(int j = 0; j < map.get(0).length; j++) {
				if(this.getTile(j, i) == 0) {
					g.setColor(Color.black);
				}
				else if(this.getTile(j, i) == 1) {
					g.setColor(Color.gray);
				}
				g.fillRect(i*10, j*10, 10, 10);
			}
		}
		if(players != null) {
			for(Player p : players) {
				if(m_parent.getPlayer().getID().equals(p.getID())) {
					g.setColor(Color.gray);
				
					m_parent.getPlayer().setX(p.getX());
					m_parent.getPlayer().setY(p.getY());
				}
				else {
					g.setColor(Color.blue);
				}
				if(p.getX() != p.getDestX() && p.getX() > p.getDestX())
					p.setX(p.getX()-1);
				else if(p.getX() != p.getDestX() && p.getX() < p.getDestX())
					p.setX(p.getX()+1);
				if(p.getY() != p.getDestY() && p.getY() > p.getDestY())
					p.setY(p.getY()-1);
				else if(p.getY() != p.getDestY() && p.getY() < p.getDestY())
					p.setY(p.getY()+1);
				
				g.fillOval(p.getX(), p.getY(), 10, 10);
			}
		}
	}
	
	public int getTile(int x, int y)
		{ return map.get(y)[x]; }
	
	public void setTile(int x, int y, int value)
		{ map.get(y)[x] = value; }
	
	public void updatePlayers(List<Player> pList) {
		players = pList;
		repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		m_parent.getPlayer().setDestX(e.getX());
		m_parent.getPlayer().setDestY(e.getY());
		m_parent.sendUpdate();
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
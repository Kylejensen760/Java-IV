import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

/* Tileset
	0 ----- Ground ------------------(traversable)
	1 ----- Player ------------------(traversable)
	2 ----- Gold   ------------------(traversable/pickupable)
	(etc)
	*/

@SuppressWarnings("serial")
public class Map extends JPanel implements MouseListener
{
	private List<int[]> map;
	private List<Player> players;
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
					g.fillRect(i*10, j*10, 10, 10);
				}
				else if(this.getTile(j, i) == 1) {
					g.setColor(Color.gray);
					g.fillRect(i*10, j*10, 10, 10);
				}
			}
		}
	}
	
	public int getTile(int x, int y)
		{ return map.get(y)[x]; }
	
	public void setTile(int x, int y, int value)
		{ map.get(y)[x] = value; }
	
	public void updatePlayers(List<Player> pList) {
		players = pList;
		for(Player p : players) {
			setTile(p.getY()/10, p.getX()/10, 1);
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		m_parent.updatePlayer(e.getX(), e.getY());
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
package DKeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Map extends JPanel{

	private static final long serialVersionUID = 1L;
	char[][] map;
	
	private BufferedImage wallIMG;
	private BufferedImage heroIMG;
	private BufferedImage guardIMG;
	private BufferedImage ogreIMG;
	private BufferedImage clubIMG;
	private BufferedImage doorOpenIMG;
	private BufferedImage doorClosedIMG;
	private BufferedImage keyIMG;
	private BufferedImage leverIMG;
	private BufferedImage sleepIMG;
	
	public Map() {
		// TODO Auto-generated constructor stub
		super();
		map = null;
		wallIMG = null;
		heroIMG = null;
		guardIMG = null;
		ogreIMG = null;
		clubIMG = null;
		doorOpenIMG = null;
		doorClosedIMG = null;
		keyIMG = null;
		leverIMG = null;
		sleepIMG = null;
		
		try {
			wallIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_WALL.png"));
			heroIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_HERO.png"));
			guardIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_GUARD.png"));
			ogreIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_OGRE.png"));
			clubIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_CLUB.jpg"));
			doorOpenIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_DOOROPEN.png"));	
			doorClosedIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_DOORCLOSED.png"));
			keyIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_KEY.png"));
			leverIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_LEVER.png"));
			sleepIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_SLEEP.png"));			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void setMap(char [][] m)
	{
		map = m;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if(map == null)
			return;
		
		for(int j = 0; j < map.length; j++)
		{
			for(int i = 0; i < map[j].length; i++)
			{
				if(map[j][i] == 'X')
				{					
					g.drawImage(wallIMG, i*32, j*32, this);
				}
				else if(map[j][i] == 'H' || map[j][i] == 'A' || map[j][i] == 'K')
				{					
					g.drawImage(heroIMG, i*32, j*32, this);
				}
				else if(map[j][i] == 'G')
				{
					g.drawImage(guardIMG, i*32, j*32, this);
				}
				else if(map[j][i] == '0')
				{
					g.drawImage(ogreIMG, i*32, j*32, this);
				}
				else if(map[j][i] == '*')
				{					
					g.drawImage(clubIMG, i*32, j*32, this);
				}
				else if(map[j][i] == 'S')
				{					
					g.drawImage(doorOpenIMG, i*32, j*32, this);
				}				
				else if(map[j][i] == 'I')
				{					
					g.drawImage(doorClosedIMG, i*32, j*32, this);
				}
				else if(map[j][i] == 'k' || map[j][i] == '$')
				{
					g.drawImage(keyIMG, i*32, j*32, this);
				}
				else if(map[j][i] == 'l')
				{
					g.drawImage(leverIMG, i*32, j*32, this);
				}
				else if(map[j][i] == '8' || map[j][i] == 'g')
				{
					g.drawImage(sleepIMG, i*32, j*32, this);
				}
				else
				{
					g.setColor(Color.WHITE);
					g.fillRect(i*32, j*32, 32, 32);
				}
			}
		}		
	}
}

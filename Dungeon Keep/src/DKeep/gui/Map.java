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
	
	public Map() {
		super();
		map = null;
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
					BufferedImage wallIMG = null;
					try {
						wallIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_WALL.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					g.drawImage(wallIMG, i*32, j*32, this);
				}
				else if(map[j][i] == 'H' || map[j][i] == 'A' || map[j][i] == 'K')
				{
					BufferedImage heroIMG = null;
					try {
						heroIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_HERO.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					g.drawImage(heroIMG, i*32, j*32, this);
				}
				else if(map[j][i] == 'G')
				{
					BufferedImage guardIMG = null;
					try {
						guardIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_GUARD.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					g.drawImage(guardIMG, i*32, j*32, this);
				}
				else if(map[j][i] == '0')
				{
					BufferedImage ogreIMG = null;
					try {
						ogreIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_OGRE.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					g.drawImage(ogreIMG, i*32, j*32, this);
				}
				else if(map[j][i] == '*')
				{
					BufferedImage clubIMG = null;
					try {
						clubIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_CLUB.jpg"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					g.drawImage(clubIMG, i*32, j*32, this);
				}
				else if(map[j][i] == 'S')
				{
					BufferedImage doorOpenIMG = null;
					try {
						doorOpenIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_DOOROPEN.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					g.drawImage(doorOpenIMG, i*32, j*32, this);
				}				
				else if(map[j][i] == 'I')
				{
					BufferedImage doorClosedIMG = null;
					try {
						doorClosedIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_DOORCLOSED.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					g.drawImage(doorClosedIMG, i*32, j*32, this);
				}
				else if(map[j][i] == 'k' || map[j][i] == '$')
				{
					BufferedImage keyIMG = null;
					try {
						keyIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_KEY.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					g.drawImage(keyIMG, i*32, j*32, this);
				}
				else if(map[j][i] == 'l')
				{
					BufferedImage leverIMG = null;
					try {
						leverIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_LEVER.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					g.drawImage(leverIMG, i*32, j*32, this);
				}
				else if(map[j][i] == '8' || map[j][i] == 'g')
				{
					BufferedImage sleepIMG = null;
					try {
						sleepIMG = ImageIO.read(this.getClass().getResource("/DungeonKeep_SLEEP.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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

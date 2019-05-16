import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.*; 

//imports! :D

public class Map extends JPanel
{
	
	//array lists that are used to hold towers, enemies, and bullets so that they are easy to manage when creating them.
	public static ArrayList<Tower> towerArray = new ArrayList<Tower>();
	public static ArrayList<Enemies> enemyArray = new ArrayList<Enemies>();
	public static ArrayList<Bullets> bulletArray = new ArrayList<Bullets>();
	
	//important variables for use later 
	Enemies e;
	static int counter = 0;
	
	//map constructor
	public Map()
	{
		//creating variables to hold map size for the MyCanvas class to later use that will allow texture tiles to be painted the correct amount of times.
		int numRows = 0;
		int numCols = 0;
		MyCanvas myCanvas = null; 
		
		try {
			//this was a workaround eclipse forced on me to get the map file the will be read in through a file reader and used to create the map based on what the TheMap.txt file contains
			java.net.URL url = getClass().getResource("TheMap.txt");
			
			//assigning the TheMap.txt file to file variable
			File file = new File(url.toURI());
			
			//scanner to read the file
			Scanner FileRead = new Scanner(file);
			
			//setting tile bounds of the map
			numRows = 12;
			numCols = 16;
			
			//creating a MyCanvas object with the size of the map.
			myCanvas = new MyCanvas(numRows, numCols);
			
			//for loops that will read through the MyCanvas class will read through the file housing the map layout and paint the tiles based on the file sent in the desired bounds created earlier.
			for(int x = 0; x < numRows; x++)
				for(int y = 0; y < numCols; y++)
				{
					String s = FileRead.next();
					myCanvas.addPicture(x, y, s + ".png");
				}
		
		
		}
		catch (FileNotFoundException | URISyntaxException e)
		{
			System.out.println("no file found");
		}
		
		if (myCanvas != null)
			this.add(myCanvas);
		this.setVisible(true);
	}
	
	//method to create a cannon tower, use to be archer tower but realized using an arrow would look stupid so I changed the bullet to a cannon ball and thus made it a cannon tower.
	public void createArcherTower(int x, int y)
	{
		try 
		{
			//adding the created tower to a tower arraylist
			towerArray.add(new ArcherTower(x - 24, y - 124, ImageIO.read(new File("Tower1.png")), 49, 124));
		}
		catch(IOException Exc)
		{
			System.out.println("Unable to generate tower due to IO exception");
		}
	}
	
	//same thing as last method just for fire tower
	public void createFireTower(int x, int y)
	{
		try 
		{
			towerArray.add(new FireTower(x - 24, y - 124, ImageIO.read(new File("Tower2.png")), 49, 124));
		}
		catch(IOException Exc)
		{
			System.out.println("Unable to generate tower due to IO exception");
		}
	}
	
	//same thing as previous 2 methods but for different tower
	public void createLightningTower(int x, int y)
	{
		try 
		{
			towerArray.add(new LightningTower(x - 24, y - 124, ImageIO.read(new File("Tower3.png")), 49, 124));
		}
		catch(IOException Exc)
		{
			System.out.println("Unable to generate tower due to IO exception");
		}
	}
	
	//method to create enemies and add them to an enemy array
	public void createEnemy(int x)
	{
		try 
		{
			//ensuring there is always an enemy somewhere so that an initalization error later doesn't occur
			e = new Enemies(x - 500, 300, ImageIO.read(new File("Enemy.png")), 40, 64, 1, 0);
			//adding enemy to an array list
			enemyArray.add(new Enemies(x - 500, 300, ImageIO.read(new File("Enemy.png")), 40, 64, 1, 0));
		}
		catch(IOException Exc)
		{
			System.out.println("Unable to generate tower due to IO exception");
		}
	}
	

	//my canvas class used to make the map and contains the paint method where everything for the game is painted 
	@SuppressWarnings("serial")
	class MyCanvas extends JPanel{
		private BufferedImage[][] imgs;
		private int rows;
		private int cols;
		private int tileSize = 50;
		public MyCanvas(int rows, int cols){
			super();
			this.rows = rows;
			this.cols = cols;
			imgs = new BufferedImage[rows][cols];
		}
		//add picture method that will read in a picture from the file based on the text from said file. 
		public void addPicture(int x, int y, String filename){
			java.net.URL url = getClass().getResource(filename);
			
			try {
				File file = new File(url.toURI());
			
				if (x < 0 || x >= rows){
					System.err.println("There is no row " + x);
				}
				else if (y < 0 || y >= cols){
					System.err.println("There is no col " + y);
				}
				else{
						try {
							imgs[x][y] = ImageIO.read(file);
						} catch (IOException e) {
							System.err.println("Unable to read the file: " + filename);
						}
				}
		
			}catch (URISyntaxException e) {
			
				System.out.println("Nah");
		}
	}
	//paint method that paints everything the game does.
		public void paint(Graphics g){
			//for loops drawing the map tiles 
			for (int i = 0; i < rows; i++){
				for (int j = 0; j < cols; j++){
					g.drawImage(imgs[i][j], j*tileSize, i*tileSize,null);
				}
			}
			//for loop iterating through tower array list and depending on the tower there, it paints the specific tower image
			for (int i = 0; i < towerArray.size(); i++)
			{
				towerArray.get(i).drawImage(g);
				//also calls fire method that creates bullets for that tower and puts them in a bullet arraylist every 100 times it repaints, this is mimicing fire rate
				if(counter++%100 == 0)
				{
					if(towerArray.get(i) instanceof ArcherTower)
						bulletArray.add(((ArcherTower)(towerArray.get(i))).fire());
					else if(towerArray.get(i) instanceof FireTower)
						bulletArray.add(((FireTower)(towerArray.get(i))).fire());
					else if(towerArray.get(i) instanceof LightningTower)
						bulletArray.add(((LightningTower)(towerArray.get(i))).fire());
				}
			}
			
			//if statement ensuring bullet array list and enemy array list are not empty, otherwise the program would crash when this tries to run
			if(!enemyArray.isEmpty() && !bulletArray.isEmpty())
			{
				
				//getting the enemy and bullet at position i in the array as for loop iterates through each one
				for(int i = 0; i < enemyArray.size(); i++)
					if(enemyArray.get(i) != null)
						e = enemyArray.get(i);
				Bullets b = bulletArray.get(0);
				
				//for loop checking to see if the bullet and enemy position are equal, if they are within 2 of each other, it calls for money to be added and the bullet and enemy to be removed.
				for(int i = 0; i < bulletArray.size(); i++)
				{
					if(!enemyArray.isEmpty() && !bulletArray.isEmpty())
					{
						b = bulletArray.get(i);
						
						if((b.posx-2 <= e.posx+2) && (b.posx+2 >= e.posx-2) && (b.posy-2 <= e.posy+2) && (b.posy+2 >= e.posy-2))
						{
							bulletArray.remove(bulletArray.indexOf(b));
							enemyArray.remove(e);
							GameDriver.addMoney(20);
						}
						if(b.posx > e.posx)
						{
							if(b.getVx() > 0)
								b.setVx(b.getVx() * -1);
						}
						else if(b.posx < e.posx)
						{
							if(b.getVx() < 0)
								b.setVx(b.getVx() * -1);
						}						
							
						if(b.posy > e.posy)
						{
							if(b.getVy() > 0)
								b.setVy(b.getVy() * -1);
						}
						else if(b.posy < e.posy)
						{
							if(b.getVy() < 0)
								b.setVy(b.getVy() * -1);
						}
						
						b.drawImage(g);
					}
				}
			}	
			
			//for loop that checks if the enemy has reached the end of the path and removes it as well as subtracts a life if it has reached the end.
			for (int i = 0; i < enemyArray.size(); i++)
			{
					enemyArray.get(i).drawImage(g);
					if(enemyArray.get(i).posx > 800)
					{
						enemyArray.remove(i);
						GameDriver.reduceLives();
					}
			}
			
			//this allows the repaint to not happen at lightning speeds, making the game look a lot smoother.
			try{Thread.sleep(10);}catch(Exception f){;}
			
			
			repaint();
		}//end of paint method
	}//end of MyCanvas class
}//end of map class

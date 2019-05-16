import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//general objects class to share common variables among anything that can be considered an object.
public abstract class Objects 
{
	protected String name;
	public int posx; 
	public int posy;
	protected BufferedImage bi; 
	protected int imageW;
	protected int imageH;


	Objects(int posx, int posy, BufferedImage bi, int imageW, int imageH)
	{
		this.posx = posx;
		this.posy = posy; 
		this.bi= bi;
		this.imageW = imageW; 
		this.imageH = imageH;
	}
	
	//renders our object to the screen
	public void drawImage(Graphics g)
	{
		g.drawImage(bi,posx, posy,imageW,imageH,null);
		
	}
	
	
}

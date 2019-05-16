import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class ArcherTower extends Tower
{

	ArcherTower(int posx, int posy, BufferedImage bi, int imageW, int imageH) 
	{
		super(posx, posy, bi, imageW, imageH);
	}
	
	//fire method that is how a tower gets it's bullets
	public Bullets fire()
	{
		try
		{
			return new Bullets(posx, posy, ImageIO.read(new File("CANNONBALL.png")), 25, 25, 2, 2);
		}
		catch(IOException e)
		{
			System.out.println("That bullet is an error right there bud");
			return null;
		}

	}
}

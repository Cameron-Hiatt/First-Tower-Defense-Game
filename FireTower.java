import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class FireTower extends Tower
{
	
	FireTower(int posx, int posy, BufferedImage bi, int imageW, int imageH) 
	{
		super(posx, posy, bi, imageW, imageH);
	}
	
	//fire method that is how a tower gets it's bullets
	public Bullets fire()
	{
		try
		{
			return new Bullets(posx, posy, ImageIO.read(new File("FIREBALL.png")), 25, 25, 2, 2);
		}
		catch(IOException e)
		{
			System.out.println("That bullet is an error right there bud");
			return null;
		}

	}
}

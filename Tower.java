import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

//tower class, honestly just adds good flow of parent and subclasses, not sure this is really needed other than that.
public class Tower extends Objects
{
	Tower(int posx, int posy, BufferedImage bi, int imageW, int imageH) 
	{
		super(posx, posy, bi, imageW, imageH);
	}
	
	
	
}
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//bullets class
public class Bullets extends MovingObjects
{
	Bullets(int posx, int posy, BufferedImage bi, int imageW, int imageH, int vx, int vy) 
	{
		super(posx, posy, bi, imageW, imageH, vx, vy);
		
	}
}
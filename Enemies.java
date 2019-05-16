import java.awt.image.BufferedImage;

//enemies class
public class Enemies extends MovingObjects
{

	Enemies(int posx, int posy, BufferedImage bi, int imageW, int imageH, int vx, int vy) {
		super(posx, posy, bi, imageW, imageH, vx, vy);
	}
	
}
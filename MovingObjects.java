import java.awt.Graphics;
import java.awt.image.BufferedImage;

//moving objects extends Objects class 
public class MovingObjects extends Objects
{
	private int vx;
	private int vy;
	
	//moving object constructor
	MovingObjects(int posx, int posy, BufferedImage bi, int imageW, int imageH, int vx, int vy)
	{
		super(posx, posy, bi,  imageW, imageH);
		this.vx=vx;
		this.vy=vy;
	}
	
	public void drawImage(Graphics g)
	{
		g.drawImage(bi,posx+=vx, posy+=vy,imageW,imageH,null);
	}
	
	//getter for a moving objects x velocity
	public int getVx()
	{
		return vx;
	}
	//getter for a moving objects y velocity
	public int getVy()
	{
		return vy;
	}
	//setter for a moving objects x velocity
	public void setVx(int _vx)
	{
		vx = _vx;
	}
	//setter for a moving objects y velocity
	public void setVy(int _vy)
	{
		vy = _vy;
	}
	
}

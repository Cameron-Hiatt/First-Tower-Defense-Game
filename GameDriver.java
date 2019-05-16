//Tower Defense GameDriver
//CS 1410
//April 20th, 2019
//Cameron Hiatt

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.util.ArrayList;

//imports! :D

public class GameDriver extends JFrame
{
	//variables needed across the entire GameDriver.
	private static int money;
	private static int lives;
	private int coordX;
	private int coordY;
	static JLabel LivesLabel;
	static JLabel MoneyLabel;
	int selector = 0;
	
	public GameDriver()
	{
		//set starting money and lives
		money = 100;
		lives = 10;
		
		//set JFrame title
		setTitle("Tower Defense Game");
		getContentPane().setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		
		//create the user menu where you buy towers and start enenmies across the screen
		JPanel UserMenu = new JPanel();
		UserMenu.setBounds(10, 11, 159, 600);
		getContentPane().add(UserMenu);
		UserMenu.setLayout(null);
		
		//create a map next to the user menu where the game happens
		JPanel Map = new Map();
		Map.setBounds(172, 11, 800, 600);
		getContentPane().add(Map);
		Map.setLayout(new GridLayout(1,0,0,0));
		
		//create a money label displaying players current cash
		MoneyLabel = new JLabel("Money: " + money);
		MoneyLabel.setBounds(10, 11, 100, 34);
		UserMenu.add(MoneyLabel);
		
		//create lives label displaying players current lives
		LivesLabel = new JLabel("Lives: " + lives);
		LivesLabel.setBounds(10, 44, 129, 40);
		UserMenu.add(LivesLabel);
		
		//start button creation that when clicked, will call method to create 11 enemies that will go across the path
		JButton btnStart = new JButton("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for(int i = 0; i < 550; i+=50)
					((Map) Map).createEnemy(i);
				
			}
		});
		btnStart.setBounds(10, 499, 139, 90);
		UserMenu.add(btnStart);
		
		//label displaying tower obtions
		JLabel TowersLabel = new JLabel("Towers");
		TowersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TowersLabel.setBounds(10, 76, 129, 14);
		UserMenu.add(TowersLabel);
		
		//the following labels and buttons are associated with the cannon tower, originally it was an archer tower but I renamed it in the game itself. 
			JLabel ArcherTowerDescription = new JLabel("Cannon Tower");
			ArcherTowerDescription.setFont(new Font("Tahoma", Font.PLAIN, 12));
			ArcherTowerDescription.setVerticalAlignment(SwingConstants.TOP);
			ArcherTowerDescription.setHorizontalAlignment(SwingConstants.CENTER);
			ArcherTowerDescription.setBounds(53, 94, 86, 14);
			UserMenu.add(ArcherTowerDescription);
			
			//cost of tower label
			JLabel CostLabel1 = new JLabel("Cost: 10");
			CostLabel1.setBounds(62, 130, 77, 14);
			UserMenu.add(CostLabel1);
			
			//buy button that will subtract tower cost from overall money and set a selector to 1 which will allow for the purchased tower to be placed
			JButton btnBuy = new JButton("Buy");
			btnBuy.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(money >=10 && selector == 0)
					{
						money -= 10;
						MoneyLabel.setText("Money: " + money);
						selector = 1;
					}
					else
						System.out.println("Ya, you don't got the cash homie OR you haven't placed the turret you already bought dawg");
				}
			});
			
			//mouse listener for the map that detects where the players clicks and gets those coordinates and will then place whatever tower they purchased in that position based on the bottom middle pixel of the tower.
			Map.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(selector == 1)
					{
						((Map) Map).createArcherTower(e.getX(), e.getY());
						selector = 0;
					}
					else if(selector == 2)
					{
						((Map) Map).createFireTower(e.getX(), e.getY());
						selector = 0;
					}
					else if(selector == 3)
					{
						((Map) Map).createLightningTower(e.getX(), e.getY());
						selector = 0;
					}
					else
						System.out.println("You clicked at " + e.getX() + ", " +e.getY());
					
				}
			});
			btnBuy.setBounds(53, 144, 86, 74);
			UserMenu.add(btnBuy);
			
			//label displaying an image for the tower to be bought
			Image tower1Icon = new ImageIcon(this.getClass().getResource("/Tower1.png")).getImage();
			JLabel Tower1 = new JLabel("");
			Tower1.setIcon(new ImageIcon(tower1Icon));
			Tower1.setHorizontalAlignment(SwingConstants.LEFT);
			Tower1.setBounds(10, 94, 129, 124);
			UserMenu.add(Tower1);
		
		
		//the following is associated with fire tower, similar features as the previous tower labels and buttons
			JLabel FireTowerDescription = new JLabel("Fire Tower");
			FireTowerDescription.setHorizontalAlignment(SwingConstants.CENTER);
			FireTowerDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
			FireTowerDescription.setVerticalAlignment(SwingConstants.TOP);
			FireTowerDescription.setBounds(63, 229, 86, 14);
			UserMenu.add(FireTowerDescription);
			
			JLabel CostLabel2 = new JLabel("Cost: 40");
			CostLabel2.setBounds(72, 266, 77, 14);
			UserMenu.add(CostLabel2);
			
			JButton button = new JButton("Buy");
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(money >=40 && selector == 0)
					{
						money -= 40;
						MoneyLabel.setText("Money: " + money);
						selector = 2;
					}
					else
						System.out.println("Ya, you don't got the cash homie OR you haven't placed the turret you already bought dawg");			}
			});
			button.setBounds(63, 279, 86, 74);
			UserMenu.add(button);
			
			Image tower2Icon = new ImageIcon(this.getClass().getResource("/Tower2.png")).getImage();
			JLabel Tower2 = new JLabel("");
			Tower2.setIcon(new ImageIcon(tower2Icon));
			Tower2.setHorizontalAlignment(SwingConstants.LEFT);
			Tower2.setBounds(10, 229, 129, 124);
			UserMenu.add(Tower2);
		
		//lightning tower buttons and labels, similar to the other towers
			JLabel LightningTowerDescription = new JLabel("Lightning Tower");
			LightningTowerDescription.setVerticalAlignment(SwingConstants.TOP);
			LightningTowerDescription.setHorizontalAlignment(SwingConstants.CENTER);
			LightningTowerDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
			LightningTowerDescription.setBounds(49, 364, 100, 26);
			UserMenu.add(LightningTowerDescription);
			
			JLabel CostLabel3 = new JLabel("Cost: 80");
			CostLabel3.setBounds(73, 400, 77, 14);
			UserMenu.add(CostLabel3);
			
			JButton button_1 = new JButton("Buy");
			button_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(money >=80 && selector == 0)
					{
						money -= 80;
						MoneyLabel.setText("Money: " + money);
						selector = 3;
					}
					else
						System.out.println("Ya, you don't got the cash homie OR you haven't placed the turret you already bought dawg");
				}
			});
			button_1.setBounds(63, 414, 86, 74);
			UserMenu.add(button_1);
			
			Image tower3Icon = new ImageIcon(this.getClass().getResource("/Tower3.png")).getImage();
			JLabel Tower3 = new JLabel("");
			Tower3.setIcon(new ImageIcon(tower3Icon));
			Tower3.setHorizontalAlignment(SwingConstants.LEFT);
			Tower3.setBounds(10, 364, 129, 124);
			UserMenu.add(Tower3);
		
	}
	
	//method to reduce lives when an enemy reaches the other end of the map, if someone has lost it displays an infinite loops worth of messages saying to get good you lost in slang terms.
	static void reduceLives()
	{
		--lives;
		if(lives >= 0)
			LivesLabel.setText("Lives: " + lives);
		else
			for(;;){System.out.print("GIT GUD BOI YOU LOST ");}
	}
	
	//method to add money when an enemy is killed
	static void addMoney(int _money)
	{
		money += _money;
		MoneyLabel.setText("Money: " + money);
	}
	
	//main method creating a game driver object and starting the whole game.
	public static void main(String[] args)
	{
		GameDriver game = new GameDriver();
		game.setSize(1000, 660);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setVisible(true);
	}
}
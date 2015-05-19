package core;

import core.Level;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Bullet extends Sprite{
	private static SpriteSheet EXPLOSION = null;
	private static Image BULLET = null;
	static {
		try {
			EXPLOSION = new SpriteSheet("res/explosion_spritesheet.png", 0, 0);
			BULLET = new Image("res/bullet.png");
		} catch (SlickException e) {
			System.out.println("Kaboom!");
			e.printStackTrace();
		}
	}
	
	protected double xAccel, yAccel;
	private double speed;
	private boolean exploding = false;

	Image image;
	public Bullet(int x, int y, double speed){
		this.x = x;
		this.y = y;
		this.xAccel = 0;
		this.yAccel = 0;
		this.exploding = false;
	}
	
	public boolean isExploding() {
		return exploding;
	}
	
	public void setExploding(boolean exploding) {
		this.exploding = exploding;
	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}

	
	public Bullet() {
		this(2);
	}
	
	public Bullet(double speed) {
		randomize(speed);
		this.speed = speed;
	}
	
	public void init(GameContainer container, StateBasedGame sbg)
	throws SlickException{
		image = BULLET;
//		image = new Image("res/bullet.png");
	}
	
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
	throws SlickException{
		System.out.println(image);
			g.drawImage(image, (float) this.x, (float) this.y);		
	}
	
	public void update(int delta) {
		if (exploding) {
			
			image = EXPLOSION.getSprite(0, 0);
			
		} else {
			x += xAccel;
			y += yAccel;
			if (checkEdge()) {
				randomize();
			}
			image = BULLET;
		}
	}
	
	public void randomize() {
		randomize(speed);
	}
	
//	public boolean touching(Player player) {
//		return x < player.getX() + player.getWidth() &&
//			   x + image.getWidth() > player.getX() &&
//			   y < player.getY() + player.getHeight() &&
//			   image.getHeight() + y > player.getY();
//	}
	
	public void destroy() throws SlickException {
		image.destroy();
	}
	
	public void randomize(double speed) {
		double dir = 0;
		if (Math.random() < Game.WIDTH / 2.0 / (Game.WIDTH + Game.HEIGHT)) {
			 if (Math.random() < 0.5) {
				 x = 0;
				 dir = -Math.PI / 2;
			 } else {
				 x = Game.WIDTH;
				 dir = Math.PI / 2;
			 }
			y = (float) (Math.random() * Game.HEIGHT);
		} else {
			 if (Math.random() < 0.5) {
				 y = 0;
				 dir = 0;
			 } else {
				 y = Game.HEIGHT;
				 dir = Math.PI;
			 }
			x = (float) (Math.random() * Game.WIDTH);
		}
		//make dir random and weight the amount of bullets more towards middle
		dir += Math.random() * (Math.PI - 0.5) + 0.25;
		//set speeds in direct relation to dir by cos and sin and then scale it by speed
		xAccel = Math.cos(dir) * speed;
		yAccel = Math.sin(dir) * speed;
		this.speed = speed + 1.0;
//		System.out.printf("[x: %3.0f, y: %3.0f, xAccel: %3.3f, yAccel: %3.3f]\n", x, y, xAccel, yAccel);
	}
	
	public void explode() {
		exploding = true;
	}
	
	public boolean checkEdge(){
		return x > Game.WIDTH + 4  || x < -5
		    || y > Game.HEIGHT + 4 || y < -5;
	}
}

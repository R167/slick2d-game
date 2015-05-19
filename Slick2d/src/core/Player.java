package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Sprite{
	
	public final float SQRT2 = (float) Math.sqrt(2);
	
	protected float x = 200, y = 200;
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}


	protected Image playerImg;
	protected float xAcceleration;
	protected float yAcceleration;
	protected int width = 20;
	protected int height = 20;
	public void init(GameContainer container, StateBasedGame sbg){
		
	}
	
	public Player() {
		reset();
	}
	
	public void reset() {
		x = 200;
		y = 200;
	}
	
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException{
		g.fillRect(x, y, width, height);
		g.setColor(Color.cyan);
	}
	
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException{
		Input input = container.getInput();
		collisionCheck();
		
		int xmov = 0, ymov = 0;
				
		if(input.isKeyDown(Input.KEY_W)){
			ymov -= delta;
		}
		if(input.isKeyDown(Input.KEY_S)){
			ymov += delta;
		}
		if(input.isKeyDown(Input.KEY_D)){
			xmov += delta;
		}
		if(input.isKeyDown(Input.KEY_A)){
			xmov -= delta;
		}	
		
		xAcceleration = xmov * (xmov * ymov == 0 ? SQRT2 : 1) * 0.1f;
		yAcceleration = ymov * (xmov * ymov == 0 ? SQRT2 : 1) * 0.1f;
				
		x += xAcceleration;
		y += yAcceleration;
	}
	
	public void collisionCheck(){
		if(this.x > Game.WIDTH - this.width){
			this.x = Game.WIDTH - this.width;
		}
		else if(this.x < 0){
			this.x = 0;
		}
		if(this.y > Game.HEIGHT - this.height){
			this.y = Game.HEIGHT - this.height;
		}
		else if(this.y < 0){
			this.y = 0;
		}
	}
}

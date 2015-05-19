package core;

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Level extends BasicGameState {
	HighScores highScores = null;
	protected Player player = new Player();
	protected Image background;
	protected int maxBullets = 0;
	protected long time = 0;
	protected long timerTime = 0;
	public int timerDisplay = 0;
	int xOrY;
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	Random randint = new Random();

	// float mouseX;
	// float mouseY;
	public Level(int state, int count, HighScores h) {
		maxBullets = count;
	    highScores = h;
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		// TODO Auto-generated method stub
		background = new Image("res/gameBackGround.jpg");		
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.drawImage(background, 0, 0);
		g.drawString("Time: " + Integer.toString((int) timerDisplay), Game.WIDTH - 100, 15);
		if (bullets.size() > 0) {
			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).render(container, sbg, g);
			}
		}
		player.render(container, sbg, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		Input input = container.getInput();
		player.update(container, sbg, delta);
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bull = bullets.get(i);
			if (bull.touching(player)) {
				reset();
				break;
			}
			boolean collisions = true;
			if (collisions) {
				for (int j = i + 1; j < bullets.size(); j++){
					Bullet bull2 = bullets.get(j);
					if (bull.touching(bull2)){
	//					System.out.printf("explosion @ [x: %3.0f, y: %3.0f]\n", bull.getX(), bull.getY());
	//					System.out.printf("          @ [x: %3.0f, y: %3.0f]\n", bull2.getX(), bull2.getY());
	//					bull.destroy();
	//					bull2.destroy();
						bullets.remove(i);
						bullets.remove(j - 1);
						
					}
				}
			}
			bull.update(delta);
		}
		
		int millis = 300;
		if (time - container.getTime() + millis < 0 && bullets.size() < maxBullets) {
			addBullet(container, sbg, delta);
			time = container.getTime();
		}
		if(timerTime - container.getTime() + 1000 < 0){
			timerTime = container.getTime();
			timerDisplay++;
		}
//		if (input.isMousePressed(0)) {
//			addBullet(container, sbg, delta);
//		}
		//SPACE
		if(input.isKeyPressed(Input.KEY_SPACE)){
			sbg.enterState(1);
		}
	}
	
	public void reset() throws SlickException {
		highScores.updateScoresList(timerDisplay);
		timerDisplay = 0;
		time = 0;
		for (int i = bullets.size() - 1; i > 0; i--) {
			bullets.remove(i);
		}
		bullets = new ArrayList<Bullet>();
		player.reset();
		
	}
//	public void changeBullet(GameContainer container, StateBasedGame sbg,
//			int delta, Bullet bullet) throws SlickException {
//		chooseBulletDirection(bullet);
//	}

	public void addBullet(GameContainer container, StateBasedGame sbg,
			int delta) throws SlickException {
		Bullet bullet = new Bullet();
		bullets.add(bullet);
//		System.out.println(bullet.x);
//		System.out.println(bullet.y);
		bullet.init(container, sbg);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}

}

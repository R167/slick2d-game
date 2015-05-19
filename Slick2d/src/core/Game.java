package core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {

	// String used as name of game in app. name can never be changed
	public static final String name = "Game";

	// all states defined with their respective integer representations
	public static final int MENU = 0;
	public static final int OPTIONS = 1;
	public static final int CREDITS = 2;
	public static final int LEVEL = 3;
	public static final int HIGHSCORES = 4;
	public static int WIDTH;
	public static int HEIGHT;
	// Game constructor
	public Game(String name) {
		super(name);
		// Add states to be used for access later by this.
		int bullets = 2000;
		this.addState(new Menu(MENU));
		this.addState(new Options(OPTIONS));
		this.addState(new Credits(CREDITS));
		HighScores h = new HighScores(HIGHSCORES);
		this.addState(new Level(LEVEL, bullets, h));
		this.addState(h);
		this.WIDTH = 800;
		this.HEIGHT = 600;
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		// Get states and initialize them in current instance by taking their
		// int id
		this.getState(MENU).init(container, this);
		this.getState(OPTIONS).init(container, this);
		this.getState(CREDITS).init(container, this);
		this.getState(LEVEL).init(container, this);
		this.getState(HIGHSCORES).init(container, this);
		this.enterState(MENU);
	}

	public static void main(String[] args) {
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new Game("Game"), Game.WIDTH,
					Game.HEIGHT, false);
			appgc.setTargetFrameRate(60);
			appgc.start();
		} catch (SlickException ex) {
			ex.printStackTrace();
		}

	}

}

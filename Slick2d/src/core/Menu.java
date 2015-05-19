package core;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {

	Image playButton;
	Image playButtonHover;
	float mouseX;
	float mouseY;
	boolean isMouseOverPlayButton;

	public Menu(int state) {

	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		// TODO Auto-generated method stub
		playButton = new Image("res/playButton.png");
		playButtonHover = new Image("res/playButtonHover.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// .draw(x, y, width, height);
		if (!isMouseOverPlayButton) {
			g.drawImage(playButton, Game.WIDTH / 2 - 200, Game.HEIGHT / 2 - 200);
		} else {
			g.drawImage(playButtonHover, Game.WIDTH / 2 - 200,
					Game.HEIGHT / 2 - 200);
		}
		g.drawString("X: " + mouseX, 50, 500);
		g.drawString("Y: " + mouseY, 50, 450);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		Input mouseInput = container.getInput();
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
		if ((mouseX > 300 && mouseX < 600) && (mouseY > 0 && mouseY < 300)) {
			isMouseOverPlayButton = true;
			if (mouseInput.isMousePressed(0)) {
				sbg.enterState(3);
			}
		} else {
			isMouseOverPlayButton = false;
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}

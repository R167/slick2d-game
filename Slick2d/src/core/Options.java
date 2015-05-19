package core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Options extends BasicGameState{
	Image background;
	Image wrapper;
	public Options(int state){
		
	}

	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		// TODO Auto-generated method stub
		background = new Image("res/gameBackGround.jpg");	
		wrapper = new Image("res/optionsWrapper.jpeg");
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub	
		g.drawImage(background, 0, 0);
		g.drawImage(wrapper, 75, 75);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		Input input = container.getInput();
		//SPACE
		if(input.isKeyPressed(Input.KEY_SPACE)){
			//Options
			sbg.enterState(3);
		}
		if(input.isKeyPressed(Input.KEY_1)){
			sbg.enterState(4);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}

package core;

import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class HighScores extends BasicGameState{
	protected ArrayList<String> highScores = new ArrayList<String>();
	protected ArrayList<Long> rawScores = new ArrayList<Long>();
	int titleY = 30;
	int y = titleY + 30;
	private long timerDisplay;
	public HighScores(int state){
		
	}
	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
	public void updateScoresList(long timerDisplay){
		if(highScores.size() > 0){
			for(int i = highScores.size() - 1; i > 0; i--){
				if(rawScores.get(i) < timerDisplay){
					Scanner scanner = new Scanner(System.in);
					String name = scanner.nextLine();
					rawScores.remove(i);
					highScores.remove(i);
					rawScores.set(i, (long) timerDisplay);
					highScores.set(i, name + ": " + rawScores.get(i).toString());
					scanner.close();
					timerDisplay = 0;
				}
			}
		}
		else if(highScores.size() > 0 && highScores.size() < 10){
			Scanner scanner = new Scanner(System.in);
			String name = scanner.nextLine();
			rawScores.add((long) timerDisplay);
			highScores.add(name + ": " + timerDisplay);
			scanner.close();
		}
		else{
			Scanner scanner = new Scanner(System.in);
			String name = scanner.nextLine();
			rawScores.add((long) timerDisplay);
			highScores.add(name + ": " + rawScores.get(0).toString());
			scanner.close();
		}
	}
	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		g.drawString("", Game.WIDTH - 20, titleY);
		for(int i = 0; i < 10; i++){
			g.drawString(highScores.get(i), Game.WIDTH - 20, y);
			y += 15;
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 4;
	}

}

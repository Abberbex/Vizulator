package me.brendand.main;

import me.brendand.Objects.Table;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class MainGame extends BasicGame{
	private Table t;
	private final int n = 256;

	public MainGame() {
		super("");
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer game = new AppGameContainer(new MainGame());
			game.setMaximumLogicUpdateInterval(60);			
			game.setDisplayMode(1024, 1024, false);
			game.setTargetFrameRate(60);
			game.setAlwaysRender(true);
			game.setVSync(true);
			game.setShowFPS(false);
			game.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		t.render(g);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		t = new Table();

	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		t.update(delta);
		
	}

}

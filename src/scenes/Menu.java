package scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.Game;
import ui.MyButton;
import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods {

	private BufferedImage img;
	private ArrayList<BufferedImage> sprites = new ArrayList<>();
	private Random random;
	
	private MyButton bPlaying, bSettings, bQuit;
	
	public Menu(Game game) {
		super(game);
		random = new Random();
		importImg();
		initButtons();
	}

	private void initButtons() {
		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 150;
		int yOffset = 100;
		
		bPlaying = new MyButton("Play", x, y, w, h);
		bSettings = new MyButton("Settings", x, y + yOffset, w, h);
		bQuit = new MyButton("Quit", x, y + yOffset * 2, w, h);
		
	}

	@Override
	public void render(Graphics g) {
		drawButtons(g);
	}
	
	private void drawButtons(Graphics g) {
		bPlaying.draw(g);
		bSettings.draw(g);
		bQuit.draw(g);
				
	}

	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/spriteatlas.png");
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void mouseClicked(int x, int y) {
		if(bPlaying.getBounds().contains(x, y)) 
			SetGameState(PLAYING);
		else if (bSettings.getBounds().contains(x, y))
			SetGameState(SETTINGS);
		else if (bQuit.getBounds().contains(x, y))
			System.exit(0);
	}

	@Override
	public void mouseMoved(int x, int y) {
		bPlaying.setMouseOver(false);
		bSettings.setMouseOver(false);
		bQuit.setMouseOver(false);
		
		if(bPlaying.getBounds().contains(x, y)) 
			bPlaying.setMouseOver(true);
		else if (bSettings.getBounds().contains(x, y))
			bSettings.setMouseOver(true);
		else if (bQuit.getBounds().contains(x, y))
			bQuit.setMouseOver(true);
		
	}

	@Override
	public void mousePressed(int x, int y) {
		if (bPlaying.getBounds().contains(x, y))
			bPlaying.setMousePressed(true);
		else if (bSettings.getBounds().contains(x, y))
			bSettings.setMousePressed(true);
		else if (bQuit.getBounds().contains(x, y))
			bQuit.setMousePressed(true);
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
		
	}
	
	public void mouseLeft(int x, int y) {
		// Buttons should be defaulted then when mouse is pressed but leaves the Panel
	}

	private void resetButtons() {
		bPlaying.resetBooleans();
		bSettings.resetBooleans();
		bQuit.resetBooleans();
	}
}

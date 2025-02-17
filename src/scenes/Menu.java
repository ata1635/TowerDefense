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
		loadSprites();
		initButtons();
	}

	private void initButtons() {
		bPlaying = new MyButton("Play", 100, 100, 100, 30);
		
	}

	@Override
	public void render(Graphics g) {
		
		
		drawBttons(g);
	}
	
	private void drawBttons(Graphics g) {
		bPlaying.draw(g);
		
	}

	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/spriteatlas.png");
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadSprites() {
		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < 10; x++) {
				sprites.add(img.getSubimage(x * 32, y * 32, 32, 32));
			}
		}	
	}
	
	private int getRandomInt() {
		return random.nextInt(100);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if(bPlaying.getBounds().contains(x, y)) {
			SetGameState(PLAYING);
		}	
	}

	@Override
	public void mouseMoved(int x, int y) {
		bPlaying.setMouseOver(false);
		if(bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMouseOver(true);
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		
		if(bPlaying.getBounds().contains(x, y)) {
			bPlaying.setMousePressed(true);
		}		
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
		
	}

	private void resetButtons() {
		bPlaying.resetBooleans();
		
	}
}

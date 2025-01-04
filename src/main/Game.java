package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends JFrame {
	private GameScreen gameScreen;
	private BufferedImage img;
	
	public Game() {
		importImg();
		
		setSize(640, 640);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Tower Defense");
		
		gameScreen = new GameScreen();
		add(gameScreen); //method is used to add a component (like a button, text field, label, etc.) to a container (like a frame, panel, or applet).
	}
	
	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/spriteatlas.png");
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
	}

}

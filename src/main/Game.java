package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable {
	private GameScreen gameScreen;
	private BufferedImage img;
	private Thread gameThread;
	
	private final double FPS_SET = 60.0;
	private final double UPS_SET = 60.0;
	
	public Game() {
		importImg();
		setSize(640, 640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Tower Defense");
		
		gameScreen = new GameScreen(img);
		add(gameScreen); //method is used to add a component (like a button, text field, label, etc.) to a container (like a frame, panel, or applet).
		setVisible(true); //this always HAS to be at the end
	}
	
	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/spriteatlas.png");
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void start() {
		gameThread = new Thread(this) {}; //this refers to the run() method
		gameThread.start(); //Executes thread
	}
	
	private void updateGame() {
		//System.out.println("Game Updated");
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	@Override
	public void run() { //runs the thread
		//for setting FPS and UPS:
		double timePerFrame = 1_000_000_000.0 / FPS_SET; 
		double timePerUpdate = 1_000_000_000.0 / UPS_SET; 
		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime()	;
		long lastTimeCheck = System.currentTimeMillis();
		int frames = 0;
		int updates = 0;
		
		while(true) {	
			//Render
			if(System.nanoTime() - lastFrame >= timePerFrame) {
				repaint(); //This calls paintComponent(Graphics g) in GameScreen until the window is closed
				lastFrame = System.nanoTime();
				frames++;
			}
			//Update
			if(System.nanoTime() - lastUpdate >= timePerUpdate) {
				updateGame();
				lastUpdate = System.nanoTime();
				updates++;
			}
			//FPS & UPS check
			if(System.currentTimeMillis() - lastTimeCheck >= 1000) {
				System.out.println("FPS: " + frames + " | " + "UPS: " + updates);
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}
		}	
	}
}

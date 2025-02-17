package main;

import javax.swing.JFrame;

import inputs.MyMouseListener;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;
import inputs.KeyboardListener;

public class Game extends JFrame implements Runnable {
	
	private GameScreen gameScreen;
	private Thread gameThread;
	
	private final double FPS_SET = 60.0;
	private final double UPS_SET = 60.0;
	
	//Classes
	private Render render;
	private Menu menu;
	private Playing playing;
	private Settings settings;

	public Game() {
		initClasses();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Tower Defense");	
		add(gameScreen); //method is used to add a component (like a button, text field, label, etc.) to a container (like a frame, panel, or applet).
		pack(); //HAS to be called AFTER any containers or other components, here: add(gameScreen) 
		setVisible(true); //this always HAS to be at the end
	}

	private void initClasses() {
		render = new Render(this);
		gameScreen = new GameScreen(this);	
		menu = new Menu(this);
		playing = new Playing(this);
		settings = new Settings(this);
	}
	
	private void start() {
		gameThread = new Thread(this) {}; //this refers to the run() method
		gameThread.start(); //Executes thread via start() in Thread.java
	}
	
	private void updateGame() {
		//System.out.println("Game Updated");
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.gameScreen.initInputs(); //initInputs() is called explicitly after gameScreen is fully added to the JFrame to avoid issues with input listeners.
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
		long now;
		
		while(true) {	
			now = System.nanoTime();
			//Render
			if(now - lastFrame >= timePerFrame) {
				repaint(); //This calls paintComponent(Graphics g) in GameScreen until the window is closed
				lastFrame = now;
				frames++;
			}
			//Update
			if(now - lastUpdate >= timePerUpdate) {
				updateGame();
				lastUpdate = now;
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
	
	//Getters & Setters
	public Render getRender() {
		return render;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public Playing getPlaying() {
		return playing;
	}

	public Settings getSettings() {
		return settings;
	}

}

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
	
	private MyMouseListener myMouseListener;
	private KeyboardListener keyboardListener;
	
	//Classes
	private Render render;
	private Menu menu;
	private Playing playing;
	private Settings settings;

	public Game() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Tower Defense");	
		initClasses();
		add(gameScreen); //method is used to add a component (like a button, text field, label, etc.) to a container (like a frame, panel, or applet).
		pack(); //HAS to be called AFTER any containers or other components, here: add(gameScreen) 
		setVisible(true); //this always HAS to be at the end
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.initInputs();
		game.start();
	}
	
	private void start() {
		gameThread = new Thread(this) {}; //this refers to the run() method
		gameThread.start(); //Executes thread via start() in Thread.java{c}
	}
		
	private void initClasses() {
		render = new Render(this);
		gameScreen = new GameScreen(this);	
		menu = new Menu(this);
		playing = new Playing(this);
		settings = new Settings(this);
	}


	private void initInputs() {
		myMouseListener = new MyMouseListener();
		keyboardListener = new KeyboardListener();
		
		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);
		addKeyListener(keyboardListener);
		
		requestFocus(); //Has to be here otherwise bugs can happen
	}
	
	private void updateGame() {
		//System.out.println("Game Updated");
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

package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import inputs.KeyboardListener;
import inputs.MyMouseListener;

public class GameScreen extends JPanel {
	
	private Game game;
	private Dimension size;
	
	private MyMouseListener myMouseListener;
	private KeyboardListener keyboardListener;
	
	public GameScreen(Game game) {
		this.game = game;
		setPanelSize();
	}
	
	public void initInputs() {
		myMouseListener = new MyMouseListener(game);
		keyboardListener = new KeyboardListener();
		
		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);
		addKeyListener(keyboardListener);
		
		requestFocus(); //Has to be here otherwise bugs can happen
	}
	
	private void setPanelSize() {
		size = new Dimension(640, 740);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		
	}

	//Gets called by the repaint() method in game.java
	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
		game.getRender().render(g);
	}
	
}

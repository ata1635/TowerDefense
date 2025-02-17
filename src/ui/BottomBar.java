package ui;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import objects.Tile;
import scenes.Playing;

public class BottomBar {

	private int x, y, width, height;
	private MyButton bMenu;
	private Playing playing;
	private ArrayList<MyButton> tileButtons = new ArrayList<>();
	
	public BottomBar(int x, int y, int width, int height, Playing playing) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.playing = playing;
		
		initButtons();
	}
	
	private void initButtons() {
		bMenu = new MyButton("Menu", 2, 642, 100, 30);
		
		int w = 50;
		int h = 50;
		int xStart = 110;
		int yStart = 650;
		int xOffset = (int)(w * 1.1f);
		
		int i = 0;
		for(Tile tile : playing.getTileManager().tiles) {
			tileButtons.add(new MyButton(tile.getName(), xStart + xOffset * i, yStart, w, h, i));
			i++;
		}
	}
	
	public void draw(Graphics g) {
		//Background
		g.setColor(new Color(201, 152, 104));
		g.fillRect(x, y, width, height);
		
		//Buttons
		drawButtons(g);
	}
	
	private void drawButtons(Graphics g) {
		bMenu.draw(g);
		drawTileButtons(g);
	}
	
	private void drawTileButtons(Graphics g) {
		for(MyButton b : tileButtons) {
			g.drawImage(getBuffImg(b.getID()), b.x, b.y, b.widht, b.height, null);
		}
	}
	
	public BufferedImage getBuffImg(int id) {
		return playing.getTileManager().getSprite(id);
	}

	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);

	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);

	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);

	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();

	}
}

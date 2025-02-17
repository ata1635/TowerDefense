package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MyButton {
	
	public int x, y, widht, height, id;
	private String text;
	private Rectangle bounds; //We use it to check if Mouse is inside bounds
	private boolean mouseOver, mousePressed;
	
	//For normal buttons
	public MyButton(String text, int x, int y, int width, int height) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.widht = width;
		this.height = height;
		this.id = -1; //We wont use it but have it here to prevent potential bugs
		initBounds();
	}
	
	//For Tile-buttons
	public MyButton(String text, int x, int y, int width, int height, int id) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.widht = width;
		this.height = height;
		this.id = id;
		initBounds();
	}
	
	private void initBounds() {
		this.bounds = new Rectangle(x, y, widht, height);
	}
	
	public void draw(Graphics g) { //Here is all the code to draw the button
		//Body
		drawBody(g);
						
		//Border
		drawBorder(g);
	
		//Text
		drawText(g);
			
	}
	
	private void drawBorder(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(x, y, widht, height);
		if(mousePressed) {
			g.drawRect(x + 1, y + 1, widht - 2, height - 2);
			g.drawRect(x + 2, y + 2, widht - 4, height - 4);			
		}
	}

	private void drawBody(Graphics g) {
		if(mouseOver) {
			g.setColor(Color.lightGray);
		} else {
			g.setColor(Color.white);	
		}
		g.fillRect(x, y, widht, height);
		
	}

	private void drawText(Graphics g) {
		int w = g.getFontMetrics().stringWidth(text);
		int h = g.getFontMetrics().getHeight();		
		g.drawString(text, x - w / 2 + widht / 2, y + h / 3 + height / 2);
		
	}
	
	public void resetBooleans() {
		this.mouseOver = false;
		this.mousePressed = false;
	}
	
	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getID() {
		return id;
	}
	
}

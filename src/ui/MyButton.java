package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MyButton {
	
	private int x, y, widht, height;
	private String text;
	private Rectangle bounds; //We use it to check if Mouse is inside bounds
	
	public MyButton(String text, int x, int y, int width, int height ) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.widht = width;
		this.height = height;
		initBounds();
	}
	
	private void initBounds() {
		this.bounds = new Rectangle(x, y, widht, height);
	}
	
	public void draw(Graphics g) { //Here is all the code to draw the button
		//Body
		g.setColor(Color.white);
		g.fillRect(x, y, widht, height);
				
		//Border
		g.setColor(Color.black);
		g.drawRect(x, y, widht, height);
		
		//Text
		drawText(g);
			
	}
	
	private void drawText(Graphics g) {
		int w = g.getFontMetrics().stringWidth(text);
		int h = g.getFontMetrics().getHeight();		
		g.drawString(text, x - w / 2 + widht / 2, y + h / 3 + height / 2);
		
	}

	public Rectangle getBounds() {
		return bounds;
	}

	
}

package me.brendand.Objects;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Object that holds a square Shape with location, color and brightness
 * @author brendan
 *
 */
public class Unit {
	private int x,y;
	//private Table t;
	private Rectangle rect;
	private final int d = 4; //dimensions
	private Color color;
	private int r,g,b;

	
	public Unit(int x, int y, Table t) {
		//this.t=t;
		this.x=x;
		this.y=y;
	    r=18;b=21;g=21;
		rect = new Rectangle(x*d, y*d, d, d);
		color = new Color(r,g,b);
	}
	
	public void update() {
		
	}
	
	public void setColor(int r, int g, int b) {
		this.r=r;
		this.g=g;
		this.b=b;
		color = new Color(r,g,b);
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fill(rect);
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getR() {
		return r;
	}
	
	public int getG() {
		return g;
	}
	
	public int getB() {
		return b;
	}

}

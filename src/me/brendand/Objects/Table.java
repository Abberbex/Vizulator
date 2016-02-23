package me.brendand.Objects;

import java.util.Random;

import me.brendand.main.Generator;
import org.newdawn.slick.Graphics;

/**
 * Object that holds all units, functions for getting rows and columns
 * and getting individual Units.
 * 0------------------------(n-1)x
 * |--------------------------
 * |--------------------------
 * |--------------------------
 * |--------------------------
 * |--------------------------
 * |--------------------------
 * |--------------------------
 * (n-1)-------------------------
 * y
 * @author brendan
 *
 */
public class Table {

	private final int n = 256; //size of table display
	private Unit[][] units;  //rows 1-n(0-(n-1)
	public Generator gen;
	private Random rand = new Random();


	public Table() {
		units = new Unit[n][n];
		gen = new Generator(this, 2);
		createTable();
	}

	private void createTable() {
		//make table
		for(int i = 0; i<(n); i++) {
			for(int k = 0; k<(n); k++) {
				units[i][k] = new Unit(i,k,this);
			}
		}
	}

	public Unit getUnit(int x, int y, Unit u) {
		if(x<0||y<0||x>(n-1)||y>(n-1)) {
			return u;
		}
		return units[x][y];
	}

	public void update(int delta) {
		for(int i = 0; i<(n); i++) {
			for(int k = 0; k<(n); k++) {
				units[i][k].update();
			}
		}
		gen.update(delta);
	}

	public void render(Graphics g) {
		for(int i = 0; i<(n); i++) {
			for(int k = 0; k<(n); k++) {
				units[i][k].render(g);
			}
		}
	}
	
	public Unit getRandomUnit() {
		return units[rand.nextInt(n)][rand.nextInt(n)];
	}
	
	public int getN() {
		return n;
	}
}

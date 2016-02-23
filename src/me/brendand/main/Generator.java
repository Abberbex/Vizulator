package me.brendand.main;

import java.util.Random;

import org.newdawn.slick.Color;

import me.brendand.Objects.Table;
import me.brendand.Objects.Unit;

/**
 * Takes t and changes values based on Generator.*
 * Functions will return array of drawable objects for 
 * container to paint.
 * @author Brendan Dickerson
 *
 */
public class Generator {
	private Table t;
	private Random rand = new Random();
	private Pallete pal;
	//private int r,g,b;
	private int code;
	public final int RANDOM_GEN = 0;
	public final int SPLASH_GEN = 1;
	public final int SPIRAL_GEN = 2;

	private Spiral branch1;
	private NearestRandom branch3;
	private NearestRandom branch4;
	private NearestRandom branch5;
	private NearestRandom branch2;
	Unit[][] work;



	public Generator(Table t, int code) {
		pal = new Pallete();
		this.t=t;
		this.code=code;
		work = new Unit[t.getN()][t.getN()];
		branch1 = new Spiral(t.getN()/2,t.getN()/2);
		branch2 = new NearestRandom();
		branch3 = new NearestRandom();
		branch4 = new NearestRandom();
		branch5 = new NearestRandom();

	}

	public void update(int delta) {
		branch1.generate(delta); 
		branch2.generate(delta);
		branch3.generate(delta);
		branch5.generate(delta);


	}


	/**
	 * Generation that starts at a random point and generates a ring that gets bigger outwardly.
	 * 
	 * @author brendan
	 *
	 */
	private class OutwardRing {
		private Unit current;


		public OutwardRing() {

		}

		public void generate(int delta) {

		}

	}

	/**
	 * Generation that forms a square spiral pattern.
	 * 
	 * @author Breadbran
	 */
	private class Spiral {
		private Unit current;
		private int cx;
		private int cy;
		private int d;
		private int c; //counter for spiral arms
		private int x, y;
		private Direction direction;
		private boolean running;



		public Spiral(int x, int y) {
			running=true;
			current = null;
			cx=x;
			cy=y;
			c = 1;
			direction = Direction.UP;
			current = t.getUnit(cx, cy, null);  //  D:
			System.out.print("("+cx+" , "+cy+" )");
		}

		public void generate(int delta) {
			current = t.getUnit(cx, cy, current);
			work[cx][cy] = current;

			d+=delta;
			if (cx<t.getN()&&cy<t.getN()) {
				if(d>50){
					d=0;
					switch (direction) {
					case UP:  					//when moving up
						for(int i = 0; i < c; i++ ) {
							if(work[cx][cy]!=null){
								System.out.print("up\n");
								work[cx][cy].setColor(255, 255, 255); 
								//set space white
								cy++;
								current = t.getUnit(cx, cy, current);
							} if (t.getUnit(cx,cy,null)!=null){

								work[cx][cy] = current;
							} else { cx--;
							running = false;}
						}
						direction = Direction.LEFT; //change direction = work[cx][cy]; //set current space to where work left off
						c++; //increase spiral arm count
						break;

					case LEFT:
						for(int i = 0; i < c; i++ ) {
							if(work[cx][cy]!=null){
								work[cx][cy].setColor(255, 255, 255);

								System.out.print("left\n");
								//set space white
								cx--;
								current = t.getUnit(cx, cy, current);
							} if (t.getUnit(cx,cy,null)!=null){

								work[cx][cy] = current;
							} else { cx--;
							running = false;}

						}
						direction = Direction.DOWN; //change direction
						c++; //increase spiral arm count	

						break;

					case DOWN: 
						for(int i = 0; i < c; i++ ) {
							if(work[cx][cy]!=null){
								work[cx][cy].setColor(255, 255, 255);				
								System.out.print("down\n");
								//set space white
								cy--;
								current = t.getUnit(cx, cy, current);
							} if (t.getUnit(cx,cy,null)!=null){

								work[cx][cy] = current;
							} else { cx--;
							running = false;}
						}
						direction = Direction.RIGHT; //change direction
						c++; //increase spiral arm count

						break;

					case RIGHT:
						for(int i = 0; i < c; i++ ) {
							if(work[cx][cy]!=null){
								work[cx][cy].setColor(255, 255, 255);
								System.out.print("right\n");
								//set space white
								cx++;
								current = t.getUnit(cx, cy, current);
							} if (t.getUnit(cx,cy,null)!=null){
								work[cx][cy] = current;
							} else { cx--;
							running = false;}
						}
						direction = Direction.UP; //change direction
						c++; //increase spiral arm count
						break;
					}
				}
			}
		}
	}

	/**
	 * Starts at a random point and works its way randomly across the table
	 * changing between red, green, and blooooo
	 * @author brendan
	 *
	 */
	private class NearestRandom {
		private Unit current;
		private int x;
		private int d; //delta accumulator for color change
		boolean red, blue, green;

		public NearestRandom() { 
			red=false;blue=false;green=false;
			current = null;

		}

		public void generate(int delta) {
			if(current == null) {
				current = t.getRandomUnit(); //start at random unit
			}

			work[current.getX()][current.getY()] = current;
			d+=delta;

			if(d>50) { //color change
				d=0;
				for (int i = 0; i < (t.getN()); i++) {
					for (int k = 0; k < (t.getN()); k++) {
						if (work[i][k] != null) {
							if(!red&&!blue&&!green) {
								work[i][k].setColor(work[i][k].getR() + 1,
										work[i][k].getG(),
										work[i][k].getB()); 
								if(work[i][k].getR()>225){
									red=true;
								}
							}
							if(red) {
								work[i][k].setColor(work[i][k].getR() -1,
										work[i][k].getG() + 1,
										work[i][k].getB());
								if(work[i][k].getG()>225){
									red=false; green=true;
								}
							}
							if(green) {
								work[i][k].setColor(work[i][k].getR(),
										work[i][k].getG() - 1,
										work[i][k].getB() + 1);
								if(work[i][k].getB()>225){
									green=false; blue=true;
								}
							}
							if(blue) {
								work[i][k].setColor(work[i][k].getR() + 1,
										work[i][k].getG(),
										work[i][k].getB()-1);
								if(work[i][k].getR()>225){
									blue=false; red=true;
								}
							}
						}
					}
				}
			}
			x = rand.nextInt(4);
			switch (x) { //random nearest neighbor movement
			case 0: current = t.getUnit(current.getX()+1, current.getY(),current); break;
			case 1: current = t.getUnit(current.getX()-1, current.getY(),current); break;
			case 2: current = t.getUnit(current.getX(), current.getY()+1,current); break;
			case 3: current = t.getUnit(current.getX(), current.getY()-1,current); break;
			}
		}
	}

	/**
	 * Get a random color from the default pallete
	 * @return
	 */
	public Color getRandomColor() {
		int i = rand.nextInt(11);
		Color c = Color.black;
		switch (i) {
		case 0: c = pal.A0; break;
		case 1: c = pal.A1; break;
		case 2: c = pal.A2; break;
		case 3: c = pal.B0; break;
		case 4: c = pal.B1; break;
		case 5: c = pal.B2; break;
		case 6: c = pal.C0; break;
		case 7: c = pal.C1; break;
		case 8: c = pal.C2; break;
		case 9: c = pal.D0; break;
		case 10: c = pal.D1; break;
		case 11: c = pal.D2; break;
		}
		return c;
	}

	public class Pallete { //move to Gen
		public final Color A0;
		public final Color A1;
		public final Color A2;
		public final Color B0;
		public final Color B1;
		public final Color B2;
		public final Color C0;
		public final Color C1;
		public final Color C2;
		public final Color D0;
		public final Color D1;
		public final Color D2;
		public Color mod;

		public Pallete() {
			A0 = new Color(173,139,37);
			A1 = new Color(206,166,44);
			A2 = new Color(125,95,5);
			B0 = new Color(23,108,99);
			B1 = new Color(27,128,118);
			B2 = new Color(3,78,70);
			C0 = new Color(173,95,37);
			C1 = new Color(206,113,44);
			C2 = new Color(125,56,5);
			D0 = new Color(40,40,121);
			D1 = new Color(48,48,145);
			D2 = new Color(16,16,87);
			mod = new Color(0,0,0);
		}

		public void updateModular(int r, int g, int b) {
			mod = new Color(r,g,b);
		}
	}

	public enum Direction { UP, DOWN, LEFT, RIGHT
	}
}

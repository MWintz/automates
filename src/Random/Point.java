package Random;

public class Point {
	
	private int x;
	private int y;
	
	private final static float PowRacine = 0.5f;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public double distance (Point p1) {
		float x,y;
		double X,Y;
		
		x = this.x - p1.getX();
		y = this.y - p1.getY();
		
		X = Math.pow(x, 2);
		Y = Math.pow(y, 2);
		
		return Math.pow((X+Y),PowRacine);
	}
	
	public boolean equals(Object obj) {
		boolean egalite = false;
		if(obj instanceof Point)
			egalite =( this.x == ((Point)obj).getX() ) && ( this.y == ((Point)obj).getY() );	
		return egalite;
	}
	
	public String toString() {
		return "("+x+","+y+")";
	}
}

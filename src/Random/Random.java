package Random;

public class Random {
	
	public static double randomDouble( double min ,double max) {
        return (Math.random() * (max - min)) + min;
	}
	
	public static int randomInt( int min ,int max) {	
		return (int)( Math.random()*( max - min + 1 ) ) + min;
	}
	
	public static int randomInt1(int max) {
		return (int)(Math.random() * max);
	}
	
	public static int randomInt(int max, boolean included) {
		if(included)
			return (int)(Math.random() * (max + 1));
		else
			return (int)(Math.random() * max);
	}
	
	public static boolean randomBool() {
		return (Math.random()<0.5);
	}
	
	public static boolean randomBool(int n) {
		return (Math.random()<(float)n/10);
	}
}
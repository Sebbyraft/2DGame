package toolkit;


public class Maths {
	
	public static float dist(Vec2 positionA, Vec2 positionB) {
		float x = positionA.getX() - positionB.getX();
		float y = positionA.getY() - positionB.getY();
		
		return (float) Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
	}

}

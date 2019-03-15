package toolkit;

public class Maths {
	
	public static Vec2 calculateDirection(Vec2 mousePosition) {
		
		Vec2 direction = new Vec2(0, 0);
		
		if(mousePosition.getX() == 0) {
			direction.setX(0);
		} 
		
		if(mousePosition.getY() == 0) {
			direction.setY(0);
		} 
		
		
		return direction;
		
	}
	
	public static float dist(Vec2 positionA, Vec2 positionB) {
		float x = positionA.getX() - positionB.getX();
		float y = positionA.getY() - positionB.getY();
		
		return (float) Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
	}

}

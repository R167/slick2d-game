package core;

public abstract class Sprite {
	protected float x;
	protected float y;
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	
	public abstract int getWidth();
	public abstract int getHeight();
	
	public boolean touching(Sprite other) {
		return x < other.getX() + other.getWidth() &&
			   x + getWidth() > other.getX() &&
			   y < other.getY() + other.getHeight() &&
			   getHeight() + y > other.getY();
	}
}

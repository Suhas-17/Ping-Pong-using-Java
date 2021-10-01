package version1;

import java.awt.*;

public interface Paddle {//Interface for human and AI paddle contains abstract methods.
	public void draw(Graphics g);

	public void move();

	public int getY();
}

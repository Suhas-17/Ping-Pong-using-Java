package version2;

import java.awt.*;

public class AIPaddle implements Paddle {
	double y, yVel;
	int player, x;
	Ball b1;

	public AIPaddle(int player, Ball b) {
		b1 = b;
		y = 210;
		yVel = 0;
		if (player == 1)
			x = 20;
		else
			x = 660;
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, (int) y, 20, 80);
	}

	public void move() {
		y = b1.getY() - 40;// AI Paddle moves wherever the ball moves using its y co-ordinate.

		if (y < 0)// Keeps the paddle within screen.
			y = 0;
		if (y > 420)
			y = 420;
	}

	public int getY() {// Returns y co-ordinate of AIPaddle.
		return (int) y;
	}

}
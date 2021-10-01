package version1;

import java.awt.*;

public class HumanPaddle implements Paddle {
	double y, yVel;
	boolean upAccel, downAccel;
	final double FRICTION = 0.94;
	int player, x;

	public HumanPaddle(int player) {
		upAccel = false;
		downAccel = false;
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
		if (upAccel) {
			yVel -= 2;
		} else if (downAccel) {
			yVel += 2;
		} else if (!upAccel && !downAccel) {
			yVel *= FRICTION;
		}
		if (yVel >= 5)
			yVel = 5;
		if (yVel <= -5)
			yVel = -5;

		y += yVel;
		if (y < 0)
			y = 0;
		if (y > 420)
			y = 420;
	}

	public void setUpAccel(Boolean input) {
		upAccel = input;

	}

	public void setDownAccel(Boolean input) {
		downAccel = input;

	}

	public int getY() {
		return (int) y;// returns integer value of y co-ordinate.
	}

}

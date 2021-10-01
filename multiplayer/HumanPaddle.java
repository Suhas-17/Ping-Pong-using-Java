package version2;

import java.awt.*;

public class HumanPaddle implements Paddle {
	double y, yVel;// y co-ordinate and paddle's y velocity.
	boolean upAccel, downAccel;// upward and downward accleration.
	final double FRICTION = 0.94;
	int player, x;// player decides player position(left or right).x co-ordinate.

	public HumanPaddle(int player) {// HumanPaddle constructor.
		upAccel = false;// initially acceleration will be set to false
		downAccel = false;
		y = 210;// Paddle placed at middle of y axis.
		yVel = 0;// Paddle velocity is zero initially.
		if (player == 1)// player value is passed from tennis method to choose paddle position(left or
						// right).
			x = 20;// 20 space left for borders.
		else
			x = 660;// 20 paddle length+20 spacing.
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, (int) y, 20, 80);// white rectangle(paddle) of size 20*80.
	}

	public void move() {
		if (upAccel) {// if up arrow is pressed velocity is increased negatively(upward).
			yVel -= 2;
		} else if (downAccel) {// if down arrow is pressed velocity is increased positively(downward).
			yVel += 2;
		} else if (!upAccel && !downAccel) {// Instead of stopping the paddles suddenly,friction co-efficient is used to
											// maintain smoothness.
			yVel *= FRICTION;
		}
		if (yVel >= 5)// used to keep the paddle speed in control(velocity is kept below 5).
			yVel = 5;
		if (yVel <= -5)
			yVel = -5;

		y += yVel;// velocity is added to y co-ordinate.
		if (y < 0)// to keep paddle inside the screen.
			y = 0;
		if (y > 420)// 500-80paddle length.
			y = 420;
	}

	public void setUpAccel(Boolean input) {
		upAccel = input;

	}

	public void setDownAccel(Boolean input) {
		downAccel = input;

	}

	public int getY() {
		return (int) y;// returns integer value of y co-ordinate.Used to check collision.
	}

}

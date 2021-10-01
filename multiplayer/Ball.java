package version2;

import java.awt.*;

public class Ball {
	double xVel, yVel, x, y;// velocity and co-ordinates of ball.
	public Ball() {// Ball constructor.
		x = 350;// Ball is at the centre initially.
		y = 250;
		xVel = getRandomSpeed() * getRandomDirection();// velocity and direction are decide random while starting the
														// game.
		yVel = getRandomSpeed() * getRandomDirection();
	}

	public double getRandomSpeed() {
		return (Math.random() * 3 + 1);// Generates random number.
	}

	public int getRandomDirection() {
		int rand = (int) (Math.random() * 2);
		if (rand == 1)// This decides whether the ball goes left or right at the beginning.
			return 1;
		else
			return -1;
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int) x - 10, (int) y - 10, 20, 20);// diameter of the ball=20, -10 coz of radius.
	}

	public void checkPaddleCollision(Paddle p1, Paddle p2) {
		// if the ball collides with paddles,xVel is multiplied with -1 to change its
		// direction.
		if (x <= 50 && x > 30) {
			if (y >= p1.getY() && y <= p1.getY() + 80) {
				xVel = -xVel;
			}

		} else if (x >= 650 && x < 670) {
			if (y >= p2.getY() && y <= p2.getY() + 80) {
				xVel = -xVel;
			}

		}
	}

	public void move() {
		x += xVel;
		y += yVel;

		if (y < 10)// Keeps the ball within certain height.
			yVel = -yVel;
		if (y > 490)
			yVel = -yVel;
	}

	public int getX() {// Returns x co ordinate of Ball.
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

}

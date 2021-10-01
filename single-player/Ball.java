package version1;

import java.awt.*;

public class Ball {
	double xVel, yVel, x, y;
	int pts = 0;// score

	public Ball() {
		x = 350;
		y = 250;
		xVel = getRandomSpeed() * getRandomDirection();// velocity and direction are decided randomly while starting the game.
		yVel = getRandomSpeed() * getRandomDirection();
	}

	public double getRandomSpeed() {
		return (Math.random() + 2);// Generates random value from 2-3.
	}

	public int getRandomDirection() {
		double rand = Math.random();
		if (rand > 0.5)
			return 1;
		else
			return -1;
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int) x - 10, (int) y - 10, 20, 20);
	}

	public void checkPaddleCollision(Paddle p1, Paddle p2) {
		if (x <= 50 && x > 30) {
			if (y >= p1.getY() && y <= p1.getY() + 80) {
				xVel = -xVel;
				pts += 10;// when the ball collides with human paddle, score is incremented by 10pts.
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

		if (y < 10)
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

	public int getScore() {
		return pts;
	}
}

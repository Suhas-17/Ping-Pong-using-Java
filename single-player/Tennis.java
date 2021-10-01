package version1;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Tennis extends Applet implements Runnable, KeyListener {
	final int WIDTH = 700, HEIGHT = 500;
	Thread thread1;
	HumanPaddle p1;
	AIPaddle p2;
	Ball b1;
	Boolean gameStarted;
	Graphics gfx;
	Image img;

	public void init() {
		this.resize(WIDTH, HEIGHT);// Applet window is enlarged to required size.
		gameStarted = false;
		this.addKeyListener(this);
		p1 = new HumanPaddle(1);
		b1 = new Ball();
		p2 = new AIPaddle(2, b1);
		img = createImage(WIDTH, HEIGHT);
		gfx = img.getGraphics();
		thread1 = new Thread(this);
		thread1.start();// execution of thread begins.
	}

	public void paint(Graphics gfx) {
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);
		gfx.setFont(new Font("", Font.BOLD, 14));
		if (b1.getX() < -10 || b1.getX() > 710) {
			gfx.setColor(Color.red);
			gfx.drawString("GAME OVER", 315, 240);
			gfx.setColor(Color.white);
			gfx.drawString("SCORE ", 333, 40);

		} else {
			p1.draw(gfx);
			b1.draw(gfx);
			p2.draw(gfx);
		}
		if (!gameStarted) {// Until the game is started,the following message is displayed.
			gfx.setColor(Color.WHITE);
			gfx.drawString("PONG", 330, 100);
			gfx.drawString("PRESS ENTER TO BEGIN....", 265, 130);

		}
		gfx.drawString(" " + b1.getScore(), 345, 70);// Prints score.
		gfx.drawImage(img, 0, 0, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void run() {
		for (;;) {
			if (gameStarted) {
				p1.move();
				p2.move();
				b1.move();
				b1.checkPaddleCollision(p1, p2);
			}
			repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUpAccel(true);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAccel(true);
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			gameStarted = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUpAccel(false);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAccel(false);
		}
	}

	public void keyTyped(KeyEvent arg0) {

	}
}

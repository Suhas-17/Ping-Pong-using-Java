package version2;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Runnable is an interface that is to be implemented by a class whose instances are intended to be executed by a thread. run() method.
//The Keylistener interface for receiving keyboard events (keystrokes).
public class Tennis extends Applet implements Runnable, KeyListener {
	final int WIDTH = 700, HEIGHT = 500;
	int mode=1;
	Thread thread1;
	HumanPaddle p1;
	HumanPaddle p2;
	Ball b1;
	Boolean gameStarted;
	Graphics gfx;
	Image img;

	public void init() {
		this.resize(WIDTH, HEIGHT);// Applet window is enlarged to required size.
		gameStarted = false;
		this.addKeyListener(this);// To recognise input from keyboard.

		p1 = new HumanPaddle(1);// Create an object of class HumanPaddle,Ball,AIPaddle.
		p2 = new HumanPaddle(2);
		b1 = new Ball();
		img = createImage(WIDTH, HEIGHT);// To avoid painting the whole screen black for every frame.
		gfx = img.getGraphics();
		thread1 = new Thread(this);
		thread1.start();// execution of thread(run() method).
	}

	public void paint(Graphics gfx) {
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);// The complete portion of the applet is painted black.
		gfx.setFont(new Font("", Font.BOLD, 14));// This command is used to set font.
		if (b1.getX() < -10 || b1.getX() > 710) {// Check if the ball as gone outside the screen to end the game.
			gfx.setColor(Color.red);
			gfx.drawString("GAME OVER", 315, 210);
			gfx.setColor(Color.white);
			if (b1.getX() > 710)
				gfx.drawString("PLAYER 1 WINS ", 310, 240);
			if (b1.getX() < -10)
				gfx.drawString("PLAYER 2 WINS ", 305, 240);

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
		gfx.drawImage(img, 0, 0, this);// The whole screen is painted black for every frame. To avoid screen flickering or white flashes.
	}

	public void update(Graphics g) {// Method of an applet.
		paint(g);
	}

	public void run() {
		for (; b1.getX() >= -10 || b1.getX() <= 710;) {
			if (gameStarted) {
				p1.move();// to move paddles.
				p2.move();
				b1.move();// to move ball.
				b1.checkPaddleCollision(p1, p2);// checks for ball and paddle collision.
			}
			repaint();
			try {
				Thread.sleep(10);// 10ms break to slow down the program for the game to be visible to the player.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {// When the up arrow is pressed the following code is executed.
			p1.setUpAccel(true);// upward acceleration is increased.
		} else if (e.getKeyCode() == KeyEvent.VK_S) {// Similarly down arrow.
			p1.setDownAccel(true);// downward accleration is increased.
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {// When the up arrow is pressed the following code is executed.
			p2.setUpAccel(true);// upward acceleration is increased.
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {// Similarly down arrow.
			p2.setDownAccel(true);// downward accleration is increased.

		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {// Press Enter to begin the game.
			gameStarted = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {// When the up arrow is released the following code is executed.
			p1.setUpAccel(false);// upward acceleration is decreased.
		} else if (e.getKeyCode() == KeyEvent.VK_S) {// Similarly down arrow.
			p1.setDownAccel(false);// downward accleration is decreased.
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {// When the up arrow is released the following code is executed.
			p2.setUpAccel(false);// upward acceleration is decreased.
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {// Similarly down arrow.
			p2.setDownAccel(false);// downward accleration is decreased.
		}
	}

	public void keyTyped(KeyEvent arg0) {// Method of KeyListener.Not used in this program.

	}
}

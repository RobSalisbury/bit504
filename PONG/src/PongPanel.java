import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;

public class PongPanel extends JPanel implements ActionListener, KeyListener{

	private final static Color BACKGROUND_COLOR = Color.BLACK;
	
	private final static int TIMER_DELAY = 5;
	
	GameState gameState = GameState.Initialising;
	
	ball ball;
	paddle paddle1, paddle2;
	
	public PongPanel() {
		setBackground(BACKGROUND_COLOR);
		Timer timer = new Timer(TIMER_DELAY, this);
		timer.start();
	}
	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void createObjects() {
		ball = new ball(getWidth(), getHeight());
		paddle1 = new paddle(Player.One, getWidth(), getHeight());
		paddle2 = new paddle(Player.Two, getWidth(), getHeight());
	}
	
	private void update() {
		switch(gameState) {
		case Initialising: {
			createObjects();
			gameState = GameState.Playing;
			break;
		}
		case Playing: {
			break;
		}
		case GameOver: {
			break;
		}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		update();
		repaint();
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintDottedLine(g);
		if(gameState != GameState.Initialising) {
			paintSprite(g, ball);
			paintSprite(g, paddle1);
			paintSprite(g, paddle2);
		}

	}
	
	 private void paintDottedLine(Graphics g) {
	      Graphics2D g2d = (Graphics2D) g.create();
	         Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
	         g2d.setStroke(dashed);
	         g2d.setPaint(Color.WHITE);
	         g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
	         g2d.dispose();
	 }
	
	 private void paintSprite(Graphics g, sprites sprite) {
		 g.setColor(sprite.getcolor());
		 g.fillRect(sprite.getxPosition(), sprite.getyPosition(), sprite.getwidth(), sprite.getheight());
	 }
}

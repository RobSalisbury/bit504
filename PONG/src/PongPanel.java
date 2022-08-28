import java.awt.Color;
import java.awt.Font;
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
	
	private final static int BALL_MOVEMENT_SPEED = 4;
	
	private final static int POINTS_TO_WIN = 5;
	
	private final static int SCORE_TEXT_X = 100;
	
    private final static int SCORE_TEXT_Y = 100;
    
    private final static int SCORE_FONT_SIZE = 50;
    
    private final static String SCORE_FONT_FAMILY = "Serif";
	
	int player1Score = 0, player2Score = 0;
	  
	Player gameWinner;
	
	GameState gameState = GameState.Initialising;
	
	ball ball;
	paddle paddle1, paddle2;
	
	public PongPanel() {
		setBackground(BACKGROUND_COLOR);
		Timer timer = new Timer(TIMER_DELAY, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
	}
	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	  @Override
      public void keyPressed(KeyEvent event) {
          if(event.getKeyCode() == KeyEvent.VK_UP) {
              paddle2.setyVelocity(-5);
         } else if(event.getKeyCode() == KeyEvent.VK_DOWN) {
              paddle2.setyVelocity(5);
          }
          if(event.getKeyCode() == KeyEvent.VK_W) {
              paddle1.setyVelocity(-5);
         } else if(event.getKeyCode() == KeyEvent.VK_S) {
              paddle1.setyVelocity(5);
          }
      }
  
     @Override
     public void keyReleased(KeyEvent event) {
         if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) {
             paddle2.setyVelocity(0);
         }
         if(event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S) {
             paddle1.setyVelocity(0);
         }
     }

	public void createObjects() {
		ball = new ball(getWidth(), getHeight());
		paddle1 = new paddle(Player.One, getWidth(), getHeight());
		paddle2 = new paddle(Player.Two, getWidth(), getHeight());
	}
	private void moveObject(sprites obj) {
		obj.setxPosition(obj.getxPosition() + obj.getxVelocity(), getWidth());
		obj.setyPosition(obj.getyPosition() + obj.getyVelocity(), getHeight());
	}
	
	private void checkWallBounce() {
		if(ball.getxPosition() <= 0) {
			ball.setxVelocity(-ball.getxVelocity());
			resetBall();
		} else if(ball.getxPosition() >= getWidth() - ball.getwidth()) {
			ball.setxVelocity(-ball.getxVelocity());
			resetBall();
		}
		if(ball.getyPosition() <= 0 || ball.getyPosition() >= getHeight() - ball.getheight()) {
			ball.setyVelocity(-ball.getyVelocity());
		}
	}
	
	private void update() {
		switch(gameState) {
		case Initialising: {
			createObjects();
			gameState = GameState.Playing;
			ball.setxVelocity(BALL_MOVEMENT_SPEED);
			ball.setyVelocity(BALL_MOVEMENT_SPEED);
			break;
		}
		case Playing: {
			moveObject(paddle1);
			moveObject(paddle2);
			moveObject(ball);
			checkWallBounce();
			checkPaddleBounce();
			break;
		}
		case GameOver: {
			break;
		}
		}
	}
	
	private void resetBall() {
		ball.resetToInitialPosition();
	}
	
	private void checkPaddleBounce() {
	      if(ball.getxVelocity() < 0 && ball.getRectangle().intersects(paddle1.getRectangle())) {
	          ball.setxVelocity(BALL_MOVEMENT_SPEED);
	      }
	      if(ball.getxVelocity() > 0 && ball.getRectangle().intersects(paddle2.getRectangle())) {
	          ball.setxVelocity(-BALL_MOVEMENT_SPEED);
	      }
	}
	
	private void addScore(Player player) {
		if (player == Player.One) {
			player1Score++;
		} else if(player == Player.Two) {
			player2Score++;
		}
	}
	
	private void checkWin() {
		if(player1Score >= POINTS_TO_WIN) {
			gameWinner = Player.One;
			gameState = GameState.GameOver;
		} else if(player2Score >= POINTS_TO_WIN) {
			gameWinner = Player.Two;
			gameState = GameState.GameOver;
		}
			
	}
	
	 private void paintScores(Graphics g) {
         Font scoreFont = new Font(SCORE_FONT_FAMILY, Font.BOLD, SCORE_FONT_SIZE);
        String leftScore = Integer.toString(player1Score);
        String rightScore = Integer.toString(player2Score);
        g.setFont(scoreFont);
        g.drawString(leftScore, SCORE_TEXT_X, SCORE_TEXT_Y);
        g.drawString(rightScore, getWidth()-SCORE_TEXT_X, SCORE_TEXT_Y);
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
			paintScores(g);
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

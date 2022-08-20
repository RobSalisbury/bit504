import java.awt.Color;

public class ball extends sprites {

	private final static Color BALL_COLOR = Color.WHITE;
	private final static int BALL_WIDTH = 25;
	private final static int BALL_HEIGHT = 25;
	
	public ball(int panelWidth, int panelHeight) {
		setwidth(BALL_WIDTH);
		setheight(BALL_HEIGHT);
		setcolor(BALL_COLOR);
		setInitialPosition(panelWidth / 2 - (getwidth() / 2), panelHeight / 2 - (getheight() / 2));
		resetToInitialPosition();
		
	}
}

import java.awt.Color;

public class paddle extends sprites {

	private final static Color PADDLE_COLOR = Color.WHITE;
	private final static int PADDLE_WIDTH = 10;
	private final static int PADDLE_HEIGHT = 100;
	private final static int DISTANCE_FROM_EDGE = 40;
	
	public paddle (Player player, int panelWidth, int panelHeight) {
		setwidth(PADDLE_WIDTH);
		setheight(PADDLE_HEIGHT);
		setcolor(PADDLE_COLOR);
		int xPos;
		if(player == Player.One) {
			xPos = DISTANCE_FROM_EDGE;
		} else {
			xPos = panelWidth - DISTANCE_FROM_EDGE - getwidth();
		}
		setInitialPosition(xPos, panelHeight / 2 - (getheight() / 2));
		resetToInitialPosition();
	}
}

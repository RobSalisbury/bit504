import java.awt.Color;
import java.awt.Rectangle;

public class sprites {
	
private int xPosition, yPosition;
private int xVelocity, yVelocity;
private int width, height;
private Color color;
private int initialXPosition, initialYPosition;


public int getxPosition() {
	return xPosition;
}

public void setxPosition(int xPosition) {
	this.xPosition = xPosition;
}
public int getyPosition() {
	return yPosition;
}

public void setyPosition(int yPosition) {
	this.yPosition = yPosition;
}

public int getxVelocity() {
	return xVelocity;
}

public void setxVelocity(int xVelocity) {
	this.xVelocity = xVelocity;
}

public int getyVelocity() {
	return yVelocity;
}

public void setyVelocity(int yVelocity) {
	this.yVelocity = yVelocity;
}

public int getwidth() {
	return width;
}

public void setwidth(int width) {
	this.width = width;
}

public int getheight() {
	return height;
}

public void setheight(int height) {
	this.height = height;
}	
	
public Color getcolor() {
		return color;
}

public void setcolor(Color color) {
	this.color = color;	
}
public void setXPosition(int newX, int panelWidth) {
    xPosition = newX;
    if(xPosition < 0) {
    	xPosition = 0;
    } else if(xPosition + width > panelWidth) {
    	xPosition = panelWidth - width;
    }
}
public void setYPosition(int newY, int panelHeight) {
    yPosition = newY;
    if(yPosition < 0) {
    	yPosition = 0;
    } else if(yPosition + height > panelHeight) {
    	yPosition = panelHeight - height;
    }
}
public void setInitialPosition(int initialX, int initialY) {
	initialXPosition = initialX;
	initialYPosition = initialY;
}
public void resetToInitialPosition() {
	setxPosition(initialXPosition);
	setyPosition(initialYPosition);
}
public Rectangle getRectangle() {
    return new Rectangle(getxPosition(), getyPosition(), getwidth(), getheight());
}
}
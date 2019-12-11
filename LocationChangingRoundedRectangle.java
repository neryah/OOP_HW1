package homework1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class LocationChangingRoundedRectangle extends LocationChangingRectangle {
	
	
	
	
	
	
	LocationChangingRoundedRectangle(Point location, Color color, Dimension dimension) throws ImpossibleSizeException {
		super(location, color, dimension);
		//this.serialNumber = nextNumber;
		//LocationChangingNumberedOval.nextNumber++;
	}
	
	/**
     * @modifies g
     * @effects Draws this onto g.
     */
	@Override
	public void draw(Graphics g) {
		//super.draw(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(this.getColor());
		g2.drawRoundRect((int)this.getLocation().x, (int)this.getLocation().y, (int)this.getBounds().width, (int)this.getBounds().height, 50, 50);
		g2.fillRoundRect((int)this.getLocation().x, (int)this.getLocation().y, (int)this.getBounds().width, (int)this.getBounds().height, 50, 50);
		
	}


}

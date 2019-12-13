package homework1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * LocationChangingRoundedRectangle is LocationChangingRectangle with a rounded corners.
 */

public class LocationChangingRoundedRectangle extends LocationChangingRectangle {
	
	/**
	 * Abstraction Function:	The class represents a rectangle changing location with a rounded corners while the
	 * 							arc parameters are fixed and set to 50,50.		 
	 */
	
	/**
	 * Representation Invariant:	Same as LocationChangingRectangle.
	 */
	
	
	/**
     * @throws ImpossibleSizeException 
	 * @effects Initializes this with a given location, color and dimension. Each
     *          of the horizontal and vertical velocities of the new
     *          object is set to a random integral value i such that
     *          -5 <= i <= 5 and i != 0
     */
	LocationChangingRoundedRectangle(Point location, Color color, Dimension dimension) throws ImpossibleSizeException {
		super(location, color, dimension);
	}
	
	/**
     * @modifies g
     * @effects Draws this onto g.
     */
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(this.getColor());
		g2.drawRoundRect((int)this.getLocation().x, (int)this.getLocation().y, (int)this.getBounds().width, (int)this.getBounds().height, 50, 50);
		g2.fillRoundRect((int)this.getLocation().x, (int)this.getLocation().y, (int)this.getBounds().width, (int)this.getBounds().height, 50, 50);
		
	}


}

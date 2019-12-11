package homework1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/**
 * LocationChangingNumberedOval is LocationChangingOval that is numbered and its number is presented in its center.
 */
public class LocationChangingNumberedOval extends LocationChangingOval {
	
	private static int nextNumber = 1;
	private int serialNumber;
	
	/**
	 * Abstraction Function:	The serial number of the oval is given in the field serialNumber and represents the
	 * 							instance number of the oval from this class.
	 */
	
	/**
	 * Rep. Invariant:	Same as LocationChangingOval.
	 */

	/**
     * @throws ImpossibleSizeException 
	 * @effects Initializes this with a given location, color and dimension. Each
     *          of the horizontal and vertical velocities of the new
     *          object is set to a random integral value i such that
     *          -5 <= i <= 5 and i != 0
     */
	LocationChangingNumberedOval(Point location, Color color, Dimension dimension) throws ImpossibleSizeException {
		super(location, color, dimension);
		this.serialNumber = nextNumber;
		LocationChangingNumberedOval.nextNumber++;
	}

	/**
     * @modifies g
     * @effects Draws this onto g.
     */
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.BLACK);
		//Make sure number is visible.
		if (this.getColor().getBlue() < 90 && this.getColor().getGreen() < 90 && this.getColor().getRed() < 90) {
			g.setColor(Color.WHITE);
		}
		g.drawString(new Integer(serialNumber).toString(),
				(int)getLocation().getX() + (int)this.getBounds().getCenterX(), (int)getLocation().getY() + (int)this.getBounds().getCenterY());
		
		//g.drawString(String.valueOf(this.serialNumber), this.getLocation().x+this.getBounds().width/2, this.getLocation().y+this.getBounds().height/2);

	}
	
	/**
	 * @modifies this
	 * @effects resets the nextNumber field for serializing numbered oval numbers.
	 */
	public static void reset() {
		LocationChangingNumberedOval.nextNumber = 1;
	}

}
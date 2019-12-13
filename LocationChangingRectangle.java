package homework1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class LocationChangingRectangle extends LocationChangingShape implements Cloneable {
	
    private Dimension dimension;
	/**
	 * Abstraction Function:	same as LocationChangingShape with the addition of a dimension, representing the 
	 * 							size properties of the Rectangle.
	 */
	
	/**
	 * Representation Invariant: same as LocationChangingShape.
	 */
	
	/**
     * @throws ImpossibleSizeException 
	 * @effects Initializes this with a given location, color and dimension. Each
     *          of the horizontal and vertical velocities of the new
     *          object is set to a random integral value i such that
     *          -5 <= i <= 5 and i != 0
     */
	LocationChangingRectangle(Point location, Color color, Dimension dimension) throws ImpossibleSizeException {
		super(location, color);
		this.setSize(dimension);
	}
	
	/**
     * @modifies this
     * @effects Resizes this so that its bounding rectangle has the specified
     *          dimension.
     *          If this cannot be resized to the specified dimension =>
     *          this is not modified, throws ImpossibleSizeException
     *          (the exception suggests an alternative dimension that is
     *           supported by this).
     */
	@Override
	public void setSize(Dimension dimension) throws ImpossibleSizeException {
		if (dimension.getHeight() <= 0 || dimension.getWidth() <= 0) {
			throw new ImpossibleSizeException();
		}
		this.dimension = (Dimension)dimension.clone();
	}

	/**
     * @return the bounding rectangle of this.
     */
	@Override
	public Rectangle getBounds() {
		Rectangle shapeBounds = new Rectangle(this.dimension);
		shapeBounds.setLocation(getLocation().x, getLocation().y);
		return shapeBounds;
	}
	
	/**
     * @modifies g
     * @effects Draws this onto g.
     */
	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.fillRect((int)this.getLocation().getX(), (int)this.getLocation().getY(), 
				(int)this.dimension.getWidth(), (int)this.dimension.getHeight());
	}


}

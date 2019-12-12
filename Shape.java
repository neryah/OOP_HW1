package homework1;

import java.awt.*;


/**
 * A Shape is an abstraction of a shape object. A typical Shape consists of
 * a set of properties: {location, color, shape, size}.
 * Shapes are mutable and cloneable.
 */
public abstract class Shape implements Cloneable {


	private Point location;
	private Color color;


    /**
     * Abstraction Function:	This class represent a shape. shape's 'location' represent the top-left corner of
     *                          the bounding rectangle. shape's 'color' is the color.
     */

    /**
     * Representation Invariant: color!=Null. location!=Null, both location.x and location.y non negative.
     */


	private boolean checkRep(){
	    assert location.getX() >= 0;
	    assert location.getY() >=0;
	    boolean locationIsValid = location!=null && location.getX() >= 0 && location.getY() >=0;
	    return locationIsValid && color != null;
    }
	
	/**
	 * @effects Initializes this with a a given location and color.
	 */
    public Shape(Point location, Color color) {
        if(color == null){
            throw new IllegalArgumentException("color is null!");
        }
        boolean locationIsValid = location != null && location.getX() >= 0 && location.getY() >=0;
        if(!locationIsValid){
            throw new IllegalArgumentException("location isn't valid: null or" +
                    " consist of non-positive parameter!");
        }
        this.location = (Point)location.clone();
        this.color = color;
    	assert checkRep();
    }


    /**
     * @return the top left corner of the bounding rectangle of this.
     */
    public Point getLocation() {
        assert checkRep();
        Point location = new Point(this.location);
        assert checkRep();
        return location;
    }


    /**
     * @modifies this
     * @effects Moves this to the given location, i.e. this.getLocation()
     * 			returns location after call has completed.
     */
    public void setLocation(Point location) {
        assert checkRep();
    	this.location = (Point)location.clone();
        assert checkRep();
    }


    /**
     * @modifies this
     * @effects Resizes this so that its bounding rectangle has the specified
     * 			dimension.
     * 			If this cannot be resized to the specified dimension =>
     * 			this is not modified, throws ImpossibleSizeException
     * 			(the exception suggests an alternative dimension that is
     * 			 supported by this).
     */
    public abstract void setSize(Dimension dimension)
    	throws ImpossibleSizeException;

    
    /**
     * @return the bounding rectangle of this.
     */
    public abstract Rectangle getBounds();
  

    /**
     * @return true if the given point lies inside the bounding rectangle of
     * 		   this and false otherwise.
     */
    public boolean contains(Point point) {
        assert checkRep();
        return getBounds().contains(point);
    }
        

    /**
     * @return color of this.
     */
    public Color getColor() {
        assert checkRep();
    	return color;
    }


    /**
     * @modifies this
     * @effects Sets color of this.
     */
    public void setColor(Color color) {
        assert checkRep();
    	this.color = color;
    }


    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    public abstract void draw(Graphics g);


    /**
     * @effects Creates and returns a copy of this.
     */
    public Object clone() {
        assert checkRep();
        Shape myShape = null;
        try {
            myShape = (Shape)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        assert checkRep();
        return myShape;
    }
}
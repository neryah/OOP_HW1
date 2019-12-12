package homework1;

import java.awt.*;
import java.util.Random;


/**
 * A LocationChaningShape is a Shape that can change its location using its step()
 * method. A LocationChaningShape has a velocity property that determines the speed
 * of location changing.
 * Thus, a typical LocationChaningShape consists of the following set of
 * properties: {location, color, shape, size, velocity}
 */
public abstract class LocationChangingShape extends Shape implements Animatable {
    private final int MAX_VELOCITY_ABS = 5;

    private int Vx, Vy;
    private Rectangle shapeBounds;

    /**
     * Abstraction Function:	This class represent a movable shape. LocationChangingShape's 'location' represent the
     *                          top-left corner of the bounding rectangle. LocationChangingShape's 'color' is the color.
     *                          'Vx' and 'Vy', representing a vector with 2D velocity.
     *
     */

    /**
     * Representation Invariant: color!=Null. location!=Null, both location.x and location.y non negative.
     *                           Each of the horizontal and vertical velocities Vx,Vy is an integral value i such that.
     * 	                        -5 <= i <= 5 and i != 0
     */

    private boolean checkRep() {
        boolean VxGood = Vx != 0 && Vx >= -MAX_VELOCITY_ABS && Vx <= MAX_VELOCITY_ABS;
        boolean VyGood = Vy != 0 && Vy >= -MAX_VELOCITY_ABS && Vy <= MAX_VELOCITY_ABS;
        return VxGood && VyGood;
    }


    /**
	 * @effects Initializes this with a a given location and color. Each
	 *          of the horizontal and vertical velocities of the new
	 *          object is set to a random integral value i such that
	 *          -5 <= i <= 5 and i != 0
	 */
	LocationChangingShape(Point location, Color color) {
        super(location, color);
        Random r = new Random();
        Vx = r.nextInt(2*MAX_VELOCITY_ABS) - MAX_VELOCITY_ABS;//random integer V such that -5 <= V < 5
        if(Vx == 0){
            Vx = MAX_VELOCITY_ABS;
        }
        Vy = r.nextInt(2*MAX_VELOCITY_ABS) - MAX_VELOCITY_ABS;//random integer V such that -5 <= V < 5
        if(Vy == 0){
            Vy = MAX_VELOCITY_ABS;
        }
        assert checkRep();
    }


    /**
     * @return the horizontal velocity of this.
     */
    public int getVelocityX() {
        assert checkRep();
        return Vx;
    }


    /**
     * @return the vertical velocity of this.
     */
    public int getVelocityY() {
        assert checkRep();
        return Vy;
    }


    /**
     * @modifies this
     * @effects Sets the horizontal velocity of this to velocityX and the
     * 			vertical velocity of this to velocityY.
     */
    public void setVelocity(int velocityX, int velocityY) {
        assert checkRep();
        Vx = velocityX;
        Vy = velocityY;
        assert checkRep();
    }


    /**
     * @modifies this
     * @effects Let p = location
     * 				v = (vx, vy) = velocity
     * 				r = the bounding rectangle of this
     *         	If (part of r is outside bound) or (r is within bound but
     *          adding v to p would bring part of r outside bound) {
     * 				If adding v to p would move r horizontally farther away
     * 				from the center of bound,
     * 					vx = -vx
     * 				If adding v to p would move r vertically farther away
     * 				from the center of bound,
     * 					vy = -vy
     *          }
     * 			p = p + v
     */
    public void step(Rectangle bound) {
        assert checkRep();
        Rectangle shapeBounds = getBounds();
        boolean nextXIsBad = shapeBounds.getMaxX() + Vx > bound.getMaxX() ||
                getLocation().getX() +  Vx < bound.getMinX();
        boolean nextYIsBad = shapeBounds.getMaxY() + Vy > bound.getMaxY() ||
                getLocation().getY() +  Vx < bound.getMinY();
        if (!bound.contains(shapeBounds) || nextXIsBad || nextYIsBad){
            if((shapeBounds.getCenterX() > bound.getCenterX() && Vx > 0) ||
                      (shapeBounds.getCenterX() < bound.getCenterX() && Vx < 0)){
                Vx = nextXIsBad? -Vx : Vx;
            }
            if((shapeBounds.getCenterY() > bound.getCenterY() && Vy > 0) ||
                    (shapeBounds.getCenterY() < bound.getCenterY() && Vy < 0)){
                Vy = nextYIsBad? -Vy : Vy;
            }
        }
        setLocation(new Point(getLocation().x + getVelocityX(),
                getLocation().y + getVelocityY()));
        assert checkRep();
    }
}

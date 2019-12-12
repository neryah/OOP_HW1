package homework1;

import java.awt.Dimension;
//TODO

/**
 * Getting this exception means u use setSize() of <? extends Shape> with wrong size.
 * U can get valid size through getDimension()
 */
public class ImpossibleSizeException extends RuntimeException {
    private static final Dimension def = new Dimension(40,60);
    /**
     * @effects creates new Dimension instance with valid parameters for setSize()
     */
    public Dimension getDimension(){
        return new Dimension(def);
    }
    ImpossibleSizeException(){
        super();
    }
}

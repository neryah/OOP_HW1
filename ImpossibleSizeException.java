package homework1;

import java.awt.Dimension;
//TODO

/**
 * ImpossibleSizeException is a class inheriting from java RuntimeException class.
 * ImpossibleSizeException class has also a class property: {def- the
 * size which is given in case ImpossibleSizeException was thrown}. 
 */
public class ImpossibleSizeException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Dimension def = new Dimension(40,60);
    /**
     * @effects creates new Dimension instance with valid parameters for setSize()
     * @return valid Dimension
     */
    public Dimension getDimension(){
        return new Dimension(def);
    }
    ImpossibleSizeException(){
        super();
    }
}

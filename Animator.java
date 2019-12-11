package homework1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * Main application class for exercise #1.
 * This application allows the user to add shapes to a graphical window and
 * to animate them.
 */
@SuppressWarnings("serial")
public class Animator extends JFrame implements ActionListener {

	// preferred frame width and height.
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 400;

	private static final int MAX_HEIGHT = ((3 * WINDOW_HEIGHT) / 10);
    private static final int MAX_WIDTH = ((3* WINDOW_WIDTH) / 10);
    private static final int MIN_HEIGHT = (WINDOW_HEIGHT / 10);
    private static final int MIN_WIDTH = (WINDOW_WIDTH / 10);

	// graphical components
	private JMenuBar menuBar;
	private JMenu fileMenu, insertMenu, helpMenu;
	private JMenuItem newItem, exitItem,
						rectangleItem, roundedRectangleItem, ovalItem,
						numberedOvalItem, sectorItem, aboutItem;
	private JCheckBoxMenuItem animationCheckItem;
	private JPanel mainPanel;

	private Graphics2D graphics;
	private BufferedImage image;
	private boolean uninitialized;
	
	// TODO: Add and initialize a container of shapes called shapes.
	private Collection<Shape> shapes = new HashSet<>();
	private Random rand = new Random();
	

	/**
	 * @modifies this
	 * @effects Initializes the GUI and enables a timer that steps animation
	 * 			of all shapes in this 25 times per second while animation
	 * 			checkbox is selected.
	 */
	public Animator() {
		super("Animator");
		this.uninitialized = true;

		// create main panel and menubar
		mainPanel = (JPanel)createMainPanel();
		getContentPane().add(mainPanel);
		
		menuBar = (JMenuBar)createMenuBar();
        setJMenuBar(menuBar);

        // enable animation timer (ticks 25 times per second)
        Timer timer = new Timer(40, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (animationCheckItem.isSelected()) {
                	// TODO: Add code for making one animation step for all
                	// 		 shapes in this
                	Iterator<Shape> it = shapes.iterator();
                    while(it.hasNext()){
                    	Shape current = it.next();
                    	if(current instanceof Animatable){
                    		((Animatable)current).step(mainPanel.getBounds());
                    	}
                    }
                
            		repaint();	// make sure that the shapes are redrawn
                }
            }
        });
        timer.start();
	}


	/**
	 * @return main GUI panel.
	 */
	private JComponent createMainPanel() {
    	JPanel mainPanel = new JPanel();
    	mainPanel.setPreferredSize(
    			new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
    	mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
    	mainPanel.setBackground(Color.WHITE);

    	return mainPanel;
	}


	/**
	 * @return main GUI menubar.
	 */
	private JMenuBar createMenuBar() {
    	JMenuBar menuBar = new JMenuBar();

    	fileMenu = new JMenu("File");
    	newItem = new JMenuItem("New");
    	newItem.addActionListener(this);
    	fileMenu.add(newItem);
    	animationCheckItem = new JCheckBoxMenuItem("Animation");
    	fileMenu.add(animationCheckItem);
    	exitItem = new JMenuItem("Exit");
    	exitItem.addActionListener(this);
    	fileMenu.add(exitItem);
    	menuBar.add(fileMenu);

    	insertMenu = new JMenu("Insert");
    	rectangleItem = new JMenuItem("Rectangle");
    	rectangleItem.addActionListener(this);
    	insertMenu.add(rectangleItem);
    	roundedRectangleItem = new JMenuItem("Rounded Rectangle");
    	roundedRectangleItem.addActionListener(this);
    	insertMenu.add(roundedRectangleItem);
    	ovalItem = new JMenuItem("Oval");
    	ovalItem.addActionListener(this);
    	insertMenu.add(ovalItem);
    	numberedOvalItem = new JMenuItem("Numbered Oval");
    	numberedOvalItem.addActionListener(this);
    	insertMenu.add(numberedOvalItem);
    	sectorItem = new JMenuItem("Sector");
    	sectorItem.addActionListener(this);
    	insertMenu.add(sectorItem);
    	menuBar.add(insertMenu);

    	helpMenu = new JMenu("Help");
    	aboutItem = new JMenuItem("About");
    	aboutItem.addActionListener(this);
    	helpMenu.add(aboutItem);
    	menuBar.add(helpMenu);

    	return menuBar;
	}


	/**
	 * @modifies g
	 * @effects Paint this including all its shapes to g. This method is
	 * 			invoked by Swing to draw components. It should not be invoked
	 * 			directly, but the repaint method should be used instead in
	 * 			order to schedule the component for redrawing.
	 */
	public void paint(Graphics g) {
		if ( uninitialized ) {
			image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			graphics = image.createGraphics();
			uninitialized = false;
		}
		super.paint(graphics);
		//TODO: Add code for drawing all shapes in this
		for(Shape s : shapes){
			s.draw(graphics);
		}
		g.drawImage(image, 0, 0, null);
	}


	/**
	 * @modifies this
	 * @effects Invoked when the user selects an action from the menubar
	 * 			and performs the appropriate operation.
	 */
	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem)(e.getSource());

		// File->New : clear all shapes
		if (source.equals(newItem)) {
			shapes.clear();
			repaint();
			
			//TODO  Add code for number of LocationChangingNumerOval = 0
			LocationChangingNumberedOval.reset();
		}

		// File->Exit: close application
		else if (source.equals(exitItem)) {
        	dispose();
        }

		// Insert a shape
		else if ((source.equals(rectangleItem)) ||
      		 	 (source.equals(roundedRectangleItem)) ||
      		 	 (source.equals(ovalItem)) ||
      		 	 (source.equals(numberedOvalItem)) ||
      		 	 (source.equals(sectorItem))) {

			// TODO: Add code for creating the appropriate shape such that:
			// 		 it is completely inside the window's bounds &&
			//		 its location and size are randomly selected &&
			//		 1/10*WINDOW_WIDTH <= shape.width < 3/10*WINDOW_WIDTH &&
			//		 1/10*WINDOW_HEIGHT <= shape.height < 3/10*WINDOW_HEIGHT
			
			Color color = new Color(rand.nextInt());
        	int randWidth = rand.nextInt(MAX_WIDTH-MIN_WIDTH+1) + MIN_WIDTH;
        	int randHeight = rand.nextInt(MAX_HEIGHT-MIN_HEIGHT+1) + MAX_HEIGHT;
        	int randX = rand.nextInt(WINDOW_WIDTH-randWidth+1);
        	int randY = rand.nextInt(WINDOW_HEIGHT-randHeight+1);
        	
        	Point location = new Point(randX, randY);
        	Dimension dimension = new Dimension(randWidth, randHeight);
        	
        	Shape shape;
        	if(source.equals(rectangleItem)){
        		try{
        			shape = new LocationChangingRectangle(location,color,dimension);
        		}
        		catch (ImpossibleSizeException e1){
        			shape = new LocationChangingRectangle(location,color,e1.getDimension());
        		}
        	}
        	else if (source.equals(roundedRectangleItem)){
        		try{
        			shape = new LocationChangingRoundedRectangle(location,color,dimension);
        		}
        		catch (ImpossibleSizeException e2){
        			shape = new LocationChangingRoundedRectangle(location,color,e2.getDimension());
        		}
        	}
        	else if (source.equals(ovalItem)){
        		try{
        			shape = new LocationChangingOval(location,color,dimension);
        		}
        		catch (ImpossibleSizeException e3){
        			shape = new LocationChangingOval(location,color,e3.getDimension());
        		}
        	}
        	else if (source.equals(numberedOvalItem)){
        		try{
        			shape = new LocationChangingNumberedOval(location,color,dimension);
        		}
        		catch (ImpossibleSizeException e4){
        			shape = new LocationChangingNumberedOval(location,color,e4.getDimension());
        		}
        	} 
        	else { // sectorItem
        		try{
        			shape = new AngleChangingSector(location,color,dimension,
        					rand.nextInt(360),rand.nextInt(360));
        		}
        		catch (ImpossibleSizeException e5){
        			shape = new AngleChangingSector(location,color,e5.getDimension(),
        					rand.nextInt(360),rand.nextInt(360));
        		}
        	}
        	shapes.add(shape);
		
			////
			repaint();
		}

		// Help->About : show about message dialog
		else if (source.equals(aboutItem)){
			JOptionPane.showMessageDialog(
					this,
					"Animator - 1st" +
					" homework assignment",
					"About",
					JOptionPane.INFORMATION_MESSAGE);
		}
    }


	/**
	 * @effects Animator application.
	 */
	public static void main(String[] args) {
        Animator application = new Animator();

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setResizable(false);
        application.pack();
        application.setVisible(true);
	}
}

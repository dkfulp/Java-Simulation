/***************************************************
 * Amoeba.java - This object is the organism of an
 * amoeba.  This is the child of the organism class 
 * and it specifies the abstract methods of organism
 * 
 *
 * @author: Dakota Fulp
 * Lab07 - Abstract and Inheritance Assignment
 * Course: CSCI 150
 * Email: dkfulp@g.coastal.edu
 * Date: 3/23/2016
 * @version: 1.0
 ***************************************************/
public class Amoeba extends Organism
{
	//ATTRIBUTES --------------------------------//
	/**
	 * This is the symbol that will represent the 
	 * amoeba in the simulation
	 */
	public static final char AMOE_CHAR = '@'; 
	
	/**
	 * This is the constant that we will use to 
	 * determine the steps till spawn
	 */
	public static final int STEPS_TIL_SPAWN = 4;

	
	//CONSTRUCTORS ------------------------------//
	/**
	 * This is the default constructor and does
	 * everything the same as the organism constructor
	 * and it will set the Steps till Spawn to our
	 * constant
	 */
	public Amoeba() 
	{
		super();
		setStepsTilSpawn(STEPS_TIL_SPAWN);
	}

	/**
	 * This is the overloaded constructor and allows
	 * the user to enter the position of the cell and 
	 * it will set our character as well.  Lastly, we 
	 * will set our steps till spawn here as well.
	 * @param xPos is the X position of the organism
	 * @param yPos is the Y position of the organism
	 */
	public Amoeba(int xPos, int yPos)
	{
		super(xPos, yPos, AMOE_CHAR);
		setStepsTilSpawn(STEPS_TIL_SPAWN);
	}
	
	
	//REDEFINED ABSTRACT METHODS ----------------//
	/**
	 * This is the overridden move method and this
	 * will set hasMoved to true, increment the
	 * number of steps, and return a random direction
	 * to the user.
	 */
	@Override
	public int move() 
	{
		setHasMoved(true);
		setNumOfSteps(getNumOfSteps() + 1);
		int direction = (int)(Math.random()*4);
		
		return direction;
	}

	/**
	 * This is the overridden method for the
	 * spawnChild method and it is given an X 
	 * and Y and returns a child.
	 * @param X is the X coordinate
	 * @param Y is the Y coordinate
	 * @return is the child that is made
	 */
	@Override
	public Organism spawnChild(int X, int Y)
	{
		Organism amoeba = new Amoeba(X,Y);
		return amoeba;
	}
	
	/**
	 * This is the overridden method for the draw
	 * method and it prints out the Amoeba character
	 */
	@Override
	public void draw() 
	{
		System.out.print(AMOE_CHAR);
	}

	/**
	 * This is the overridden method that lets the user
	 * set how many steps an organism needs to spawn again
	 * This allows the user to differ one organism from the
	 * constant above.
	 * @param numOfSteps is a non negative number of steps
	 */
	@Override
	public void setStepsTilSpawn(int numOfSteps) 
	{
		if(numOfSteps >= 0)
		{
			this.stepsTilSpawn = numOfSteps;
		}
		else
		{
			System.out.println("INVALID NUMBER OF STEPS! Please try again");
		}
	}
	
	
	
}

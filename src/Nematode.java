/***************************************************
 * Nematode.java - This object is the organism of an
 * nematode.  This is the child of the organism class 
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
public class Nematode extends Organism
{
	//ATTRIBUTES ---------------------------//
	/**
	 * This is the number of steps till the nematode
	 * starves
	 */
	private int stepsTilStarve;
	
	/**
	 * This is the counter that increments with
	 * each time step and if it reaches the 
	 * stepsTilStarve then the organism will die
	 */
	private int starveCounter;
	
	/**
	 * This is the character that we will use to 
	 * display the nematode on the grid
	 */
	public static final char NEMA_CHAR = 'N';
	
	/**
	 * This is the constant that we will use to 
	 * set the initial steps til death for each
	 * organism
	 */
	public static final int STEPS_TIL_DEATH = 4;
	
	
	//CONSTRUCTORS -------------------------//
	/**
	 * This is the default constructor that does
	 * the same thing as the organism but sets the
	 * steps to starve, starve counter, and steps 
	 * till spawn as well
	 */
	public Nematode() 
	{
		super();
		setStepsToStarve(STEPS_TIL_DEATH);
		setStarveCounter(0);
		setStepsTilSpawn(10);
	}
	
	/**
	 * This is the overloaded constructor that allows
	 * the user to set the cells initial position as well
	 * @param x is the x position of the cell
	 * @param y is the y position of the cell
	 */
	public Nematode(int x, int y)
	{
		super(x, y, NEMA_CHAR);
		setStepsToStarve(STEPS_TIL_DEATH);
		setStarveCounter(0);
		setStepsTilSpawn(10);
	}
	
	
	//BEHAVIORAL METHODS ------------------//
	/**
	 * This is the method that will reset the starve
	 * counter and should only be called once the 
	 * nematode has eaten.
	 */
	public void consume()
	{
		setStarveCounter(0);
	}
	
	/**
	 * This checks to see whether the nematode has 
	 * died from hunger yet
	 * @return is true for starved or false for 
	 * healthy
	 */
	public boolean starve()
	{
		if(starveCounter >= stepsTilStarve)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * This is the method that lets the user return 
	 * the number of steps before the nematode dies
	 * @return is the number of steps till starvation
	 */
	public int getStepsToStarve()
	{
		return stepsTilStarve;
	}
	
	/**
	 * This is the method that lets the user set the 
	 * number of steps before starvation occurs and 
	 * allows the user to manipulate the situation more
	 * @param steps is the number of steps to change it
	 * to that is not negative.
	 */
	public void setStepsToStarve(int steps)
	{
		if(steps >= 0)
		{
			this.stepsTilStarve = steps;
		}
		else
		{
			System.out.println("INVALID NUMBER OF STEPS! Please try again");
		}
	}
	
	/**
	 * This allows the user to see how close the nematode
	 * is to death
	 * @return is the starvation counter
	 */
	public int getStarveCounter()
	{
		return starveCounter;
	}
	
	/**
	 * This allows the user to manipulate the starvation
	 * counter of a certain nematode
	 * @param num is the number they want to set it to
	 */
	public void setStarveCounter(int num)
	{
		starveCounter = num;
	}
	
	
	//REDEFINED ABSTRACT METHODS ----------//
	/**
	 * This is the overridden move method and sets 
	 * hasMoved to true, increments the number of
	 * steps and starve counter, and returns a 
	 * direction for the cell to move in.
	 * @return direction is the movement direction
	 * between 0 and 3.
	 */
	@Override
	public int move() 
	{
		setHasMoved(true);
		setNumOfSteps(getNumOfSteps() +1);
		setStarveCounter(getStarveCounter() + 1);
		int direction = (int)(Math.random()*4);
		
		return direction;
	}

	/**
	 * This is the overridden spawnChild method
	 * and returns a nematode child to the user 
	 * while taking in an X and Y location
	 * @param X is the X location of child
	 * @param Y is the Y location of child
	 * @return is the nematode child that is 
	 * spawned
	 */
	@Override
	public Organism spawnChild(int X, int Y) 
	{
		Organism nema = new Nematode(X,Y);
		return nema;
	}
	
	/**
	 * This is the overridden draw method and
	 * this will draw the symbol for the 
	 * nematodes
	 */
	@Override
	public void draw()
	{
		System.out.print(NEMA_CHAR);
	}

	/**
	 * This is the overridden method that allows
	 * the user to set the number of steps till
	 * a certain organism can spawn again
	 * @param numOfSteps is a non negative number
	 * of steps till the user can spawn again
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

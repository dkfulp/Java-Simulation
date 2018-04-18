/***************************************************
 * Organism.java - This object is the abstract object
 * of an organism.  It is the parent of more specific
 * organisms such as a Amoeba or a Nematode.
 * 
 *
 * @author: Dakota Fulp
 * Lab07 - Abstract and Inheritance Assignment
 * Course: CSCI 150
 * Email: dkfulp@g.coastal.edu
 * Date: 3/23/2016
 * @version: 1.0
 ***************************************************/
public abstract class Organism 
{
	//ATTRBUTES -----------------------------------//
	/**
	 * This is the x position of the organism in the environment
	 */
	private int XcellPosition;
	
	/**
	 * This is the y position of the organism in the environment
	 */
	private int YcellPosition;
	
	/**
	 * This is the number of steps left till the organism can spawn again
	 */
	protected int stepsTilSpawn;
	
	/**
	 * This is the health of the organism and tells whether it's alive or dead
	 */
	private boolean alive;
	
	/**
	 * This is the symbol that represents the organism in its environment
	 */
	private char symbol;
	
	/**
	 * This is the number of steps the organism has taken and is reset to zero
	 * every time an organism spawns
	 */
	private int numOfSteps;
	
	/**
	 * This is the variable that determines whether the cell has moved this turn
	 * yet or not
	 */
	protected boolean hasMoved;
	
	
	//CONSTRUCTORS --------------------------------//
	/**
	 * This is the default constructor for the organism.
	 * It sets the organisms position to (0,0), sets 
	 * alive to true, sets steps to zero, sets has moved to
	 * false, and gives it a symbol of "?".
	 */
	public Organism() 
	{
		setNumOfSteps(0);
		setCellPosition(0,0);
		setAlive(true);
		setSymbol('?');
		setHasMoved(false);
	}
	
	/**
	 * This is the overloaded constructor for the organism.
	 * Alive is set to true by default, has moved is set to 
	 * false by default, and zero steps taken is set to 
	 * default as well.
	 * @param xPos is the X position of the organism
	 * @param yPos is the Y position of the organism
	 * @param aChar is the Char to represent the organism
	 */
	public Organism(int xPos, int yPos, char aChar)
	{
		setNumOfSteps(0);
		setCellPosition(xPos, yPos);
		setAlive(true);
		setSymbol(aChar);
		setHasMoved(false);
	}

	
	//BEHAVIORAL METHODS ---------------------------//
	/**
	 * This is the function to record information from
	 * the organisms movement
	 */
	public abstract int move();
	
	/**
	 * This is the abstract method that allows the birth
	 * of a child
	 * @return is a child.
	 */
	public abstract Organism spawnChild(int X, int Y);
	
	/**
	 * This is the function that draws the organism
	 */
	public abstract void draw();
	
	/**                 
	 * This sets the number of steps till the organism
	 * can spawn again.
	 */
	public abstract void setStepsTilSpawn(int numOfSteps); 
	
	//GETTERS AND SETTERS --------------------------//
	/**
	 * This returns the organisms X Position to the user
	 * @return is the X position for the user
	 */
	public int getXCellPosition() 
	{
		return XcellPosition;
	}

	/**
	 * This returns the organisms Y Position to the user
	 * @return is the Y position for the user
	 */
	public int getYCellPosition() 
	{
		return YcellPosition;
	}
	
	/**
	 * This sets the organisms current position
	 * @param X is the X coordinate of the organism.
	 * @param Y is the Y coordinate of the organism.
	 */
	public void setCellPosition(int X, int Y) 
	{
		XcellPosition = X;
		YcellPosition = Y;
	}

	/**
	 * This returns the number of steps needed to spawn
	 * this certain organism
	 * @return is the number of steps needed to spawn
	 */
	public int getStepsTilSpawn() 
	{
		return stepsTilSpawn;
	}

	/**
	 * This is the true/false function that lets the
	 * user see if the organism is alive
	 * @return is a boolean value telling whether the 
	 * organism is alive or not
	 */
	public boolean isAlive() 
	{
		return alive;
	}

	/**
	 * This allows the user to set whether the organism
	 * is alive or not
	 * @param alive is a boolean value: true = alive, 
	 * false = dead
	 */
	public void setAlive(boolean alive) 
	{
		this.alive = alive;
	}

	/**
	 * This is the function that returns the symbol that
	 * represents this organism to the user
	 * @return is the one character symbol that 
	 * represents the organism
	 */
	public char getSymbol() 
	{
		return symbol;
	}

	/**
	 * This is the function that allows the user to set 
	 * the symbol that they want to represent a certain
	 * creature
	 * @param symbol is a single character to represent 
	 * the organism
	 */
	public void setSymbol(char symbol) 
	{
		this.symbol = symbol;
	}
	
	/**
	 * This allows the user to access the number of steps
	 * that the organism has taken
	 * @return is the number of steps
	 */
	public int getNumOfSteps()
	{
		return numOfSteps;
	}
	
	/**
	 * This allows the user to set the number of steps the
	 * organism has taken
	 * @param num is a valid number of steps
	 */
	public void setNumOfSteps(int num)
	{
		numOfSteps = num;
	}
	
	/**
	 * This allows the user to see if the cell has moved yet or not
	 * @return is a boolean to determine if the cell has already moved
	 */
	public boolean getHasMoved()
	{
		return hasMoved;
	}
	
	/**
	 * This allows the user to set whether the cell has moved yet or not
	 * @param decision is the boolean the user sends to it
	 */
	public void setHasMoved(boolean decision)
	{
		hasMoved = decision;
	}
	
	
	
	

}

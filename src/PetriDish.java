/***************************************************
 * PetriDish.java - This object is the environment 
 * where the nematodes, amoebas, and other organisms
 * can live.  This is also where the simulation runs
 * 
 *
 * @author: Dakota Fulp
 * Lab07 - Abstract and Inheritance Assignment
 * Course: CSCI 150
 * Email: dkfulp@g.coastal.edu
 * Date: 3/23/2016
 * @version: 1.0
 ***************************************************/
public class PetriDish 
{
	//ATTRIBUTES ---------------------------//
	/**
	 * This is the number of amoebas to start with
	 * in the simulation
	 */
	public final static int STARTING_AMOEBAS = 100;
	
	/**
	 * This is the number of nematodes to start with
	 * in the simulation
	 */
	public final static int STARTING_NEMATODES = 5;
	
	/**
	 * This is the array of organisms that will be
	 * used with this Petri dish
	 */
	private Organism[][] dish;

	
	//CONSTRUCTORS -------------------------//
	/**
	 * This is the default constructor for the
	 * petri dish and it sets dish to be a default
	 * 20 x 20 array of organisms
	 */
	public PetriDish() 
	{
		dish = new Organism[20][20];
	}
	
	/**
	 * This overloaded constructor allows the user
	 * to set the size of the array if they would like
	 * @param Aray is the array you would like to
	 * use
	 */
	public PetriDish(Organism[][] Array)
	{
		dish = Array;
	}
	
//----------------------------------------------------------------------//
							//MAIN IS BELOW//
//----------------------------------------------------------------------//
	
	public static void main(String[]args)
	{
		//1. Set up our dish and our starting conditions
		PetriDish PetriDish = new PetriDish();
		int numOfAmoeba = STARTING_AMOEBAS;
		int numOfNematode = STARTING_NEMATODES;
		
		//2. Fill it with all the organisms
		fillAllOrganisms(PetriDish);
		System.out.println();
		
		//3. Draw the initial set up of organisms
		drawAllOrganisms(PetriDish);
		
		//4. LOOP until only one is left
		while(numOfAmoeba != 0 && numOfNematode != 0)
		{
			//4a. Move all the organism
			moveAllOrganisms(PetriDish);
			System.out.println();
			//4b. Check to see if any can spawn
			checkSpawning(PetriDish);
			//4c. Redraw the dish
			drawAllOrganisms(PetriDish);
			
			//4d. Set counts to zero so we can recount the number of each left
			int amoeCount = 0;
			int nemaCount = 0;
			//4e. LOOP to count all left
			for(int k = 0; k < PetriDish.getArrayLength(); k++)
			{
				for(int i = 0; i < PetriDish.getArrayLength(); i++)
				{
					//4e1. If amoeba increment amoecount
					if(PetriDish.dish[k][i] instanceof Amoeba)
					{
						amoeCount++;
					}
					//4e2. If nematode increment nemacount
					if(PetriDish.dish[k][i] instanceof Nematode)
					{
						nemaCount++;
					}
				}
			}
			//4f. Slow down animation so it can be seen
			try{
				Thread.sleep(2000);
			}
			catch(InterruptedException ie)
			{
				System.out.println("Interrupted Exception should not have occured.");
			}
			System.out.println("Nematode Count: " + nemaCount);
			System.out.println("Amoeba Count: " + amoeCount);
			
			//4g. Set new counts 
			numOfNematode = nemaCount;
			numOfAmoeba = amoeCount;
		}
	}
	
	
//----------------------------------------------------------------------//
						//OTHER METHODS ARE BELOW//
//----------------------------------------------------------------------//
	
	/**
	 * This is the method that draws all the organisms onto the petridish
	 * @param PetriDish is the array of organisms that we are using
	 */
	public static void drawAllOrganisms(PetriDish PetriDish)
	{
		//1. LOOP through entire dish
		for(int k = 0; k < PetriDish.getArrayLength(); k++)
		{
			for(int i = 0; i <PetriDish.getArrayLength(); i++)
			{
				//1a. If nothing is there print a " - "
				if(PetriDish.dish[k][i] == null)
				{
					System.out.print(" - ");
				}
				//1b. If an amoeba is there print its character
				else if(PetriDish.dish[k][i] instanceof Amoeba)
				{
					System.out.print(" ");
					PetriDish.dish[k][i].draw();
					System.out.print(" ");
				}
				//1c. If a nematode is there print its character
				else if(PetriDish.dish[k][i] instanceof Nematode)
				{
					System.out.print(" ");
					PetriDish.dish[k][i].draw();
					System.out.print(" ");
				}
				//1d. If its something else print a question mark
				else
				{
					System.out.print(" ? ");
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * This is the method that we use in the beginning to fill the initial
	 * dish
	 * @param PetriDish is the array of organisms that we are using
	 */
	public static void fillAllOrganisms(PetriDish PetriDish)
	{
		//1. Initialize the starting amounts
		int amoeCount = STARTING_AMOEBAS;
		int nemaCount = STARTING_NEMATODES;
		
		//2. LOOP
		while(amoeCount != 0)
		{
			//2a. Find a random spot in the array
			int xPosition = (int)(Math.random()*20);
			int yPosition = (int)(Math.random()*20);
			
			//2b. If spot is null then fill it and reduce the number of amoeba
			if(PetriDish.dish[xPosition][yPosition] == null)
			{
				PetriDish.dish[xPosition][yPosition] = new Amoeba(xPosition,yPosition);
				amoeCount --;
			}
		}
		//3. LOOP (again)
		while(nemaCount != 0)
		{
			//3a. Find a random spot in the array
			int xPosition = (int)(Math.random()*20);
			int yPosition = (int)(Math.random()*20);
			
			//3b. If spot is null then fill it and reduce number of nematodes
			if(PetriDish.dish[xPosition][yPosition] == null)
			{
				PetriDish.dish[xPosition][yPosition] = new Nematode(xPosition,yPosition);
				nemaCount --;
			}
		}
	}
	
	/**
	 * This is the method that we use to move all the organisms around 
	 * the array.  
	 * @param PetriDish is the array of organisms that we are using
	 */
	public static void moveAllOrganisms(PetriDish PetriDish)
	{
		//1. Call the nematodeMove sub move function
		nematodeMove(PetriDish);
		//2. Call the amoebaMove sub move function
		amoebaMove(PetriDish);
		
		//3. LOOP and reset everyones number of moves
		for(int k = 0; k < PetriDish.getArrayLength(); k++)
		{
			for(int i = 0; i < PetriDish.getArrayLength(); i++)
			{
				if(PetriDish.dish[k][i] != null)
				{
					PetriDish.dish[k][i].setHasMoved(false);
				}
			}
		}
	}
	
	/**
	 * This is the sub move method that moves the amoebas only
	 * @param PetriDish is the array of organisms that we are using
	 */
	public static void amoebaMove(PetriDish PetriDish)
	{
		//1. LOOP
		for(int k = 0; k < PetriDish.getArrayLength(); k++)
		{
			for(int i = 0; i < PetriDish.getArrayLength(); i++)
			{
				//1a. Check to see if this spot is an amoeba
				if(PetriDish.dish[k][i] instanceof Amoeba)
				{
					//1b. Check to see if the amoeba has moved yet or not
					if(PetriDish.dish[k][i].getHasMoved() == false)
					{
						//1c. Enact the move option and get back a random number
						int direction = ((Amoeba) PetriDish.dish[k][i]).move();
						
						//1d. Move in that direction
						//1d1. moves the organism up
						if(direction == 0)
						{
							if(k - 1 >= 0)
							{
								if(PetriDish.dish[k-1][i] == null)
								{
									PetriDish.dish[k-1][i] = PetriDish.dish[k][i];
									PetriDish.dish[k][i] = null;
								}
							}	
						}
						//1d2. moves the organism right
						else if(direction == 1)
						{
							if(i + 1 <= 19)
							{
								if(PetriDish.dish[k][i+1] == null)
								{
									PetriDish.dish[k][i+1] = PetriDish.dish[k][i];
									PetriDish.dish[k][i] = null;
								}
							}
						}
						//1d3. moves the organism down
						else if(direction == 2)
						{
							if((k + 1) <= 19)
							{
								if(PetriDish.dish[k+1][i] == null)
								{
									PetriDish.dish[k+1][i] = PetriDish.dish[k][i];
									PetriDish.dish[k][i] = null;
								}
							}
						}
						//1d4. moves the organism left
						else if(direction == 3)
						{
							if(i - 1 >= 0)
							{
								if(PetriDish.dish[k][i-1] == null)
								{
									PetriDish.dish[k][i-1] = PetriDish.dish[k][i];
									PetriDish.dish[k][i] = null;
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * This is the sub move method that moves the nematodes only
	 * and allows the nematodes to find food and eat as well
	 * @param PetriDish is the array of organisms that we are using
	 */
	public static void nematodeMove(PetriDish PetriDish)
	{
		//1. LOOP
		for(int k = 0; k < PetriDish.getArrayLength(); k++)
		{
			for(int i = 0; i < PetriDish.getArrayLength(); i++)
			{
				//1a. Check to see if the spot is a nematode
				if(PetriDish.dish[k][i] instanceof Nematode)
				{
					//1b. Check to see if the nematode has moved yet
					if(PetriDish.dish[k][i].getHasMoved() == false)
					{
						//1c. Check to see if the nematode has died yet
						if(((Nematode) PetriDish.dish[k][i]).starve() == false)
						{
							//1c1. Enact the move command and get a direction for later
							int direction = ((Nematode) PetriDish.dish[k][i]).move();
							
							//1c2. Check to see if there is a amoeba above
							if((k - 1) >= 0 && PetriDish.dish[k - 1][i] instanceof Amoeba)
							{
								((Nematode) PetriDish.dish[k][i]).consume();
								PetriDish.dish[k - 1][i] = null;
								PetriDish.dish[k - 1][i] = PetriDish.dish[k][i];
								PetriDish.dish[k][i] = null;
								
							}
							//1c3. Check to see if there is a amoeba to the right
							else if((i + 1) <= 19 && PetriDish.dish[k][i + 1] instanceof Amoeba)
							{
								((Nematode) PetriDish.dish[k][i]).consume();
								PetriDish.dish[k][i + 1] = null;
								PetriDish.dish[k][i + 1] = PetriDish.dish[k][i];
								PetriDish.dish[k][i] = null;
							}
							//1c4. Check to see if there is a amoeba below
							else if((k + 1) <= 19 && PetriDish.dish[k + 1][i] instanceof Amoeba)
							{
								((Nematode) PetriDish.dish[k][i]).consume();
								PetriDish.dish[k + 1][i] = null;
								PetriDish.dish[k + 1][i] = PetriDish.dish[k][i];
								PetriDish.dish[k][i] = null;
							}
							//1c5. Check to see if there is a amoeba to the left
							else if((i - 1) >= 0 && PetriDish.dish[k][i - 1] instanceof Amoeba)
							{
								((Nematode) PetriDish.dish[k][i]).consume();
								PetriDish.dish[k][i - 1] = null;
								PetriDish.dish[k][i - 1] = PetriDish.dish[k][i];
								PetriDish.dish[k][i] = null;
							}
							//1c6. Move in random direction if no amoebas are near
							else
							{
								//1c6a. Move the organism in direction of direction
								//1c6a1. moves the organism up
								if(direction == 0)
								{
									if(k - 1 >= 0)
									{
										if(PetriDish.dish[k-1][i] == null)
										{
											PetriDish.dish[k-1][i] = PetriDish.dish[k][i];
											PetriDish.dish[k][i] = null;
											
										}
									}
										
								}
								//1c6a2. moves the organism right
								else if(direction == 1)
								{
									if(i + 1 <= 19)
									{
										if(PetriDish.dish[k][i+1] == null)
										{
											PetriDish.dish[k][i+1] = PetriDish.dish[k][i];
											PetriDish.dish[k][i] = null;
										}
									}
								}
								//1c6a3. moves the organism down
								else if(direction == 2)
								{
									if((k + 1) <= 19)
									{
										if(PetriDish.dish[k+1][i] == null)
										{
											PetriDish.dish[k+1][i] = PetriDish.dish[k][i];
											PetriDish.dish[k][i] = null;
										}
									}
								}
								//1c6a4. moves the organism left
								else if(direction == 3)
								{
									if(i - 1 >= 0)
									{
										if(PetriDish.dish[k][i-1] == null)
										{
											PetriDish.dish[k][i-1] = PetriDish.dish[k][i];
											PetriDish.dish[k][i] = null;
										}
									}
								}
							}
						}
						//1d. If it starves then destroy it
						else
						{
							PetriDish.dish[k][i] = null;
						}
					}		
				}
			}
		}
	}

	/**
	 * This is the method that checks to see if children are ready
	 * to be born and finds a place for them as well
	 * @param PetriDish is the array of organisms that we are using
	 */
	public static void checkSpawning(PetriDish PetriDish)
	{
		//1. LOOP
		for(int k = 0; k < PetriDish.getArrayLength(); k++)
		{
			for(int i = 0; i < PetriDish.getArrayLength(); i++)
			{
				//2. Check to see if nematode
				if(PetriDish.dish[k][i] instanceof Nematode)
				{
					//2a. Check to see if it has move the right amount of steps
					if(PetriDish.dish[k][i].getNumOfSteps() == PetriDish.dish[k][i].getStepsTilSpawn())
					{
						//2b. Find a spot for the new child
						if((k - 1) >= 0 && PetriDish.dish[k - 1][i] == null)
						{
							PetriDish.dish[k][i].setNumOfSteps(0);
							PetriDish.dish[k - 1][i] = PetriDish.dish[k][i].spawnChild(k - 1, i);
						}
						else if((i + 1) <= 19 && PetriDish.dish[k][i + 1] == null)
						{
							PetriDish.dish[k][i].setNumOfSteps(0);
							PetriDish.dish[k][i + 1] = PetriDish.dish[k][i].spawnChild(k , i + 1);
						}
						else if((k + 1) <= 19 && PetriDish.dish[k + 1][i] == null)
						{
							PetriDish.dish[k][i].setNumOfSteps(0);
							PetriDish.dish[k + 1][i] = PetriDish.dish[k][i].spawnChild(k + 1, i);
						}
						else if((i - 1) >= 0 && PetriDish.dish[k][i - 1] == null)
						{
							PetriDish.dish[k][i].setNumOfSteps(0);
							PetriDish.dish[k][i - 1] = PetriDish.dish[k][i].spawnChild(k, i - 1);
						}
						//2c. If no spots then reset spawn steps without child
						else
						{
							PetriDish.dish[k][i].setNumOfSteps(0);
						}
					}
				}
				//3. Chekc to see if amoeba
				if(PetriDish.dish[k][i] instanceof Amoeba)
				{
					//3a. Check to see if it has moved the right amount of steps
					if(PetriDish.dish[k][i].getNumOfSteps() == PetriDish.dish[k][i].getStepsTilSpawn())
					{
						//3b. Find spot for new child
						if((k - 1) >= 0 && PetriDish.dish[k - 1][i] == null)
						{
							PetriDish.dish[k][i].setNumOfSteps(0);
							PetriDish.dish[k - 1][i] = PetriDish.dish[k][i].spawnChild(k - 1, i);
						}
						else if((i + 1) <= 19 && PetriDish.dish[k][i + 1] == null)
						{
							PetriDish.dish[k][i].setNumOfSteps(0);
							PetriDish.dish[k][i + 1] = PetriDish.dish[k][i].spawnChild(k , i + 1);
						}
						else if((k + 1) <= 19 && PetriDish.dish[k + 1][i] == null)
						{
							PetriDish.dish[k][i].setNumOfSteps(0);
							PetriDish.dish[k + 1][i] = PetriDish.dish[k][i].spawnChild(k + 1, i);
						}
						else if((i - 1) >= 0 && PetriDish.dish[k][i - 1] == null)
						{
							PetriDish.dish[k][i].setNumOfSteps(0);
							PetriDish.dish[k][i - 1] = PetriDish.dish[k][i].spawnChild(k, i - 1);
						}
						//3c. If no spots then reset spawn without child
						else
						{
							PetriDish.dish[k][i].setNumOfSteps(0);
						}
					}
				}
			}
		}
	}
	
	/**
	 * This is the method to return the length of the array that
	 * we are using
	 * @return is the length of the dish array
	 */
	public int getArrayLength()
	{
		return dish.length;
	}
}

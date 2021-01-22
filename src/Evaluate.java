
public class Evaluate {

	private int boardRows;
	private int boardColumns;
	private int tilesNeeded;
	private int maxLevels;
	private char[][] gameBoard;
	
	public Evaluate (int boardRows, int boardColumns, int tilesNeeded, int maxLevels) {
		
		//declares a new gameboard with the rows and columns provided
		char[][] gameBoard = new char[boardRows][boardColumns];
		
		//initializes the gameboard all with the letter g, meaning the board is empty
		//the inner loop is for the columns, outer loop fills in the rows
		
		for (int i = 0; i < boardRows; i++)
		{
			for (int j = 0; j < boardColumns; j++)
			{
				gameBoard[i][j] = 'g';
			}
		}
		
		//setting the instance variable to the gameboard just created
		this.gameBoard = gameBoard;
		this.boardRows = boardRows;
		this.boardColumns = boardColumns;
		this.tilesNeeded = tilesNeeded;
		this.maxLevels = maxLevels;
		
	}
	
	public Dictionary createDictionary() {
		
		//creates a dictionary of size 9887, arbitrarily picked number
		Dictionary myDictionary = new Dictionary(9887);
		
		return myDictionary;
	}
	
	public Data repeatedConfig (Dictionary dict) {
		
		//appends all the characters in the gameboard array into a string
		String gameKey = String.valueOf(gameBoard);
		
		//if the string is not within the dictionary, it did not exist there in the first place, this returns a null
		if (dict.get(gameKey) == null)
		{
			return null;
		}
		//else if the string was found in the dictionary, it is returned
		else
		{
			return dict.get(gameKey);
		}
	}
	
	public void insertConfig(Dictionary dict, int score, int level) {
		
		//appends all the characters in the gameboard array into a string
		String gameKey = String.valueOf(gameBoard);
		
		//creates a new Data object with the right information (key, score, level) with the key calculated above
		Data thisConfig = new Data(gameKey,score,level);
		
		//puts this object into the dictionary
		dict.put(thisConfig);
			
	}
	
	public void storePlay(int row, int col, char symbol) {
		//stores the symbol at the row and column identified
		gameBoard[row][col] = symbol;

	}
	
	public boolean squareIsEmpty (int row, int col) {
		
		//checks if the gameboard has a g, indicating it is empty
		if(gameBoard[row][col] == 'g')
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean tileOfComputer (int row, int col) {
		
		//if the indicated spot has an o, it is a computer controlled spot
		if(gameBoard[row-1][col-1] == 'o')
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean tileOfHuman (int row, int col) {
		
		//if the indicated spot has a b, it is a human controlled spot
		if(gameBoard[row-1][col-1] == 'b')
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean wins (char symbol) {
		
		/*
		 * calls the 4 private methods to check all cases where the game could be won
		 * this checks the horizontals if there are specified tiles needed in a row, verticals if there are
		 * tilesNeeded in a row, and the diagonal up and diagonal down if there are specified tiles needed in a row.
		 */
		if (checkHorizontal(symbol) == true || checkVertical(symbol) == true || checkDiagonalUp(symbol) == true || checkDiagonalDown(symbol) == true)
		{
			return true;
		}
		
		//false if none of the conditions are won, meaning the game is lost
		else
		{
			return false;
		}
		
		
	}
	
	
	private boolean checkHorizontal(char symbol) {
		
		//declaring a character element
		char currentElement;
		
		//this counter will keep track of "tilesNeeded in a row", so when the counter reaches tilesNeeded in a row, the game wins
		int symbolCounter = 0;
		
		
		//this outer loop cycles through the rows of the array
		for (int i = 0; i < boardRows; i++ )
		{
			//this inner loop cycles through the columns of the array
			for (int j = 0; j < boardColumns; j++)
			{
				//sets the element that is being looked at at grid i and j
				currentElement = gameBoard[i][j];
				
				//this compares the element at the position in the grid, with the element input for the symbol to see if they are the same
				//the function compare will store the answer as an integer
				int compareChar = Character.compare(currentElement, symbol);
				
				//if the integer is a 0, then that means it is the same symbol
				if (compareChar == 0)
				{
					//since we have found one of the symbols, we will increment that we are adding 1 more in a row
					//which is one step closer to the win condition
					symbolCounter++;
					
					//once we reach tilesNeeded in a row, the game wins
					if(symbolCounter == tilesNeeded)
					{
						return true;
					}
				}
				//if we didnt find the symbol we are looking for, it is reset to 0 in a row of the symbol we are looking for
				else
				{
					symbolCounter = 0;
				}
				
			}
			//this resets the counter to 0 in a row before we begin looking at the next row
			symbolCounter = 0;
		}
		
		//if the program got this far, it did not win, so will return a lose condition
		return false;
	}
	
	private boolean checkVertical(char symbol) {
		
		//declares variables that will be used in a loop
		char currentElement;
		int symbolCounter = 0;
		
		/*the outter loop changes the columns while the inner loop goes through the rows. it will check each individual element, and
		 * subsequently check the one underneath it for a possible vertical victory. if it finds the right symbol, it will increment
		 * the counter by 1, meaning it is one step closer to being a win. once the counter reaches tiles needed, the game will 
		 * return a true. otherwise, it will loop through the entire board and just return a false
		 * 
		 */
		for(int i = 0; i < boardColumns; i++)
		{
			
			for (int j = 0; j < boardRows; j++)
			{
				
				currentElement = gameBoard[j][i];
				
				int compareChar = Character.compare(currentElement, symbol);
				
				if (compareChar == 0)
					{
						symbolCounter++;
						if(symbolCounter == tilesNeeded)
							{
								return true;
							}
					}
				else
					{
						symbolCounter = 0;
					}
				
				
			}
			
			symbolCounter = 0;
		}
		
		return false;
	}
	
	
	private boolean checkDiagonalDown(char symbol)
	{
		//initializing variables that will be used in the loop
		char currentElement;
		int symbolCounter = 0;
		int counter;
		
		//initializing variables that will use different values depending on if it is a square or rectangle
		//these temporary values will set limits, so that the array does not go out of bounds
		int tempBoundary;
		int tempColumns;
		int tempRows;
		
		//this would be a rectangle with more columns than rows
		if (boardRows < boardColumns)
		{
			tempColumns = boardRows;
			tempRows = boardRows;
			tempBoundary = boardRows;
		}
		
		//rectangle with more rows than columns
		else if (boardColumns < boardRows)
		{
			tempRows = boardColumns;
			tempColumns = boardColumns;
			tempBoundary = boardColumns;
		}
		
		//this case would be in the case of a square
		else
		{
			tempColumns = boardColumns;
			tempRows = boardRows;
			tempBoundary = boardRows;
		}
		
		/*this will attempt to check all the diagonals in the bottom half of a square. Picturing a square, this loop will
		 * first iterate through the middle diagonal from the top left to the bottom right, and then proceed to check every diagonal beneath it
		 * the counter is reset everytime outside the inner loop, as that will also be incremented with every column increment. This way, with
		 * every increase horizontally, the vertical will move down with it, creating a diagonal
		 * 
		 */
		for (int i = 0; i < tempRows; i++)
		{
			counter = 0;
			//decrementing the columns ensures we do not go out of index bounds when you pass the middle diagonal
			tempColumns--;
			for (int j = 0; j <= tempColumns; j++)
			{
				currentElement = gameBoard[counter][j];
				
				int compareChar = Character.compare(currentElement, symbol);
				
				if (compareChar == 0)
					{
						symbolCounter++;
						if(symbolCounter == tilesNeeded)
							{
								return true;
							}
					}
				else
					{
						symbolCounter = 0;
					}
				//increments the counter every inner loop
				counter++;
			}
			
			symbolCounter = 0;
		}
		
		//resets the tempColumns that was decremented above
		tempColumns = tempBoundary;
		
		/*
		 * This loop cycles through the pseudo 'top' of a square. if the grid was a square, this would check the squares. As highlighted below,
		 * if this were a rectangle, the middle diagonals would shift this pseudo 'top' over by a certain number. That number will be explained below.
		 * the top starts at where the column subtracted by rows +1 is, and it will check every diagonal above that.
		 */
		for (int j = boardColumns-boardRows+1; j < boardColumns; j++)
		{
			
			counter = 1;
			tempRows--;
			for (int i = 0; i < tempRows; i++)
			{
				currentElement = gameBoard[i][counter];
				int compareChar = Character.compare(currentElement, symbol);
				
				if (compareChar == 0)
					{
						//counter to keep track if there was already symbol found, and it will take it one step closer to winning
						symbolCounter++;
						
						//of the number for a required win is reached, this will return a win condition
						if(symbolCounter == tilesNeeded)
							{
								return true;
							}
					}
				else
					{
						symbolCounter = 0;
					}
				
				//counter incrememnts within the inner loop, to create the diagonal effect
				counter++;
			}
			
			symbolCounter = 0;
		}
		
		
		

		//returns false at the end if no win condition was found
		return false;
	}
	
	//same code as above, this time checking upwards diagonals
	private boolean checkDiagonalUp(char symbol)
	{
		//declares variables that will be used within the loops
		char currentElement;
		int symbolCounter = 0;
		int counter;
		
		//declares varaibles that will be used to determine limites
		int tempBoundary;
		int tempColumns;
		int tempRows;
		
		//if statements are there in the case that this is a rectangle, it will set limits on either the rows or columns, such that
		//the index doesnt go out of bounds and throw an exception
		if (boardRows < boardColumns)
		{
			tempColumns = boardRows;
			tempRows = boardRows;
		}
		else if (boardColumns < boardRows)
		{
			tempRows = boardColumns;
			tempColumns = boardColumns;
		}
		else
		{
			tempColumns = boardColumns;
			tempRows = boardRows;
		}
		
		/*this will attempt to check all the diagonals in the top half of a square. Picturing a square, this loop will
		 * first iterate through the middle diagonal from the bottom left to the top right, and then proceed to check every diagonal above it
		 * the counter is reset everytime outside the inner loop, as that will also be incremented with every column increment. This way, with
		 * every increase horizontally, the vertical will move up with it, creating a diagonal
		 * 
		 */
		for (int i = tempRows-1; i >= 0; i--)
		{
			counter = i;
			
			//decremented everytime to ensure the index does not go out of bounds
			tempColumns--;
			
			//this inner loop will check all of the columns, and increment with the counter, creating a diagonal effect
			for (int j = 0; j <= tempColumns; j++)
			{
				currentElement = gameBoard[counter][j];
				
				int compareChar = Character.compare(currentElement, symbol);
				
				//if the character is found, it will increment the counter by one, and when the appropriate number of tiles in a row
				//is reached, this will result in a win
				if (compareChar == 0)
					{
						symbolCounter++;
						if(symbolCounter == tilesNeeded)
							{
								return true;
							}
					}
				
				//if the tile does not match, it will reset the counter
				else
					{
						symbolCounter = 0;
					}
				
				//decrements the counter with the inside loop
				counter--;
			}
			
			symbolCounter = 0;
		}
		
		//resets the temp columns that was decremented in the loop above
		tempColumns = boardColumns;
		
		/*
		 * This loop cycles through the pseudo 'bottom' of a square. if the grid was a square, this would check the squares. As highlighted below,
		 * if this were a rectangle, the middle diagonals would shift this pseudo 'bottom' over by a certain number. That number will be explained below.
		 */
		int lowerLimit = 0;
		for (int j = 1; j < tempColumns; j++)
		{
			
			counter = j;
			lowerLimit++;
			
			//the inner loop has a lower limit to prevent it from going out of index range
			for (int i = tempRows - 1; i >= lowerLimit; i--)
			{
				currentElement = gameBoard[i][counter];
				int compareChar = Character.compare(currentElement, symbol);
				
				if (compareChar == 0)
					{
						symbolCounter++;
						if(symbolCounter == tilesNeeded)
							{
								return true;
							}
					}
				else
					{
						symbolCounter = 0;
					}
				counter++;
			}
			
			symbolCounter = 0;
		}
		
		
		

		return false;
	}
	
	
	//checks if the result is a draw, so if all squares are filled
	public boolean isDraw() {
		
		//initializes a boolean to check if there are empty spaces on the board
		boolean containsEmpty = false;
		
		//loop to check all squares for an empty space 'g'.
		for (int i = 0; i < boardRows; i++)
		{
			for (int j = 0; j < boardColumns; j++)
			{
				
				int compareChar = Character.compare(gameBoard[i][j], 'g');
				
				if (compareChar == 0)
				{
					//when found, the result is true, meaning the gameboard contains an empty space
					containsEmpty = true;
				}
			}
		}
		
		//returns the opposite of empty. if the result is there is an empty space, that means it is not a draw. this means it will return a false for draw
		//if the result is there are no empty spaces, this will return the opposite, meaning it is a draw
		return !containsEmpty;
	}
	
	public int evalBoard() {

		//checks the win condition for if a human wins, returns the appropriate integer
		if (wins('b'))
		{
			return 0;
		}
		
		//checks the win condition for if the computer wins, returns the appropriate integer
		else if (wins('o'))
		{
			return 3;
		}
		
		//checks the draw condition, returns the appropriate integer
		else if (isDraw())
		{
			return 2;
		}
		
		//if it doesnt fall into any of the categories above, the game goes on
		else
		{
			return 1;
		}
	}
	
}

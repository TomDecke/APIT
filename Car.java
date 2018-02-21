import java.util.*;

/**
 * Class to model a car. This might get abstracted in a future process into a vehicle class
 * @author Tom
 *
 */
public class Car implements Runnable{
	
	//speed range of the car
	private final int MIN_SPEED = 50;
	private final int MAX_SPEED = 500;
	
	//initial properties of the car
	private int speed;
	private String symbol;
	private MoveSet move;
	private boolean onGrid;
	
	//current position of the car in the grid
	private int currentRow;
	private int currentColumn;
	
	//properties of the intersection
	private Intersection intersection;
	private int numRows;
	private int numCols;
	
	/**
	 * Constructor to create a car object.
	 * 
	 * @param move MoveSet determining the direction of movement
	 * @param symbol String for the representation on the grid
	 * @param row int starting row
	 * @param col int starting column
	 * @param Intersection the intersection on which the car moves
	 */
	public Car(MoveSet move, String symbol, int row, int col, Intersection intersection) {
				
		//information about intersection
		this.intersection = intersection;
		numRows = intersection.getRows();
		numCols = intersection.getColumns();
		
		//information about the car
		this.move = move;
		this.symbol = symbol;
		currentRow = row;
		currentColumn = col;
		onGrid = true;
		
		//calculate a random number for the movement speed between MIN_SPEED and MAX_SPEED
		Random rand = new Random();
		speed = rand.nextInt(MAX_SPEED-MIN_SPEED)+MIN_SPEED;
	}

	/**
	 * move the car on the grid
	 */
	public void moveCar() {
		//get the movement direction of the car
		String currentMove = move.getMoves();
		//based on the direction, check if you have reached the end of the grid and if not execute the movement
		switch (currentMove) {
		case "NORTH":
			//check if the end of the board is reached
			if (currentRow - 1 < 0) {
				intersection.leaveField(this);
				onGrid = false;
			}
			//check if you are entering the board
			else if(currentRow == numRows-1) {
				intersection.occupyField(this, currentRow, currentColumn);
			}
			//move one field
			else {
				intersection.occupyField(this, currentRow-1, currentColumn);
				intersection.leaveField(this);
			}
			//update position
			currentRow--;
			break;

		//analogous to case "NORTH"
		case "EAST":
			if (currentColumn + 1 >= numCols) {
				intersection.leaveField(this);
				onGrid = false;
			}
			else if(currentColumn == 0) {
				intersection.occupyField(this, currentRow, currentColumn);
			}
			else {
				intersection.occupyField(this, currentRow, currentColumn + 1);
				intersection.leaveField(this);
			}
			currentColumn++;
			break;
			
			
		case "SOUTH":
			if(currentRow + 1 >= numRows) {
				intersection.leaveField(this);
				onGrid = false;
			}
			else if(currentRow == 0) {
				intersection.occupyField(this, currentRow, currentColumn);
			}
			else {
				intersection.occupyField(this, currentRow + 1, currentColumn);
				intersection.leaveField(this);
			}
			currentRow++;
			break;
			

		case "WEST":
			if(currentColumn - 1 < 0) {
				intersection.leaveField(this);
				onGrid = false;
			}
			else if(currentColumn == numCols-1) {
				intersection.occupyField(this, currentRow, currentColumn);
			}
			else {
				intersection.occupyField(this, currentRow, currentColumn-1);
				intersection.leaveField(this);
			}
			currentColumn--;
			break;
		}
		
	}

	/**
	 * run method for the thread
	 */
	@Override
	public void run() {
		while(onGrid) {
			try {
				Thread.sleep(speed);
				moveCar();
			} catch (InterruptedException e) {}
		}
	}
	
	/**
	 * Accessor for the current row
	 * @return int current row
	 */
	public int getCurrentRow() {
		return currentRow;
	}

	/**
	 * Accessor for the current column
	 * @return int current column
	 */
	public int getCurrentColumn() {
		return currentColumn;
	}

	/**
	 * Accessor for the speed
	 * @return int movement speed of the car
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Accessor for the car symbol
	 * @return String with graphical representation
	 */
	public String getSymbol() {
		return symbol;
	}


}

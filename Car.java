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
	private long travelTime;
	
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
				removeCarFromGrid();
			}
			//move one field
			else {
				intersection.getFieldAtPosition(currentRow-1,currentColumn).occupyField(this);
				intersection.getFieldAtPosition(currentRow,currentColumn).leaveField();
				//update position
				currentRow--;
			}
			break;

		//analogous to case "NORTH"
		case "EAST":
			if (currentColumn + 1 >= numCols) {
				removeCarFromGrid();
			}
			else {
				intersection.getFieldAtPosition(currentRow,currentColumn +1).occupyField(this);
				intersection.getFieldAtPosition(currentRow,currentColumn).leaveField();
				currentColumn++;
			}
			break;
			
			
		case "SOUTH":
			if(currentRow + 1 >= numRows) {
				removeCarFromGrid();
			}
			else {
				intersection.getFieldAtPosition(currentRow+1,currentColumn).occupyField(this);
				intersection.getFieldAtPosition(currentRow,currentColumn).leaveField();
				currentRow++;
			}
			break;
			

		case "WEST":
			if(currentColumn - 1 < 0) {
				removeCarFromGrid();
			}
			else {
				intersection.getFieldAtPosition(currentRow,currentColumn-1).occupyField(this);
				intersection.getFieldAtPosition(currentRow,currentColumn).leaveField();
				currentColumn--;
			}
			break;
		}
		
	}
	
	/**
	 * Method to place a car on the grid
	 */
	public void addCarToGrid() {
		intersection.getFieldAtPosition(currentRow, currentColumn).occupyField(this);
		//remember the time when the car enters the intersection
		travelTime = System.currentTimeMillis();
	}
	
	/**
	 * Method to remove a car from the grid
	 */
	public void removeCarFromGrid() {
		intersection.getFieldAtPosition(currentRow, currentColumn).leaveField();
		onGrid = false;
		//calculate the time it took a single car to cross the intersection
		long endTime = System.currentTimeMillis();
		travelTime = endTime - travelTime;
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
	
	/**
	 * Accessor for the boolean stating if the car is on the grid
	 * @return boolean whether the car is on the grid
	 */
	public boolean getOnGrid() {
		return onGrid; 
	}
	
	/**
	 * Accessor for the time a car spent on the intersection
	 * @return long 
	 */
	public long getTravelTime() {
		return travelTime;
	}


}

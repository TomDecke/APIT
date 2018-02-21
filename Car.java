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
	
	//current position of the car in the grid
	private int currentRow;
	private int currentColumn;
	//initial properties
	private int speed;
	private String symbol;
	private MoveSet move;
	
	/**
	 * Constructor to create a car object.
	 * 
	 * @param move MoveSet determining the direction of movement
	 * @param symbol String for the representation on the grid
	 * @param row int starting row
	 * @param col int starting column
	 */
	public Car(MoveSet move, String symbol, int row, int col) {
		this.move = move;
		this.symbol = symbol;
		currentRow = row;
		currentColumn = col;
		
		//calculate a random number for the movement speed between MIN_SPEED and MAX_SPEED
		Random rand = new Random();
		speed = rand.nextInt(MAX_SPEED-MIN_SPEED)+MIN_SPEED;
	}

	/**
	 * move the car in the grid
	 */
	public void moveCar() {
		//get the movement direction of the car
		String currentMove = move.getMoves();
		
		//based on the direction, execute the movement
		switch (currentMove) {
		case "NORTH":
			System.out.println("north");
			break;

		case "EAST":
			System.out.println("east");			
			break;
			
		case "SOUTH":
			System.out.println("south");
			break;

		case "WEST":
			System.out.println("west");
			break;
		default:
			System.err.println("Invalid move");
			break;
		}
		
	}

	/**
	 * run method for the thread
	 */
	@Override
	public void run() {

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

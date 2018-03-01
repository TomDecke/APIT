/**
 * Class to model the moves of a vehicle - in our specific case a car
 * Since every car only has one move the instance variable is a String. For more complex movement patterns
 * the class is intended to be extendable into an ArrayList of Strings
 * @author 2354160d
 *
 */
public class MoveSet {

	//String representing the movement direction
	private String moves;
	
	/**
	 * Constructor to create a move set
	 * @param move String stating the movement direction
	 */
	public MoveSet(String move) {
		moves = move;
	}
	
	/**
	 * Accessor for the move
	 * @return String the direction of the move
	 */
	public String getMoves() {
		return moves;
	}
	
}

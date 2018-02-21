/**
 * Class to model the moves of a vehicle - in our specific case a car
 * Since every car only has one move the instance variable is a String. For more complex movement patterns
 * the class is intended to be extendable into an ArrayList of Strings
 * @author Tom
 *
 */
public class MoveSet {

	//String representing the movement direction
	private String moves;
	
	//constructor to create a move object
	public MoveSet(String move) {
		moves = move;
	}
	
	public String getMoves() {
		return moves;
	}
	
}

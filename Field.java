/**
 * Class to represent one field in the grid(intersection)
 * @author Tom
 *
 */
public class Field {

	private int posRow;
	private int posCol;
	private Car curCar;
	private boolean available;
	
	/**
	 * Constructor to create a field 
	 * @param row int row position in the grid
	 * @param column int column position in the grid
	 */
	public Field(int row, int column) {
		posRow = row;
		posCol = column;
		curCar = null;
		available = true;
	}
	
	public void setCurCar(Car car) {
		curCar = car;
	}
	
	public Car getCurCar() {
		return curCar;
	}
}

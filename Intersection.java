/**
 * Class to represent the intersection
 * @author Tom
 *
 */
public class Intersection {
	private Car[][] grid;
	private int rows;
	private int cols;
	
	/**
	 * constructor to create an intersection object
	 * @param dimM int number of rows
	 * @param dimN int number of columns
	 */
	public Intersection(int dimM, int dimN) {
		grid = new Car[dimM][dimN];
		rows = dimM;
		cols = dimN;
	}
	
	/**
	 * Check if a field in the grid is empty
	 * @param row int position in row
	 * @param col int position in column
	 * @return boolean whether or not the requested field is empty
	 */
	public boolean checkField(int row, int col) {
		if(grid[row][col] == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Removes car from the field it was on
	 * @param car Car that is currently occupying the field
	 */
	public void leaveField(Car car) {
		grid[car.getCurrentRow()][car.getCurrentColumn()] = null;
	}
	
	/**
	 * moves a car to another field
	 * @param car the car to be moved
	 * @param row the row to move to
	 * @param col the column to move to
	 */
	public void occupyField(Car car, int row, int col) {
	//	if(checkField(row, col)) {
			grid[row][col] = car;
	//	}
		
	}
	
	/**
	 * Accessor for the car grid
	 * @return Car[][] the intersection
	 */
	public Car[][] getGrid() {
		return grid;
	}
	
	/**
	 * Accessor for the number of rows
	 * @return int thenumber of rows
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * Accessor for the number of colums
	 * @return int the number of columns
	 */
	public int getColumns() {
		return cols;
	}
}

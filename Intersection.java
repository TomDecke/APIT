/**
 * Class to represent the intersection
 * @author 2354160d
 *
 */
public class Intersection {
	
	//field-matrix to model the intersection
	private Field[][] grid;
	private int rows;
	private int cols;
	
	/**
	 * Constructor to create an intersection object
	 * @param int number of rows
	 * @param int number of columns
	 */
	public Intersection(int dimM, int dimN) {
		
		//set up the grid
		grid = new Field[dimM][dimN];
		for(int x = 0; x < dimM; x++) {
			for(int y = 0; y < dimN; y++) {
				grid[x][y] = new Field();
			}
		}
		rows = dimM;
		cols = dimN;
	}

	/**
	 * Accessor for the car grid
	 * @return Car[][] the intersection
	 */
	public Field[][] getGrid() {
		return grid;
	}
	
	/**
	 * Get a specified field from the intersection
	 * @param row int position in the row
	 * @param col int position in the column
	 * @return Field the field at the specified position
	 */
	public Field getFieldAtPosition(int row, int col) {
		return grid[row][col];
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

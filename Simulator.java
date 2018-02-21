/**
 * Class to display the intersection
 * @author Tom
 *
 */
public class Simulator implements Runnable{

	//time intervall between the drawing of intersections
	private final int INTERVALL = 20;
	private Car[][] grid;
	private int cap;
	private int numRows;
	private int numCols;
	
	/**
	 * Constructor to create a simulator
	 * @param intersection the intersection that is to be drawn
	 * @param cap the number of times the grid should be drawn
	 */
	public Simulator(Intersection intersection, int cap) {
		this.cap = cap;
		grid = intersection.getGrid();
		numCols = intersection.getColumns();
		numRows = intersection.getRows();
	}
	
	/**
	 * Draws the intersection in it's current state
	 * @param execution number of the execution
	 */
	public void draw(int execution) {
		System.out.println("Execution: "+ execution);
		

		drawHorizontalLine(numCols);
		
		Car currentCar;
		//iterate over the grid and draw occupied fields 
		for (int x = 0; x < numRows; x++) {
			for (int y = 0; y < numCols; y++) {
				currentCar = grid[x][y];
				if(currentCar != null) {
					System.out.print("|"+currentCar.getSymbol());
				}
				else {
					System.out.print("| ");
				}
			}
			System.out.println("|");
		}
		drawHorizontalLine(numCols);
		System.out.println("");
		
		
	}
	
	/**
	 * Draw a horizontal line consisting of "-" for top and/or bottom of the grid
	 * @param numCols int number of columns
	 */
	public void drawHorizontalLine(int numCols) {
		//calculate the amount of needed "-" and print them
		int length =  2*numCols + 1;
		for(int i = 0; i < length; i++) {
			System.out.print("-");
		}

		System.out.println("");
	}
	
	@Override
	public void run() {
		int counter = 0;
		while(counter <= cap) {
			try {
				Thread.sleep(INTERVALL);
				draw(counter);
			} catch (InterruptedException e) {}
		
			counter++;
		}
		
	}

}

/**
 * Car generator that generates cars for exactly one line either horizontal or vertical
 * @author 2354160
 *
 */
public class TestGenerator extends CarGenerator{

	//properties for the test cars
	private boolean horizontal;
	private int row;
	private int col;
	private String symbol;
	
	
	/**
	 * Constructor to create a test generator
	 * @param intersection Intersection, the shared intersection
	 * @param dir boolean determining if the car goes horizontal or vertical
	 */
	public TestGenerator(Intersection intersection, boolean dir) {
		super(intersection);
		horizontal = dir;
	}
	
	@Override
	public Car generateCar() {
		
		
		Car newCar;
		MoveSet ms;
		//initialize the car to go either east or south on one row/column
		if(horizontal) {
			ms = new MoveSet("EAST");
			row = 5;
			col = 0;
			symbol = "-";
			//different delays that were used to test the program behavior
			super.delay = 1000;
			//super.delay = 2000;
			//super.delay = 3000;
			newCar = new Car(ms, symbol, row, col,intersection);
			newCar.setSpeed(300);

		}
		else {
			ms = new MoveSet("SOUTH");
			col = 9;
			row = 0;
			symbol = "o";
			newCar = new Car(ms, symbol, row, col,intersection);
			newCar.setSpeed(700);
			super.delay = 1000;

		}

		//add the created car to the list of cars created by this generator
		createdCars.add(newCar);

		return newCar;
	}

}

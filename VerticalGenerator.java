/**
 * Class to represent a generator that creates cars going from north to south or vice versa
 * @author 2354160d
 *
 */
public class VerticalGenerator extends CarGenerator {

	//the columns the generator is applied to
	private int[] cols;
	//direction the car is supposed to move
	private String firstDirection;
	private String symbol = "o";


	/**
	 * Use the constructor of the CarGenerator to create a vertical generator
	 * @param intersection Intersection grid on which the cars move
	 * @param cols int[] referring to the columns on which the generator is to be applied
	 * @param direction int for the first heading of cars; 0 = NORTH, 2 = SOUTH
	 * @see CarGenerator
	 */
	public VerticalGenerator(Intersection intersection, int[] cols, int direction) {
		super(intersection);
		
		//derive the direction of the car from map and parameter
		this.firstDirection = dirMap.get(direction);
		this.cols = cols;
		
		//assign a random delay in in the range[500,1000) to the generator
		super.delay = rand.nextInt(500)+500;
	}


	/**
	 * Create a car that starts either north or south
	 * @return Car the generated car
	 */
	@Override
	public Car generateCar() {

		//determine the first move of the car
		MoveSet ms = new MoveSet(firstDirection);

		int row = 0;
		//if the car heads north change start row to the south-most position in the grid
		if(firstDirection.equals("NORTH")) {
			row = maxRow-1;
		}
		
		//determine a random start column
		int col = cols[rand.nextInt(cols.length)];
		//use the obtained information to create a new car
		Car newCar = new Car(ms, symbol, row, col,intersection);

		//add the created car to the list of cars created by this generator
		createdCars.add(newCar);

		return newCar;
	}
}

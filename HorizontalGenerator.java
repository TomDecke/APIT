/**
 * Class to represent a generator that creates cars going from east to west or vice versa
 * @author Tom
 *
 */
public class HorizontalGenerator extends CarGenerator{

	//the rows the generator is applied to
	private int[] rows;
	//direction the car is supposed to move
	private String firstDirection;
	private String symbol = "-";
	

	/**
	 * Uses the constructor of the CarGenerator to create a horizontal generator
	 * @param intersection grid on which the cars move
	 * @param rows int[] referring to the rows on which the generator is to be applied
	 * @param direction int for the first heading of cars; 1 = EAST, 3 = WEST
	 * @param delay int delay that is to be applied in between creation of cars
	 * @see CarGenerator
	 */
	public HorizontalGenerator(Intersection intersection, int[] rows, int direction, int delay) {
		super(intersection);
		
		//derive the direction of the car from map and parameter
		this.firstDirection = dirMap.get(direction);
		this.rows = rows;
	
		//overwrite the delay with the passed value
		super.delay = delay;
	}


	/**
	 * Create a car that starts either west or east
	 * @return Car the generated car
	 */
	@Override
	public Car generateCar() {

		//determine the first move of the car
		MoveSet ms = new MoveSet(firstDirection);

		//set start column to 0
		int col = 0;
		//if the car heads west change start column to the east-most position in the grid
		if(firstDirection.equals("WEST")) {
			col = maxCol-1;
		}
		
		//use random to allocate a row
		int row = rows[rand.nextInt(rows.length)];
		//use the obtained information to create a new car 
		Car newCar = new Car(ms, symbol, row, col,intersection);

		//add the created car to the list of cars created by this generator
		createdCars.add(newCar);

		return newCar;
	}
}

/**
 * Car generator that generates cars for exactly one line either horizontal or vertical
 * @author 2354160
 *
 */
public class TestGenerator extends CarGenerator{

	private boolean horizontal;
	private int row;
	private int col;
	private String symbol;
	
	private int placed = 0;
	
	public TestGenerator(Intersection intersection, boolean dir) {
		super(intersection);
		horizontal = dir;
	}
	
	@Override
	public Car generateCar() {
		
		//determine the first move of the car
		Car newCar;
		MoveSet ms;
		if(horizontal) {
			ms = new MoveSet("EAST");
			row = 5;
			col = 0;
			symbol = "-";
			super.delay = 1000;
			//super.delay = 2000;
			//super.delay = 3000;
			newCar = new Car(ms, symbol, row, col,intersection);
			

		}
		else {
			ms = new MoveSet("SOUTH");
			col = 9;
			row = 0;
			symbol = "o";
			newCar = new Car(ms, symbol, row, col,intersection);
			newCar.setSpeed(500);
			super.delay = 1000;

		}

		//add the created car to the list of cars created by this generator
		createdCars.add(newCar);
		placed++;

		return newCar;
	}
	
	public void determineTraffic() {
		int left = 0;
		for(Car c : createdCars) {
			if(!c.isOnGrid()) {
				left++;
			}
		}
		String report = String.format("%d %s were put on the grid. %d left the grid. Please count the remaining cars.", placed,symbol,left);
		System.out.println(report);
	}
}

import java.util.*;

import javax.naming.spi.DirectoryManager;

/**
 * Class to generate car objects
 * @author Tom
 *
 */
public class CarGenerator implements Runnable{

	//instance variables for generator specific information
	protected Intersection intersection;
	protected int maxRow;
	protected int maxCol;
	protected int delay;
	protected boolean active;



	//information about the travel time of the cars
	protected ArrayList<Car> createdCars;
	protected Log cLog;

	//map for the travel directions
	protected Map<Integer, String> dirMap;
	protected Random rand = new Random();

	/**
	 * Constructor to create a car generator
	 * @param intersection Intersection on which the cars are to be placed
	 */
	public CarGenerator(Intersection intersection, Log log) {
		this.intersection = intersection;
		maxRow = intersection.getRows();
		maxCol = intersection.getColumns();
		active = true;
		this.cLog = log;

		//create a hashmap that includes the possible movement directions
		dirMap = new HashMap<Integer,String>();
		dirMap.put(0, "NORTH");
		dirMap.put(1, "EAST");
		dirMap.put(2, "SOUTH");
		dirMap.put(3, "WEST");

		//initialize delay and space for the cars
		delay = 200;
		createdCars = new ArrayList<>();
	}

	/**
	 * generate a new car object and place it on the grid
	 * @return Car the created car
	 */
	public Car generateCar() {


		//get either EAST or SOUTH for the direction and use the information to set up a move-set
		String direction = dirMap.get(rand.nextInt(2)+1);
		MoveSet ms = new MoveSet(direction);

		//initialize start position randomly
		int startRow = rand.nextInt(maxRow);
		int startCol = rand.nextInt(maxCol);

		//reserve space for the car symbol
		String symbol = "x";

		//update either row or column to first position
		switch (ms.getMoves()) {
		case "EAST":
			symbol = "-";
			startCol = 0;
			break;

		case "SOUTH":
			symbol = "o";
			startRow = 0;
			break;		

		}

		//use the obtained information to create a new car
		Car newCar = new Car(ms, symbol, startRow, startCol,intersection);

		//add the created car to the list of cars created by this generator
		createdCars.add(newCar);

		return newCar;

	}

	/**
	 * run method for the thread
	 */
	@Override
	public void run() {
		//create an space for the car threads
		ArrayList<Thread> threads = new ArrayList<>();
		while(active) {
			try {
				//slow down the car creation
				Thread.sleep(delay);
			} catch (InterruptedException e) {}
			//create a car and add it to the grid
			Car cCar = generateCar();
			cCar.addCarToGrid();

			//add the car to the threads and start it 
			threads.add(new Thread(cCar));
			threads.get(threads.size()-1).start();
		}
		//after being deactivated from the simulator, calculate the statistics
		calculateStatistics();
	}

	/**
	 * deactivates the car generator
	 */
	public void deactivateGenerator() {
		this.active = false;
	}

	/**
	 * Iterates through the created cars and updates the log-class
	 */
	private void calculateStatistics() {
		//find the first car that traversed the grid to set the initial value
		for(Car c : createdCars) {
			if(!c.isOnGrid()) {
				cLog.setUpLog(c.getTravelTime());
				break;
			}
		}
		//pass every car that made it through the grid to the log
		for(Car c : createdCars) {
			if(!(c.isOnGrid())) {
				cLog.passCarToLog(c);
			}
		}
	}
}

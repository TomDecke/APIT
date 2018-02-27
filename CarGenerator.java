import java.util.*;

import javax.naming.spi.DirectoryManager;

/**
 * Class to generate car objects
 * @author Tom
 *
 */
public class CarGenerator implements Runnable{

	protected Intersection intersection;
	protected int maxRow;
	protected int maxCol;
	protected boolean active;

	//information about the travel time of the cars
	protected ArrayList<Car> createdCars;
	protected long minTime;
	protected long maxTime;
	protected double avgTime;

	protected Map<Integer, String> dirMap;

	/**
	 * Constructor to create a car generator
	 * @param intersection Intersection on which the cars are to be placed
	 */
	public CarGenerator(Intersection intersection) {
		this.intersection = intersection;
		maxRow = intersection.getRows();
		maxCol = intersection.getColumns();
		active = true;

		//create a hashmap that includes the possible movement directions
		dirMap = new HashMap<Integer,String>();
		dirMap.put(0, "NORTH");
		dirMap.put(1, "EAST");
		dirMap.put(2, "SOUTH");
		dirMap.put(3, "WEST");

		createdCars = new ArrayList<>();
	}

	/**
	 * generate a new car object and place it on the grid
	 */
	public Car generateCar() {

		Random rand = new Random();
		//get either EAST or SOUTH for the direction
		String direction = dirMap.get(rand.nextInt(2)+1);
		MoveSet ms = new MoveSet(direction);

		//initialize start position randomly
		int startRow = rand.nextInt(maxRow);
		int startCol = rand.nextInt(maxCol);

		//reserve space for the car symbol
		String symbol = "x";

		//update to either row or column to first position
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
				Thread.sleep(200);
			} catch (InterruptedException e) {}
			Car cCar = generateCar();
			cCar.addCarToGrid();

			//add the car to the threads and start it 
			threads.add(new Thread(cCar));
			threads.get(threads.size()-1).start();
		}
		calculateStatistics();
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void calculateStatistics() {
		int numCars = createdCars.size();
		if(numCars != 0) {
			minTime = createdCars.get(0).getTravelTime();
			maxTime = minTime;
			avgTime = minTime;
			//TODO re-think
			for(int i = 1; i < numCars-1 ; i++) {
				long currentTime = createdCars.get(i).getTravelTime();
				if(currentTime > maxTime) {
					maxTime = currentTime;
				}
				else if(currentTime < minTime) {
					minTime = currentTime;
				}
				avgTime += currentTime;
			}
			avgTime = avgTime/numCars;
		}
		System.out.println(numCars);
		System.out.println("min: " + (double)minTime/1000 + " max: " + maxTime/1000 +" avg: "+ avgTime/1000);
	}

}

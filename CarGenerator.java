import java.util.*;

import javax.naming.spi.DirectoryManager;

/**
 * Class to generate car objects
 * @author Tom
 *
 */
public class CarGenerator implements Runnable{
	
	private Intersection intersection;
	private int maxRow;
	private int maxCol;
	private boolean active;
	
	private Map<Integer, String> dirMap;
	
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
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	

}

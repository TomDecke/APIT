import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class to keep track of max- min- and average time a car needs to pass the grid
 * @author 2354160d
 *
 */
public class Log {
	//space for values of interest
	private float overallMax;
	private float overallMin;
	private float overallAvg;
	private int numCars;

	//space for all cars that traversed the grid
	private ArrayList<Car> cars;

	//lock to make sure that the log is only altered by one thread at a time
	private ReentrantLock lock = new ReentrantLock();

	/**
	 * Constructor to create an instance of the log
	 * it sets the overallMax to -1 so that the setup only updates the fields once
	 */
	public Log() {
		cars = new ArrayList<Car>();
		//set overallMax to -1 as a flag for the setUpLog-method
		overallMax = -1;
	}

	/**
	 * Assigns max, min, average and number of cars.
	 * @param time float containing the time it took one car to traverse the grid
	 */
	public void setUpLog(float time) {
		lock.lock();
		//make sure no other class has already set up the variables
		if(overallMax == -1) {
			overallMax = time;
			overallMin = overallMax;
			overallAvg = overallMax;
			numCars = 0;
		}
		lock.unlock();
	}

	/**
	 * Method to execute the arithmetic operations when a car is taken into account for the log
	 * @param Car the car which is to be considered
	 */
	public void passCarToLog(Car car) {
		//use a lock to prevent different threads from updating at the same time
		lock.lock();
		float travelTime = car.getTravelTime();
		updateMax(travelTime);
		updateMin(travelTime);
		accumulateTime(travelTime);
		cars.add(car);
		lock.unlock();
	}



	public String getReport() {
		//check if any cars made it through the grid
		if(numCars>0) {
			String text = "%d cars traversed the grid!%nMaximum time: %.2fs%nMinimum time: %.2fs%nAverage time: %.2fs%nVariance: %.2fms";
			return String.format(text, numCars,(double)overallMax/1000,(double)overallMin/1000,calcAvg(),calcVar(numCars));
		}
		else {
			return "No car made it through the grid";
		}

	}

	/**
	 * compare given value to the global maximum
	 * @param max float potential maximum
	 */
	private void updateMax(float max) {
		if(max > overallMax) {
			overallMax = max;
		}
	}

	/**
	 * compare the given value to the global minimum
	 * @param min float potential minimum
	 */
	private void updateMin(float min) {
		if(min < overallMin) {
			overallMin = min;
		}
	}

	/**
	 * sum up the times of the cars which pass through the grid
	 * @param duration float travel time of a car
	 */
	private void accumulateTime(float duration) {
		overallAvg+=duration;
		numCars++;
	}
	
	/**
	 * calculate the average time a car needs to pass through the grid (in seconds)
	 * @return double the average time of a car in seconds
	 */
	private double calcAvg() {
		return ((double)overallAvg/numCars)/1000;
	}

	/**
	 * calculate the variance of travel time
	 * @param int number of cars (has to be > 0)
	 * @return double the variance of travel (in ms)
	 */
	private double calcVar(int n) {
		//x at position i
		double xi;
		//mean
		double x = calcAvg();
		double sumOfSquares = 0;
		//calculate sum(x-xi)^2
		for(int i = 0 ; i < n ; i++) {
			xi = cars.get(i).getTravelTime()/1000;
			sumOfSquares += Math.pow((xi - x), 2);
		}
		//calculate (1/n)*sum(x-xi)^2
		double variance = sumOfSquares/n;
		return variance;
	}
}

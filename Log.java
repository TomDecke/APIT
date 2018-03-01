import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class to keep track of max- min- and average time a car needs to pass the grid
 * @author Tom
 *
 */
public class Log {
	private float overallMax;
	private float overallMin;
	private float overallAvg;

	private ArrayList<Car> cars;

	private int numCars;
	private ReentrantLock lock = new ReentrantLock();

	/**
	 * Constructor to create an instance of the log
	 * it sets the overallMax to -1 so that the setup only updates the fields once
	 */
	public Log() {
		cars = new ArrayList<Car>();
		overallMax = -1;
	}

	public void setUpLog(float time) {
		lock.lock();
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
		System.out.println("Hello");
		cars.add(car);
		lock.unlock();
	}



	public String getReport() {
		int n = cars.size();
		if(n>0) {
			String text = "%d cars traversed the grid!%nMaximum time: %.2fs%nMinimum time: %.2fs%nAverage time: %.2fs%nVariance: %.2fms";
			return String.format(text, numCars,(double)overallMax/1000,(double)overallMin/1000,calcAvg()/1000,calcVar(n));
		}
		else {
			return "No car made it through the grid";
		}

	}

	/**
	 * compare given value to the global maximum
	 * @param max possible maximum
	 */
	private void updateMax(float max) {
		if(max > overallMax) {
			overallMax = max;
		}
	}

	/**
	 * compare the given value to the global minimum
	 * @param min possible minimum
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
	 * calculate the variance of travel time
	 * @param int number of cars (has to be > 0)
	 * @return double the variance of travel
	 */
	private double calcVar(int n) {
		double avg = calcAvg();
		double sumOfSquares = 0;
		//calculate sum(x-xi)^2
		for(int i = 0 ; i < n ; i++) {
			sumOfSquares += Math.pow((cars.get(i).getTravelTime() - avg), 2);
			System.out.println(sumOfSquares);
		}
		//calculate (1/n)*sum(x-xi)^2
		double variance = (1/n)*sumOfSquares;
		return variance;

	}

	/**
	 * calculate the average time a car needs to pass through the grid
	 * @return
	 */
	private double calcAvg() {
		return (double)overallAvg/numCars;
	}
}

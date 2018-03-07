import java.util.ArrayList;
/**
 * Class to calculate the statistics of the generators
 * @author 2354160d
 *
 */
public class Statistics {

	//space for values of interest
	private double overallMax;
	private double overallMin;
	private double average;
	private int numGens;
	private int numContrib;
	
	//array list to store the travel times
	private ArrayList<Double> times;
	
	/**
	 * Constructor to create a statistics class object
	 */
	public Statistics(){
		numGens = 0;
		numContrib = 0;
		average = 0;
		times = new ArrayList<Double>();
	}
	
	/**
	 * Add a travel time to the times and increase the number counting the involved generators
	 * @param time double total travel time of a generator
	 */
	public void addTime(double time) {
		//make sure the generator passed cars trhough the grid
		if(time > 0) {
			times.add(time);
			numContrib++;
		}
		numGens++;
	}

	/**
	 * Create a report containing max- min- and average-time as well as variance
	 * @return String containing the formatted report
	 */
	public String getReport() {
		//check if any cars made it through the grid
		if(numGens>0) {
			if(times.size()>0) {
				//calculate max min and avg
				calculateStatistics();
				String text = "%d Generators created!%nMaximum time: %.2fs%nMinimum time: %.2fs%nAverage time: %.2fs%nVariance: %.2fs";
				return String.format(text, numGens,(double)overallMax/1000,(double)overallMin/1000,calcAvg(),calcVar(numContrib));
			}
			else {
				return "No car made it through the grid";
			}

		}
		else {
			return "No generators were added";
		}

	}
	
	/**
	 * Calculates min max and accumulates the total travel time
	 */
	private void calculateStatistics(){
		overallMax = times.get(0);
		overallMin = overallMax;
		for(double time : times) {
			if(time != 0) {
				updateMax(time);
				updateMin(time);
				accumulateTime(time);
			}
		}
	}

	/**
	 * compare given value to the global maximum
	 * @param max float potential maximum
	 */
	private void updateMax(double max) {
		if(max > overallMax) {
			overallMax = max;
		}
	}

	/**
	 * compare the given value to the global minimum
	 * @param min float potential minimum
	 */
	private void updateMin(double min) {
		if(min < overallMin) {
			overallMin = min;
		}
	}

	/**
	 * sum up the times of the cars which pass through the grid
	 * @param duration float travel time of a car
	 */
	private void accumulateTime(double duration) {
		average+=duration;
	}
	
	/**
	 * calculate the average time a car needs to pass through the grid (in seconds)
	 * @return double the average time of a car in seconds
	 */
	private double calcAvg() {
		return (average/numContrib)/1000;
	}

	/**
	 * calculate the variance of travel time
	 * @param int number of cars (has to be > 0)
	 * @return double the variance of travel (in s)
	 */
	private double calcVar(int n) {
		//mean
		double x = calcAvg();
		double sumOfSquares = 0;
		//calculate sum(xi-x)^2
		for(double xi : times) {
			xi /= 1000;
			sumOfSquares += Math.pow((xi - x), 2);
		}
		//calculate (1/n)*sum(xi-x)^2
		double variance = sumOfSquares/n;
		return variance/1000;
	}
	
}

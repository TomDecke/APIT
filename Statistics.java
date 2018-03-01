import java.util.ArrayList;
/**
 * Class to calculate the statistics of the generators
 * @author 2354160d
 *
 */
public class Statistics {

	//space for values of interest
	private float overallMax;
	private float overallMin;
	private float overallAvg;
	private int numGens;
	
	//array list to store the travel times
	private ArrayList<Float> times;
	
	/**
	 * Constructor to create a statistics class object
	 */
	public Statistics(){
		numGens = 0;
		overallAvg = 0;
		times = new ArrayList<Float>();
	}
	
	public void addTime(float time) {
		times.add(time);
		numGens++;
	}

	public String getReport() {
		//check if any cars made it through the grid
		if(numGens>0) {
			//calculate max min and avg
			calculateStatistics();
			String text = "%d Generators created!%nMaximum time: %.2fs%nMinimum time: %.2fs%nAverage time: %.2fs%nVariance: %.2fs";
			return String.format(text, numGens,(double)overallMax/1000,(double)overallMin/1000,calcAvg(),calcVar(numGens));
		}
		else {
			return "No generators were added";
		}

	}
	
	private void calculateStatistics(){
		overallMax = times.get(0);
		overallMin = overallMax;
		for(float time : times) {
			updateMax(time);
			updateMin(time);
			accumulateTime(time);
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
	}
	
	/**
	 * calculate the average time a car needs to pass through the grid (in seconds)
	 * @return double the average time of a car in seconds
	 */
	private float calcAvg() {
		return (overallAvg/numGens)/1000;
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
		//calculate sum(x-xi)^2
		for(float xi : times) {
			xi /= 1000;
			sumOfSquares += Math.pow((xi - x), 2);
		}
		//calculate (1/n)*sum(x-xi)^2
		double variance = sumOfSquares/n;
		return variance/1000;
	}
	
}

import java.util.ArrayList;

/**
 * Main class to start the execution of specification level 2
 * @author 2354160d
 *
 */
public class APSpec2 {
	
	public static void main(String[] args) {
		
		//create a new intersection, size 10x20
		Intersection intersection = new Intersection(10, 20);
		
		//create the ArrayList for the carGenerators
		ArrayList<CarGenerator> carGen = new ArrayList<CarGenerator>();
		
		//create int arrays to store the rows/columns on which generators are to be applied
		int[] rowsEast = {0,1,2,3,4};
		int[] rowsWest = {5,6,7,8,9};
		int[] colsNorth = new int[10];
		int[] colsSouth = new int[10];

		//fill remaining arrays with values 
		for(int i =0 ; i<10;i++) {
			colsNorth[i]=i;
			colsSouth[i]=i+10;
		}
		
		//create vehicle generators
		HorizontalGenerator eastGenerator = new HorizontalGenerator(intersection, rowsEast, 1, 750);
		HorizontalGenerator westGenerator = new HorizontalGenerator(intersection, rowsWest, 3, 800);
		VerticalGenerator northGenerator = new VerticalGenerator(intersection, colsNorth, 0);
		VerticalGenerator southGenerator = new VerticalGenerator(intersection, colsSouth, 2);

		//add generators to the array list
		carGen.add(eastGenerator);
		carGen.add(northGenerator);
		carGen.add(southGenerator);
		carGen.add(westGenerator);

		//create the simulator running 600 times
		Simulator simulator = new Simulator(intersection, 600, carGen);

		//create threads for generators and simulator and start them
		Thread[] t = new Thread[5];
		t[0] = new Thread(simulator);
		t[1] = new Thread(eastGenerator);
		t[2] = new Thread(westGenerator);
		t[3] = new Thread(northGenerator);
		t[4] = new Thread(southGenerator);
		for(int i= 0 ; i < 5 ; i++) {
			t[i].start();;
		}
		
		//wait until all threads have finished
		for(int i= 0 ; i < 5 ; i++) {
			try {
				t[i].join();
			} catch (InterruptedException e) {};
		}

		//get the statistics
		Statistics stats = new Statistics();
		stats.addTime(eastGenerator.reportTotalTravelTime());
		stats.addTime(westGenerator.reportTotalTravelTime());
		stats.addTime(northGenerator.reportTotalTravelTime());
		stats.addTime(southGenerator.reportTotalTravelTime());
		System.out.println(stats.getReport());
		
		//end the program
		System.exit(0);
	}
}

import java.util.ArrayList;

/**
 * Main class to start test the functionality
 * @author 2354160d
 *
 */
public class APSpecTest {
	
	public static void main(String[] args) {
		//create a new intersection, size 10x20
		Intersection intersection = new Intersection(10, 20);
		
		//create the ArrayList for the carGenerators and a Log-class object
		ArrayList<CarGenerator> carGen = new ArrayList<CarGenerator>();
		
		//create a new CarGenerator and add it to the ArraList
		TestGenerator tg1 = new TestGenerator(intersection,true);
		TestGenerator tg2 = new TestGenerator(intersection,false);
		carGen.add(tg1);
		carGen.add(tg2);
		
		//create a simulator running 1000 iterations
		Simulator simulator = new Simulator(intersection, 1500, carGen);

		//create and start threads for the simulator and the carGenerator
		Thread[] t = new Thread[3];
		t[0] = new Thread(simulator);
		t[1] = new Thread(carGen.get(0));
		t[2] = new Thread(carGen.get(1));
		t[0].start();
		t[1].start();
		t[2].start();
		
		//wait until generator and simulator are done and end the program
		try {
			t[0].join();
			t[1].join();
			t[2].join();
			tg1.determineTraffic();
			tg2.determineTraffic();
		} catch (InterruptedException e) {}
		
		
	}
		
}

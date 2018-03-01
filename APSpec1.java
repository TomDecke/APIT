import java.util.ArrayList;

/**
 * Main class to start the execution of specification level 1
 * @author 2354160d
 *
 */
public class APSpec1 {

	public static void main(String[] args)  {
		//create a new intersection, size 10x20
		Intersection intersection = new Intersection(10, 20);
		
		//create the ArrayList for the carGenerators and a Log-class object
		ArrayList<CarGenerator> carGen = new ArrayList<CarGenerator>();
		
		//create a new CarGenerator and add it to the ArraList
		carGen.add(new CarGenerator(intersection));
		
		//create a simulator running 1000 iterations
		Simulator simulator = new Simulator(intersection, 1000, carGen);

		//create and start threads for the simulator and the carGenerator
		Thread[] t = new Thread[2];
		t[0] = new Thread(simulator);
		t[1] = new Thread(carGen.get(0));
		t[0].start();
		t[1].start();

		//wait until all threads have finished to print out the log
		try {
			t[0].join();
			t[1].join();
		} catch (InterruptedException e) {}
	}
}

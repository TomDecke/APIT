/**
 * Main class to start the execution
 * @author Tom
 *
 */
public class Main {

	public static void main(String[] args) {
		Intersection intersection = new Intersection(10, 20);
		Simulator simulator = new Simulator(intersection, 500);
		Thread t1 = new Thread(simulator);
		t1.start();
	}
}

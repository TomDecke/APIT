/**
 * Main class to start the execution
 * @author Tom
 *
 */
public class Main {

	public static void main(String[] args) {
		Intersection intersection = new Intersection(10, 20);
		Simulator simulator = new Simulator(intersection, 200);
		Thread t1 = new Thread(simulator);
		t1.start();
		
		Car c1 = new Car(new MoveSet("NORTH"), "o", 9,12,intersection);
		Thread t2 = new Thread(c1);
		t2.start();
	}
}

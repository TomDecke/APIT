/**
 * Main class to start the execution
 * @author Tom
 *
 */
public class Main {

	public static void main(String[] args) {
		Intersection intersection = new Intersection(10, 20);
		CarGenerator carGen = new CarGenerator(intersection);
		Simulator simulator = new Simulator(intersection, 2000, carGen);
		
		Thread[] t = new Thread[2];
		t[0] = new Thread(simulator);
		t[1] = new Thread(carGen);
		
		t[0].start();
		t[1].start();
		
//		Thread t1 = new Thread(simulator);
//		t1.start();
//		
//		Car c1 = new Car(new MoveSet("SOUTH"), "o", 0,12,intersection);
//		Car c2 = new Car(new MoveSet("EAST"),"-",3,0,intersection);
//		Thread t2 = new Thread(c1);
//		Thread t3 = new Thread(c2);
//		t3.start();
//		t2.start();
	}
}

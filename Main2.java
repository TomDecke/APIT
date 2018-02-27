import java.util.ArrayList;

/**
 * Main for the Specification 2
 * @author Tom
 *
 */
public class Main2 {

	public static void main(String[] args) {
		Intersection intersection = new Intersection(10, 20);
		ArrayList<CarGenerator> carGen = new ArrayList<CarGenerator>();
		for(int i =0 ; i<30;i++) {
			if(i<5) {
				carGen.add(new HorizontalGenerator(intersection, i, 1));
			}
			else if(i < 10){
				carGen.add(new HorizontalGenerator(intersection, i, 3));
			}
			else if( i < 20) {
				carGen.add(i, new VerticalGenerator(intersection, i-20, 0));
			}
			else {
				carGen.add(i, new VerticalGenerator(intersection, i-20, 2));
			}
		}
		
		Simulator simulator = new Simulator(intersection, 1000, carGen);
		
		Thread[] t = new Thread[31];
		t[0] = new Thread(simulator);
		
		for(int i= 1 ; i < 31 ; i++) {
			t[i] = new Thread(carGen.get(i-1));
		}
		for(int i= 0 ; i < 31 ; i++) {
			t[i].start();;
		}
	}
}

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class to represent one field in the grid(intersection)
 * @author 2354160d
 *
 */
public class Field {

	//instance variables to represent the occupation status of the field
	private Car curCar;
	private boolean occupied;
	
	//create lock and condition to manage "one car per field at a time"
	private ReentrantLock fieldLock = new ReentrantLock();
	private Condition available = fieldLock.newCondition();
	
	/**
	 * Constructor to create a field (on creation empty)
	 */
	public Field() {
		curCar = null;
		occupied = false;
	}
	
	/**
	 * Accessor for the car on the field
	 * @return Car the car that is on this field
	 */
	public Car getCurCar() {
		return curCar;
	}
	
	/**
	 * Removes a car from the field
	 */
	public void leaveField() {
		fieldLock.lock();
		try {
			//remove car and set occupied to false
			curCar = null;
			occupied = false;
			//signal all waiting cars that the field is now empty
			available.signalAll();
		}finally {
			fieldLock.unlock();
		}
	}
	
	/**
	 * Moves a car to the field
	 * @param car Car the car to be moved
	 */
	public void occupyField(Car car) {
		fieldLock.lock();
		
		try {
			//wait for the field to become available if occupied
			while(occupied) {
				available.await();
			}
			//occupy the field and remark it in the occupied variable
			occupied = true;
			curCar = car;
			
		}catch (InterruptedException ie) {}
		finally {
			fieldLock.unlock();
		}	
	}	
}

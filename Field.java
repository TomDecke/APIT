import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class to represent one field in the grid(intersection)
 * @author Tom
 *
 */
public class Field {

	private int posRow;
	private int posCol;
	private Car curCar;
	private boolean occupied;
	
	private ReentrantLock fieldLock = new ReentrantLock();
	private Condition available = fieldLock.newCondition();
	
	/**
	 * Constructor to create a field 
	 * @param row int row position in the grid
	 * @param column int column position in the grid
	 */
	public Field(int row, int column) {
		posRow = row;
		posCol = column;
		curCar = null;
		occupied = false;
	}
	
	public void setCurCar(Car car) {
		curCar = car;
	}
	
	public Car getCurCar() {
		return curCar;
	}
	
	/**
	 * Removes car from the field it was on
	 * @param car Car that is currently occupying the field
	 */
	public void leaveField() {
		fieldLock.lock();
		try {
			occupied = false;
			curCar = null;
			available.signalAll();
		}finally {
			fieldLock.unlock();
		}
		
	}
	
	/**
	 * moves a car to another field
	 * @param car the car to be moved
	 */
	public void occupyField(Car car) {
		fieldLock.lock();
		
		try {
			while(occupied) {
				available.await();
			}
			occupied = true;
			curCar = car;
			
		}catch (InterruptedException ie) {}
		finally {
			fieldLock.unlock();
		}
	
	}
	
}

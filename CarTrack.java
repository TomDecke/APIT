import java.util.concurrent.locks.ReentrantLock;

public class CarTrack {
	private static int enter = 0;
	private static int leave = 0;
	
	private static ReentrantLock modifyLock = new ReentrantLock();
	
	public static void incrementEnter() {
		modifyLock.lock();
		enter++;
		modifyLock.unlock();
	}

	public static void incrementLeave() {
		modifyLock.lock();
		leave++;
		modifyLock.unlock();
	}
	
	public static int getDifference() {
		return enter-leave;
	}
}

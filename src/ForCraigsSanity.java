/**
 * 
 * @author Craig Dazey and Nora Husani
 * @course CSE 211
 * @professor Dr. Kiper
 * @date 05/07/15
 * @description ForCraigsSanity is a class for handling the clients, server, and
 *              threads
 */
public class ForCraigsSanity {

	public static void main(String[] args) throws InterruptedException {
		Object lock = new Object();

		synchronized (lock) {
			new Thread(new RunServer()).start();
			lock.wait(250);
			new Thread(new RunClient()).start();
			lock.wait(250);
			new Thread(new RunClient()).start();
		}
	}

	public static class RunServer implements Runnable {
		public void run() {
			String[] args = new String[0];
			BattleShipServer.main(args);
		}
	}

	public static class RunClient implements Runnable {
		public void run() {
			String[] args = new String[0];
			BattleShipClient.main(args);
		}
	}
}

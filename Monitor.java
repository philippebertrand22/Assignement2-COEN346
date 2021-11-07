/**
 * Class Monitor
 * To synchronize dining philosophers.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */
import java.util.Arrays;

public class Monitor
{
	/*
	 * ------------
	 * Data members
	 * ------------
	 */
	DiningPhilosophers DP = new DiningPhilosophers();
	int DNP = DiningPhilosophers.DEFAULT_NUMBER_OF_PHILOSOPHERS;

	boolean chopsticks[] = new boolean [DNP];	//creating an array of chopsticks
	public static void fill(boolean[] chopsticks){	//setting the initial value of all chopsticks to true
		Arrays.fill(chopsticks, true);					//meaning all the chopsticks are available
	}

	boolean talk = true;
	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers)
	{
		// TO DO: set appropriate number of chopsticks based on the # of philosophers

		piNumberOfPhilosophers = DNP;	//we have the same amount of chopsticks than philosophers
	}

	/*
	 * -------------------------------
	 * User-defined monitor procedures
	 * You may need to add more procedures for task 5
	 * -------------------------------
	 */

	/**
	 * Grants request (returns) to eat when both chopsticks/forks are available.
	 * Else forces the philosopher to wait()
	 * @throws InterruptedException
	 */
	public synchronized void pickUp(final int piTID) throws InterruptedException
	{
		if (piTID == 0){
			if(chopsticks[piTID] = true){
				if(chopsticks[piTID + DNP - 1] = true){
						chopsticks[piTID] = false;
						chopsticks[piTID + DNP -1] = false;
						return;
				}
				else{
					//add to the count of the maxHeap 
					wait();
				}
			}
			else{
					//add to the count of the maxHeap
					wait();
			}
		}
		else{
			if (chopsticks[piTID] = true){
				if (chopsticks[piTID - 1] = true){
					chopsticks[piTID] = false;
					chopsticks[piTID - 1] = false;
					return;
				}
				else{
					//add to the count of the maxHeap
					wait();
				}
			}
			else{
					//add to the count of the maxHeap
					wait();
		}
	}
	}

	/**
	 * When a given philosopher's done eating, they put the chopstiks/forks down
	 * and let others know they are available.
	 */
	public synchronized void putDown(final int piTID)
	{
		if(piTID == 0){
			chopsticks[piTID] = true;
			chopsticks[piTID + DNP - 1] = true;
			notifyAll();
		}
		else{
			chopsticks[piTID] = true;
			chopsticks[piTID - 1] = true;
			notifyAll();
		}
		
	}

	/**
	 * Only one philosopher at a time is allowed to philosophy
	 * (while she is not eating).
	 * @throws InterruptedException
	 */
	public synchronized void requestTalk() throws InterruptedException
	{
		if (talk = true){
			talk = false;
			return;
		}
		else{
			wait(); //add to the count of the maxHeap
		}
	}

	/**
	 * When one philosopher is done talking stuff, others
	 * can feel free to start talking.
	 */
	public synchronized void endTalk()
	{
		talk = true;
		notifyAll();
	}
}

// EOF

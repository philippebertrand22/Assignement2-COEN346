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

	int NP = DiningPhilosophers.NumberOfPhilosophers;

	//creating an array of chopsticks
	Boolean[] chopsticks = new Boolean[NP+1];



	boolean talk = true;
	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers)
	{
		for(int k = 0;k< NP; k++){		//setting all values of the array to true meaning available
		chopsticks[k] = true;
		}
		
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
				if(chopsticks[piTID + NP - 1] = true){
						chopsticks[piTID] = false;
						chopsticks[piTID + NP -1] = false;
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
			if (chopsticks[piTID] = true){		//if left chopstick is available check right chopstick
				if (chopsticks[piTID - 1] = true){	//if both chopsticks available pick them up
					chopsticks[piTID] = false;
					chopsticks[piTID - 1] = false;
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
			chopsticks[piTID + NP - 1] = true;
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

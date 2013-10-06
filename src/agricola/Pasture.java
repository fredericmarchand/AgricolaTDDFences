/**
 * 
 */
package agricola;

import java.util.ArrayList;

/**
 * @author Fred
 *
 */
public class Pasture {

	private int turnCreated;
	private ArrayList<Fence> fences;
	
	public Pasture(int turnCreated, ArrayList<Fence> fences) {
		this.setTurnCreated(turnCreated);
		fences = new ArrayList<Fence>();
		this.fences.addAll(fences);
	}
	
	/**
	 * @return the turnCreated
	 */
	public int getTurnCreated() {
		return turnCreated;
	}

	/**
	 * @param turnCreated the turnCreated to set
	 */
	public void setTurnCreated(int turnCreated) {
		this.turnCreated = turnCreated;
	}
	
	public ArrayList<Fence> getFences() {
		return fences;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

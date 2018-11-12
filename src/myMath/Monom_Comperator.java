package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	// ******** add your code below *********
	/**
	 * this function get two monons and compere which one is bigger by the power
	 * @param o1 -the first monom
	 * @param o2 - the second monom
	 * return a positive or negative integer if one is bigger than second or zero if they equal
	 */
	@Override
	public int compare(Monom o1, Monom o2) {
		if(o1.get_power() > o2.get_power()) {
			return -1;
		}
		else if (o1.get_power() < o2.get_power()) {
			return 1;
		}
		
			else{
				return 0;
			}
		}
			
		
	}

	


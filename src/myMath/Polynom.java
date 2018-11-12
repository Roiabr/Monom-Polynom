package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author roi abramovitch && gal hadida
 *
 */
public class Polynom implements Polynom_able{
	private final Monom_Comperator compare = new Monom_Comperator();
	private ArrayList<Monom> poly = new ArrayList<Monom>();
	// ********** add your code below ***********
	public Polynom() {
		// TODO Auto-generated constructor stub
	}
	/**
	 *the function Initializing string to object from the type polynom
	 * @param string - the polynom input from the user
	 */
	public Polynom(String string) {
		// TODO Auto-generated constructor stub
		Monom m1 = new Monom();
		string = string.replaceAll("-", "+-");
		string = string.replaceAll(" ","");
		string = string.replaceAll("  ","");
		String arr1[] = string.split("\\+");
		for (int i = 0; i < arr1.length; i++) { 
			if(arr1[i].equals("")) {   
				i++;
			}
			String s1 = arr1[i];
			m1 = m1.StringMonom(s1);
			this.add(m1);
		}

	}
	/**
	 * the method calculate the result of polynom for x 
	 * @param double x - the x for each Monom that in the Polynom
	 * @return sum - the sum f(x) for the whole Polynom
	 */
	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		double sum = 0;
		Iterator<Monom> iter = this.iteretor(); 
		while (iter.hasNext()) {  //the pointer run above all the Monom in the polynom 
			Monom m1 = iter.next();
			sum = sum + m1.f(x); //sum the value of all Monoms for X 
		}

		return sum;
	}
	/**
	 * this function add a diffrent Polynom to our object Polynom
	 * @param p1 - a differnt Polynom
	 */
	@Override
	public void add(Polynom_able p1) {
		// TODO Auto-generated method stub
		Iterator<Monom> iter = p1.iteretor(); //Initializing the pointer to begin in the start of the list
		while(iter.hasNext()) { 
			this.add(new Monom(iter.next()));
		}
	}
	/**
	 * Checks whether there is a Monom with the same _power ,
	 * If exists , It add the coefficient to Monom that exists. 
	 * If not exists  ,It add m1 at the end of the link ArrayList.
	 * @param m1 - a different Monom that add to Polynom
	 */
	@Override
	public void add(Monom m1) {
		// TODO Auto-generated method stub
		Iterator<Monom> iter = poly.iterator();
		boolean succeed = true;				
		while(iter.hasNext() && succeed) {
			Monom p1 = iter.next();
			if(p1.get_power() == m1.get_power()) { //test if exists monom with the same power in the polynom
				p1.add(m1); 		//add the coefficient of Monom p1&&m1
				if(p1.get_coefficient() == 0) {
					poly.remove(p1);
				}
				succeed = false;
			}
		}
		if(succeed) { 	//if dont exists any monom with the same power ,add the Monom to the end of the list
			poly.add(m1);
		}	
		poly.sort(compare); 
	}
	/**
	 * this function substract two Polynom 
	 * Multiply p1 with Monom =(-1)` and adds it to the Polynom of the function
	 * @param p1 - a different polynom to for the Subtraction of the two Polynom
	 */
	@Override
	public void substract(Polynom_able p1) {
		// TODO Auto-generated method stub
		Polynom_able polySub = p1.copy();
		Iterator<Monom> iter = polySub.iteretor();
		Monom minus1 = new Monom(-1,0); 
		while(iter.hasNext()) {
			iter.next().multiply(minus1); //multiply(change) all the sign Monoms in p1 

		}
		this.add(polySub); //this.Polynom+(-Polynom p1)

	}
	/**
	 * Multiply this Polynom by a different Polynom
	 * @param p1 - different Polynom 
	 */
	@Override
	public void multiply(Polynom_able p1) {
		// TODO Auto-generated method stub
		Polynom_able PolyMul = p1.copy();
		Iterator<Monom> iter1 = p1.iteretor();
		Iterator<Monom> iter = this.iteretor();
		Iterator<Monom> iter2 = PolyMul.iteretor();
		Monom m3;
		boolean flag = true;
		if(p1.isZero() || this.isZero()) {
			Polynom_able PolyMul1 = new Polynom("0");
			this.multiply(PolyMul1);
		}
		while(iter1.hasNext()) {
			Monom m1 = iter1.next();
			while(iter.hasNext() && iter2.hasNext()) {
				if(flag) {
					Monom m0 = iter.next();
					m0.multiply(m1);
				}
				else {
					m3 =new Monom (iter2.next());
					m3.multiply(m1);
					this.add(m3);
				}
			}
			flag = false;
			iter = this.iteretor();
			iter2 = PolyMul.iteretor();
		}


	}
	/**
	 * Test if this Polynom is logically equals to p1.
	 * @param p1 - a different Polynom
	 * @return true if this polynom represents the same function answer of p1
	 */
	@Override
	public boolean equals(Polynom_able p1) {
		// TODO Auto-generated method stub
		Iterator<Monom> iter = this.iteretor(); //pointer that run on this.Polynom
		Iterator<Monom> iter2 = p1.iteretor(); //pointer 2 that run on Polynom p1 
		if(size(this) != size(p1)) 
			return false;
		else {
			while(iter.hasNext() && iter2.hasNext()) {	
				Monom m1 = iter.next();
				Monom m2 = iter2.next();
				if(m1.get_power() > m2.get_power() || m1.get_power() < m2.get_power()) { //test if all the Monoms have the same power
					return false;
				}
				else if(m1.get_coefficient() > m2.get_coefficient() || m1.get_coefficient() < m2.get_coefficient()){
					return false; //test if all the Monoms have the same coefficient

				}
			}
		}
		return true;
	}
	/**
	 * test if the polynom contain Monom that his coefficient = 0
	 * @return true if it empty
	 * @return false if it contains Monoms
	 */
	@Override
	public boolean isZero() {
		Iterator<Monom> iter = this.iteretor();
		if(iter.hasNext()==false) { //check if the Polynom is empty
			return true;
		}

		return false;
	}
	/**
	 * Calculates the function cuts with the axes until approximation of epslion
	 *@param double x0 - starting point
	 *@param double x1  - end point
	 *@param double epslion - coming near the cut with the axes
	 *@return x0 - return the x that close to the eps
	 */

	@Override
	public double root(double x0, double x1, double eps) {

		double y1 = this.f(x0);
		double y2 = this.f(x1);
		if(y1 * y2 > 0) {
			throw new RuntimeException("Error,the points are in the positve side of the axis");
		}
		double del = Math.abs(y1-y2);
		while (del > eps) {
			double xMid = (x0+x1)/2;
			double yMid = this.f(xMid);
			double dir = y1*yMid;
			if(dir < 0) {x1 = xMid;}
			else {x0=xMid;}
			y1 = this.f(x0);
			y2 = this.f(x1);
			del = Math.abs(y1-y2);
		}
		return x0;
	}
	/**
	 * Makes a precise copy of the polynom and saves it in a new loction in the memory
	 */
	@Override
	public Polynom_able copy() {
		// TODO Auto-generated method stub
		Polynom polyNew = new Polynom(); //create new Polynom with new loction in the memory
		Iterator<Monom> iter = this.iteretor();
		while(iter.hasNext()) {
			Monom m1 = iter.next();
			polyNew.add(new Monom(m1));   //add all the monoms from the old list to the new list of polynom
		}
		return polyNew;

	}
	/**
	 * Compute a new Polynom which is the derivative of this Polynom
	 * @return the new polynom after the derivative
	 */
	@Override
	public Polynom_able derivative() {
		// TODO Auto-generated method stub
		Polynom polyNew = new Polynom();
		Monom m2;
		Iterator<Monom> iter = this.iteretor();
		while(iter.hasNext()) {
			Monom m1 = iter.next();
			m2 = new Monom (m1); //copy the data of m1 to m2 
			m2.derivative(); //Send m2 to derivative in Class "Monom"
			polyNew.add(m2); 
		}
		if (polyNew.isZero() == true) {
			System.out.println("The polynom is empty");
			return polyNew;
		}
		else
			return polyNew; //return the new Polynom after the derivative
	}
	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
	 * @param x0 - the start of the range 
	 * @param x1 - the end of the range
	 * @param epsilon - the width of rectangle
	 * @return the approximated area above the x-axis below this Polynom and between the [x0,x1] range.
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		double sum = 0.0;
		double sumTotal = 0.0;

		while(x0 <= x1 ) {  
			if(f(x0)>0) {  //calculate just the rectangle above the axis X
				sum = f(x0) * eps;
			}
			x0 +=  eps; 
			sumTotal += sum;

		}
		return sumTotal;
	}
	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
	 * @param x0 - the start of the range 
	 * @param x1 - the end of the range
	 * @param epsilon - the width of rectangle
	 * @return the approximated area above the x-axis below this Polynom and between the [x0,x1] range.
	 */
	public double areaUnderAxisX(double x0, double x1, double eps) {//this fuction copute the area 
		// TODO Auto-generated method stub
		double sum = 0.0;
		double sumTotal = 0.0;

		while(x0 <= x1 ) {  
			if(f(x0) < 0) {
				sum = -f(x0) * eps;
			}

			x0 +=  eps; 
			sumTotal += sum;

		}
		return sumTotal;
	}
	/**
	 * this function run over each object in the arryList
	 */
	@Override
	public Iterator<Monom> iteretor() {
		// TODO Auto-generated method stub

		return poly.iterator();
	}
	/**
	 * this function get a polynom and check how many elements he has
	 * @param p1 - the Polynom we want check his size
	 * @return - the number of elements of the polynom
	 */
	private int size(Polynom_able p1) {
		Iterator<Monom> iter = p1.iteretor();
		int count = 0;
		while(iter.hasNext()) {
			iter.next(); //counter how much Monoms contians the Polynom
			count++;
		}
		return count;
	}
	/**
	 * this function print the Polynom by string
	 * @return s = a string the contains polynom
	 */
	public String toString() {
		Iterator<Monom> iter = this.iteretor();
		String s = "";

		if(iter.hasNext() ) {
			Monom m0 = iter.next();
			if((m0.get_coefficient() != 0)) {
				s = "" + m0;
				while(iter.hasNext()) {
					Monom m1 = iter.next();
					if(m1.get_coefficient() > 0 )
						s = s + "+" + m1  ;
					else if ((m1.get_coefficient() == 0))
						s = "" +  s;
					else
						s = s + m1  ;
				}
			}
			else
				System.out.println();
		}
		else{
			System.out.println("0.0");
		}

		return s;
	}

}

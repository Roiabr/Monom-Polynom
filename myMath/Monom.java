<<<<<<< HEAD:src/myMath/Monom.java

package myMath;



/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Roi Abramovitch && Gal hadida
 *
 */
public class Monom implements function{



	//****************** Private Methods and Data *****************

	private double _coefficient; // 

	private int _power;
	/**
	 * Default constructor 
	 */
	public Monom() {
		this.set_coefficient(0.0);
		this.set_power(0);
	}
	/**
	 * constructor 
	 * @param a = the coefficient of the Monom
	 * @param b = the power of the monom
	 */ 
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom (Monom ot) {
		this(ot.get_coefficient(),ot.get_power());

	}
	public double get_coefficient() {
		return _coefficient;
	}
	public int get_power() {
		return _power;
	}

	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		this._power = p;
	}
	// ***************** add your code below **********************
	/**
	 *  This interface represents a simple function of type y=f(x), where both y and x are real numbers.
	 *  @param x - the value of x
	 *  @return - the value y of the value x
	 */
	@Override
	public double f(double x) {
		return (this.get_coefficient()) * Math.pow(x, this.get_power());
	}
	/**
	 * this function get a monom and derivative him
	 * @param ot - the Monom we wants to derivative
	 */
	public void derivative() {
		if(get_power() == 0)//if power = 0
			set_coefficient(0.0);
		else {//if power != 0
			set_coefficient(get_power()* get_coefficient());//power * coefficient
			set_power((get_power()-1));//power -1
		}
	}
	/**
	 * this function get a monom and multiply by this monom
	 * @param ot - a diffrent monom for the multiply 
	 */
	public void multiply(Monom ot) {
		this.set_coefficient(this.get_coefficient() * ot.get_coefficient());//this coefficient * ot coefficient
		this.set_power(this.get_power() + ot.get_power());// this power + ot power
	}
	/**
	 * this function add a different monon to this monom by the same power
	 * @param m1 - a different monon for the adding
	 */
	public void add(Monom m1) {
		// TODO Auto-generated method stub
		if (m1 == null)//if monom is empty
		{

			throw new RuntimeException("the monom is empty so cant adding");
		}
		else
		{
			if(this._power == m1._power) {//if this monom has the same power of m1 power
				this.set_coefficient(this._coefficient + m1._coefficient);//this coefficient + m1 coefficient

			}
			else {
				throw new RuntimeException("the monom has a differnt power so cant adding");
			}

		}
	}
	/**
	 * this function get a monom by string and change him to object monom
	 * @param s - the monom string
	 * @return m1 - a new monom from the string monom
	 */
	public Monom StringMonom(String s) {
		Monom m1 = new Monom();
		s.replaceAll(" ", "");//if there is a spaces in the string
		try {		
			if(s.contains("*") && s.contains("x")) { //if the monom string is 3*x
				String[] arr1 = s.split("\\*");//remove the * and put the string in arr
				if(arr1[1].contains("x") && arr1[1].contains("^")) { //if in arr[1] has x^ and a number
					double coeffi = Double.parseDouble(arr1[0]);
					arr1[1] = arr1[1].substring(2);//get the number
					int power = Integer.parseInt(arr1[1]);
					m1 = new Monom(coeffi, power);
				}
				else {// the arr[1] has only x
					double coeffi = Double.parseDouble(arr1[0]);
					m1 = new Monom(coeffi, 1);
				}


			}
			else if(s.contains("x")) {//if the monom string is like 3x^2

				String[] arr = s.split("x");// remove the x and put in arry
				if(arr.length == 1) { // only coefficient no power in the number
					if(!arr[0].equals("") ) {
						if(arr[0].equals("-")) {
							m1 = new Monom(-1,1);
						}
						else {
							double coeffi = Double.parseDouble(arr[0]);
							m1 = new Monom(coeffi,1);	
						}
					}
				}
				else if(arr.length == 2) { //there is two elments in two places in the array 
					if(!arr[0].equals("") && !arr[1].equals("") && !arr[0].equals("-")) {//the two places in array is not empty
						double coeffi = Double.parseDouble(arr[0]); // get the coefficient
						arr[1] = arr[1].substring(1); // get the power
						int power = Integer.parseInt(arr[1]);
						m1 = new Monom(coeffi, power);
					}
					else if(!arr[0].equals("") && !arr[1].equals("") && arr[0].equals("-")) {//if there is a minus before the coefficient

						arr[1] = arr[1].substring(1);
						int power = Integer.parseInt(arr[1]);
						m1 = new Monom(-1, power);
					}

					else if(!arr[0].equals("")) {//if the first place in the array not empty
						if(arr[0].equals("-")) {//if there is minus in the first place in the arry
							m1 = new Monom(-1,1);
						}
						else {
							double coeffi = Double.parseDouble(arr[0]);
							m1 = new Monom(coeffi,1);	
						}
					}
					else if(arr[0].equals("") && !arr[1].equals("")){//if the first place in the array empty and second not
						arr[1] = arr[1].substring(1);
						int power = Integer.parseInt(arr[1]);
						m1 = new Monom(1, power);
					}


				}
				else 

					m1 = new Monom(1,1);

			}
		
			else {
				if(s.contains("0.0") || s.contains("0"))
					m1 = new Monom();
				else {
					double coeffi = Double.parseDouble(s);
					m1 = new Monom(coeffi,0);
				}
			}
		}
		catch(NumberFormatException e1) {
			throw new RuntimeException("Please insert right Monom");
		}
		return m1;
	}
	/**
	 * this function print a stirng with a monom
	 * @return str - the string the represent a monom
	 */
	@Override
	public String toString() {
		String str = "";
		if(this.get_power() > 0) { // if the power is bigger the zero
			str = this.get_coefficient() + "*x^" + this.get_power();
			return str;
		}
		else
			str = this.get_coefficient() + "";
		return str;
	}


}
=======

package myMath;

//import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Roi Abramovitch && Gal hadida
 *
 */
public class Monom implements function{



	//****************** Private Methods and Data *****************

	private double _coefficient; // 

	private int _power;
	/**
	 * Default constructor 
	 */
	public Monom() {
		this.set_coefficient(0.0);
		this.set_power(0);
	}
	/**
	 * constructor 
	 * @param a = the coefficient of the Monom
	 * @param b = the power of the monom
	 */ 
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom (Monom ot) {
		this(ot.get_coefficient(),ot.get_power());

	}
	public double get_coefficient() {
		return _coefficient;
	}
	public int get_power() {
		return _power;
	}

	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		this._power = p;
	}
	// ***************** add your code below **********************
	/**
	 *  This interface represents a simple function of type y=f(x), where both y and x are real numbers.
	 *  @param x - the value of x
	 *  @return - the value y of the value x
	 */
	@Override
	public double f(double x) {
		return (this.get_coefficient()) * Math.pow(x, this.get_power());
	}
	/**
	 * this function get a monom and derivative him
	 * @param ot - the Monom we wants to derivative
	 */
	public void derivative() {
		if(get_power() == 0)//if power = 0
			set_coefficient(0.0);
		else {//if power != 0
			set_coefficient(get_power()* get_coefficient());//power * coefficient
			set_power((get_power()-1));//power -1
		}
	}
	/**
	 * this function get a monom and multiply by this monom
	 * @param ot - a diffrent monom for the multiply 
	 */
	public void multiply(Monom ot) {
		this.set_coefficient(this.get_coefficient() * ot.get_coefficient());//this coefficient * ot coefficient
		this.set_power(this.get_power() + ot.get_power());// this power + ot power
	}
	/**
	 * this function add a different monon to this monom by the same power
	 * @param m1 - a different monon for the adding
	 */
	public void add(Monom m1) {
		// TODO Auto-generated method stub
		if (m1 == null)//if monom is empty
		{

			throw new RuntimeException("the monom is empty so cant adding");
		}
		else
		{
			if(this._power == m1._power) {//if this monom has the same power of m1 power
				this.set_coefficient(this._coefficient + m1._coefficient);//this coefficient + m1 coefficient

			}
			else {
				throw new RuntimeException("the monom has a differnt power so cant adding");
			}

		}
	}
	/**
	 * this function get a monom by string and change him to object monom
	 * @param s - the monom string
	 * @return m1 - a new monom from the string monom
	 */
	public Monom StringMonom(String s) {
		Monom m1 = new Monom();
		s.replaceAll(" ", "");//if there is a spaces in the string
		try {		
			if(s.contains("*") && s.contains("x")) { //if the monom string is 3*x
				String[] arr1 = s.split("\\*");//remove the * and put the string in arr
				if(arr1[1].contains("x") && arr1[1].contains("^")) { //if in arr[1] has x^ and a number
					double coeffi = Double.parseDouble(arr1[0]);
					arr1[1] = arr1[1].substring(2);//get the number
					int power = Integer.parseInt(arr1[1]);
					m1 = new Monom(coeffi, power);
				}
				else {// the arr[1] has only x
					double coeffi = Double.parseDouble(arr1[0]);
					m1 = new Monom(coeffi, 1);
				}


			}
			else if(s.contains("x")) {//if the monom string is like 3x^2

				String[] arr = s.split("x");// remove the x and put in arry
				if(arr.length == 1) { // only coefficient no power in the number
					if(!arr[0].equals("") ) {
						if(arr[0].equals("-")) {
							m1 = new Monom(-1,1);
						}
						else {
							double coeffi = Double.parseDouble(arr[0]);
							m1 = new Monom(coeffi,1);	
						}
					}
				}
				else if(arr.length == 2) { //there is two elments in two places in the array 
					if(!arr[0].equals("") && !arr[1].equals("") && !arr[0].equals("-")) {//the two places in array is not empty
						double coeffi = Double.parseDouble(arr[0]); // get the coefficient
						arr[1] = arr[1].substring(1); // get the power
						int power = Integer.parseInt(arr[1]);
						m1 = new Monom(coeffi, power);
					}
					else if(!arr[0].equals("") && !arr[1].equals("") && arr[0].equals("-")) {//if there is a minus before the coefficient

						arr[1] = arr[1].substring(1);
						int power = Integer.parseInt(arr[1]);
						m1 = new Monom(-1, power);
					}

					else if(!arr[0].equals("")) {//if the first place in the array not empty
						if(arr[0].equals("-")) {//if there is minus in the first place in the arry
							m1 = new Monom(-1,1);
						}
						else {
							double coeffi = Double.parseDouble(arr[0]);
							m1 = new Monom(coeffi,1);	
						}
					}
					else if(arr[0].equals("") && !arr[1].equals("")){//if the first place in the array empty and second not
						arr[1] = arr[1].substring(1);
						int power = Integer.parseInt(arr[1]);
						m1 = new Monom(1, power);
					}


				}
				else 

					m1 = new Monom(1,1);

			}
		
			else {
				if(s.contains("0.0") || s.contains("0"))
					m1 = new Monom();
				else {
					double coeffi = Double.parseDouble(s);
					m1 = new Monom(coeffi,0);
				}
			}
		}
		catch(NumberFormatException e1) {
			throw new RuntimeException("Please insert right Monom");
		}
		return m1;
	}
	/**
	 * this function print a stirng with a monom
	 * @return str - the string the represent a monom
	 */
	@Override
	public String toString() {
		String str = "";
		if(this.get_power() > 0) { // if the power is bigger the zero
			str = this.get_coefficient() + "*x^" + this.get_power();
			return str;
		}
		else
			str = this.get_coefficient() + "";
		return str;
	}


}
>>>>>>> 8da464b9bd44425c256af5a03c2c5b40d0784507:myMath/Monom.java

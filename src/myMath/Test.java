package myMath;

import java.util.Iterator;


public class Test {
//Hello github
	public static void main(String[] args) {
		System.out.println("\nTest\n");
		test();
		
	}
	public static void test() {
		Polynom_able p1 = new Polynom();
		Polynom_able p2 = new Polynom();
		p1.add(new Monom(-4,2));
		p1.add(new Monom(1,3));
		p2.add(new Monom(1, 1));
		p2.add(new Monom(-5, 0));
		Polynom_able p4 = new Polynom("-x^3-x^2+45*x^4");
		Polynom_able p6 = new Polynom("x^35");
		System.out.println("\npolynom as string: " + p4);
		Polynom_able p5 = new Polynom("x^3+3x^2-3x-6");
		System.out.println("\npolynom as string2: " + p5);
		Polynom_able p8 = new Polynom("3");		
		Polynom_able p9 = new Polynom("4x^3-x");	
		System.out.println("derivative:" + p8.derivative());
		System.out.println(p9);
		Polynom_able p10 = new Polynom("x^2-4");
		System.out.println("root");
		System.out.println("root:" + p10.root(-1, 5, 0.001));
		System.out.println("polynom as Monom:" + p2);
		System.out.println("\nf(x)\n");
		System.out.println("p6.f(x):" + p6.f(4));
		System.out.println("\narea\n");
		double eps = 0.0001;
		System.out.println("\narea: " + p2.area(1, 35, eps));
		System.out.println("\nis zero:"+p2.isZero());
		System.out.println("\nSubstract");
		System.out.println(p1);
		System.out.println(p2);
		p1.substract(p2);
		System.out.println("\nsubstract:" + p1);
		System.out.println("\nmultiply\n");
		Polynom_able mul = p1.copy();
		mul.multiply(p1);
		System.out.println("multiply: " + mul);
		System.out.println("\nroot\n");
		double eps1 = 0.0001;
		System.out.println(p2.root(-10, 8, eps1));
		
		System.out.println("\npolynom test\n");
		Polynom_able p3 = new Polynom();
		p3.add(new Monom(-5.6,0));
		p3.add(new Monom(3,2));
		p3.add(new Monom(-6,1));
		p3.add(new Monom(4.3,3));
		p3.add(new Monom(2,7));
		p3.add(new Monom(33.2,8));
		p3.add(new Monom(5.2,12));
		System.out.println("polynom is: "+p3);
		System.out.println("\ncopy\n");
		p1 = p3.copy();
		System.out.println("get same polynom:");
		System.out.println("polynom is: "+p1);
		System.out.println("equals 1");
		p3.add(new Monom(0.2,12));
		System.out.println(p1);
		System.out.println(p3);
		System.out.println("get true:"+p1.equals(p3));
		
		p3.add(new Monom(-0.2,12));
		System.out.println(p1);
		System.out.println(p3);
		System.out.println("get true:"+p1.equals(p3));
		System.out.println("\niterator test\n");
		Iterator<Monom> iterator = p1.iteretor();
		while(iterator.hasNext()){
			Monom m1 = iterator.next();
			System.out.print("coefficient:" + m1.get_coefficient()  + ", ");
			System.out.println("powers:" + m1.get_power());
			
		}
	}
}
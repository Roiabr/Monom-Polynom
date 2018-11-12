package TestJunit;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import myMath.Monom;

class MonomTest {


	@Test
	void testMonomDoubleInt() {
		double cof = 2.5;
		int power = 3;
		Monom m1 = new Monom(cof,power);
		Monom m2 = new Monom(2.5,3);
		assertEquals(m2+"",m1+"");

	}

	@Test
	void testMonomMonom() {
		Monom m1 = new Monom(3,2);
		Monom m2 = new Monom(m1);
		assertEquals(m1+"",m2+"");

	}

	@Test
	void testGet_coefficient() {
		double cof = -2.4;
		Monom m1 = new Monom(cof,2);
		assertEquals(cof,m1.get_coefficient());
		if(cof!= m1.get_coefficient()) {
			fail("JUnit fail: Somthing is wrong with the get_coefficient() method");
			}
	}

	@Test
	void testGet_power() {
		int power = 11;
		Monom m1 = new Monom(1.22,power);
		if(power!= m1.get_power()) {
			fail("Somthing is wrong with the get_power() method");
		}
	}

	@Test
	void testF() {
		double cof = -2.4;
		int pow = 3;
		double x = -1.2;
		Monom m1 = new Monom(cof,pow);
		double ev = cof*Math.pow(x, pow);
		assertEquals(ev,m1.f(x));
	}

	@Test
	void testDerivative() {
		Monom m1 = new Monom(3,3);
		m1.derivative();
		Monom m2 = new Monom(9,2);
		assertEquals(m2+"", m1+"");
	}

	@Test
	void testMultiply() {
		double cof1 = -2.4, cof2 = 1.3;
		int pow1 = 2, pow2 = 5;
		Monom m1 = new Monom(cof1,pow1);
		Monom m2 = new Monom(cof2,pow2);
		m1.multiply(m2);
		assertEquals(m1.get_coefficient(), cof1*cof2);
		assertEquals(m1.get_power(), pow1+pow2);
	}

	@Test
	void testAdd() {
		double cof1 = -2.4, cof2 = 1.3;
		int pow = 6;
		Monom m1 = new Monom(cof1,pow);
		Monom m2 = new Monom(cof2,pow);
		m1.add(m2);
		assertEquals(m1.get_coefficient(), cof1+cof2);
	}

	@Test
	void testStringMonom() {
		Monom m1  = new Monom();
		m1 = m1.StringMonom("1.3x^2");
		Monom m2  = new Monom(1.3,2);
		assertEquals(m2+"",m1+"");
		
	}

	@Test
	void testToString() {
		Monom m1 = new Monom(3.2, 3);
		String s = "3.2*x^3";
		String r = m1.toString();
		assertEquals(s, r);
	}



}

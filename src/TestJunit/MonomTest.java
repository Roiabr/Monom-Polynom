package TestJunit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

import myMath.Monom;

class MonomTest {


	@Test
	void testMonomDoubleInt() {
		
		boolean threw = false;
		try {
			
			Monom m1 = new Monom(0,-3);
			
		}
		catch (RuntimeException e){
			threw = true;
		}
		if(!threw) {
			fail("should threw because has a worng input");
		}
		else {
			Monom m1 = new Monom(2.5,3);
			Monom m2 = new Monom(2.5,3);
			assertEquals(m2+"",m1+"");
		}
		
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
		assertEquals(cof+"",m1.get_coefficient()+"");
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
		
		Monom m1 = new Monom(2,3);
		double x = 1.0;
		assertEquals("2.0",""+m1.f(x));
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
		assertEquals(m1.get_coefficient()+"", ""+cof1*cof2);
		assertEquals(m1.get_power(), pow1+pow2);
	}

	@Test
	void testAdd() {
		
		Monom m3 = new Monom(2,2);
		Monom m5 = new Monom(5,2);
		m3.add(m5);
		assertEquals("7.0*x^2", m3+"");
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

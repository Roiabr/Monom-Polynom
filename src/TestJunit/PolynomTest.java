package TestJunit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import myMath.Polynom;
import myMath.Polynom_able;


class PolynomTest {
	Polynom p1_2  = new Polynom("217.2x^3+2x+2");
	Polynom p3  = new Polynom("x^2-4");
	Polynom p2  = new Polynom("213.2x^3+2x^0");
	Polynom p1  = new Polynom("4*x^3+2x^1");
	Polynom p0  = new Polynom("0");
	Polynom p10  = new Polynom("x^3-4");


	@Test
	void testPolynom() {
		
		assertEquals("",p0.toString());
	}

	@Test
	void testPolynomString() {
		boolean threw = false;
		try {
			Polynom p32  = new Polynom("x^2-4x^");
		}
		catch (RuntimeException e){
			threw = true;
		}
		if(!threw) {
			fail("should threw because has a worng input");
		}
		else {
			Polynom p32  = new Polynom("x^2-4x^1");
			String str="1.0*x^2-4.0*x^1";
			assertEquals(str, p32+"");
		}
		
	}

	@Test
	void testF() {
		String p3f4="264.0";
		assertEquals(p3f4, ""+	p1.f(4));
	}

	@Test
	void testAddPolynom_able() {
		p1.add(p2);
		assertEquals(""+p1_2, ""+p1);
	}

	

	@Test
	void testSubstract() {
		p2.substract(p1);
		Polynom p2_1 = new Polynom("209.2x^3+2-2x");
		assertEquals(""+p2_1, ""+p2);	}

	@Test
	void testMultiply() {
		p1.multiply(p0);
		assertEquals("", ""+p1);
	}


	@Test
	void testEqualsPolynom_able() {
		
		Polynom pequals  = new Polynom("4*x^3+2x^1");

		assertEquals(true, p1.equals(pequals));
}

	@Test
	void testIsZero() {
			assertEquals("",""+p0);	
	}

	@Test
	void testRoot() {
		double x=p3.root(-1, 5, 0.01);
		System.out.println(x);
		assertEquals("1.994140625" , ""+p3.root(-1, 5, 0.01));
	}

	@Test
	void testCopy() {
		Polynom_able pcopy = p1.copy();
		assertEquals("4.0*x^3+2.0*x^1", pcopy+"");
	}

	@Test
	void testDerivative() {
		Polynom p1d = new Polynom("12x^2+2");
		assertEquals(""+p1d, ""+p1.derivative());	}

	@Test
	void testArea() {
		assertEquals("0.7423127900000117", p10.area(-2, 2,0.01)+"");
	}


}

import static org.junit.Assert.*;

import org.junit.Test;

public class ResultEvaluatorTest {
	ResultEvaluator resultEvalautor = new ResultEvaluator();

	@Test
	public void evaluate_NullExpression_ThrowsException() {
		// Arrange
		boolean isThrown = false;
		// Act
		try {
			resultEvalautor.evaluate("");
		} catch (Exception e) {
			isThrown = true;
		}
		// Assert
		assertTrue(isThrown);

	}

	@Test(expected = Exception.class)
	public void evaluate_DivideByZero_ThrowsException() throws Exception {
		resultEvalautor.evaluate("7/0");
	}

	@Test
	public void evaluate_InvalidExpression_ThrowsException() {
		// Arrange
		boolean isThrown = false;
		// Act
		try {
			resultEvalautor.evaluate("3+8=");
			resultEvalautor.evaluate("6/2-8+3-");
			resultEvalautor.evaluate("10%2");
		} catch (Exception e) {
			isThrown = true;
		}
		// Assert
		assertTrue(isThrown);
	}
	
	@Test
	public void evaluate_CheckDivision_ExpectedResult2() throws Exception {

		float expectedResult = 2;
		float actualResult = resultEvalautor.evaluate("10/5");
		assertEquals(expectedResult, actualResult, 0.001);

	}

	@Test
	public void evaluate_CheckDecimalDivision_ExpectedResult() throws Exception {

		double expectedResult = 3.3333;
		double actualResult = resultEvalautor.evaluate("10/3");
		assertEquals(expectedResult, actualResult, 0.001);

	}
	
	@Test
	public void evaluate_CheckMultiplication_ExpectedResult6() throws Exception {

		float expectedResult = 6;
		float actualResult = resultEvalautor.evaluate("2*3");
		assertEquals(expectedResult, actualResult, 0.001);

	}
	
	@Test
	public void evaluate_CheckAddition_ExpectedResult9() throws Exception {

		float expectedResult = 9;
		float actualResult = resultEvalautor.evaluate("3+6");
		assertEquals(expectedResult, actualResult, 0.001);

	}
	
	@Test
	public void evaluate_CheckSubtraction_ExpectedResult16() throws Exception {

		float expectedResult = 16;
		float actualResult = resultEvalautor.evaluate("19-3");
		assertEquals(expectedResult, actualResult, 0.001);

	}
	
	@Test
	public void evaluate_CheckHybridExpression_ExpectedResult25() throws Exception {

		float expectedResult = 25;
		float actualResult = resultEvalautor.evaluate("12*2-6/2+4");
		assertEquals(expectedResult, actualResult, 0.001);

	}
	
	@Test
	public void evaluate_CheckNegativexpression_ExpectedResultNegative18() throws Exception {

		float expectedResult = -18;
		float actualResult = resultEvalautor.evaluate("-3-6-9");
		assertEquals(expectedResult, actualResult, 0.001);

	}
	
	@Test
	public void evaluate_AcceptPI_ReplaceByValue() throws Exception {

		double expectedResult = 3.1415927;
		double actualResult = resultEvalautor.evaluate("PI");
		assertEquals(expectedResult, actualResult, 0.001);

	}
	
	@Test
	public void evaluate_HybridExpressionwithPI_ReplaceByValue() throws Exception {

		double expectedResult = 2.142;
		double actualResult = resultEvalautor.evaluate("10/2+PI-3*2");
		assertEquals(expectedResult, actualResult, 0.001);

	}
	
	@Test
	public void evaluate_AcceptE_ExpectedResultByReplacePIValue() throws Exception {

		double expectedResult = 2.71828;
		double actualResult = resultEvalautor.evaluate("E");
		assertEquals(expectedResult, actualResult, 0.001);

	}
	
	@Test
	public void evaluate_HybridExpressionwithE_ExpectedResultByReplacePIValue() throws Exception {

		double expectedResult = 1.71828;
		double actualResult = resultEvalautor.evaluate("10/2+E-3*2");
		assertEquals(expectedResult, actualResult, 0.001);

	}

}

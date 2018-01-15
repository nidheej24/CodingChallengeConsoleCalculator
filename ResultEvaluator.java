import java.util.Stack;

public class ResultEvaluator implements IResultEvaluator {
	public float evaluate(String expression) throws Exception {
		try {
			char[] tokens = expression.toCharArray();
			boolean hasOperator = false;
			Stack<Float> values = new Stack<Float>();

			Stack<Character> operators = new Stack<Character>();
			for (int placeHolder = 0; placeHolder < tokens.length; placeHolder++) {
				StringBuffer sbuf = new StringBuffer();
				boolean isNegative = false;

				if (isNumberNegative(tokens, hasOperator, placeHolder)) {
					sbuf.append(tokens[placeHolder]);
					isNegative = true;
				}
				hasOperator = false;
				if (isNegative == true) {
					placeHolder++;
				}
				if (hasNumber(tokens, placeHolder)) {
					int temp = placeHolder;
					while (temp < tokens.length && tokens[temp] >= '0' && tokens[temp] <= '9')
						sbuf.append(tokens[temp++]);
					placeHolder = temp - 1;
					values.push(Float.parseFloat(sbuf.toString()));
				}else
				if(tokens[placeHolder] == 'P' && tokens[placeHolder+1] == 'I'){
					values.push((float)3.1415927);
					placeHolder++;
				}else
				if(tokens[placeHolder] == 'E'){
					values.push((float)2.71828);
				}
				else if (hasRequiredOperators(tokens, placeHolder)) {

					while (!operators.empty() && hasPrecedence(tokens[placeHolder], operators.peek()))
						values.push(applyOpeartion(operators.pop(), values.pop(), values.pop()));
					hasOperator = true;
					operators.push(tokens[placeHolder]);
				} else {
					
					throw new Exception("Invalid Expression!! ");
				}
			}
			while (!operators.empty())
				values.push(applyOpeartion(operators.pop(), values.pop(), values.pop()));
			return values.pop();

		} catch (Exception e) {
			
			throw e;
		}
	}

	private boolean isNumberNegative(char[] tokens, boolean hasOperator, int placeHolder) {
		return tokens[placeHolder] == '-' && (placeHolder == 0 || hasOperator == true);
	}

	private boolean hasNumber(char[] tokens, int placeHolder) {
		return tokens[placeHolder] >= '0' && tokens[placeHolder] <= '9';
	}

	private boolean hasRequiredOperators(char[] tokens, int placeHolder) {
		return tokens[placeHolder] == '+' || tokens[placeHolder] == '-' || tokens[placeHolder] == '*' || tokens[placeHolder] == '/';
	}

	private boolean hasPrecedence(char operator1, char operator2) {

		if ((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-'))
			return false;
		else
			return true;
	}

	private float applyOpeartion(char operator, float input2, float input1) {
		switch (operator) {
		case '+':
			return input1 + input2;
		case '-':
			return input1 - input2;
		case '*':
			return input1 * input2;
		case '/':
			if (input2 == 0)
				throw new UnsupportedOperationException("Cannot divide by zero");
			return input1 / input2;
		}
		return 0;
	}

}

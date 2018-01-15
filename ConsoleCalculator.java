import java.text.DecimalFormat;
import java.util.Scanner;

public class ConsoleCalculator {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		IResultEvaluator evaluator = new ResultEvaluator();

		while (true) {
			Scanner scanInput = new Scanner(System.in);
			String expression = scanInput.nextLine();

			if (expression.equalsIgnoreCase("exit")) {
				System.exit(1);
			} else {
				try {
					DecimalFormat format = new DecimalFormat();
					format.setDecimalSeparatorAlwaysShown(false);

					System.out.println(format.format(evaluator.evaluate(expression)));

				} catch (Exception e) {
					System.out.println(
							e.getMessage() == null ? "Please retry with a valid expression.." : e.getMessage());
				}

			}
		}
	}
}
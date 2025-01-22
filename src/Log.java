// itay alter 206132284
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Log class represents the logarithm operation in an arithmetic expression.
 * It extends the BinaryExpression class and implements the Expression interface.
 * It provides functionality to evaluate logarithm, assign values to variables,
 * differentiate with respect to variables, simplify the expression, and convert
 * it to a string representation. It includes error handling for undefined
 * logarithms, such as when the base or argument is equal to or smaller than 0,
 * or when the base is 1. The Log class also includes a helper method to
 * calculate the logarithm using the base-change formula.
 */
public class Log extends BinaryExpression implements Expression {

    /**
     * Constructs a Log expression with the given operands.
     *
     * @param x the expression representing the base of the logarithm
     * @param y the expression representing the argument of the logarithm
     */
    public Log(Expression x, Expression y) {
        super(x, y);
    }

    /**
     * Evaluates the logarithm expression using the given variable assignment.
     * The method checks if the base and argument meet the conditions for a
     * defined logarithm:
     * The base and argument must be greater than 0.
     * If the base is 1, the argument must also be 1 to avoid an undefined
     * logarithm.
     * If any of these conditions is not met, an exception is thrown with an
     * appropriate error message.
     *
     * @param assignment a map of variable names to values
     * @return the result of the logarithm expression
     * @throws Exception if the base or the argument of the logarithm is less
     * than or equal to 0, or if the base is 1 and the argument is not 1
     * (undefined logarithm)
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double logResult, base, argument;
        // evaluate the base and argument expressions using the assignment
        base = super.getOperands().get(0).evaluate(assignment);
        argument = super.getOperands().get(1).evaluate(assignment);
        /*
         * check if the base and argument values meet the conditions for a
         *  defined logarithm
         */
        if (base <= 0 || argument <= 0 || (base == 1 && argument != 1)) {
            throw new Exception("the logarithm operation is undefined for"
                    + " numbers that are equal or smaller than 0");
        }
        // calculate the logarithm result
        logResult = log(base, argument);
        return logResult;
    }

    /**
     * Calculates the logarithm of a given number with a specified base.
     * The method uses the formula log(y) / log(x) to compute the logarithm.
     *
     * @param x the base of the logarithm
     * @param y the number for which the logarithm is calculated
     * @return the result of the logarithm calculation
     */
    public double log(double x, double y) {
        return Math.log(y) / Math.log(x);
    }
    /**
     * Evaluates the logarithm expression. The method checks if the base and
     * argument meet the conditions for a defined logarithm:
     * The base and argument must be greater than 0.
     * If the base is 1, the argument must also be 1 to avoid an undefined
     * logarithm.
     * If any of these conditions is not met, an exception is thrown with an
     * appropriate error message.
     *
     * @return the result of evaluating the logarithm expression
     * @throws Exception if the logarithm operation is undefined for the given
     * operands
     */
    @Override
    public double evaluate() throws Exception {
        double logResult, base, argument;
        // evaluate the base and argument expressions using the assignment
        base = super.getOperands().get(0).evaluate();
        argument = super.getOperands().get(1).evaluate();
        /*
         * check if the base and argument values meet the conditions for a
         *  defined logarithm
         */
        if (base <= 0 || argument <= 0 || (base == 1 && argument != 1)) {
            throw new Exception("the logarithm operation is undefined for"
                    + " numbers that are equal or smaller than 0, and for base 1");
        }
        // calculate the logarithm result
        logResult = log(base, argument);
        return logResult;
    }
    /**
     * Assigns a new expression to the variable in the logarithm expression.
     * Creates a new logarithm expression with the assigned variable and
     * expression.
     *
     * @param var the variable to be assigned
     * @param expression the new expression to assign to the variable
     * @return a new logarithm expression with the assigned variable and
     * expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        List<Expression> newOperands = new ArrayList<>();
        // Assign the variable and expression to each operand
        for (Expression operand : super.getOperands()) {
            newOperands.add(operand.assign(var, expression));
        }
        // Create a new logarithm expression with the assigned operands
        return new Log(newOperands.get(0), newOperands.get(1));
    }
    /**
     * Calculates the derivative of the logarithm expression with respect to the
     * specified variable. The derivation is operated according to :
     * ((ln(f)*f*g') - (ln(g)*g*f')) / (g*f*(ln(f))^2)
     *
     * @param var the variable with respect to which the derivative is calculated
     * @return the derivative of the logarithm expression
     */
    @Override
    public Expression differentiate(String var) {
        Expression base = super.getOperands().get(0);
        Expression argument = super.getOperands().get(1);
        // Calculate the derivatives of the base and argument expressions
        Expression baseDerivative = base.differentiate(var);
        Expression argumentDerivative = argument.differentiate(var);
        // Calculate the numerator of the derivative expression
        Expression numerator = new Minus(new Mult(new Mult(new Log(new Var("e"),
                base), base), argumentDerivative), new Mult(new Mult(new Log(
                new Var("e"), argument), argument), baseDerivative));
        // Calculate the denominator of the derivative expression
        Expression denominator = new Mult(new Mult(base, argument), new Pow(
                new Log(new Var("e"), base), new Num(2)));
        // Create a new division expression with the numerator and denominator
        return new Div(numerator, denominator);
    }
    /**
     * This method simplifies the logarithm expression by evaluating it if both
     * operands are numbers, checking for logarithmic identities, or recursively
     * simplifying the operands. It returns the simplified expression.
     *
     * @return The simplified expression.
     */
    public Expression simplify() {
        Expression x = super.getOperands().get(0);
        Expression y = super.getOperands().get(1);
        // Evaluate the logarithm expression if both operands are numbers
        if (x instanceof Num && y instanceof Num) {
            try {
                double logResult = log(x.evaluate(), y.evaluate());
                return new Num(logResult);
                // Handle any exceptions that may occur during evaluation
            } catch (Exception e) {
            }
        }
        // Check for logarithmic identity: log(x, x) = 1
        if (x instanceof Expression && y instanceof Expression) {
            try {
                if (y.toString().equals(x.toString())) {
                    return new Num(1);
                }
             // Handle any exceptions that may occur during evaluation
            } catch (Exception e) {
            }
        }
        // Recursively simplify the operands
        return new Log(x.simplify(), y.simplify());
    }


    /**
     * Returns a string representation of the logarithm expression.
     *
     * @return The string representation of the logarithm expression.
     */
    public String toString() {
        double logResult = 0;
        String x = super.getOperands().get(0).toString();
        String y = super.getOperands().get(1).toString();
        return "log(" + x + ", " + y + ")";
    }
}

// itay alter 206132284
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Sin class represents the sine function in an arithmetic expression. It
 * extends the UnaryExpression class and implements the Expression interface.
 * The class provides methods to evaluate the sine function, assign values to
 * variables, differentiate the expression, simplify it, and convert it to a
 * string representation.
 */
public class Sin extends UnaryExpression implements Expression {
    /**
     * Constructs a new sine expression with the given operand.
     *
     * @param x the operand of the sine expression
     */
    public Sin(Expression x) {
        super(x);
    }

    /**
     * Evaluates the sine expression with the given variable assignments.
     *
     * @param assignment a map containing variable assignments
     * @return the result of evaluating the sine expression
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double x = Math.toRadians(super.getOperands().get(0).evaluate(assignment));
        return Math.sin(x);
    }

    /**
     * This method evaluates the sine function for the given expression.
     *
     * @return the value of the sine function for the given expression.
     * @throws Exception if the expression cannot be evaluated or if there is a math error.
     */
    @Override
    public double evaluate() throws Exception {
        double x = Math.toRadians(super.getOperands().get(0).evaluate());
        return Math.sin(x);
    }

    /**
     * This method assigns a new expression to the variable in the sin function.
     *
     * @param var the variable to be assigned a new expression
     * @param expression the new expression to be assigned to the variable
     * @return a new Sin expression with the variable replaced by the new
     * expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        List<Expression> newOperands = new ArrayList<>();
        // Assign the new expression to the variable in the operand
        newOperands.add(super.getOperands().get(0).assign(var, expression));
        // Create a new Sin expression with the updated operand
        return new Sin(newOperands.get(0));
    }

    /**
     * Returns the derivative of this Sin expression with respect to the given
     * variable. According to: (sin(g(x)))' = cos(g(x)) * g'(x).
     *
     * @param var the variable to differentiate by.
     * @return the derivative of this Sin expression.
     */
    public Expression differentiate(String var) {
        Expression operand = super.getOperands().get(0);
        // Differentiate the operand with respect to the variable
        Expression operandDiff = operand.differentiate(var);
        Expression cos = new Cos(operand);
        // Multiply the cosine expression by the derivative of the operand
        return new Mult(cos, operandDiff);
    }

    /**
     * This method simplifies the Sin expression by evaluating it if possible.
     * If the expression can be evaluated to a numerical value, a Num expression
     * is returned. Otherwise, the Sin expression is simplified by recursively
     * simplifying its operand.
     *
     * @return a simplified version of the Sin expression.
     */
    @Override
    public Expression simplify() {
        Expression x = super.getOperands().get(0);
        // Try to evaluate the Sin expression
            try {
                double sinResult = this.evaluate();
                return new Num(sinResult);
            } catch (Exception e) {
            }
        // If evaluation fails, simplify the operand expression
        return new Sin(x.simplify());
    }

    /**
     * Returns a string representation of the Sin expression.
     *
     * @return the string representation of the Sin expression.
     */
    public String toString() {
        String x = super.getOperands().get(0).toString();
        return "sin(" + x + ")";
    }
}

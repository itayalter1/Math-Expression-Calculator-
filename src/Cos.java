// itay alter 206132284
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Cos class represents the cosine function in an arithmetic expression. It
 * extends the UnaryExpression class and implements the Expression interface. It
 * provides functionality to evaluate the cosine function, assign values to
 * variables, differentiate with respect to variables, simplify the expression,
 * and convert it to a string representation.
 */
public class Cos extends UnaryExpression implements Expression {
    /**
     * Constructs a new Cos object with the specified expression as its operand.
     *
     * @param x the expression to be used as the operand
     */
    public Cos(Expression x) {
        super(x);
    }

    /**
     * This method calculates the cosine value of the expression using
     * the provided variable assignments. It takes a Map parameter called
     * assignment, which represents the variables and their assigned values.
     * If an error occurs during the evaluation process, an Exception is thrown,
     * indicating a failure.
     *
     * @param assignment a map containing variable assignments for evaluation
     * @return the cosine value of the expression with the given variable assignments
     * @throws Exception if an error occurs during evaluation
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        // Evaluate the operand expression and convert the result to radians
        double x = Math.toRadians(super.getOperands().get(0).evaluate(assignment));
        return Math.cos(x);
    }

    /**
     * This method evaluates the cosine function on the operand of this cosine
     * expression. It throws an exception if the operand cannot be evaluated.
     *
     * @return The cosine value of the expression
     * @throws Exception If an error occurs during evaluation
     */
    @Override
    public double evaluate() throws Exception {
        // Evaluate the operand expression and convert the result to radians
        double x = Math.toRadians(super.getOperands().get(0).evaluate());
        return Math.cos(x);
    }

    /**
     * This method assigns a new value to a variable in the expression and
     * returns the modified expression.
     *
     * @param var        the variable to be assigned a new value.
     * @param expression the new value assigned to the variable.
     * @return the modified expression with the new value assigned to the
     * specified variable.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        List<Expression> newOperands = new ArrayList<>();
        newOperands.add(super.getOperands().get(0).assign(var, expression));
        return new Cos(newOperands.get(0));
    }

    /**
     * This method computes the derivative of the expression with respect to the
     * specified variable.\, by applying the chain rule for the cosine function.
     *
     * @param var the variable with respect to which the derivative is computed.
     * @return the derivative of the expression with respect to the specified
     * variable.
     */
    public Expression differentiate(String var) {
        Expression operand = getOperands().get(0);
        Expression diffOperand = operand.differentiate(var);
        Expression sinOperand = new Sin(operand);
        Expression diffCos = new Mult(sinOperand, diffOperand);
        return new Neg(diffCos);
    }

    /**
     * This method simplifies the expression by evaluating it if possible or
     * applying algebraic simplifications. If the operand is a number, evaluates
     * it and returns a new number. Otherwise, returns a new cosine function
     * with the simplified operand.It returns a simplified expression.
     *
     * @return a simplified expression.
     */
    @Override
    public Expression simplify() {
        Expression x = super.getOperands().get(0);
        try {
            // If operand is a number, evaluates it and returns a new number
            double cosResult = this.evaluate();
            return new Num(cosResult);
        } catch (Exception e) {
        }
        /*
         * If operand is not a number, returns a new cosine function with the
         *  simplified operand
         */
        return new Cos(x.simplify());
    }

    /**
     * Returns a string representation of this cosine function.
     *
     * @return a string representation of this cosine function.
     */
    public String toString() {
        String x = super.getOperands().get(0).toString();
        return "cos(" + x + ")";
    }
}

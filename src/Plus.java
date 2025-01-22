import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Plus class represents the addition operation in an arithmetic expression.
 * It extends the BinaryExpression class and implements the Expression interface.
 * The class provides methods to evaluate the addition, assign values to
 * variables, differentiate the expression, simplify it, and convert it to a
 * string representation.
 */
public class Plus extends BinaryExpression implements Expression {

    /**
     * Constructs a Plus expression with the given operands.
     *
     * @param x The first operand of the Plus expression
     * @param y The second operand of the Plus expression
     */
    public Plus(Expression x, Expression y) {
        super(x, y);
    }

    /**
     * This method calculates the sum of the operands in a Plus expression,
     * considering the provided variable assignments. The method returns the
     * final sum as the result of evaluating the Plus expression. If any error
     * occurs during the evaluation process, an exception is thrown.
     *
     * @param assignment A map containing variable assignments
     * @return The result of evaluating the Plus expression
     * @throws Exception if there is an error during evaluation
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double sum = 0;
        for (Expression operand : super.getOperands()) {
            sum += operand.evaluate(assignment);
        }
        return sum;
    }

    /**
     * This method calculates the sum of the operands in a Plus expression.
     * The method returns the final sum as the result of evaluating the Plus
     * expression. If any error occurs during the evaluation process, an
     * exception is thrown.
     *
     * @return The result of evaluating the Plus expression
     * @throws Exception if there is an error during evaluation
     */
    @Override
    public double evaluate() throws Exception {
        double sum = 0;
        for (Expression operand : super.getOperands()) {
            sum += operand.evaluate();
        }
        return sum;
    }

    /**
     * Assigns a new value to the given variable in the Plus expression.
     * Replaces all occurrences of the variable with the provided expression.
     *
     * @param var The variable to be assigned.
     * @param expression The expression to replace the variable with.
     * @return A new Plus expression with the assigned variable.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        List<Expression> newOperands = new ArrayList<>();
        // Iterate over the operands and assign the variable
        for (Expression operand : super.getOperands()) {
            newOperands.add(operand.assign(var, expression));
        }
        // Create a new Plus expression with the assigned variable
        return new Plus(newOperands.get(0), newOperands.get(1));
    }

    /**
     * Returns a string representation of the Plus expression.
     *
     * @return The string representation of the Plus expression.
     */
    public String toString() {
        String x = super.getOperands().get(0).toString();
        String y = super.getOperands().get(1).toString();
        return "(" + x + " + " + y + ")";
    }

    /**
     * Returns the derivative of the Plus expression with respect to the given
     * variable.
     *
     * @param var The variable to differentiate with respect to.
     * @return The derivative of the Plus expression.
     */
    @Override
    public Expression differentiate(String var) {
        // Differentiate the first operand with respect to the variable
        Expression dx = super.getOperands().get(0).differentiate(var);
        // Differentiate the second operand with respect to the variable
        Expression dy = super.getOperands().get(1).differentiate(var);
        // Return the sum of the derivatives
        return new Plus(dx, dy);
    }

    /**
     * This method simplifies the expression by evaluating the sum of its
     * operands if they are both constants, or by returning one of its operands
     * if the other operand is zero.
     * If none of the operands are constants or zero, the method returns the
     * simplified expression.
     *
     * @return the simplified expression
     */
    public Expression simplify() {
        Expression x = super.getOperands().get(0).simplify();
        Expression y = super.getOperands().get(1).simplify();
        // If both operands are numbers, evaluate the sum and return it
        if (x instanceof Num && y instanceof Num) {
            try {
                double sum = x.evaluate() + y.evaluate();
                return new Num(sum);
            } catch (Exception e) {
            }
        }
        // If the first operand is zero it return the second operand
        if (x instanceof Num) {
            try {
                if (((Num) x).evaluate() == 0) {
                    return y;
                }
            } catch (Exception e) {
            }
        }
        // If the second operand is zero it return the first operand
        if (y instanceof Num) {
            try {
                if (((Num) y).evaluate() == 0) {
                    return x;
                }
            } catch (Exception e) {
            }
        }
        // If the first operand is zero it return the second operand
        if (x instanceof Expression) {
            try {
                if (x.evaluate() == 0) {
                    return y;
                }
            } catch (Exception e) {
            }
        }
        // If the second operand is zero it return the first operand
        if (y instanceof Expression) {
            try {
                if (y.evaluate() == 0) {
                    return x;
                }
            } catch (Exception e) {
            }
        }
        // If the expression cannot be simplified, return the original expression
        return new Plus(x, y);
    }
}

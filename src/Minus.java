// itay alter 206132284
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Minus class represents the subtraction operation in an arithmetic
 * expression. It extends the BinaryExpression class and implements the
 * Expression interface. The class provides functionality to evaluate the
 * subtraction, assign values to variables, differentiate with respect to
 * variables, simplify the expression, and convert it to a string representation.
 */
public class Minus extends BinaryExpression implements Expression {

    /**
     * Constructs a new Minus expression with the given two operands.
     *
     * @param x the left operand of the subtraction.
     * @param y the right operand of the subtraction
     */
    public Minus(Expression x, Expression y) {
        super(x, y);
    }
    /**
     * Evaluates the subtraction operation between the operands using the given
     * variable assignment and returns the result.
     *
     * @param assignment a map containing variable assignments for evaluation.
     * @return the result of subtracting the second operand from the first
     * operand.
     * @throws Exception if an error occurs during evaluation.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double differense = super.getOperands().get(0).evaluate(assignment);
        differense -= super.getOperands().get(1).evaluate(assignment);
        return differense;
    }
    /**
     * Evaluates the subtraction operation between the operands without any
     * variable assignment.
     *
     * @return The result of subtracting the two operands.
     * @throws Exception If an error occurs during evaluation.
     */
    @Override
    public double evaluate() throws Exception {
        double differense = super.getOperands().get(0).evaluate();
        differense -= super.getOperands().get(1).evaluate();
        return differense;
    }

    /**
     * Assigns a new expression to the specified variable within the subtraction
     * operation.
     *
     * @param var The variable to be assigned.
     * @param expression The new expression to assign to the variable.
     * @return A new subtraction expression with the updated variable assignment.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        List<Expression> newOperands = new ArrayList<>();
        // Assign the variable within each operand
        for (Expression operand : super.getOperands()) {
            newOperands.add(operand.assign(var, expression));
        }
        // Create a new subtraction expression with the updated variable assignment
        return new Minus(newOperands.get(0), newOperands.get(1));
    }
    /**
     * Computes the derivative of the subtraction expression with respect to the
     * specified variable.
     *
     * @param var The variable with respect to which the derivative is computed.
     * @return The derivative of the subtraction expression.
     */
    @Override
    public Expression differentiate(String var) {
        // Compute the derivatives of the operands
        Expression dx = super.getOperands().get(0).differentiate(var);
        Expression dy = super.getOperands().get(1).differentiate(var);
        /*
         * Subtract the derivatives to obtain the derivative of the subtraction
         *  expression
         */
        return new Minus(dx, dy);
    }
    /**
     * This method simplifies a subtraction expression by performing algebraic
     * simplifications. It handles cases such as subtracting a negation,
     * subtracting numbers, subtracting zero, and subtracting identical
     * expressions. The method returns the simplified subtraction expression.
     *
     * @return The simplified expression.
     */
    public Expression simplify() {
        if (super.getOperands().get(1) instanceof Neg) {
            /*
             * If the second operand is a negation, convert it to addition and
             *  simplify
             */
            Neg neg = (Neg) super.getOperands().get(1);
            Plus plus = new Plus(super.getOperands().get(0), neg.getOperand());
            return plus.simplify();
        }
        Expression x = super.getOperands().get(0).simplify();
        Expression y = super.getOperands().get(1).simplify();
        // If both operands are numbers, evaluate and return the difference
        if (x instanceof Num && y instanceof Num) {
            try {
                double difference = x.evaluate() - y.evaluate();
                return new Num(difference);
            } catch (Exception e) {
            }
        }
        // If the first operand is a number, check for subtraction by zero
        if (x instanceof Num) {
            try {
                if (((Num) x).evaluate() == 0) {
                    return new Neg(y);
                }
            } catch (Exception e) {
            }
        }
        // If the second operand is a number, check for subtraction of zero
        if (y instanceof Num) {
            try {
                if (((Num) y).evaluate() == 0) {
                    return x;
                }
            } catch (Exception e) {
            }
        }
        /*
         * If both operands are expressions, check for subtraction of identical
         *  expressions
         */
        if (x instanceof Expression && y instanceof Expression) {
            try {
                if (y.toString().equals(x.toString())) {
                    return new Num(0);
                }
            } catch (Exception e) {
            }
        }
        // Return the simplified subtraction expression
        return new Minus(x, y);
    }
    /**
     * Returns a string representation of the subtraction expression in the form
     * "(x - y)".
     *
     * @return The string representation of the subtraction expression.
     */
    public String toString() {
        String x = super.getOperands().get(0).toString();
        String y = super.getOperands().get(1).toString();
        return "(" + x + " - " + y + ")";
    }
}

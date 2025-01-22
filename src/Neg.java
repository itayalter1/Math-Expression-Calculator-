// itay alter 206132284
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Neg class represents the negation operation in an arithmetic expression.
 * It extends the UnaryExpression class and provides functionality to evaluate
 * the negation, assign values to variables, differentiate with respect to
 * variables, simplify the expression, and convert it to a string representation.
 */
public class Neg extends UnaryExpression implements Expression {
    /**
     * Constructs a new Neg object with the specified expression to be negated.
     *
     * @param x the expression to be negated
     */
    public Neg(Expression x) {
        super(x);
    }
    /**
     * Evaluates the negation of the expression by applying the unary negation
     * operator (-). The evaluation is performed with the given variable
     * assignment.
     *
     * @param assignment a map containing variable assignments for evaluation
     * @return the result of negating the expression
     * @throws Exception if an error occurs during evaluation
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return -super.getOperands().get(0).evaluate(assignment);
    }
    /**
     * This method evaluates the negative of the expression without assignment
     * of variables.
     *
     * @return the negative value of the expression.
     * @throws Exception if there is a problem with the evaluation of the
     * expression.
     */
    @Override
    public double evaluate() throws Exception {
        return -super.getOperands().get(0).evaluate();
    }
    /**
     * This method assigns a new value to the variable in the expression by
     * substituting it with the given expression.
     *
     * @param var the variable to be assigned a new value.
     * @param expression the expression representing the new value for the
     *                  variable.
     * @return a new expression with the variable replaced by the new expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        List<Expression> newOperands = new ArrayList<>();
        // Assign the variable in the expression.
        newOperands.add(super.getOperands().get(0).assign(var, expression));
        // Create a new Neg expression with the assigned operand.
        return new Neg(newOperands.get(0));
    }
    /**
     * This method calculates the derivative of the expression by taking the
     * derivative of its operand and negating it.
     *
     * @param var the variable with respect to which the derivative is calculated.
     * @return the derivative of the expression.
     */
    public Expression differentiate(String var) {
        Expression operand = super.getOperands().get(0);
        // Calculate the derivative of the operand with respect to the variable.
        Expression operandDiff = operand.differentiate(var);
        // Create a new Neg expression with the derivative of the operand.
        return new Neg(operandDiff);
    }

    /**
     * Retrieves the operand of the expression.
     *
     * @return The operand of the expression.
     */
    public Expression getOperand() {
        return super.getOperands().get(0);
    }
    /**
     * Simplifies the expression by applying specific rules for negation.
     *
     * @return The simplified expression.
     */
    @Override
    public Expression simplify() {
        Expression x = super.getOperands().get(0).simplify();
        // Evaluate the negation expression if possible.
        try {
            double negResult = this.evaluate();
            return new Num(negResult);
        } catch (Exception e) {
        }
        // If evaluation fails, return the negation of the simplified operand.
        return new Neg(x);
    }
    /**
     * Returns a string representation of the negation expression.
     *
     * @return The string representation of the negation expression.
     */
    public String toString() {
        String x = super.getOperands().get(0).toString();
        return "(-" + x + ")";
    }
}

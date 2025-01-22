// itay alter 206132284
import java.util.List;
import java.util.Map;

/**
 * The Num class represents a numeric constant in an arithmetic expression. It
 * encapsulates a double value and provides functionality to evaluate, assign,
 * differentiate, and simplify the expression.
 */
public class Num implements Expression {
    private double value;

    /**
     * Constructs a Num object with the given value.
     *
     * @param val The numeric value of the Num object.
     */
    public Num(double val) {
        this.value = val;
    }
    /**
     * Returns a string representation of the Num object.
     * If the value is an integer, it is represented as an integer string.
     * Otherwise, it is represented as a decimal string.
     *
     * @return The string representation of the Num object.
     */
    public String toString() {
        // If the value is an integer, return integer string representation
        if (Math.floor(this.value) == this.value) {
            return Integer.toString((int) this.value);
        }
        // Otherwise, return decimal string representation.
        return Double.toString(this.value);
    }
    /**
     * Evaluates the Num expression by returning its stored value.
     *
     * @param assignment A map containing variable assignments
     *                  (not used in this method)
     * @return The stored value of the Num expression
     * @throws Exception If an error occurs during evaluation (not expected in
     * this method)
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        /*
         * Since Num expression represents a constant value, return the stored
         *  value directly
         */
        return this.value;
    }
    /**
     * Evaluates the Num expression by returning its stored value.
     * @return The stored value of the Num expression
     *
     * @throws Exception If an error occurs during evaluation (not expected in
     * this method)
     */
    @Override
    public double evaluate() throws Exception {
        /*
         * Since Num expression represents a constant value, return the stored
         *  value directly
         */
        return this.value;
    }
    /**
     * Retrieves the list of variables present in the expression.
     *
     * @return An empty list, as Num expression does not contain any variables
     */
    @Override
    public List<String> getVariables() {
        // Num expression does not have any variables, so return an empty list
        return null;
    }
    /**
     * Assigns a new value to the specified variable in the expression.
     * Since Num expression does not contain any variables, the assignment does
     * not affect it.
     * @param var The variable to be assigned a new value
     * @param expression The expression representing the new value of the variable
     * @return A new Num expression with the same value as the original Num
     * expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Num(this.value);
    }
    /**
     * Returns the 0 because the derivative of a constant value is always 0.
     *
     * @param var The variable with respect to which the derivative is computed
     * @return A new Num expression representing the derivative, which is always
     * 0 for a constant value
     */
    public Expression differentiate(String var) {
        // The derivative of a constant value is always 0
        return new Num(0);
    }
    /**
     * Returns This Num expression itself, because  Num expression is already
     * simplified.
     *
     * @return This Num expression itself, as it is already in its simplest form
     */
    public Expression simplify() {
        return this;
    }
}

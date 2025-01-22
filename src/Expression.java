// itay alter 206132284
import java.util.List;
import java.util.Map;

/**
 * The Expression interface represents a mathematical expression that can be
 * evaluated, manipulated, and simplified. It provides a set of methods to
 * perform various operations on expressions.
 */
public interface Expression {
    /**
     * This method evaluates the expression by substituting the variables in the
     * expression with the corresponding values from the given assignment. It
     * returns the evaluated result as a double. It can throw an exception if
     * there is an error during evaluation.
     *
     * @param assignment a map of variable assignments
     * @return the result of the evaluation
     * @throws Exception if there is a division by zero or an error occurs
     *                   during evaluation
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * This method evaluates the expression using default variable assignments.
     * It returns the evaluated result as a double. It can throw an exception if
     * there is an error during evaluation.
     *
     * @return the result of the evaluation
     * @throws Exception if there is a division by zero or an error occurs
     *                   during evaluation
     */
    double evaluate() throws Exception;

    /**
     * This method returns a list of variables present in the expression. Each
     * variable is represented as a string.
     *
     * @return a list of Expression operands
     */
    List<String> getVariables();

    /**
     * This method returns a string representation of the expression.
     *
     *@return the string representation of the expression
     */
    String toString();

    /**
     * This method creates a new expression by assigning a new value to a
     * specific variable in the expression. It takes a variable name and an
     * expression representing the new value, and returns the modified
     * expression.
     *
     * @param var the variable to be replaced
     * @param expression the expression to replace the variable with
     * @return a new expression with the variable replaced by the expression
     */
    Expression assign(String var, Expression expression);

    /**
     * This method calculates the derivative of the expression with respect to
     * the specified variable. It returns a new expression representing the
     * derivative.
     *
     * @param var the variable with respect to which the derivative is calculated
     * @return the derivative of the expression
     */
    Expression differentiate(String var);

    /**
     * This method simplifies the expression, reducing it to a simpler form if
     * possible. It returns a new expression that is a simplified version of the
     * original expression.
     *
     * @return the simplified expression
     */
    Expression simplify();
}

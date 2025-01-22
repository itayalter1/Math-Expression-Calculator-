// itay alter 206132284
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Var class represents a variable in an arithmetic expression. It stores
 * the name of the variable and provides functionality to evaluate its value,
 * assign new values, differentiate with respect to other variables, and
 * simplify the expression.
 */
public class Var implements Expression {
    private String varName;

    /**
     * Constructs a new variable expression with the given variable name.
     *
     * @param val the name of the variable
     */
    public Var(String val) {
        this.varName = val;
    }

    /**
     * Returns the string representation of the variable.
     *
     * @return the variable name as a string
     */
    public String toString() {
        return this.varName;
    }

    /**
     * This method retrieves the value of the variable from the given assignment
     * map based on its name. It returns the value if the variable is present in
     * the map, and throws an exception if the variable is not assigned a value.
     *
     * @param assignment the variable assignment map
     * @return the value of the variable in the assignment
     * @throws Exception if the variable is not assigned a value in the given
     * assignment
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            return assignment.get(this.varName);
        } catch (Exception e) {
            throw new Exception("You didn't entered value for this variable");
        }
    }

    /**
     * Evaluates the variable without any assigned value.  This method is called
     * when attempting to evaluate a variable that does not have any assigned
     * value. It throws an exception indicating that the variable has no value
     * assigned.
     *
     * @throws Exception if the variable has no assigned value.
     * @return The evaluated value of the variable.
     */
    @Override
    public double evaluate() throws Exception {
        throw new Exception("This var has no value");
    }

    /**
     * This method retrieves the name of the variable and adds it to a list of
     * variable names. It then returns the list containing the variable name
     *
     * @return A list containing the name of the variable
     */
    @Override
    public List<String> getVariables() {
        List<String> vars = new ArrayList<>();
        vars.add(this.varName);
        return vars;
    }

    /**
     * This method assigns a new expression to the variable. If the provided
     * variable name matches the current variable name, the expression is
     * assigned to the variable. Otherwise, a new variable expression with the
     * same name is returned.
     *
     * @param var The variable name to assign the expression to.
     * @param expression The expression to be assigned to the variable.
     * @return The assigned expression if the variable name matches, or a new
     * variable expression with the same name.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        if (var.equals(this.varName)) {
            return expression;
        }
        return new Var(this.varName);
    }

    /**
     * This method calculates the derivative of the variable with respect to the
     * given variable. If the provided variable name matches the current variable
     * name, it returns a constant expression representing 1. Otherwise, it
     * returns a constant expression representing 0, indicating that the
     * derivative is zero with respect to the given variable.
     *
     * @param var The variable name to differentiate with respect to.
     * @return The derivative expression of the variable. Returns 1 if the
     * variable name matches the current variable, otherwise returns 0.
     */
    public Expression differentiate(String var) {
        if (this.varName.equals(var)) {
            return new Num(1);
        }
        return new Num(0);
    }

    /**
     * This method simplifies the expression by returning the variable itself.
     * Since the variable cannot be further simplified, it remains unchanged in
     * its simplified form.
     *
     * @return The simplified expression, which is the variable itself.
     */
    public Expression simplify() {
        return this;
    }
}

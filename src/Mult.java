// itay alter 206132284
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Mult class represents the multiplication operation in an arithmetic
 * expression. It extends the BinaryExpression class and provides functionality
 * to evaluate the multiplication, assign values to variables, differentiate
 * with respect to variables, simplify the expression, and convert it to a
 * string representation.
 */
public class Mult extends BinaryExpression implements Expression {
    /**
     * Constructs a multiplication expression with the given operands.
     *
     * @param x The first operand of the multiplication.
     * @param y The second operand of the multiplication.
     */
    public Mult(Expression x, Expression y) {
        super(x, y);
    }

    /**
     * This method evaluates the multiplication expression by iterating over the
     * operands and performing the multiplication operation. It accepts a map of
     * variable assignments, computes the product of the operands' evaluated
     * values, and returns the result.
     *
     * @param assignment A map containing variable assignments, where the keys
     *                   are variable names and the values are their assigned
     *                   values.
     * @return The result of evaluating the multiplication expression.
     * @throws Exception If an error occurs during evaluation or if a variable
     *                   is not assigned a value.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        boolean flag = true;
        double multResult = 0;
        // Iterate over the operands and perform multiplication
        for (Expression operand : super.getOperands()) {
            if (flag) {
                multResult += operand.evaluate(assignment);
                flag = false;
            } else {
                multResult *= operand.evaluate(assignment);
            }
        }
        return multResult;
    }

    /**
     * This method evaluates the expression without any variable assignment, by
     * calculating the product of all the operands. If the expression has only
     * one operand, it returns its value.
     *
     * @return the result of the multiplication of all operands.
     * @throws Exception if there is a problem with evaluating one of the operands.
     */
    @Override
    public double evaluate() throws Exception {
        boolean flag = true;
        double multResult = 0;
        // Iterate over the operands and perform multiplication
        for (Expression operand : super.getOperands()) {
            if (flag) {
                multResult += operand.evaluate();
                flag = false;
            } else {
                multResult *= operand.evaluate();
            }
        }
        return multResult;
    }

    /**
     * This method assigns a new expression to a specified variable within the
     * current expression. It creates a new list of operands with the assigned
     * expressions, and returns a new multiplication expression with the updated
     * operands.
     *
     * @param var        the variable to be assigned a new expression.
     * @param expression the new expression to be assigned to the variable.
     * @return a new multiplication expression with the assigned variable
     * replaced by the given expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        List<Expression> newOperands = new ArrayList<>();
        for (Expression operand : super.getOperands()) {
            // Assign the variable with the provided expression.
            newOperands.add(operand.assign(var, expression));
        }
        // Create and return a new multiplication expression with the new operands
        return new Mult(newOperands.get(0), newOperands.get(1));
    }

    /**
     * Computes the derivative of the expression with respect to the specified
     * variable using the product rule of differentiation:
     * (f(x) * g(x))' = f'(x) * g(x) + f(x) * g'(x).
     *
     * @param var The variable to differentiate with respect to.
     * @return The derivative of the expression with respect to the variable.
     */
    public Expression differentiate(String var) {
        // Compute the derivatives of the operands with respect to the given variable
        Expression dx = super.getOperands().get(0).differentiate(var);
        Expression dy = super.getOperands().get(1).differentiate(var);
        Expression x = super.getOperands().get(0);
        Expression y = super.getOperands().get(1);
        // Apply the product rule of differentiation: (x * dy) + (dx * y)
        return new Plus(new Mult(dx, y), new Mult(x, dy));
    }

    /**
     * This method simplifies a multiplication expression by applying various
     * algebraic simplification rules. It handles cases where both operands are
     * numbers and can be evaluated, as well as cases where one or both operands
     * are zero or one. The method returns a simplified expression if any
     * simplifications apply, otherwise it returns the original multiplication
     * expression.
     *
     * @return the simplified expression
     */
    public Expression simplify() {
        Expression x = super.getOperands().get(0).simplify();
        Expression y = super.getOperands().get(1).simplify();
        // If both operands are numbers, evaluate the result
        if (x instanceof Num && y instanceof Num) {
            try {
                double multResult = x.evaluate() * y.evaluate();
                return new Num(multResult);
            } catch (Exception e) {
            }
        }
        // If the first operand is a number
        if (x instanceof Num) {
            try {
                // If the first operand is 0, the result is 0
                if (((Num) x).evaluate() == 0) {
                    return new Num(0);
                }
            } catch (Exception e) {
            }
        }
        // If the second operand is a number
        if (y instanceof Num) {
            try {
                // If the second operand is 0, the result is 0
                if (((Num) y).evaluate() == 0) {
                    return new Num(0);
                }
            } catch (Exception e) {
            }
        }
        // If the first operand is 1, the result is the second operand
        if (x instanceof Num) {
            try {
                if (((Num) x).evaluate() == 1) {
                    return y;
                }
            } catch (Exception e) {
            }
        }
        // If the second operand is 1, the result is the first operand
        if (y instanceof Num) {
            try {
                if (((Num) y).evaluate() == 1) {
                    return x;
                }
            } catch (Exception e) {
            }
        }
        // If no simplifications apply, return the original expression
        return new Mult(x, y);
    }

    /**
     * This method returns a string representation of the multiplication
     * expression. If the operands of the expression consist of a number and a
     * variable, it simplifies the representation to concatenate them without
     * the multiplication symbol. Otherwise, it returns the operands enclosed in
     * parentheses, separated by the multiplication symbol.
     *
     * @return The string representation of the multiplication expression.
     */
    public String toString() {
        String x = super.getOperands().get(0).toString();
        String y = super.getOperands().get(1).toString();
        // Simplify the multiplication of a number and a variable
        if (super.getOperands().get(0) instanceof Num
                && super.getOperands().get(1)instanceof Var) {
            return "(" + x + y + ")";
        }
        return "(" + x + " * " + y + ")";
    }

}

// itay alter 206132284
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Div class represents the division operation in an arithmetic expression.
 * It extends the BinaryExpression class and implements the Expression interface.
 * It provides functionality to evaluate division, assign values to variables,
 * differentiate with respect to variables, simplify the expression, and convert
 * it to a string representation.
 */
public class Div extends BinaryExpression implements Expression {
    /**
     * Constructs a division expression with the specified operands.
     *
     * @param x the numerator expression
     * @param y the denominator expression
     */
    public Div(Expression x, Expression y) {
        super(x, y);
    }
    /**
     * Evaluates the division expression with the given variable assignments.
     * This method performs the division operation by evaluating the numerator
     * and denominator expressions and dividing the numerator by the denominator.
     *
     * @param assignment a map of variable assignments
     * @return the result of evaluating the division expression
     * @throws Exception if there is a division by zero or an error occurs
     * during evaluation
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (super.getOperands().get(1).evaluate(assignment) == 0) {
            throw new Exception("math error!");
        }
        // Evaluate the numerator and denominator expressions
        double numerator = super.getOperands().get(0).evaluate(assignment);
        double denominator = super.getOperands().get(1).evaluate(assignment);
        // return the result of evaluating the division expression
        return numerator / denominator;
    }
    /**
     * Evaluates the division expression without any variable assignments. This
     * method performs the division operation by evaluating the numerator and
     * denominator expressions and dividing the numerator by the denominator.
     *
     * @return the result of evaluating the division expression
     * @throws Exception if there is a division by zero or an error occurs
     * during evaluation
     */
    @Override
    public double evaluate() throws Exception {
        if (super.getOperands().get(1).evaluate() == 0) {
            throw new Exception("math error!");
        }
        // Evaluate the numerator and denominator expressions
        double numerator = super.getOperands().get(0).evaluate();
        double denominator = super.getOperands().get(1).evaluate();
        // return the result of evaluating the division expression
        return numerator / denominator;
    }
    /**
     * Assigns a new value to a variable within the division expression.This
     * method replaces all occurrences of the variable with the provided
     * expression in both the numerator and denominator expressions, and returns
     * a new division expression with the updated operands.
     *
     * @param var the variable to be replaced
     * @param expression the expression to replace the variable with
     * @return a new division expression with the variable replaced by the
     * expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        List<Expression> newOperands = new ArrayList<>();
        // Iterate over the operands of the division expression
        for (Expression operand : super.getOperands()) {
            // Assign the variable with the expression in the current operand
            newOperands.add(operand.assign(var, expression));
        }
        return new Div(newOperands.get(0), newOperands.get(1));
    }
    /**
     * Calculates the derivative of the division expression with respect to the
     * specified variable. The derivative of a division expression (f / g) is
     * calculated using the quotient rule: (f'g - fg') / g^2
     *
     * @param var the variable with respect to which the derivative is calculated
     * @return the derivative of the division expression
     */
    public Expression differentiate(String var) {
        // Retrieve the numerator and denominator expressions
        Expression f = super.getOperands().get(0);
        Expression g = super.getOperands().get(1);
        // Differentiate the numerator and denominator
        Expression dF = f.differentiate(var);
        Expression dG = g.differentiate(var);
        // Calculate the numerator of the derivative expression
        Expression numerator = new Minus(new Mult(dF, g), new Mult(f, dG));
        // Calculate the denominator of the derivative expression
        Expression denominator = new Pow(g, new Num(2));
        // Return a new division expression with the derivative as its operands
        return new Div(numerator, denominator);
    }
    /**
     * Simplifies the division expression by performing algebraic simplifications.
     * Simplification rules applied:
     * If the denominator is a numeric value of 0, the expression is returned as
     * is.
     * If both the numerator and denominator are numeric values, the division is
     * evaluated and returned as a numeric value.
     * If the denominator is a numeric value of 1, the numerator is returned.
     * If both the numerator and denominator are variables, and they are the same,
     * the expression is simplified to 1.
     * If both the numerator and denominator are expressions, and they are equal,
     * the expression is simplified to 1.
     * If none of the above simplification rules apply, the expression is
     * returned as is.
     *
     * @return the simplified division expression
     */
    public Expression simplify() {
        Expression x = super.getOperands().get(0).simplify();
        Expression y = super.getOperands().get(1).simplify();
        if (y instanceof Num) {
            try {
                if (y.evaluate() == 0) {
                    return new Div(x, y);
                }
            } catch (Exception e) {
            }
        }
        if (x instanceof Num && y instanceof Num) {
            try {
                double divResult = x.evaluate() / y.evaluate();
                return new Num(divResult);
            } catch (Exception e) {
            }
        }
        if (y instanceof Num) {
            try {
                if (((Num) y).evaluate() == 1) {
                    return x;
                }
            } catch (Exception e) {
            }
        }
        if (x instanceof Var && y instanceof Var) {
            try {
                if (x.toString().equals(y.toString())) {
                    return new Num(1);
                }
            } catch (Exception e) {
            }
        }
        if (x instanceof Expression && y instanceof Expression) {
            try {
                if (y.toString().equals(x.toString())) {
                    return new Num(1);
                }
            } catch (Exception e) {
            }
        }
        return new Div(x, y);
    }

    /**
     * Returns a string representation of the division expression.
     *
     * @return the string representation of the division expression
     */
    public String toString() {
        String x = super.getOperands().get(0).toString();
        String y = super.getOperands().get(1).toString();
        return "(" + x + " / " + y + ")";
    }
}

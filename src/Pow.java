// itay alter 206132284
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Pow class represents the exponentiation operation in an arithmetic
 * expression. It extends the BinaryExpression class and implements the
 * Expression interface. The class provides methods to evaluate exponentiation,
 * assign values to variables, differentiate the expression, simplify it, and
 * convert it to a string representation.
 */
public class Pow extends BinaryExpression implements Expression {
    /**
     * Constructs a new Power expression with the specified base and exponent.
     *
     * @param x the base expression
     * @param y the exponent expression
     */
    public Pow(Expression x, Expression y) {
        super(x, y);
    }

    /**
     * Returns the result of evaluating the power function with the given
     * variable assignments. If the base is negative and the exponent is less
     * than 1, an exception is thrown since the result is undefined.
     *
     * @param assignment a map containing variable assignments
     * @return the evaluated value of the Power expression
     * @throws Exception if a math error occurs, such as taking the power of a
     * negative number with a non-integer exponent
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double powResult, base, exponent;
        // Evaluate the base and exponent using the given variable assignments
        base = super.getOperands().get(0).evaluate(assignment);
        exponent = super.getOperands().get(1).evaluate(assignment);
        if (base < 0 && exponent < 1) {
            throw new Exception("math error!");
        }
        // Compute the power using the Math.pow() method
        powResult = Math.pow(base, exponent);
        return powResult;
    }

    /**
     * Returns the result of evaluating the power function. If the base is
     * negative and the exponent is less than 1, an exception is thrown since
     * the result is undefined.
     *
     * @return the value of the power function
     * @throws Exception if the base is negative and the exponent is less than 1,
     * indicating an undefined result
     */
    @Override
    public double evaluate() throws Exception {
        double powResult, base, exponent;
        // Evaluate the base and exponent expressions
        base = super.getOperands().get(0).evaluate();
        exponent = super.getOperands().get(1).evaluate();
        if (base < 0 && exponent < 1) {
            throw new Exception("math error!");
        }
        // Compute the power using the Math.pow() method
        powResult = Math.pow(base, exponent);
        return powResult;
    }

    /**
     * Assigns a new expression to the given variable in the power expression.
     * The method recursively assigns the variable to each operand and creates a
     * new power expression with the updated operands.
     *
     * @param var the variable to assign a new expression to
     * @param expression the new expression to assign to the variable
     * @return a new power expression with the updated operands
     */
    @Override
    public Expression assign(String var, Expression expression) {
        List<Expression> newOperands = new ArrayList<>();
        // Assign the variable to each operand
        for (Expression operand : super.getOperands()) {
            newOperands.add(operand.assign(var, expression));
        }
        // Create a new power expression with the updated operands
        return new Pow(newOperands.get(0), newOperands.get(1));
    }

    /**
     * Calculates the derivative of the power function using the formula:
     * (f^g)' = f^g * (g * f' / f + ln(e,f) * g').
     *
     * @param var the variable to differentiate the expression by.
     * @return the derivative of the power function.
     */
    public Expression differentiate(String var) {
        // Get the two operands of the power expression
        Expression f = super.getOperands().get(0);
        Expression g = super.getOperands().get(1);
        // Get the derivatives of the two operands
        Expression dF = f.differentiate(var);
        Expression dG = g.differentiate(var);
        // Calculate the first term of the derivative, which is f^g
        Expression firstTerm = new Pow(f, g);
        // Calculate the second term of the derivative
        Expression secondTerm = new Plus(new Mult(dF, new Div(g, f)),
                new Mult(dG, new Log(new Var("e"), f)));
        // Return the multiplication of the two terms.
        return new Mult(firstTerm, secondTerm);
    }

    /**
     * A method that simplifies a Pow expression.
     *
     * @return the simplified expression
     */
    @Override
    public Expression simplify() {
        Expression x = super.getOperands().get(0).simplify();
        Expression y = super.getOperands().get(1).simplify();
        // if both operands are numbers calculate the result of the Pow expression
        if (x instanceof Num && y instanceof Num) {
            try {
                if (x.evaluate() < 0 && y.evaluate() < 1) {
                    return new Pow(x, y);
                }
                double powResult = Math.pow(x.evaluate(), y.evaluate());
                // return the result of the Pow expression
                return new Num(powResult);
            } catch (Exception e) {
            }
        }
        /*
         * if none of the simplifications apply, return the Pow expression with
         *  the simplified operands
         */
        return new Pow(x, y);
    }

    /**
     * Returns a string representation of the power expression.
     *
     * @return a string representing the power expression in the form
     * "(base ^ exponent)".
     */
    public String toString() {
        String x = super.getOperands().get(0).toString();
        String y = super.getOperands().get(1).toString();
        return "(" + x + "^" + y + ")";
    }
}

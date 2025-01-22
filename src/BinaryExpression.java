// itay alter 206132284
import java.util.ArrayList;
import java.util.List;

/**
 * The BinaryExpression class is an abstract class that extends the
 * BaseExpression class. It represents a binary expression, which is an
 * expression involving two operands. This class provides a common structure and
 * functionality for binary expressions. Derived classes can inherit from this
 * class and extend its behavior to represent specific types of binary
 * expressions.
 */
public abstract class BinaryExpression extends BaseExpression {
    /**
     * This is a constructor that takes two expressions as operands and passes
     * them to the superclass constructor to initialize the operands.
     *
     * @param x the first operand
     * @param y the second operand
     */
    public BinaryExpression(Expression x, Expression y) {
        super(x, y);
    }

    /**
     * This is a method that returns a list of variables present in the binary
     * expression. It iterates over the operands and collects the variables from
     * each operand. The variables are stored in a list. The returned list
     * contains all the variables present in the binary expression.
     *
     * @return a list of variables
     */
    public List<String> getVariables() {
        List<String> vars = new ArrayList();
        vars.add("");
        for (Expression operand : super.getOperands()) {
            if (operand.getVariables() != null) {
                vars.addAll(operand.getVariables());
            }
        }
        vars.remove(0);
        return vars;
    }
}

// itay alter 206132284
import java.util.ArrayList;
import java.util.List;

/**
 * The BaseExpression class is an abstract class that serves as a base for other
 * expression classes. It provides common functionality and data structure for
 * managing operands in an expression. This class is designed to be extended by
 * other classes that represent specific types of expressions. By inheriting
 * from this class, derived expression classes can benefit from the shared
 * functionality for managing operands.
 */
public abstract class BaseExpression {
    private List<Expression> operands = new ArrayList<>();

    /**
     * It is a constructor that takes two expressions as operands and adds them
     * to the list of operands.
     *
     * @param x the first expression operand
     * @param y the second expression operand
     */
    public BaseExpression(Expression x, Expression y) {
        this.operands.add(x);
        this.operands.add(y);
    }

    /**
     *  It is a constructor that takes a single expression (x) as an operand and
     *  adds it to the list of operands.
     *
     * @param x the expression operand
     */
    public BaseExpression(Expression x) {
        this.operands.add(x);
    }

    /**
     * Returns a copy of the list of operands in this BaseExpression.
     *
     * @return a list of Expression operands
     */
    public List<Expression> getOperands() {
        List<Expression> operandsCopy = new ArrayList();
        operandsCopy.addAll(this.operands);
        return operandsCopy;
    }
}

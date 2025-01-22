// itay alter 206132284
import java.util.List;

/**
 * The UnaryExpression class is an abstract class that extends the
 * BaseExpression class. It represents a unary expression, which is an
 * expression involving a single operand.
 */
public abstract class UnaryExpression extends BaseExpression {
    /**
     * This is a constructor that takes a single expression as the operand and
     * passes it to the superclass constructor to initialize the operand.
     *
     * @param x the operand of the unary expression.
     */
    public UnaryExpression(Expression x) {
        super(x);
    }

    /**
     * It is a method that returns a list of variables present in the unary
     * expression.
     *
     * @return a list of variables in the expression.
     */
    public List<String> getVariables() {
        return super.getOperands().get(0).getVariables();
    }
}

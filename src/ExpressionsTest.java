// itay alter 206132284
import java.util.Map;
import java.util.TreeMap;

/**
 * The ExpressionsTest class is used to test the functionality of the Expression
 * hierarchy. It demonstrates the creation, evaluation, differentiation, and
 * simplification of expressions.
 */
public class ExpressionsTest {
    /**
     * The main method serves as the entry point of the program and demonstrates
     * the usage of expressions. It creates expressions, evaluates them, performs
     * differentiation, and simplifies the expressions.
     *
     * @param args The command line arguments.
     * @throws Exception if an error occurs during evaluation or differentiation.
     */
    public static void main(String[] args) throws Exception {
        // Expression
        Expression expression = new Plus(new Plus(new Mult(new Num(2),
                new Var("x")), new Sin(new Mult(new Num(4),
                new Var("y")))), new Pow(new Var("e"), new Var("x")));
        System.out.println(expression);
        // Evaluation
        Map<String, Double> assignment = new TreeMap<>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);
        double result = expression.evaluate(assignment);
        System.out.println(result);
        // Differentiation
        Expression derExpression = expression.differentiate("x");
        System.out.println(derExpression);
        // Differentiation Evaluation
        double derEvaluation = derExpression.evaluate(assignment);
        System.out.println(derEvaluation);
        // Simplification
        Expression simplified = derExpression.simplify();
        System.out.println(simplified);
    }
}

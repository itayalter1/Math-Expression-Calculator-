import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws Exception {
        Expression e1 = new Sin(new Plus(new Mult(new Num(2), new Var("x")),
                new Var("y")));
        Expression e2 = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));
        Expression e3 = new Mult(new Num(4), new Var("x"));
        Expression e4 = new Div(new Neg(new Var("y")), new Num(2));

        System.out.println(e1.toString()); // sin((2.0*x+y))
        System.out.println(e2.toString()); // (x+y)^2.0
        System.out.println(e3.toString()); // 4.0*x
        System.out.println(e4.toString()); // (-y)/2.0


        System.out.println(e1.getVariables()); // [x, y]
        System.out.println(e2.getVariables()); // [x, y]
        System.out.println(e3.getVariables()); // [x]
        System.out.println(e4.getVariables()); // [y]


        Map<String, Double> assignment = new HashMap<>();
        assignment.put("x", 2.0);
        assignment.put("y", 3.0);

        System.out.println(e1.evaluate(assignment)); // 0.35078322768961984
        /* li yatza 0.12186934340514748 , the gpt said that there could be a
         difference because of the double type but its to big of a difference
        so tell me if you got close to me or to the gpt */
        System.out.println(e2.evaluate(assignment)); // 25.0
        System.out.println(e3.evaluate(assignment)); // 8.0
        System.out.println(e4.evaluate(assignment)); // -1.5


        Expression e5 = e1.assign("x", new Var("z"));
        System.out.println(e5.toString()); // sin((2.0*z+y))

        Expression e6 = e2.assign("y", new Var("z"));
        System.out.println(e6.toString()); // (x+z)^2.0

        Expression e7 = e3.assign("x", new Var("y"));
        System.out.println(e7.toString()); // 4.0*y

        Expression e8 = e4.assign("y", new Num(2));
        System.out.println(e8.toString()); // (-2.0)/2.0
    }
}

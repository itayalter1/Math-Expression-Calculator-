import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class LogTest {

    @org.junit.jupiter.api.Test
    void testToString() {
    }
    private Map<String, Double> assignment;
    public void setUp() {
        assignment = new HashMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 3.0);
    }

    @org.junit.jupiter.api.Test
    public void testEvaluate() throws Exception {
        assignment = new HashMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 3.0);
        // Test evaluate() with positive values for both operands
        Expression log1 = new Log(new Num(2), new Num(8));
        assertEquals(3, log1.evaluate(), 0.001);

        // Test evaluate() with variable for the argument
        Expression log2 = new Log(new Num(2), new Var("x"));
        assertEquals(1, log2.evaluate(assignment), 0.001);

        // Test evaluate() with negative base and positive argument
        Expression log3 = new Log(new Num(-2), new Num(8));
        try {
            log3.evaluate();
            fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            assertEquals("the logarithm operation is undefined for"
                    + " numbers that are equal or smaller than 0", e.getMessage());
        }

        // Test evaluate() with positive base and negative argument
        Expression log4 = new Log(new Num(2), new Num(-8));
        try {
            log4.evaluate();
            fail("Expected an Exception to be thrown");
        } catch (Exception e) {
            assertEquals("the logarithm operation is undefined for"
                    + " numbers that are equal or smaller than 0", e.getMessage());
        }
    }
    @org.junit.jupiter.api.Test
    public void testAssign() {
        // Test assign() with variable for the base
        Expression log1 = new Log(new Var("x"), new Num(8));
        Expression assignedLog1 = log1.assign("x", new Num(2));
        assertEquals("log(2.0, 8.0)", assignedLog1.toString());

        // Test assign() with variable for the argument
        Expression log2 = new Log(new Num(2), new Var("y"));
        Expression assignedLog2 = log2.assign("y", new Num(3));
        assertEquals("log(2.0, 3.0)", assignedLog2.toString());
    }
}
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpressionEvaluatorTest {

    @Test
    public void testInfixToPostfix() {
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertEquals("3 4 +", evaluator.infixToPostfix("3 + 4"));
        assertEquals("5 1 2 + 4 * + 3 -", evaluator.infixToPostfix("5 + ((1 + 2) * 4) - 3"));
    }

    @Test
    public void testEvaluatePostfix() {
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertEquals(7.0, evaluator.evaluatePostfix("3 4 +"));
        assertEquals(14.0, evaluator.evaluatePostfix("5 1 2 + 4 * + 3 -"));
    }

    @Test
    public void testDivisionByZero() {
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            evaluator.evaluatePostfix("5 0 /");
        });
        assertEquals("Division by zero", exception.getMessage());
    }
}
import java.util.Scanner;

/**
 * Класс тела программы, принимающий на вход обычное математическое выражение
 * и на выходе получается постфикная форма этого выражения
 * и результат вычисления этого выражения
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическое выражение:");
        String expression = scanner.nextLine();

        ExpressionEvaluator evaluator = new ExpressionEvaluator();

        try {
            String postfix = evaluator.infixToPostfix(expression);
            System.out.println("Постфиксная форма: " + postfix);
            double result = evaluator.evaluatePostfix(postfix);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
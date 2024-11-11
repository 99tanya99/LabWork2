import java.util.Stack;

/**
 * Класс предоставляет методы для преобразования инфиксных выражений
 * в постфикный вид и вычисляет значение постфиксных выражений
 */
public class ExpressionEvaluator {

    /**
     *Преобразует инфиксное выражение в постфиксное
     * @param expression Инфиксное матемаьтическое выражение,
     * которое нужно преобразовать
     * @return Постфикное представление входного выражения в виде строки
     */
    public String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {
            if (Character.isDigit(ch)) {
                result.append(ch);
                result.append(' '); // добавляем пробел для разделения чисел
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                    result.append(' ');
                }
                stack.pop(); // удаляем '('
            } else if (isOperator(ch)) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch)) {
                    result.append(stack.pop());
                    result.append(' ');
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
            result.append(' ');
        }

        return result.toString().trim();
    }

    /**
     * Вычисляет значение постфикного выражения
     * @param postfix Постфиксное математическое выражение,
     * которое нужн вычислить
     * @return Результат вычисления постфикного выражения
     * как чсило с плавающей точкой
     */
    public double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();

        for (String token : postfix.split(" ")) {
            if (token.isEmpty()) continue;
            if (Character.isDigit(token.charAt(0))) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                double result = applyOperator(a, b, token.charAt(0));
                stack.push(result);
            }
        }

        return stack.pop();
    }

    /**
     * Проверяет является ли символ оператором
     * @param ch Символ, который нужно проверить
     * @return true-если сиивол является оператором, false-иначе
     */
    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    /**
     * Возвращает приоритет оператора
     * @param operator Оператор, для которого нужно определить приоритет
     * @return Целое число, представляющее приоритет оператора(больше число-болше приоритет)
     */
    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    /**
     * Применяет оператор к двум числам
     * @param a Первое число
     * @param b Второе число
     * @param operator Оператор, который нужно применить к числам
     * @return Результат применения оператора к числам
     * @throws ArithmeticException Если происходит деление на ноль.
     * @throws UnsupportedOperationException Если оператор неизвестен.
     */
    private double applyOperator(double a, double b, char operator) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            default: throw new UnsupportedOperationException("Unknown operator: " + operator);
        }
    }
}
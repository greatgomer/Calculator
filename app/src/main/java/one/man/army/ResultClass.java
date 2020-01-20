package one.man.army;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultClass {
    private String result;
    private ArrayList finalMass = new ArrayList();
    private Stack<Character> operations = new Stack<>();

    void resultCheker(String history) {                                                           //Проверка вводимой строки на наличие знаков математических действий и .
        Pattern pattern = Pattern.compile("[./*+-]$");
        Matcher matcher = pattern.matcher(history);
        if (matcher.find()) {
            this.result = history.substring(0, history.length() - 1);
        } else {
            this.result = history;
        }
        algoritm();
    }

    private int priorityAction(Character symbol) {                                                        //Приоритет действий
        switch (symbol) {
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return -1;
        }
    }

    private void algoritm() {                                                                            //Реализация алгоритма Обратной Польской Нотации
        String[] numbers = result.split("(?=[()/*+-])|(?<=[()/*+-])");
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i].matches("\\d+$") | (numbers[i].matches("\\d+(\\.\\d+)"))) {
                finalMass.add(numbers[i]);
            } else if (numbers[i].equals("(")) {
                operations.push(numbers[i].charAt(0));
            } else if (numbers[i].equals(")")) {
                Character nextStack = operations.pop();
                while (nextStack != '(' & !operations.empty()) {
                    finalMass.add(nextStack);
                    nextStack = operations.pop();
                }
            } else {
                if (!operations.empty()) {
                    Character stack = operations.pop();
                    Integer priorityOperations = priorityAction(numbers[i].charAt(0));
                    Integer priorityOperationsStack = priorityAction(stack);
                    if (priorityOperations <= priorityOperationsStack & stack != '(') {
                        finalMass.add(stack);
                        operations.push(numbers[i].charAt(0));
                    } else {
                        operations.push(stack);
                        operations.push(numbers[i].charAt(0));
                    }
                } else {
                    operations.push(numbers[i].charAt(0));
                }
            }
        }
        while (!operations.empty()) {
            finalMass.add(operations.pop());
        }
    }

    private void finalResult() {                                                                         //Результат
    }
}
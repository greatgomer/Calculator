package one.man.army;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultClass {
    private String result;
    private ArrayList finalMass = new ArrayList();
    private Stack<String> operations = new Stack<>();

    String resultCheker(String history) {                                                                //Проверка вводимой строки на наличие знаков математических действий и .
        history = history.replace("(-", "(0-");
        if(history.charAt(0) == '-') {
            history = "0" + history;
        }
        Pattern pattern = Pattern.compile("[./*+-]$");
        Matcher matcher = pattern.matcher(history);
        if (matcher.find()) {
            this.result = history.substring(0, history.length() - 1);
        } else {
            this.result = history;
        }
        algoritm();
        return String.valueOf(finalResult(algoritm()));
    }

    private int priorityAction(String symbol) {                                                        //Приоритет действий
        switch (symbol) {
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                return -1;
        }
    }

    private ArrayList algoritm() {                                                                          //Реализация алгоритма Обратной Польской Нотации
        String[] numbers = result.split("(?=[()/*+-])|(?<=[()/*+-])");
        numbers = negativeNumbers(numbers);
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i].matches("\\d+$") | (numbers[i].matches("\\d+(\\.\\d+)"))) {
                finalMass.add(numbers[i]);
            } else if (numbers[i].equals("(")) {
                operations.push(numbers[i]);
            } else if (numbers[i].equals(")")) {
                String nextStack = operations.pop();
                while (!nextStack.equals("(") & !operations.empty()) {
                    finalMass.add(nextStack);
                    nextStack = operations.pop();
                }
            } else {
                if (!operations.empty()) {
                    String stack = operations.pop();
                    int priorityOperations = priorityAction(numbers[i]);
                    int priorityOperationsStack = priorityAction(stack);
                    if (priorityOperations <= priorityOperationsStack & !stack.equals("(")) {
                        finalMass.add(stack);
                        operations.push(numbers[i]);
                    } else {
                        operations.push(stack);
                        operations.push(numbers[i]);
                    }
                } else {
                    operations.push(numbers[i]);
                }
            }
        }
        while (!operations.empty()) {
            finalMass.add(operations.pop());
        }

        return finalMass;
    }

    private double finalResult(ArrayList finalResult) {                                                   //Результат
        double firstNumber;
        double secondNumber;
        String resultCheker;
        double calculatorResult = 0.0;
        Stack<String> resultStack = new Stack<>();
        while (!finalResult.isEmpty()) {
            resultCheker = finalResult.get(0).toString();
            finalResult.remove(0);
            if (resultCheker.matches("\\d+$") | (resultCheker.matches("\\d+(\\.\\d+)"))) {
                resultStack.push(resultCheker);
            } else {
                if (resultCheker.equals("/")) {
                    secondNumber = Double.parseDouble(resultStack.pop());
                    firstNumber = Double.parseDouble(resultStack.pop());
                    calculatorResult = firstNumber / secondNumber;
                    resultStack.push(Double.toString(calculatorResult));
                } else if (resultCheker.equals("*")) {
                    secondNumber = Double.parseDouble(resultStack.pop());
                    firstNumber = Double.parseDouble(resultStack.pop());
                    calculatorResult = firstNumber * secondNumber;
                    resultStack.push(Double.toString(calculatorResult));
                }
                if (resultCheker.equals("+")) {
                    secondNumber = Double.parseDouble(resultStack.pop());
                    firstNumber = Double.parseDouble(resultStack.pop());
                    calculatorResult = firstNumber + secondNumber;
                    resultStack.push(Double.toString(calculatorResult));
                }
                if (resultCheker.equals("-")) {
                    secondNumber = Double.parseDouble(resultStack.pop());
                    firstNumber = Double.parseDouble(resultStack.pop());
                    calculatorResult = firstNumber - secondNumber;
                    resultStack.push(Double.toString(calculatorResult));
                }
            }
        }

        return calculatorResult;
    }

    private String[] negativeNumbers(String[] result) {                                                 //Функция обработки отрицательных чисел
        ArrayList arrayList = new ArrayList<String>();
        Collections.addAll(arrayList, result);
        String[] arr = new String[0];
        if (arrayList.get(0).equals("-")) {
            arrayList.add(0, "0");
            arrayList.add(0, "(");
            arrayList.add(4, ")");
        }
        for(int i = 0; i < arrayList.size()-1; i++){
            if(arrayList.get(i).equals("(") & arrayList.get(i+1).equals("-")) {
                arrayList.add(i+1, "0");
                arrayList.add(i+1, "(");
                arrayList.add(i+5, ")");
            }
        }
        arr = (String[]) arrayList.toArray(new String[arrayList.size()]);

        return arr;
    }

}
package one.man.army;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultClass{                                                                               //Реализация обратной польской анотации.
    Character symbol;

    public void resultCheker(String result) {                                                           //Проверка вводимой строки на наличие знаков математических действий и .
        Pattern pattern = Pattern.compile("[./*+-]$");
        Matcher matcher = pattern.matcher(result);
        if (matcher.find()) {
            result = result.substring(0, result.length() -1);
        }
    }

    public int priorityAction(){                                                                       //Приоритет действий
        switch (symbol){
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
}

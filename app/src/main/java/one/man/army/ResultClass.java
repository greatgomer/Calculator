package one.man.army;

public class ResultClass{                                                                               //Реализация обратной польской анотации.
    Character symbol;
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

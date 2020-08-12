package one.man.army;

public class ButtonsNumbers {

    public String history, result, act, debugNumbers;

    public ButtonsNumbers(String history, String result) {
        this.history = history;
        this.result = result;
    }

    public ButtonsNumbers(String history, String result, String act, String debugNumbers) {
        this.history = history;
        this.result = result;
        this.act = act;
        this.debugNumbers = debugNumbers;
    }

    public void zeroButton() {                                                                          //Функция обработки кнопки 0
        if ((history.length() == 0) || (result.length() > 1)) {
            result += "0";
            history += "0";
        } else if (history.charAt(history.length()-1) != '0' & history.charAt(history.length()-1) != ')' || history.charAt(history.length()-1) == '('){
            result += "0";
            history += "0";
        }
    }

    public void numbersButtons() {                                                                       //Функция обработки кнопок от 1 до 9
        if (result.equals("") & act.equals("") & history.equals("")) {                                    //Ошибка при которой перед цифрой не затирался 0
            result = debugNumbers;
            history += debugNumbers;
        } else if (history.length() > 0 & history.charAt(history.length()-1) != ')'){
            result += debugNumbers;
            history += debugNumbers;
        }
    }

}

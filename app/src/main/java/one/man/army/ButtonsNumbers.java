package one.man.army;

public class ButtonsNumbers {

    public String history, result;

    public ButtonsNumbers(String history, String result){
        this.history = history;
        this.result = result;
    }

    public void zeroButton(){
        if((history.length() == 0) || (result.length() > 1)){
            result += "0";
            history += "0";
        }else if(history.charAt(history.length()-1) != '0' & history.charAt(history.length()-1) != ')' || history.charAt(history.length()-1) == '('){
            result += "0";
            history += "0";
        }
    }
}

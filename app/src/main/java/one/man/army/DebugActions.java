package one.man.army;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DebugActions {

    String history, result, act;

    public DebugActions(String history, String result, String act){
        this.history = history;
        this.result = result;
        this.act = act;
    }

    public void actionButtons(){
        Pattern pattern = Pattern.compile("\\d$");
        Matcher matcher = pattern.matcher(history);
        if(history.length() > 0){
            if((matcher.find()) || (history.charAt(history.length()-1) == ')')){
                result = "";
                history += act;
            }
        }else{
            act = "";
        }
    }

    public void actionMinus(){
        Pattern pattern = Pattern.compile("\\d$");
        Matcher matcher = pattern.matcher(history);
        if(history.length() > 0){
            if((matcher.find()) || (history.charAt(history.length()-1) == ')')){
                result = "";
                history += "-";
            }
        }else{
            result = "-";
            history = "-";
        }
    }
}

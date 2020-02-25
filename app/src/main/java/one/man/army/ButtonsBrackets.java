package one.man.army;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ButtonsBrackets {
    Integer openBrackets = 0, closeBrackets = 0;

    public String openBrackets(String history, String result){
        Pattern pattern = Pattern.compile("[/*+-]$");
        Matcher matcher = pattern.matcher(history);
        if((result.length() == 0 & history.length() == 0) | matcher.find()){
            history += "(";
        }
        return history;
    }

    public String closeBrackets(String history, String result){
        Pattern pattern = Pattern.compile("\\d$");
        Matcher matcher = pattern.matcher(history);
        for (int i = 0; i < history.length(); i++){
            if(history.charAt(i) == '('){
                openBrackets ++;
            }else if(history.charAt(i) == ')') {
                closeBrackets++;
            }
        }
        if((!openBrackets.equals(closeBrackets)) & (matcher.find())){
            history += ")";
        }
        openBrackets = 0;
        closeBrackets = 0;
        return history;
    }
}

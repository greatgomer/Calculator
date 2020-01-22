package one.man.army;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends AppCompatActivity {

    TextView resultView, historyView, historyUpView, temporaryResultView;
    Button btnPoint, btnResult, btnSplit, btnMultiply, btnPlus, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine;
    String result = "", history = "",debugNumbers = "", act = "", historyGlob = "";
    Integer openBrackets = 0, closeBrackets = 0;
    ResultClass resultClass = new ResultClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        resultView = findViewById(R.id.textViewResult);
        historyView = findViewById(R.id.textViewHist);
        historyUpView = findViewById(R.id.textViewHistory);
        temporaryResultView = findViewById(R.id.textTemporaryResult);
        btnPoint = findViewById(R.id.buttonPoint);
        btnResult = findViewById(R.id.buttonResult);
        btnOne = findViewById(R.id.buttonOne);
        btnTwo = findViewById(R.id.buttonTwo);
        btnThree = findViewById(R.id.buttonThree);
        btnFour = findViewById(R.id.buttonFour);
        btnFive = findViewById(R.id.buttonFive);
        btnSix = findViewById(R.id.buttonSix);
        btnSeven = findViewById(R.id.buttonSeven);
        btnEight = findViewById(R.id.buttonEight);
        btnNine = findViewById(R.id.buttonNine);
        btnSplit = findViewById(R.id.buttonSplit);
        btnMultiply = findViewById(R.id.buttonMultiply);
        btnPlus = findViewById(R.id.buttonPlus);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                  //Обработка кнопок от 1 до 9, математических действий /, *, +, - и результата
                switch (v.getId()){
                    case R.id.buttonResult:
                        resultButton();
                        break;
                    case R.id.buttonOne:
                        debugNumbers = "1";
                        debugNumbersError();
                        break;
                    case R.id.buttonTwo:
                        debugNumbers = "2";
                        debugNumbersError();
                        break;
                    case R.id.buttonThree:
                        debugNumbers = "3";
                        debugNumbersError();
                        break;
                    case R.id.buttonFour:
                        debugNumbers = "4";
                        debugNumbersError();
                        break;
                    case R.id.buttonFive:
                        debugNumbers = "5";
                        debugNumbersError();
                        break;
                    case R.id.buttonSix:
                        debugNumbers = "6";
                        debugNumbersError();
                        break;
                    case R.id.buttonSeven:
                        debugNumbers = "7";
                        debugNumbersError();
                        break;
                    case R.id.buttonEight:
                        debugNumbers = "8";
                        debugNumbersError();
                        break;
                    case R.id.buttonNine:
                        debugNumbers = "9";
                        debugNumbersError();
                        break;
                    case R.id.buttonSplit:
                        act = "/";
                        debugActionError();
                        break;
                    case R.id.buttonMultiply:
                        act = "*";
                        debugActionError();
                        break;
                    case R.id.buttonPlus:
                        act = "+";
                        debugActionError();
                        break;
                }
            }
        };
        btnResult.setOnClickListener(onClickListener);
        btnOne.setOnClickListener(onClickListener);
        btnTwo.setOnClickListener(onClickListener);
        btnThree.setOnClickListener(onClickListener);
        btnFour.setOnClickListener(onClickListener);
        btnFive.setOnClickListener(onClickListener);
        btnSix.setOnClickListener(onClickListener);
        btnSeven.setOnClickListener(onClickListener);
        btnEight.setOnClickListener(onClickListener);
        btnNine.setOnClickListener(onClickListener);
        btnSplit.setOnClickListener(onClickListener);
        btnMultiply.setOnClickListener(onClickListener);
        btnPlus.setOnClickListener(onClickListener);

        historyUpView.setOnClickListener(new View.OnClickListener() {                                   //Сброс истории действий при нажатии на элемент
            @Override
            public void onClick(View v) {
                historyGlob = "";
                historyView.setText(historyGlob);
                historyUpView.setText(R.string.history);
            }
        });
    }

    public void onClickButtonOpenBracket(View view) {                                                   //Обработчики кнопок скобок
        Pattern pattern = Pattern.compile("[/*+-]$");
        Matcher matcher = pattern.matcher(history);
        if(result.length() == 0 & history.length() == 0){
            history += "(";
            temporaryResultView.setText(history);
        }else if(matcher.find()){
            history += "(";
            temporaryResultView.setText(history);
        }
    }

    public void onClickButtonCloseBracket(View view) {
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
            temporaryResultView.setText(history);
        }
        openBrackets = 0;
        closeBrackets = 0;
        resultView.setText(result);
    }

    public void onClickButtonDelete(View view) {                                                        //Обработчик кнопки удаления по одному символу
        deleteLastSymbol();
    }

    public void onClickButtonAC(View view) {                                                            //Обработчик кнопки полной очистки
        cleanAll();
    }

    public void onClickButtonZero(View view) {                                                          //Обработчик кнопки 0
        if(history.length() == 0){
            result += "0";
            history += "0";
            resultView.setText(result);
            temporaryResultView.setText(history);
        }else if(history.charAt(history.length()-1) != '0' & history.charAt(history.length()-1) != ')' | history.charAt(history.length()-1) == '('){
            result += "0";
            history += "0";
            resultView.setText(result);
            temporaryResultView.setText(history);
        }else if(result.length() > 1){
            result += "0";
            history += "0";
            resultView.setText(result);
            temporaryResultView.setText(history);
        }
    }

    public void onClickButtonPoint(View view) {                                                         //Обработчик кнопки .
        Pattern pattern = Pattern.compile("\\d$");
        Matcher matcher = pattern.matcher(history);
        if(result.indexOf('.') == -1 & matcher.find()){
            result += ".";
            history += ".";
            resultView.setText(result);
            temporaryResultView.setText(history);
        }
        }

    public void debugNumbersError(){                                                                    //Функция обработки ошибок при вводе знаков от 1 до 9
        if(result.equals("") & act.equals("") & history.equals("")){                                    //Ошибка при которой перед цифрой не затирался 0
            result = debugNumbers;
            history += debugNumbers;
            resultView.setText(result);
            temporaryResultView.setText(history);
        }else if(history.length() > 0 & history.charAt(history.length()-1) != '0' & history.charAt(history.length()-1) != ')'){
            result += debugNumbers;
            history += debugNumbers;
            resultView.setText(result);
            temporaryResultView.setText(history);
        }
    }

        public void debugActionError() {                                                                //Функция обработки ошибок при вводе знаков математических дейтвий /, *, +
            Pattern pattern = Pattern.compile("\\d$");
            Matcher matcher = pattern.matcher(history);
            if(matcher.find()){
                result = "";
                resultView.setText(result);
                history += act;
                temporaryResultView.setText(history);
            }
        }

    public void onClickMinus(View view) {                                                               //Функция обработки -
        Pattern pattern = Pattern.compile("\\d$");
        Matcher matcher = pattern.matcher(history);
        if(result.equals("") & history.equals("")){
            result = "-";
            resultView.setText("");
            history = "-";
            temporaryResultView.setText(history);
        }else if(history.charAt(history.length()-1) == '('){
            result += "-";
            resultView.setText("");
            history += "-";
            temporaryResultView.setText(history);
        }else if(matcher.find()){
            result += "-";
            resultView.setText("");
            history += "-";
            temporaryResultView.setText(history);
        }
    }

        public void deleteLastSymbol(){                                                                 //Функция удаления последнего символа
            if(result.length() == 0 & history.length() == 0){
                cleanAll();
            }else if(result.length() == 0 & history.length() != 0){                                     //Очистка истории, при пустом result
                history = history.substring(0, history.length() -1);
                temporaryResultView.setText(history);
            }else if(history.length() == 0){                                                            //Случай, когда history пуста
             cleanAll();
            }else{                                                                                      //Обычный случай
                result = result.substring(0, result.length() -1);
                history = history.substring(0, history.length() -1);
                resultView.setText(result);
                temporaryResultView.setText(history);
            }
        }

        public void cleanAll(){                                                                         //Функция очистки экрана, кроме истории
            temporaryResultView.setText("");
            resultView.setText("0");
            debugNumbers = "";
            history = "";
            result = "";
            act = "";
        }

    public void resultButton() {                                                                         //Функция обработки результата
        if(history.length() == 0){
            cleanAll();
        }else{
            String finalResult = resultClass.resultCheker(history);
            resultView.setText(finalResult);
            historyOutput(finalResult);
            result = "";
            act ="";
        }
    }
    public void historyOutput(String result){                                                           //Сохранение истории дейсвий
        historyGlob = historyGlob + history + " = " + result + "\n";
        historyView.setText(historyGlob);
        history = "";
        historyUpView.setText(R.string.historyUp);
    }
}


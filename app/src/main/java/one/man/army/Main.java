package one.man.army;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {

    TextView resultView, historyView, historyUpView, temporaryResultView;
    Button btnPoint, btnResult, btnSplit, btnMultiply, btnMinus, btnPlus, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine;
    String result = "", history = "",debugNumbers = "", act = "", historyGlob = "";
    Integer openBrackets = 0, closeBrackets = 0;

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
        btnMinus = findViewById(R.id.buttonMinus);
        btnPlus = findViewById(R.id.buttonPlus);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                                //Обработка кнопок от 1 до 9, математических действий /, *, +, - и результата
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
                    case R.id.buttonMinus:
                        act = "-";
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
        btnMinus.setOnClickListener(onClickListener);

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
        if(result.length() == 0){
            result += "(";
            history += "(";
            resultView.setText(result);
            temporaryResultView.setText(history);
        }else if((history.charAt(history.length()-1) == '/') | (history.charAt(history.length()-1) == '*') | (history.charAt(history.length()-1) == '+') | (history.charAt(history.length()-1) == '-')){
            result += "(";
            history += "(";
            resultView.setText(result);
            temporaryResultView.setText(history);
        }
    }

    public void onClickButtonCloseBracket(View view) {
        for (int i = 0; i < history.length(); i++){
            if(history.charAt(i) == '('){
                openBrackets ++;
            }else if(history.charAt(i) == ')') {
                closeBrackets++;
            }
        }
        if((openBrackets != closeBrackets) & ((history.charAt(history.length()-1) == '1') | (history.charAt(history.length()-1) == '2') | (history.charAt(history.length()-1) == '3') | (history.charAt(history.length()-1) == '4') | (history.charAt(history.length()-1) == '5') | (history.charAt(history.length()-1) == '6') | (history.charAt(history.length()-1) == '7') | (history.charAt(history.length()-1) == '8') | (history.charAt(history.length()-1) == '9') | (history.charAt(history.length()-1) == '0'))){
            result += ")";
            history += ")";
            resultView.setText(result);
            temporaryResultView.setText(history);
        }
        openBrackets = 0;
        closeBrackets = 0;
    }

    public void onClickButtonDelete(View view) {                                                        //Обработчик кнопки удаления по одному символу
        deleteLastSymbol();
    }

    public void onClickButtonAC(View view) {                                                            //Обработчик кнопки полной очистки
        cleanAll();
    }

    public void onClickButtonZero(View view) {                                                          //Обработчик кнопки 0
        if(result.length() > 0){
            result += "0";
            history += "0";
            resultView.setText(result);
            temporaryResultView.setText(history);
        }
    }

    public void onClickButtonPoint(View view) {                                                         //Обработчик кнопки .
        if(result.indexOf('.') == -1 & result.length() != 0){
            result += ".";
            history += ".";
            resultView.setText(result);
            temporaryResultView.setText(history);
        }else if(result.length() == 0){
            result += "0.";
            history += "0.";
            resultView.setText(result);
            temporaryResultView.setText(history);
        }
        }

    public void debugNumbersError(){                                                                    //Функция обработки ошибок при вводе знаков от 1 до 9
        if(result == "" & act == ""){                                                                   //Ошибка при которой перед цифрой не затирался 0
            result = debugNumbers;
            history = debugNumbers;
            resultView.setText(result);
            temporaryResultView.setText(history);
        }else if(result != "" & act == ""){                                                             //Ввод чисел
            result += debugNumbers;
            history += debugNumbers;
            resultView.setText(result);
            temporaryResultView.setText(history);
        }else if(act != ""){                                                                            //ввод чисел после указанного математического действия
            result += debugNumbers;
            history += debugNumbers;
            resultView.setText(result);
            temporaryResultView.setText(history);
        }
    }

        public void debugActionError() {                                                                //Функция обработки ошибок при вводе знаков математических дейтвий /, *, +, -
            if (result.length() != 0 & history.length() != 0) {                                                                         //Ввод знака
                result = "";
                resultView.setText("");
                history += act;
                temporaryResultView.setText(history);
            }else if(history.length() == 0){
                cleanAll();
            }else if((history.charAt(history.length()-1) == '/') | (history.charAt(history.length()-1) == '*') | (history.charAt(history.length()-1) == '+') | (history.charAt(history.length()-1) == '-')){         //При введенном знаке заменяет его другим, при повторном выборе знака
                deleteLastSymbol();
                history += act;
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
        }else if((history.charAt(history.length()-1) == '/') | (history.charAt(history.length()-1) == '*') | (history.charAt(history.length()-1) == '+') | (history.charAt(history.length()-1) == '-') & history.length() != 0){
        deleteLastSymbol();
        historyOutput();
        }else if(history.charAt(history.length()-1) == '.'){
            deleteLastSymbol();
        }else{
            historyOutput();
        }
    }
    public void historyOutput(){                                                                        //Сохранение истории дейсвий
        historyGlob = historyGlob + history + " = " + result + "\n";
        historyView.setText(historyGlob);
        history = "";
        historyUpView.setText(R.string.historyUp);
    }
}

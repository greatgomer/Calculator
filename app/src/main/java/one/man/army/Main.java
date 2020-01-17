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
    Integer openBreckets = 0, closeBrackets = 0;
    ResultClass resultClass = new ResultClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        resultView = (TextView) findViewById(R.id.textViewResult);
        historyView = (TextView) findViewById(R.id.textViewHist);
        historyUpView = (TextView) findViewById(R.id.textViewHistory);
        temporaryResultView = (TextView) findViewById(R.id.textTemporaryResult);
        btnPoint = (Button) findViewById(R.id.buttonPoint);
        btnResult = (Button) findViewById(R.id.buttonResult);
        btnOne = (Button) findViewById(R.id.buttonOne);
        btnTwo = (Button) findViewById(R.id.buttonTwo);
        btnThree = (Button) findViewById(R.id.buttonThree);
        btnFour = (Button) findViewById(R.id.buttonFour);
        btnFive = (Button) findViewById(R.id.buttonFive);
        btnSix = (Button) findViewById(R.id.buttonSix);
        btnSeven = (Button) findViewById(R.id.buttonSeven);
        btnEight = (Button) findViewById(R.id.buttonEight);
        btnNine = (Button) findViewById(R.id.buttonNine);
        btnSplit = (Button) findViewById(R.id.buttonSplit);
        btnMultiply = (Button) findViewById(R.id.buttonMultiply);
        btnMinus = (Button) findViewById(R.id.buttonMinus);
        btnPlus = (Button) findViewById(R.id.buttonPlus);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                                                //Обработка кнопок от 1 до 9, математических действий /, *, +, - и результата
                switch (v.getId()){
                    case R.id.buttonResult:
                        ResultButton();
                        break;
                    case R.id.buttonOne:
                        debugNumbers = "1";
                        DebugNumbersError();
                        break;
                    case R.id.buttonTwo:
                        debugNumbers = "2";
                        DebugNumbersError();
                        break;
                    case R.id.buttonThree:
                        debugNumbers = "3";
                        DebugNumbersError();
                        break;
                    case R.id.buttonFour:
                        debugNumbers = "4";
                        DebugNumbersError();
                        break;
                    case R.id.buttonFive:
                        debugNumbers = "5";
                        DebugNumbersError();
                        break;
                    case R.id.buttonSix:
                        debugNumbers = "6";
                        DebugNumbersError();
                        break;
                    case R.id.buttonSeven:
                        debugNumbers = "7";
                        DebugNumbersError();
                        break;
                    case R.id.buttonEight:
                        debugNumbers = "8";
                        DebugNumbersError();
                        break;
                    case R.id.buttonNine:
                        debugNumbers = "9";
                        DebugNumbersError();
                        break;
                    case R.id.buttonSplit:
                        act = "/";
                        DebugActionError();
                        break;
                    case R.id.buttonMultiply:
                        act = "*";
                        DebugActionError();
                        break;
                    case R.id.buttonPlus:
                        act = "+";
                        DebugActionError();
                        break;
                    case R.id.buttonMinus:
                        act = "-";
                        DebugActionError();
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
            result = result + "(";
            history = history + "(";
            resultView.setText(result);
            temporaryResultView.setText(history);
        }else if((history.charAt(history.length()-1) == '/') | (history.charAt(history.length()-1) == '*') | (history.charAt(history.length()-1) == '+') | (history.charAt(history.length()-1) == '-')){
            result = result + "(";
            history = history + "(";
            resultView.setText(result);
            temporaryResultView.setText(history);
        }
    }

    public void onClickButtonCloseBracket(View view) {
        for (int i = 0; i < history.length(); i++){
            if(history.charAt(i) == '('){
                openBreckets ++;
            }else if(history.charAt(i) == ')') {
                closeBrackets++;
            }
        }
        if((openBreckets != closeBrackets) & ((history.charAt(history.length()-1) == '1') | (history.charAt(history.length()-1) == '2') | (history.charAt(history.length()-1) == '3') | (history.charAt(history.length()-1) == '4') | (history.charAt(history.length()-1) == '5') | (history.charAt(history.length()-1) == '6') | (history.charAt(history.length()-1) == '7') | (history.charAt(history.length()-1) == '8') | (history.charAt(history.length()-1) == '9') | (history.charAt(history.length()-1) == '0'))){
            result = result + ")";
            history = history + ")";
            resultView.setText(result);
            temporaryResultView.setText(history);
        }
        openBreckets = 0;
        closeBrackets = 0;
    }

    public void onClickButtonDelete(View view) {                                                        //Обработчик кнопки удаления по одному символу
        DeleteLastSumbol();
    }

    public void onClickButtonAC(View view) {                                                            //Обработчик кнопки полной очистки
        CleanAll();
    }

    public void onClickButtonZero(View view) {                                                          //Обработчик кнопки 0
        if(result.length() > 0){
            result = result + "0";
            history = history + "0";
            resultView.setText(result);
            temporaryResultView.setText(history);
        }
    }

    public void onClickButtonPoint(View view) {                                                         //Обработчик кнопки .
        if(result.indexOf('.') == -1 & result.length() != 0){
            result = result + ".";
            history = history + ".";
            resultView.setText(result);
            temporaryResultView.setText(history);
        }else if(result.length() == 0){
            result = result + "0.";
            history = history + "0.";
            resultView.setText(result);
            temporaryResultView.setText(history);
        }
        }

    public void DebugNumbersError(){                                                                    //Функция обработки ошибок при вводе знаков от 1 до 9
        if(result == "" & act == ""){                                                                   //Ошибка при которой перед цифрой не затирался 0
            result = debugNumbers;
            history = debugNumbers;
            resultView.setText(result);
            temporaryResultView.setText(history);
        }else if(result != "" & act == ""){                                                             //Ввод чисел
            result = result + debugNumbers;
            history = history + debugNumbers;
            resultView.setText(result);
            temporaryResultView.setText(history);
        }else if(act != ""){                                                                            //ввод чисел после указанного математического действия
            result = result + debugNumbers;
            history = history + debugNumbers;
            resultView.setText(result);
            temporaryResultView.setText(history);
        }
    }

        public void DebugActionError() {                                                                //Функция обработки ошибок при вводе знаков математических дейтвий /, *, +, -
            if (result.length() != 0 & history.length() != 0) {                                                                         //Ввод знака
                result = "";
                resultView.setText("");
                history = history + "" + act + "";
                temporaryResultView.setText(history);
            }else if(history.length() == 0){
                CleanAll();
            }else if((history.charAt(history.length()-1) == '/') | (history.charAt(history.length()-1) == '*') | (history.charAt(history.length()-1) == '+') | (history.charAt(history.length()-1) == '-')){         //При введенном знаке заменяет его другим, при повторном выборе знака
                DeleteLastSumbol();
                history = history + "" + act + "";
                temporaryResultView.setText(history);
            }
        }

        public void DeleteLastSumbol(){                                                                 //Функция удаления последнего символа
            if(result.length() == 0 & history.length() == 0){
                CleanAll();
            }else if(result.length() == 0 & history.length() != 0){                                     //Очистка истории, при пустом result
                history = history.substring(0, history.length() -1);
                temporaryResultView.setText(history);
            }else if(history.length() == 0){                                                            //Случай, когда history пуста
             CleanAll();
            }else{                                                                                      //Обычный случай
                result = result.substring(0, result.length() -1);
                history = history.substring(0, history.length() -1);
                resultView.setText(result);
                temporaryResultView.setText(history);
            }
        }

        public void CleanAll(){                                                                         //Функция очистки экрана, кроме истории
            temporaryResultView.setText("");
            resultView.setText("0");
            debugNumbers = "";
            history = "";
            result = "";
            act = "";
        }

    public void ResultButton() {                                                                         //Функция обработки результата
        if(history.length() == 0){
            CleanAll();
        }else if((history.charAt(history.length()-1) == '/') | (history.charAt(history.length()-1) == '*') | (history.charAt(history.length()-1) == '+') | (history.charAt(history.length()-1) == '-') & history.length() != 0){
        DeleteLastSumbol();
        resultClass.Alhorithm(history);
        HistoryOutput();
        }else{
            resultClass.Alhorithm(history);
            HistoryOutput();
        }
    }
    public void HistoryOutput(){                                                                        //Сохранение истории дейсвий
        historyGlob = historyGlob + history + " = " + result + "\n";
        historyView.setText(historyGlob);
        history = "";
        historyUpView.setText(R.string.historyUp);
    }
}


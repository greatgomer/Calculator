package one.man.army;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.jakewharton.rxbinding4.view.RxView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import one.man.army.databinding.LayoutMainBinding;
public class Main extends AppCompatActivity {

    String result = "", history = "",debugNumbers = "", act = "", historyGlob = "";
    ResultClass resultClass = new ResultClass();
    ButtonsBrackets buttonsBrackets = new ButtonsBrackets();
    LayoutMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.layout_main);

        RxView.clicks(binding.buttonOne).subscribe(aVoid ->{debugNumbers = "1"; debugNumbersError();}).isDisposed();
        RxView.clicks(binding.buttonTwo).subscribe(aVoid ->{debugNumbers = "2"; debugNumbersError();}).isDisposed();
        RxView.clicks(binding.buttonThree).subscribe(aVoid ->{debugNumbers = "3"; debugNumbersError();}).isDisposed();
        RxView.clicks(binding.buttonFour).subscribe(aVoid ->{debugNumbers = "4"; debugNumbersError();}).isDisposed();
        RxView.clicks(binding.buttonFive).subscribe(aVoid ->{debugNumbers = "5"; debugNumbersError();}).isDisposed();
        RxView.clicks(binding.buttonSix).subscribe(aVoid ->{debugNumbers = "6"; debugNumbersError();}).isDisposed();
        RxView.clicks(binding.buttonSeven).subscribe(aVoid ->{debugNumbers = "7"; debugNumbersError();}).isDisposed();
        RxView.clicks(binding.buttonEight).subscribe(aVoid ->{debugNumbers = "8"; debugNumbersError();}).isDisposed();
        RxView.clicks(binding.buttonNine).subscribe(aVoid ->{debugNumbers = "9"; debugNumbersError();}).isDisposed();
        RxView.clicks(binding.buttonResult).subscribe(aVoid -> resultButton()).isDisposed();
        RxView.clicks(binding.buttonSplit).subscribe(aVoid -> {act = "/"; debugActionError();}).isDisposed();
        RxView.clicks(binding.buttonMultiply).subscribe(aVoid -> {act = "*"; debugActionError();}).isDisposed();
        RxView.clicks(binding.buttonPlus).subscribe(aVoid -> {act = "+"; debugActionError();}).isDisposed();

        //Сброс истории действий при нажатии на элемент
        binding.textViewHistory.setOnClickListener(v -> {
            historyGlob = "";
            binding.textViewHist.setText(historyGlob);
            binding.textViewHistory.setText(R.string.history);
        });
    }

    public void onClickButtonOpenBracket(View view) {
        history = buttonsBrackets.openBrackets(history, result);
        binding.textTemporaryResult.setText(history);
    }

    public void onClickButtonCloseBracket(View view) {
        history = buttonsBrackets.closeBrackets(history, result);
        binding.textTemporaryResult.setText(history);
        binding.textViewResult.setText(result);
    }

    public void onClickButtonDelete(View view) {                                                        //Обработчик кнопки удаления по одному символу
        deleteLastSymbol();
    }

    public void onClickButtonAC(View view) {                                                            //Обработчик кнопки полной очистки
        cleanAll();
    }

    public void onClickButtonZero(View view) {
        ButtonsNumbers buttonsNumbers = new ButtonsNumbers(history, result);
        buttonsNumbers.zeroButton();
        history = buttonsNumbers.history;
        result = buttonsNumbers.result;
        binding.textTemporaryResult.setText(history);
        binding.textViewResult.setText(result);
    }

    public void onClickButtonPoint(View view) {
        Pattern pattern = Pattern.compile("\\d$");
        Matcher matcher = pattern.matcher(history);
        if (result.indexOf('.') == -1 & matcher.find()) {
            result += ".";
            history += ".";
            binding.textViewResult.setText(result);
            binding.textTemporaryResult.setText(history);
        }
    }

    public void debugNumbersError(){
        ButtonsNumbers buttonsNumbers = new ButtonsNumbers(history, result, act, debugNumbers);
        buttonsNumbers.numbersButtons();
        history = buttonsNumbers.history;
        result = buttonsNumbers.result;
        binding.textViewResult.setText(result);
        binding.textTemporaryResult.setText(history);
    }

    public void debugActionError() {
        DebugActions debugActions = new DebugActions(history, result, act);
        debugActions.actionButtons();
        history = debugActions.history;
        result = debugActions.result;
        binding.textViewResult.setText(result);
        binding.textTemporaryResult.setText(history);
    }

    public void onClickMinus(View view) {
        DebugActions debugActions = new DebugActions(history, result, act);
        debugActions.actionMinus();
        history = debugActions.history;
        result = debugActions.result;
        binding.textViewResult.setText(result);
        binding.textTemporaryResult.setText(history);
    }

    public void deleteLastSymbol() {
        if (result.length() == 0 & history.length() == 0) {
            cleanAll();
        } else if (result.length() == 0 & history.length() != 0) {
            history = history.substring(0, history.length() -1);
            binding.textTemporaryResult.setText(history);
        } else if (history.length() == 0) {
            cleanAll();
        } else {
            result = result.substring(0, result.length() -1);
            history = history.substring(0, history.length() -1);
            binding.textViewResult.setText(result);
            binding.textTemporaryResult.setText(history);
        }
    }

    public void cleanAll() {
        binding.textTemporaryResult.setText("");
        binding.textViewResult.setText("0");
        debugNumbers = "";
        history = "";
        result = "";
        act = "";
    }

    public void resultButton() {
        if (result.length() == 0) {
            cleanAll();
        } else {
            String finalResult = resultClass.resultCheker(history);
            binding.textViewResult.setText(finalResult);
            historyOutput(finalResult);
            history = finalResult;
            binding.textTemporaryResult.setText(history);
            result = "";
        }
    }
    public void historyOutput(String result) {
        historyGlob = historyGlob + history + " = " + result + "\n";
        binding.textViewHist.setText(historyGlob);
        history = "";
        binding.textViewHistory.setText(R.string.historyUp);
    }

}


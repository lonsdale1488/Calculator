package com.sersh.lesson19;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;


public class Main extends AppCompatActivity implements View.OnClickListener {
    private Button one, two, three, four, five, six, seven, eigth, nine, zero;
    private Button plus, minus, multiplication, division, step, result, delete, ligthDelete, dot, plusminus;
    private final int MENU_QUIT_ID = 2;
    private TextView textView, textView2;
    private ListView listView;
    private boolean actionSwitch = true;
    private boolean resultSwitch = true;
    private boolean deleteSwitch = true;
    private boolean LasSing = true;
    private boolean dotSwitch = true;
    private boolean plusMinusSwitch = true;
    private String forNumberOne = "", forNumberTwo = "", sing = "", contein, textForView = "";
    private final String LOG_TAG = "calc";
    ArrayList<String> dat;
    ArrayAdapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAdapter ();
        init();
    }

    private void initAdapter () {
        dat = new ArrayList<>();
        Adapter = new ArrayAdapter(this, R.layout.item, dat);
        listView = (ListView) findViewById(R.id.history);
        listView.setAdapter(Adapter);
      //  adapterLisener ();
    }

    private void adapterLisener ()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int number = position;
                actionSwitch = true;
                resultSwitch = true;
                deleteSwitch = true;
                forNumberOne = "";
                forNumberTwo = "";
                sing = "";
            }
        });
    }
    private void init() {
        textView = (TextView) findViewById(R.id.tvResult);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eigth = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        zero = (Button) findViewById(R.id.zero);
        dot = (Button) findViewById(R.id.dot);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        multiplication = (Button) findViewById(R.id.multiply);
        division = (Button) findViewById(R.id.divate);
        step = (Button) findViewById(R.id.step);
        result = (Button) findViewById(R.id.result);
        delete = (Button) findViewById(R.id.delete);
        ligthDelete = (Button) findViewById(R.id.ligthDelete);
        plusminus = (Button) findViewById(R.id.plusminus);
        initLisener();


    }

    private void initLisener() {
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eigth.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        dot.setOnClickListener(this);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        multiplication.setOnClickListener(this);
        division.setOnClickListener(this);
        step.setOnClickListener(this);
        result.setOnClickListener(this);
        delete.setOnClickListener(this);
        ligthDelete.setOnClickListener(this);
        plusminus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one:
                textForView = textForView + "1";
                break;
            case R.id.two:
                textForView = textForView + "2";
                break;
            case R.id.three:
                textForView = textForView + "3";
                break;
            case R.id.four:
                textForView = textForView + "4";
                break;
            case R.id.five:
                textForView = textForView + "5";
                break;
            case R.id.six:
                textForView = textForView + "6";
                break;
            case R.id.seven:
                textForView = textForView + "7";
                break;
            case R.id.eight:
                textForView = textForView + "8";
                break;
            case R.id.nine:
                textForView = textForView + "9";
                break;
            case R.id.zero:
                textForView = textForView + "0";
                break;
            case R.id.dot:
                if (dotSwitch) {
                    textForView = textForView + ".";
                }
                dotSwitch = false;
                break;
            case R.id.delete:
                deleteSwitch = false;
                break;
            case R.id.ligthDelete:
                LasSing = false;
                break;
            case R.id.result:
                if (!actionSwitch) {
                    resultSwitch = false;
                }
                break;
            case R.id.plus:
                sing = "+";
                actionSing();
                break;
            case R.id.minus:
                sing = "-";
                actionSing();
                break;
            case R.id.multiply:
                sing = "*";
                actionSing();
                break;
            case R.id.divate:
                sing = "/";
                actionSing();
                break;
            case R.id.step:
                sing = "^";
                actionSing();
                break;
            case R.id.plusminus:
                plusMinusSwitch = false;
                break;
        }

        if (!resultSwitch) {
            resultAction();
        } else {
            if (!actionSwitch) {
                forNumberTwo = forNumberTwo + textForView;
                if (forNumberTwo.equals("")) {
                    if (!LasSing) {
                        sing = "";
                        actionSwitch = true;
                    }
                }
                if (!LasSing) {
                    forNumberTwo = deliteLastSing(forNumberTwo);
                }
                if (!plusMinusSwitch) {
                    forNumberTwo = plusMinusAction(forNumberTwo);
                }

            } else {
                if (forNumberOne.equals("0")) {
                    forNumberOne = "";
                }
                forNumberOne = forNumberOne + textForView;
                if (!LasSing) {
                    forNumberOne = deliteLastSing(forNumberOne);
                }
                if (!plusMinusSwitch) {
                    forNumberOne = plusMinusAction(forNumberOne);
                }
            }
        }
        remuvAction();
        if (forNumberOne.equals("")) {
            forNumberOne = "0";
        }
        contein = forNumberOne + sing + forNumberTwo;
        textView.setText(contein);
        textForView = "";
    }

    private String deliteLastSing(String chang) {
        int size = chang.length();
        char[] chars = chang.toCharArray();
        chang = "";
        Log.d(LOG_TAG, "metodwork");
        for (int i = 0; i < size - 1; i++) {
            chang = chang + chars[i];
        }
        LasSing = true;
        return chang;
    }

    private void actionSing() {
        actionSwitch = false;
        textForView = "";
        dotSwitch = true;
    }

    private void ifDotZero() {
        if (forNumberOne.contains(".")) {
            String[] rozryad = forNumberOne.split("\\.");
            if (rozryad[1].equals("0")) {
                forNumberOne = rozryad[0];
            }
        }
    }

    private void remuvAction() {
        if (!deleteSwitch) {
            actionSwitch = true;
            resultSwitch = true;
            deleteSwitch = true;
            forNumberOne = "";
            forNumberTwo = "";
            sing = "";
        }
    }

    private void resultAction() {
        double num1 = 0, num2 = 0, resolt = 0;
        num1 = Double.parseDouble(forNumberOne);
        num2 = Double.parseDouble(forNumberTwo);
        switch (sing) {
            case "+":
                resolt = num1 + num2;
                break;
            case "-":
                resolt = num1 - num2;
                break;
            case "*":
                resolt = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    contein = "error";
                    break;
                }
                resolt = num1 / num2;
                break;
            case "^":
                if (num2 == 0) {
                    contein = "error";
                }
                resolt = Math.pow(num1, num2);
                break;
        }
        actionSwitch = true;
        resultSwitch = true;
        forNumberOne = resolt + "";
        forNumberTwo = "";
        sing = "";
        ifDotZero();
        contein = forNumberOne;

        dat.add(contein);
        Adapter.notifyDataSetChanged();
    }

    private String plusMinusAction(String chang) {
            if (chang.equals(""))
            {
                plusMinusSwitch = true;
                return "";
            }
            char [] chars = chang.toCharArray();
            chang = "";
            if (chars[0]== '-')
            {
                for (int i = 1; i < chars.length; i++)
                {
                    chang = chang + chars[i];
                }

            } else {
                for (int i = 0; i < chars.length; i++)
                {
                    chang = chang + chars[i];
                }
                chang = "-" + chang;
            }
        plusMinusSwitch = true;
           return chang;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_QUIT_ID, 0, "Вийти");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_QUIT_ID:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

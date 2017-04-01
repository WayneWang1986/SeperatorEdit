package com.wang.thomas.seperatoredit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    SeparatorEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (SeparatorEditText) findViewById(R.id.edittext);

        editText.setMax(15);
        editText.setSeparator("-");
        editText.setCondition(new SeparatorEditText.SeparatorCondition() {
            @Override
            public boolean addSeparator(int charIndex) {
                return charIndex == 2 || charIndex == 6 || charIndex == 10;
            }
        });
    }
}

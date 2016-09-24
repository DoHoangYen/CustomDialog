package com.example.admin.customdiaglog;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CustomDialogEnterText("Please Enter Text",size.x,MainActivity.this)
                        .setEnterText(getResources().getString(R.string.password))
                        .setUpPositiveButton(R.string.ok, getResources().getColor(R.color.green),
                                new CustomDialogEnterText.OnClickListener() {
                                    @Override
                                    public void onClick(View v, String content) {

                                    }
                                })
                        .setUpNegativeButton(R.string.cancel, Color.BLACK,
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                })
                        .show();
            }
        });
    }
}

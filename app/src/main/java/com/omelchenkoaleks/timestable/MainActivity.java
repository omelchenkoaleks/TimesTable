package com.omelchenkoaleks.timestable;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView mNumbersListView;
    private SeekBar mSeekBar;

    private ArrayList<Integer> mNumbers;

    private int max = 20;
    private int min = 1;
    private int count = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mNumbersListView = findViewById(R.id.numbers_list_view);
        mSeekBar = findViewById(R.id.seek_bar);
        mSeekBar.setMax(max);

        mNumbers = new ArrayList<>();
        final ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, mNumbers);
        mNumbersListView.setAdapter(arrayAdapter);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mNumbers.clear();

                if (progress < min) {
                    mSeekBar.setProgress(min);
                }
                for (int i = min; i <= count; i++) {
                    mNumbers.add(mSeekBar.getProgress() * i);
                }
                // явно указываем на то, что значения изменились и их нужно отобразить
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSeekBar.setProgress(10);
    }
}

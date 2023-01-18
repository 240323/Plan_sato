//findViewByIdメソッド：現在のアクティビティからid値をキーにウィジェットを検索する
package com.example.plan_sato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private TextView titleText;
    private Button prevButton, nextButton, addButton; //prevボタン、NEXTボタン、追加ボタンを定義
    private CalendarAdapter mCalendarAdapter;
    private GridView calendarGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ビューの変数を初期化する
        titleText = findViewById(R.id.titleText);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
        addButton = findViewById(R.id.addButton);
        calendarGridView = findViewById(R.id.calendarGridView);

        //ボタンクリック時に呼び出されるイベントリスナー
        //前の月表示

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarAdapter.prevMonth();
                titleText.setText(mCalendarAdapter.getTitle());
            }
        });

        //次の月表示
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalendarAdapter.nextMonth();
                titleText.setText(mCalendarAdapter.getTitle());
            }
        });


        mCalendarAdapter = new CalendarAdapter(this);
        calendarGridView.setAdapter(mCalendarAdapter);
        titleText.setText(mCalendarAdapter.getTitle());

        addButton.setOnClickListener(v -> {
            //AddPlanへのインテントを作成
            Intent intent = new Intent(getApplication(), AddPlan.class);
            //アクティビティを起動
            startActivity(intent);
        });

        //カレンダーセルをクリックした時の処理を定義
        calendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int potion, long id) {
            //予定情報がないカレンダーセルをタップすると、予定なしとトーストする
            //予定情報があるカレンダーセルをタップすると、予定詳細画面へ遷移
            String message = "予定なし";
            Toast.makeText(MainActivity.this, message,Toast.LENGTH_SHORT).show();
            }
        });
    }
}

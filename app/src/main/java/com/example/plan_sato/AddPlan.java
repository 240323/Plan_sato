package com.example.plan_sato;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class AddPlan extends AppCompatActivity {

    // タイトル入力欄
    private EditText etTitle;
    //場所入力欄
    private EditText etPlace;
    //予定追加ボタン
    private Button btAddPlan;
    //予定詳細画面へ遷移するためのボタン
    private ImageButton ibtReturn;
    //開始時間を表示するTextView
    public static TextView tvStartTime;
    //開始日付を表示するTextView
    public static TextView tvStartDate;
    //修了時間を表示するTextView
    public static TextView tvFinishTime;
    //修了日付を表示するTextView
    public static TextView tvFinishDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);

        // ビューの変数を初期化する
        etTitle = findViewById(R.id.etTitle);
        etPlace = findViewById(R.id.etPlace);
        btAddPlan = findViewById(R.id.btAddPlan);
        ibtReturn = findViewById(R.id.ibtReturn);
        tvStartTime = findViewById(R.id.tvStartTime);
        tvStartDate = findViewById(R.id.tvStartDate);
        tvFinishTime = findViewById(R.id.tvFinishTime);
        tvFinishDate = findViewById(R.id.tvFinishDate);

        //SubActivityを終了
        ibtReturn.setOnClickListener(v -> finish());

        btAddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //*予定追加確認ダイアログ*//
                ConfirmDialogFragment dialogFragment = new ConfirmDialogFragment();
                Bundle args = new Bundle();
                args.putString("title", "予定追加確認");
                args.putString("msg", "予定を追加しても良いですか？");

                dialogFragment.setArguments(args);
                dialogFragment.show(getSupportFragmentManager(), "ConfrimDialogFragment");
            }
        });
    }
    public void showTimePickerDialog(View v) {
        //*時間入力ダイアログ*//
        DialogFragment timePickerDialog = new TimePickerFragment();

        Bundle args = new Bundle();
        Log.d("Sakamot1", "" + v.getId() + ":" + R.id.btStartTime + ":" + R.id.btFinishTime);
        switch (v.getId()){
            case R.id.btStartTime:
                args.putString("tvTime", "Start");
                break;
            case R.id.btFinishTime:
                args.putString("tvTime", "Finish");
                break;
        }

        timePickerDialog.setArguments(args);
        timePickerDialog.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        //*日付入力ダイアログ*//
        DialogFragment dateDialogFragment = new DatePickerFragment();

        Bundle args = new Bundle();
        switch (v.getId()){
            case R.id.btStartDate:
                args.putString("tvDate", "Start");
                break;
            case R.id.btFinishDate:
                args.putString("tvDate", "Finish");
                break;
        }

        dateDialogFragment.setArguments(args);
        dateDialogFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
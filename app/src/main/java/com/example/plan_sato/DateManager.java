package com.example.plan_sato;

//カレンダーの日付を操作するクラス
// SimpleDateFormatのコンストラクタの引数で取得する日時形式を決定　引数がパターン文字列、ロケールのタイプ。
//ロケールとは日付を表すときに使う言語のこと
//formatメソッドは指定されたDateをパターン文字列にフォーマットする

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateManager {
    Calendar mCalendar;

    public DateManager(){
        mCalendar = Calendar.getInstance();
    }

    //当月の要素を取得
    public List<Date> getDays(){
        //現在の状態を取得
        Date startDate = mCalendar.getTime();

        //GriViewに表示するマスの合計を計算
        int count = getWeeks() * 7;

        //当月のカレンダーに表示される前月分の日数を計算
        mCalendar.set(Calendar.DATE,1);
        int dayOfWeek =mCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        mCalendar.add(Calendar.DATE, -dayOfWeek);

        List<Date> days = new ArrayList<>();

        for (int i = 0; i < count; i ++) {
            days.add(mCalendar.getTime());
            mCalendar.add(Calendar.DATE,1);
        }

        //状態を復元
        mCalendar.setTime(startDate);

        return days;

    }

    //当月かどうか確認
    public boolean isCurrentMonth(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM", Locale.JAPAN);
        String currentMonth = format.format(mCalendar.getTime());
        if (currentMonth.equals(format.format(date))){
            return true;
        }else {
            return false;
        }
    }

    //週数を取得
    public int getWeeks(){
        return mCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH);//指定した日付の月末日付を取得する
    }

    //曜日を取得
    public int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    //翌月へ
    public void nextMonth() {
        mCalendar.add(Calendar.MONTH, 1);
    }

    //前月へ
    public void prevMonth() {
        mCalendar.add(Calendar.MONTH,-1);
    }

    //当日かどうか判定するメソッド
    public boolean isToday(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd",Locale.JAPAN);
        String today = format.format(Calendar.getInstance().getTime());//DateインスタンスからStringを生成
        return today.equals(format.format(date));

    }
}

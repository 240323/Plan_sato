package com.example.plan_sato;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class ConfirmDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceStance) {

        //値を受け取る
        String title = getArguments().getString("title", "");
        String msg = getArguments().getString("msg", "");

        //ダイアログビルダーを生成
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //ダイアログのタイトルを設定
        builder.setTitle(title);
        //ダイアログのメッセージを設定
        builder.setMessage(msg);
        //Positive Button を設定
        builder.setPositiveButton(R.string.dialog_btn_ok, new DialogButtonClickListener());
        //Negative Button を設定
        builder.setNegativeButton(R.string.dialog_btn_ng, new DialogButtonClickListener());
        //ダイアログオブジェクトを生成し、リターン
        AlertDialog dialog = builder.create();
        return dialog;
    }

    //ダイアログのアクションボタンがタップされた時の処理の記述されたメンバクラス
    private class DialogButtonClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            //トーストメッセージ用文字列変換を用意
            String msg = "";
            //タップされたアクションボタンで分岐
            switch (which) {
                //Positive Buttonならば
                case DialogInterface.BUTTON_POSITIVE:
                    //注文用のメッセージを格納
                    msg = getString(R.string.dialog_ok_toast);
                    break;
                //Negative Buttonならば
                case DialogInterface.BUTTON_NEGATIVE:
                    //キャンセル用のメッセージを格納
                    msg = getString(R.string.dialog_ng_toast);
                    break;
            }
            //トーストを表示
            Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
        }
    }

}

package jmy.mylibrary.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;

import jmy.mylibrary.R;

/**
 * Created by jmy on 2017/11/15.
 */

public class DatePickerDialog extends Dialog {
    private DatePicker datePicker;
    private OnDateSelectOnClickListener onDateSelectOnClickListener;
    private Context context;
    private boolean isBirthDay = false;

    public void setBirthDay(boolean birthDay) {
        isBirthDay = birthDay;
    }

    public void setOnDateSelectOnClickListener(OnDateSelectOnClickListener onDateSelectOnClickListener) {
        this.onDateSelectOnClickListener = onDateSelectOnClickListener;
    }

    public DatePickerDialog(@NonNull Context context) {
        this(context, R.style.Mydialog);
        this.context = context;
        getWindow().setGravity(Gravity.BOTTOM);
    }

    public DatePickerDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_date_picker);
        datePicker = (DatePicker) findViewById(R.id.datePicker);

        findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDateSelectOnClickListener != null) {
                    onDateSelectOnClickListener.onOkClick(format(datePicker.getYear()) + "-" +
                            format(datePicker.getMonth() + 1) + "-" +
                            format(datePicker.getDayOfMonth()));
                }
                dismiss();
            }
        });
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public interface OnDateSelectOnClickListener {
        void onOkClick(String date);
    }

    private String format(int num) {
        return String.format("%02d", num);
    }

    @Override
    public void show() {
        super.show();
        if (isBirthDay) {
            datePicker.setMaxDate(System.currentTimeMillis());
        }
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        getWindow().setAttributes(lp);
    }
}

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
import android.widget.TimePicker;

import jmy.mylibrary.R;

/**
 * Created by jmy on 2017/11/15.
 */

public class DateTimePickerDialog extends Dialog {
    private DatePicker datePicker;
    private TimePicker timePicker;
    private OnDateTimeSelectOnClickListener onDateSelectOnClickListener;
    private Context context;

    public void setOnDateSelectOnClickListener(OnDateTimeSelectOnClickListener onDateSelectOnClickListener) {
        this.onDateSelectOnClickListener = onDateSelectOnClickListener;
    }

    public DateTimePickerDialog(@NonNull Context context) {
        this(context, R.style.Mydialog);
        this.context = context;
        getWindow().setGravity(Gravity.BOTTOM);
    }

    public DateTimePickerDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_date_time_picker);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDateSelectOnClickListener != null) {
                    onDateSelectOnClickListener.onOkClick(format(datePicker.getYear()) + "-" +
                            format(datePicker.getMonth() + 1) + "-" +
                            format(datePicker.getDayOfMonth()) + " " +
                            format(timePicker.getCurrentHour()) + ":" + format(timePicker.getCurrentMinute()));
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

    public interface OnDateTimeSelectOnClickListener {
        void onOkClick(String date);
    }

    private String format(int num) {
        return String.format("%02d", num);
    }

    @Override
    public void show() {
        super.show();
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        getWindow().setAttributes(lp);
    }
}

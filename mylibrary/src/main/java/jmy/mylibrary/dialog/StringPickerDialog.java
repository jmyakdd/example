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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jmy.mylibrary.R;
import jmy.mylibrary.widget.WheelView;

/**
 * Created by jmy on 2017/11/15.
 */

public class StringPickerDialog extends Dialog {
    private Context context;
    private WheelView whType;
    private TextView cancel;
    private TextView ok;
    private List<String> strings = new ArrayList<>();
    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public StringPickerDialog(@NonNull Context context) {
        this(context, R.style.Mydialog);
    }

    public StringPickerDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        getWindow().setGravity(Gravity.BOTTOM);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_age_picker);

        whType = (WheelView) findViewById(R.id.wh);
        cancel = (TextView) findViewById(R.id.cancel);
        ok = (TextView) findViewById(R.id.ok);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOkClick(whType.getSelectedText(),whType.getSelected());
                dismiss();
            }
        });

        whType.setData(strings);
        whType.setDefault(0);
    }

    public void setData(List<String> data) {
        this.strings.clear();
        this.strings.addAll(data);
        if (whType != null) {
            whType.setData(strings);
            whType.setDefault(0);
        }
    }

    public void setDefault(int position){
        if(whType!=null){
            whType.setDefault(position);
        }
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

    public interface OnClickListener {
        void onOkClick(String str,int position);
    }
}

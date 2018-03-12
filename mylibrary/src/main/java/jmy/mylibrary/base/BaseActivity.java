package jmy.mylibrary.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jmy on 2018/1/24.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private String TAG;
    private Bundle bundle;
    private ProgressDialog waitDialog;

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initEnvent();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        TAG = this.getClass().getName();
        init();
        initView();
        initEnvent();
        initData();
    }

    private void init() {
        waitDialog = new ProgressDialog(this);
    }

    protected void showToast(String msg) {
        showToast(msg, Toast.LENGTH_SHORT);
    }

    protected void showToast(String msg, int during) {
        Toast.makeText(this, msg, during).show();
    }

    protected void showLog(String msg) {
        Log.e(TAG, msg);
    }

    protected void showDialog() {
        waitDialog.show();
    }

    protected void cancelDialog() {
        waitDialog.dismiss();
    }
}

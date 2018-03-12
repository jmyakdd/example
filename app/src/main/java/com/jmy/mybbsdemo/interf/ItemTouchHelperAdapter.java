package com.jmy.mybbsdemo.interf;

/**
 * Created by jmy on 2018/2/27.
 */

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition,int toPosition);

    void onItemDissmiss(int position);
}

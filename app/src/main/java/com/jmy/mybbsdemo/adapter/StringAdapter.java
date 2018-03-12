package com.jmy.mybbsdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jmy.mybbsdemo.R;
import com.jmy.mybbsdemo.interf.ItemTouchHelperAdapter;
import com.jmy.mybbsdemo.interf.ItemTouchHelperViewHolder;

import java.util.Collections;
import java.util.List;

/**
 * Created by jmy on 2018/2/27.
 */

public class StringAdapter extends RecyclerView.Adapter<StringAdapter.MyHolder> implements ItemTouchHelperAdapter{
    private List<String> datas;
    private Context context;
    private LayoutInflater inflater;

    public StringAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(this.inflater.inflate(R.layout.item_string,parent,false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.item.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(datas,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDissmiss(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
    }

    class MyHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{
        TextView item;
        public MyHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}

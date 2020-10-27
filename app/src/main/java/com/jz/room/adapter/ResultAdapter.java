package com.jz.room.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jz.room.R;
import com.jz.room.xls.LogUtil;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jackzhous
 * @package com.jz.room.adapter
 * @filename ResultAdapter
 * date on 2020/10/26 3:29 PM
 * @describe TODO
 * @email jackzhouyu@foxmail.com
 **/
public class ResultAdapter<T extends Result> extends BaseAdapter {
    private List<T> list;
    private Context ctx;

    public ResultAdapter(Context ctx) {
        this.ctx = ctx;
        list = new ArrayList<>();
    }

    public void setList(List<T> data) {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if(view == null){
            view = LayoutInflater.from(ctx).inflate(R.layout.item_perfect_d, viewGroup, false);
            holder = new Holder();
            holder.name = view.findViewById(R.id.name);
            holder.detail = view.findViewById(R.id.detail);
        }else {
            holder = (Holder) view.getTag();
        }
        view.setTag(holder);
        holder.name.setText(list.get(i).getName());
        holder.detail.setText(list.get(i).getDetail());
        return view;
    }


    class Holder {
        TextView name;
        TextView detail;
    }
}

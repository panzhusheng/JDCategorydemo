package com.example.jdcategorydemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.jdcategorydemo.GoodsTypeBN;
import com.example.jdcategorydemo.R;

import java.util.List;

public class OneTypeAdapter extends RecyclerView.Adapter<OneTypeAdapter.OneTypeHolder> {
    private final Context context;
    private final List<GoodsTypeBN> list;
    public int selectedPosition = 0;//当前选择的下标
    private OnItemClickListener onItemClickListener;

    public OneTypeAdapter(Context context, List<GoodsTypeBN> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OneTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_type_one,parent,false);
        return new OneTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OneTypeHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.type.setText(list.get(position).getType_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition != holder.getAdapterPosition()) {
                    //点击了新的 item，更新状态
                    selectedPosition = holder.getAdapterPosition();
                }
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClickListener(v,position);
                }
                notifyDataSetChanged();
            }
        });
        if (position==selectedPosition){
            holder.type.setTextColor(context.getColor(R.color.red));
            holder.type.setTextSize(30);
        }
        else {
            holder.type.setTextColor(context.getColor(R.color.black));
            holder.type.setTextSize(18);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class OneTypeHolder extends RecyclerView.ViewHolder{
        TextView type;
        public OneTypeHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.type);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View v, int position);
    }
}

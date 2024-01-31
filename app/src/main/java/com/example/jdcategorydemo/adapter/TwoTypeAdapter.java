package com.example.jdcategorydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jdcategorydemo.GoodsTypeBN;
import com.example.jdcategorydemo.R;
import com.hjq.shape.view.ShapeButton;


import java.util.List;

public class TwoTypeAdapter extends RecyclerView.Adapter<TwoTypeAdapter.TwoTypeHolder> {
    private Context context;
    private List<GoodsTypeBN> list;
    private OnItemClickListener onItemClickListener;
    public int selectPosition=0;

    public TwoTypeAdapter(Context context, List<GoodsTypeBN> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TwoTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_type_two,parent,false);
        return new TwoTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TwoTypeHolder holder, int position) {
        holder.type.setText(list.get(position).getType_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPosition=position;
                notifyDataSetChanged();
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClickListener(view,position);
                }
            }
        });
        if (position==selectPosition){
            holder.type.setTextColor(context.getColor(R.color.common_accent_color));
            holder.type.getShapeDrawableBuilder()
                    .setStrokeColor(context.getColor(R.color.common_accent_color))
                    .intoBackground();
        }
        else {
            holder.type.setTextColor(context.getColor(R.color.grey));
            holder.type.getShapeDrawableBuilder()
                    .setStrokeColor(context.getColor(R.color.grey))
                    .intoBackground();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class TwoTypeHolder extends RecyclerView.ViewHolder{
        ShapeButton type;
        public TwoTypeHolder(@NonNull View itemView) {
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

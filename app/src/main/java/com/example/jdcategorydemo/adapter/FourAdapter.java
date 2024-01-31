package com.example.jdcategorydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.jdcategorydemo.GoodsTypeBN;
import com.example.jdcategorydemo.R;

import java.util.List;

public class FourAdapter extends RecyclerView.Adapter<FourAdapter.FourHolder> {
    private final Context context;
    private final List<GoodsTypeBN> list;

    public FourAdapter( Context context,List<GoodsTypeBN> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FourHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_type_four,parent,false);
        return new FourHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FourHolder holder, int position) {
        GoodsTypeBN goodsTypeBN=list.get(position);
        holder.type.setText(goodsTypeBN.getType_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "点击了"+goodsTypeBN.getType_name(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class FourHolder extends RecyclerView.ViewHolder{
        TextView type;
        ImageView icon;
        public FourHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.type);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}

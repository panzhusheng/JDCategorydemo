package com.example.jdcategorydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.jdcategorydemo.GoodsTypeBN;
import com.example.jdcategorydemo.R;

import java.util.List;

public class ThreeTypeAdapter extends RecyclerView.Adapter<ThreeTypeAdapter.ThreeTYpeHolder> {
    private final Context context;
    private final List<GoodsTypeBN> list;
    public ThreeTypeAdapter(Context context, List<GoodsTypeBN> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ThreeTYpeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_type_three,parent,false);
        return new ThreeTYpeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThreeTYpeHolder holder, int position) {
        holder.type.setText(list.get(position).getType_name());

        holder.recyclerView.setAdapter(new FourAdapter(context,list.get(position).getNextType()));
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context,3));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ThreeTYpeHolder extends RecyclerView.ViewHolder{
        TextView type;
        RecyclerView recyclerView;
        public ThreeTYpeHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.type);
            recyclerView = itemView.findViewById(R.id.recycler);
        }
    }
}

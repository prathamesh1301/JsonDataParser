package com.example.jsonparser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<String> colors;
    private List<String> category;
    private List<String> type;
    private List<String> rgba;
    private List<String> hex_code;
    private Context context;

    public RecyclerAdapter(List<String> colors, List<String> category, List<String> type, List<String> rgba, List<String> hex_code, Context context) {
        this.colors = colors;
        this.category = category;
        this.type = type;
        this.rgba = rgba;
        this.hex_code = hex_code;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.colorTV.setText(colors.get(position).toUpperCase());
        holder.categoryTV.setText("Category: "+category.get(position));
        holder.typeTV.setText("Type: "+type.get(position));
        holder.rgbaTV.setText("RGBA code: "+rgba.get(position));
        holder.hexTV.setText("Hex Code: "+hex_code.get(position));

    }

    @Override
    public int getItemCount() {
        final int size = colors.size();
        return size;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView colorTV,categoryTV,typeTV,rgbaTV,hexTV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            colorTV=itemView.findViewById(R.id.color_name);
            categoryTV=itemView.findViewById(R.id.category);
            typeTV=itemView.findViewById(R.id.type);
            rgbaTV=itemView.findViewById(R.id.rgba_code);
            hexTV=itemView.findViewById(R.id.hex_code);
        }
    }
}

package com.example.weatherwise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<RecyclerViewModal> RecyclerViewModalArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<RecyclerViewModal> recyclerViewModalArrayList) {
        this.context = context;
        RecyclerViewModalArrayList = recyclerViewModalArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        RecyclerViewModal modal = RecyclerViewModalArrayList.get(position);
        holder.Temp.setText(modal.getTemperature() + "°c");
        Picasso.get().load("http.".concat(modal.getIcon())).into(holder.Condition);
        holder.Wind.setText(modal.getWindSpeed() + "km/h");
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");
        try {
            Date t = input.parse(modal.getTime());
            holder.Time.setText(output.format(t));
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return RecyclerViewModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView Wind, Temp, Time;
        private ImageView Condition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Wind = itemView.findViewById(R.id.WindSpeed);
            Temp = itemView.findViewById(R.id.Temperature);
            Time = itemView.findViewById(R.id.Time);
            Condition = itemView.findViewById(R.id.Condition);
        }
    }
}

package com.example.touchmarker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.touchmarker.Model.BusStopTime;
import com.example.touchmarker.R;
import java.util.ArrayList;
import java.util.List;

public class BusTimeAdapter extends RecyclerView.Adapter<BusTimeAdapter.ViewHolder> {

    private List<BusStopTime> modelBusStopTimes = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context context;


    public BusTimeAdapter(List<BusStopTime> modelBusStopTimes, Context context){
        this.modelBusStopTimes = modelBusStopTimes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(context);
        View v  = layoutInflater.inflate(R.layout.bustimes_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.linearLayout.setTag(holder);
        holder.passTimeFirst.setText(modelBusStopTimes.get(position).getPassTime());
        holder.passTimeLast.setText(modelBusStopTimes.get(position).getPassTime());

    }

    @Override
    public int getItemCount() {
       return modelBusStopTimes.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView passTimeFirst,passTimeLast;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            passTimeFirst = itemView.findViewById(R.id.passTimeFirst);
            passTimeLast = itemView.findViewById(R.id.passTimeLast);
            linearLayout  = itemView.findViewById(R.id.linearLayoutBusTime);
        }

    }
}

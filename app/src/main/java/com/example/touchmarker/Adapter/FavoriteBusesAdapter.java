package com.example.touchmarker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.touchmarker.BusTimes;
import com.example.touchmarker.FavoriteBuses;
import com.example.touchmarker.Model.ModelFavoriteBus;
import com.example.touchmarker.R;

import java.util.ArrayList;

import static com.example.touchmarker.BusTimes.TAG;

public class FavoriteBusesAdapter extends RecyclerView.Adapter<FavoriteBusesAdapter.ViewHolder> {



    String routeCodeForFav;
    private OnItemClickListener mListener;
    private Context context;
    private ArrayList<ModelFavoriteBus> modelFavoriteBuses;
    private LayoutInflater layoutInflater;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    public FavoriteBusesAdapter(ArrayList<ModelFavoriteBus> modelFavoriteBuses, Context context,OnItemClickListener mListener) {
        this.modelFavoriteBuses = modelFavoriteBuses;
        this.context = context;
        this.mListener = mListener;

    }

    @NonNull
    @Override
    public FavoriteBusesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.favorite_buses_items,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull final FavoriteBusesAdapter.ViewHolder holder, final int position) {
        holder.busName.setText(modelFavoriteBuses.get(position).getBusName());
        holder.busCode.setText(modelFavoriteBuses.get(position).getRouteCode());
        holder.linearLayout.setTag(holder);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String routeCode = modelFavoriteBuses.get(position).getRouteCode();
                setRouteCode(routeCode);
                Intent  intent = new Intent(v.getContext(),BusTimes.class);
                intent.putExtra("WhereIntentComeFrom","FavoriteBusesAdapter");
                intent.putExtra("RouteCode",routeCode);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelFavoriteBuses.size();
    }

    public void setRouteCode(String routeCode){
        routeCodeForFav = routeCode;


    }
    public String getRouteCode(){
        return routeCodeForFav;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView busCode,busName;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            busName = itemView.findViewById(R.id.busName);
            busCode = itemView.findViewById(R.id.busCode);
            linearLayout = itemView.findViewById(R.id.linearLayoutFavoritebuses);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}

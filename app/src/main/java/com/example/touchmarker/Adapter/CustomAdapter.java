package com.example.touchmarker.Adapter;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.touchmarker.Database.AppDatabase;
import com.example.touchmarker.Model.FavoriteBus;
import com.example.touchmarker.Model.ModelBus;
import com.example.touchmarker.R;
import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Context context;

    public String  busId;
    private OnItemClickListener mListener;//Our Activity
    private ArrayList<ModelBus> modelBus = new ArrayList<>();
    private LayoutInflater layoutInflater;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    public CustomAdapter(ArrayList<ModelBus> modelBus,Context context){
        this.modelBus = modelBus;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.listofbuses_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomAdapter.ViewHolder holder, int position) {
        holder.busName.setText(modelBus.get(position).getName());
        holder.busCode.setText(modelBus.get(position).getRouteCode());
        holder.linearLayout.setTag(holder);

    }

    @Override
    public int getItemCount() {
        return modelBus.size();
    }


    public void addBusToFavorite(FavoriteBus modelFavoriteBus){
        AppDatabase appDatabase = AppDatabase.getDatabase(context);
        if (!(modelFavoriteBus.getRouteCode()).equals(appDatabase.favBusDao().insertedBefore(modelFavoriteBus.getRouteCode()))){
            appDatabase.favBusDao().addFavBus(modelFavoriteBus);
            Toast.makeText(context, modelFavoriteBus.getName().toUpperCase()+" eklendi", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, modelFavoriteBus.getName().toUpperCase()+" zaten listede.", Toast.LENGTH_SHORT).show();
        }
        Log.d("FavoriteBus", modelFavoriteBus.getRouteCode());

    }
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView busName,busCode;

        CardView linearLayout;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            busName = itemView.findViewById(R.id.busName);
            busCode = itemView.findViewById(R.id.busCode);
            linearLayout = itemView.findViewById(R.id.cardview);

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

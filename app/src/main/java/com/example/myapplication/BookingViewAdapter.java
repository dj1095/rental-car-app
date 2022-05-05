package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.Vehicle;

import java.util.List;

public class BookingViewAdapter extends RecyclerView.Adapter<BookingViewAdapter.BookingViewHolder> {
    private final Context context;
    private final List<Vehicle> vehicles;
    private final RecyclerViewInterface recyclerViewInterface;

    public BookingViewAdapter(Context context, List<Vehicle> vehicles, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.vehicles = vehicles;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_booking_details,parent,false);
        return new BookingViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Vehicle vehicle = vehicles.get(position);
        holder.setDetails(vehicle);
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    public static class BookingViewHolder extends  RecyclerView.ViewHolder {
        private final TextView vehicleId;
        private final TextView vehicleDesc;
        private final TextView Year;
        BookingViewHolder(View view,RecyclerViewInterface recyclerViewInterface){
            super(view);
            vehicleId = view.findViewById(R.id.bkgvehicleId);
            vehicleDesc = view.findViewById(R.id.bkgvehicleDesc);
            Year = view.findViewById(R.id.bkgvehicleYear);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItenClick(position);
                        }
                    }
                }
            });

        }
        void setDetails(Vehicle vehicle){
            vehicleId.setText(vehicle.getVehicleId());
            vehicleDesc.setText(vehicle.getDesciption());
            Year.setText(String.valueOf(vehicle.getYear()));
        }
    }
}

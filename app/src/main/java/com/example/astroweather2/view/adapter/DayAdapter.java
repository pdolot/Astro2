package com.example.astroweather2.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroweather2.R;

import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ViewHolder> {

    private List<Pair<String, String>> days;
    private int currentPosition = 0;
    private DayListener dayListener;

    public void setData(List<Pair<String, String>> days) {
        this.days = days;
        notifyDataSetChanged();
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        notifyDataSetChanged();
    }

    public void setDayListener(DayListener dayListener) {
        this.dayListener = dayListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        TextView day = holder.itemView.findViewById(R.id.day);
        TextView month = holder.itemView.findViewById(R.id.month);
        View indicator = holder.itemView.findViewById(R.id.indicator);

        Pair<String, String> d = days.get(position);

        day.setText(d.first);
        month.setText(d.second);

        if (position == currentPosition) {
            indicator.setBackgroundColor(ContextCompat.getColor(context, R.color.indicatorColor));
        } else {
            indicator.setBackgroundColor(Color.TRANSPARENT);
        }

        holder.itemView.setOnClickListener(v -> {
            if (dayListener != null) {
                dayListener.onDaySelected(position);
            }
            setCurrentPosition(position);
        });
    }

    @Override
    public int getItemCount() {
        if (days != null) {
            return days.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface DayListener {
        void onDaySelected(int position);
    }
}

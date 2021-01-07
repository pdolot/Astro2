package com.example.astroweather2.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroweather2.R;

import java.util.List;

public class TabNameAdapter extends RecyclerView.Adapter<TabNameAdapter.ViewHolder> {

    private List<String> tabsNames;
    private int currentPosition = 0;
    private TabNameListener tabNameListener;
    private boolean isEnableChangePosition = true;

    public void setTabsNames(List<String> tabsNames) {
        this.tabsNames = tabsNames;
        notifyDataSetChanged();
    }

    public void setTabNameListener(TabNameListener tabNameListener) {
        this.tabNameListener = tabNameListener;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        notifyDataSetChanged();
    }

    public void disableChangePosition(){
        this.isEnableChangePosition = false;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tab_name, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        TextView textView = holder.itemView.findViewById(R.id.tabName);
        View indicator = holder.itemView.findViewById(R.id.indicator);

        textView.setText(tabsNames.get(position));

        if (isEnableChangePosition){
            if (position == currentPosition) {
                indicator.setBackgroundColor(ContextCompat.getColor(context, R.color.indicatorColor));
            } else {
                indicator.setBackgroundColor(Color.TRANSPARENT);
            }
        } else {
            indicator.setBackgroundColor(ContextCompat.getColor(context, R.color.indicatorColor));
        }

        holder.itemView.setOnClickListener(v -> {
            if (tabNameListener != null) {
                tabNameListener.onTabSelected(position);
            }
            setCurrentPosition(position);
        });
    }

    @Override
    public int getItemCount() {
        if (tabsNames != null) {
            return tabsNames.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface TabNameListener {
        void onTabSelected(int position);
    }
}

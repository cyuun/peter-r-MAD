package com.example.apollolauncher;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class RecentsRecyclerViewAdapter extends RecyclerView.Adapter<RecentsRecyclerViewAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView;

        public ViewHolder(View view) {
            super(view);

            textView = view.findViewById(R.id.textView2);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Context context = view.getContext();

            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(recentsList.get(position).getPackageName());
            context.startActivity(launchIntent);
        }
    }

    LayoutInflater layoutInflater;
    List<AppInfo> recentsList;

    public RecentsRecyclerViewAdapter(Context context, List<AppInfo> recentsList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.recentsList = recentsList;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recents_item, parent, false);
        return new RecentsRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int index) {
        String appLabel = recentsList.get(index).getLabel().toString();
        String appPackage = recentsList.get(index).getPackageName().toString();
        Drawable appIcon = recentsList.get(index).getIcon();

        viewHolder.textView.setText(appLabel);
    }

    @Override
    public int getItemCount() {
        return recentsList.size();
    }
}

package com.example.apollolauncher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MyRecyclerViewAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);

            textView = view.findViewById(R.id.textView);
            imageView = view.findViewById(R.id.imageView);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Context context = view.getContext();

            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(appsList.get(position).packageName.toString());
            context.startActivity(launchIntent);
            Toast.makeText(context, appsList.get(position).label.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private LayoutInflater inflater;
    //private AdapterView.OnItemClickListener clickListener;
    private List<AppInfo> appsList;

    MyRecyclerViewAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);

        PackageManager pm = context.getPackageManager();
        appsList = new ArrayList<AppInfo>();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> allApps = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo ri:allApps) {
            AppInfo app = new AppInfo();
            app.label = ri.loadLabel(pm);
            app.packageName = ri.activityInfo.packageName;
            app.icon = ri.activityInfo.loadIcon(pm);
            appsList.add(app);
        }
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int index) {
        String appLabel = appsList.get(index).label.toString();
        String appPackage = appsList.get(index).packageName.toString();
        Drawable appIcon = appsList.get(index).icon;

        viewHolder.textView.setText(appLabel);
        viewHolder.imageView.setImageDrawable(appIcon);
    }

    @Override
    public int getItemCount() {
        return appsList.size();
    }

    /*public void setClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }*/
}

package com.example.apollolauncher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// I learned about recycler views in general from Suragch on stack overflow
//      link: https://stackoverflow.com/questions/40587168/simple-android-grid-example-using-recyclerview-with-gridlayoutmanager-like-the
// I learned about how the recycler view relates to an android launcher and grabbing apps info and everything from Adam Sinicki from Android Authority
//      link: https://www.androidauthority.com/make-a-custom-android-launcher-837342-837342/

public class AppsRecyclerViewAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<AppsRecyclerViewAdapter.ViewHolder> {

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
    private List<AppInfo> appsList;

    AppsRecyclerViewAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);

        appsList = new ArrayList<AppInfo>();

        fillAppList(context);
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

    private void fillAppList(Context context) {
        PackageManager pm = context.getPackageManager();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> allApps = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo  primApp : allApps) {
            AppInfo app = new AppInfo();
            app.label = primApp.loadLabel(pm);
            app.packageName = primApp.activityInfo.packageName;
            app.icon = primApp.activityInfo.loadIcon(pm);
            if (!appsList.contains(app)) {
                appsList.add(app);
            }
        }
    }
}

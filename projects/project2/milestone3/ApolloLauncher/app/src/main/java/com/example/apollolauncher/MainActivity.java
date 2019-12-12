package com.example.apollolauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView appsRecyclerView;
    private int displayWidth;
    private int numColumns;
    private List<AppInfo> appsList;

    private final int APPS_VIEW_MARGINS = 64;
    private final int GRID_CELL_WIDTH = 64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appsList = new ArrayList<AppInfo>();
        fillAppList(this);

        appsRecyclerView = findViewById(R.id.recyclerView);
        appsRecyclerView.setAdapter(new AppsRecyclerViewAdapter(this, appsList));

        displayWidth = getDisplayWidth(this);
        numColumns = (int) Math.floor(displayWidth / GRID_CELL_WIDTH) - 2;
        appsRecyclerView.setLayoutManager(new GridLayoutManager(this, numColumns));
    }

    private int getDisplayWidth(Context context) {
        WindowManager wm = getWindowManager();
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        float pxWidth = size.x;
        int dpWidth = (int) pxWidth / (context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT); // this line inspired by Salam El-Banna and Muhammad Nabeel Arif from Stack Overflow link: https://stackoverflow.com/questions/4605527/converting-pixels-to-dp

        return dpWidth - APPS_VIEW_MARGINS;
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

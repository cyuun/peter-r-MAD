package com.example.apollolauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    private RecyclerView appsRecyclerView;
    private RecyclerView recentsRecyclerView;
    //private ConstraintLayout firstDockItem;
    //private ConstraintLayout secondDockItem;
    //private ConstraintLayout thirdDockItem;
    //private ConstraintLayout fourthDockItem;
    private int displayWidth;
    private int numColumns;
    private List<AppInfo> appsList;
    private List<AppInfo> recentsList;

    private final int APPS_VIEW_MARGINS = 64;
    private final int GRID_CELL_WIDTH = 64;

    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appsList = new ArrayList<AppInfo>();
        fillAppList(this);
        alphebetizeAppList();

        appsRecyclerView = findViewById(R.id.appsRecyclerView);
        appsRecyclerView.setAdapter(new AppsRecyclerViewAdapter(this, appsList));

        displayWidth = getDisplayWidth(this);
        numColumns = (int) Math.floor(displayWidth / GRID_CELL_WIDTH) - 2;
        appsRecyclerView.setLayoutManager(new GridLayoutManager(this, numColumns));

        recentsList = new ArrayList<AppInfo>();
        fillRecentsList(this);

        recentsRecyclerView = findViewById(R.id.recentsRecyclerView);
        recentsRecyclerView.setAdapter(new RecentsRecyclerViewAdapter(this, recentsList));
        recentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //firstDockItem = findViewById(R.id.firstDockItem);
        //secondDockItem = findViewById(R.id.secondDockItem);
        //thirdDockItem = findViewById(R.id.thirdDockItem);
        //fourthDockItem = findViewById(R.id.fourthDockItem);
        //initializeDock();
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
        for (ResolveInfo  app : allApps) {
            String label = app.loadLabel(pm).toString();
            String packageName = app.activityInfo.packageName;
            Drawable icon = app.activityInfo.loadIcon(pm);
            AppInfo newApp = new AppInfo(label, packageName, icon);
            if (!appsList.contains(newApp)) {
                appsList.add(newApp);
            }
        }
    }

    private void alphebetizeAppList() {
        boolean sorting = true;
        while (sorting) {
            sorting = false;
            for (int i = 0; i < appsList.size() - 1; i++) {
                String appName = appsList.get(i).getLabel();
                String nextAppName = appsList.get(i + 1).getLabel();
                if (appName.compareToIgnoreCase(nextAppName) > 0) {
                    AppInfo swapApp = appsList.get(i + 1);
                    appsList.set(i + 1, appsList.get(i));
                    appsList.set(i, swapApp);
                    sorting = true;
                }
            }
        }
    }

    private void fillRecentsList(Context context) {
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processes = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo process : processes) {
            for (AppInfo app : appsList) {
                if (process.processName.equals(app.getPackageName())) {
                    recentsList.add(app);
                }
            }
        }
    }

    //private void initializeDock() {
    //}
}

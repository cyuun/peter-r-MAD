package com.example.apollolauncher;

import android.graphics.drawable.Drawable;

public class AppInfo {
    private String label;
    private String packageName;
    private Drawable icon;

    public AppInfo(String label, String packageName, Drawable icon) {
        this.label = label;
        this.packageName = packageName;
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public String getPackageName() {
        return packageName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}

package com.example.myapplication.widgets;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.R;

public class WidgetManager {
    private final FragmentManager fragmentManager;
    private final int containerId; // ID of the layout that holds widgets
    private final Context context;

    public WidgetManager(FragmentManager fm, int containerId, Context context) {
        this.fragmentManager = fm;
        this.containerId = containerId;
        this.context = context;
    }

    public void loadWidget(String widgetId) {
        Fragment widget = WidgetRegistry.createWidget(widgetId);
        if (widget != null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                    .add(containerId, widget, widgetId)
                    .commit();
        }
    }

    public void unloadWidget(String widgetId) {
        Fragment fragment = fragmentManager.findFragmentByTag(widgetId);
        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .remove(fragment)
                    .commit();
        }
    }

    public void unloadAllWidgets() {
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment instanceof WidgetFragment) {
                fragmentManager.beginTransaction()
                        .remove(fragment)
                        .commit();
            }
        }
    }
}

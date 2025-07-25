package com.example.myapplication.widgets;

import android.util.Log;

import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WidgetRegistry {
    private static final Map<String, Class<? extends Fragment>> widgetMap = new HashMap<>();

    static {
        // widgetMap.put("weather", WeatherWidgetFragment.class);
        // widgetMap.put("news", NewsWidgetFragment.class);
        // add others here
    }

    public static Fragment createWidget(String widgetId) {
        try {
            return Objects.requireNonNull(widgetMap.get(widgetId)).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            Log.e("WidgetRegistry", "Failed to instantiate widget: " + widgetId, e);
            return null;
        }
    }

    public static boolean isValidWidget(String widgetId) {
        return widgetMap.containsKey(widgetId);
    }
}

package com.example.myapplication.widgets;

public interface WidgetFragment {
    String getWidgetId(); // unique ID to identify it
    void onWidgetDataUpdated(Object data); // for backend-driven updates
}

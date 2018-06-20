package com.example.android.didyoufeelit;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Event>>{

    private String url;

    EarthquakeLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public List<Event> loadInBackground() {
        return Utils.fetchEarthquakeData(url);
    }
}

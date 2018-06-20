/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.didyoufeelit;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays the perceived strength of a single earthquake event based on responses from people who
 * felt the earthquake.
 */
public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Event>> {

    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";

    EventAdapter eventAdapter = new EventAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        if (recyclerView != null) {
            recyclerView.setAdapter(eventAdapter);
        }

        getSupportLoaderManager().initLoader(0, null, this).forceLoad();
    }

    @Override
    public Loader<List<Event>> onCreateLoader(int id, Bundle args) {
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Event>> loader, List<Event> data) {
        eventAdapter.setEvents(data != null ? data : new ArrayList<Event>());
        eventAdapter.notifyDataSetChanged();

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        TextView emptyTextView = (TextView) findViewById(R.id.empty);

        if (data.size() == 0) {

            emptyTextView.setVisibility(View.VISIBLE);
        } else {
            emptyTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Event>> loader) {
        eventAdapter.setEvents(new ArrayList<Event>());
    }
}

package com.example.android.didyoufeelit;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{

    List<Event> events;

    public EventAdapter(List<Event> events) {
        this.events = events;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);

        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        Event event = events.get(position);

        holder.magnitude.setText(event.perceivedStrength);
        holder.title.setText(event.title);
        holder.numberOfPeople.setText(event.numOfPeople);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {
        TextView magnitude;
        TextView title;
        TextView numberOfPeople;

        public EventViewHolder(View itemView) {
            super(itemView);

            magnitude = (TextView) itemView.findViewById(R.id.magnitude);
            title = (TextView) itemView.findViewById(R.id.title);
            numberOfPeople = (TextView) itemView.findViewById(R.id.number_of_people);
        }


    }
}

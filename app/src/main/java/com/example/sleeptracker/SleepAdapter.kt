package com.example.sleeptracker

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SleepAdapter (private val sleeps: ArrayList<Sleep?>) : RecyclerView.Adapter<SleepAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // TODO: Create member variables for any view that will be set
        // as you render a row.
        val dateView: TextView
        val hoursView: TextView
        val moodView: TextView
        val notesView: TextView

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // TODO: Store each of the layout's views into
            // the public final member variables created above
            dateView = itemView.findViewById(R.id.date)
            hoursView = itemView.findViewById(R.id.hours)
            moodView = itemView.findViewById(R.id.mood)
            notesView = itemView.findViewById(R.id.notes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.sleep_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val sleep = sleeps.get(position)
        // Set item views based on views and data model
        holder.dateView.text = sleep?.date
        holder.hoursView.text = sleep?.hours
        holder.moodView.text = sleep?.mood
        holder.notesView.text = sleep?.notes
    }

    override fun getItemCount(): Int {
        return sleeps.size
    }
}
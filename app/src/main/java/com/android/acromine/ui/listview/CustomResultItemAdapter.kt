package com.android.acromine.ui.listview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.acromine.R
import com.android.acromine.data.pojo.LfsItem

/**
 * Custom adapter to setup the view for recycler view
 */
class CustomResultItemAdapter(
    private val itemList: List<LfsItem>
) : RecyclerView.Adapter<CustomResultItemAdapter.ViewHolder>() {

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textViewLongForm: TextView = itemView.findViewById(R.id.tv_long_form)
        val textViewFreq: TextView = itemView.findViewById(R.id.tv_freq)
        val textViewSince: TextView = itemView.findViewById(R.id.tv_since)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_item, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemViewModel = itemList[position]
        // sets the text to the longForm from our itemHolder class
        holder.textViewLongForm.text = itemViewModel.lf
        // sets the text to the longForm from our itemHolder class
        holder.textViewFreq.text = "Freq : " + itemViewModel.freq.toString()
        // sets the text to the longForm from our itemHolder class
        holder.textViewSince.text = "Since : " + itemViewModel.since.toString()
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
package com.p2ptestexercise.ui

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<ListItem> : RecyclerView.Adapter<BaseViewHolder<ListItem>>() {

    private val items = mutableListOf<ListItem>()

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<ListItem>, position: Int) =
        holder.bindTo(items[position])

    fun updateItems(data: List<ListItem>) {
        items.clear()
        items.addAll(data)

        // It's OK now, but for better UX we should use diff callback and dispatching results
        // in case of more complex update logic
        notifyDataSetChanged()
    }
}
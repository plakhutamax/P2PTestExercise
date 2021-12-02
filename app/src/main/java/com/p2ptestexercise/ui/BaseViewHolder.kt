package com.p2ptestexercise.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<ListItem>(view: View): RecyclerView.ViewHolder(view) {
    abstract fun bindTo(item: ListItem)
}
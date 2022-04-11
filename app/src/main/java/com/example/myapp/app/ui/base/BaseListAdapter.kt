package com.example.myapp.app.ui.base

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


/**
Crete by Minh at 28/02/2022
 **/

abstract class BaseListAdapter<T>(diffCallBack: BaseDiffUtilCallBack<T> = BaseDiffUtilCallBack()) :
    ListAdapter<T, BaseListAdapter<T>.BaseItemViewHolder>(diffCallBack) {

    override fun onBindViewHolder(holder: BaseItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    open inner class BaseItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal open fun bind(data: T) = Unit
    }

    open class BaseDiffUtilCallBack<T> : DiffUtil.ItemCallback<T>() {
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem

        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem

    }
}

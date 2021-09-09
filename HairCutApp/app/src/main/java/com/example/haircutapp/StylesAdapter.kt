package com.example.haircutapp

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.haircutapp.hairstylesdatabase.Hairstyle

class StylesAdapter (val activity: Activity, val stylesList: List<Hairstyle>):
        RecyclerView.Adapter<StylesAdapter.StylesViewHolder>() {

            inner class StylesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
                val styleName: TextView = itemView.
            }
        }
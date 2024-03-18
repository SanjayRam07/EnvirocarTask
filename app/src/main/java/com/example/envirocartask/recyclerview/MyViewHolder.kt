package com.example.envirocartask.recyclerview

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.envirocartask.R
import com.example.envirocartask.shared.Shared

class RecyclerAdapter(var trackIds: List<String>): RecyclerView.Adapter<RecyclerAdapter.TrackIdHolder>() {

    inner class TrackIdHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.RVText)
        private val DOUBLE_CLICK_TIME_DELTA: Long = 300
        init {
            textView.setOnTouchListener(object : View.OnTouchListener {
                private var lastClickTime: Long = 0
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    if (event?.action == MotionEvent.ACTION_UP) {
                        val clickTime = System.currentTimeMillis()
                        if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                            // Double click occurred, handle it here
                            val text = textView.text.toString()

                        }
                        lastClickTime = clickTime
                    }
                    return true
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackIdHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return TrackIdHolder(view)
    }

    override fun getItemCount(): Int {
        return trackIds.size
    }

    override fun onBindViewHolder(holder: TrackIdHolder, position: Int) {
        val trackIdTV = holder.itemView.findViewById<TextView>(R.id.RVText)
        trackIdTV.text = trackIds[position]
    }
}
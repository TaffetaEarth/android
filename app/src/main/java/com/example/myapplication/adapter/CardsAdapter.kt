package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.myapplication.R


class CardsAdapter(
    private val context: Context,
    private val cards: ArrayList<Int>
) :
    BaseAdapter() {

    override fun getCount(): Int {
        return cards.size
    }

    override fun getItem(position: Int): Any {
        return cards[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val holder: ViewHolder

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false)
            holder = ViewHolder()
            holder.itemText = view.findViewById(R.id.card)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val card = cards[position]

        holder.itemText?.text = card.toString()
        holder.itemText?.setBackgroundColor(getColor(card))

        return view!!
    }

    private class ViewHolder {
        var itemText: TextView? = null
    }

    fun addCard() {
        this.cards.add(this.cards.size)
    }

    private fun getColor(value: Int) : Int {
        if (value % 2 == 0) {
            return ContextCompat.getColor(context, R.color.red)
        }
        return ContextCompat.getColor(context, R.color.blue)
    }
}
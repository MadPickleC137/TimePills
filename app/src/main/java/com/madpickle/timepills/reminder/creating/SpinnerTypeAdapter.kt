package com.madpickle.timepills.reminder.creating

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.madpickle.timepills.R
import com.madpickle.timepills.databinding.ItemSpinnerTypesBinding.*

class SpinnerTypeAdapter(context: Context, private val types: Array<String>): ArrayAdapter<String>(context, 0, types) {
    override fun getCount(): Int = types.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }
    override fun isEnabled(position: Int): Boolean = position != 0

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }
    private fun createItemView(position: Int, recycledView: View?, parent: ViewGroup):View {
        val type = getItem(position)
        val view = inflate(LayoutInflater.from(context), parent, false)

        type?.let {
            view.itemTypeMedicine.text = type
        }
        if (position == 0) {
            view.itemTypeMedicine.setTextColor(ContextCompat.getColor(context, R.color.text_color_1))
        } else {
            view.itemTypeMedicine.setTextColor(Color.BLACK)
        }
        return view.root
    }
}
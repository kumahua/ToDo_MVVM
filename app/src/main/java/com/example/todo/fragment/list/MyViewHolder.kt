package com.example.todo.fragment.list

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.models.Priority
import com.example.todo.data.models.TodoData
import com.example.todo.databinding.RowLayoutBinding

class MyViewHolder(private val v: RowLayoutBinding, private val context: Context) :
    RecyclerView.ViewHolder(v.root) {

    fun setResult(data: TodoData) {

        val priority = data.priority

        v.title.text = data.title
        v.description.text = data.description

        when (priority) {

            Priority.HIGH -> {
                v.priorityIndicator.setCardBackgroundColor(
                    ContextCompat.getColor(context, R.color.red)
                )
            }
            Priority.MEDIUM -> {
                v.priorityIndicator.setCardBackgroundColor(
                    ContextCompat.getColor(context, R.color.yellow)
                )
            }
            Priority.LOW -> {
                v.priorityIndicator.setCardBackgroundColor(
                    ContextCompat.getColor(context, R.color.green)
                )
            }
        }

        v.rowBackground.setOnClickListener {
            // click ctrl p
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(data)
            v.rowBackground.findNavController().navigate(action)
        }
    }
}
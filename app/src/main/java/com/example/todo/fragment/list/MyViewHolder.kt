package com.example.todo.fragment.list

import androidx.recyclerview.widget.RecyclerView
import com.example.todo.data.models.TodoData
import com.example.todo.databinding.RowLayoutBinding

class MyViewHolder(private val v: RowLayoutBinding) : RecyclerView.ViewHolder(v.root) {

    fun setResult(todoData: TodoData) {

        v.toDoData = todoData
        v.executePendingBindings()
    }
}
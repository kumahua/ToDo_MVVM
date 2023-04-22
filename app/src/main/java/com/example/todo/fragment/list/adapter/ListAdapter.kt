package com.example.todo.fragment.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.data.models.TodoData
import com.example.todo.databinding.RowLayoutBinding
import com.example.todo.fragment.list.MyViewHolder

class ListAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dataList = listOf<TodoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val viewBinding = RowLayoutBinding.inflate(LayoutInflater.from(context), parent, false)

        return MyViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is MyViewHolder -> {

                holder.setResult(dataList[position])
            }
        }
    }

    override fun getItemCount() = dataList.count()

    fun setData(todoData: List<TodoData>) {
        val toDoDiffUtil = ToDoDiffUtil(dataList, todoData)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        dataList = todoData
        toDoDiffResult.dispatchUpdatesTo(this)
    }
}
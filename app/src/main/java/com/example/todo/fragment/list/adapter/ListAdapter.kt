package com.example.todo.fragment.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.models.TodoData
import com.example.todo.databinding.RowLayoutBinding
import com.example.todo.fragment.list.MyViewHolder

class ListAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dataList = listOf<TodoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false)
        val viewBinding = RowLayoutBinding.bind(view)

        return MyViewHolder(viewBinding, context)
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
//        notifyDataSetChanged()
    }
}
package com.example.todo.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.todo.data.TodoDao
import com.example.todo.data.models.TodoData

class TodoRepository(private val todoDao: TodoDao) {

    val getAllData: LiveData<List<TodoData>> = todoDao.getAllData()
    val sortByHighPriority: LiveData<List<TodoData>> = todoDao.sortByHighPriority()
    val sortByLowPriority: LiveData<List<TodoData>> = todoDao.sortByLowPriority()

    suspend fun insertData(todoData: TodoData) {
        todoDao.insertData(todoData)
    }

    suspend fun updateData(todoData: TodoData) {
        todoDao.updateData(todoData)
    }

    suspend fun deleteItem(todoData: TodoData) {
        todoDao.deleteItem(todoData)
    }

    suspend fun deleteAll() {
        todoDao.deleteAll()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<TodoData>> {
        return todoDao.searchDatabase(searchQuery)
    }
}
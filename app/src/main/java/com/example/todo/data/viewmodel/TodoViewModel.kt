package com.example.todo.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.data.TodoDatabase
import com.example.todo.data.models.TodoData
import com.example.todo.data.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val toDoDao = TodoDatabase.getDatabase(
        application
    ).toDoDao()
    private val repository = TodoRepository(toDoDao)

    val getAllData: LiveData<List<TodoData>> = repository.getAllData
    val sortByHighPriority: LiveData<List<TodoData>> = repository.sortByHighPriority
    val sortByLowPriority: LiveData<List<TodoData>> = repository.sortByLowPriority

    /**
     * Inserts a new TodoData object into the database.
     *
     * @param todoData The TodoData object to be inserted.
     */
    fun insertData(todoData: TodoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(todoData)
        }
    }

    /**
     * Updates an existing TodoData object in the database.
     *
     * @param todoData The TodoData object to be updated.
     */
    fun updateData(todoData: TodoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(todoData)
        }
    }

    /**
     * Deletes a TodoData object from the database.
     *
     * @param todoData The TodoData object to be deleted.
     */
    fun deleteItem(todoData: TodoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(todoData)
        }
    }

    /**
     * Deletes all TodoData objects from the database.
     */
    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    /**
     * Searches the database for TodoData objects that match the specified search query.
     *
     * @param searchQuery The search query.
     * @return A LiveData object that emits a list of TodoData objects that match the search query.
     */
    fun searchDatabase(searchQuery: String): LiveData<List<TodoData>> {
        return repository.searchDatabase(searchQuery)
    }
}
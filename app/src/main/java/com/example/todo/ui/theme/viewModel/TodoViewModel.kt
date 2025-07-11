package com.example.todo.ui.theme.viewModel

import android.app.Application
import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.ui.theme.data.Todo
import com.example.todo.ui.theme.data.TodoDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    val allTodos = repository.allTodos

    fun addTodo(title: String) {
        viewModelScope.launch {
            repository.insert(Todo(title = title))
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            repository.delete(todo)
        }
    }

    fun updateTodo(todo: Todo) {   // ✅ यही चाहिए!
        viewModelScope.launch {
            repository.update(todo)
        }
    }
}

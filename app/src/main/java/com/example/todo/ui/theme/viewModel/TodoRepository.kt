package com.example.todo.ui.theme.viewModel

import com.example.todo.ui.theme.data.Todo
import com.example.todo.ui.theme.data.TodoDao
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {
    val allTodos: Flow<List<Todo>> = todoDao.getAllTodos()

    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

    suspend fun delete(todo: Todo) {
        todoDao.delete(todo)
    }
    suspend fun update(todo: Todo) = todoDao.update(todo)

}


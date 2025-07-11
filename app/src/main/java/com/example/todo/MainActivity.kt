package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.todo.ui.theme.TODOTheme
import com.example.todo.ui.theme.data.TodoDatabase
import com.example.todo.ui.theme.ui.TodoListScreen
import com.example.todo.ui.theme.viewModel.TodoRepository
import com.example.todo.ui.theme.viewModel.TodoViewModel
import com.example.todo.ui.theme.viewModel.TodoViewModelFactory


class MainActivity : ComponentActivity() {

    private lateinit var viewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = TodoDatabase.getDatabase(applicationContext).todoDao()
        val repository = TodoRepository(dao)
        val factory = TodoViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(TodoViewModel::class.java)

        setContent {
            TodoListScreen(viewModel)
        }
    }
}


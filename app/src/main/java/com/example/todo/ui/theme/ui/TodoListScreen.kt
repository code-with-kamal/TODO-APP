package com.example.todo.ui.theme.ui



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todo.ui.theme.viewModel.TodoViewModel

@Composable
fun TodoListScreen(viewModel: TodoViewModel) {
//    val todos by viewModel.allTodos.collectAsStateWithLifecycle()
    val todos by viewModel.allTodos.collectAsStateWithLifecycle(initialValue = emptyList())
    var newTitle by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = newTitle,
                onValueChange = { newTitle = it },
                label = { Text("New Task") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            Button(
                onClick = {
                    if (newTitle.isNotBlank()) {
                        viewModel.addTodo(newTitle.trim())
                        newTitle = ""
                    }
                }
            ) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(todos) { todo ->
                TodoItem(
                    todo = todo,
                    onDelete = { viewModel.deleteTodo(todo) },
                    onToggleDone = {
                        val updatedTodo = todo.copy(isDone = !todo.isDone)
                        viewModel.updateTodo(updatedTodo)
                    }
                )
            }
        }
    }
}


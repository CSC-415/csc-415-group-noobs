package com.example.bottomnav.todo.ui.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnav.data.entity.ToDoItem
import com.example.bottomnav.databinding.ToDoCardViewBinding
import com.example.bottomnav.todo.viewmodel.ToDoViewModel

class ToDoAdaptor(
    private var todos: List<ToDoItem>,
    private val viewModel: ToDoViewModel
): RecyclerView.Adapter<ToDoAdaptor.ToDoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ToDoCardViewBinding.inflate(LayoutInflater,parent,false)
        return ToDoViewHolder(binding)
    }

    override fun getItemCount(): Int = todos.size


    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val todo = todos[position]
        holder.bind(todo)
    }

    fun updateList(newList: List<ToDoItem>) {
        todos = newList
        notifyDataSetChanged()
    }

    inner class ToDoViewHolder(
        private val binding: ToDoCardViewBinding
        ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(todo: ToDoItem){
            binding.todoName.text = todo.task
            binding.todoDue.text = todo.dueDate
            binding.todoPriority.text = todo.priority.toString()
            binding.deleteBtn.setOnClickListener {
                deleteTodoItem(todo)
            }
        }

        private fun deleteTodoItem(todo: ToDoItem) {
            // Call the deleteTodo() function in the ToDoViewModel to delete the todo item
            viewModel.deleteTodo(todo)
        }

    }

}
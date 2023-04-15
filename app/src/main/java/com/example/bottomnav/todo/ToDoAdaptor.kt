package com.example.bottomnav.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnav.todo.db.ToDo
import com.example.bottomnav.databinding.ToDoCardViewBinding

class ToDoAdaptor(
    private var todos: List<ToDo>
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

    fun updateList(newList: List<ToDo>) {
        todos = newList
        notifyDataSetChanged()
    }

    inner class ToDoViewHolder(
        private val binding: ToDoCardViewBinding
        ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(todo: ToDo){
            binding.todoName.text = todo.task
            binding.todoDue.text = todo.dueDate
            binding.todoPriority.text = todo.priority.toString()
//            itemView.setOnClickListener { onNoteClicked(todo) }
//            binding.deleteBtn.setOnClickListener { onNoteDeleteClicked(todo) }
        }

    }

    interface todoClickDeleteInterface {
        fun onDeleteIconClick(todo: ToDo)
    }

    interface todoClickInterface {
        fun onTodoClick(todo: ToDo)
    }


}
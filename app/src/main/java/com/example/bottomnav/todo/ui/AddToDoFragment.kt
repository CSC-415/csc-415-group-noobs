package com.example.bottomnav.todo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.example.bottomnav.R
import com.example.bottomnav.data.entity.TodoItem
import com.example.bottomnav.databinding.FragmentToDoFormBinding
import com.example.bottomnav.todo.viewmodel.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddToDoFragment : Fragment() {

    private var _binding: FragmentToDoFormBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ToDoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using view binding
        _binding = FragmentToDoFormBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun writetodo() {
        val name = binding.todoName.text.toString()
        val priority = binding.todoPriority.text.toString().toIntOrNull()
            ?: 0 // Default to 0 if age is not a valid integer
        val due = binding.todoDue.text.toString()

        val todo = TodoItem(
            0, name,  due, priority, false
        )

        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.addTodo(todo)
        }

        binding.todoName.text.clear()
        binding.todoPriority.text.clear()
        binding.todoDue.text.clear()

        Toast.makeText(context, "Created Successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toDoFragment = ToDoFragment()

        binding.submitBtn.setOnClickListener {
            writetodo()
            //setFragmentResult(requestKey = "requestKey", bundle)
            activity?.supportFragmentManager?.commit {
                setReorderingAllowed(true)
                replace(R.id.frame_layout, toDoFragment)
                addToBackStack(null)
            }
        }
    }

}
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
import java.text.SimpleDateFormat
import java.util.*

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
            0, name, due, priority, false
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


            val validForm = CheckAllFields()

            if (validForm) {
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

    fun CheckAllFields(): Boolean {
        if (binding.todoName.length() == 0) {
            binding.todoName.error = "This name is required"
            return false
        }
        if (binding.todoPriority.length() == 0) {
            binding.todoPriority.error = "This priority is required"
            return false
        }
        if (binding.todoPriority.text.toString().toInt() > 10 || binding.todoPriority.text
                .toString().toInt() < 0
        ) {
            binding.todoPriority.error = "Must be Between 1 to 10"
            return false
        }
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        val yesterday = calendar.time
        val dueDateParsed = try {
            SimpleDateFormat(
                "MM/dd/yyyy",
                Locale.getDefault()
            ).parse(binding.todoDue.text.toString().trim())
        } catch (e: Exception) {
            null
        }
        if (dueDateParsed == null) {
            // Show error message for invalid due date
            binding.todoDue.error = "Invalid Data Format"
            return false
        }
        if (dueDateParsed.before(yesterday)) {
            // Show error message for invalid due date
            binding.todoDue.error = "Date has already past"
            return false
        }
        return true
    }

}
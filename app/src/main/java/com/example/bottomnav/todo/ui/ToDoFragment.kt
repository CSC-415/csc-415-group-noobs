package com.example.bottomnav.todo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomnav.R
import com.example.bottomnav.data.entity.TodoItem
import com.example.bottomnav.databinding.FragmentToDoBinding
import com.example.bottomnav.todo.ui.adaptor.ToDoAdaptor
import com.example.bottomnav.todo.viewmodel.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToDoFragment : Fragment() {
    private lateinit var adaptor: ToDoAdaptor
    private var _binding: FragmentToDoBinding? = null
    private val binding get() = _binding!!
    private var listOfTodo: List<TodoItem> = emptyList()

    private val viewModel: ToDoViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentToDoBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adaptor = ToDoAdaptor(listOfTodo, viewModel)
        binding.taskRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.taskRecyclerView.adapter = adaptor

        viewModel.allItemToDos.observe(viewLifecycleOwner) { toDos ->
            listOfTodo = toDos
            adaptor.updateList(listOfTodo)
        }

        binding.addFab.setOnClickListener {
            activity?.supportFragmentManager?.commit {
                setReorderingAllowed(true)
                replace(R.id.frame_layout, AddToDoFragment())
                addToBackStack(null)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
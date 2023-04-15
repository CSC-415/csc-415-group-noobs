package com.example.bottomnav.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomnav.R
import com.example.bottomnav.ToDoApplication
import com.example.bottomnav.todo.db.AppDatabase
import com.example.bottomnav.todo.db.ToDo
import com.example.bottomnav.databinding.FragmentToDoBinding
import com.example.bottomnav.todo.viewmodel.ToDoViewModel
import com.example.bottomnav.todo.viewmodel.ToDoViewModelFactory

class ToDoFragment : Fragment() {
    private lateinit var adaptor: ToDoAdaptor
    private var _binding: FragmentToDoBinding? = null
    private val binding get() = _binding!!
    private var listOfTodo: List<ToDo> = emptyList()

    private val viewModel: ToDoViewModel by activityViewModels {
        ToDoViewModelFactory((activity?.application as ToDoApplication).repository)
    }

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

        adaptor = ToDoAdaptor(listOfTodo)
        binding.taskRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.taskRecyclerView.adapter = adaptor

        viewModel.allToDos.observe(viewLifecycleOwner) { toDos ->
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

//    override fun onNoteClick(note: Note) {
//        val intent = Intent(requireContext(), AddEditNoteActivity::class.java).apply {
//            putExtra("noteType", "Edit")
//            putExtra("noteTitle", note.noteTitle)
//            putExtra("noteDescription", note.noteDescription)
//            putExtra("noteId", note.id)
//        }
//        startActivity(intent)
//        requireActivity().finish()
//    }
//
//    override fun onDeleteIconClick(note: Note) {
//        viewModal.deleteNote(note)
//        Toast.makeText(requireContext(), "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
//    }
}
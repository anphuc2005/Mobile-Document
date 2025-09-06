package com.example.bt_buoi_13.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bt_buoi_13.adapter.TaskAdapter
import com.example.bt_buoi_13.database.Task
import com.example.bt_buoi_13.database.TaskDatabase
import com.example.bt_buoi_13.databinding.FragmentAllBinding
import com.example.bt_buoi_13.databinding.FragmentCompletedBinding
import kotlinx.coroutines.launch

class FragmentCompleted : Fragment() {

    private var _binding: FragmentCompletedBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter
    private val taskDao by lazy { TaskDatabase.getDatabase(requireContext()).taskDao() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompletedBinding.inflate(inflater, container, false)

        taskAdapter = TaskAdapter(
            taskList = emptyList(),
            onEdit = {editTask() },
            onDelete = { task -> deleteTask(task) },
            onConfirm = { task -> confirmTask(task) }
        )

        binding.recyclerViewTodos.apply {
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        taskDao.getCompletedTasks().observe(viewLifecycleOwner) { tasks ->
            taskAdapter.setData(tasks)
        }

        return binding.root
    }

    private fun editTask() {
        // TODO: mở Fragment/BottomSheet để sửa task
    }

    private fun deleteTask(task: Task) {
        lifecycleScope.launch {
            taskDao.delete(task)
        }
    }

    private fun confirmTask(task: Task) {
        lifecycleScope.launch {
            taskDao.update(task.copy(isCompleted = true))

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

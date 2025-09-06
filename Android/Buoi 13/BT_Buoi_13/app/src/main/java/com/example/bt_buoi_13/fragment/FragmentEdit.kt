package com.example.bt_buoi_13.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.bt_buoi_13.MainActivity
import com.example.bt_buoi_13.database.Task
import com.example.bt_buoi_13.database.TaskDatabase
import com.example.bt_buoi_13.databinding.FragmentEditBinding
import kotlinx.coroutines.launch

class FragmentEdit : Fragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    private val taskDao by lazy { TaskDatabase.getDatabase(requireContext()).taskDao() }

    private var taskToEdit: Task? = null

    companion object {
        private const val ARG_TASK = "task"

        fun newInstance(task: Task): FragmentEdit {
            val fragment = FragmentEdit()
            val args = Bundle()
            args.putSerializable(ARG_TASK, task)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ✅ Lấy task từ arguments
        taskToEdit = arguments?.getSerializable(ARG_TASK) as? Task
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ✅ Hiển thị dữ liệu hiện có trong form
        taskToEdit?.let { task ->
            binding.titleField.setText(task.title)
            binding.detailField.setText(task.subTitle)
        }

        binding.updateBtn.setOnClickListener { updateTask() }
        binding.cancelButton.setOnClickListener { cancelTask() }
    }

    private fun updateTask() {
        val title = binding.titleField.text.toString().trim()
        val subTitle = binding.detailField.text.toString().trim()

        if (title.isEmpty()) {
            Toast.makeText(requireContext(), "Title cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        taskToEdit?.let { task ->
            val updatedTask = task.copy(
                title = title,
                subTitle = subTitle
            )

            lifecycleScope.launch {
                taskDao.update(updatedTask)
                Toast.makeText(requireContext(), "Task updated", Toast.LENGTH_SHORT).show()

                requireActivity().supportFragmentManager.popBackStack()
                (activity as? MainActivity)?.showBottomNav()
            }
        }
    }

    private fun cancelTask() {
        requireActivity().supportFragmentManager.popBackStack()
        (activity as? MainActivity)?.showBottomNav()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
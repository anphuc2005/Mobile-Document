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
import com.example.bt_buoi_13.databinding.FragmentAddBinding
import kotlinx.coroutines.launch

class FragmentAdd : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val taskDao by lazy { TaskDatabase.getDatabase(requireContext()).taskDao() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        binding.addBtn.setOnClickListener {
            saveTask()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
            (activity as MainActivity).showBottomNav()
        }
    }

    private fun saveTask() {
        val title = binding.titleField.text.toString().trim()
        val subtitle = binding.detailField.text.toString().trim()

        if (title.isEmpty()) {
            Toast.makeText(requireContext(), "Title cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        val task = Task(title = title, subTitle = subtitle, isCompleted = false)

        lifecycleScope.launch { // Các hàm trong DAO dùng ở suspend (Bất đồng bộ) nên dùng để gọi vào main thread
            taskDao.insert(task)
            Toast.makeText(requireContext(), "Task added", Toast.LENGTH_SHORT).show()

            requireActivity().supportFragmentManager.popBackStack()
            (activity as MainActivity).showBottomNav()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
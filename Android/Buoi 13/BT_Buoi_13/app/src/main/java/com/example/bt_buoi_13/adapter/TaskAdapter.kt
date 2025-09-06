package com.example.bt_buoi_13.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bt_buoi_13.R
import com.example.bt_buoi_13.database.Task
import com.example.bt_buoi_13.databinding.TodoItemBinding

class TaskAdapter(
    private var taskList: List<Task>,
    private val onEdit: (Task) -> Unit,
    private val onDelete: (Task) -> Unit,
    private val onConfirm: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(private val binding: TodoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.todoTitle.text = task.title
            binding.todoSubTitle.text = task.subTitle

            binding.editButton.setOnClickListener { onEdit(task) }
            binding.deleteButton.setOnClickListener { onDelete(task) }
            binding.confirmButton.setOnClickListener { onConfirm(task) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int = taskList.size

    fun setData(newList: List<Task>) {
        taskList = newList
        notifyDataSetChanged()
    }
}


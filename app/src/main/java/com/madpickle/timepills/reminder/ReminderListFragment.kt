package com.madpickle.timepills.reminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.madpickle.timepills.databinding.FragmentReminderListBinding
import com.madpickle.timepills.reminder.viewmodel.ReminderListViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.madpickle.timepills.reminder.list.CustomRecyclerAdapter

@AndroidEntryPoint
class ReminderListFragment : Fragment() {

    private var _binding: FragmentReminderListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReminderListViewModel by lazy {
        ViewModelProvider(this).get(ReminderListViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel
    }
    private fun fillList(): List<String> {
        val data = mutableListOf<String>()
        (0..30).forEach { i -> data.add("$i element") }
        return data
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReminderListBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = CustomRecyclerAdapter(fillList())
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("onDestroyView")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
    }
}
package com.madpickle.timepills.reminder.creating

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import com.madpickle.timepills.R
import com.madpickle.timepills.databinding.FragmentCreateReminderBinding
import com.madpickle.timepills.main.MainActivity.Companion.LOGCAT_TAG
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CreateReminderFragment : Fragment() {
    private var _binding: FragmentCreateReminderBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateReminderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        context?.let {
            binding.typeSpinner.adapter = SpinnerTypeAdapter(it, resources.getStringArray(R.array.type_array) )
        }
        binding.typeSpinner.onItemSelectedListener = object: OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                Timber.d("$LOGCAT_TAG --- Spinner onItemSelected")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Timber.d("$LOGCAT_TAG --- Spinner onNothingSelected")
            }

        }
        binding.weekButton.setOnClickListener {

        }
        binding.calendarButton.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
    }
}
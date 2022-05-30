package com.madpickle.timepills.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.madpickle.timepills.R
import com.madpickle.timepills.databinding.FragmentIntroBinding
import com.madpickle.timepills.main.MainActivityVM
import com.madpickle.timepills.utils.safeNavigate
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class IntroFragment : Fragment() {
    private var _binding: FragmentIntroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIntroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity()).get(MainActivityVM::class.java)
        binding.nextButton.setOnClickListener {
            viewModel.introIsShowed()
            findNavController().navigateUp()
            findNavController().safeNavigate(R.id.action_global_reminderListFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
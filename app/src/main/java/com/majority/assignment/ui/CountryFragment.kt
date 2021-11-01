package com.majority.assignment.ui

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.majority.assignment.databinding.CountryDetailFragmentBinding
import com.majority.assignment.extensions.obtainViewModel
import com.majority.assignment.viewmodels.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CountryFragment : Fragment() {

    private lateinit var binding: CountryDetailFragmentBinding
    private val args: CountryFragmentArgs by navArgs()
    private val countryViewModel: CountryViewModel by lazy {
        obtainViewModel(
            requireActivity(),
            CountryViewModel::class.java,
            defaultViewModelProviderFactory
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = CountryDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        val countryInfo = args.countryInfo
        binding.viewmodel = countryViewModel
        countryViewModel.countryInfo.set(countryInfo)
        binding.executePendingBindings()

    }


}

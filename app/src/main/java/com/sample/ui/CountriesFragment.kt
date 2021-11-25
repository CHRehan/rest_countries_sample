package com.sample.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.sample.R
import com.sample.data.remote.Resource
import com.sample.databinding.CountriesFragmentBinding
import com.sample.extensions.handleApiError
import com.sample.extensions.obtainViewModel
import com.sample.extensions.visible
import com.sample.ui.countries.CountriesAdapter
import com.sample.utilities.MarginItemDecoration
import com.sample.utilities.autoCleared
import com.sample.viewmodels.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CountriesFragment : Fragment() {


    private var binding: CountriesFragmentBinding by autoCleared()

    private lateinit var countriesAdapter: CountriesAdapter

    private val TAG = CountriesFragment::class.java.name

    private val viewModel: CountriesViewModel by lazy {
        obtainViewModel(
            requireActivity(),
            CountriesViewModel::class.java,
            defaultViewModelProviderFactory
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView")


        binding = CountriesFragmentBinding.inflate(inflater, container, false)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        countriesAdapter = CountriesAdapter { binding, countryInfo ->

            val action =
                CountriesFragmentDirections.openCountryDetail(countryInfo)

            findNavController()
                .navigate(
                    action,
                    FragmentNavigatorExtras(
                        binding.flag to "flag_transition"
                    )
                )

        }

        binding.countriesRV.adapter = countriesAdapter

        binding.countriesRV.addItemDecoration(
            MarginItemDecoration(
                R.dimen._16sdp,
                R.dimen._4sdp,
                R.dimen._16sdp,
                R.dimen._4sdp,
                R.dimen._6sdp
            )
        )


        postponeEnterTransition()
        binding.countriesRV.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }




        viewModel.searchQuery.observe({ lifecycle }) {
            countriesAdapter.search(it) { it1 ->

                //Update on UI.
                binding.errorMessage.visible(it1)
                binding.errorMessage.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        android.R.color.black
                    )
                )
                binding.errorMessage.text = getString(R.string.no_data_found)


            }
        }

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")


        viewModel.countries.observe({ lifecycle }) {
            Log.d(TAG, "observe")

            binding.progressBar.visible(it is Resource.Loading)

            if (it !is Resource.Loading)
                when (it) {
                    is Resource.Success -> {
                        lifecycleScope.launch {
                            Log.d(TAG,"Size: ${it.value?.size}")
                            countriesAdapter.setItems(it.value)
                        }
                    }
                    is Resource.Failure -> {
                        handleApiError(binding.errorMessage, it) { viewModel.fetchAndCacheData() }
                    }
                    else -> {
                    }
                }

        }


    }


}

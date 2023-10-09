package be.heb.g48982.myfirstapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import be.heb.g48982.myfirstapp.R
import be.heb.g48982.myfirstapp.databinding.FragmentReviewsBinding
import be.heb.g48982.myfirstapp.util.ReviewAdapter
import be.heb.g48982.myfirstapp.util.ReviewViewModelFactory
import be.heb.g48982.myfirstapp.viewmodel.ReviewViewModel


/**
 * This [Fragment] will show the list of reviews for a selected brewery.
 */

class ReviewsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentReviewsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_reviews, container, false
        )
        val args = ReviewsFragmentArgs.fromBundle(requireArguments())
        val item = args.brewery
        val viewModelFactory = ReviewViewModelFactory(item?.id.toString())
        val viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(ReviewViewModel::class.java)
        binding.viewModel = viewModel
        val adapter = ReviewAdapter()
        binding.reviewsList.adapter = adapter
        viewModel.reviews.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        return binding.root
    }

}
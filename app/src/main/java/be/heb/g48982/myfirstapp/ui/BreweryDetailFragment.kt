package be.heb.g48982.myfirstapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import be.heb.g48982.myfirstapp.R
import be.heb.g48982.myfirstapp.database.Review
import be.heb.g48982.myfirstapp.databinding.FragmentBreweryDetailBinding
import be.heb.g48982.myfirstapp.util.BreweryDetailViewModelFactory
import be.heb.g48982.myfirstapp.viewmodel.BreweryDetailViewModel
import com.google.firebase.database.*

/**
 * This [Fragment] will show the user a specific brewery and allow the user to rate it.
 */

class BreweryDetailFragment : Fragment() {

    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private lateinit var reference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBreweryDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_brewery_detail, container, false)
        val arguments = BreweryDetailFragmentArgs.fromBundle(requireArguments())
        val item = arguments.breweryKey
        val inputMap = arguments.mapData
        val mapData = inputMap.userData
        val viewModelFactory = BreweryDetailViewModelFactory(item)

        val breweryDetailViewModel =
            ViewModelProvider(this, viewModelFactory).get(BreweryDetailViewModel::class.java)
        binding.breweryDetailViewModel = breweryDetailViewModel
        binding.lifecycleOwner = this

        binding.textInputLayoutReview.setEndIconOnClickListener {
            val comment = binding.textInputReview.text.toString()
            if (comment == String()) {
                Toast.makeText(
                    requireContext(), "NO TEXT ENTERED!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                reference = database.reference.child(item!!.id.toString())
                val id = reference.push().key.toString()
                val review = Review(id, comment)
                reference.child(id).setValue(review).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            requireContext(), "The review has been successfully added",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.textInputReview.text?.clear()
                    } else {
                        Toast.makeText(
                            requireContext(), "sorry could not save your review",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                }

            }
        }
        binding.textInputReview.doOnTextChanged { text, start, before, count ->
            if (text!!.length > 50) {
                binding.textInputLayoutReview.error = "No more than 50 characters!"
            } else {
                binding.textInputLayoutReview.error = null
            }
        }
        binding.btnReviews.setOnClickListener {
            val ref = database.reference
            BreweryDetailFragmentDirections.actionBreweryDetailFragmentToReviewsFragment(
                item,
                inputMap
            )
            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.hasChild(item?.id.toString())) {
                        findNavController().navigate(
                            BreweryDetailFragmentDirections
                                .actionBreweryDetailFragmentToReviewsFragment(item, inputMap)
                        )
                    } else {
                        Toast.makeText(
                            requireContext(), "BREWERY HAS NO REVIEWS",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
        }

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(
                        BreweryDetailFragmentDirections.actionBreweryDetailFragmentToBreweryFragment(
                            if (mapData.containsKey("by_state")) mapData["by_state"] else "",
                            if (mapData.containsKey("by_city")) mapData["by_city"] else "",
                            if (mapData.containsKey("by_type")) mapData["by_type"] else ""
                        )
                    )
                }
            })
        return binding.root
    }


}
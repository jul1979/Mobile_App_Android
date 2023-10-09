package be.heb.g48982.myfirstapp.ui

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import be.heb.g48982.myfirstapp.R
import be.heb.g48982.myfirstapp.database.UserDatabase
import be.heb.g48982.myfirstapp.databinding.FragmentSearchBinding
import be.heb.g48982.myfirstapp.viewmodel.SearchViewModel
import be.heb.g48982.myfirstapp.viewmodel.SearchViewModelModelFactory
import com.google.firebase.auth.FirebaseAuth

/**
 * This [Fragment] will show a list of input fields that are used to fetch the data
 * based on the entered criteria.
 */

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var searchViewModelModelFactory: SearchViewModelModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        searchViewModelModelFactory = SearchViewModelModelFactory(
            UserDatabase.getDataBase(requireContext()).userDatabaseDao()
        )
        viewModel =
            ViewModelProvider(this, searchViewModelModelFactory).get(SearchViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        configureMenu()
        binding.viewmodel = viewModel
        viewModel.states.observe(viewLifecycleOwner, Observer { statesNames ->
            binding.stateText.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, statesNames
                )
            )
        })
        viewModel.typesData.observe(viewLifecycleOwner, Observer { typesList ->
            binding.typeText.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    R.layout.dropdown_item,
                    typesList
                )
            )
        })



        binding.searchBtn.setOnClickListener {
            val type = binding.typeText.text.toString()
            val state = binding.stateText.text.toString()
            val city = binding.cityText.text.toString()
            val args = HashMap<String, String?>()
            args["by_state"] = state
            args["by_city"] = city
            args["by_type"] = type
            args["size"] = "50"
            if (viewModel.checkUserInput(args))
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToBreweryFragment(
                        state,
                        city,
                        type
                    )
                )
        }

        viewModel.userInputFailure.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Toast.makeText(
                    requireContext(),
                    "You have to provide at least one Search option!!",
                    Toast.LENGTH_SHORT
                ).show()

                viewModel.doneChecking()
            }
        })
        return binding.root
    }

    private fun configureMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.signout_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.signOut) {
                    FirebaseAuth.getInstance().signOut()
                    findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToLoginFragment())
                } else {
                    return false
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


}
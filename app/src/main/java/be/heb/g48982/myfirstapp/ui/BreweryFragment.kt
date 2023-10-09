package be.heb.g48982.myfirstapp.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import be.heb.g48982.myfirstapp.R
import be.heb.g48982.myfirstapp.databinding.FragmentBreweryBinding
import be.heb.g48982.myfirstapp.util.BreweryItemAdapter
import be.heb.g48982.myfirstapp.util.InputMap
import be.heb.g48982.myfirstapp.viewmodel.BreweryViewModel
import com.google.firebase.auth.FirebaseAuth



/**
 * This [Fragment] will show the list of breweries.
 */
class BreweryFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentBreweryBinding
    private lateinit var viewModel: BreweryViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_brewery, container, false)
        viewModel = ViewModelProvider(this).get(BreweryViewModel::class.java)
        binding.viewmodel = viewModel
        val adapter = BreweryItemAdapter(BreweryItemAdapter.BreweryItemListener { brewery_id ->
            viewModel.onBreweryClicked(brewery_id)
        })
        binding.rvBreweries.adapter = adapter

        viewModel.response.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        binding.lifecycleOwner = this

        val args = BreweryFragmentArgs.fromBundle(requireArguments())
        val data = HashMap<String, String?>()
        data["by_state"] = args.stateData
        data["by_city"] = args.cityData
        if (args.typeData != String()) {
            data["by_type"] = args.typeData
        }
        data["size"] = "50"

        viewModel.getBreweryByFilters(data)

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Toast.makeText(
                    context,
                    "SORRY NO DATA FOUND FOR YOUR SEARCH",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(BreweryFragmentDirections.actionBreweryFragmentToSearchFragment())
                viewModel.resetRequestStatus()
            }
        })
        viewModel.navigateToBreweryDetail.observe(viewLifecycleOwner, Observer { brewery ->
            findNavController().navigate(
                BreweryFragmentDirections.actionBreweryFragmentToBreweryDetailFragment(
                    brewery,
                    InputMap(data)
                )
            )
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.signout_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return if (menuItem.itemId == R.id.signOut) {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(BreweryFragmentDirections.actionBreweryFragmentToLoginFragment())
            true
        } else
            false
    }
}
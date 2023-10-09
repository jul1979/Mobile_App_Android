package be.heb.g48982.myfirstapp.screens.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import be.heb.g48982.myfirstapp.R
import be.heb.g48982.myfirstapp.databinding.FragmentAboutBinding

/**
 * This [Fragment] will show the some fun facts about the brewery industry in America.
 */
class AboutFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAboutBinding>(
            inflater,
            R.layout.fragment_about,
            container,
            false
        )
        return binding.root
    }
}
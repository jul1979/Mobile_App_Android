package be.heb.g48982.myfirstapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.heb.g48982.myfirstapp.network.BreweryItem
import be.heb.g48982.myfirstapp.viewmodel.BreweryDetailViewModel

/**
 * Factory for constructing BreweryDetailViewModel with parameter
 */

class BreweryDetailViewModelFactory(
    private val breweryItem: BreweryItem?,
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BreweryDetailViewModel::class.java)) {
            return BreweryDetailViewModel(breweryItem) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
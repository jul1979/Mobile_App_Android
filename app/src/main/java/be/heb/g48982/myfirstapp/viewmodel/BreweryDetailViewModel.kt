package be.heb.g48982.myfirstapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.heb.g48982.myfirstapp.network.BreweryItem


/**
 * ViewModel for BreweryDetailFragment
 */
class BreweryDetailViewModel(breweryItem: BreweryItem?) : ViewModel() {

    /**
     * Holds a reference to the current brewery
     */
    private var _currentBrewery = MutableLiveData<BreweryItem?>()

    /**
     * Backing property
     * exposes current brewery for access outside of class
     */
    val currentBrewery: LiveData<BreweryItem?>
        get() = _currentBrewery

    init {
        _currentBrewery.value = breweryItem
    }
}
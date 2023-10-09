package be.heb.g48982.myfirstapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.heb.g48982.myfirstapp.database.State
import be.heb.g48982.myfirstapp.database.Type
import be.heb.g48982.myfirstapp.database.UserDatabaseDao
import be.heb.g48982.myfirstapp.util.StatesData

class SearchViewModel(private val dao: UserDatabaseDao) : ViewModel() {

    /**
     * states used in AutoCompleteTextView
     */
    var states: LiveData<List<String>> = dao.getAllStates()

    /**
     * breweries types used in AutoCompleteTextView
     */
    val typesData: LiveData<List<String>> = dao.getAlphabetizedTypes()

    init {
        dao.deleteAllTypes()
        StatesData.getTypes().forEach {
            dao.insert(Type(it))
        }
        dao.deleteAllStates()
        StatesData.getStates().forEach {
            dao.insertState(State(0L, it))
        }
    }

    /**
     * Backing property used to monitor user input validity.
     */
    private val _userInputFailure = MutableLiveData<Boolean?>()
    val userInputFailure: LiveData<Boolean?>
        get() = _userInputFailure

    /**
     * checks if at least one input field is entered by user.
     */
    fun checkUserInput(args: HashMap<String, String?>): Boolean {
        return if (args["by_type"] == String() && args["by_state"] == String() && args["by_city"] == String()) {
            _userInputFailure.value = true
            false
        } else {
            true
        }
    }

    /**
     * reset the input check result.
     */
    fun doneChecking() {
        _userInputFailure.value = null
    }
}

/**
 * Factory for constructing SearchViewModel with parameter
 */
class SearchViewModelModelFactory(private val dao: UserDatabaseDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package be.heb.g48982.myfirstapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.heb.g48982.myfirstapp.viewmodel.ReviewViewModel


/**
 * Factory for constructing ReviewViewModel with parameter
 */
class ReviewViewModelFactory(
    private val tableName: String,
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReviewViewModel::class.java)) {
            return ReviewViewModel(tableName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
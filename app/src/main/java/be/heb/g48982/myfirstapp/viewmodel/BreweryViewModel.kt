package be.heb.g48982.myfirstapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.heb.g48982.myfirstapp.network.BrewApi
import be.heb.g48982.myfirstapp.network.BreweryItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * ViewModel for BreweryFragment
 */
class BreweryViewModel : ViewModel() {


    /**
     * Backing property for data fetched from service.
     */
    private val _response = MutableLiveData<List<BreweryItem>>()
    val response: LiveData<List<BreweryItem>>
        get() = _response

    /**
     * container for data fetched from Rest service.
     */
    private var list: List<BreweryItem>

    /**
     * Backing property for service request status
     * if true(no data found),it triggers toast message.
     */
    private val _status = MutableLiveData<Boolean?>()
    val status: LiveData<Boolean?>
        get() = _status

    init {
        _status.value = null
        list = ArrayList()
    }

    /**
     * calls api Rest Service
     */
    fun getBreweryByFilters(options: HashMap<String, String?>) {

        BrewApi.retrofitService.getBreweryByFilters(options).enqueue(
            object : Callback<List<BreweryItem>> {
                override fun onResponse(
                    call: Call<List<BreweryItem>>,
                    response: Response<List<BreweryItem>>
                ) {
                    if (response.body()?.isEmpty() == true) {
                        _status.postValue(true)
                    } else {
                        _response.value = response.body()
                        list = _response.value!!
                    }
                }

                override fun onFailure(call: Call<List<BreweryItem>>, t: Throwable) {
                    _status.value = true
                }
            }
        )
    }

    /**
     * resets the status value upon completion of previous request.
     */
    fun resetRequestStatus() {
        _status.value = null
    }

    /**
     * Backing property used to store selected brewery from the RecyclerView.
     * if set triggers navigation to DetailFragment.
     */
    private val _navigateToBreweryDetail = MutableLiveData<BreweryItem?>()
    val navigateToBreweryDetail: LiveData<BreweryItem?>
        get() = _navigateToBreweryDetail

    /**
     * Handles click events on RecyclerView breweries list.
     */
    fun onBreweryClicked(currentId: String?) {
        val filteredList = list.filter {
            it.id.equals(currentId)
        }
        _navigateToBreweryDetail.value = filteredList[0]
    }


}
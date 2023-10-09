package be.heb.g48982.myfirstapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.heb.g48982.myfirstapp.database.Review
import com.google.firebase.database.*

/**
 * ViewModel for ReviewsFragment
 */
class ReviewViewModel(tableName: String) : ViewModel() {

    /**
     * firebase instance
     */
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    /**
     * reference to database table to be queried next.
     */
    private val myReference: DatabaseReference = database.reference.child(tableName)

    /**
     * list used to populate data fetched from firebase
     */
    private var list = arrayListOf<Review?>()

    /**
     * Backing property containing list of reviews.
     */
    private val _reviews = MutableLiveData<ArrayList<Review?>>()
    val reviews: LiveData<ArrayList<Review?>>
        get() = _reviews

    init {
        myReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    list.add(it.getValue(Review::class.java))
                }
                _reviews.value = list
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}
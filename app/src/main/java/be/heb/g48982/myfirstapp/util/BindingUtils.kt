package be.heb.g48982.myfirstapp.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import be.heb.g48982.myfirstapp.R
import be.heb.g48982.myfirstapp.database.Review
import be.heb.g48982.myfirstapp.network.BreweryItem


/**
 * Binding adapter used to set the brewery name.
 */
@BindingAdapter("breweryNameString")
fun TextView.setBreweryNameString(item: BreweryItem?) {
    item?.let {
        text = item.name
    }
}

/**
 * Binding adapter used to set the comment.
 */
@BindingAdapter("commentString")
fun TextView.setCommentString(item: Review?) {
    item?.let {
        text = item.comment
    }
}


/**
 * Binding adapter used to set the street name.
 */
@BindingAdapter("breweryStreetString")
fun TextView.setBreweryStreetString(item: BreweryItem?) {
    item?.let {
        text = item.street ?: "unavailable address"
    }
}

/**
 * Binding adapter used to display images.
 */
@BindingAdapter("breweryImage")
fun ImageView.setBrewertImage(item: BreweryItem?) {
    item?.let {

        setImageResource(
            when ((1..5).random()) {
                1 -> R.drawable.image1
                2 -> R.drawable.image2
                3 -> R.drawable.image3
                4 -> R.drawable.image4
                5 -> R.drawable.image5
                else -> {
                    R.drawable.image5
                }
            }
        )
    }
}

/**
 * Binding adapter used to display images.
 */
@BindingAdapter("reviewImage")
fun ImageView.setReviewImage(item: Review?) {
    item?.let {
        setImageResource(
            when ((1..5).random()) {
                1 -> R.drawable.image1
                2 -> R.drawable.image2
                3 -> R.drawable.image3
                4 -> R.drawable.image4
                5 -> R.drawable.image5
                else -> {
                    R.drawable.image5
                }
            }
        )
    }
}


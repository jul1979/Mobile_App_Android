package be.heb.g48982.myfirstapp.util

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * This class is used to pass data between fragment
 */
@Parcelize
data class InputMap(
    val userData: HashMap<String, String?>

) : Parcelable

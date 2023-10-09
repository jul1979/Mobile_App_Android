package be.heb.g48982.myfirstapp.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * This data class defines a Brewery.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */

@Parcelize
data class BreweryItem(
    @SerializedName("address_2")
    val address_2: String?,
    @SerializedName("address_3")
    val address_3: String?,
    @SerializedName("brewery_type")
    val brewery_type: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("county_province")
    val county_province: String?,
    @SerializedName("created_at")
    val created_at: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("latitude")
    val latitude: String?,
    @SerializedName("longitude")
    val longitude: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("postal_code")
    val postal_code: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("street")
    val street: String?,
    @SerializedName("updated_at")
    val updated_at: String?,
    @SerializedName("website_url")
    val website_url: String?
) : Parcelable


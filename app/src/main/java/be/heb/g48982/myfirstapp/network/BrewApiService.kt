package be.heb.g48982.myfirstapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap


/**
 *The Moshi object that Retrofit will be using
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private const val BASE_URL = "https://api.openbrewerydb.org"


/**
 * Retrofit builder to build a retrofit object using a Moshi converter with the Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


/**
 * A public interface that exposes the [getBreweryByFilters] method
 */

interface BrewApiService {

    /**
     * gets breweries based on request parameters provided
     *The @GET annotation indicates that the "breweries" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("breweries")
    fun getBreweryByFilters(@QueryMap options: HashMap<String, String?>):
            Call<List<BreweryItem>>
}


/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object BrewApi {
    val retrofitService: BrewApiService by lazy {
        retrofit.create(BrewApiService::class.java)
    }
}
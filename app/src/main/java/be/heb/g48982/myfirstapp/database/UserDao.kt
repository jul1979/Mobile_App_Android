package be.heb.g48982.myfirstapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import java.util.*


/**
 * Data access object for ROOM database,used to store states and types
 * that are used in autocompletion textview
 */
@Dao
interface UserDatabaseDao {


    @Query("select type FROM type_table ORDER BY type ASC")
    fun getAlphabetizedTypes(): LiveData<List<String>>

    @Insert(onConflict = IGNORE)
    fun insert(type: Type)

    @Query("DELETE FROM type_table")
    fun deleteAllTypes()

    @Query("DELETE FROM  states_table")
    fun deleteAllStates()

    @Query("SELECT stateName FROM states_table ORDER BY stateId ASC")
    fun getAllStates(): LiveData<List<String>>

    @Insert(onConflict = IGNORE)
    fun insertState(state: State)
}
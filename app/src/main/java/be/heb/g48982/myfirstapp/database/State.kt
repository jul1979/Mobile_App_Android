package be.heb.g48982.myfirstapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * represents a State data in the Database
 */
@Entity(tableName = "states_table")
data class State(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "stateId")
    var stateId: Long = 0L,

    @ColumnInfo(name = "stateName")
    var stateName: String,

) {
    override fun toString(): String {
        return "'$stateName'"
    }
}

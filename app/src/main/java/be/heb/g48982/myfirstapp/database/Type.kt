package be.heb.g48982.myfirstapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * represents a type of Bar in the Database
 */
@Entity(tableName = "type_table")
data class Type(@PrimaryKey @ColumnInfo(name = "type") val type : String)

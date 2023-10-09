package be.heb.g48982.myfirstapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database class allowing to get the singleton object
 */
@Database(entities = [State::class, Type::class], version = 5, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDatabaseDao(): UserDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDataBase(context: Context): UserDatabase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_history_database"
                    ).fallbackToDestructiveMigration()

                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}

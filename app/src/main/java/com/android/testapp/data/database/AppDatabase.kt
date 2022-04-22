/*
package com.android.testapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.testapp.data.database.dao.CollectionDao
import java.util.concurrent.Executors


const val DATABASE_NAME = "MvvmDB"
@Database(entities = [Collection::class, MyCollection::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    fun getDatabase(context: Context): AppDatabase? {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, DATABASE_NAME
                    ).build()

                }
            }
        }
        return INSTANCE
    }

    internal abstract fun collectionDao(): CollectionDao

    companion object {

        // marking the instance as volatile to ensure atomic access to the variable
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)


    }
}
*/

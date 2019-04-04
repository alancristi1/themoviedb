package project.kobe.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import project.kobe.dao.GenreDao
import project.kobe.model.GenreX

@Database(entities = [GenreX::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun genreDao() : GenreDao

    companion object {
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context : Context) : AppDatabase?{
            if(INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java,
                        "kobe-database")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}
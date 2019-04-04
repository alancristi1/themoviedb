package project.kobe.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import project.kobe.model.GenreX

@Dao
interface GenreDao {

    @Query("Select name from genre where id = :id")
    fun getGenreId(id : Int) : String

    @Insert(onConflict = REPLACE)
    fun add(genre : List<GenreX>)
}
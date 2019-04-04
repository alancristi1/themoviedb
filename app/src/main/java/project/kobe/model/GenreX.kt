package project.kobe.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "genre")
data class GenreX(
    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String
)
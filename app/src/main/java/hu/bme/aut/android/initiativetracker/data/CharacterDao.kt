package hu.bme.aut.android.initiativetracker.data
import androidx.room.*

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character")
    fun getAll(): List<PlayerCharacter>

    @Query("SELECT * FROM character Where is_picked")
    fun getPicked(): List<PlayerCharacter>

    @Insert
    fun insert(characters: PlayerCharacter): Long

    @Update
    fun update(characters: PlayerCharacter)

    @Delete
    fun deleteItem(characters: PlayerCharacter)
}
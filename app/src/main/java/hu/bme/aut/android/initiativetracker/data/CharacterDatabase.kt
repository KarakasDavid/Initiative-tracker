package hu.bme.aut.android.initiativetracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PlayerCharacter::class], version = 1)
@TypeConverters(value = [PlayerCharacter.Class::class])
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object {
        fun getDatabase(applicationContext: Context): CharacterDatabase {
            return Room.databaseBuilder(
                applicationContext,
                CharacterDatabase::class.java,
                "character-list"
            ).build();
        }
    }
}
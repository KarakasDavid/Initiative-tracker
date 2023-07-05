package hu.bme.aut.android.initiativetracker.data
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "character")
data class PlayerCharacter(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "race") var race: String,
    @ColumnInfo(name = "category") var player_class: Class,
    @ColumnInfo(name = "HP") var hp: Int,
    @ColumnInfo(name = "AC") var ac: Int,
    @ColumnInfo(name = "Init") var init: Int,
    @ColumnInfo(name = "is_picked") var isPicked: Boolean
)
{
    enum class Class {
        ARTIFICER, BARBARIAN, BARD, CLERIC, DRUID, FIGHTER, MONK,PALADIN, RANGER, ROGUE, SORCERER, WARLOCK, WIZARD;
        companion object {
            @JvmStatic
            @TypeConverter
            fun getByOrdinal(ordinal: Int): Class? {
                var ret: Class? = null
                for (cat in values()) {
                    if (cat.ordinal == ordinal) {
                        ret = cat
                        break
                    }
                }
                return ret
            }

            @JvmStatic
            @TypeConverter
            fun toInt(class_: Class): Int {
                return class_.ordinal
            }
        }
    }
}
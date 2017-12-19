package innovations.doubleeh.com.noted.repository

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by arjunachatz on 2017-12-09.
 */

const val NotesTable = "Notes"

@Entity(tableName = NotesTable)
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var msg: String = "",
    var highPriority: Boolean = false,
    var dateToRemind: String = "",
    var timeToRemind: String = "")
{
    override fun equals(other: Any?): Boolean {
        if(other !is Note) return false
        if(this.id != other.id) return false
        if(this.msg != other.msg) return false
        if(this.highPriority != other.highPriority) return false
        if(this.dateToRemind != other.dateToRemind) return false
        if(this.timeToRemind != other.timeToRemind) return false
        return true
    }
}
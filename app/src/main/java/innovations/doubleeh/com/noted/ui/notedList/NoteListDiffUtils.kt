package innovations.doubleeh.com.noted.ui.notedList

import android.support.v7.util.DiffUtil
import innovations.doubleeh.com.noted.repository.Note

/**
 * Created by arjunachatz on 2017-12-19.
 */

class NoteListDiffUtils(private val oldNoteList: List<Note>, private val newNoteList: List<Note>)
    : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldNoteList.size

    override fun getNewListSize(): Int = newNoteList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNoteList[oldItemPosition].id != newNoteList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNoteList[oldItemPosition] != newNoteList[newItemPosition]
    }
}
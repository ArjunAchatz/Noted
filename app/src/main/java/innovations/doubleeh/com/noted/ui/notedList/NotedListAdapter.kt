package innovations.doubleeh.com.noted.ui.notedList

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import innovations.doubleeh.com.noted.R
import innovations.doubleeh.com.noted.repository.Note
import kotlinx.android.synthetic.main.note_list_item.view.*

/**
 * Created by arjunachatz on 2017-12-15.
 */

class NotedListAdapter(var listOfNotes: ArrayList<Note> = ArrayList())
    : RecyclerView.Adapter<NotedListAdapter.NotedListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            NotedListViewHolder(LayoutInflater
                    .from(parent?.context)
                    .inflate(R.layout.note_list_item, parent, false))


    override fun onBindViewHolder(holder: NotedListViewHolder?, position: Int) {
        holder?.bind(listOfNotes[position])
    }

    override fun getItemCount() = listOfNotes.size

    fun handleNewData(newList: List<Note>) {
        val diffUtilsInput = NoteListDiffUtils(newList, listOfNotes)
        val diffResult = DiffUtil.calculateDiff(diffUtilsInput)
        listOfNotes.apply {
            clear()
            addAll(newList)
        }
        diffResult.dispatchUpdatesTo(this)
    }

    inner class NotedListViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note) {
            itemView.noteListItemMsg.text = note.msg
            itemView.showExtraText.setOnClickListener {
                if(itemView.extraText.visibility == View.VISIBLE){
                    itemView.extraText.visibility = View.GONE
                    itemView.noteListItemBackground.background = itemView.context.getDrawable(R.drawable.list_item_background)
                } else {
                    itemView.extraText.visibility = View.VISIBLE
                    itemView.noteListItemBackground.background = itemView.context.getDrawable(R.drawable.list_item_highlight_background)
                }

            }
        }

    }

}
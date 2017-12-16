package innovations.doubleeh.com.noted.ui.notedList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import innovations.doubleeh.com.noted.R
import innovations.doubleeh.com.noted.repository.Note
import kotlinx.android.synthetic.main.note_list_item.view.*

/**
 * Created by arjunachatz on 2017-12-15.
 */

class NotedListAdapter(
        val listOfNotes: List<Note> = ArrayList(),
        val itemClicked: (Long) -> Unit
) : RecyclerView.Adapter<NotedListAdapter.NotedListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            NotedListViewHolder(LayoutInflater
                    .from(parent?.context)
                    .inflate(R.layout.note_list_item, parent, false))


    override fun onBindViewHolder(holder: NotedListViewHolder?, position: Int) {
        holder?.bind(listOfNotes[position])
    }

    override fun getItemCount() = listOfNotes.size

    inner class NotedListViewHolder(itemView: View, val msg: TextView? = itemView.noteListItemMsg)
        : RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note) {
            itemView.setOnClickListener { itemClicked(note.id) }
            msg?.text = note.msg
        }

    }
}
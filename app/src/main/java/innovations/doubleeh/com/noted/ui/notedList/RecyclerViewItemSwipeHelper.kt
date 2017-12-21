package innovations.doubleeh.com.noted.ui.notedList

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log


/**
 * Created by arjunachatz on 2017-12-21.
 */

class RecyclerViewItemSwipeHelper(val onSwipeComplete: (Int?) -> Unit) :
        ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    var deleteIcon: Drawable? = null

    private val redPaint: Paint = Paint().apply {
        color = Color.RED
    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        onSwipeComplete(viewHolder?.adapterPosition)
    }

    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        if(deleteIcon == null){
            deleteIcon = recyclerView?.context?.getDrawable(android.R.drawable.ic_menu_delete)
        }

        when {
            dX > 0 -> {
                c?.drawRect((viewHolder?.itemView?.left?.toFloat() ?: 0f),
                        viewHolder?.itemView?.top?.toFloat() ?: 0f,
                        (viewHolder?.itemView?.right?.toFloat() ?: 0f) + dX,
                        viewHolder?.itemView?.bottom?.toFloat() ?: 0f,
                        redPaint)
            }
            dX < 0 -> {
                c?.drawRect((viewHolder?.itemView?.left?.toFloat() ?: 0f) + dX,
                        viewHolder?.itemView?.top?.toFloat() ?: 0f,
                        (viewHolder?.itemView?.right?.toFloat() ?: 0f),
                        viewHolder?.itemView?.bottom?.toFloat() ?: 0f,
                        redPaint)
            }
        }
    }

    override fun onChildDrawOver(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

}
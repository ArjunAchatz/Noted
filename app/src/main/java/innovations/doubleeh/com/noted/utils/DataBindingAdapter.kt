package innovations.doubleeh.com.noted.utils

import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.widget.TextView

/**
 * Created by arjunachatz on 2017-12-15.
 */

@BindingAdapter("app:setDateText")
fun setDateText(textView: TextView, date : String) {
    textView.text = if(date.isNullOrEmpty()) "Enter Date" else date
}

@BindingAdapter("app:setTimeText")
fun setTimeText(textView: TextView, time : String) {
    textView.text = if(time.isNullOrEmpty()) "Enter Time" else time
}
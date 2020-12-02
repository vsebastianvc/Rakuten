package com.vsebastianvc.rakuten.customviews

/**
 * I took the code from Lars Werkman as a base and converted it to kotlin with some tweaks
 *
 * https://github.com/LarsWerkman/QuickReturnListView
 */
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ListView


/**
 * Created by Sebastian on 12/02/2020.
 */
class QuickReturnListView(context: Context, attrs: AttributeSet) : ListView(context, attrs) {
    private var itemCount = 0
    private var itemOffsetY: IntArray? = null
    private var scrollIsComputed = false
    private var listViewHeight = 0

    fun getListHeight(): Int {
        return listViewHeight
    }

    fun computeScrollY() {
        listViewHeight = 0
        itemCount = adapter.count
        if (itemOffsetY == null) {
            itemOffsetY = IntArray(itemCount)
        }
        for (i in 0 until itemCount) {
            val view: View = adapter.getView(i, null, this)
            view.measure(
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
            itemOffsetY?.let { it[i] = listViewHeight }
            listViewHeight += view.measuredHeight
        }
        scrollIsComputed = true
    }

    fun scrollYIsComputed(): Boolean {
        return scrollIsComputed
    }

    fun getComputedScrollY(): Int {
        val scrollY: Int
        val itemY: Int
        val pos: Int = firstVisiblePosition
        val view: View = getChildAt(0)
        itemY = view.top
        scrollY = (itemOffsetY?.get(pos) ?: 0) - itemY
        return scrollY
    }
}
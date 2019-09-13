package id.rezkyauliapratama.fhome.ui.common.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class SwipeLockableViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {
    var swipeEnabled = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (swipeEnabled) super.onTouchEvent(event) else false
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (swipeEnabled) super.onInterceptTouchEvent(event) else false
    }

    override fun executeKeyEvent(event: KeyEvent): Boolean {
        return if (swipeEnabled) super.executeKeyEvent(event) else false
    }
}
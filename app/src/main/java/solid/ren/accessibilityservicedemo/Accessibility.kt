package solid.ren.accessibilityservicedemo

import android.view.accessibility.AccessibilityNodeInfo

/**
 * @author Stas
 * @since 4/9/19.
 */
fun AccessibilityNodeInfo.performClick():Boolean {
    return this.performAction(AccessibilityNodeInfo.ACTION_CLICK)
}
package solid.ren.accessibilityservicedemo.service

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import solid.ren.accessibilityservicedemo.PrintUtils.log
import solid.ren.accessibilityservicedemo.performClick


/**
 * Created by _SOLID
 * Date:2016/7/20
 * Time:16:19
 */
class MyAccessibilityService : AccessibilityService() {

    @RequiresApi(api = Build.VERSION_CODES.DONUT)
    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        //PrintUtils.printEvent(event)
        val nodeInfo = event.source

        if (event.eventType == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
            if (event.packageName.toString().equals("com.android.vending")) {
                val message = StringBuilder()
                if (event.text.isNotEmpty()) {
                    Log.d("test", message.toString())
                }
            }
        }

        if (event.eventType == AccessibilityEvent.TYPE_VIEW_CLICKED) {
            nodeInfo.refresh()
            log("ClassName:" + nodeInfo.className +
                    " Text:" + nodeInfo.text +
                    " ViewIdResourceName:" + nodeInfo.viewIdResourceName +
                    " isClickable:" + nodeInfo.isClickable)
        }

        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            if (event.packageName.toString() == "com.android.vending") {

                val rootInActiveWindow = rootInActiveWindow

                //Inspect app elements if ready
                if (rootInActiveWindow != null) {
                    //Search bar is covered with textview which need to be clicked
                    val searchBarIdle = rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.android.vending:id/search_box_idle_text")
                    if (searchBarIdle.size > 0) {
                        val searchBar = searchBarIdle[0]
                        searchBar.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    }

                    //Check is search bar is visible
                    val searchBars = rootInActiveWindow.findAccessibilityNodeInfosByViewId("com.android.vending:id/search_box_text_input")

                    }

                    val list = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/left_button")
                    for (node in list) {
                        log("ACC::onAccessibilityEvent: left_button $node")
                        node.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    }

                    val button = nodeInfo.findAccessibilityNodeInfosByViewId("com.android.vending:id/button1")
                    button?.forEach { n ->
                        log("id button:$n")
                        n.performClick()
                        return
                    }
                }
            }
        }


        override fun onInterrupt() {
            log("onInterrupt")
        }

        override fun onGesture(gestureId: Int): Boolean {
            log("onGesture")
            return super.onGesture(gestureId)
        }

        @RequiresApi(api = Build.VERSION_CODES.DONUT)
        override fun onServiceConnected() {
            super.onServiceConnected()
            log("onServiceConnected")
            //可用代码配置当前Service的信息

            val info = AccessibilityServiceInfo()
            info.packageNames = arrayOf("com.android.packageinstaller", "com.android.vending", "de.androidpit.app") //监听过滤的包名
            info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK //监听哪些行为
            info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN //反馈
            info.notificationTimeout = 100 //通知的时间

            serviceInfo = info
        }


    }

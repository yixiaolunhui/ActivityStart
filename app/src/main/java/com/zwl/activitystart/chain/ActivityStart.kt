package com.zwl.activitystart.chain

import android.app.Activity
import android.content.Intent
import com.zwl.activitystart.ext.startForResult

object ActivityStarter {

    fun with(activity: Activity): Builder {
        return Builder(activity)
    }

    class Builder(private val activity: Activity) {

        private val taskChainManager by lazy { TaskChainBuilder.builder() }

        fun addInterceptor(interceptor: TaskInterceptor): Builder {
            taskChainManager.addTask(interceptor)
            return this
        }

        fun start(intent: Intent) {
            if (taskChainManager.manager.getTaskCount() > 0) {
                taskChainManager.setStatusListener(object : TaskChainStatusListener {
                    override fun onStatusChanged(isEndOfChain: Boolean) {
                        if (isEndOfChain) {
                            activity.startActivity(intent)
                        }
                    }
                }).execute()
            } else {
                activity.startActivity(intent)
            }
        }

        fun startForResult(requestCode: Int = 1000, intent: Intent, callback: (requestCode: Int, resultCode: Int, data: Intent?) -> Unit) {
            if (taskChainManager.manager.getTaskCount() > 0) {
                taskChainManager.setStatusListener(object : TaskChainStatusListener {
                    override fun onStatusChanged(isEndOfChain: Boolean) {
                        if (isEndOfChain) {
                            activity.startForResult(requestCode, intent, callback)
                        }
                    }
                }).execute()
            } else {
                activity.startForResult(requestCode, intent, callback)
            }
        }
    }
}

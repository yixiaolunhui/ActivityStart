package com.zwl.activitystart

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.zwl.activitystart.chain.ActivityStarter
import com.zwl.activitystart.chain.TaskChain
import com.zwl.activitystart.chain.TaskInterceptor
import com.zwl.activitystart.ext.startForResult
import com.zwl.activitystart.page.LoginActivity
import com.zwl.activitystart.page.NextActivity

object JumpHelper {
    private const val LOG_TAG = "JumpHelper"

    /**
     * 跳转到下一页并回传数据
     */
    fun jumpNextPageForResult(activity: FragmentActivity) {
        activity.startForResult(1000, Intent(activity, NextActivity::class.java).apply {
            putExtra("message", "我是上个界面传入的数据")
        }) { requestCode, resultCode, data ->
            if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
                val message = data?.getStringExtra("message")
                Log.d(LOG_TAG, "message=$message")
            }
        }
    }


    fun jumpNextPageLoginForResult(activity: FragmentActivity) {
        ActivityStarter.with(activity).addInterceptor(object : TaskInterceptor {
            override fun execute(chain: TaskChain) {
                activity.startForResult(1001, Intent(activity, LoginActivity::class.java)) { requestCode, resultCode, data ->
                    if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
                        val message = data?.getStringExtra("message")
                        Log.d(LOG_TAG, "message=$message")
                        chain.proceed()
                    }
                }
            }
        }).startForResult(1002, Intent(activity, NextActivity::class.java).apply {
            putExtra("message", "我是上个界面传入的数据")
        }) { requestCode, resultCode, data ->
            if (requestCode == 1002 && resultCode == Activity.RESULT_OK) {
                val message = data?.getStringExtra("message")
                Log.d(LOG_TAG, "message=$message")
            }
        }
    }

}
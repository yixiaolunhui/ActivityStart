package com.zwl.activitystart.page

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.zwl.activitystart.R

class NextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
        findViewById<Button>(R.id.next).setOnClickListener {
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra("message","NextActivity 返回的数据")
            })
            finish()
        }
    }
}
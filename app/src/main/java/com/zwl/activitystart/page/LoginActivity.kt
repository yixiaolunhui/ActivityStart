package com.zwl.activitystart.page

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.zwl.activitystart.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<Button>(R.id.login).setOnClickListener {
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra("message","登录成功")
            })
            finish()
        }
    }
}
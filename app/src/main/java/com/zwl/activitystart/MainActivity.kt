package com.zwl.activitystart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.zwl.activitystart.databinding.ActivityMainBinding
import com.zwl.activitystart.page.NextActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(LayoutInflater.from(this)) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //跳转到下一页并回传数据
        binding.jumpNextPage.setOnClickListener {
            JumpHelper.jumpNextPageForResult(this)
        }

        //跳转到下一页(必须登录或者...)并回传数据
        binding.jumpPageNeedLogin.setOnClickListener {
            JumpHelper.jumpNextPageLoginForResult(this)
        }


        startActivity(Intent(this,NextActivity::class.java).apply {
            putExtra("params","测试参数数据")
        })

    }
}

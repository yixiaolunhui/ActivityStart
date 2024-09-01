

#### 需求1：跳转到下一个界面：NextActivity
~~~
startActivity(Intent(this,NextActivity::class.java))
~~~

#### 需求2：跳转到下一个界面(并传参数)：NextActivity
~~~
 startActivity(Intent(this,NextActivity::class.java).apply { 
            putExtra("params","测试参数数据")
 })
~~~
#### 需求3：跳转到下一个界面(并传参数)&(返回结果)：NextActivity

- 对Activity拓展方法startForResult，支持直接返回结果
~~~
 activity.startForResult(1000, Intent(activity, NextActivity::class.java).apply {
            putExtra("message", "我是上个界面传入的数据")
        }) { requestCode, resultCode, data ->
            if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
                val message = data?.getStringExtra("message")
                Log.d(LOG_TAG, "message=$message")
            }
        }
~~~

#### 需求4：跳转到下一个界面(并传参数)&(多个拦截)：NextActivity
~~~
   ActivityStarter.with(activity)
            // 登录拦截
            .addInterceptor(LoginInterceptor())
            // 认证拦截
            .addInterceptor(AuthInterceptor())
            // 跳转下一页
            .start(Intent(activity, NextActivity::class.java).apply {
                putExtra("message", "我是上个界面传入的数据")
            })
~~~

#### 需求4：跳转到下一个界面(并传参数)&(多个拦截)&(返回结果)：NextActivity
~~~
 ActivityStarter.with(activity)
            // 登录拦截
            .addInterceptor(LoginInterceptor())
            // 认证拦截
            .addInterceptor(AuthInterceptor())
            // 跳转下一页
            .startForResult(1001,Intent(activity, NextActivity::class.java).apply {
                putExtra("message", "我是上个界面传入的数据")
            }){
                requestCode, resultCode, data ->
                if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
                   //TODO
                }   
            }

~~~

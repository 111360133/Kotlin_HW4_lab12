package com.example.myapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    override fun onCreate() {
        super.onCreate()

        // 使用獨立 Thread 執行耗時任務
        Thread {
            try {
                Thread.sleep(3000) // 模擬延遲三秒的操作

                // 使用 Intent 啟動 SecActivity
                val intent = Intent(this, MainActivity2::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK // 指定新的 Activity 實例
                }
                startActivity(intent)

                // 停止服務
                stopSelf()
            } catch (e: InterruptedException) {
                // 處理可能的中斷例外
                Log.e("MyService", "Thread interrupted", e)
            }
        }.start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // 返回 START_NOT_STICKY，Service 被系統終止後不會自動重啟
        return START_NOT_STICKY
    }

    // 綁定 Service 時調用，這裡不需要綁定，直接返回 null
    override fun onBind(intent: Intent): IBinder? = null
}

package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 確保main view已正確載入，否則可能拋出 NullPointerException
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 設定按鈕監聽器
        val startButton = findViewById<Button>(R.id.btnStart)
        startButton?.setOnClickListener {
            // 確保Service類別存在
            try {
                // 啟動 Service
                startService(Intent(this, MyService::class.java))

                // 顯示 Toast 訊息
                Toast.makeText(this, "啟動 Service", Toast.LENGTH_SHORT).show()

                // 關閉 Activity
                finish()
            } catch (e: Exception) {
                // 如果啟動 Service 發生問題，顯示錯誤訊息
                Toast.makeText(this, "啟動 Service 失敗: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}

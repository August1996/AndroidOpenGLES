package com.demo.android.opengles

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.demo.android.opengles.simple.SimpleRenderActivity

class MainActivity : AppCompatActivity() {

    data class Item(
        val title: String,
        val act: Class<out Activity>,
        val permissions: List<String> = arrayListOf(),
        val params: List<Pair<String, String>> = arrayListOf()
    )

    private val rootView by lazy {
        LinearLayout(this)
            .apply {
                orientation = LinearLayout.VERTICAL
            }
    }


    private val clickItems = arrayListOf<Item>()


    init {
        clickItems.add(Item("OpenGLES简介", SimpleRenderActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val scrollView = ScrollView(this)
        scrollView.addView(rootView)
        setContentView(scrollView)

        clickItems.forEach { item ->
            val button = Button(this)
            button.text = item.title
            button.setOnClickListener {
                var allPermissionsGranted = true
                item.permissions.forEach { permission ->
                    if (ContextCompat.checkSelfPermission(
                            this,
                            permission
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        allPermissionsGranted = false
                    }
                }
                if (allPermissionsGranted) {
                    val intent = Intent(this, item.act)
                    item.params.forEach {
                        intent.putExtra(it.first, it.second)
                    }
                    startActivity(intent)
                } else {
                    ActivityCompat.requestPermissions(this, item.permissions.toTypedArray(), 0)
                }
            }
            rootView.addView(button)
        }
    }
}
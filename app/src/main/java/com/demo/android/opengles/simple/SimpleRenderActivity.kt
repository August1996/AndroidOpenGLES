package com.demo.android.opengles.simple

import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class SimpleRenderActivity : AppCompatActivity() {

    private val glSurfaceView by lazy {
        GLSurfaceView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val random = Random()
        val render = SimpleRenderer()

        // 下面1、2、3是使用GLSurfaceView的基本步骤
        // 1、设置gles版本
        glSurfaceView.setEGLContextClientVersion(2)
        // 2、设置渲染器
        glSurfaceView.setRenderer(render)
        // 3、设置渲染模式
        // RENDERMODE_CONTINUOUSLY：自动刷新，也就是不断回调Renderer.onDrawFrame
        // RENDERMODE_WHEN_DIRTY：手动刷新，调用requestRender方法时才会回调Renderer.onDrawFrame
        glSurfaceView.renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY

        glSurfaceView.setOnClickListener {
            // 设置随机色
            glSurfaceView.queueEvent {
                render.glSetColor(random.nextFloat(), random.nextFloat(), random.nextFloat())
            }
        }

        setContentView(glSurfaceView)
    }
}
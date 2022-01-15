package com.demo.android.opengles.simple

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

// Renderer的三个方法都是在EGLThread中被调用的
class SimpleRenderer : GLSurfaceView.Renderer {

    // EGLSurface创建完后回调，也就是EGLThread启动时
    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {

    }

    // EGLSurface大小改变后被回调，也就是EGLThread初始化以及GLSurfaceView大小被改变时
    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
    }

    // 每一帧渲染都会回调到这里，这里写主要的渲染逻辑
    override fun onDrawFrame(gl: GL10?) {
        // glClear清除屏幕，结合GLES20.GL_COLOR_BUFFER_BIT使用会设置成glClearColor设置的颜色
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
    }

    /**
     * 自定义方法：设置颜色
     */
    fun glSetColor(red: Float, green: Float, blue: Float) {
        GLES20.glClearColor(red, green, blue, 1.0f)
    }
}
//package com.konstantinmuzhik.hw80
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.graphics.*
//import android.util.AttributeSet
//import android.view.View
//
//
//class MyCustomAnimation @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
//) : View(context, attrs, defStyleAttr) {
//
//    private val andrewUp = BitmapFactory.decodeResource(resources, R.drawable.up)!!
//    private val andrewDown = BitmapFactory.decodeResource(resources, R.drawable.down)!!
//
//    private var up = 0f
//    private var down = 0f
//    private var f = true
//    private var distnace = 80f
//
//    @SuppressLint("DrawAllocation")
//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//
//
//        val andrewHeightUp = (andrewUp.height * 2 / 3).toFloat()
//        val andrewHeightDown = (andrewDown.height * 2 / 3).toFloat()
//        val andrewWidth = (andrewUp.width * 2 / 3).toFloat()
//        val andrewDistanceVertical = (width - andrewWidth) / 2
//        canvas.save()
//        //canvas.rotate(deg, 0f, 0f)
//
//        val rectUp = RectF(
//            (width - andrewWidth) / 2,
//            (height - andrewHeightUp - andrewHeightDown) / 2 - up,
//            (width - andrewWidth) / 2 + andrewWidth,
//            (height - andrewHeightUp - andrewHeightDown) / 2 + andrewHeightUp - up
//        )
//
//        val rectDown = RectF(
//            (width - andrewWidth) / 2,
//            (height - andrewHeightUp - andrewHeightDown) / 2 + andrewHeightUp + down + distnace,
//            (width - andrewWidth) / 2 + andrewWidth,
//            (height - andrewHeightUp - andrewHeightDown) / 2 + + andrewHeightUp + andrewHeightDown + down + distnace
//        )
//
//
//
//
//        canvas.drawBitmap(andrewUp, null, rectUp, null)
//        canvas.drawBitmap(andrewDown, null, rectDown, null)
//
//        canvas.restore()
//
//        if (f) {
//            up++
//            down++
//        } else {
//            up--
//            down--
//            if (up == 0f)
//                f = true
//        }
//
//        if (up == 70f) {
//            f = false
//        }
//
//        invalidate()
//    }
//
//}
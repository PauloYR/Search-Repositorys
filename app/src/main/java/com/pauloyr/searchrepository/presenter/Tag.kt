package com.pauloyr.searchrepository.presenter


import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.pauloyr.searchrepository.R


class Tag(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs), View.OnClickListener {

    private var name: TextView? = null
    private val paddingHorizontal = 20
    private val paddingVertical = 7
    var selectd = true
    private var backgroundColor: ColorStateList? = null
    private var backgroundTranparentColor: ColorStateList? = null
    private var backgroundStroker: ColorStateList? = null

    init {
        initView(attrs, 0)
    }

    private fun initView(attrs: AttributeSet?, defStyleAttr: Int) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.Tag, defStyleAttr, 0)
        backgroundColor = array.getColorStateList(R.styleable.Tag_backgroud_color)
        backgroundTranparentColor =
            array.getColorStateList(R.styleable.Tag_backgroud_color_tranparent)
        backgroundStroker = array.getColorStateList(R.styleable.Tag_background_stoker)
        val text = array.getString(R.styleable.Tag_text)

        array.recycle()

        setBackground(backgroundTranparentColor!!.defaultColor, backgroundColor!!.defaultColor)

        name = TextView(context)
        name?.text = text
        name?.setTextColor(Color.WHITE)
        name?.textSize = 12f
        name?.typeface = Typeface.DEFAULT_BOLD;
        setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical)
        addView(name)
        setOnClickListener(this)
    }

    private fun Tag.setBackground(
        colorFrom: Int?,
        colorTo: Int?
    ) {
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation.duration = 500

        colorAnimation.addUpdateListener { animator ->
            val border = GradientDrawable()
            border.setColor(animator.animatedValue as Int)
            border.setStroke(2, backgroundStroker!!.defaultColor)
            border.cornerRadius = 10f

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                setBackgroundDrawable(border)
            } else {
                setBackground(border)
            }
        }
        colorAnimation.start()
    }

    private fun setColorText(
        colorFrom: Int?,
        colorTo: Int?
    ){
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation.duration = 500

        colorAnimation.addUpdateListener { animator ->
            name?.setTextColor(animator.animatedValue as Int)
        }
        colorAnimation.start()
    }

    override fun onClick(v: View?) {
        selectd = !selectd
        if (selectd) {
            setBackground(backgroundTranparentColor!!.defaultColor, backgroundColor!!.defaultColor)
            setColorText(Color.GRAY,Color.WHITE)
        } else {
            setBackground(backgroundColor!!.defaultColor,backgroundTranparentColor!!.defaultColor)
            setColorText(Color.WHITE,Color.GRAY)
        }
    }
}
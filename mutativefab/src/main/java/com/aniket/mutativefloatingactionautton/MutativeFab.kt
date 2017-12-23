package com.aniket.mutativefloatingactionautton

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v4.content.ContextCompat
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView


/**
 * Created by aniket on 03-12-2017.
 */
class MutativeFab : ConstraintLayout {

    private val TAG = MutativeFab::class.java.simpleName
    //TODO: add v-21 of layout file for elevation
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var constraintLayout: ConstraintLayout
    private var textVisibility = true
    private val animationDuration: Long = 150
    private lateinit var ogText: String
    private val constraintSet1 = ConstraintSet()
    private val constraintSet2 = ConstraintSet()

    constructor(context: Context) : super(context) {
        init(context)
    }

    private fun init(context: Context) {
        val view: View = inflate(context, R.layout.mutative_fab_layout, this)
        imageView = view.findViewById(R.id.image)
        textView = view.findViewById(R.id.card_textView)
        constraintLayout = view.findViewById(R.id.constraint_Layout)
        ogText = textView.text.toString()
        constraintSet1.clone(constraintLayout)
        constraintSet2.clone(context, R.layout.mutative_fab_layout_alt)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
        getAttributeSet(context, attrs)
    }

    private fun getAttributeSet(context: Context, attrs: AttributeSet) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.MutativeFab)

        if (!typedArray.hasValue(R.styleable.MutativeFab_fabIcon)) {
            throw RuntimeException("missing attribute fabIcon")
        }
        if (!typedArray.hasValue(R.styleable.MutativeFab_fabText)) {
            throw RuntimeException("missing attribute fabText")
        }
        val imageResId: Int = typedArray.getResourceId(R.styleable.MutativeFab_fabIcon, R.drawable.ic_add)
        val fabText = typedArray.getString(R.styleable.MutativeFab_fabText)
        val fabTextVisibility = typedArray.getInt(R.styleable.MutativeFab_fabTextVisibility, 0)
        val fabTextColor = typedArray.getColor(R.styleable.MutativeFab_fabTextColor,
                ContextCompat.getColor(context, android.R.color.white))
        val fabBackgroundColor: Int = typedArray.getColor(R.styleable.MutativeFab_fabBackgroundColor,
                ContextCompat.getColor(context, R.color.colorAccent))
        setFabIcon(imageResId)
        setFabText(fabText)
        setFabTextVisibility(fabTextVisibility)
        setFabBackgroundColor(fabBackgroundColor)
        setFabTextColor(fabTextColor)

        typedArray.recycle()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
        getAttributeSet(context, attrs)
    }

    fun setFabIcon(resId: Int) {
        imageView.setImageResource(resId)
    }

    fun setFabText(text: String) {
        textView.text = text
        ogText = text
    }


    fun setFabTextVisibility(visibility: Int) {

        var constraint: ConstraintSet = constraintSet1
        if (visibility == View.VISIBLE) {
            constraint = constraintSet1
            textVisibility = true
        }
        if (visibility == View.GONE) {
            constraint = constraintSet2
            textVisibility = false
        }

        val myTransition = ChangeBounds()
        myTransition.duration = animationDuration

        TransitionManager.beginDelayedTransition(this, myTransition)
        constraint.applyTo(constraintLayout)
    }


    fun setFabTextColor(color: Int) {
        textView.setTextColor(color)
    }

    fun setFabBackgroundColor(color: Int) {
        val background = constraintLayout.background as RippleDrawable
        val bgShape = background.findDrawableByLayerId(R.id.fab_shape) as GradientDrawable
        bgShape.setColor(color)
    }


    fun getFabTextVisibility(): Boolean = textVisibility

}

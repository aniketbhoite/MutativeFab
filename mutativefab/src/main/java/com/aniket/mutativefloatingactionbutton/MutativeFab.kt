package com.aniket.mutativefloatingactionbutton

import android.content.Context
import android.content.res.TypedArray
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.support.annotation.ColorInt
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.design.widget.CoordinatorLayout
import android.support.transition.ChangeBounds
import android.support.transition.TransitionManager
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView


/**
 * Created by aniket on 03-12-2017.
 */

@CoordinatorLayout.DefaultBehavior(SnackbarBehavior::class)
class MutativeFab @JvmOverloads constructor(
        context: Context,
        attributeSet: AttributeSet? = null,
        defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var constraintLayout: ConstraintLayout
    private val animationDuration: Long = 150
    private val constraintSet1 = ConstraintSet()
    private val constraintSet2 = ConstraintSet()

    var fabTextVisibility: Int = View.VISIBLE
        set(visibility) {
            var constraint: ConstraintSet = constraintSet1
            if (visibility == View.VISIBLE) {
                constraint = constraintSet1
            }
            if (visibility == View.GONE) {
                constraint = constraintSet2
            }

            val myTransition = ChangeBounds()
            myTransition.duration = animationDuration

            TransitionManager.beginDelayedTransition(this, myTransition)
            constraint.applyTo(constraintLayout)

            field = visibility
        }



    init {
        inflate(context, R.layout.mutative_fab_layout, this).apply {
            imageView = findViewById(R.id.image)
            textView = findViewById(R.id.card_textView)
            constraintLayout = findViewById(R.id.constraint_Layout)
            constraintSet1.clone(constraintLayout)
            constraintSet2.clone(context, R.layout.mutative_fab_layout_alt)
            isClickable = true
            isFocusable = true
        }
        attributeSet?.let {
            getAttributeSet(
                    context = context,
                    attrs = it
            )
        }
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
        this.fabTextVisibility = fabTextVisibility
        setFabBackgroundColor(fabBackgroundColor)
        setFabTextColor(fabTextColor)

        typedArray.recycle()
    }

    fun setFabIcon(resId: Int) {
        imageView.setImageResource(resId)
    }

    fun setFabText(text: String) {
        textView.text = text
    }

    fun setFabTextColor(@ColorInt color: Int) {
        textView.setTextColor(color)
    }

    fun setFabBackgroundColor(@ColorInt color: Int) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if (constraintLayout.background is LayerDrawable) {
                (constraintLayout.background as LayerDrawable).findDrawableByLayerId(R.id.fab_shape)?.apply {
                    mutate().setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                }
            }
        } else {
            constraintLayout.background.mutate().setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }


}

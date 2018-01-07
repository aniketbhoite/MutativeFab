package com.example.aniket.floatingtextbutton

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.aniket.mutativefloatingactionbutton.MutativeFab


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val constraintLayout: ConstraintLayout = findViewById(R.id.constraintLayout)
        val button = findViewById<Button>(R.id.button)

        val constraintSet1 = ConstraintSet()
        constraintSet1.clone(constraintLayout)
        val constraintSet2 = ConstraintSet()
        constraintSet2.clone(this, R.layout.activity_main_alt)

        val mFab = findViewById<MutativeFab>(R.id.m_fab)
        mFab.apply {
            setFabText("start chat")
            setFabTextColor(ContextCompat.getColor(context, R.color.textColor))
            setFabIcon(R.drawable.ic_chat)
            setFabBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark))
        }

        button.setOnClickListener {


            mFab.setFabTextVisibility(if (mFab.getFabTextVisibility() == View.VISIBLE) View.GONE else View.VISIBLE)

        }


    }


}

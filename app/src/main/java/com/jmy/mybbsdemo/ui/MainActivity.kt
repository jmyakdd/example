package com.jmy.mybbsdemo.ui

import com.jmy.mybbsdemo.R
import jmy.mylibrary.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
    }

    override fun initView() {
//        showDialog()
        /*image.setImageResource(R.drawable.loading_animation)
        var anima:AnimationDrawable = image.drawable as AnimationDrawable
        anima.start()*/
    }

    override fun initEnvent() {
    }
}

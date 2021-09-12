package com.watermelon.memegenerator.ui

import android.util.Log
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.watermelon.memegenerator.R
import com.watermelon.memegenerator.ui.base.BaseActivity
import com.watermelon.memegenerator.databinding.ActivityMainBinding
import com.watermelon.memegenerator.util.Constant

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val theme: Int
        get() = R.style.Theme_MemeGenerator

    override fun setup() {
        initMenu()
    }

    override fun callBack() {
        binding.apply {
            buttonGenerate.setOnClickListener {
                initRequest()
            }
        }
    }

    override val inflate: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    private fun initMenu() {
        val menuArray = resources.getStringArray(R.array.meme_menu)
        val adapter = ArrayAdapter(this, R.layout.dropdown_item, menuArray)
        binding.selectedMeme.setAdapter(adapter)
    }

    private fun initRequest() {
        binding.apply {
            val meme = selectedMeme.text.toString().replace(' ', '-')
            val param1 = "meme=$meme"
            val param2 = "top=${textTop.text}"
            val param3 = "bottom=${textBottom.text}"
            val url = "${Constant.URL}?$param1&$param2&$param3"
            loadImage(url = url)
            Log.v("MAIN_ACTIVITY", url)
        }
    }

    private fun loadImage(url: String) {
        Glide.with(this).load(url)
            .placeholder(R.drawable.ic_baseline_downloading_24)
            .error(R.drawable.ic_baseline_error_outline_24)
            .into(binding.imageView)
    }

}
package jp.wings.nikkeibp.omikuji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.wings.nikkeibp.omikuji.databinding.MainBinding
import android.animation.AnimatorListenerAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //文字を表示する
        binding.helloView.text = "おみくじアプリです。"

        /* Fade out&in 1 by chatGPT-4
        binding.helloView.animate()
            .alpha(0f)
            .setDuration(1000)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: android.animation.Animator) {
                    // Change the text
                    binding.helloView.text = "可可的脸\n=_="

                    // Fade in
                    binding.helloView.animate()
                        .alpha(1f)
                        .setDuration(1000)
                        .setListener(null)
                }
            })*/

        /* Fade in 2 by bing
        binding.helloView.apply {
            alpha = 0f
            text = "可可的脸\n=_="
            animate()
                .alpha(1f)
                .setDuration(1000)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: android.animation.Animator) {
                        animate().alpha(0f).duration = 1000
                    }
                })
        }*/

    }
}
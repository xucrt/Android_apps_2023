package jp.wings.nikkeibp.omijkuji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.wings.nikkeibp.omijkuji.databinding.MainBinding

class OmikujiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //文字表示
        binding.helloView.text = "おみくじアプリでござる。"
    }
}
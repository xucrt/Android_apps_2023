package jp.wings.nikkeibp.omijkuji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.wings.nikkeibp.omijkuji.databinding.MainBinding
import java.util.Random

class OmikujiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //文字表示
        //binding.helloView.text = "おみくじアプリでござる。"　//P60
        /* P74
        val str = "大吉"
        val rnd = Random()
        val number = rnd.nextInt(3)
        binding.helloView.text = "$str 香香 ${number + 1}"
        */
        /*P81
        var str = "大吉"
        val rnd = Random()
        val number = rnd.nextInt(3)
        if(number == 0){
            str = "吉"
        }
        else if(number == 1){
            str = "凶"
        }
        binding.helloView.text = str
        */
        //P87くじ番号の取得
        val rnd = Random()
        val number = rnd.nextInt(20)

        //おみくじ棚の準備
        val omikujiShelf = Array<String>(20) {"吉"}
        omikujiShelf[0] = "大吉"
        omikujiShelf[19] = "凶"

        //おみくじ棚の取得
        val str = omikujiShelf[number]

        binding.helloView.text = "$str 番号⇒ ${number}"
    }
}
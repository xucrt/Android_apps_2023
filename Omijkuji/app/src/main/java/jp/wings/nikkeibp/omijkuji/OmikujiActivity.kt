package jp.wings.nikkeibp.omijkuji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import jp.wings.nikkeibp.omijkuji.databinding.MainBinding
import jp.wings.nikkeibp.omijkuji.databinding.OmikujiBinding
import java.util.Random

class OmikujiActivity : AppCompatActivity() {
    lateinit var binding: OmikujiBinding //P116
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OmikujiBinding.inflate(layoutInflater) //P117
        //val binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        //文字表示
        //binding.helloView.text = "おみくじアプリでござる。"　//P60
        //P74
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
        */
    }

    //P130
    fun onButtonClick(v: View){
        //P133
        val translate = TranslateAnimation(0f,0f,0f,-200f)
        translate.repeatMode = Animation.REVERSE
        translate.repeatCount = 5
        translate.duration = 100

        //P139
        val rotate = RotateAnimation(0f,-36f,binding.imageView.width/2f, binding.imageView.height/2f)
        rotate.duration = 200
        val set = AnimationSet(true)
        set.addAnimation(rotate)
        set.addAnimation(translate)

        binding.imageView.startAnimation(set)
        //binding.imageView.setImageResource(R.drawable.result1)
    }
}
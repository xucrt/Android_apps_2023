package jp.wings.nikkeibp.omijkuji

import android.hardware.SensorEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import java.util.Random

class OmikujiBox(): Animation.AnimationListener {
    /*P96
    val number : Int = -1 //くじ番号；プロパティの宣言；プロパティは クラス 内 のグロバール変数である。
    */

    //p252
    var beforeTime = 0L
    var beforeValue = 0F

    //p267
    lateinit var omikujiButton: Button
    //P147
    lateinit var omikujiView: ImageView

    var finish = false //箱から出たか？

    //P100 乱数を返すようにアクセサー(getter)を使った。
    val number : Int //くじ番号(0-19の乱数)
        get() {
            val rnd = Random()
            return rnd.nextInt(20)
        }

    //p252-253
    fun chkShake(event: SensorEvent?): Boolean{

        val nowtime = System.currentTimeMillis()
        val differtime: Long = nowtime - beforeTime
        val nowvalue: Float = (event?.values?.get(0) ?:0F) + (event?.values?.get(1) ?: 0F)

        if(1500 < differtime){

            //前回の値との差からスピードを計算
            val speed = Math.abs(nowvalue - beforeValue) / differtime * 10000
            beforeTime = nowtime
            beforeValue = nowvalue

            //50を超えるスピードでシェイクしたとみなす
            if(50 < speed){
                return true
            }
        }
        return false
    }

    //P150
    fun shake(){
        //P133
        val translate = TranslateAnimation(0f,0f,0f,-200f)
        translate.repeatMode = Animation.REVERSE
        translate.repeatCount = 5
        translate.duration = 100

        //P139
        val rotate = RotateAnimation(0f,-36f,omikujiView.width/2f, omikujiView.height/2f)
        rotate.duration = 200
        val set = AnimationSet(true)
        set.addAnimation(rotate)
        set.addAnimation(translate)

        set.setAnimationListener(this)

        omikujiView.startAnimation(set)
    }

    override fun onAnimationStart(animation: Animation?) {
    }

    override fun onAnimationEnd(animation: Animation?) {
        omikujiView.setImageResource(R.drawable.omikuji2)
        omikujiButton.setText(R.string.bt_result)
        omikujiButton.visibility = View.VISIBLE
        finish = true
    }

    override fun onAnimationRepeat(animation: Animation?) {
    }

}


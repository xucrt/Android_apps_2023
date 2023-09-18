package jp.wings.nikkeibp.omijkuji

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import jp.wings.nikkeibp.omijkuji.databinding.FortuneBinding
import jp.wings.nikkeibp.omijkuji.databinding.OmikujiBinding

class OmikujiActivity : AppCompatActivity(), SensorEventListener {

    //p242
    lateinit var manager: SensorManager

    //p188
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /*
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.item1 -> {
                    Toast.makeText(this, "設定选项被选择了", Toast.LENGTH_LONG).show()
                    return true
                }
                R.id.item2 -> {
                    Toast.makeText(this, "おみくじアプリについて选项被选择了", Toast.LENGTH_LONG).show()
                    return true
                }
                R.id.item3 -> {
                    Toast.makeText(this, "許伝授の初アプリ选项被选择了", Toast.LENGTH_LONG).show()
                    return true
                }
            }
            return super.onOptionsItemSelected(item)
        }
        */

    //P191
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       //val toast = Toast.makeText(this@OmikujiActivity, "ばぶばぶばぶばぶばぶばぶばぶばぶ", Toast.LENGTH_LONG)
        // Toastメッセージが表示されなかったテスト↑でした。結局ハードウェアシミュレーターに問題があった、エラーが出ていた携帯のVMを削除して作り直したら、Toastメッセージが表示できた。

        /* P221
        val toast = Toast.makeText(this, item.title, Toast.LENGTH_LONG)
        toast.show()
        */

        //Toast.makeText(this, item.title, Toast.LENGTH_LONG).show()
        //return true

        if (item.itemId == R.id.item1){
            val intent = Intent(this, OmikujiPreferenceActivity::class.java)
            startActivity(intent)
        }
        else {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }


    //P169 おみくじ棚の配列
    val omikujiShelf = Array<OmikujiParts>(20)
    { OmikujiParts(R.drawable.result2, R.string.contents1)}

    //P169おみくじ番号保管用
    var omikujiNumber = -1

    val omikujiBox = OmikujiBox() //P152

    lateinit var binding: OmikujiBinding //P116
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OmikujiBinding.inflate(layoutInflater) //P117
        //val binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //p243
        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        omikujiBox.omikujiView = binding.imageView //P152
        omikujiBox.omikujiButton = binding.button //p268

        //P170 おみくじ棚の準備
        omikujiShelf[0].drawID = R.drawable.result1
        omikujiShelf[0].fortuneID = R.string.contents2

        omikujiShelf[1].drawID = R.drawable.result3
        omikujiShelf[1].fortuneID = R.string.contents9

        omikujiShelf[2].fortuneID = R.string.contents3
        omikujiShelf[3].fortuneID = R.string.contents4
        omikujiShelf[4].fortuneID = R.string.contents5
        omikujiShelf[5].fortuneID = R.string.contents6
        omikujiShelf[6].fortuneID = R.string.contents7
        omikujiShelf[7].fortuneID = R.string.contents8
        omikujiShelf[8].fortuneID = R.string.contents10
        omikujiShelf[9].fortuneID = R.string.contents1

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

    //p244
    override fun onResume() {
        super.onResume()

        val sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        manager.registerListener(this, sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        manager.unregisterListener(this)
    }

    //P130
    fun onButtonClick(v: View){
        //p269
        if(omikujiNumber < 0 && omikujiBox.finish){
            drawResult()
        }

        omikujiBox.shake() //P152

        /*P133
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
        */
        //binding.imageView.setImageResource(R.drawable.result1)
    }

    //P170
    fun drawResult(){

        //おみくじ番号を取得
        omikujiNumber = omikujiBox.number

        //おみくじ棚の配列から、omikujiPartsを取得
        val op = omikujiShelf[omikujiNumber]

        //レイアオウトを運勢表示に変更する
        val fortuneBinding = FortuneBinding.inflate(layoutInflater)
        setContentView(fortuneBinding.root)

        //画像とテキストを変更する。
        fortuneBinding.imageView2.setImageResource(op.drawID)
        fortuneBinding.textView.setText(op.fortuneID)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN){
            if(omikujiNumber < 0 && omikujiBox.finish){
                drawResult()
            }
        }
        return super.onTouchEvent(event)
    }

    //p246
    override fun onSensorChanged(event: SensorEvent?) {

        //p253
        if (omikujiBox.chkShake(event)){
            if (omikujiNumber < 0){
                omikujiBox.shake()
            }
        }

        val value = event?.values?.get(0)
        if (value != null && 10 < value){
            Toast.makeText(this, "加速度：${value}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }

}
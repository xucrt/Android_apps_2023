package jp.wings.nikkeibp.omijkuji

import android.content.pm.PackageManager
import android.os.Bundle
import jp.wings.nikkeibp.omijkuji.databinding.AboutBinding
import androidx.appcompat.app.AppCompatActivity

//P224-225
class AboutActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val aboutBinding = AboutBinding.inflate(layoutInflater)
        setContentView(aboutBinding.root)

        val info = packageManager.getPackageInfo(packageName, 0)
        //val info = packageManager.getPackageInfo(String packageName, @PackageManager.PackageInfoFlags int flags)
        aboutBinding.textView2.text = "Version" + info.versionName
    }
}
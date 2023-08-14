package jp.wings.nikkeibp.omijkuji

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class OmikujiPreferenceActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceStae: Bundle?) {
        super.onCreate(savedInstanceStae)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content, OmikujiPreferenceFragment())
        fragmentTransaction.commit()
    }
}
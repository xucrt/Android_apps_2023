package jp.wings.nikkeibp.omijkuji

import java.util.Random

class Omikuji() {
    /*P96
    val number : Int = -1 //くじ番号；プロパティの宣言；プロパティは クラス 内 のグロバール変数である。
    */

    //P100 乱数を返すようにアクセサー(getter)を使った。
    val number : Int //くじ番号(0-19の乱数)
        get() {
            val rnd = Random()
            return rnd.nextInt(20)
        }
}


package com.example.qtetris

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class GameOver : AppCompatActivity() {
    val PREFS = "game_configuracoes"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val configuracoes = getSharedPreferences(PREFS,Context.MODE_PRIVATE)
        val params = intent.extras
        val record = configuracoes.getInt("record", 0)
        val pontos = params?.getInt("pontos")
/*
        if(pontos != null && pontos > record){
            txtNewRecord.text = getResources().getString(R.string.new_record) + " " + pontos
            txtNewRecord.visibility = View.VISIBLE
            configuracoes.edit().putInt("record", pontos).commit()
        }
*/
    }
}
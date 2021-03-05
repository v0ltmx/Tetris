package com.example.qtetris

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.qtetris.Constantes.Constantes
import com.example.qtetris.databinding.ActivityMainBinding

//import androidx.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        var params = intent.extras
        var continuar = params?.getBoolean("pause")

            if(continuar == true){
                binding.botaoContinuar.visibility = View.VISIBLE
            }
            else{
                binding.botaoContinuar.visibility = View.INVISIBLE
            }


        binding.apply {
            botaoNovojogo.setOnClickListener {
                var intent = Intent(this@MainActivity, Tabuleiro::class.java)
                startActivity(intent)
            }
            botaoConfigurar.setOnClickListener {
                val intent = Intent(this@MainActivity, Configurar::class.java)
                startActivityForResult(intent, 3)
            }

            botaoContinuar.setOnClickListener{
               val intent = Intent()
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

        }
        /*fun updateScore() {
            txtPoints.text = "${getResources().getString(R.string.points_label)} ${vm.points}"
        }*/


        /*
            override fun onStart(){
                super.onStart()
                if(GameEstado.saved){
                    botaoContinuar.visibility = View.VISIBLE
                }
            }*/


    }
}
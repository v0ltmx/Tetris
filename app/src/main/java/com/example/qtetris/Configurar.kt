package com.example.qtetris

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.qtetris.Constantes.Constantes
import com.example.qtetris.Constantes.Dificuldades
import com.example.qtetris.databinding.ActivityConfigurarBinding

class Configurar : AppCompatActivity() {
    lateinit var binding:ActivityConfigurarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this,R.layout.activity_configurar)
    // em construção ...
        binding.apply{
            facilButton.setOnClickListener {
                finish()
            }
            medioButton.setOnClickListener {
                finish()
            }
            dificilButton.setOnClickListener {
                finish()
            }
        }
    }

    override fun onStop(){
        super.onStop()
        val configuracoes = getSharedPreferences(Constantes.PREFS, Context.MODE_PRIVATE)
        val edit = configuracoes.edit()

        binding.apply {
            if(facilButton!!.isClickable){
                edit.putString(Dificuldades.DIFICULDADE, Dificuldades.FACIL).apply()
                edit.commit()
            }
            if(medioButton!!.isClickable){
                edit.putString(Dificuldades.DIFICULDADE, Dificuldades.MEDIO).apply()
                edit.commit()
                }
            if(dificilButton!!.isClickable){
                edit.putString(Dificuldades.DIFICULDADE, Dificuldades.DIFICIL).apply()
                edit.commit()
            }
            else{
                edit.remove(Dificuldades.DIFICULDADE)
                edit.commit()
            }
        }
    }
}
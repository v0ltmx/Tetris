package com.example.qtetris

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.qtetris.databinding.ActivityGameOverBinding

class GameOver : AppCompatActivity() {
    lateinit var binding:ActivityGameOverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_game_over)


        var config = getSharedPreferences("${R.string.app_name}_configuracoes", Context.MODE_PRIVATE)
        var pontuacao = intent.extras?.getInt("pontuacao");
        var record = config.getInt("record", 0);

        if (pontuacao != null) {
            if(pontuacao > record){
                config.edit().putInt("record", pontuacao).apply();
            }
        }
        record = config.getInt("record", 0);


        binding.apply {
            textViewPontuacaoValue.text = pontuacao.toString()
            textViewRecordValue.text = record.toString()

            buttonNovoJogo.setOnClickListener{
                val intent = Intent(this@GameOver,Tabuleiro::class.java)
                startActivityForResult(intent, 1)
                finish()
            }

            buttonSair.setOnClickListener{
                val intent = Intent(this@GameOver, MainActivity::class.java)
                startActivityForResult(intent,2)
                finish()
            }
        }


     //   binding.textViewPontuacaoValue.text = pontuacao.toString();
      //  binding.textViewRecordValue.text = record.toString();
/*
        binding.buttonNovoJogo.setOnClickListener {
            val intent = Intent(this, Tabuleiro::class.java);
            startActivityForResult(intent, 1);
            finish();
        }*/

/*
        binding.buttonSair.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java);
            startActivityForResult(intent, 2);
            finish();
        }*/


/*
        if(pontos != null && pontos > record){
            txtNewRecord.text = getResources().getString(R.string.new_record) + " " + pontos
            txtNewRecord.visibility = View.VISIBLE
            configuracoes.edit().putInt("record", pontos).commit()
        }
*/
    }
}